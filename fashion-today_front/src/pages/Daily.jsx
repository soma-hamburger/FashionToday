import React, {useState} from 'react';
import DailyLook from '../components/Daily/DailyLook';
import { DailyLookArray } from '../dummyAPI';

const Daily = ({match}) => {

  const [LookNum, setLookNum] = useState(0);

  const prevLook = (e) => {
    if(LookNum > 0) {
      setLookNum(LookNum-1);
    } else {
      setLookNum(DailyLookArray.length - 1);
    }
  }
  
  const nextLook = (e) => {
    if(LookNum < DailyLookArray.length - 1) {
      setLookNum(LookNum+1);
    } else {
      setLookNum(0);
    }
  }

  return (
    <>
      <button onClick ={prevLook}>prev</button>
      <button onClick ={nextLook}>next</button>
      <DailyLook item={DailyLookArray[LookNum]} />
    </>
  );
}

export default Daily;