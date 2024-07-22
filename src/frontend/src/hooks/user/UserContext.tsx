import { createContext } from "react";

import { useUSer } from "./UseUser";
import UserContextProps from "../../models/user/UserContextProps";

export const UserContext = createContext<UserContextProps>({
    user: {
        id: '',
        email: '',
        username: '',
        isBanned: false,
        isMuted: false,
        nsfwOn: false
    },
    login: () => {},
    logout: () => {},
    roles: []
});

export const UserContextProvider = ( { children}: { children: any } ) => {
    const { user, login, logout, roles } = useUSer();

    return (
        <UserContext.Provider value={{ user, login, logout, roles }}>
            {children}
        </UserContext.Provider>
    )
};