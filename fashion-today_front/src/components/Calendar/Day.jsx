import React from 'react';
import { LookInfo } from '../../dummyAPI';
import DayLook from './DayLook';
import { DayGrid, Schedule } from '../../styled/calendar';

const Day = ({match}) => {
  return (
    <>
      <DayGrid>
        <Schedule>
          Day : {match.params.dayid}
          <p>일정</p>
        </Schedule>
        <DayLook item={LookInfo}/> 
      </DayGrid>
    </>
  );
}

export default Day;