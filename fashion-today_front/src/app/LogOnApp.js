import React from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

//7 Core Pages
import Calendar from "../pages/Calendar";
import Closet from "../pages/Closet";
import Daily from "../pages/Daily";
import Looks from "../pages/Looks";
import Main from "../pages/Main";
import MyPage from "../pages/MyPage";
import Recommend from "../pages/Recommend";

import MainBar from "../components/Common/MainBar";
import { Pages } from "../styled";

function LogOnApp() {
  return (
    <Router>
    <>
      <Route path="/" component={MainBar} />
      <Switch>
      <Pages>
        <Route path="/" exact component={Main} />
        <Route path="/calendar/" component={Calendar} />
        <Route path="/looks/" component={Looks} />
        <Route path="/closet/" component={Closet} />
        <Route path="/daily/" component={Daily} />
        <Route path="/recommend/" component={Recommend} />
        <Route path="/mypage/" component={MyPage} />
      </Pages>
      </Switch>
    </>
    </Router>
  )
}

export default LogOnApp;