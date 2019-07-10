import React from 'react';
import { Button } from '../../styled';

const Character = ({item, setClothesItem}) => {

  const setTop = () => setClothesItem(item.top);
  const setBottom = () => setClothesItem(item.bottom);

  return (
    <>
      <Button color={item.top.color} onClick={setTop}>top: {item.top.id}</Button>
      <Button color={item.bottom.color} onClick={setBottom}>bottom: {item.bottom.id}</Button>
    </>
  )
}

export default Character;