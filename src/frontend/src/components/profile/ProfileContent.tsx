import { useState } from "react";

import { PROFILE_SECTIONS_BUTTON_NAMES } from "../../constants/Misc";
import UserData from "../../models/user/UserData";
import handleSectionVisibility from "../../events/mouse/profile/HandleSectionVisibility";

interface Props {
    userData: UserData | null
}

export default function ProfileContent(props: Props) {
    const { userData } = props;
    const [sectionVisibility, setSectionVisibility] = useState<boolean[]>([true, false, false]);    //i = 0 - about me visibility, i = 1 - posts visibility, i = 2 - comments visibility
    
    if(userData === null) {
        return <p>Not available</p>
    }

    return (
        <div className="w-full p-3">
            <div className="bg-gray-800 w-full flex flex-row justify-around">
                {
                    sectionVisibility.map((section: boolean, index: number) => (
                        <div 
                            key={index} 
                            className="w-full flex items-center justify-center text-white font-bold text-xl h-12 hover:cursor-pointer hover:border-b-4 hover:border-color-white"
                            onClick={() => handleSectionVisibility(index, sectionVisibility, setSectionVisibility)}>
                            {PROFILE_SECTIONS_BUTTON_NAMES[index]}
                        </div>
                    ))
                }
            </div>
            {
                sectionVisibility[0] && (
                    <div className="w-full bg-gray-800 text-white font-semibold text-lg mt-3 h-96 p-3 max-h-screen">
                        {userData.aboutMe}
                    </div>
                )
            }
            {
                sectionVisibility[1] && (<p>Posts</p>)  // to do
            }
            {
                sectionVisibility[2] && (<p>Comments</p>)   // to do
            }
        </div>
    )
}