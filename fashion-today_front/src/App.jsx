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
import ErrorPage from './pages/ErrorPage';

const getToken = () => {
  const token = localStorage.getItem('token');
  if (token === null) return false;
  return token;
};

function App() {
  const [token, setToken] = useState(getToken());
  const error = localStorage.getItem('error');

  if (error) return <ErrorPage error={error} />;

  return (
    <Router>
      {token ? (
        // LogIn State Components
        <>
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
        </>
      ) : (
        // LogOut State Components
        <Switch>
          <Route exact path="/" component={Index} />
          <Route
            path="/login"
            render={({ history, location }) => (
              <LogIn
                setToken={setToken}
                history={history}
                location={location}
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
