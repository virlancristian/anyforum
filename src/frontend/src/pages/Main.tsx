import { RouterProvider } from "react-router-dom";

import PageHeader from "../components/header/PageHeader";
import { router } from "../routers/Router";

export default function MainPage() {
    return (
        <>
            <PageHeader />
            <RouterProvider router={router} />
        </>
    )
}