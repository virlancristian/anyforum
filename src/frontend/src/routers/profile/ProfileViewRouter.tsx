import { RouteObject } from "react-router-dom";

import ProfileView from "../../components/profile/ProfileView";
import MainPage from "../../pages/Main";

export const profileViewRouter: RouteObject = {
    path: "/user/:username",
    element: <MainPage children={<ProfileView />} />
}