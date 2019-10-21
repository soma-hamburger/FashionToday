import axios from 'axios';
import { useState, useEffect, useRef } from 'react';
import {
  UserCloset,
  UserInfo,
  LookRequestorList,
  RequestorCloset,
  getDailyLookList,
  LookListInfo,
} from './defaultAPI';

const findDefaultAPI = url => {
  if (url === 'closet') return { data: UserCloset };
  if (url === 'user/info') return { data: UserInfo };
  if (url === 'user/dailylook') return { data: getDailyLookList };
  if (url === 'requestor/list') return { data: LookRequestorList };
  if (url === 'requestor/closet') return { data: RequestorCloset };
  if (url === 'look') return { data: LookListInfo };
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

export const UserPost = async (url, token, body) => {
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

export const UserGet = async (url, token, body) => {
  const userInstance = axios.create({
    baseURL: 'https://api.pashiontoday.com/',
    timeout: 2000,
    headers: { Authorization: token },
  });

  try {
    return await userInstance.get(url, body);
  } catch (error) {
    console.log(error);
    return findDefaultAPI(url);
  }
};

export const useFetch = (method, url, token, body) => {
  const [res, setRes] = useState(null);

  useEffect(() => {
    const postRes = async () => {
      const response = await UserPost(url, token, body);
      setRes(response);
    };
    const getRes = async () => {
      const response = await UserGet(url, token, body);
      setRes(response);
    };
    if (method === 'post') {
      postRes();
    } else {
      getRes();
    }
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
