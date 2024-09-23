import axios, { AxiosRequestConfig } from "axios"
import { API_URL } from "../../constants/Misc"
import UserData from "../../models/user/UserData";
import { SessionUtil } from "../../util/session/SessionUtil";
import { Logger } from "../../util/logger/Logger";

async function getUserData(username: string): Promise<UserData | null> {
    try {
        const response = await axios.get(`${API_URL}/api/user/${username}/public`);
        return {
            id: response.data.id,
            username: response.data.username,
            aboutMe: response.data.aboutMe,
            profilePictureURL: response.data.profilePictureURL,
            joinedAt: response.data.joinedAt,
            nsfwOn: response.data.nsfwOn,
            isMuted: response.data.muted,
            isBanned: response.data.banned
        };
    } catch(error) {
        console.error(`Error un UserApi::getUserData - failed to get user data: ${error}`);
        return null;
    }
}

async function updateUserData(userID: string, username: string | null, aboutMe: string | null, isNSFWOn: boolean | null): Promise<{ status: number; message: string }> {
    const sessionToken: string = SessionUtil.getAuthorizationToken();
    const body: any = {
        username: username,
        aboutMe: aboutMe,
        isNSFWOn: isNSFWOn
    };
    const options: AxiosRequestConfig = {
        headers: {
            "Authorization": `AnyTopicToken ${sessionToken}`,
            "Content-Type": "application/json"
        }
    };

    try {
        const response = await axios.post(`${API_URL}/api/user/${userID}/public/update`, body, options);
        return {
            status: response.status,
            message: response.data.message
        };
    } catch(error) {
        Logger.error(`Error in UserApi::updateUserData - failed to update user data: ${error}`);
        return {
            status: 500,
            message: `There was an error while trying to update the profile`
        };
    }
}

export const UserApi = {
    getUserData,
    updateUserData
}