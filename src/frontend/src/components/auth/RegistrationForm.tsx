import { useContext, useState } from "react";

import { AUTH_FORM_LABELS } from "../../constants/RegisterFormLabels";
import { AUTH_FORM_TYPES } from "../../constants/RegisterFormTypes";
import { useAuthForm } from "../../hooks/auth/UseAuthForm";
import FormTemplate from "../form/FormTemplate";
import handleRegister from "../../events/mouse/auth/handleRegister";
import { UserContext } from "../../hooks/user/UserContext";
import UserContextProps from "../../models/user/UserContextProps";
import { useNavigate } from "react-router-dom";

export default function RegistrationForm() {
    const userContextProps: UserContextProps = useContext(UserContext);
    const { login } = userContextProps;
    const { authFormData, changeFormField } = useAuthForm(`register`);
    const [isError, setIsError] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const navigate = useNavigate();

    return (
        <div className="w-11/12 h-full bg-black bg-opacity-10 mt-5 rounded flex flex-col items-center justify-center">
            <FormTemplate 
                item={authFormData} 
                changeItemField={changeFormField} 
                inputTypeMapper={AUTH_FORM_TYPES} 
                inputLabelMapper={AUTH_FORM_LABELS}
            />
            <button 
                className="font-bold bg-black text-white text-lg h-12 w-48 mt-5 hover:bg-opacity-30"
                onClick={() => handleRegister(authFormData, setIsError, setErrorMessage, login, navigate)}>
                    Create account
            </button>
            {isError && <p className="text-red-500 font-semibold">{errorMessage}</p>}
        </div>
    )
}