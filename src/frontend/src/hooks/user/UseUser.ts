import { useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

import User from '../../models/user/User';
import Cookies from 'universal-cookie';
import { SessionService } from '../../services/session/SessionService';

export const useUSer = () => {
    const [user, setUser] = useState<User | null>(null);

    const login = (user: User) => {
        setUser(user);
    };

    const logout = async () => {
        const cookies = new Cookies();
        const sessionID: string = cookies.get(`anyforum-session-id`);

        const response = await SessionService.deleteSession(sessionID);

        if(response.status === 200) {
            setUser(null);
            cookies.remove(`anyforum-session-id`);
            cookies.remove(`anyforum-session-token`);
        }
    };

    const getSession = async () => {
        const cookies = new Cookies(null, { path: '/' });
        const sessionID: string = cookies.get(`anyforum-session-id`);

        const response = await SessionService.getSession(sessionID);

        if(response.status === 200) {
            const { user, sessionID, sessionToken, sessionExpiryDate } = SessionService.decodeSessionToken(response.data.token);

            setUser(user);
            cookies.set(`anyforum-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });
        }
    }

    useEffect(() => {
        getSession();
        console.log(user);
    }, []); 

    return {
        user,
        login,
        logout
    }
};