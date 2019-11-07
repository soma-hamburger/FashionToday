import React from 'react';
import { LinkDiv } from '../components/Common/Components';
import Logo from '../img/logo/logo-height.png';
import '../style/Login.scss';
import KakaoIcon from '../img/kakao_icon.png';
import NaverIcon from '../img/naver_icon.png';

const Index = () => (
  <div className="Index">
    <div className="TitleView">
      <div className="Title">
        PASHION
        <br />
        TODAY
      </div>
      <div className="Subtitle">Daily Fashion Platform</div>
      <div className="Describe">
        당신의 옷장 속 옷들로 데일리룩을 코디해드립니다.
        <br />
        매일매일 특별한 하루를 맞이하세요.
      </div>
    </div>
    <div className="Interface">
      <img src={Logo} className="Logo" alt="Logo" />
      <LinkDiv
        href={`https://kauth.kakao.com/oauth/authorize?client_id=ac9e9659b36eef40d08466896116a84a&redirect_uri=${window.location.href}login&response_type=code`}
        className="KakaoLogin"
      >
        <>
          <img alt="kakaoicon" src={KakaoIcon} className="icon" />
          카카오 계정으로 로그인
        </>
      </LinkDiv>
      <LinkDiv
        href="https://kauth.kakao.com/oauth/authorize?client_id=ac9e9659b36eef40d08466896116a84a&redirect_uri=http://localhost:3000/login&response_type=code"
        className="NaverLogin"
      >
        <>
          <img alt="navericon" src={NaverIcon} className="icon" />
          네이버 계정으로 로그인
        </>
      </LinkDiv>
    </div>
  </div>
);

export default Index;
