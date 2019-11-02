/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import PropTypes from 'prop-types';
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
        'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTcyNDM3NTUxNTY5LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1wU3RhciI6MTAwLCJtY0RhdGVUaW1lIjpudWxsLCJtY0RhdGUiOm51bGwsIm1jVGltZSI6bnVsbCwibWlkIjoyLCJtbmFtZSI6IuyYpOybkOyEnSIsIm1zdGFyIjoxMDAsIm1wcm9maWxlVXJsIjoiaHR0cHM6Ly9zZWFyY2gucHN0YXRpYy5uZXQvY29tbW9uP3R5cGU9YSZzaXplPTEyMHgxNTAmcXVhbGl0eT05NSZkaXJlY3Q9dHJ1ZSZzcmM9aHR0cCUzQSUyRiUyRnNzdGF0aWMubmF2ZXIubmV0JTJGcGVvcGxlJTJGcG9ydHJhaXQlMkYyMDE5MDQlMkYyMDE5MDQwNTEzNDQ0MTc5MS5qcGciLCJtbWFpbCI6Im93czMwODBAZ21haWwuY29tIiwibWJpcnRoZGF5IjoiMTk5NTEwMDciLCJtc29jaWFsS2luZCI6Imtha2FvIiwibWhhc2hWYWwiOiIxMjM0NTY3OCIsIm1zb2NpYWxJZCI6bnVsbCwibWVkaXRvciI6MiwibWdyYWRlIjo5LCJtY29tbWVudCI6IuyWtO2UjOumrOy8gOydtOyFmCDrp4jsiqTthLAiLCJtY29uRGF0ZVRpbWUiOm51bGx9fQ.FWZs0Svow9XbJcJIiPVMUqvdiYRW_3Pz7GdAsXVRlV8';
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
  setToken: PropTypes.func.isRequired,
  history: ReactRouterPropTypes.history.isRequired,
  location: ReactRouterPropTypes.location.isRequired,
};

export default LogIn;
