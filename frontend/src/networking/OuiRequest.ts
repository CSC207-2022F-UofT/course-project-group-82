import axios from "axios";
import { OuiResponse } from "./OuiResponse";

export class OuiRequest {
    // Change this to the hosted url once uploaded
    // Change this to your computer's local ip address
    static baseUrl: string = process.env.BASE_URL || "http://10.0.0.201:8080";

    static async make(url: string, data: any, method: string) {
        try {
            let response = await axios({
                method,
                baseURL: this.baseUrl,
                url,
                responseType: "json",
                data,
            });
            return new OuiResponse(response.data);
        } catch (e) {
            console.warn("Error: " + e);
            return new OuiResponse({
                dateTime: new Date().toTimeString(),
                status: "error",
                data: "",
                destination: "client",
                origin: "OuiRequest",
            });
        }
    }
}
