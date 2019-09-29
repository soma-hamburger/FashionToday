/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */

import React from 'react';

const LogOn = () => (
  <>
    <h1>LogIn</h1>

    <a href="https://kauth.kakao.com/oauth/authorize?client_id=ac9e9659b36eef40d08466896116a84a&redirect_uri=http://localhost:3000/login&response_type=code">
      kakao
    </a>
  </>
);

export default LogOn;
