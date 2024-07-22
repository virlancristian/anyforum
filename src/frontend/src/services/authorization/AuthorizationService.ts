import axios from "axios"
import { API_URL } from "../../constants/Misc"
import Role from "../../models/authorization/Role";

async function getUserRoles(username: string) {
    try {
        const response = await axios.get(`${API_URL}/api/roles/user/${username}`);
        const roles: Role[] = response.data.roles.length > 0 ?
                        response.data.roles.map((role: any) => ({
                            roleID: role.roleID,
                            name: role.roleName,
                            color: role.roleColor
                        })) :
                        [];
        return roles;
    } catch(error) {
        console.error(`Error in AuthorizationService::getUserRoles - failed to fetch user roles: ${error}`);
        return [];
    }
}

export const AuthorizationService = {
    getUserRoles
}