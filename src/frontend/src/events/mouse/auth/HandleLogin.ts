import { Dispatch, SetStateAction } from "react";
import { NavigateFunction } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import Cookies from "universal-cookie";

import AuthForm from "../../../models/user/AuthForm";
import User from "../../../models/user/User";
import sanitizeInputs from "../../../util/auth/SanitizeInputs";
import { AUTH_ERROR_MESSAGES } from "../../../constants/AuthErrorMessages";
import { AuthApi } from "../../../api/auth/AuthApi";
import showNotification from "../../../util/notification/ShowNotification";
import { SessionApi } from "../../../api/session/SessionApi";

export default async function handleLogin(authFormData: AuthForm,
                                            setIsError: Dispatch<SetStateAction<boolean>>,
                                            setErrorMessage: Dispatch<SetStateAction<string>>,
                                            login: (user: User) => void,
                                            navigate: NavigateFunction) {
    const sanitizerCode = sanitizeInputs(authFormData);

    if(sanitizerCode > 0) {
        setIsError(true);
        setErrorMessage(AUTH_ERROR_MESSAGES[sanitizerCode]);
        return;
    }

    const authServiceResponse = await AuthApi.login(authFormData);

    if(authServiceResponse.status !== 200) {
        showNotification(authServiceResponse.data.message, 2);
        return;
    }

    const decodedToken = jwtDecode(authServiceResponse.data.token);
    const decodedTokenData: string = decodedToken.sub ? decodedToken.sub : '';

    const sessionServiceResponse = await SessionApi.createSession(decodedTokenData);

    if(sessionServiceResponse.status !== 200) {
        showNotification(`There was an error while trying to create the session`, 2);
        return;
    }

    const { user, sessionID, sessionToken, sessionExpiryDate } = SessionApi.decodeSessionToken(sessionServiceResponse.data.token);
    const cookies = new Cookies(null, { path: '/' });
    const previousAuthURLPath: string = window.localStorage.getItem(`anytopic-preauth-path`) || "/";

    login(user);
    cookies.set(`anytopic-session-id`, sessionID, { expires: new Date(sessionExpiryDate) });
    cookies.set(`anytopic-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });
    navigate(previousAuthURLPath);
}