# Restaurant Data Aggregator

The Oui-Eat restaurant database is based off of [a Kaggle dataset of Toronto restaurants](https://www.kaggle.com/datasets/kevinbi/toronto-restaurants), a copy of which is included in `data/toronto_restaurants`.

The dataset contains about 7,400 unique restaurants and was published around 2020.

This module does the following:
 - Cleans the dataset, removing entries with missing or invalid fields as well as duplicate entries.
 - Scrapes the provided Yelp page for photos of the restaurant
 - Scrapes the provided Yelp page for reviews, using them to attempt to automatically categorize the restaurant into a number of preset categories (defined in `src/categories.ts`)
 - Transform the dataset into JSON format

## Usage

```
npm install
```
to install dependencies.

```
npm start
```
to run the module. The output of the module is written to `tmp/restaurants.json`.

### Experimental

```
npm run chain-normalization
```
to make all restaurants with the same name also share the same categories.