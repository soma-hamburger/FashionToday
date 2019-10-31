import React from 'react';
import { ClickImg, ClickDiv } from '../Common/Components';
import CloseIcon from '../../img/close_icon.png';
import PinIcon from '../../img/pin_icon.png';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { useFetch } from '../../Tool';
import { Clothes } from './DailyLookPopUp';
import LikedIcon from '../../img/heart_icon.png';
import UnLikeIcon from '../../img/heart_white.png';

const LookPopUp = ({ lookId, close, token }) => {
  console.log(lookId);

  const LookDetail = useFetch('post', 'look', token, {
    look_id: lookId,
  });

  console.log(LookDetail);
  return (
    <div className="LookPopUp">
      <div className="Head">
        {LookDetail && (
          <div className="PopUpInterface">
            <img src={PinIcon} alt="PinIcon" />
            <div className="PopUpTitle">{LookDetail.data.look_title}</div>
            <div className="Description">
              {LookDetail.data.look_introduction}
            </div>
            <div className="UserInfo">
              <img
                src={
                  LookDetail.data.user_profile_image
                    ? LookDetail.data.user_profile_image
                    : ProfileIcon
                }
                alt={LookDetail.data.user_profile_image}
                className="ProfileImage"
              />
              <div className="Name">{LookDetail.data.user_name}</div>
              <img
                src={LookDetail.data.is_like ? LikedIcon : UnLikeIcon}
                alt={LookDetail.data.user_id}
                className="LikeIcon"
              />
              <ClickDiv onClick={() => {}} className="Like">
                <>{LookDetail.data.like_num}</>
              </ClickDiv>
            </div>
          </div>
        )}
        <ClickImg onClick={close} src={CloseIcon} className="CloseIcon" />
      </div>
      <div className="Body">
        {LookDetail && (
          <>
            <div className="lookImage">
              <img
                src={LookDetail.data.look_image}
                alt="look_image"
                className="LookImage"
              />
              <div className="RecommenderInfo">
                <img
                  src={
                    LookDetail.data.recommender.profile_image
                      ? LookDetail.data.recommender.profile_image
                      : ProfileIcon
                  }
                  alt="ProfileImage"
                  className="ProfileImage"
                />
                <div className="Name">{LookDetail.data.recommender.name}</div>
                <img src={GradeIcon} alt="GradeIcon" className="GradeIcon" />
                <div className="Grade">{LookDetail.data.recommender.grade}</div>
              </div>
            </div>
            <Clothes clothes={LookDetail.data.clothes_array} />
          </>
        )}
      </div>
    </div>
  );
};

export default LookPopUp;
