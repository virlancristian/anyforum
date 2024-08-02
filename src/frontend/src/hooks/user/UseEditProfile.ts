import { ChangeEvent, ChangeEventHandler, useState } from "react";

export const useEditProfile = () => {
    const [username, setUsername] = useState<string>('');
    const [aboutMe, setAboutMe] = useState<string>('');
    const [profilePicture, setProfilePicture] = useState<File>();

    const changeUsername = (event: ChangeEvent<HTMLInputElement>): ChangeEventHandler<HTMLInputElement> => {
        setUsername(event.target.value);
        return () => {}
    };

    const changeAboutMe = (event: ChangeEvent<HTMLTextAreaElement>): ChangeEventHandler<HTMLTextAreaElement> => {
        setAboutMe(event.target.value);

        return () => {}
    };

    const changeProfilePicture = (event: ChangeEvent<HTMLInputElement>): ChangeEventHandler<HTMLInputElement> => {
        if(event.target.files) {
            setProfilePicture(event.target.files[0]);
        }

        return () => {
        }
    };

    return {
        username,
        aboutMe,
        profilePicture,
        changeUsername,
        changeAboutMe,
        changeProfilePicture
    };
}