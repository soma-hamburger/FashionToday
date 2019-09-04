import React, {useState} from "react";
import LogOnApp from "./LogOnApp";
import LogOutApp from "./LogOutApp";

function App() {
  const [isLogOn, setIsLogOn] = useState(false);

  const logOn = () => {
    setIsLogOn(true);
  }
  
  return (
    <>
      {isLogOn ? <LogOnApp /> : <LogOutApp logOn={logOn} />}
    </>
  )
}

export default App;
