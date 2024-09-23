import Role from "../authorization/Role";
import User from "./User";
import { Permission } from "../authorization/Permission";

export default interface UserContextProps {
    user: User | null;
    login: (user: User) => void;
    logout: () => void;
    roles: Role[];
    permissions: Permission[];
    isUserLoading: boolean
};