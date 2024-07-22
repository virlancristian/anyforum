import { RouteObject } from "react-router-dom";

import MainPage from "../../pages/Main";
import EditProfile from "../../components/profile/EditProfile";

export const editProfileRouter: RouteObject = {
    path: "/profile/edit",
    element: <MainPage children={<EditProfile />} />
}