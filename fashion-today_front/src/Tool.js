import axios from 'axios';
import { useState, useEffect, useRef } from 'react';
import {
  UserCloset,
  UserInfo,
  LookRequestorList,
  RequestorCloset,
  getDailyLookList,
  LookListInfo,
  UserScheduleDetail,
  UserScheduleList,
  DailyLookDetail,
  LookDetail,
} from './defaultAPI';

const findDefaultAPI = url => {
  if (url === 'closet') return { data: UserCloset };
  if (url === 'user/info') return { data: UserInfo };
  if (url === 'dailylooklist') return { data: getDailyLookList };
  if (url === 'user/schedule/list') return { data: UserScheduleList };
  if (url === 'user/schedule/detail') return { data: UserScheduleDetail };
  if (url === 'requestor/list') return { data: LookRequestorList };
  if (url === 'requestor/closet') return { data: RequestorCloset };
  if (url === 'looklist') return { data: LookListInfo };
  if (url === 'dailylook') return { data: DailyLookDetail };
  if (url === 'look') return { data: LookDetail };
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
  const input = [url, token];

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
  }, input);

  return res;
};

export const useWeahterAPI = date => {
  const [res, setRes] = useState(null);

  const key =
    'g9j0VQ1w%2B2FL2%2BirUmwnrqHSJa8Z5NlLn9pwnVa4MAdiy13rX2kf5WPbcWLKDN9S7F4Is5ht9eJKcAniXhZGjw%3D%3D';

  const userInstance = axios.create({
    baseURL: `http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?ServiceKey=${key}&base_date=${date}&base_time=0200&nx=59&ny=125&_type=json`,
    timeout: 2000,
  });

  useEffect(() => {
    const getRes = async () => {
      try {
        const response = await userInstance.get();
        setRes(response);
      } catch (error) {
        console.log(error);
      }
    };

    getRes();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [userInstance]);

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

export const useEventListener = (eventName, handler, element = window) => {
  const savedHandler = useRef();
  useEffect(() => {
    savedHandler.current = handler;
  }, [handler]);

  useEffect(() => {
    const isSupported = element && element.addEventListener;
    if (!isSupported) return;
    const eventListener = event => savedHandler.current(event);

    element.addEventListener(eventName, eventListener);
    // eslint-disable-next-line consistent-return
    return () => element.removeEventListener(eventName, eventListener);
  }, [eventName, element]);
};

export const makeDayId = dateObj => {
  const mm = dateObj.getMonth() + 1; // getMonth() is zero-based
  const dd = dateObj.getDate();

  return [
    dateObj.getFullYear(),
    (mm > 9 ? '' : '0') + mm,
    (dd > 9 ? '' : '0') + dd,
  ].join('');
};

export const makeDayObj = dayId => {
  const year = dayId.substring(0, 4);
  const month = dayId.substring(4, 6);
  const date = dayId.substring(6, 8);

  return new Date(year, month - 1, date);
};
