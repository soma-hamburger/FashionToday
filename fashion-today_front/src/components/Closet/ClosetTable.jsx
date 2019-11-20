import React, { useMemo } from 'react';
import PropTypes from 'prop-types';
import { ClickImg, getCategoryIcon } from '../Common/Components';
import { filteringArray } from '../../Tool';

const MakeClothes = filterdArray => {
  if (!filterdArray) {
    return null;
  }
  return filterdArray.map(clothes => {
    const categoryIcon = getCategoryIcon(clothes.category);
    return (
      <div className="Clothes" key={clothes.clothes_id}>
        <ClickImg
          onClick={() => {}}
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

const ClosetTable = ({ category, color, array }) => {
  const closetTable = useMemo(() => {
    const filterdArray = filteringArray(category, color, array);
    return MakeClothes(filterdArray);
  }, [array, category, color]);

  return <div className="ClosetTable">{closetTable}</div>;
};

ClosetTable.propTypes = {
  category: PropTypes.string.isRequired,
  color: PropTypes.string.isRequired,
  array: PropTypes.arrayOf(
    PropTypes.shape({
      category: PropTypes.string.isRequired,
      color: PropTypes.string.isRequired,
      clothes_id: PropTypes.number.isRequired,
      clothes_image: PropTypes.string.isRequired,
    }),
  ).isRequired,
};

export default ClosetTable;
