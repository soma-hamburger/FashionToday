import React from 'react';
import {
  Bar, BarInterface, Navigation, NavButton, MainIcon,
} from '../../styled';

const makeButton = (pathname) => {
  const buttons = [{
    'location': '/calendar',
    'name': 'Calendar',
  },
  {
    'location': '/closet',
    'name': 'Closet',
  },
  {
    'location': '/recommend',
    'name': 'Recomend',
  }];

  return buttons.map((info, index) => {
    if (pathname.indexOf(info.location) === 0) {
      return (<NavButton to={info.location} key={index} iscurrent="true">{info.name}</NavButton>);
    }
    return (<NavButton to={info.location} key={index}>{info.name}</NavButton>);
  });
};

const MainBar = ({ location }) => {
  const buttons = makeButton(location.pathname);

  return (
    <Bar>
      <BarInterface>
        <MainIcon to="/" color="white">오늘의 패션(ICON)</MainIcon>
        <Navigation>
          {buttons}
        </Navigation>
        <NavButton to="/mypage" linenum={2}>My Page <br />or Login</NavButton>
      </BarInterface>
    </Bar>
  );
};

export default MainBar;
