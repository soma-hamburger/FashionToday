import React from 'react';
import { Class } from '../../styled';

const Character = ({item, setClothesItem, isSet}) => {
  const setClothes = (e) => {
    if(isSet) setClothesItem(item.look[e.target.getAttribute("id")]);
  }

  const ClothesView = item.look.map((clothes, index)=>
    <Class id={index} color={clothes.color} onClick={isSet ? setClothes : null} key={index} small={!isSet}>
      {clothes.class} : {clothes.id}
    </Class>
  )

  return (
    <>
      {ClothesView}
    </>
  )
}

export default Character;