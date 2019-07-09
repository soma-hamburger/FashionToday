import React from 'react';
import {Link} from 'react-router-dom';
import { SLink } from '../../styled';

const ClothesList = () => {
  const ClothesArray = [{"key": 1},{"key": 2},{"key": 3}];

  const ClothesView = ClothesArray.map((item, index)=>(
    <Link to={`/closet/item/${item.key}`} key={index}><p>{item.key}</p></Link>
  ));
  return (
    <>
      ClothesList
      {ClothesView}
      <SLink small="true" color="gray" to ={`/closet/add`}>Add Clothes</SLink>
    </>
  );
}

export default ClothesList;