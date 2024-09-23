import { ImageApi } from "../../../api/image/ImageApi";
import showNotification from "../../../util/notification/ShowNotification";

interface Props {
    username: string;
    aboutMe: string;
    profilePicture: File | undefined;
    userID: string;
}

export default async function handleUpdateProfile(props: Props): Promise<void> {
    const { username, aboutMe, profilePicture, userID } = props;
    let profilePictureResponseStatus: number = 200;

    if(profilePicture) {
        const { status } = await ImageApi.uploadProfilePicture(profilePicture, userID);
        profilePictureResponseStatus = status;    
    }


}