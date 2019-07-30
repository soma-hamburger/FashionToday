import React from 'react';
import { CItem } from '../../styled';

const ClothesItem = ({item}) => {
  return (
    <CItem color={item.color}>
      Clothes Item : {item.id} {item.color}<br/>
      {item.type}
    </CItem>
  );
}

export default ClothesItem;