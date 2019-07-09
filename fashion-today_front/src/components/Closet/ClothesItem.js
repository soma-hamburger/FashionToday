import React from 'react';

const ClothesItem = ({match}) => {
  console.log("ClothesItem");
  console.log(match);
  return (
    <>
      Clothes Item : {match.params.clothesid}
    </>
  );
}

export default ClothesItem;