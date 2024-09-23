import axios, { AxiosResponse } from "axios";

import { SessionUtil } from "../../util/session/SessionUtil";
import { API_URL } from "../../constants/Misc";
import { ImageUploadResponse } from "../../models/image/ImageServiceInterfaces";

async function uploadProfilePicture(profilePicture: File, userID: string): Promise<ImageUploadResponse> {
    const sessionToken: string = SessionUtil.getAuthorizationToken();
    const body: FormData = new FormData();

    body.append(`profilePicture`, profilePicture);

    const options = {
        headers: {
            Authorization: `AnyTopicToken ${sessionToken}`,
            "Content-Type": `multipart/form-data`
        },
    };

    try {
        const response: AxiosResponse = await axios.post(`${API_URL}/api/images/avatar/user/${userID}/upload`, body, options);

        return {
            status: 200,
            imageName: response.data.content
        };
    } catch(error: any) {
        console.error(`Error in ImageApi::uploadProfilePicture - failed to upload image: ${error}`);
        return {
            status: error.code,
            imageName: error.response?.message || error.message
        };
    }
}

export const ImageApi = {
    uploadProfilePicture
}