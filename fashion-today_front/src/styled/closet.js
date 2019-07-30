import styled from 'styled-components';

export const ClosetTable = styled.div`
  border: 1px solid black;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(150px, auto);
`

export const PictureWrapper = styled.div`
  border: 1px solid black;
  margin: 5px;
`

export const ClothesPicture = styled.img`
  cursor: pointer;
  width: 100%;
  height: 100%;
`