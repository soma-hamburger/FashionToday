import React, { useContext } from 'react';
import { ClickImg } from '../Common/Components';
import CloseIcon from '../../img/close_icon.png';
import PinIcon from '../../img/pin_icon.png';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { Clothes } from '../Main/DailyLookPopUp';
import { UserPost } from '../../Tool';
import { UserContext } from '../../Context';

const CalLook = ({ look, date, close }) => {
  const { token } = useContext(UserContext);
  const {
    id,
    look_title,
    look_image,
    look_introduction,
    recommender,
    share,
    clothes_array,
  } = look;

  const clickShare = async () => {
    const res = await UserPost('look/share', token, {
      look_id: id,
      date,
    });
    if (res) {
      window.location.reload();
    }
    return res;
  };

  return (
    <div className="CalLook">
      <div className="Head">
        <div className="PopUpInterface">
          <img src={PinIcon} alt="PinIcon" />
          <div className="PopUpTitle">{look_title}</div>
          <div className="Description">{look_introduction}</div>
        </div>
        <ClickImg onClick={close} src={CloseIcon} className="CloseIcon" />
      </div>
      <div className="Body">
        <div className="lookImage">
          <img src={look_image} alt="look_image" className="LookImage" />
          {!share && (
            <button onClick={clickShare} type="button" className="ChoiceButton">
              룩 공유
            </button>
          )}
          <div className="RecommenderInfo">
            <img
              src={
                recommender.profile_image
                  ? recommender.profile_image
                  : ProfileIcon
              }
              alt="ProfileImage"
              className="ProfileImage"
            />
            <div className="Name">{recommender.name}</div>
            <img src={GradeIcon} alt="GradeIcon" className="GradeIcon" />
            <div className="Grade">{recommender.grade}</div>
          </div>
        </div>
        <Clothes clothes={clothes_array} />
      </div>
    </div>
  );
};

export default CalLook;
