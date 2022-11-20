import { Category, CategoryDatabase } from "./category";
import fs from "fs";

export const Categories = new CategoryDatabase()
    // Cuisines
    .addCategory(new Category("asian", "Asian"))
    .addCategory(new Category("chinese", "Chinese", ["chinese"], ["asian"]))
    .addCategory(new Category("japanese", "Japanese", ["japanese"], ["asian"]))
    .addCategory(new Category("korean", "Korean", ["korean"], ["asian"]))
    .addCategory(
        new Category(
            "indian",
            "Indian",
            ["indian", "tandoori", "masala"],
            ["asian"]
        )
    )
    .addCategory(
        new Category("pakistani", "Pakistani", ["pakistani"], ["asian"])
    )
    .addCategory(new Category("thai", "Thai", ["thai"], ["asian"]))
    .addCategory(
        new Category(
            "vietnamese",
            "Vietnamese",
            ["vietnamese", "viet", "pho"],
            ["asian"]
        )
    )
    .addCategory(
        new Category("african", "African", [
            "african",
            "ethiopian",
            "egyptian",
            "nigerian",
            "kenya",
            "kenyan",
            "morocco",
            "moroccan",
            "ghana",
            "ghanaian",
            "sudan",
            "sudanese",
        ])
    )
    .addCategory(
        new Category("east_european", "Eastern European", [
            "russia",
            "russian",
            "poland",
            "polish",
            "romania",
            "romanian",
            "hungary",
            "hungarian",
            "bulgaria",
            "bulgarian",
            "belarus",
            "lithuania",
            "lithuanian",
            "czech",
        ])
    )
    .addCategory(new Category("italian", "Italian", ["italian", "italy"]))
    .addCategory(new Category("french", "French", ["french", "france"]))
    .addCategory(new Category("spanish", "Spanish", ["spain", "spanish"]))
    .addCategory(
        new Category("latin_american", "Latin American", [
            "mexican",
            "mexico",
            "taco",
            "burrito",
            "argentina",
            "argentinian",
            "bolivia",
            "bolivian",
            "brazil",
            "brazilian",
            "chile",
            "chilean",
            "colombia",
            "colombian",
            "costa rica",
            "costa rican",
            "cuba",
            "cuban",
            "dominican",
            "ecuador",
            "peru",
            "peruvian",
            "venezuela",
            "venezuelan",
        ])
    )
    .addCategory(new Category("afghan", "Afghan", ["afghan"]))
    .addCategory(new Category("lebanese", "Lebanese", ["lebanon"]))
    .addCategory(
        new Category("caribbean", "Caribbean", [
            "caribbean",
            "jamaica",
            "jamaican",
        ])
    )
    .addCategory(new Category("greek", "Greek", ["greek", "gyro", "greece"]))
    .addCategory(new Category("turkish", "Turkish", ["turkish"]))
    // Specific food items
    .addCategory(
        new Category("ramen", "Ramen", ["ramen"], ["japanese", "noodles"])
    )
    .addCategory(new Category("noodles", "Noodles", ["noodle", "mein"]))
    .addCategory(
        new Category(
            "burgers",
            "Burgers",
            ["burger", "hamburger", "cheeseburger"],
            ["grill"]
        )
    )
    .addCategory(new Category("noodles", "Noodles", ["noodle", "mein"]))
    .addCategory(
        new Category("burritos", "Burritos", ["burrito"], ["latin_american"])
    )
    .addCategory(new Category("pizza", "Pizza", ["pizza"]))
    .addCategory(new Category("ice_cream", "Ice Cream", ["ice cream"]))
    .addCategory(
        new Category("fast_food", "Fast Food", [
            "fast food",
            "mcdonalds",
            "burger king",
        ])
    )
    .addCategory(new Category("poutine", "Poutine", ["poutine"]))
    .addCategory(
        new Category("sushi", "Sushi", ["sushi", "sashimi"], ["japanese"])
    )
    .addCategory(
        new Category("steak", "Steak", ["steak", "steakhouse", "wagyu"])
    )
    .addCategory(new Category("shawarma", "Shawarma", ["shawarma"]))
    .addCategory(
        new Category("fried_chicken", "Fried Chicken", [
            "fried chicken",
            "popeyes",
            "chicken tenders",
        ])
    )
    // Generic categories
    .addCategory(new Category("grill", "Grill"))
    .addCategory(new Category("fusion", "Fusion", ["fusion"]))
    .addCategory(new Category("cafe", "Café", ["cafe"], ["coffee"]))
    .addCategory(
        new Category("bakery", "Bakery", ["pastry", "pastries", "bakery"])
    )
    .addCategory(
        new Category("seafood", "Seafood", [
            "seafood",
            "lobster",
            "clam",
            "oyster",
        ])
    )
    // Drinks
    .addCategory(new Category("drinks", "Drinks"))
    .addCategory(
        new Category(
            "boba",
            "Bubble Tea",
            ["boba", "bubble tea"],
            ["asian", "drinks"]
        )
    )
    .addCategory(
        new Category("coffee", "Coffee", ["coffee", "cafe"], ["drinks"])
    )
    .addCategory(
        new Category(
            "alcohol",
            "Serves Alcohol",
            [
                "wine",
                "beer",
                "soju",
                "llbo",
                "liquor",
                "vodka",
                "tequila",
                "cocktail",
            ],
            ["drinks"]
        )
    )
    // Dietary
    .addCategory(
        new Category("vegetarian", "Vegetarian Options", ["vegetarian"])
    )
    .addCategory(
        new Category("vegan", "Vegan Options", ["vegan"], ["vegetarian"])
    )
    .addCategory(new Category("halal", "Halal Options", ["halal"]))
    .addCategory(new Category("kosher", "Kosher Options", ["kosher"]));

export function exportCategoryData(): void {
    fs.writeFileSync(
        "tmp/categories.json",
        JSON.stringify(Categories.getAllCategories())
    );
}
