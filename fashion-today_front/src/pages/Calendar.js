import React, { useState } from 'react';
import { SLink } from '../styled';
import {Route} from 'react-router-dom';
import Day from '../components/Calendar/Day';
import CalendarTable from '../components/Calendar/CalendarTable';

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
    <>
      <SLink to={match.url}>Calendar</SLink>
      <button onClick ={prevMonth}>prev</button>
      <button onClick ={nextMonth}>next</button>
      <CalendarTable year={year} month={month} today={today} />
      <Route path={`${match.url}/:dayid`} component={Day} />
    </>
  );
}

export default Calendar;