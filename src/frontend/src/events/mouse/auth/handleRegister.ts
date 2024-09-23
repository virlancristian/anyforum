import { Dispatch, SetStateAction } from "react";
import { jwtDecode } from "jwt-decode";
import Cookies from "universal-cookie";

import AuthForm from "../../../models/user/AuthForm";
import sanitizeInputs from "../../../util/auth/SanitizeInputs";
import { AUTH_ERROR_MESSAGES } from "../../../constants/AuthErrorMessages";
import { AuthApi } from "../../../api/auth/AuthApi";
import showNotification from "../../../util/notification/ShowNotification";
import { SessionApi } from "../../../api/session/SessionApi";
import User from "../../../models/user/User";
import { NavigateFunction } from "react-router-dom";

export default async function handleRegister(authFormData: AuthForm,
                                              setIsError: Dispatch<SetStateAction<boolean>>,
                                              setErrorMessage: Dispatch<SetStateAction<string>>,
                                              login: (user: User) => void,
                                              navigate: NavigateFunction) {
  const sanitizerCode: number = sanitizeInputs(authFormData);

  setIsError(sanitizerCode > 0);

  if (sanitizerCode) {
    setErrorMessage(AUTH_ERROR_MESSAGES[sanitizerCode]);
    return;
  }

  const authServiceRespone = await AuthApi.registerAccount(authFormData);

  if (authServiceRespone.status === 400 || authServiceRespone.status === 500) {
    showNotification(authServiceRespone.data.message, 2);
    return;
  }

  let decodedToken = jwtDecode(authServiceRespone.data.token);
  const sessionServiceResponse = await SessionApi.createSession(decodedToken.sub ? decodedToken.sub : '');

  if (sessionServiceResponse.status === 400 || sessionServiceResponse.status === 500) {
    showNotification(sessionServiceResponse.data.message, 2);
    return;
  }

  
  const { user, sessionID, sessionToken, sessionExpiryDate } = SessionApi.decodeSessionToken(sessionServiceResponse.data.token);

  const cookies = new Cookies(null, { path: '/' });
  const previousAuthURLPath: string = window.localStorage.getItem(`anytopic-preauth-path`) || "/";

  cookies.set(`anytopic-session-id`, sessionID, { expires: new Date(sessionExpiryDate) });
  cookies.set(`anytopic-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });

  login(user);
  navigate(previousAuthURLPath);
}