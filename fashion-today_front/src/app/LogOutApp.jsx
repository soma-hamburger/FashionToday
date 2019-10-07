/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ApiTest from '../components/Common/ApiTest';
import LogOn from '../components/Common/LogOn';
import LogIn from '../components/Common/LogIn';

function LogOutApp() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={LogOn} />
        <Route path="/login" component={LogIn} />
        <Route path="/api-test" component={ApiTest} />
      </Switch>
    </Router>
  );
}

export default LogOutApp;
