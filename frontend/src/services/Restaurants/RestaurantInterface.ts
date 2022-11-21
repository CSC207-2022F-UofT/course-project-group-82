export interface AddressInterface {
    streetAddress: string;
    city: string;
    province: string;
    zipCode: string;
}

export interface RestaurantInterface {
    id: string;
    name: string;
    phoneNumber: string;
    address: AddressInterface;
    yelpURL: string;
    lat: string;
    long: string;
    website: string;
    categories: string[];
    photos: string[];
}
