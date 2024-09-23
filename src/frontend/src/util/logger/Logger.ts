import { Color, COLOR_HEX_CODE } from "../../constants/Colors";
import { DEPLOYMENT_TYPE } from "../../constants/Misc";

function shouldBeLogged(): boolean {
    return DEPLOYMENT_TYPE === 'DEVELOPMENT';
}

function log(message: string): void {
    if(shouldBeLogged()) {
        console.log("%c" + message, `color: ${COLOR_HEX_CODE['BLUE']}; font-size: 15px; font-weight: bold`);
    }
}

function error(message: string): void {
    console.log(shouldBeLogged());
    if(shouldBeLogged()) {
        console.log("%c" + message,  `color: ${COLOR_HEX_CODE['RED']}; font-size: 15px; font-weight: bold`);
    }
}

function success(message: string): void {
    if(shouldBeLogged()) {
        console.log("%c" + message,  `color: ${COLOR_HEX_CODE['GREEN']}; font-size: 15px; font-weight: bold`);
    }
}

function warn(message: string): void {
    if(shouldBeLogged()) {
        console.log("%c" + message,  `color: ${COLOR_HEX_CODE['YELLOW']}; font-size: 15px; font-weight: bold`);
    }
}

function custom(message: string, color: Color, size: number, bold?: boolean, strikethrough?: boolean): void {  //size in px
    console.log("%c" + message, `color: ${COLOR_HEX_CODE[color]}; font-size: ${size}px; ${bold ? 'font-weight: bold;' : ''}`);
}

export const Logger = {
    log,
    error,
    success,
    warn,
    custom
}