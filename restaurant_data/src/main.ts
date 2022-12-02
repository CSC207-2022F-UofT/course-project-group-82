import { Yelp } from "./yelp";
import dotenv from "dotenv";
import { assert } from "./util";
import { Business, Coordinates } from "./yelp_dtypes";
import fs from "fs";

dotenv.config();

let TORONTO: Coordinates = {
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
        this.removeDuplicates();
    }

    removeDuplicates(): void {
        this.restaurants = this.restaurants.filter((r, i, arr) => {
            return arr.findIndex((r2) => r2.id === r.id) === i;
        });
    }
}

async function main(): Promise<void> {
    let restaurants = new RestaurantsDB();

    let i = 1;
    while (restaurants.restaurants.length < 5000 && i <= 50) {
        console.log(`iteration ${i}`);
        restaurants.addRestaurants(
            (await getRestaurantsAtRadius({radius: 500})).restaurants
        );
        console.log("Total DB: " + restaurants.restaurants.length);
        get_new_position(-500, 500);
        i++;
    }

    fs.writeFileSync(
        "data/restaurants.json",
        JSON.stringify(restaurants.restaurants)
    );
}

async function getRestaurantsAtRadius(args: {radius: number}): Promise<RestaurantsDB> {
    let apiKey = process.env.API_KEY;
    assert(Boolean(apiKey), "API key not provided");

    let yelp = new Yelp(apiKey!);
    let restaurants = new RestaurantsDB();

    restaurants.addRestaurants(
        await yelp.getRestaurantsInRadiusFromCenter({
            radius: args.radius,
            center: TORONTO,
        })
    )
    return restaurants;
}

// dx, dy are changes in meters
function get_new_position(dx: number, dy: number) {
    const r_earth = 6378100; // meters
    let new_lat = TORONTO.latitude + (dx / r_earth) * (180 / Math.PI);
    let new_long =
        TORONTO.longitude +
        ((dx / r_earth) * (180 / Math.PI)) /
            Math.cos((TORONTO.latitude * Math.PI) / 180);

    TORONTO.latitude = new_lat;
    TORONTO.longitude = new_long;
}

main();
