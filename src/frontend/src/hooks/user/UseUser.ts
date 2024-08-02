import { useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

import User from '../../models/user/User';
import Cookies from 'universal-cookie';
import { SessionService } from '../../services/session/SessionService';
import Role from '../../models/authorization/Role';
import { AuthorizationService } from '../../services/authorization/AuthorizationService';

export const useUSer = () => {
    const [user, setUser] = useState<User | null>(null);
    const [roles, setRoles] = useState<Role[]>([]);

    const login = (user: User) => {
        setUser(user);
    };

    const logout = async () => {
        const cookies = new Cookies();
        const sessionID: string = cookies.get(`anytopic-session-id`);

        const response = await SessionService.deleteSession(sessionID);

        if(response.status === 200) {
            setUser(null);
            cookies.remove(`anytopic-session-id`);
            cookies.remove(`anytopic-session-token`);
        }
    };

    const getSession = async () => {
        const cookies = new Cookies(null, { path: '/' });
        const sessionID: string = cookies.get(`anytopic-session-id`);

        const response = await SessionService.getSession(sessionID);

        if(response.status === 200) {
            const { user, sessionID, sessionToken, sessionExpiryDate } = SessionService.decodeSessionToken(response.data.token);

            setUser(user);
            cookies.set(`anytopic-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });
        }
    }

    const getRoles = async () => {
        if(user) {
            const roles: Role[] = await AuthorizationService.getUserRoles(user.id);
            setRoles(roles);
        }
    }

    useEffect(() => {
        getSession();
    }, []); 

    useEffect(() => {
        getRoles();
    }, [user]);


    return {
        user,
        login,
        logout,
        roles
    }
};