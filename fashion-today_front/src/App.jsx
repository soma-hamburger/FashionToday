import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ApiTest from './components/Common/ApiTest';
import LogIn from './components/Common/LogIn';
import { LoginContext } from './Context';
import Main from './pages/Main';
import Index from './pages/Index';
// import axios from 'axios';

const getToken = () => {
  console.log('getToken');
  return localStorage.getItem('token');
};

function App() {
  const [token, setToken] = useState(getToken());

  console.log(token);

  const loginContext = {
    token,
    setToken,
    getToken,
  };

  return (
    <LoginContext.Provider value={loginContext}>
      <Router>
        {token ? (
          <Switch>
            <Route exact path="/" component={Main} />
            <Route path="/api-test" component={ApiTest} />
          </Switch>
        ) : (
          <Switch>
            <Route exact path="/" component={Index} />
            <Route path="/login" component={LogIn} />
            <Route path="/api-test" component={ApiTest} />
          </Switch>
        )}
      </Router>
    </LoginContext.Provider>
  );
}

export default App;
