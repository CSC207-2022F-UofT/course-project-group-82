export interface AddressInterface {
    streetAddress: string;
    city: string;
    province: string;
    zipCode: string;
}

export interface CategoryInterface {
    alias: string;
    title: string;
}

export interface LocationInterface {
    address1: string;
    address2: string;
    address3: string;
    city: string;
    zip_code: string;
    country: string;
    state: string;
    display_address: string[];
}

export interface CoordinatesInterface {
    latitude: number;
    longitude: number;
}

export interface RestaurantInterface {
    id: string;
    alias: string;
    name: string;
    image_url: string;
    url: string;
    review_count: number;
    phoneNumber: string;
    categories: CategoryInterface[];
    rating: number;
    coordinates: CoordinatesInterface;
    price: string;
    location: LocationInterface;
    phone: string;
    display_phone: string;
}
