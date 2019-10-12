import axios from 'axios';
import { useState, useEffect } from 'react';
import { UserCloset, UserInfo } from './defaultAPI';

const findDefaultAPI = url => {
  if (url === 'closet') return UserCloset;
  if (url === 'userInfo') return UserInfo;
  return null;
};

export const Request = async (url, body, header) => {
  const instance = axios.create({
    baseURL: 'https://api.pashiontoday.com/',
    timeout: 2000,
    headers: header,
  });

  try {
    return await instance.post(url, body);
  } catch (error) {
    return error;
  }
};

export const UserRequest = async (url, token, body) => {
  const userInstance = axios.create({
    baseURL: 'https://api.pashiontoday.com/',
    timeout: 2000,
    headers: { Authorization: token },
  });

  try {
    return await userInstance.post(url, body);
  } catch (error) {
    console.log(error);
    return findDefaultAPI(url);
  }
};

export const useFetch = (url, token) => {
  const [res, setRes] = useState(null);
  useEffect(() => {
    const getRes = async () => {
      const response = await UserRequest(url, token);
      setRes(response);
    };
    getRes();
  }, [url, token]);

  return res;
};
