import axios from "axios"
import { API_URL } from "../../constants/Misc"
import UserData from "../../models/user/UserData";

async function getUserData(username: string): Promise<UserData | null> {
    try {
        const response = await axios.get(`${API_URL}/api/user/${username}/public`);
        return {
            id: response.data.userID,
            username: response.data.username,
            aboutMe: response.data.aboutMe,
            profilePictureURL: response.data.profilePictureURL,
            joinedAt: response.data.joinedAt,
            nsfwOn: response.data.nsfwOn,
            isMuted: response.data.muted,
            isBanned: response.data.banned
        };
    } catch(error) {
        console.error(`Error un UserService::getUserData - failed to get user data: ${error}`);
        return null;
    }
}

export const UserService = {
    getUserData
}