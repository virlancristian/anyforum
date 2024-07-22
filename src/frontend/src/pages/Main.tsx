import { RouterProvider } from "react-router-dom";
import { ToastContainer } from "react-toastify";

import PageHeader from "../components/header/PageHeader";
import { router } from "../routers/Router";

export default function MainPage({ children }: { children: any }) {
    return (
        <>
            <PageHeader />
            {children}
            <ToastContainer />
        </>
    )
}