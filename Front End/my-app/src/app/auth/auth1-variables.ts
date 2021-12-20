interface AuthConfig {
  redirectUrl: string;
  clientID: string;
  domain: string;
  returnURL: string;
}

export const AUTH_CONFIG: AuthConfig = {




   clientID: '12345678',
   redirectUrl: 'http://localhost:8085/',
  


  domain: 'drawing.auth1.com',
  returnURL: 'http://localhost:8085/'
};