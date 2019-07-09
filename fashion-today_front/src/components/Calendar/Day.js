import React from 'react';
import { LookInfo } from '../../dummyAPI';
import LookDetail from '../Common/LookDetail';

const Day = ({match}) => {
  return (
    <>
      Day : {match.params.dayid}
      <LookDetail item={LookInfo} match={match}/>
    </>
  );
}

export default Day;