import React from 'react';
import { SLink } from '../../styled';

const MyPageMain = ({ match }) => (
  <>
    My Page Main
    <SLink small="true" color="gray" to={`${match.url}/settings`}>Settings</SLink>
  </>
);

export default MyPageMain;
