
import React, { useState } from 'react';
import {Route} from 'react-router-dom';
import Day from '../components/Calendar/Day';
import CalendarTable from '../components/Calendar/CalendarTable';
import { CalNav, CalTitle, Cal } from '../styled/calendar';

const Calendar = ({match}) => {
  const today = new Date();
  const [year, setYear] = useState(today.getFullYear());
  const [month, setMonth] = useState(today.getMonth());
  
  const prevMonth = (e) => {
    if(month > 1){
      setMonth(month-1);
    } else {
      setYear(year-1);
      setMonth(11);
    }
  }
  
  const nextMonth = (e) => {
    if(month < 11){
      setMonth(month+1);
    } else {
      setYear(year+1);
      setMonth(0);
    }
  }

  return (
    <Cal>
      <CalNav>
        <button onClick ={prevMonth}>이전달</button>
        <CalTitle>{year}년 {month + 1}월</CalTitle>
        <button onClick ={nextMonth}>다음달</button>
      </CalNav>
      <CalendarTable year={year} month={month} today={today} />
      <Route path={`${match.url}/:dayid`} component={Day} />
    </Cal>
  );
}

export default Calendar;