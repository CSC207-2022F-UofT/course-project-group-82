import axios from "axios";
import { OuiResponse } from "./OuiResponse";

export const BASE_URL = process.env.BASE_URL || "http://10.0.0.201:8080";

export class OuiRequest {
    static async makeRequest<T>(url: string, data: any, method: string) {
        try {
            let response = await axios({
                method,
                baseURL: BASE_URL,
                url,
                responseType: "json",
                data,
            });

            return new OuiResponse(response.data);
        } catch (e) {
            // In the case of a network or other error, return a default
            // error response.

            console.warn("Error: " + e);
            return new OuiResponse({
                dateTime: new Date().toTimeString(),
                status: "error",
                data: null,
                destination: "client",
                origin: "OuiRequest",
            });
        }
    }


}
