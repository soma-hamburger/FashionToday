import React, { useContext } from 'react';
import { Redirect } from 'react-router-dom';
import ReactRouterPropTypes from 'react-router-prop-types';
import Day from '../components/Calendar/Day';
import CalendarTable from '../components/Calendar/CalendarTable';
import '../style/Calendar.scss';
import { makeDayId, useFetch } from '../Tool';
import LookList from '../components/Main/LookList';
import { UserContext } from '../Context';

export const RedirectCalendar = () => {
  const today = new Date();
  const todayId = makeDayId(today);
  return <Redirect to={`/calendar/${todayId}`} />;
};

const Calendar = ({ match }) => {
  const DayId = match.params.dayid;
  const { token } = useContext(UserContext);
  const ScheduleList = useFetch('get', 'schedule/list', token);
  const LookListInfo = useFetch('post', 'looklist', token);
  console.log(ScheduleList);
  let scheduleIndex = -1;

  if (ScheduleList) {
    scheduleIndex = ScheduleList.data.schedule_array.findIndex(
      s => s.date === DayId,
    );
  }

  return (
    <div className="CalendarPage">
      <div className="CalendarSection">
        <CalendarTable
          dayId={DayId}
          scheduleList={ScheduleList ? ScheduleList.data : null}
        />
        <Day dayId={DayId} isSchedule={scheduleIndex >= 0} />
      </div>
      {LookListInfo && <LookList LookArray={LookListInfo.data.look_array} />}
    </div>
  );
};

Calendar.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};

export default Calendar;
