import React from 'react';

const Day = ({match}) => {
  return (
    <>
      Day : {match.params.dayid}
    </>
  );
}

export default Day;