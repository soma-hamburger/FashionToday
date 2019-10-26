import React from 'react';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { ClickImg } from '../Common/Components';

const makeLookView = LookArray =>
  LookArray.map(look => {
    let ProfileImage = ProfileIcon;

    if (look.recommender_profile_image)
      ProfileImage = look.recommender_profile_image;

    return (
      <div className="LookPreview" key={look.look_id}>
        <ClickImg
          src={look.look_image}
          onClick={() => {}}
          alt={String(look.look_id)}
          className="LookImage"
        />
        <div className="UserInfo">
          <img
            src={ProfileImage}
            alt={look.user_profile_image}
            className="ProfileImage"
          />
          <div className="Name">{look.user_name}</div>
          <img src={GradeIcon} alt={look.user_id} className="LikeIcon" />
          <div className="Like">{look.look_like_num}</div>
        </div>
      </div>
    );
  });

const LookList = ({ LookArray }) => {
  const DailyLookView = makeLookView(LookArray);

  return <div className="LookList">{DailyLookView}</div>;
};

export default LookList;
