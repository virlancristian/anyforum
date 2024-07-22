import { useContext } from "react"
import { UserContext } from "../../hooks/user/UserContext"
import { NavigateFunction, useNavigate } from "react-router-dom";

export default function SignedUserDropDown() {
    const userContextProps = useContext(UserContext);
    const { user, logout } = userContextProps;
    const navigate: NavigateFunction = useNavigate();

    return (
        <>
            <button onClick={() => navigate(`/user/${user?.username}`,{ state: { userID: user?.id } })}>Dashboard</button>
            <button onClick={() => navigate(`/profile/edit`)}>Edit profile</button>
            <button onClick={logout}>Log out</button>
        </>
    )
}