import React, { useState } from 'react';
import { LinkDiv, ClickImg } from '../Common/Components';
import { makeDayId } from '../../Tool';
import PrevMonthIcon from '../../img/prevmonth_icon.png';
import NextMonthIcon from '../../img/nextmonth_icon.png';

const makeDay = (dayId, date, today, schedule) => {
  let className = 'Day';

  if (today) className = 'Today';

  return (
    <LinkDiv to={`/calendar/${dayId}`} key={dayId} className={className}>
      <>
        <div className="DateNum">{date}</div>
        {schedule ? (
          <div className="scheduleIcon" />
        ) : (
          <div className="schedule" />
        )}
      </>
    </LinkDiv>
  );
};

const makeTable = (year, month, dayId, scheduleList) => {
  const days = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const table = [[], [], [], [], [], [], []];

  const lastDate = new Date(year, month + 1, 0).getDate();
  const startDay = new Date(year, month, 1).getDay();

  table.forEach((week, index) => {
    week.push(
      <div className="DateName" key={`dateName${index}`}>
        {days[index]}
      </div>,
    );
  });

  let pDay = startDay;
  while (pDay > 0) {
    pDay -= 1;
    table[pDay].push(
      <div className="pDay" key={`pDay${pDay}`}>
        .
      </div>,
    );
  }

  for (let i = 1; i <= lastDate; i += 1) {
    const dayObj = new Date(year, month, i);
    const newDayId = makeDayId(dayObj);

    const today = newDayId === dayId;
    let schedule = null;
    if (scheduleList) {
      const index = scheduleList.schedule_array.findIndex(
        s => s.date === newDayId,
      );
      schedule = index >= 0;
    }

    table[dayObj.getDay()].push(makeDay(newDayId, i, today, schedule));
  }

  return table.map((week, index) => {
    if (index === 0) {
      return (
        <div key={index} className="sun">
          {week}
        </div>
      );
    }
    return <div key={index}>{week}</div>;
  });
};

const CalendarTable = ({ dayId, scheduleList }) => {
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

  const table = makeTable(year, month, dayId, scheduleList);

  return (
    <div className="Calendar">
      <div className="CalendarNav">
        <ClickImg onClick={prevMonth} src={PrevMonthIcon} />
        <div className="CalendarTitle">
          {year}. {month + 1}
        </div>
        <ClickImg onClick={nextMonth} src={NextMonthIcon} />
      </div>
      <div className="CalendarTable">{table}</div>
    </div>
  );
};

export default CalendarTable;
