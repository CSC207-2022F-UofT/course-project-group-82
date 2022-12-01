import { Yelp } from "./yelp";
import dotenv from "dotenv";
import { assert } from "./util";
import { Business, Coordinates } from "./yelp_dtypes";
import fs from "fs";

dotenv.config();

const TORONTO: Coordinates = {
    latitude: 43.665040511677184,
    longitude: -79.38466903783511,
};

class RestaurantsDB {
    restaurants: Business[];
    private _seenIDs: Set<string>;

    constructor() {
        this.restaurants = [];
        this._seenIDs = new Set();
    }

    addRestaurants(restaurants: Business[]): void {
        for (let r of restaurants) {
            if (this._seenIDs.has(r.id)) continue;
            this.restaurants.push(r);
            this._seenIDs.add(r.id);
        }
    }
}

async function main(): Promise<void> {
    let apiKey = process.env.API_KEY;
    assert(Boolean(apiKey), "API key not provided");

    let yelp = new Yelp(apiKey!);
    let restaurants = new RestaurantsDB();

    restaurants.addRestaurants(
        await yelp.getAllBusinesses({
            search: {
                center: TORONTO,
                radius: 4000,
                priceQuery: "1",
            },
            pageSize: 50,
            verbose: true,
            limit: 1000,
        })
    );

    restaurants.addRestaurants(
        await yelp.getAllBusinesses({
            search: {
                center: TORONTO,
                radius: 4000,
                priceQuery: "2",
            },
            pageSize: 50,
            verbose: true,
            limit: 1000,
        })
    );

    restaurants.addRestaurants(
        await yelp.getAllBusinesses({
            search: {
                center: TORONTO,
                radius: 4000,
                priceQuery: "3",
            },
            pageSize: 50,
            verbose: true,
            limit: 1000,
        })
    );

    restaurants.addRestaurants(
        await yelp.getAllBusinesses({
            search: {
                center: TORONTO,
                radius: 4000,
                priceQuery: "4",
            },
            pageSize: 50,
            verbose: true,
            limit: 1000,
        })
    );

    fs.writeFileSync(
        "data/restaurants.json",
        JSON.stringify(restaurants.restaurants)
    );
}

main();
