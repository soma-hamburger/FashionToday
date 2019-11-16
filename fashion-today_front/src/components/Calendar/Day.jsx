import React, { useState, useContext } from 'react';
import PropTypes from 'prop-types';
import { makeDayObj, UserPost, useFetch } from '../../Tool';
import WhetherIcon from '../../img/whether_icon.png';
import TempIcon from '../../img/temp_icon.png';
import StarIcon from '../../img/star_icon.png';
import { UserContext } from '../../Context';
import CalLook from './CalLook';

const getDayMark = dday => {
  if (dday === 0) return 'D-day';
  if (dday > 0) return `D+${dday}`;
  return `D${dday}`;
};

const ScheduleDiv = ({ scheduleDetail, dday, scheduleForm }) => {
  const [lookOpen, setLookOpen] = useState(false);
  if (!scheduleDetail) {
    if (dday >= 0) return null;
    return scheduleForm;
  }
  const { title, introduce, star, look } = scheduleDetail;

  return (
    <div className="ScheduleDiv">
      <div className="ScheduleTitle">{title}</div>
      <div className="ScheduleIntroduction">: {introduce}</div>
      <div className="Bar">
        <div className="StarNum">
          <img src={StarIcon} alt="StarIcon" />
          <div>{star}</div>
        </div>
        {look && (
          <button type="button" onClick={() => setLookOpen(!lookOpen)}>
            룩 보기
          </button>
        )}
      </div>
      {look && lookOpen && (
        <CalLook look={look} close={() => setLookOpen(false)} />
      )}
    </div>
  );
};

ScheduleDiv.propTypes = {
  scheduleDetail: PropTypes.shape({
    title: PropTypes.string.isRequired,
    introduce: PropTypes.string.isRequired,
    star: PropTypes.number.isRequired,
    look: PropTypes.object.isRequired,
  }).isRequired,
  dday: PropTypes.number.isRequired,
  scheduleForm: PropTypes.element.isRequired,
};

const Day = ({ dayId, isSchedule }) => {
  const dayObj = makeDayObj(dayId);
  const today = new Date();
  const dday = Math.floor(
    (today.getTime() - dayObj.getTime()) / (1000 * 60 * 60 * 24),
  );

  const dayMark = getDayMark(dday);

  const { token } = useContext(UserContext);

  const ScheduleDetail = useFetch(
    'post',
    'schedule/detail',
    token,
    JSON.stringify({
      date: dayId,
    }),
  );

  const [title, setTitle] = useState('');
  const [introduce, setIntroduce] = useState('');
  const [starNum, setStarNum] = useState(0);

  const registerSchedule = async () => {
    const res = await UserPost('schedule', token, {
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
      <div className="DDay">{dayMark}</div>
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

Day.propTypes = {
  dayId: PropTypes.string.isRequired,
  isSchedule: PropTypes.bool.isRequired,
};

export default Day;
