import { useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

import User from '../../models/user/User';

export const useUSer = () => {
    const [user, setUser] = useState<User | null>(null);

    const login = () => {

    };

    const logout = () => {

    };

    useEffect(() => {
        const userJwtToken = window.localStorage.getItem(`anyforum-user-token`);

        if(userJwtToken !== null) {
            console.log(jwtDecode(userJwtToken));
        } else {
            setUser(null);
        }
    }, []); 

    return {
        user,
        login,
        logout
    }
};