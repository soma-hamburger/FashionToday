import React, { useContext, useMemo } from 'react';
import ProfileIcon from '../../img/default_profile.png';
import LikedIcon from '../../img/heart_icon.png';
import UnLikeIcon from '../../img/heart_white.png';
import { ClickImg } from '../Common/Components';
import { UserContext } from '../../Context';
import { UserPost } from '../../Tool';

const makeLookView = (LookArray, onClick = () => {}, token) => {
  const reversedArray = LookArray.reverse();
  console.log(reversedArray);
  return reversedArray.map(look => {
    let ProfileImage = ProfileIcon;
    let LikeIcon = UnLikeIcon;

    if (look.user_profile_image) ProfileImage = look.user_profile_image;
    if (look.is_like) LikeIcon = LikedIcon;

    const clickLike = async () => {
      const res = await UserPost('look/like', token, {
        look_id: look.look_id,
        is_like: look.is_like,
      });
      return res;
    };

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
          <ClickImg
            onClick={clickLike}
            src={LikeIcon}
            alt={String(look.user_id)}
            className="LikeIcon"
          />
          <div className="Like">
            <>{look.look_like_num}</>
          </div>
        </div>
      </div>
    );
  });
};

const LookList = ({ LookArray, onClick }) => {
  const { token } = useContext(UserContext);
  const DailyLookView = useMemo(() => makeLookView(LookArray, onClick, token), [
    LookArray,
    token,
    onClick,
  ]);

  return <div className="LookList">{DailyLookView}</div>;
};

export default LookList;
