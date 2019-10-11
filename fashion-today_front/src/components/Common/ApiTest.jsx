import React, { useContext } from 'react';
// import axios from 'axios';
import { UserRequest } from '../../Tool';
import { LoginContext } from '../../Context';

const get200 = async token => {
  console.log(token);
  const res = await UserRequest('userInfo', token, { body: 'body' });
  console.log(res);
};

const ApiTest = () => {
  const { token } = useContext(LoginContext);

  return (
    <>
      <button type="button" className="api" onClick={() => get200(token)}>
        200
      </button>
    </>
  );
};

export default ApiTest;
