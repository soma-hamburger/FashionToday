import React from 'react';
import { ClickImg } from '../Common/Components';

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
  console.log('render');
  return filterdArray.map(clothes => (
    <div className="Clothes" key={clothes.clothes_id}>
      <ClickImg
        onClick={onClick}
        src={clothes.clothes_image}
        alt={String(clothes.mId)}
        className="Image"
      />
      <div className="Account">
        {clothes.category},{clothes.color}
      </div>
    </div>
  ));
};

const ClosetTable = ({ category, color, array, onClick = () => {} }) => {
  const closetTable = MakeClothes(category, color, array, onClick);

  return <div className="ClosetTable">{closetTable}</div>;
};

export default ClosetTable;
