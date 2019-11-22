import React, { useMemo } from 'react';
import PropTypes from 'prop-types';
import { ClickImg, getCategoryIcon } from '../Common/Components';
import CheckIcon from '../../img/checkbox_icon.png';
import { filteringArray } from '../../Tool';

const MakeClothes = (filterdArray, onClick, lookData) => {
  if (!filterdArray) {
    return null;
  }
  return filterdArray.map(clothes => {
    const categoryIcon = getCategoryIcon(clothes.category);
    const index = lookData.findIndex(i => i.src === clothes.clothes_image);

    return (
      <div className="Clothes" key={clothes.clothes_id}>
        {index >= 0 && (
          <img src={CheckIcon} alt="CheckIcon" className="CheckIcon" />
        )}
        <ClickImg
          onClick={onClick}
          src={clothes.clothes_image}
          alt={String(clothes.clothes_id)}
          className="Image"
        />
        <div className="Account">
          {categoryIcon}
          <div>
            {clothes.category}, {clothes.color}
          </div>
        </div>
      </div>
    );
  });
};

const RecommendCloset = ({ category, color, array, onClick, lookData }) => {
  const closetTable = useMemo(() => {
    const filterdArray = filteringArray(category, color, array);
    return MakeClothes(filterdArray, onClick, lookData);
  }, [array, category, color, lookData, onClick]);

  return <div className="ClosetTable">{closetTable}</div>;
};

RecommendCloset.defaultProps = {
  category: null,
  color: null,
};

RecommendCloset.propTypes = {
  category: PropTypes.string,
  color: PropTypes.string,
  array: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired,
  lookData: PropTypes.array.isRequired,
};

export default RecommendCloset;
