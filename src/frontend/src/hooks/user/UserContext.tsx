import { createContext } from "react";

import { useUSer } from "./UseUser";
import UserContextProps from "../../models/user/UserContextProps";

export const UserContext = createContext<UserContextProps | null>(null);

export const UserContextProvider = ( { children}: { children: any } ) => {
    const { user, login, logout } = useUSer();

    return (
        <UserContext.Provider value={{ user, login, logout }}>
            {children}
        </UserContext.Provider>
    )
};