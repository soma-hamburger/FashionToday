import React from 'react';
import { Link } from 'react-router-dom';

const Main = () => {
  return (
    <>
      <Link to="/calendar">Calendar</Link><br/>
      <Link to="/closet">Closet</Link><br/>
      <Link to="/daily">Daily Look</Link><br/>
      <Link to="/looks">Looks</Link><br/>
      <Link to="/mypage">My Page</Link><br/>
      <Link to="/recommend">Recomend</Link><br/>
    </> 
  );
}

export default Main;