import React from 'react';
import { ClickImg } from '../Common/Components';
import { getCategoryIcon } from '../Closet/ClosetTable';
import CheckIcon from '../../img/checkbox_icon.png';

const MakeClothes = (category, color, array, onClick, lookData) => {
  console.log(lookData);
  let filterdArray = array;

  if (category) {
    filterdArray = filterdArray.filter(
      clothes => clothes.category === category,
    );
  }
  if (color) {
    filterdArray = filterdArray.filter(clothes => clothes.color === color);
  }

  return filterdArray.map(clothes => {
    const categoryIcon = getCategoryIcon(clothes.category);
    const index = lookData.findIndex(i => i.src === clothes.clothes_image);
    console.log(index);

    return (
      <div className="Clothes" key={clothes.clothes_id}>
        {index >= 0 && (
          <img src={CheckIcon} alt="CheckIcon" className="CheckIcon" />
        )}
        <ClickImg
          onClick={onClick}
          src={clothes.clothes_image}
          alt={String(clothes.mId)}
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
  const closetTable = MakeClothes(category, color, array, onClick, lookData);

  return <div className="ClosetTable">{closetTable}</div>;
};

export default RecommendCloset;
