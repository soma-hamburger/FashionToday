import React from 'react';
import { SLink, CalTable } from '../../styled';
import Character from '../Common/Character';
import { LookInfo } from '../../dummyAPI';

const makeDayId = (dateObj) => {
  const mm = dateObj.getMonth() + 1; // getMonth() is zero-based
  const dd = dateObj.getDate();

  return [dateObj.getFullYear(),
          (mm>9 ? '' : '0') + mm,
          (dd>9 ? '' : '0') + dd
         ].join('');
}

const makeDay = (dayId, date, color) => (
  <SLink to ={`/calendar/${dayId}`} key={dayId} color={color ? color : "black"}>
    {date} <br/>
    <Character item={LookInfo} isSet={false}/>
  </SLink>
);

const makeTable = (year, month, today) => {
  const days = ['일', '월', '화', '수', '목', '금', '토'];
  const table = [[],[],[],[],[],[],[]];

  const lastDate = new Date(year, month+1, 0).getDate();
  const startDay = new Date(year, month, 1).getDay();
  const prevDate = new Date(year, month, 0).getDate();

  table.forEach((week,index) => {
    week.push(<div key = {index}>{days[index]}</div>);
  });

  let pDay = startDay;
  let pDate = prevDate;

  while(pDay > 0){
    const pDayObj = new Date(year,month-1,pDate);
    const dayId = makeDayId(pDayObj);
    table[--pDay].push(makeDay(dayId, pDate--, "#abc"));
  }

  for(let i=1; i <= lastDate; i++){
    const dayObj = new Date(year, month, i);
    const dayId = makeDayId(dayObj);

    if(dayId === makeDayId(today)){
      table[dayObj.getDay()].push(makeDay(dayId, i, "green"));
      continue;
    }
    table[dayObj.getDay()].push(makeDay(dayId, i));
  }
  
  const showTable = table.map((week, index)=>(<div key={index}>{week}</div>));

  return <CalTable>{showTable}</CalTable>;
}


const CalendarTable = ({year, month, today}) => {

  const table = makeTable(year, month, today);
  
  return (
    <>
      <h3>{year}년 {month + 1}월</h3>
      {table}
    </>
  );
}

export default CalendarTable;