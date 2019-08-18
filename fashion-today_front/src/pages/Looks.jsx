import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LookList from '../components/Looks/LookList';
import LookWindow from '../components/Looks/LookWindow';

const Looks = ({match}) => {
  return (
    <>
      <Switch>
        <Route path={`${match.url}/:lookid`} component={LookWindow} />
        <Route path={match.url} component={LookList}/>
      </Switch>
    </>
  );
}

export default Looks;