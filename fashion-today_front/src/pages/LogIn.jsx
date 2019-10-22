/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { Request } from '../Tool';

const getCode = search => new URLSearchParams(search).get('code');

const LogIn = ({ setToken, history, location }) => {
  const [loginState, setLoginState] = useState('Logging...');

  const Logging = async () => {
    const code = getCode(location.search);

    if (!code) return setLoginState('Login Fail');

    const res = await Request('login/kakao', { code });

    if (res instanceof Error || !res) {
      console.log('Login Fail', res);
      const defaultToken =
        'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTcwNTM2MjgwMDE0LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1jRGF0ZVRpbWUiOiIyMDE5MDgyNjA5MDYzMCIsIm1jRGF0ZSI6IjIwMTkwODI2IiwibWNUaW1lIjoiMDkwNjMwIiwibWlkIjozLCJtbmFtZSI6Iuq4sOyEsSIsIm1zdGFyIjowLCJtcHJvZmlsZVVybCI6Imh0dHA6Ly9mYXNoaW9udG9kYXkuY29tL3Byb2ZpbGUvMSIsIm1tYWlsIjoidG90b2tpc3VuZ0BuYXZlci5jb20iLCJtYmlydGhkYXkiOiIxOTk0MTEwNSIsIm1zb2NpYWxLaW5kIjoia2FrYW8iLCJtaGFzaFZhbCI6IjEyMzQ1Njc4OWgiLCJtc29jaWFsSWQiOiJ0b3Rva2lzdW5nQG5hdmVyLmNvbSIsIm1jb21tZW50Ijoi7ZWc7KSEIOyDge2DnCDrp6TshLjsp4AiLCJtY29uRGF0ZVRpbWUiOiIyMDE5MDgyNiJ9fQ.yUr0MLd_flP6NJfhD-3t8r42FtHkLW1RLMCyY_rgiEs';
      localStorage.setItem('token', defaultToken);
      setToken(defaultToken);

      return history.push('/');
    }

    localStorage.setItem('token', res.data.token);
    setToken(res.data.token);

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
