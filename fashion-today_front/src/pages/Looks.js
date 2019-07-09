import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LookList from '../components/Looks/LookList';
import LookWindow from '../components/Looks/LookWindow';
import { SLink } from '../styled';

const Looks = ({match}) => {
  return (
    <>
      <SLink to = {match.url}>Looks</SLink>
      <Switch>
        <Route path={`${match.url}/:lookid`} component={LookWindow} />
        <Route path={match.url} component={LookList}/>
      </Switch>
    </>
  );
}

export default Looks;