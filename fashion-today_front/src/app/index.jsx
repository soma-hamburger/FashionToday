import React, { useState } from 'react';
import LogOnApp from './LogOnApp';
import LogOutApp from './LogOutApp';
// import axios from 'axios';

function App() {
  const [isLogOn, setIsLogOn] = useState(false);

  const logOn = async () => {
    setIsLogOn(true);
  };

  return (
    <>
      {isLogOn ? <LogOnApp /> : <LogOutApp logOn={logOn} />}
    </>
  );
}

export default App;
