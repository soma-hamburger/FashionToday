import React from 'react';
import LookPreview from '../components/Common/LookPreview';
import { getDailyLookList } from '../defaultAPI';
import DailyLookList from '../components/Main/DailyLookList';

const Main = () => {
  const DLlist = getDailyLookList;
  return (
    <>
      <b>main</b>
      <DailyLookList d_l_list={DLlist} />
      <LookPreview />
    </>
  );
};

export default Main;
