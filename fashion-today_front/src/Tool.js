import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://api.pashiontoday.com/',
  timeout: 2000,
});

export const Request = async (url, body, header) => {
  try {
    return await instance.post(url, body, header);
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
    return error;
  }
};
