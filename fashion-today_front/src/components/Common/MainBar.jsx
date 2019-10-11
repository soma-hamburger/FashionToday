import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { ClickDiv, ClickImg } from './Components';

const ProfilePopUp = (
  <div className="PopUpProfile">
    Profile
    <br />
    <Link to="/mypage">mypage</Link>
  </div>
);

const AlarmPopUp = <div className="PopUpAlarm">Alarm</div>;

const MainBar = () => {
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
        <Link className="MainLogo" to="/">
          Main
        </Link>
        <div className="pageLink">
          <Link to="/calendar">Calendar</Link>
          <Link to="/closet">Closet</Link>
          <Link to="/recommend">Recommend</Link>
        </div>
        <div className="Icons">
          <div className="dropdown">
            <ClickImg
              onClick={AlarmOpen}
              className="icon"
              alt="alarm"
              src="https://cdn0.iconfinder.com/data/icons/mintab-outline-for-ios-4/30/alarm-clock-timer-time-ring-512.png"
            />
            {PopUp === 'alarm' && AlarmPopUp}
          </div>
          <div className="dropdown">
            <ClickImg
              onClick={ProfileOpen}
              className="icon"
              alt="profile"
              src="https://cdn2.iconfinder.com/data/icons/business-management-52/96/Artboard_20-512.png"
            />
            {PopUp === 'profile' && ProfilePopUp}
          </div>
        </div>
      </div>
    </ClickDiv>
  );
};

export default MainBar;
