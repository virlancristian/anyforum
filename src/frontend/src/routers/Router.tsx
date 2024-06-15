import { createBrowserRouter } from "react-router-dom";

import { loginRouter } from "./auth/LoginRouter";
import { registrationRouter } from "./auth/RegistrationRouter";

export const router = createBrowserRouter([
    loginRouter,
    registrationRouter
]);