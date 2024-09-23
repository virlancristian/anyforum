import axios from "axios";

import { API_URL } from "../../constants/Misc";
import Role from "../../models/authorization/Role";
import { Permission } from "../../models/authorization/Permission";
import { Logger } from "../../util/logger/Logger";

async function getUserRoles(username: string) {
    try {
        const response = await axios.get(`${API_URL}/api/authorization/roles/user/${username}`);
        const roles: Role[] = response.data.map((fetchedRole: any) => ({
            roleID: fetchedRole.roleID,
            name: fetchedRole.roleName,
            color: fetchedRole.roleColor
        }));

        return roles;
    } catch(error) {
        Logger.error(`Error in PermissionApi::getUserRoles - failed to fetch user roles: ${error}`);
        return [];
    }
}

async function getUserPerms(userID: string): Promise<Permission[]> {
    try {
        const response = await axios.get(`${API_URL}/api/authorization/permissions/user/${userID}`);
        const permissions: Permission[] = response.data;

        return permissions;
    } catch(error) {
        Logger.error(`Error in PermissionApi::getUserPerms - failed to fetch user perms: ${error}`);
        return [];
    }
}

async function getAllPermissions(): Promise<Permission[]> {
    try {
        const response = await axios.get(`${API_URL}/api/authorization/permissions/all`);
        const permissions: Permission[] = response.data;

        return permissions;
    } catch(error) {
        Logger.error(`Error in PermissionApi::getAllPermissions - faled to fetch all permissions: ${error}`);
        return [];
    }
}

export const PermissionApi = {
    getUserRoles,
    getUserPerms,
    getAllPermissions
}