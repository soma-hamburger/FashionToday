import React from 'react';
import PropTypes from 'prop-types';
import LookPreview from '../Common/LookPreview';
import { LookGrid } from '../../styled/main';

const makeDailyLookView = array =>
  array.map((look, index) => (
    <LookPreview
      lookimgurl={look.look_image}
      username={look.recommender_name}
      profileimgurl={look.recommender_profile_image}
      key={index}
    />
  ));

const DailyLookList = ({ DalyLookList }) => {
  const array = DalyLookList.daily_look_array;
  const DailyLookView = makeDailyLookView(array);

  return <LookGrid>{DailyLookView}</LookGrid>;
};

DailyLookList.propTypes = {
  DalyLookList: PropTypes.object.isRequired,
};

export default DailyLookList;
