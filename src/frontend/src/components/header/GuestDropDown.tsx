import { useLocation } from "react-router-dom"
import handleSaveURLPath from "../../events/mouse/url/HandleSaveURLPath";

export default function GuestDropdown() {
    const location = useLocation();

    return (
        <>
            <a 
                href="/account/login" 
                className="font-bold hover:bg-gray-800 w-full h-full rounded-lg flex items-center justify-center"
                onClick={() => handleSaveURLPath(location)}>
                    Log in
            </a>
            <a 
                href="/account/register" 
                className="font-bold hover:bg-gray-800 w-full h-full rounded-lg flex items-center justify-center"
                onClick={() => handleSaveURLPath(location)}>
                    Register
            </a>
        </>
    )
}