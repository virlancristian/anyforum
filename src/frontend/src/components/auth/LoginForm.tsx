import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

import UserContextProps from "../../models/user/UserContextProps";
import { UserContext } from "../../hooks/user/UserContext";
import { useAuthForm } from "../../hooks/auth/UseAuthForm";

import FormTemplate from "../form/FormTemplate";
import { AUTH_FORM_TYPES } from "../../constants/RegisterFormTypes";
import { AUTH_FORM_LABELS } from "../../constants/RegisterFormLabels";
import handleLogin from "../../events/mouse/HandleLogin";

export default function LoginForm() {
    const userContextProps: UserContextProps = useContext(UserContext);
    const { login } = userContextProps;
    const { authFormData, changeFormField } = useAuthForm(`login`);
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
                onClick={() => handleLogin(authFormData, setIsError, setErrorMessage, login, navigate)}>
                    Log in
            </button>
            {isError && <p className="text-red-500 font-semibold">{errorMessage}</p>}
        </div>
    )
}