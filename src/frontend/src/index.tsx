import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

import { UserContextProvider } from './hooks/user/UserContext';

import './css/global.css';
import { RouterProvider } from 'react-router-dom';
import { router } from './routers/Router';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
    <UserContextProvider>
      <RouterProvider router={router} />
    </UserContextProvider>
);

reportWebVitals();
