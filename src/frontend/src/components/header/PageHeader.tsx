import { useContext, useState } from 'react';

import UserOptionDrownDown from "./UserOptionDropDown";
import { UserContext } from '../../hooks/user/UserContext';

export default function PageHeader() {
    const userContextValue = useContext(UserContext);
    const [isDropdownVisible, setIsDropdownVisible] = useState<boolean>(false);         //state hook for account dropdown visibility

    return (
        <div className="w-11/12 bg-black bg-opacity-60 text-white h-16 rounded mt-10 flex flex-row justify-between">
            <h1 className="font-bold text-5xl flex justify-center items-center ml-5">AnyTopic</h1>
            <div className="">
                <div className="flex justify-end">
                    <button 
                        className="font-bold text-xl hover:bg-opacity-30 hover:bg-gray-600 mr-5 rounded h-12 my-2"
                        onClick={() => setIsDropdownVisible(!isDropdownVisible)}>
                        { userContextValue?.user !== null ? userContextValue?.user.username : `Hello, guest!` }
                    </button>
                </div>
                { isDropdownVisible && <UserOptionDrownDown /> }
            </div>
        </div>
    )
}