
/**
 * Represents an API response with
 * response data type `T` (type of `data` field in API response)
 */
export class APIResponse<T> {
    private _dateTime: string;
    private _ok: boolean;
    private _origin: string;
    private _destination: string;
    private _errorMessage: string | null;
    private _data: T | null;

    private constructor(
        dateTime: string,
        ok: boolean,
        origin: string,
        destination: string,
        errorMessage: string | null,
        data: T | null
    ) {
        this._dateTime = dateTime;
        this._ok = ok;
        this._origin = origin;
        this._destination = destination;
        this._errorMessage = errorMessage;
        this._data = data;

        // sanity check; make sure error message is present if `ok === false`
        if (!ok && errorMessage === null)
            throw new Error("Error response must have error message");
    }

    static fromAPIResponseObject<T>(data: any): APIResponse<T> {
        let responseOk = data.status === "success";
        let errorMessage: string | null = null;

        if (!responseOk) {
            errorMessage = data.data.errorMessage;
        }

        return new APIResponse(
            data.dateTime,
            responseOk,
            data.origin,
            data.destination,
            errorMessage,
            // only represent data if response succeeded
            responseOk ? data.data : null
        );
    }

    static apiRequestFailed(message: string): APIResponse<any> {
        return new APIResponse(
            new Date().toTimeString(),
            false,
            "unknown",
            "client",
            "API request failed: " + message,
            null
        );
    }

    getDateTime(): Date {
        return new Date(Date.parse(this._dateTime));
    }

    getOrigin(): string {
        return this._origin;
    }

    getDestination(): string {
        return this._destination;
    }

    getErrorMessage(): string {
        if (this.successful)
            throw new Error("Response succeeded; no error message provided");
        return this._errorMessage!;
    }

    getData(): T {
        if (!this.successful)
            throw new Error("No response data; response failed");
        return this._data!;
    }

    /**
     * `true` if the API response `status` field was `"success"`.
     */
    get successful(): boolean {
        return this._ok;
    }
}
