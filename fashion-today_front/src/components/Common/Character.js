import React from 'react';
import { SLink } from '../../styled';

const Character = ({item, match}) => {
  return (
    <>
      <SLink to={`${match.url}/${item.top.id}`} color={item.top.color}>{item.top.id}</SLink>
      <SLink to={`${match.url}/${item.bottom.id}`} color={item.bottom.color}>{item.bottom.id}</SLink>
    </>
  )
}

export default Character;