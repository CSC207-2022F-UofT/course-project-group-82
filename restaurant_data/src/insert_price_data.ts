import fs from "fs";
import { parsePriceRange, PriceRange, Restaurant } from "./data_types";
import csvParser from "csv-parser";

const EXPORTED_RESTAURANT_DB_PATH = "tmp/restaurants.json";
const DATASET_PATH = "data/toronto_restaurants.csv";
const OUTPUT_PATH = "tmp/restaurants.prices_inserted.json";

type GeoHash = string;

function restaurantGeoHash(lat: string, long: string): GeoHash {
    return lat + long;
}

async function aggregatePriceRangesFromCSV(
    csvPath: string
): Promise<Map<GeoHash, PriceRange | null>> {
    let out = new Map<GeoHash, PriceRange | null>();

    await new Promise<void>((resolve) => {
        fs.createReadStream(csvPath)
            .pipe(csvParser())
            .on("data", (data) => {
                let hash = restaurantGeoHash(
                    data["Restaurant Latitude"],
                    data["Restaurant Longitude"]
                );

                out.set(hash, parsePriceRange(data["Restaurant Price Range"]));
            })
            .on("close", () => {
                resolve();
            });
    });

    return out;
}

async function main(): Promise<void> {
    let restaurants: Restaurant[] = JSON.parse(
        fs.readFileSync(EXPORTED_RESTAURANT_DB_PATH, "utf-8")
    );

    let priceRanges = await aggregatePriceRangesFromCSV(DATASET_PATH);

    for (let restaurant of restaurants) {
        let hash = restaurantGeoHash(restaurant.lat, restaurant.long);
        let priceRange = priceRanges.get(hash);

        if (priceRange) {
            if (priceRange.max <= 10) {
                restaurant.priceLevel = 1;
            } else if (priceRange.max <= 30) {
                restaurant.priceLevel = 2;
            } else {
                restaurant.priceLevel = 3;
            }
        } else {
            restaurant.priceLevel = null;
        }
    }

    fs.writeFileSync(OUTPUT_PATH, JSON.stringify(restaurants));
}

main();
