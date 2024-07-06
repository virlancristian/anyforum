import { ChangeEvent, useState } from 'react';

import AuthForm from '../../models/user/AuthForm';

export const useAuthForm = (authType: string) => {
    const [authFormData, setAuthFormData] = useState<AuthForm>({
        username: authType === `register` ? '' : undefined,
        email: '',
        password: '',
        repeatPassword: authType === `register` ? '' : undefined
    });

    const changeFormField = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;

        setAuthFormData((prevAuthFormData) => ({
            ...prevAuthFormData,
            [name]: value
        }))
    };

    return {
        authFormData,
        changeFormField
    }
};