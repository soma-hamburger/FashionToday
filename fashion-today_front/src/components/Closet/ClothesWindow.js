import React from 'react';

const ClothesWindow = ({match}) => {
  return (
    <>
      Clothes Item : {match.params.clothesid}
    </>
  );
}

export default ClothesWindow;