import { ImageService } from "../../../services/image/ImageService";
import showNotification from "../../../util/notification/ShowNotification";

interface Props {
    username: string;
    aboutMe: string;
    profilePicture: File | undefined;
    userID: string;
}

export default async function handleUpdateProfile(props: Props): Promise<void> {
    const { username, aboutMe, profilePicture, userID } = props;

    if(profilePicture) {
        const {status, imageName} = await ImageService.uploadProfilePicture(profilePicture, userID);
        if(status === 200) {
            showNotification(`Image uploaded successfully!`, 0);
        } else {
            showNotification(imageName, 2);
        }
    }
}