import { Dispatch, SetStateAction } from "react";
import { NavigateFunction } from "react-router-dom";

import AuthForm from "../../models/user/AuthForm";
import User from "../../models/user/User";
import sanitizeInputs from "../../util/auth/SanitizeInputs";
import { AUTH_ERROR_MESSAGES } from "../../constants/AuthErrorMessages";
import { AuthService } from "../../services/auth/AuthService";
import showNotification from "../../util/notification/ShowNotification";
import { jwtDecode } from "jwt-decode";
import { SessionService } from "../../services/session/SessionService";
import Cookies from "universal-cookie";

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

    const authServiceResponse = await AuthService.login(authFormData);

    if(authServiceResponse.status !== 200) {
        showNotification(authServiceResponse.data.message, 2);
        return;
    }

    const decodedToken = jwtDecode(authServiceResponse.data.token);
    const decodedTokenData: string = decodedToken.sub ? decodedToken.sub : '';
    console.log(decodedTokenData);
    const sessionServiceResponse = await SessionService.createSession(decodedTokenData);

    if(sessionServiceResponse.status !== 200) {
        showNotification(`There was an error while trying to create the session`, 2);
        return;
    }

    const { user, sessionID, sessionToken, sessionExpiryDate } = SessionService.decodeSessionToken(sessionServiceResponse.data.token);
    const cookies = new Cookies(null, { path: '/' });
    console.log(sessionExpiryDate);
    login(user);
    cookies.set(`anyforum-session-id`, sessionID, { expires: new Date(sessionExpiryDate) });
    cookies.set(`anyforum-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });
    navigate("/");
}