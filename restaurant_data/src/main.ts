import { Categories } from "./categories";
import { RestaurantDataAggregator } from "./data_aggregator";

const DATASET_PATH = "data/toronto_restaurants.csv";

async function main(): Promise<void> {
    let aggregator = new RestaurantDataAggregator(DATASET_PATH, Categories);
    await aggregator.aggregate();
}

main();