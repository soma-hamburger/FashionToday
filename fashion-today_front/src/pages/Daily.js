import React, {useState} from 'react';
import { SLink } from '../styled';
import {Switch, Route} from 'react-router-dom';
import DailyLook from '../components/Daily/DailyLook';
import DClothes from '../components/Daily/DClothes';
import LookDetail from '../components/Common/LookDetail';
import { LookArray } from '../dummyAPI';

const Daily = ({match}) => {

  const [LookNum, setLookNum] = useState(0);

  const prevLook = (e) => {
    if(LookNum > 0) {
      setLookNum(LookNum-1);
    } 
  }
  
  const nextLook = (e) => {
    if(LookNum < LookArray.length - 1) {
      setLookNum(LookNum+1);
    }
  }

  return (
    <>
      <SLink to ={match.url}>Daily Look</SLink>
      <button onClick ={prevLook}>prev</button>
      <button onClick ={nextLook}>next</button>
      <DailyLook item={LookArray[LookNum]} />
    </>
  );
}

export default Daily;