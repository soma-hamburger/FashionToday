/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useContext, useState } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { LoginContext } from '../Context';
import { Request } from '../Tool';

const getCode = search => new URLSearchParams(search).get('code');

const LogIn = ({ history, location }) => {
  const [loginState, setLoginState] = useState('Logging...');
  const loginTool = useContext(LoginContext);

  const Logging = async () => {
    const code = getCode(location.search);

    if (!code) return setLoginState('Login Fail');

    const res = await Request('login/kakaotest', { code });

    console.log(res);

    if (res instanceof Error || !res) {
      return setLoginState('Login Fail');
    }

    localStorage.setItem('token', res.data.token);
    loginTool.setToken(res.data.token);

    return history.push('/');
  };

  if (loginState === 'Logging...') Logging();

  return <>{loginState}</>;
};

LogIn.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
  location: ReactRouterPropTypes.location.isRequired,
};

export default LogIn;
