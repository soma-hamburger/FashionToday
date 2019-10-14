import axios from 'axios';
import { useState, useEffect, useRef } from 'react';
import {
  UserCloset,
  UserInfo,
  LookRequestorList,
  RequestorCloset,
} from './defaultAPI';

const findDefaultAPI = url => {
  if (url === 'closet') return UserCloset;
  if (url === 'userInfo') return UserInfo;
  if (url === 'requestor/list') return LookRequestorList;
  if (url === 'requestor/closet') return RequestorCloset;
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

export const useFetch = (url, token, body) => {
  const [res, setRes] = useState(null);

  useEffect(() => {
    const getRes = async () => {
      const response = await UserRequest(url, token, body);
      setRes(response);
    };
    getRes();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [url, token]);

  return res;
};

export const useCanvas = (draw, context = '2d') => {
  const canvasRef = useRef(null);

  useEffect(() => {
    if (canvasRef.current) {
      const ctx = canvasRef.current.getContext(context);
      console.log(ctx);
      draw(ctx);
    }
  });

  return canvasRef;
};
