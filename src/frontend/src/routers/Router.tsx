import { createBrowserRouter } from "react-router-dom";

import { loginRouter } from "./auth/LoginRouter";
import { registrationRouter } from "./auth/RegistrationRouter";
import { profileViewRouter } from "./profile/ProfileViewRouter";
import { editProfileRouter } from "./profile/EditProfileRouter";

export const router = createBrowserRouter([
    loginRouter,
    registrationRouter,
    profileViewRouter,
    editProfileRouter
]);