import React from 'react';

const LookItem = ({match}) => {
  console.log("LookItem");
  console.log(match);
  return (
    <>
      <p>LookItem</p>
      {match.params.lookid}
    </>
  );
}

export default LookItem;