import axios from "axios";
import { sleep } from "./util";
import { Business, BusinessSearchResponse, Coordinates } from "./yelp_dtypes";

export const YELP_API_URL_BASE = "https://api.yelp.com/v3";

const LAT_METERS_UNIT = 111320;

function getMetersPerDegLatitude(): number {
    return LAT_METERS_UNIT;
}

function getMetersPerDegLongitude(latitude: number): number {
    return 111320 * Math.cos(latitude);
}

function getDegLatitiduePerMeter(): number {
    return 1 / LAT_METERS_UNIT;
}

function getDegLongitudePerMeter(latitude: number): number {
    return 1 / getMetersPerDegLongitude(latitude);
}

export class BusinessAggregator {
    private _businesses: Business[];
    private _seenIDs: Set<string>;

    constructor() {
        this._businesses = [];
        this._seenIDs = new Set();
    }

    addBusinesses(restaurants: Business[]): void {
        for (let r of restaurants) {
            if (this._seenIDs.has(r.id)) continue;
            this._businesses.push(r);
            this._seenIDs.add(r.id);
        }
        this.removeDuplicates();
    }

    getBusinesses(): Business[] {
        return [...this._businesses];
    }

    count(): number {
        return this._businesses.length;
    }

    removeDuplicates(): void {
        this._businesses = this._businesses.filter((r, i, arr) => {
            return arr.findIndex((r2) => r2.id === r.id) === i;
        });
    }
}

export enum ResponseStatus {
    Ok,
    Invalid,
    Unauthorized,
    NotFound,
    RateLimited,
    InternalError,
    Unknown,
}

export type GetQuery = { [k: string]: string | number | boolean };

export interface BusinessSearchOptions {
    /**
     * The center of the search query
     */
    center: Coordinates;

    /**
     * In meters
     */
    radius: number;

    /**
     * A comma separated string of numbers
     */
    priceQuery?: string;
}

export class YelpResponse<T> {
    private _data: T | null;
    readonly status: ResponseStatus;

    private constructor(data: T | null, status: ResponseStatus) {
        this._data = data;
        this.status = status;
    }

    get ok(): boolean {
        return this.status === ResponseStatus.Ok;
    }

    get rateLimited(): boolean {
        return this.status === ResponseStatus.RateLimited;
    }

    get data(): T {
        if (!this.ok) {
            throw new Error("Cannot get response data from failed request");
        }

        return this._data!;
    }

    static Ok<T>(data: T): YelpResponse<T> {
        return new YelpResponse(data, ResponseStatus.Ok);
    }

    static Err<T>(status: ResponseStatus): YelpResponse<T> {
        return new YelpResponse<T>(null, status);
    }
}

export class Yelp {
    readonly apiKey: string;

    constructor(apiKey: string) {
        this.apiKey = apiKey;
    }

    private _getAuthorizationHeaderValue(): string {
        return "Bearer " + this.apiKey;
    }

    private _makeResponse<T>(status: number, data: any): YelpResponse<T> {
        switch (status) {
            case 200:
                return YelpResponse.Ok(data);
            case 400:
                return YelpResponse.Err(ResponseStatus.Invalid);
            case 401:
                return YelpResponse.Err(ResponseStatus.Unauthorized);
            case 404:
                return YelpResponse.Err(ResponseStatus.NotFound);
            case 429:
                return YelpResponse.Err(ResponseStatus.RateLimited);
            case 500:
                return YelpResponse.Err(ResponseStatus.InternalError);
            case 503:
                return YelpResponse.Err(ResponseStatus.InternalError);
            default:
                return YelpResponse.Err(ResponseStatus.Unknown);
        }
    }

    private async _get<T>(
        url: string,
        query: GetQuery = {}
    ): Promise<YelpResponse<T>> {
        if (query) {
            let _query: { [k: string]: string } = {};

            for (let [k, v] of Object.entries(query)) {
                _query[k] = v.toString();
            }

            url += "?" + new URLSearchParams(_query).toString();
        }

        try {
            let response = await axios.get(url, {
                baseURL: YELP_API_URL_BASE,
                headers: {
                    Authorization: this._getAuthorizationHeaderValue(),
                    Accept: "application/json",
                    "Accept-Encoding": "identity",
                },
                decompress: true,
            });

            let status = response.status;
            return this._makeResponse(status, response.data);
        } catch (e: any) {
            if (axios.isAxiosError(e)) {
                return this._makeResponse(e.status ?? 0, null);
            }
            throw e;
        }
    }

