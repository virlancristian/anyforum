import { useContext, useEffect } from "react";
import { Location, useLocation } from "react-router-dom";

import { useUserPublicData } from "../../hooks/user/UseUserPublicData";
import ProfileFrame from "./ProfileFrame";
import ProfileContent from "./ProfileContent";
import { UserContext } from "../../hooks/user/UserContext";
import Swal from "sweetalert2";


export default function ProfileView() {
    const location: Location = useLocation();
    const urlPath: string = location.pathname;
    const splitURLPath: string[] = urlPath.split("/");
    const username: string = splitURLPath[splitURLPath.length - 1];
    const { userData, roles, isUserDataLoading } = useUserPublicData(username);
    const { user } = useContext(UserContext);

    useEffect(() => {
        if(user && userData) {
            if(userData.nsfwOn && !user.nsfwOn) {
                Swal.fire({
                    text: `This user has NSFW on. Please turn on NSFW to view this profile`
                }).then(() => {
                    window.history.back();
                });
            }
        }
    }, [userData, user]);

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