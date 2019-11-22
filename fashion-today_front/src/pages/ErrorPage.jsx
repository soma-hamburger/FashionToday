import React from 'react';
import ClosetBackground from '../img/background/main_background.jpg';
import '../style/ErrorPage.scss';

const refreshError = () => {
  localStorage.removeItem('error');
  window.location.href = '/';
};

const ErrorPage = ({ error }) => (
  <div className="ErrorPage">
    <img
      alt="ClosetBackground"
      src={ClosetBackground}
      className="ClosetBackground"
    />
    <div className="HandleBar">
      <div>{error}</div>
      <button type="button" onClick={refreshError}>
        홈으로
      </button>
    </div>
    <div className="Footer" />
  </div>
);
export default ErrorPage;
