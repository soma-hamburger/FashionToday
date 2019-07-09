import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { Pages } from "./styled";

//7 Core Pages
import Calendar from "./pages/Calendar";
import Closet from "./pages/Closet";
import Daily from "./pages/Daily";
import Looks from "./pages/Looks";
import Main from "./pages/Main";
import MyPage from "./pages/MyPage";
import Recommend from "./pages/Recommend";
import MainBar from "./components/Common/MainBar";

function App() {
  return (
    <Router>
      <>
        <MainBar />
        <Pages>
          <Route path="/" exact component={Main} />
          <Route path="/calendar/" component={Calendar} />
          <Route path="/looks/" component={Looks} />
          <Route path="/closet/" component={Closet} />
          <Route path="/daily/" component={Daily} />
          <Route path="/recommend/" component={Recommend} />
          <Route path="/mypage/" component={MyPage} />
        </Pages>
      </>
    </Router>
  );
}

export default App;
