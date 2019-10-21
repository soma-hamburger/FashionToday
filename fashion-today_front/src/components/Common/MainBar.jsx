import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { ClickDiv, ClickImg, LinkImg, LinkDiv } from './Components';
import AlarmIcon from '../../img/alarm_icon.png';
import ProfileIcon from '../../img/profile_icon.png';
import StarIcon from '../../img/star_icon.png';
import MainLogo from '../../img/logo/logo-width-white.png';

const logout = () => {
  localStorage.removeItem('token');
  window.location.replace('/');
};

const ProfilePopUp = ({ userInfo }) => (
  <div className="PopUpProfile">
    <div className="ProfileBody">
      <div className="StarNum">
        <img src={StarIcon} className="StarIcon" alt="StarIcon" />
        <div className="StarNumber">{userInfo.star}</div>
      </div>
      <LinkDiv to="/mypage" className="MyPageLink">
        <>My Page</>
      </LinkDiv>
      <ClickDiv onClick={logout} className="LogOutLink">
        <>Log Out</>
      </ClickDiv>
    </div>
  </div>
);

const AlarmPopUp = () => (
  <div className="PopUpAlarm">
    <div className="AlarmBody">Alarm</div>
  </div>
);

const MainBar = ({ userInfo }) => {
  const [PopUp, setPopUp] = useState('none');

  const PopUpClose = () => {
    setPopUp('none');
  };

  const AlarmOpen = e => {
    e.stopPropagation();
    if (PopUp === 'alarm') setPopUp('none');
    else setPopUp('alarm');
  };

  const ProfileOpen = e => {
    e.stopPropagation();
    if (PopUp === 'profile') setPopUp('none');
    else setPopUp('profile');
  };

  return (
    <ClickDiv onClick={PopUpClose} className="MainBar">
      <div className="flexButtons">
        <div className="ghost">ghost</div>
        <LinkImg className="MainLogo" to="/" src={MainLogo} />
        <div className="pageLink">
          <Link to="/calendar">Calendar</Link>
          <Link to="/closet">Closet</Link>
          <Link to="/recommend">Recommend</Link>
        </div>
        <div className="Icons">
          <div className="dropdown">
            <ClickImg
              onClick={AlarmOpen}
              className="AlarmIcon"
              alt="alarm"
              src={AlarmIcon}
            />
            {PopUp === 'alarm' && <AlarmPopUp />}
          </div>
          <div className="dropdown">
            <ClickImg
              onClick={ProfileOpen}
              className="ProfileIcon"
              alt="profile"
              src={ProfileIcon}
            />
            {PopUp === 'profile' && <ProfilePopUp userInfo={userInfo} />}
          </div>
        </div>
        <div className="ghost">ghost</div>
      </div>
    </ClickDiv>
  );
};

export default MainBar;
