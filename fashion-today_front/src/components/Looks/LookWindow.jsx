import React from 'react';
import LookDetail from '../Common/LookDetail';
import { LookArray } from '../../dummyAPI';

const LookWindow = ({match}) => {
  const LookInfo = LookArray[match.params.lookid-1];

  return (
    <>
      Look Window
      <LookDetail item={LookInfo} match={match}/>
    </>
  );
}

export default LookWindow;