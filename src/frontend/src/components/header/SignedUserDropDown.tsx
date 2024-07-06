import { useContext } from "react"
import { UserContext } from "../../hooks/user/UserContext"

export default function SignedUserDropDown() {
    const userContextProps = useContext(UserContext);
    const { logout } = userContextProps;

    return (
        <>
            <a>Dashboard</a>
            <a>Edit profile</a>
            <button onClick={logout}>Log out</button>
        </>
    )
}