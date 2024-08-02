import Cookies from "universal-cookie"

function getAuthorizationToken(): string {
    const cookies: Cookies = new Cookies(null, { path: "/" });
    const sessionToken: string = cookies.get(`anytopic-session-token`);

    return sessionToken;
}

export const SessionUtil = {
    getAuthorizationToken
}