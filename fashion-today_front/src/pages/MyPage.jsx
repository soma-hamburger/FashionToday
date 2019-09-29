import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { SLink } from '../styled';
import Settings from '../components/MyPage/Setting';
import MyPageMain from '../components/MyPage/MyPageMain';

const MyPage = ({ match }) => (
  <>
    <SLink to={match.url}>My Page</SLink>
    <Switch>
      <Route path={`${match.url}/settings`} component={Settings} />
      <Route path={match.url} component={MyPageMain} />
    </Switch>
  </>
);

export default MyPage;
