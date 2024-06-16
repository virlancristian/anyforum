import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

/*
    notification type:
    0 - success,
    1 - warning,
    2 - error,
    3 - info
*/
export default function showNotification(message: string, notificationType: number) {
    switch(notificationType) {
        case 0:
            toast.success(message);
            break;
        case 1:
            toast.warning(message);
            break;
        case 2:
            toast.error(message);
            break;
        case 3:
            toast.info(message);
            break;
    }
}