import React from 'react';
import {Link} from 'react-router-dom';

const DailyLook = () => {
  const ClothesArray = [{"key": 1},{"key": 2},{"key": 3}];

  const ClothesView = ClothesArray.map((item, index)=>(
    <Link to={`/daily/${item.key}`} key={index}><p>{item.key}</p></Link>
  ));
  
  return (
    <>
      Daily Look
      {ClothesView}
    </>
  );
}

export default DailyLook;