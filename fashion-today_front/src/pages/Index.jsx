import React from 'react';
import KakaoLogin from '../img/kakao_login_btn/kr/kakao_account_login_btn_medium_wide.png';

const Index = () => (
  <>
    <h1>LogIn</h1>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=ac9e9659b36eef40d08466896116a84a&redirect_uri=http://localhost:3000/login&response_type=code">
      <img alt="kakao_login" src={KakaoLogin} />
    </a>
  </>
);

export default Index;
