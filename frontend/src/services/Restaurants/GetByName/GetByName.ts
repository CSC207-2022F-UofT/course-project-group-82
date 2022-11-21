import { OuiClient } from "../../../networking/OuiClient";

export async function getByNameService(name: string): Promise<any[] | boolean> {
    let response = await OuiClient.get("/getRestaurantsByName", { name: name });

    if (response.successful) {
        let responseData = response.getData();
        return (responseData as any).restaurants;
    }

    return false;
}
