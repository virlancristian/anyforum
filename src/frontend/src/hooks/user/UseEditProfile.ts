import { ChangeEvent, ChangeEventHandler, useState } from "react";
import { Logger } from "../../util/logger/Logger";

export const useEditProfile = () => {
    const [username, setUsername] = useState<string | null>(null);
    const [aboutMe, setAboutMe] = useState<string | null>(null);
    const [profilePicture, setProfilePicture] = useState<File | null>(null);
    const [isNSFWOn, setIsNSFWOn] = useState<boolean | null>(null);

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

        return () => {}
    };

    const changeIsNSFWOn = (event: ChangeEvent<HTMLInputElement>): ChangeEventHandler<HTMLInputElement> => {
        setIsNSFWOn(event.target.checked);

        return () => {};
    }

    const resetFields = (): void => {
        setUsername(null);
        setAboutMe(null);
        setProfilePicture(null);
        setIsNSFWOn(null);

        const editProfileInputs: any = document.querySelectorAll('#edit-profile-input');

        for(const input of editProfileInputs) {
            input.value = null;
        }
    }

    return {
        username,
        aboutMe,
        profilePicture,
        isNSFWOn,
        changeUsername,
        changeAboutMe,
        changeProfilePicture,
        changeIsNSFWOn,
        resetFields
    };
}