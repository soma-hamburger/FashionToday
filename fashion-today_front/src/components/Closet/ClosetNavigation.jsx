import React, { useState } from 'react';
import PropTypes from 'prop-types';

const ClosetNavigation = ({ navTool }) => {
  const [recentCategory, setRecentCategory] = useState(null);
  const [recentColor, setRecentColor] = useState(null);

  const changeCategory = e => {
    if (e.target.value === '') setRecentCategory(null);
    else setRecentCategory(e.target.value);
  };

  const changeColor = e => {
    if (e.target.value === '') setRecentColor(null);
    else setRecentColor(e.target.value);
  };

  const chooseFilter = () => {
    navTool.setCategory(recentCategory);
    navTool.setColor(recentColor);
  };

  return (
    <div className="ClosetNavigation">
      <div>
        <select name="category" className="selectBox" onChange={changeCategory}>
          <option value="">Category</option>
          <option value="jean">Jean</option>
          <option value="tee">Tee</option>
          <option value="shirts">Shirts</option>
        </select>
        <select name="color" className="selectBox" onChange={changeColor}>
          <option value="">Color</option>
          <option value="red">Red</option>
          <option value="blue">Blue</option>
          <option value="green">Green</option>
        </select>
      </div>
      <button onClick={chooseFilter} type="submit">
        보기
      </button>
    </div>
  );
};

ClosetNavigation.propTypes = {
  navTool: PropTypes.shape({
    setColor: PropTypes.func.isRequired,
    setCategory: PropTypes.func.isRequired,
  }).isRequired,
};

export default ClosetNavigation;
