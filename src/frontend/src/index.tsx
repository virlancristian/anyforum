import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

import MainPage from './pages/Main';
import { UserContextProvider } from './hooks/user/UserContext';

import './css/global.css';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
    <UserContextProvider>
      <MainPage />
    </UserContextProvider>
);

reportWebVitals();
