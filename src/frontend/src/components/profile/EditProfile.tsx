import { useContext, useEffect } from "react";
import handleUpdateProfile from "../../events/mouse/profile/HandleUpdateProfile";
import { useEditProfile } from "../../hooks/user/UseEditProfile"
import UserContextProps from "../../models/user/UserContextProps";
import { UserContext } from "../../hooks/user/UserContext";

export default function EditProfile() {
    const userContextProps: UserContextProps = useContext(UserContext);
    const { user } = userContextProps;
    const { 
        username,
        aboutMe,
        profilePicture,
        changeUsername,
        changeAboutMe,
        changeProfilePicture
     } = useEditProfile();

    //  useEffect(() => {
    //     if(!user) {
    //         window.location.href = "/account/login";
    //     }
    //  }, [user]);

    return (
        <div className="w-11/12 bg-black mt-5 h-5/6 p-3">
            <h1 className="text-white text-3xl font-bold mb-10">Edit profile</h1>
            <div className="flex flex-row items-center">
                <p className="text-white font-bold text-2xl mr-5">Username</p>
                <input 
                    type="text" 
                    className="bg-gray-600 outline-none pl-1 h-10 w-64 text-white font-semibold text-lg"
                    onChange={changeUsername}/>
            </div>
            <div className="flex flex-row">
                <p className="text-white font-bold text-2xl mr-5">About me</p>
                <textarea 
                    className="mt-3 bg-gray-600 outline-none pl-1 text-white font-semibold" 
                    rows={10} 
                    cols={50}
                    style={{resize: `none`}}
                    onChange={changeAboutMe}></textarea>
            </div>
            <div>
                <p>Profile picture</p>
                <input 
                    type="file" 
                    accept="image/jpeg, image/png, image/gif" 
                    className="text-white"
                    onChange={changeProfilePicture}
                    />
            </div>
            <button 
                className="text-white font-bold mt-3 bg-blue-500 hover:bg-blue-600 rounded w-32 h-10"
                onClick={() => handleUpdateProfile({username, aboutMe, profilePicture, userID: user?.id || ""})}>
                    Save changes
            </button>
        </div>
    )
}