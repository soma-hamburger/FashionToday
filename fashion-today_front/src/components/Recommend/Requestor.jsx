import React from 'react';
import { LinkDiv } from '../Common/Components';
import ClosetIcon from '../../img/closet_icon.png';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import WhetherIcon from '../../img/whether_icon.png';
import TempIcon from '../../img/temp_icon.png';
import { makeDayObj } from '../../Tool';

const Requestor = ({ requestor }) => {
  const DayObj = makeDayObj(String(requestor.schedule.date));
  const today = new Date();
  const gap = today.getTime() - DayObj.getTime();
  const dday = Math.floor(gap / (1000 * 60 * 60 * 24));

  return (
    <div className="Requestor">
      <div className="Profile">
        <div className="ProfileImg">
          <img
            src={
              requestor.profile_image ? requestor.profile_image : ProfileIcon
            }
            alt="ProfileIcon"
          />
        </div>
        <div className="ProfileInfo">
          {requestor.name}
          <div className="Grade">
            <img src={GradeIcon} alt="GradIcon" />
            {requestor.grade}
          </div>
        </div>
      </div>
      <div className="UserInterface">
        <div>{requestor.self_introduction}</div>
        <LinkDiv to={`recommend/${requestor.id}`} className="ClosetViewButton">
          <>
            <img src={ClosetIcon} alt="ClosetIcon" />
            <span className="name">{requestor.name} 님의 옷장</span>
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
        <div className="Title">{requestor.schedule.schedule_title}</div>
        <div className="Introduction">
          {requestor.schedule.schedule_introduction}
        </div>
      </div>
    </div>
  );
};

export default Requestor;
