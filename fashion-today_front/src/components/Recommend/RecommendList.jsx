import React, { useContext } from 'react';
import Requestor from './Requestor';
import { UserContext } from '../../Context';
import { useFetch } from '../../Tool';

const MakeRequestors = RequestorList => {
  if (RequestorList)
    return RequestorList.data.requestor_array.map(requestor => (
      <Requestor requestor={requestor} key={requestor.id} />
    ));
  return null;
};

const RecommendList = () => {
  const user = useContext(UserContext);
  const RequestorList = useFetch('post', 'requestor/list', user.token);
  console.log(RequestorList);

  const Requstors = MakeRequestors(RequestorList);

  return <div className="RecommendList">{Requstors}</div>;
};

export default RecommendList;
