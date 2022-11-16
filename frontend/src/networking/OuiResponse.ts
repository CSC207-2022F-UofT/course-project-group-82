export interface OuiResponseType<T extends {[k: string]: any} | null> {
    dateTime: string;
    status: string;
    data: T;
    origin: string;
    destination: string;
};

export class OuiResponse<T extends {[k: string]: any} | null> {
    dateTime: string;
    status: string;
    data: T;
    origin: string;
    destination: string;

    constructor(response: OuiResponseType<T>) {
        this.dateTime = response.dateTime;
        this.data = response.data;
        this.origin = response.origin;
        this.destination = response.destination;
        this.status = response.status;
    }
}
