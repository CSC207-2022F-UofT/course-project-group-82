export interface Restaurant {
    name: string;
    phoneNumber: string;
    yelpURL: string;
    lat: string;
    long: string;
    website: string;

    address: Address;

    /** Stores categories by ID */
    categories: string[];

    photos: string[];

    priceLevel: number | null;
}

export interface PriceRange {
    min: number;
    max: number;
}

export interface Address {
    streetAddress: string; // e.g. "6233 Bathurst Street"
    city: string; // e.g. "Toronto"
    province: string; // e.g. "ON"
    zipCode: string; // e.g. "M2R 2A5"
}

export function formatAddress(address: Address): string {
    return `${address.streetAddress} ${address.city}, ${address.province} ${address.zipCode}`;
}

/**
 * Return `null` if the address could not be parsed for whatever reason
 */
export function parseAddress(addressString: string): Address | null {
    const assert = (condition: boolean) => {
        if (!condition) throw "assertionError";
    };

    try {
        let parts = addressString.split(",");

        assert(parts.length == 2);

        let [addressCity, provinceZip] = parts;
        let addressCityParts = addressCity.split("\n");
        assert(addressCityParts.length >= 2);
        let address = addressCityParts.slice(0, -1).join(" ");
        let city = addressCityParts[addressCityParts.length - 1];

        provinceZip = provinceZip.trim();
        let provinceZipParts = provinceZip.split(" ");
        assert(provinceZipParts.length >= 2);
        let province = provinceZipParts[0];
        let zipParts = provinceZipParts.slice(1);

        return {
            streetAddress: address,
            city: city,
            province: province,
            zipCode: zipParts.join(" "),
        };
    } catch (e) {
        if (e === "assertionError") return null;
        throw e;
    }
}

/**
 * Return `null` if the price range could not be parsed for whatever reason
 */
export function parsePriceRange(rangeString: string): PriceRange | null {
    const assert = (condition: boolean) => {
        if (!condition) throw "assertionError";
    };

    try {
        if (rangeString.startsWith("Under ")) {
            let match = rangeString.match(/\d+/g);
            assert(match !== null);
            let max = Number.parseInt(match![0]);

            assert(!Number.isNaN(max));

            return {
                min: 0,
                max: max,
            };
        }

        assert(rangeString.startsWith("$"));
        rangeString = rangeString.slice(1);

        let parts = rangeString.split("-");
        assert(parts.length == 2);

        let [minString, maxString] = parts;

        let min = Number.parseInt(minString);
        let max = Number.parseInt(maxString);

        assert(!Number.isNaN(min) && !Number.isNaN(max));

        return {
            min: min,
            max: max,
        };
    } catch (e) {
        if (e === "assertionError") return null;
        throw e;
    }
}
