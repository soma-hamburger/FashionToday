import React, { useContext, useState } from 'react';
// import ReactRouterPropTypes from 'react-router-prop-types';
import { useFetch } from '../Tool';
import { LoginContext } from '../Context';
import ClosetTable from '../components/Closet/ClosetTable';
import ClosetNavigation from '../components/Closet/ClosetNavigation';
import '../style/Closet.scss';

const Closet = () => {
  const loginTool = useContext(LoginContext);
  const UserCloset = useFetch('closet', loginTool.token);
  const [color, setColor] = useState(null);
  const [category, setCategory] = useState(null);

  console.log(UserCloset);

  const navTool = {
    setColor,
    setCategory,
  };

  return (
    <div className="Closet">
      MY CLOSET
      <ClosetNavigation navTool={navTool} />
      {UserCloset && (
        <ClosetTable
          category={category}
          color={color}
          array={UserCloset.clothes_array}
        />
      )}
    </div>
  );
};
export default Closet;
