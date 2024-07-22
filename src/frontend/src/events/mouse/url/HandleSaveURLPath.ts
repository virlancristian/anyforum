import { Location } from "react-router-dom";

export default function handleSaveURLPath(location: Location) {
    window.localStorage.setItem(`anyforum-preauth-path`, location.pathname)
}