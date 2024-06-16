import AuthForm from "../../models/user/AuthForm";
import { EMAIL_REGEX, PASSWORD_REGEX, USERNAME_REGEX } from "../../constants/AuthConstants";

/*return codes:
    0 - input ok,
    1 - empty input field
    2 - invalid username
    3 - invalid e-mail
    4 - invalid password
    5 - passwords not matching
*/ 
export default function sanitizeInputs(authFormData: AuthForm) {
    if((authFormData.username && authFormData.username === '') ||
        authFormData.email === '' ||
        authFormData.password === '' ||
        (authFormData.repeatPassword && authFormData.repeatPassword === '')) {
            return 1;
    }

    if(authFormData.username && !USERNAME_REGEX.test(authFormData.username)) {
        return 2;
    }

    if(!EMAIL_REGEX.test(authFormData.email)) {
        return 3;
    }

    if(!PASSWORD_REGEX.test(authFormData.password)) {
        return 4;
    }

    if(authFormData.repeatPassword && !(authFormData.password === authFormData.repeatPassword)) {
        return 5;
    }

    return 0;
}