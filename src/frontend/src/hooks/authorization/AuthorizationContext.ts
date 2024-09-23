import { createContext } from "react";
import { usePermissions } from "./UsePermissions";

interface Props {

}

export const AuthorizationContext = createContext(null);

export function AuthorizationContextProvider({ children }: { children: any }) {
    const { permissions } = usePermissions();
}