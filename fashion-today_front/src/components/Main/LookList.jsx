import React from 'react';
import ProfileIcon from '../../img/default_profile.png';
import LikedIcon from '../../img/heart_icon.png';
import UnLikeIcon from '../../img/heart_white.png';
import { ClickImg, ClickDiv } from '../Common/Components';

const makeLookView = (LookArray, onClick = () => {}) =>
  LookArray.map(look => {
    let ProfileImage = ProfileIcon;
    let LikeIcon = UnLikeIcon;

    if (look.recommender_profile_image)
      ProfileImage = look.recommender_profile_image;
    if (look.is_like) LikeIcon = LikedIcon;

    return (
      <div className="LookPreview" key={look.look_id}>
        <ClickImg
          src={look.look_image}
          onClick={onClick}
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
          <img src={LikeIcon} alt={look.user_id} className="LikeIcon" />
          <ClickDiv onClick={() => {}} className="Like">
            <>{look.look_like_num}</>
          </ClickDiv>
        </div>
      </div>
    );
  });

const LookList = ({ LookArray, onClick }) => {
  const DailyLookView = makeLookView(LookArray, onClick);

  return <div className="LookList">{DailyLookView}</div>;
};

export default LookList;
