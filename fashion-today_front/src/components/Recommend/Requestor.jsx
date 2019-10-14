import React from 'react';
import { LinkDiv } from '../Common/Components';

const Requestor = ({ requestor }) => (
  <div className="Requestor">
    {requestor.id}
    <LinkDiv to={`recommend/${requestor.id}`}>
      <>{requestor.name}의 옷장 보기</>
    </LinkDiv>
  </div>
);

export default Requestor;
