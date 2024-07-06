import { Dispatch, SetStateAction } from "react";
import { jwtDecode } from "jwt-decode";
import Cookies from "universal-cookie";

import AuthForm from "../../models/user/AuthForm";
import sanitizeInputs from "../../util/auth/SanitizeInputs";
import { AUTH_ERROR_MESSAGES } from "../../constants/AuthErrorMessages";
import { AuthService } from "../../services/auth/AuthService";
import showNotification from "../../util/notification/ShowNotification";
import { SessionService } from "../../services/session/SessionService";
import User from "../../models/user/User";
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

  const authServiceRespone = await AuthService.registerAccount(authFormData);

  if (authServiceRespone.status === 400 || authServiceRespone.status === 500) {
    showNotification(authServiceRespone.data.message, 2);
    return;
  }

  let decodedToken = jwtDecode(authServiceRespone.data.token);
  const sessionServiceResponse = await SessionService.createSession(decodedToken.sub ? decodedToken.sub : '');

  if (sessionServiceResponse.status === 400 || sessionServiceResponse.status === 500) {
    showNotification(sessionServiceResponse.data.message, 2);
    return;
  }

  
  const { user, sessionID, sessionToken, sessionExpiryDate } = SessionService.decodeSessionToken(sessionServiceResponse.data.token);

  const cookies = new Cookies(null, { path: '/' });

  cookies.set(`anyforum-session-id`, sessionID, { expires: new Date(sessionExpiryDate) });
  cookies.set(`anyforum-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });

  login(user);
  navigate("/");
}