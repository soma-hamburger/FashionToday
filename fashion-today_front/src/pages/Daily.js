import React from 'react';
import { SLink } from '../styled';
import {Switch, Route} from 'react-router-dom';
import DailyLook from '../components/Daily/DailyLook';
import DClothes from '../components/Daily/DClothes';

const Daily = ({match}) => {
  return (
    <>
      <SLink to ={match.url}>Daily Look</SLink>
      <Switch>
        <Route path={`${match.url}/:clothesid`} component={DClothes}/>
        <Route path={match.url} component={DailyLook}/>
      </Switch>
    </>
  );
}

export default Daily;