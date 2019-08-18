import React from 'react';
import { Link } from 'react-router-dom';
import LookList from '../components/Looks/LookList';
import Daily from './Daily';

const Main = () => {
  return (
    <>
      <Daily/>
      <br/>
      <LookList/>
    </> 
  );
}

export default Main;