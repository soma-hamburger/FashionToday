import React, { useContext } from 'react';
import ProfileIcon from '../../img/default_profile.png';
import LikedIcon from '../../img/heart_icon.png';
import UnLikeIcon from '../../img/heart_white.png';
import { ClickImg } from '../Common/Components';
import { UserContext } from '../../Context';
import { UserPost } from '../../Tool';

const makeLookView = (LookArray, onClick = () => {}, token) =>
  LookArray.map(look => {
    let ProfileImage = ProfileIcon;
    let LikeIcon = UnLikeIcon;

    if (look.recommender_profile_image)
      ProfileImage = look.recommender_profile_image;
    if (look.is_like) LikeIcon = LikedIcon;

    const clickLike = async () => {
      console.log('clickLike');
      const res = await UserPost('look/like', token, {
        look_id: look.look_id,
        is_like: look.is_like,
      });
      console.log(res);
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
            alt={look.user_id}
            className="LikeIcon"
          />
          <div className="Like">
            <>{look.look_like_num}</>
          </div>
        </div>
      </div>
    );
  });

const LookList = ({ LookArray, onClick }) => {
  const UserInfo = useContext(UserContext);
  const DailyLookView = makeLookView(LookArray, onClick, UserInfo.token);

  return <div className="LookList">{DailyLookView}</div>;
};

export default LookList;
