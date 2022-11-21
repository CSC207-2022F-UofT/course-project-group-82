import axios from "axios";
import csvParser from "csv-parser";
import fs from "fs";
import jsdom from "jsdom";
import UserAgent from "user-agents";
import { CategoryDatabase, CategoryID } from "./category";
import { parseAddress, Restaurant } from "./data_types";

export class RestaurantDataAggregator {
    readonly datasetPath: string;
    readonly categories: CategoryDatabase;

    constructor(datasetPath: string, categories: CategoryDatabase) {
        this.datasetPath = datasetPath;
        this.categories = categories;
    }

    async getCanonicalYelpURL(yelpURL: string): Promise<string> {
        // detect redirects
        if (yelpURL.includes("adredir?")) {
            return (
                (
                    await axios.get(yelpURL, {
                        headers: { "User-Agent": new UserAgent().toString() },
                    })
                ).data as string
            ).match(/(?<=location\.replace\(").+(?="\))/g)![0];
        }

        return yelpURL;
    }

    async scrapeCategoryAndImageData(
        yelpURL: string,
        minMatches: number
    ): Promise<{ categories: CategoryID[]; photos: string[] }> {
        let response = await axios.get(yelpURL, {
            headers: { "User-Agent": new UserAgent().toString() },
        });
        let data = response.data as string;

        let dom = new jsdom.JSDOM(data);
        let document = dom.window.document;

        // yelp passes JSON data about the restaurant in specific script elements
        // scrape this data into `queryableText` to attempt to match categories
        // to the text contained inside.
        let queryableText = "";

        for (let script of document.querySelectorAll(
            `script[type="application/ld+json"]`
        )) {
            queryableText += script.innerHTML + "\n";
        }

        for (let script of document.querySelectorAll(
            `script[type="application/json"]`
        )) {
            queryableText += script.innerHTML + "\n";
        }

        queryableText = queryableText.toLowerCase();

        let categories = new Set<CategoryID>();

        for (let category of this.categories.getAllCategories()) {
            for (let query of category.searchKeywords) {
                let match = queryableText.match(
                    new RegExp("\\b" + query + "s?\\b", "g")
                );

                if (match && match.length >= minMatches) {
                    categories.add(category.id);

                    let implications = [...category.implicates];

                    while (implications.length) {
                        let impl = implications.shift()!;

                        if (categories.has(impl)) continue;
                        categories.add(impl);

                        let category = this.categories.getCategory(impl);
                        implications.push(...category.implicates);
                    }
                }
            }
        }
        // fs.writeFileSync("tmp/out.html", data);
        // Scrape image data
        let images = document.querySelectorAll<HTMLImageElement>(
            `img[class*="photo-header-media-image"]`
        );

        return {
            categories: Array.from(categories),
            photos: Array.from(images).map((image) => image.src),
        };
    }

    async augmentRestaurantData(restaurant: Restaurant): Promise<void> {
        try {
            // canonicalize yelp URL
            restaurant.yelpURL = await this.getCanonicalYelpURL(
                restaurant.yelpURL
            );

            let yelpData = await this.scrapeCategoryAndImageData(
                restaurant.yelpURL,
                4
            );
            restaurant.categories = yelpData.categories;
            restaurant.photos = yelpData.photos;
        } catch (e: any) {
            if (axios.isAxiosError(e)) {
                console.log(
                    `Error when fetching data for "${restaurant.name}": ` +
                        e.message
                );
            } else {
                console.log(
                    `Error when processing data for "${restaurant.name}": ` +
                        e.toString()
                );
            }
        }
    }

    async augmentRestaurantBatch(batch: Restaurant[]): Promise<void> {
        await Promise.all(batch.map((r) => this.augmentRestaurantData(r)));
    }

    async aggregate(): Promise<Restaurant[]> {
        let aggregatedRestaurants = await new Promise<Restaurant[]>(
            (resolve) => {
                let out: Restaurant[] = [];
                fs.createReadStream(this.datasetPath)
                    .pipe(csvParser())
                    .on("data", (row) => {
                        let address = parseAddress(row["Restaurant Address"]);
                        let yelpURL = row["Restaurant Yelp URL"];
                        if (!address) {
                            console.log(
                                `Address string ${JSON.stringify(
                                    row["Restaurant Address"]
                                )} is invalid`
                            );
                            return;
                        }

                        out.push({
                            name: row["Restaurant Name"],
                            phoneNumber: row["Restaurant Phone"],
                            address: address,
                            yelpURL: yelpURL,
                            lat: row["Restaurant Latitude"],
                            long: row["Restaurant Longitude"],
                            website: row["Restaurant Website"],
                            categories: [],
                            photos: [],
                            priceLevel: null,
                        });
                    })
                    .on("close", () => {
                        resolve(out);
                    });
            }
        );

        // remove restaurants with missing entries
        aggregatedRestaurants = aggregatedRestaurants.filter((res) => {
            return (
                Boolean(res.address) &&
                Boolean(res.phoneNumber) &&
                Boolean(res.name) &&
                Boolean(res.yelpURL)
            );
        });

        // remove duplicate restaurants (by using the phone number as a "hash")
        let restaurantIndex: { [phone: string]: Restaurant } = {};
        let restaurantDuplicates: { [phone: string]: number } = {};

        for (let restaurant of aggregatedRestaurants) {
            let phoneNumber = restaurant.phoneNumber;

            if (restaurantIndex[phoneNumber]) {
                if (!restaurantDuplicates[phoneNumber]) {
                    restaurantDuplicates[phoneNumber] = 0;
                }
                restaurantDuplicates[phoneNumber] += 1;
            } else {
                restaurantIndex[phoneNumber] = restaurant;
            }
        }

        console.log("Removed duplicate restaurants:");
        for (let [phone, number] of Object.entries(restaurantDuplicates)) {
            let restaurant = restaurantIndex[phone];
            console.log(
                `- Restaurant "${restaurant.name}" duplicated ${number} times`
            );
        }

        aggregatedRestaurants = Object.values(restaurantIndex);

        // add category data to aggregated restaurants

        let i = 1;
        let currentBatch: Restaurant[] = [];

        for (let restaurant of aggregatedRestaurants.slice(3265)) {
            console.log(
                `Fetching Yelp data from "${restaurant.name}"... (${i}/${aggregatedRestaurants.length})`
            );

            currentBatch.push(restaurant);

            if (currentBatch.length === 3) {
                await this.augmentRestaurantBatch(currentBatch);
                currentBatch = [];
                fs.writeFileSync(
                    "tmp/restaurants.json",
                    JSON.stringify(aggregatedRestaurants, null, 4)
                );
            }

            i += 1;
        }

        if (currentBatch.length) {
            await this.augmentRestaurantBatch(currentBatch);
        }

        fs.writeFileSync(
            "tmp/restaurants.json",
            JSON.stringify(aggregatedRestaurants, null, 4)
        );

        return aggregatedRestaurants;
    }
}
