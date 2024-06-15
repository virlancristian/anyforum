import { RouteObject } from "react-router-dom";
import RegistrationForm from "../../components/auth/RegistrationForm";

export const registrationRouter: RouteObject = {
    path: "/account/register",
    element: <RegistrationForm />
}