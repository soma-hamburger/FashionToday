import React from 'react';
import { LWindow } from '../../styled';

const LookWindow = ({item}) => {
  return (
    <LWindow>
      <p>LookWindow</p>
      {item.key}
    </LWindow>
  );
}

export default LookWindow;