    private async _rateLimitProtectedGet<T>(
        url: string,
        query: GetQuery = {}
    ): Promise<YelpResponse<T>> {
        while (true) {
            let response = await this._get<T>(url, query);

            if (response.rateLimited) {
                await sleep(1000);
                continue;
            }

            return response;
        }
    }

    async getNumberOfBusinessesInRadius(args: {
        center: Coordinates;
        radius: number;
    }): Promise<number> {
        let response: YelpResponse<BusinessSearchResponse> =
            await this._rateLimitProtectedGet("/businesses/search", {
                latitude: args.center.latitude,
                longitude: args.center.longitude,
                radius: args.radius,
            });

        return response.data.total;
    }

    /**
     * width, height, safeRadius in meters
     */
    async getAllRestaurantsInRectangle(args: {
        center: Coordinates;
        width: number;
        height: number;
        safeRadius: number;
        pageSize: number;
        limit?: number;
        verbose?: boolean;
    }): Promise<Business[]> {
        let aggregator = new BusinessAggregator();

        let squareWidth = Math.sqrt(2 * args.safeRadius * args.safeRadius);

        let latMin =
            args.center.latitude - (getDegLatitiduePerMeter() * args.width) / 2;
        let latMax =
            args.center.latitude + (getDegLatitiduePerMeter() * args.width) / 2;

        let i = 0;

        for (
            let lat = latMin;
            lat + (squareWidth * getDegLatitiduePerMeter()) / 2 < latMax;
            lat += getDegLatitiduePerMeter() * squareWidth
        ) {
            let longMin =
                args.center.longitude -
                (getDegLongitudePerMeter(lat) * args.height) / 2;
            let longMax =
                args.center.longitude +
                (getDegLongitudePerMeter(lat) * args.height) / 2;
            for (
                let long = longMin;
                long + (squareWidth * getDegLongitudePerMeter(lat)) / 2 <
                longMax;
                long += getDegLongitudePerMeter(lat) * squareWidth
            ) {
                if (args.verbose) {
                    console.log(
                        `Finding restaurants within ${args.safeRadius}m centered at ${lat},${long}`
                    );
                }
                let numBefore = aggregator.count();
                let newBusinesses = await this.getAllBusinessesInRadius({
                    center: {
                        latitude: lat,
                        longitude: long,
                    },
                    radius: args.safeRadius,
                    pageSize: args.pageSize,
                    limit: args.limit,
                    verbose: args.verbose,
                    throwOnTotalExceedsLimit: true,
                });

                aggregator.addBusinesses(newBusinesses);

                let numAdded = aggregator.count() - numBefore;

                if (args.verbose) {
                    console.log(
                        `Added ${numAdded} businesses; ${
                            newBusinesses.length - numAdded
                        } were duplicates/overlapping; total: ${aggregator.count()}`
                    );
                }

                i += 1;
            }
        }
        return aggregator.getBusinesses();
    }

    /**
     * radius in meters
     */
    async getAllBusinessesInRadius(args: {
        center: Coordinates;
        radius: number;
        pageSize: number;
        limit?: number;
        verbose?: boolean;
        throwOnTotalExceedsLimit?: boolean;
    }): Promise<Business[]> {
        let allBusinesses: Business[] = [];
        let limit = args.limit ?? 1000;

        let offset = 0;

        while (true) {
            let response: YelpResponse<BusinessSearchResponse> =
                await this._rateLimitProtectedGet("/businesses/search", {
                    latitude: args.center.latitude,
                    longitude: args.center.longitude,
                    radius: args.radius,
                    limit: args.pageSize,
                    offset: offset,
                });

            if (response.data.total > limit && args.throwOnTotalExceedsLimit) {
                throw new Error(
                    `Provided search returned more than ${limit} businesses (got ${response.data.total})`
                );
            }

            let businesses = response.data.businesses;

            allBusinesses.push(...businesses);

            if (args.verbose) {
                console.log(
                    `aggreggated ${allBusinesses.length} businesses (total: ${response.data.total})`
                );
            }

            offset += businesses.length;

            if (
                businesses.length < args.pageSize ||
                allBusinesses.length >= limit
            ) {
                return allBusinesses;
            }
        }
    }
}
