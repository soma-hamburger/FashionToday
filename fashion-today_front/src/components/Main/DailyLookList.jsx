import React, { useContext, useMemo } from 'react';
import PropTypes from 'prop-types';
import ProfileIcon from '../../img/default_profile.png';
import GradeIcon from '../../img/grade_icon.png';
import { ClickImg } from '../Common/Components';
import { useFetch } from '../../Tool';
import { UserContext } from '../../Context';

const makeDailyLookView = (LookArray, onClick = () => {}) =>
  LookArray.map(look => {
    let ProfileImage = ProfileIcon;

    if (look.recommender.profile_image)
      ProfileImage = look.recommender.profile_image;

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
            alt={look.recommender.id}
            className="ProfileImage"
          />
          <div className="Name">{look.recommender.name}</div>
          <img
            src={GradeIcon}
            alt={look.recommender.id}
            className="GradeIcon"
          />
          <div className="Grade">{look.recommender.grade}</div>
        </div>
      </div>
    );
  });

const DailyLookList = ({ onClick }) => {
  const { token } = useContext(UserContext);
  const DailyLookListInfo = useFetch('get', 'dailylist', token);

  const DailyLookView = useMemo(() => {
    if (!DailyLookListInfo) return null;
    return makeDailyLookView(DailyLookListInfo.data.daily_look_array, onClick);
  }, [DailyLookListInfo, onClick]);

  return <div className="DailyLookList">{DailyLookView}</div>;
};

DailyLookList.propTypes = {
  onClick: PropTypes.func.isRequired,
};

export default DailyLookList;
