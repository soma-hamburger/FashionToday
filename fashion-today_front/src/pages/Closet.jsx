import React, { useContext, useState } from 'react';
// import ReactRouterPropTypes from 'react-router-prop-types';
import { useFetch } from '../Tool';
import { UserContext } from '../Context';
import ClosetTable from '../components/Closet/ClosetTable';
import ClosetNavigation from '../components/Closet/ClosetNavigation';
import '../style/Closet.scss';
import ClosetBackground from '../img/closet_background.jpg';

const Closet = () => {
  const { token } = useContext(UserContext);
  const UserCloset = useFetch('get', 'closet', token);

  const [color, setColor] = useState(null);
  const [category, setCategory] = useState(null);

  const navTool = {
    setColor,
    setCategory,
  };

  return (
    <div className="Closet">
      <img
        alt="ClosetBackground"
        src={ClosetBackground}
        className="ClosetBackground"
      />
      <div className="TitleBar">
        MY CLOSET
        <span className="describe">보유한 옷 리스트</span>
      </div>
      <ClosetNavigation navTool={navTool} />
      {UserCloset && (
        <ClosetTable
          category={category}
          color={color}
          array={UserCloset.data.clothes_array}
        />
      )}
    </div>
  );
};
export default Closet;
