import { useContext, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { DashboardPageController } from "./DashboardPageController";
import { OuiRecommendations } from "./types/OuiRecommendations";

export function DashboardPageModel(props: { navigation: any }) {
    const { userID, setUserID } = useContext(UserContext);
    const [restaurantRecommendations, setRestaurantRecommendations] = useState<
        OuiRecommendations[]
    >([
        {
            id: 1,
            recommendedByName: "Curry Muncher",
            recommendedByUsername: "currymuncher27",
            recommendedByProfilePictureLink:
                "https://gravatar.com/avatar/35cc0f86ca226c87e0d75b711d0601f7?s=400&d=robohash&r=x",
            timestamp: "2021-04-01 12:00:00",
            recommends: true,
            restaurantName: "Mabu Generation",
            restaurantAddress: "578 Yonge St, Toronto, ON M4Y 1Z3",
            restaurantImageLink:
                "https://eatingyyz.com/wp-content/uploads/2021/12/116881662_332188711145548_7041157667196461624_n.jpg",
            restaurantForTags: ["Noodles", "Japanase"],
        },
        {
            id: 2,
            recommendedByName: "Kimchi Chower",
            recommendedByUsername: "kimchichower82",
            recommendedByProfilePictureLink:
                "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?f=y",
            timestamp: "2021-04-08 12:32:03",
            recommends: false,
            restaurantName: "Kimchi Korea House",
            restaurantAddress: "149 Dundas St W, Toronto, ON M5G 1C5",
            restaurantImageLink:
                "https://media.blogto.com/listings/fa72-20121005-Kimchi5.jpg?w=2048&cmd=resize_then_crop&height=1365&quality=70",
            restaurantForTags: ["Kimchi", "Korean"],
        },
    ]);

    const controllerProps = {
        ...props,
        userID,
        setUserID,
        restaurantRecommendations,
        setRestaurantRecommendations,
    };

    return <DashboardPageController {...controllerProps} />;
}
