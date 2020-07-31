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
          <option value="pants">Pants</option>
          <option value="jean">Jean</option>
          <option value="shirts">Shirts</option>
          <option value="tee">Tee</option>
          <option value="dress">Dress</option>
          <option value="shoes">Shoes</option>
          <option value="bag">Bag</option>
          <option value="hat">Hat</option>
          <option value="accessory">Accessory</option>
        </select>
        <select name="color" className="selectBox" onChange={changeColor}>
          <option value="">Color</option>
          <option value="red">Red</option>
          <option value="blue">Blue</option>
          <option value="green">Green</option>
          <option value="brown">Brown</option>
          <option value="yellow">Yellow</option>
          <option value="purple">Purple</option>
        </select>
      </div>
      <button onClick={chooseFilter} type="submit" className="NavButton">
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
