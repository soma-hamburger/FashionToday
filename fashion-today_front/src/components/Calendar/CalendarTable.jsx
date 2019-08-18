import React from 'react';
import { SLink } from '../../styled';
import { LookInfo } from '../../dummyAPI';
import { CalFrame, CalDay, DayNum, DateName } from '../../styled/calendar';

const makeDayId = (dateObj) => {
  const mm = dateObj.getMonth() + 1; // getMonth() is zero-based
  const dd = dateObj.getDate();

  return [dateObj.getFullYear(),
          (mm>9 ? '' : '0') + mm,
          (dd>9 ? '' : '0') + dd
         ].join('');
}

const makeDay = (dayId, date, color) => {
  let look;
  let schedule;

  if (dayId==="20190728" || dayId==="20190726") {
    look = LookInfo;
  }
  if (dayId==="20190730") {
    schedule = "Interview"
  }

  return (
    <SLink to ={`/calendar/${dayId}`} key={dayId} color={color ? color : "black"}>
      <CalDay>
        <DayNum>{date}</DayNum>
        {look ? <img src={look.look_picture} alt={look.title}/> : ""}
        {schedule ? `일정 : ${schedule}`: ""}
      </CalDay>
    </SLink>
  )
};

const makeTable = (year, month, today) => {
  const days = ['일', '월', '화', '수', '목', '금', '토'];
  const table = [[],[],[],[],[],[],[]];

  const lastDate = new Date(year, month+1, 0).getDate();
  const startDay = new Date(year, month, 1).getDay();
  const prevDate = new Date(year, month, 0).getDate();

  table.forEach((week,index) => {
    week.push(<DateName key = {index}>{days[index]}</DateName>);
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
  
  return table.map((week, index)=>(<div key={index}>{week}</div>));
}


const CalendarTable = ({year, month, today}) => {

  const table = makeTable(year, month, today);
  
  return (
    <CalFrame>
      {table}
    </CalFrame>
  );
}

export default CalendarTable;