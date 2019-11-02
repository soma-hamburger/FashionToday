import React from 'react';
import { ClickImg } from '../Common/Components';
import bagIcon from '../../img/category/bag_icon.png';
import jeanIcon from '../../img/category/jean_icon.png';
import hatIcon from '../../img/category/hat_icon.png';
import dressIcon from '../../img/category/dress_icon.png';
import shoesIcon from '../../img/category/shoes_icon.png';
import shirtsIcon from '../../img/category/shirts_icon.png';
import teeIcon from '../../img/category/tee_icon.png';
import accesoryIcon from '../../img/category/accesory_icon.png';

export const getCategoryIcon = category => {
  let src;
  if (category === 'jean') src = jeanIcon;
  if (category === 'bag') src = bagIcon;
  if (category === 'hat') src = hatIcon;
  if (category === 'dress') src = dressIcon;
  if (category === 'shirts') src = shirtsIcon;
  if (category === 'tee') src = teeIcon;
  if (category === 'accesory') src = accesoryIcon;
  if (category === 'shoes') src = shoesIcon;

  if (!src) return null;
  return (
    <div className="categoryIcon">
      <img src={src} alt="categoryIcon" />
    </div>
  );
};

const MakeClothes = (category, color, array, onClick) => {
  let filterdArray = array;

  if (category) {
    filterdArray = filterdArray.filter(
      clothes => clothes.category === category,
    );
  }
  if (color) {
    filterdArray = filterdArray.filter(clothes => clothes.color === color);
  }

  if (!filterdArray) {
    return null;
  }
  return filterdArray.map(clothes => {
    const categoryIcon = getCategoryIcon(clothes.category);
    return (
      <div className="Clothes" key={clothes.clothes_id}>
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

const ClosetTable = ({ category, color, array, onClick = () => {} }) => {
  const closetTable = MakeClothes(category, color, array, onClick);

  return <div className="ClosetTable">{closetTable}</div>;
};

export default ClosetTable;
