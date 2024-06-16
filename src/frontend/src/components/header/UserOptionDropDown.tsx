import { useContext } from 'react';

import { UserContext } from '../../hooks/user/UserContext';
import GuestDropdown from './GuestDropDown';
import SignedUserDropDown from './SignedUserDropDown';

import '../../css/header/dropdown.css';

export default function UserOptionDrownDown() {
    const userContextValue = useContext(UserContext);

    return (
        <div className="bg-gray-500 w-48 h-24 flex items-center justify-center rounded-lg mt-2 bg-gray-700 flex-col relative" id='user-option-dropdown'>
            {
                userContextValue?.user !== null ?
                <SignedUserDropDown /> :
                <GuestDropdown />
            }
        </div>
    )
}