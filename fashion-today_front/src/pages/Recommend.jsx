import React from 'react';
import { SLink } from '../styled';

const Recommend = ({ match }) => {
  return (
    <>
      <SLink to={match.url}>Recommend</SLink>
    </>
  );
};

export default Recommend;
