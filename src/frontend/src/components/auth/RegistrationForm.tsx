import { useState } from "react";

import { REGISTER_FORM_LABELS } from "../../constants/RegisterFormLabels";
import { REGISTER_FORM_TYPES } from "../../constants/RegisterFormTypes";
import { useAuthForm } from "../../hooks/auth/UseAuthForm";
import FormTemplate from "../form/FormTemplate";
import handleRegister from "../../events/mouse/handleRegister";

export default function RegistrationForm() {
    const { authFormData, changeFormField } = useAuthForm(`register`);
    const [isError, setIsError] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>('');

    return (
        <div className="w-11/12 h-full bg-black bg-opacity-10 mt-5 rounded flex flex-col items-center justify-center">
            <FormTemplate 
                item={authFormData} 
                changeItemField={changeFormField} 
                inputTypeMapper={REGISTER_FORM_TYPES} 
                inputLabelMapper={REGISTER_FORM_LABELS}
            />
            <button 
                className="font-bold bg-black text-white text-lg h-12 w-48 mt-5 hover:bg-opacity-30"
                onClick={() => handleRegister(authFormData, setIsError, setErrorMessage)}>
                    Create account
            </button>
            {isError && <p className="text-red-500 font-semibold">{errorMessage}</p>}
        </div>
    )
}