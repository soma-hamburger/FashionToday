import React, {useState,useEffect} from 'react';
import { LookArray } from '../../dummyAPI';
import LookPrewindow from './LookPrewindow';
import { LookGrid } from '../../styled/looks';

const makeLookView = (gridNum) => {
  const LookBase = [];
  let num = gridNum;
  let dummyNum = 15;

  while(num--){
    LookBase.push([]);
  }

  while(dummyNum--){
    LookArray.map((item, index)=>
      LookBase[(index+(15-dummyNum)%gridNum)%gridNum].push(
        <LookPrewindow item={item} key={index+dummyNum*100}/>)
    );
  }

  return LookBase.map((item, index)=><div key={index}>{item}</div>);
}

const useGridNum = () => {
  const [width, setWidth] = useState(window.innerWidth);
  
  useEffect(() => {
    const handleResize = () => setWidth(window.innerWidth);
    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);
  
  if(width > 800) return 4;
  if(width > 600) return 3;
  return 2;
}

const LookList = () => {
  const gridNum = useGridNum();
  const LookView = makeLookView(gridNum);
  return (
    <LookGrid gridNum={gridNum}>
      {LookView}
    </LookGrid>
  );
}

export default LookList;