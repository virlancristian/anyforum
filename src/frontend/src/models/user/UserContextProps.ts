import User from "./User";

export default interface UserContextProps {
    user: User | null;
    login: () => void;
    logout: () => void;
};