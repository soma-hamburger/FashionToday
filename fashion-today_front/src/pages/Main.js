import React from 'react';
import { Link } from 'react-router-dom';

const Main = () => {
  return (
    <>
      <nav>
        <Link to="/calendar">Calendar</Link><br/>
        <Link to="/closet">Closet</Link><br/>
        <Link to="/daily">Daily Look</Link><br/>
        <Link to="/look">Looks</Link><br/>
        <Link to="/mypage">My Page</Link><br/>
        <Link to="/recommend">Recomend</Link><br/>
      </nav>
    </> 
  );
}

export default Main;