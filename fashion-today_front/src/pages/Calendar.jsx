import React, { useState } from 'react';
import { Route } from 'react-router-dom';
import Day from '../components/Calendar/Day';
import CalendarTable from '../components/Calendar/CalendarTable';

const Calendar = ({ match }) => {
  const today = new Date();
  const [year, setYear] = useState(today.getFullYear());
  const [month, setMonth] = useState(today.getMonth());
  const prevMonth = () => {
    if (month > 1) {
      setMonth(month - 1);
    } else {
      setYear(year - 1);
      setMonth(11);
    }
  };

  const nextMonth = () => {
    if (month < 11) {
      setMonth(month + 1);
    } else {
      setYear(year + 1);
      setMonth(0);
    }
  };

  return (
    <div>
      <div>
        <button type="button" onClick={prevMonth}>이전달</button>
        <div>
          {year} 년
          {' '}
          {month + 1} 월
        </div>
        <button type="button" onClick={nextMonth}>다음달</button>
      </div>
      <CalendarTable year={year} month={month} today={today} />
      <Route path={`${match.url}/:dayid`} component={Day} />
    </div>
  );
};

export default Calendar;
