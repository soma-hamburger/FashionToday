import React from 'react';
import { SLink, Bar } from '../../styled';
import styled from 'styled-components';

const MainButton = styled(SLink)`
  width: 120px;
  float: left;
`
const MainBar = () => {
  return (
    <Bar>
      <MainButton to = "/" color="white" >오늘의 패션</MainButton>
    </Bar>
  );
}

export default MainBar;