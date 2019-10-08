/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useContext, useState } from 'react';
import axios from 'axios';
import ReactRouterPropTypes from 'react-router-prop-types';
import { LoginContext } from '../../Context';

const getCode = search => new URLSearchParams(search).get('code');

const LoginRequest = async code => {
  try {
    return await axios.post('https://api.pashiontoday.com/login/kakaotest', {
      code,
    });
  } catch (error) {
    console.log(error);
    return 'temp_token';
    // return false;
  }
};

const LogIn = ({ history, location }) => {
  const [loginState, setLoginState] = useState('Logging...');
  const loginTool = useContext(LoginContext);

  const Logging = async () => {
    const code = getCode(location.search);
    if (!code) return setLoginState('Login Fail');

    const res = await LoginRequest(code);
    console.log(res);

    if (res) {
      localStorage.setItem('token', res);
      localStorage.setItem('is_login', true);
      loginTool.setToken(res);
      return history.push('/');
    }
    return setLoginState('Login Fail');
  };

  if (loginState === 'Logging...') Logging();

  return <>{loginState}</>;
};

LogIn.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
  location: ReactRouterPropTypes.location.isRequired,
};

export default LogIn;
