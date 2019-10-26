import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ApiTest from './components/Common/ApiTest';
import LogIn from './pages/LogIn';
import Main from './pages/Main';
import Index from './pages/Index';
import Closet from './pages/Closet';
import Recommend from './pages/Recommend';
import LayOut from './components/Common/LayOut';
import Calendar, { RedirectCalendar } from './pages/Calendar';
import MyPage from './pages/MyPage';

const getToken = () => {
  const token = localStorage.getItem('token');

  console.log(`getToken: ${token}`);
  if (token === null) return false;
  return token;
};

function App() {
  const [token, setToken] = useState(getToken());

  return (
    <Router>
      {token ? (
        // LogIn State Components
        <LayOut token={token}>
          <Switch>
            <Route exact path="/" component={Main} />
            <Route path="/closet" component={Closet} />
            <Route exact path="/calendar" component={RedirectCalendar} />
            <Route path="/calendar/:dayid" component={Calendar} />
            <Route path="/recommend" component={Recommend} />
            <Route path="/mypage" component={MyPage} />
            <Route path="/api-test" component={ApiTest} />
          </Switch>
        </LayOut>
      ) : (
        // LogOut State Components
        <Switch>
          <Route exact path="/" component={Index} />
          <Route
            path="/login"
            render={props => (
              <LogIn
                setToken={setToken}
                history={props.history}
                location={props.location}
              />
            )}
          />
          <Route path="/api-test" component={ApiTest} />
        </Switch>
      )}
    </Router>
  );
}

export default App;
