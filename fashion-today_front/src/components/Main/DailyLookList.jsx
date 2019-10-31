import React from 'react';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { ClickImg } from '../Common/Components';

const makeDailyLookView = (LookArray, onClick = () => {}) =>
  LookArray.map(look => {
    let ProfileImage = ProfileIcon;

    if (look.recommender_profile_image)
      ProfileImage = look.recommender_profile_image;

    return (
      <div className="LookPreview" key={look.look_id}>
        <ClickImg
          src={look.look_image}
          alt={String(look.look_id)}
          onClick={onClick}
          className="LookImage"
        />
        <div className="UserInfo">
          <img
            src={ProfileImage}
            alt={look.recommender_id}
            className="ProfileImage"
          />
          <div className="Name">{look.recommender_name}</div>
          <img
            src={GradeIcon}
            alt={look.recommender_id}
            className="GradeIcon"
          />
          <div className="Grade">{look.recommender_grade}</div>
        </div>
      </div>
    );
  });

const DailyLookList = ({ LookArray, onClick }) => {
  const DailyLookView = makeDailyLookView(LookArray, onClick);

  return <div className="DailyLookList">{DailyLookView}</div>;
};

export default DailyLookList;
