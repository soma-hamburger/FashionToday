import React from "react";
import LogOnApp from "./LogOnApp";
import LogOutApp from "./LogOutApp";

function App() {
  let isLogOn = false;

  return (
    <>
      {isLogOn ? <LogOnApp /> : <LogOutApp />}
    </>
  )
}

export default App;
