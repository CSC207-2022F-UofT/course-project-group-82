import axios from "axios";
import {sleep} from "./util";
import {Business, BusinessSearchResponse, Coordinates} from "./yelp_dtypes";

export const YELP_API_URL_BASE = "https://api.yelp.com/v3";

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

    async getAllBusinesses(args: {
        search: BusinessSearchOptions;
        pageSize: number;
        limit?: number;
        verbose?: boolean;
    }): Promise<Business[]> {
        let allBusinesses: Business[] = [];
        let search = args.search;

        let offset = 0;

        while (true) {
            let response: YelpResponse<BusinessSearchResponse> =
                await this._rateLimitProtectedGet("/businesses/search", {
                    latitude: search.center.latitude,
                    longitude: search.center.longitude,
                    radius: search.radius,
                    limit: args.pageSize,
                    offset: offset,
                });

            let businesses = response.data.businesses;

            allBusinesses.push(...businesses);
            console.log(
                `aggreggated ${allBusinesses.length} businesses (total: ${response.data.total})`
            );
            offset += businesses.length;

            if (
                businesses.length < args.pageSize ||
                allBusinesses.length >= (args.limit ?? Infinity)
            ) {
                return allBusinesses;
            }
        }
    }

    async getRestaurantsInRadiusFromCenter(args: {
        radius: number,
        center: Coordinates,
    }) {
        return await this.getAllBusinesses({
            search: {
                center: args.center,
                radius: args.radius,
            },
            pageSize: 50,
            verbose: true,
            limit: 1000,
        });
    }
}
