import React, { useContext } from 'react';
import { useFetch } from '../Tool';
import { UserContext } from '../Context';
import MainBackground from '../img/main_background.jpg';
import '../style/Main.scss';
import PinWhite from '../img/pin-white.png';
import SlashIcon from '../img/slash_icon.png';
import SeparateLine from '../img/separate_line.png';
import DailyLookList from '../components/Main/DailyLookList';
import LookList from '../components/Main/LookList';

const Main = () => {
  const { token } = useContext(UserContext);

  const DailyLookListInfo = useFetch('post', 'user/dailylook', token);
  const LookListInfo = useFetch('post', 'look', token);

  return (
    <div className="Main">
      <img
        alt="MainBackground"
        src={MainBackground}
        className="MainBackground"
      />
      <div className="Introduce">
        <img alt="Pin White" src={PinWhite} className="PinWhite" />
        <div className="Title">나만의 데일리룩 추천</div>
        <div className="SubTitle">My special dailylook</div>
        <img alt="SlashIcon" src={SlashIcon} className="SlashIcon" />
        <br />
        당신의 옷장 속 잠자고 있는 옷들로 특별한 룩을 스타일링 합니다.
        <br />
        옷을 사도 사도 입을 옷이 없는 고민은 이제 그만!
        <br />
        매일 특별한 하루를 맞이해보세요.
      </div>
      {DailyLookListInfo && (
        <DailyLookList LookArray={DailyLookListInfo.data.daily_look_array} />
      )}
      <img alt="Separate Line" src={SeparateLine} className="SeparateLine" />
      {LookListInfo && <LookList LookArray={LookListInfo.data.look_array} />}
    </div>
  );
};

export default Main;
