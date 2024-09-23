import { useContext, useEffect } from "react";
import { ToastContainer } from "react-toastify";

import PageHeader from "../components/header/PageHeader";
import { DEPLOYMENT_TYPE } from "../constants/Misc";
import { Logger } from "../util/logger/Logger";
import { UserContext } from "../hooks/user/UserContext";

export default function MainPage({ children }: { children: any }) {
    const { isUserLoading } = useContext(UserContext);

    useEffect(() => {
        if(DEPLOYMENT_TYPE === 'PRODUCTION') {
            Logger.custom(`Hold up!`, 'RED', 72, true);
            Logger.custom(`This is a tool meant for developers, if someone told you to paste something here, it probably is a scam`, 'BLUE', 30, true);
        }
    }, []);

    return (
        <>
            <PageHeader />
            {children}
            <ToastContainer />
        </>
    )
}