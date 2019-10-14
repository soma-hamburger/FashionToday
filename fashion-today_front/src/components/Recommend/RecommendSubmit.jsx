import React, { useContext, useState, useRef } from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { useFetch } from '../../Tool';
import { LoginContext } from '../../Context';
import ClosetTable from '../Closet/ClosetTable';
import ClosetNavigation from '../Closet/ClosetNavigation';

const RecommendSubmit = ({ match }) => {
  const userId = match.params.userid;
  const loginTool = useContext(LoginContext);
  const RequestorCloset = useFetch('requestor/closet', loginTool.token, {
    userId,
  });

  const [category, setCategory] = useState(null);
  const [color, setColor] = useState(null);

  const navTool = {
    setCategory,
    setColor,
  };

  const LookImage = useRef();

  const drawImage = () => {
    const ctx = LookImage.current.getContext('2d');
    ctx.fillRect(20, 20, 150, 100);
  };

  const DownloadImage = () => {
    const link = document.createElement('a');
    link.download = 'image.png';

    LookImage.current.toBlob(blob => {
      link.href = URL.createObjectURL(blob);
      console.log(blob);

      console.log(link.href);

      link.click();
    }, 'image/png');
  };

  console.log(RequestorCloset);
  return (
    <div>
      {RequestorCloset && (
        <>
          {RequestorCloset.requestor_name}님의 옷장
          <br />
          <ClosetNavigation navTool={navTool} />
          <ClosetTable
            category={category}
            color={color}
            array={RequestorCloset.clothes_array}
          />
          <canvas ref={LookImage} className="LookImage" onClick={drawImage} />
          <button type="submit" onClick={DownloadImage}>
            다운
          </button>
        </>
      )}
    </div>
  );
};

RecommendSubmit.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};

export default RecommendSubmit;
