import axios, { AxiosError } from "axios";

import AuthForm from "../../models/user/AuthForm";
import { API_URL } from "../../constants/Misc";

async function registerAccount(authFormData: AuthForm) {
    const body: any = {
        username: authFormData.username,
        email: authFormData.email,
        password: authFormData.password
    };

    try {
        const response = await axios.post(`${API_URL}/api/user/register`, body);
        
        return response;
    } catch(error: any) {
        console.log(`Error while trying to register account: ${error}`);
        return error.response;
    }
}

async function login(authFormData: AuthForm) {
    const body: any = {
        email: authFormData.email,
        password: authFormData.password
    };

    try {
        const response = await axios.post(`${API_URL}/api/user/login`, body);
        
        return response;
    } catch(error: any) {
        console.log(`Error while trying to register account: ${error}`);
        return error.response;
    }
}

export const AuthService = {
    registerAccount,
    login
}