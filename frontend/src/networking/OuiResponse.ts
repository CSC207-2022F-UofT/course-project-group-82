export type OuiResponseType = {
    dateTime: string;
    status: string;
    data: string;
    origin: string;
    destination: string;
};

export class OuiResponse {
    dateTime: string;
    status: string;
    data: string;
    origin: string;
    destination: string;
    parsedData: undefined | boolean | Object;

    constructor(response: OuiResponseType) {
        this.dateTime = response.dateTime;
        this.data = response.data;
        this.origin = response.origin;
        this.destination = response.destination;
        this.status = response.status;

        try {
            this.parsedData = JSON.parse(this.data);
        } catch (e) {
            this.parsedData = false;
        }
    }
}
