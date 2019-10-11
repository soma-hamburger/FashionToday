import React from 'react';
import { UserRequest } from '../Tool';

const Main = () => {
  const getUserInfo = () => UserRequest('userInfo');

  console.log(getUserInfo);
  return (
    <>
      <b>main</b>
    </>
  );
};

export default Main;
