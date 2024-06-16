export const USERNAME_REGEX: RegExp = /^[a-zA-Z0-9_]{1,50}$/;
export const EMAIL_REGEX: RegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$/;
export const PASSWORD_REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,}$/; 