export const API_URL: string = process.env.REACT_APP_API_URL || `http://localhost:8080`;
export const JWT_SECRET: string = process.env.REACT_APP_JWT_SECRET || '';

export const PROFILE_SECTIONS_BUTTON_NAMES = [`About me`, `Posts`, `Comments`];

type DeploymentType = 'DEVELOPMENT' | 'PRODUCTION';

const EnvDeploymentType: { [key: string]: DeploymentType } = {
    'DEVELOPMENT': 'DEVELOPMENT',
    'PRODUCTION': 'PRODUCTION',
    '': 'DEVELOPMENT'
};

export const DEPLOYMENT_TYPE = EnvDeploymentType[process.env.REACT_APP_DEPLOYMENT_TYPE || ''];