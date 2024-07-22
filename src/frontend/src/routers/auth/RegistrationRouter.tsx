import { RouteObject } from "react-router-dom";

import RegistrationForm from "../../components/auth/RegistrationForm";
import MainPage from "../../pages/Main";

export const registrationRouter: RouteObject = {
    path: "/account/register",
    element: <MainPage children={<RegistrationForm />}/>
}