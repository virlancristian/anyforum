import { RouteObject, RouterProps } from "react-router-dom";
import LoginForm from "../../components/auth/LoginForm";
import MainPage from "../../pages/Main";

export const loginRouter: RouteObject = {
    path: "/account/login",
    element: <MainPage children={<LoginForm />}/>
}