import React, { useContext } from 'react';
import { useFetch } from '../Tool';
import { LoginContext } from '../Context';

const Main = () => {
  const loginTool = useContext(LoginContext);

  const UserInfo = useFetch('userInfo', loginTool.token);

  console.log(UserInfo);
  return (
    <>
      <b>main</b>
      <div className="scrolltest">sdlifjsdlf</div>
    </>
  );
};

export default Main;
