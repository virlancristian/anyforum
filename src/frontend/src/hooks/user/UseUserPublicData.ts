import { useState, useEffect } from 'react';

import UserData from '../../models/user/UserData';
import Role from '../../models/authorization/Role';
import { UserService } from '../../services/user/UserService';
import { AuthorizationService } from '../../services/authorization/AuthorizationService';

export const useUserPublicData = (username: string) => {
    const [userData, setUserData] = useState<UserData | null>(null);
    const [roles, setRoles] = useState<Role[]>([]);
    const [isUserDataLoading, setIsUserDataLoading] = useState<boolean>(false);

    const fetchUserData = async () => {
        const fetchedUserData: UserData | null = await UserService.getUserData(username);

        if(fetchedUserData) {
            setUserData(fetchedUserData);
        }
    };

    const fetchUserRoles = async () => {
        const fetchedData: any = await AuthorizationService.getUserRoles(username);
        if(Object.keys(fetchedData).length > 0) {
            setRoles(fetchedData);
        }
    };

    const fetchAllData = async () => {
        try {
            setIsUserDataLoading(true);
            await fetchUserData();
            await fetchUserRoles();
        } catch(error) {
            console.error(`Error in useUserPublicData hook - failed to fetch user data on state change: ${error}`);
        } finally {
            setIsUserDataLoading(false);
        }
    }

    useEffect(() => {
        fetchAllData();
    }, [username]);

    return {
        userData,
        roles,
        isUserDataLoading
    };
};