import React, { useContext } from 'react';
import { UserContext } from '../Context';
import ProfileIcon from '../img/default_profile.png';
import '../style/MyPage.scss';

const MyPage = () => {
  const { UserInfo } = useContext(UserContext);

  return (
    <div className="MyPage">
      {UserInfo && (
        <div className="ProfileInterface">
          <img
            src={UserInfo.profile_image ? UserInfo.profile_image : ProfileIcon}
            alt="ProfileIcon"
          />
          <div className="Name">{UserInfo.name} 님</div>
        </div>
      )}
    </div>
  );
};

export default MyPage;
