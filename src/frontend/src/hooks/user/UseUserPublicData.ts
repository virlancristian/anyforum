import { useState, useEffect, useReducer } from 'react';

import UserData from '../../models/user/UserData';
import Role from '../../models/authorization/Role';
import { UserApi } from '../../api/user/UserApi';
import { PermissionApi } from '../../api/permission/PermissionApi';

interface UserPublicDataState {
    userData: UserData | null;
    roles: Role[];
    isUserDataLoading: boolean;
};

type UserPublicDataAction = 
    | { type: `SET_USER_DATA`; payload: UserData | null }
    | { type: `SET_USER_ROLES`; payload: Role[] }
    | { type: `SET_IS_USER_LOADING`; payload: boolean };

const userPublicDataReduer = (state: UserPublicDataState, action: UserPublicDataAction): UserPublicDataState => {
    switch(action.type) {
        case `SET_USER_DATA`: 
            return {
                ...state,
                userData: action.payload
            };
        case `SET_USER_ROLES`: 
            return {
                ...state,
                roles: action.payload
            };
        case `SET_IS_USER_LOADING`: 
            return {
                ...state,
                isUserDataLoading: action.payload
            }
        default:
            return state;
    }
}

const initialState: UserPublicDataState = {
    userData: null,
    roles: [],
    isUserDataLoading: false
};

export const useUserPublicData = (username: string): {
    userData: UserData | null;
    roles: Role[];
    isUserDataLoading: boolean;
} => {
    const [state, dispatch] = useReducer(userPublicDataReduer, initialState);

    const fetchUserPublicData = async () => {
        dispatch({ type: `SET_IS_USER_LOADING`, payload: true });

        const userData: UserData | null = await UserApi.getUserData(username);

        if(userData) {
            const roles: Role[] = await PermissionApi.getUserRoles(username);
            dispatch({ type: `SET_USER_ROLES`, payload: roles });
        }

        dispatch({ type: `SET_USER_DATA`, payload: userData });
        dispatch({ type: `SET_IS_USER_LOADING`, payload: false });
    }

    useEffect(() => {
        fetchUserPublicData();
    }, [username]);

    return {
        userData: state.userData,
        roles: state.roles,
        isUserDataLoading: state.isUserDataLoading
    };
};