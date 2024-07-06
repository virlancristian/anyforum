import axios from "axios"
import { jwtDecode } from "jwt-decode";

import { API_URL } from "../../constants/Misc"
import User from "../../models/user/User";

async function createSession(userID: string) {
    try {
        const response = await axios.post(`${API_URL}/api/session/create`, { userID });

        return response;
    } catch(error: any) {
        console.log(`Error in SessionService::createSession - unable to create session: ${error}`);
        return error.response;
    }
}

async function getSession(sessionID: string) {
    try {
        const response = await axios.post(`${API_URL}/api/session/get`, { sessionID });

        return response;
    } catch(error: any) {
        console.log(`Error in SessionService::getSession - unable to get session: ${error}`);
        return error.response;
    }
}

async function deleteSession(sessionID: string) {
    try {
        const response = await axios.post(`${API_URL}/api/session/delete`, { sessionID });

        return response;
    } catch(error: any) {
        console.log(`Error in SessionService::getSession - unable to get session: ${error}`);
        return error.response;
    }
}

function decodeSessionToken(token: string) {
    const decodedToken = jwtDecode(token);
    const decodedTokenData: any = decodedToken.sub ? JSON.parse(decodedToken.sub) : {};

    const user: User = {
        username: decodedTokenData.username,
        id: decodedTokenData.id,
        email: decodedTokenData.email,
        isMuted: decodedTokenData.isMuted,
        isBanned: decodedTokenData.isBanned,
        nsfwOn: decodedTokenData.nsfwOn
      };
    const sessionID: string = decodedTokenData.sessionID;
    const sessionToken: string = decodedTokenData.sessionToken;
    const sessionExpiryDate: string = decodedTokenData.expiresAt;

    return {
        user,
        sessionID,
        sessionToken,
        sessionExpiryDate
    };
}

export const SessionService = {
    createSession,
    getSession,
    deleteSession,
    decodeSessionToken
};