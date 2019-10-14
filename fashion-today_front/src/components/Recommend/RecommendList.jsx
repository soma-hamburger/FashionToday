import React, { useContext } from 'react';
import Requestor from './Requestor';
import { LoginContext } from '../../Context';
import { useFetch } from '../../Tool';

const MakeRequestors = RequestorList => {
  if (RequestorList)
    return RequestorList.requestor_array.map(requestor => (
      <Requestor requestor={requestor} key={requestor.id} />
    ));
  return null;
};

const RecommendList = () => {
  const loginTool = useContext(LoginContext);
  const RequestorList = useFetch('requestor/list', loginTool.token);
  console.log(RequestorList);

  const Requstors = MakeRequestors(RequestorList);

  return <div className="RecommendList">{Requstors}</div>;
};

export default RecommendList;
