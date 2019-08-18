import React, {useState} from 'react';
import DailyLook from '../components/Daily/DailyLook';
import { DailyLookArray } from '../dummyAPI';
import { DailyNav } from '../styled/daily';
import { Button } from '../styled';

const Daily = ({match}) => {
  console.log(match);
  
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
    <DailyNav>
      <Button onClick={prevLook}>{`<`}</Button>
      <DailyLook item={DailyLookArray[LookNum]} />
      <Button onClick={nextLook}>{`>`}</Button>
    </DailyNav>
  );
}

export default Daily;