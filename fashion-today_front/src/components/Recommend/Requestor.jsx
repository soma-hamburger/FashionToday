import React from 'react';
import PropTypes from 'prop-types';
import { LinkDiv } from '../Common/Components';
import ClosetIcon from '../../img/closet_icon.png';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import WhetherIcon from '../../img/whether_icon.png';
import TempIcon from '../../img/temp_icon.png';
import { makeDayObj } from '../../Tool';

const Requestor = ({ requestor }) => {
  const {
    schedule,
    profile_image,
    grade,
    name,
    id,
    self_introduction,
  } = requestor;

  const DayObj = makeDayObj(String(schedule.date));
  const today = new Date();
  const gap = today.getTime() - DayObj.getTime();
  const dday = Math.floor(gap / (1000 * 60 * 60 * 24));

  return (
    <div className="Requestor">
      <div className="Profile">
        <div className="ProfileImg">
          <img src={profile_image || ProfileIcon} alt="ProfileIcon" />
        </div>
        <div className="ProfileInfo">
          {name}
          <div className="Grade">
            <img src={GradeIcon} alt="GradIcon" />
            {grade}
          </div>
        </div>
      </div>
      <div className="UserInterface">
        <div>{self_introduction}</div>
        <LinkDiv to={`recommend/${id}`} className="ClosetViewButton">
          <>
            <img src={ClosetIcon} alt="ClosetIcon" />
            <span className="name">{name} 님의 옷장</span>
          </>
        </LinkDiv>
      </div>
      <div className="ScheduleInterface">
        <div className="Date">
          {DayObj.getFullYear()}년 {DayObj.getMonth() + 1}월 {DayObj.getDate()}
          일
        </div>
        <div className="DayBar">
          <div className="DDay">D{dday >= 0 ? `+${dday}` : dday}</div>
          <div className="Whether">
            <img src={WhetherIcon} alt="WhetherIcon" />
            <div>90%</div>
            <img src={TempIcon} alt="TempIcon" className="tempicon" />
            <div>14도</div>
          </div>
        </div>
        <div className="Title">{schedule.title}</div>
        <div className="Introduction">{schedule.introduce}</div>
      </div>
    </div>
  );
};

Requestor.propTypes = {
  requestor: PropTypes.shape({
    schedule: PropTypes.object.isRequired,
    profile_image: PropTypes.string.isRequired,
    grade: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    id: PropTypes.number.isRequired,
    self_introduction: PropTypes.string.isRequired,
  }).isRequired,
};

export default Requestor;
