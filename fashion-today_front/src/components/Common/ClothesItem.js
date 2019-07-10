import React from 'react';
import { CItem } from '../../styled';

const ClothesItem = ({item}) => {
  return (
    <CItem>
      Clothes Item : {item.id} {item.color}
    </CItem>
  );
}

export default ClothesItem;