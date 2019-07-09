import React from 'react';
import { A, LItem } from '../../styled';

const LookItem = ({item}) => {
  return (
    <LItem>
      <A to={`looks/${item.id}`}>
        {item.user}의 {item.weather && `${item.weather} 날씨에 어울리는`} 룩
      </A><br/>
      {item.id}<br/>
      {item.top.color}
    </LItem>
  );
}

export default LookItem;