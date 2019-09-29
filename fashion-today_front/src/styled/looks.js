import styled from 'styled-components';

export const LookGrid = styled.div`
  display: grid;
  grid-template-columns: ${(props) => `repeat(${props.gridNum}, 1fr)`};
  grid-gap: 10px;
  padding: 10px;
`;
export const LookTemplate = styled.div`
  border: 1px solid gray;
  border-radius: 10px;
  margin-bottom: 20px;
`;
export const LookImageWrapper = styled.div`
  min-height: 100px;
  border-radius: 10px;
`;
export const LookView = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: max-content;
`;
export const LookImageView = styled.div`
  width: 100%;
  border: 1px solid gray;
  text-align: center;
  grid-row: 1/3;
`;
export const LEView = styled.div`
`;

export const Comment = styled.div`
  padding: 5px;
  border-radius: 3px 3px 0px 0px;
  border: 1px solid gray;
  font-size: 13px;
`;

export const LookInfoView = styled.div`
  padding: 15px;
  border: 1px solid gray;
`;

export const Social = styled.div`
  padding: 15px;
  border: 1px solid gray;
  margin: 5px 0px 5px 0px;
`;
export const CommentBox = styled.div`
  margin-top: 10px;
  border: 1px solid gray;
  padding-left: 10px;
  border-radius: 0px 7px 7px 7px;
`;
