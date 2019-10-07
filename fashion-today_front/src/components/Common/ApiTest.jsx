/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import axios from 'axios';
// import axios from 'axios';

const get200 = async () => {
  const res = await axios.post('https://api.pashiontoday.com/userInfo');
  console.log(res);
};

const ApiTest = props => {
  console.log(props);

  return (
    <button type="button" className="api" onClick={get200}>
      200
    </button>
  );
};

export default ApiTest;
