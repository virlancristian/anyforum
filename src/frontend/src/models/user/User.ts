export default interface User {
    id: string;
    username: string;
    email: string;
    isBanned: boolean;
    isMuted: boolean;
    nsfwOn: boolean;
};