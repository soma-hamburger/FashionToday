import React from 'react';
import MainBar from './MainBar';
import '../../style/Layout.scss';

const LayOut = ({ children }) => (
  <div className="LayOut">
    <MainBar />

    <div className="Children">{children}</div>
  </div>
);

export default LayOut;
