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

async function estimateSafeRadius(
    yelp: Yelp,
    restaurantLimit: number
): Promise<number> {
    let safeRadius = 100;

    while (true) {
        let restaurantsInRadius = await yelp.getNumberOfBusinessesInRadius({
            center: TORONTO,
            radius: safeRadius,
        });

        console.log(
            `Restaurants in ${safeRadius}m radius: ${restaurantsInRadius}`
        );

        if (restaurantsInRadius < 600) {
            safeRadius += 100;
        } else {
            return safeRadius;
        }
    }
}

async function main(): Promise<void> {
    let apiKey = process.env.API_KEY;
    let safeRadiusString = process.env.SAFE_RADIUS;
    assert(Boolean(apiKey), "API key not provided");

    let yelp = new Yelp(apiKey!);

    // compute safe radius

    let safeRadius: number;
    if (safeRadiusString) {
        safeRadius = Number.parseFloat(safeRadiusString);
    } else {
        safeRadius = await estimateSafeRadius(yelp, 600);
    }

    console.log("Safe radius: " + safeRadius);

    let restaurants = await yelp.getAllRestaurantsInRectangle({
        center: TORONTO,
        width: 8000,
        height: 6000,
        safeRadius: safeRadius,
        pageSize: 50,
        verbose: true,
    });

    fs.writeFileSync("data/restaurants.json", JSON.stringify(restaurants));
}

main();
