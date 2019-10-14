import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ApiTest from './components/Common/ApiTest';
import LogIn from './pages/LogIn';
import { LoginContext } from './Context';
import Main from './pages/Main';
import Index from './pages/Index';
import LayOut from './components/Common/LayOut';
import Closet from './pages/Closet';
import Recommend from './pages/Recommend';

const getToken = () => {
  console.log('getToken');
  const token = localStorage.getItem('token');
  if (token === null) return false;
  return token;
};

function App() {
  const [token, setToken] = useState(getToken());

  const loginContext = {
    token,
    setToken,
    getToken,
  };

  return (
    <LoginContext.Provider value={loginContext}>
      <Router>
        {token ? (
          // LogIn State Components
          <LayOut>
            <Switch>
              <Route exact path="/" component={Main} />
              <Route path="/closet" component={Closet} />
              <Route path="/recommend" component={Recommend} />
              <Route path="/api-test" component={ApiTest} />
            </Switch>
          </LayOut>
        ) : (
          // LogOut State Components
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
