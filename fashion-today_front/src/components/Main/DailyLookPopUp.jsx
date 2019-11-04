import React from 'react';
import { ClickImg } from '../Common/Components';
import CloseIcon from '../../img/close_icon.png';
import PinIcon from '../../img/pin_icon.png';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { useFetch, makeDayId, UserPost } from '../../Tool';
import { getCategoryIcon } from '../Closet/ClosetTable';

export const Clothes = ({ clothes }) => {
  const ClothesArray = clothes.map(c => {
    const categoryIcon = getCategoryIcon(c.category);
    return (
      <div className="CItem" key={`Clothes${c.clothes_id}`}>
        <div className="Image">
          <img
            src={c.clothes_image}
            alt={String(c.clothes_id)}
            className="Image"
          />
        </div>
        <div className="Account">
          {categoryIcon}
          <div>
            {c.category}, {c.color}
          </div>
        </div>
      </div>
    );
  });

  return <div className="ClothesArray">{ClothesArray}</div>;
};

const DailyLookPopUp = ({ lookId, close, token }) => {
  console.log(lookId);
  const today = new Date();
  const date = makeDayId(today);
  const DailyLookDetail = useFetch(
    'post',
    'dailylook',
    token,
    JSON.stringify({
      look_id: lookId,
    }),
  );

  const choiceDailyLook = async () => {
    console.log('choiceDL');
    const res = await UserPost('look/choice', token, {
      look_id: lookId,
      date,
    });
    console.log(res);
  };

  console.log(DailyLookDetail);

  return (
    <div className="DailyLookPopUp">
      <div className="Head">
        {DailyLookDetail && (
          <div className="PopUpInterface">
            <img src={PinIcon} alt="PinIcon" />
            <div className="PopUpTitle">{DailyLookDetail.data.look_title}</div>
            <div className="Description">
              {DailyLookDetail.data.look_introduction}
            </div>
          </div>
        )}
        <ClickImg onClick={close} src={CloseIcon} className="CloseIcon" />
      </div>
      <div className="Body">
        {DailyLookDetail && (
          <>
            <div className="lookImage">
              <img
                src={DailyLookDetail.data.look_image}
                alt="look_image"
                className="LookImage"
              />
              <button
                onClick={choiceDailyLook}
                type="button"
                className="ChoiceButton"
              >
                룩선택
              </button>
              <div className="RecommenderInfo">
                <img
                  src={
                    DailyLookDetail.data.recommender.profile_image
                      ? DailyLookDetail.data.recommender.profile_image
                      : ProfileIcon
                  }
                  alt="ProfileImage"
                  className="ProfileImage"
                />
                <div className="Name">
                  {DailyLookDetail.data.recommender.name}
                </div>
                <img src={GradeIcon} alt="GradeIcon" className="GradeIcon" />
                <div className="Grade">
                  {DailyLookDetail.data.recommender.grade}
                </div>
              </div>
            </div>
            <Clothes clothes={DailyLookDetail.data.clothes_array} />
          </>
        )}
      </div>
    </div>
  );
};

export default DailyLookPopUp;
