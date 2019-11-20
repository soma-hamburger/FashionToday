import React, { useContext } from 'react';
// import axios from 'axios';
import { UserPost } from '../../Tool';
import { UserContext } from '../../Context';

const get200 = async token => {
  const res = await UserPost('userInfo', token, { body: 'body' });
  console.log(res);
};

const ApiTest = () => {
  const { token } = useContext(UserContext);

  return (
    <>
      <button type="button" className="api" onClick={() => get200(token)}>
        200
      </button>
    </>
  );
};

export default ApiTest;
