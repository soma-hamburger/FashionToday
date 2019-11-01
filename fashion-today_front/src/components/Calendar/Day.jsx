import React from 'react';
import { makeDayObj, useWeahterAPI } from '../../Tool';
import WhetherIcon from '../../img/whether_icon.png';
import TempIcon from '../../img/temp_icon.png';
import StarIcon from '../../img/star_icon.png';

const Day = ({ dayId, scheduleDetail }) => {
  const dayObj = makeDayObj(dayId);
  const today = new Date();
  const gap = today.getTime() - dayObj.getTime();
  const dday = Math.floor(gap / (1000 * 60 * 60 * 24));

  const weather = useWeahterAPI(dayId);

  console.log(weather);
  console.log(scheduleDetail);
  return (
    <div className="DayComponent">
      <div className="DDay">D{dday >= 0 ? `+${dday}` : dday}</div>
      <div className="DayTitle">
        {dayObj.getFullYear()}년 {dayObj.getMonth() + 1}월 {dayObj.getDate()}일
      </div>
      <div className="Whether">
        <img src={WhetherIcon} alt="WhetherIcon" />
        <div>90%</div>
        <img src={TempIcon} alt="TempIcon" className="tempicon" />
        <div>14도</div>
      </div>
      <form className="ScheduleForm">
        <input id="Title" name="Title" type="text" placeholder="일정 제목" />
        <textarea
          id="Description"
          name="Description"
          type="text"
          rows="5"
          placeholder="일정 설명을 써주세요."
        />
        <div className="SubmitBar">
          <div className="StarNum">
            <img src={StarIcon} alt="StarIcon" />
            <input id="StarNum" name="StarNum" type="number" min={0} max={10} />
          </div>
          <div>
            <button type="submit">일정 등록</button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Day;
