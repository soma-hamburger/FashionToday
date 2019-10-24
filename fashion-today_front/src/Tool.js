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
} from './defaultAPI';

const findDefaultAPI = url => {
  if (url === 'closet') return { data: UserCloset };
  if (url === 'user/info') return { data: UserInfo };
  if (url === 'user/dailylook') return { data: getDailyLookList };
  if (url === 'user/schedule/list') return { data: UserScheduleList };
  if (url === 'user/schedule/detail') return { data: UserScheduleDetail };
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

export const useEventListener = (eventName, handler, element = window) => {
  // Create a ref that stores handler
  const savedHandler = useRef();

  // Update ref.current value if handler changes.
  // This allows our effect below to always get latest handler ...
  // ... without us needing to pass it in effect deps array ...
  // ... and potentially cause effect to re-run every render.
  useEffect(() => {
    savedHandler.current = handler;
  }, [handler]);

  useEffect(
    () => {
      // Make sure element supports addEventListener
      // On
      const isSupported = element && element.addEventListener;
      if (!isSupported) return;

      // Create event listener that calls handler function stored in ref
      const eventListener = event => savedHandler.current(event);

      // Add event listener
      element.addEventListener(eventName, eventListener);

      // Remove event listener on cleanup
      return () => {
        element.removeEventListener(eventName, eventListener);
      };
    },
    [eventName, element], // Re-run if eventName or element changes
  );
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
