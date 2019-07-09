import React from 'react';
import { CItem } from '../../styled';

const ClothesItem = ({match}) => {
  return (
    <CItem>
      Clothes Item : {match.params.clothesid}
    </CItem>
  );
}

export default ClothesItem;