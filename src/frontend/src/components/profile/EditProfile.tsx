import { FormEvent, useContext } from "react";

import { useEditProfile } from "../../hooks/user/UseEditProfile"
import UserContextProps from "../../models/user/UserContextProps";
import { UserContext } from "../../hooks/user/UserContext";
import { ImageApi } from "../../api/image/ImageApi";
import showNotification from "../../util/notification/ShowNotification";
import { UserApi } from "../../api/user/UserApi";

export default function EditProfile() {
    const userContextProps: UserContextProps = useContext(UserContext);
    const { user } = userContextProps;
    const {
        username,
        aboutMe,
        profilePicture,
        isNSFWOn,
        changeUsername,
        changeAboutMe,
        changeProfilePicture,
        changeIsNSFWOn,
        resetFields
    } = useEditProfile();

    const handleProfileUpdate = async (event: FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        let pfpUploadResponseStatus: number = 200;

        if (profilePicture) {
            const { status } = await ImageApi.uploadProfilePicture(profilePicture, user?.id || '');
            pfpUploadResponseStatus = status;
        }

        const { status } = await UserApi.updateUserData(user?.id || '', username, aboutMe, isNSFWOn);

        if (pfpUploadResponseStatus === 200 && status === 200) {
            showNotification(`Profile updated successfully!`, 0);
            resetFields();
        } else {
            showNotification(`There was an error while trying to update your profile`, 2);
        }
    }

    return (
        <div className="w-11/12 bg-black mt-5 h-5/6 p-3">
            <form onSubmit={handleProfileUpdate}>
                <h1 className="text-white text-3xl font-bold mb-10">Edit profile</h1>
                <div className="flex flex-row items-center">
                    <p className="text-white font-bold text-2xl mr-5">Username</p>
                    <input
                        type="text"
                        className="bg-gray-600 outline-none pl-1 h-10 w-64 text-white font-semibold text-lg"
                        id='edit-profile-input'
                        onChange={changeUsername} />
                </div>
                <div className="flex flex-row">
                    <p className="text-white font-bold text-2xl mr-5">About me</p>
                    <textarea
                        className="mt-3 bg-gray-600 outline-none pl-1 text-white font-semibold"
                        rows={10}
                        cols={50}
                        style={{ resize: `none` }}
                        id='edit-profile-input'
                        onChange={changeAboutMe}></textarea>
                </div>
                <div>
                    <p>Profile picture</p>
                    <input
                        type="file"
                        accept="image/jpeg, image/png, image/gif"
                        className="text-white"
                        id='edit-profile-input'
                        onChange={changeProfilePicture}
                    />
                </div>
                <div>
                    <label className="inline-flex items-center cursor-pointer">
                        <input type="checkbox" value="" className="sr-only peer" onChange={changeIsNSFWOn}/>
                            <div className="relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"></div>
                            <span className="ms-3 text-sm font-medium text-gray-900 dark:text-gray-300">Toggle me</span>
                    </label>
                </div>
                <button
                    type="submit"
                    className="text-white font-bold mt-3 bg-blue-500 hover:bg-blue-600 rounded w-32 h-10">
                    Save changes
                </button>
            </form>
        </div>
    )
}