interface AppConfig {
    redirectUri: string;
    clientID: string;
    serverURL: string;
  }
  

     let mode: string = 'default';


  let cI: string;
  let uri: string;
  let server: string;
  switch(mode) {
   default: { 
        console.log("opt1");
         cI = 'aIeu8NVeP6e0AQpSA7CjuI2yGOnxGwq5';
         uri = 'http://localhost:8085/';
         server = 'http://localhost:9045';
        break; 
     } 
     
    } 
  
  export const APP_CONFIG: AppConfig = {clientID: cI,redirectUri: uri,serverURL: server};