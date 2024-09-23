import { useEffect, useReducer } from 'react';

import User from '../../models/user/User';
import Cookies from 'universal-cookie';
import { SessionApi } from '../../api/session/SessionApi';
import Role from '../../models/authorization/Role';
import { PermissionApi } from '../../api/permission/PermissionApi';
import { Permission } from '../../models/authorization/Permission';

interface UserState {
    user: User | null;
    roles: Role[];
    permissions: Permission[];
    isUserLoading: boolean;
}

type UserAction = 
    | { type: `SET_USER`; payload: User | null }
    | { type: `SET_ROLES`; payload: Role[] }
    | { type: `SET_PERMISSIONS`; payload: Permission[] }
    | { type: `SET_IS_USER_LOADING`; payload: boolean };

const userReducer = (state: UserState, action: UserAction): UserState => {
    switch(action.type) {
        case `SET_USER`:
            return {
                ...state,
                user: action.payload
            };
        case `SET_PERMISSIONS`:
            return {
                ...state,
                permissions: action.payload
            };
        case `SET_ROLES`:
            return {
                ...state,
                roles: action.payload
            };
        case `SET_IS_USER_LOADING`:
            return {
                ...state,
                isUserLoading: action.payload
            }
        default:
            return state;
    }
}

const initialState: UserState = {
    user: null,
    roles: [],
    permissions: [],
    isUserLoading: false
};

export const useUSer = (): {
    user: User | null,
    roles: Role[],
    permissions: Permission[],
    isUserLoading: boolean,
    login: (user: User) => void;
    logout: () => Promise<void>
} => {
    const [state, dispatch] = useReducer(userReducer, initialState);

    const login = (user: User) => {
        dispatch({ type: `SET_USER`, payload: user });
    };

    const logout = async () => {
        const cookies = new Cookies();
        const sessionID: string = cookies.get(`anytopic-session-id`);

        const response = await SessionApi.deleteSession(sessionID);

        if(response.status === 200) {
            dispatch({ type: `SET_USER`, payload: null })
            cookies.remove(`anytopic-session-id`);
            cookies.remove(`anytopic-session-token`);
        }
    };

    const getSession = async (): Promise<User | null> => {
        const cookies = new Cookies(null, { path: '/' });
        const sessionID: string = cookies.get(`anytopic-session-id`);

        const response = await SessionApi.getSession(sessionID);

        if(response.status === 200) {
            const { user, sessionID, sessionToken, sessionExpiryDate } = SessionApi.decodeSessionToken(response.data.token);

            cookies.set(`anytopic-session-token`, sessionToken, { expires: new Date(sessionExpiryDate) });
            return user;
        }

        return null;
    }

    const fetchUserAuthorizationData = async (): Promise<void> => {
        dispatch({ type: `SET_IS_USER_LOADING`, payload: true });

        const user: User | null = await getSession();

        if(user) {
           const roles: Role[] = await PermissionApi.getUserRoles(user.id);
           const permissions: Permission[] = await PermissionApi.getUserPerms(user.id);
           
           dispatch({ type: `SET_ROLES`, payload: roles });
           dispatch({ type: `SET_PERMISSIONS`, payload: permissions });
        }

        dispatch({ type: `SET_USER`, payload: user });
        dispatch({ type: `SET_IS_USER_LOADING`, payload: false });
    };

    useEffect(() => {
        fetchUserAuthorizationData();
    }, []); 

    return {
        user: state.user,
        roles: state.roles,
        permissions: state.permissions,
        isUserLoading: state.isUserLoading,
        login: login,
        logout: logout,
    }
};