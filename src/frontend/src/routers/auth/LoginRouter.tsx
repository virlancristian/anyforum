import { RouteObject, RouterProps } from "react-router-dom";
import LoginForm from "../../components/auth/LoginForm";

export const loginRouter: RouteObject = {
    path: "/account/login",
    element: <LoginForm />
}