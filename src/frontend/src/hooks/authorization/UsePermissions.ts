import { useEffect, useState } from "react";

import { Permission } from "../../models/authorization/Permission";
import { PermissionApi } from "../../api/permission/PermissionApi";

export const usePermissions = () => {
    const [permissions, setPermissions] = useState<Permission[]>([]);

    const fetchPerms = async (): Promise<void> => {
        const permissions: Permission[] = await PermissionApi.getAllPermissions();
        setPermissions(permissions); 
    };

    useEffect(() => {
        fetchPerms();
    }, []);

    return {
        permissions
    };
}