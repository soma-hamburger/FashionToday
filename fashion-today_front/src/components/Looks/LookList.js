import React from 'react';
import LookItem from '../Common/LookItem';
import { LookArray } from '../../dummyAPI';

const LookList = () => {
  const LookView = LookArray.map((item, index)=>(
    <LookItem item={item} key={index}/>
  ));

  return (
    <>
      Look List
      {LookView}
    </>
  );
}

export default LookList;