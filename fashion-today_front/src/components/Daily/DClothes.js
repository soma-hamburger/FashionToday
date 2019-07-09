import React from 'react';

const DClothes = ({match}) => {
  console.log("LookItem");
  console.log(match);
  return (
    <>
      <p>Daily Clothes</p>
      
      {match.params.clothesid}
    </>
  );
}

export default DClothes;