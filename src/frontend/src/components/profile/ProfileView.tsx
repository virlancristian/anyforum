import { Location, useLocation } from "react-router-dom";

import { useUserPublicData } from "../../hooks/user/UseUserPublicData";
import ProfileFrame from "./ProfileFrame";
import ProfileContent from "./ProfileContent";


export default function ProfileView() {
    const location: Location = useLocation();
    const urlPath: string = location.pathname;
    const splitURLPath: string[] = urlPath.split("/");
    const username: string = splitURLPath[splitURLPath.length - 1];
    const { userData, roles, isUserDataLoading } = useUserPublicData(username);

    if(userData === null && !isUserDataLoading) {
        return <p>User not found.</p>
    }

    return (
        <div className="w-11/12 mt-5 flex flex-row">
            <ProfileFrame userData={userData} roles={roles} />
            <ProfileContent userData={userData}/>
        </div>
    )
}