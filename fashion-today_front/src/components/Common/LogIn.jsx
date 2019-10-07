/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import axios from 'axios';
import ReactRouterPropTypes from 'react-router-prop-types';

const getCode = search => new URLSearchParams(search).get('code');

const Logging = async accessToken => {
  // const params = new URLSearchParams();

  // params.append('code', accessToken);

  const res = await axios.post('https://api.pashiontoday.com/login/kakaotest', {
    code: accessToken,
  });

  console.log(res);
  return res;
};

const LogIn = ({ location }) => {
  const accessToken = getCode(location.search);

  const LetLogging = () => {
    Logging(accessToken);
  };

  return (
    <button type="button" onClick={LetLogging}>
      Login
    </button>
  );
};

LogIn.propTypes = {
  location: ReactRouterPropTypes.location.isRequired,
};

export default LogIn;
