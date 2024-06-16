import { Dispatch, SetStateAction } from "react";

import AuthForm from "../../models/user/AuthForm";
import sanitizeInputs from "../../util/auth/SanitizeInputs";
import { AUTH_ERROR_MESSAGES } from "../../constants/AuthErrorMessages";
import { AuthService } from "../../services/auth/AuthService";
import showNotification from "../../util/notification/ShowNotification";

export default async function handleRegister(authFormData: AuthForm, 
                                             setIsError: Dispatch<SetStateAction<boolean>>, 
                                             setErrorMessage: Dispatch<SetStateAction<string>>) {
   const sanitizerCode: number = sanitizeInputs(authFormData); 

   setIsError(sanitizerCode > 0);

   if(sanitizerCode) {
        setErrorMessage(AUTH_ERROR_MESSAGES[sanitizerCode]);
        return;
   }

   const response = await AuthService.registerAccount(authFormData);

   if(response.status === 400) {
     showNotification(response.data.message, 2);
   }
}