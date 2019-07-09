import React from 'react';
import LookWindow from '../Common/LookWindow';
import { A } from '../../styled';

const LookList = () => {
  const LookArray = [{"key": 1},{"key": 2},{"key": 3}];

  const LookView = LookArray.map((item, index)=>(
    <A to={`/look/${item.key}`} key={index}>
      <LookWindow item={item}/>
    </A>
  ));
  return (
    <>
      LookList
      {LookView}
    </>
  );
}

export default LookList;