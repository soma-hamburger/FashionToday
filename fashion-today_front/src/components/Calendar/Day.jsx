import React from 'react';
// import { UserScheduleDetail } from '../../defaultAPI';
// import DayLook from './DayLook';

const Day = ({ dayId }) => (
  <div className="Day">
    {dayId}
    <div className="Schedule">
      <p>일정</p>
    </div>
  </div>
);

export default Day;
