import React, { useState, useContext } from 'react';
import { makeDayObj, UserPost, useFetch } from '../../Tool';
import WhetherIcon from '../../img/whether_icon.png';
import TempIcon from '../../img/temp_icon.png';
import StarIcon from '../../img/star_icon.png';
import { UserContext } from '../../Context';

const ScheduleDiv = ({ scheduleDetail, dday, scheduleForm }) => {
  if (!scheduleDetail) {
    if (dday >= 0) return null;
    return scheduleForm;
  }

  const { title, introduce } = scheduleDetail;

  return (
    <div className="ScheduleDiv">
      <div className="ScheduleTitle">{title}</div>
      <div className="ScheduleIntroduction">: {introduce}</div>
    </div>
  );
};

const Day = ({ dayId, isSchedule }) => {
  const dayObj = makeDayObj(dayId);
  const today = new Date();
  const dday = Math.floor(
    (today.getTime() - dayObj.getTime()) / (1000 * 60 * 60 * 24),
  );
  const UserInfo = useContext(UserContext);

  const ScheduleDetail = useFetch(
    'post',
    'schedule/detail',
    UserInfo.token,
    JSON.stringify({
      date: dayId,
    }),
  );

  const [title, setTitle] = useState('');
  const [introduce, setIntroduce] = useState('');
  const [starNum, setStarNum] = useState(0);

  const registerSchedule = async () => {
    const res = await UserPost('schedule', UserInfo.token, {
      date: dayId,
      title,
      introduce,
      star: starNum,
    });
    console.log(res);
    window.location.reload();
  };

  const ScheduleForm = (
    <div className="ScheduleForm">
      <input
        id="Title"
        name="Title"
        type="text"
        placeholder="일정 제목"
        autoComplete="off"
        value={title}
        onChange={e => setTitle(e.target.value)}
      />
      <textarea
        id="Description"
        name="Description"
        type="text"
        rows="5"
        value={introduce}
        placeholder="일정 설명을 써주세요."
        onChange={e => setIntroduce(e.target.value)}
      />
      <div className="SubmitBar">
        <div className="StarNum">
          <img src={StarIcon} alt="StarIcon" />
          <input
            id="StarNum"
            name="StarNum"
            type="number"
            value={starNum}
            min={0}
            max={10}
            onChange={e => setStarNum(e.target.value)}
          />
        </div>
        <div>
          <button type="button" onClick={registerSchedule}>
            일정 등록
          </button>
        </div>
      </div>
    </div>
  );

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
      <ScheduleDiv
        scheduleDetail={
          ScheduleDetail && isSchedule ? ScheduleDetail.data : null
        }
        dday={dday}
        scheduleForm={ScheduleForm}
      />
    </div>
  );
};

export default Day;
