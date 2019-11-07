import React, { useMemo } from 'react';
import { ClickImg } from '../Common/Components';
import { getCategoryIcon } from '../Closet/ClosetTable';
import CheckIcon from '../../img/checkbox_icon.png';

const MakeClothes = (category, color, array, onClick, lookData) => {
  let filterdArray = array;

  if (category) {
    filterdArray = array.filter(clothes => clothes.category === category);
  }
  if (color) {
    filterdArray = array.filter(clothes => clothes.color === color);
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
  const closetTable = useMemo(
    () => MakeClothes(category, color, array, onClick, lookData),
    [array, category, color, lookData, onClick],
  );

  return <div className="ClosetTable">{closetTable}</div>;
};

export default RecommendCloset;
