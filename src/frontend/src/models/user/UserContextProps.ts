import Role from "../authorization/Role";
import User from "./User";

export default interface UserContextProps {
    user: User | null;
    login: (user: User) => void;
    logout: () => void;
    roles: Role[];
};