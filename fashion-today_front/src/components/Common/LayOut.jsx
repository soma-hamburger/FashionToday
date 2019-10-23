import React, { useState, useEffect } from 'react';
import MainBar from './MainBar';
import { UserContext } from '../../Context';
import { useFetch } from '../../Tool';
import '../../style/Layout.scss';

const LayOut = ({ children, token }) => {
  const [userContext, setUserContext] = useState({
    token,
    UserInfo: null,
  });

  const UserInfo = useFetch('post', 'user/info', token);

  useEffect(() => {
    if (UserInfo) {
      setUserContext({
        token,
        UserInfo: UserInfo.data,
      });
    }
  }, [UserInfo, token]);

  return (
    <UserContext.Provider value={userContext}>
      <div className="LayOut">
        {UserInfo && <MainBar userInfo={UserInfo.data} />}
        <div className="children">{children}</div>
        <div className="Footer">.</div>
      </div>
    </UserContext.Provider>
  );
};

export default LayOut;
