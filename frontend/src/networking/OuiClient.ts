import axios from "axios";
import { API_BASE } from "../../env";
import { APIResponse } from "./OuiResponse";

export class OuiClient {
    static async get<T>(
        endpoint: string,
        query: { [k: string]: string } = {}
    ): Promise<APIResponse<T>> {
        try {
            let response = await axios({
                method: "GET",
                baseURL: API_BASE,
                url: endpoint + "?" + new URLSearchParams(query).toString(),
                responseType: "json",
            });

            return APIResponse.fromAPIResponseObject(response.data);
        } catch (e: any) {
            return APIResponse.apiRequestFailed(e.toString());
        }
    }

    static async post<T>(endpoint: string, data: any): Promise<APIResponse<T>> {
        try {
            let response = await axios({
                method: "POST",
                baseURL: API_BASE,
                url: endpoint,
                responseType: "json",
                data: data,
            });

            return APIResponse.fromAPIResponseObject(response.data);
        } catch (e: any) {
            return APIResponse.apiRequestFailed(e.toString());
        }
    }
}
