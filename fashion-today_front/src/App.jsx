import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ApiTest from './components/Common/ApiTest';
import LogIn from './pages/LogIn';
import Main from './pages/Main';
import Index from './pages/Index';
import Closet from './pages/Closet';
import Recommend from './pages/Recommend';
import LayOut from './components/Common/LayOut';

const getToken = () => {
  const token = localStorage.getItem('token');

  console.log(`getToken: ${token}`);
  if (token === null) return false;
  return token;
};

function App() {
  const token = getToken();

  return (
    <Router>
      {token ? (
        // LogIn State Components
        <LayOut token={token}>
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
  );
}

export default App;
