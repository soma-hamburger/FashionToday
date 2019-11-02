import React, { useContext } from 'react';
import { UserContext } from '../Context';
import ProfileIcon from '../img/default_profile.png';
import '../style/MyPage.scss';

const MyPage = () => {
  const userContext = useContext(UserContext);
  console.log(userContext.UserInfo);

  return (
    <div className="MyPage">
      {userContext.UserInfo && (
        <div className="ProfileInterface">
          <img
            src={
              userContext.UserInfo.profile_image
                ? userContext.UserInfo.profile_image
                : ProfileIcon
            }
            alt="ProfileIcon"
          />
          <div className="Name">{userContext.UserInfo.name} 님</div>
        </div>
      )}
    </div>
  );
};

export default MyPage;
