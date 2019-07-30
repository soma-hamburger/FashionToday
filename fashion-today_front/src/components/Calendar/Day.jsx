import React, {useState} from 'react';
import { LookInfo } from '../../dummyAPI';
import Character from '../Common/Character';
import ClothesItem from '../Common/ClothesItem';
import styled from 'styled-components';

const DayLook = styled.div`
  width: 100%;
`
const Schedule = styled.div`
  border: 1px solid black;
  padding: 10px;
`
const DayGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
`
const Day = ({match}) => {
  const [clothesItem, setClothesItem] = useState(false);

  return (
    <>
      Day : {match.params.dayid}
      <DayGrid>
        <DayLook>
          <Character item={LookInfo} match={match} setClothesItem={setClothesItem} isSet={true}/>
          {clothesItem &&  <ClothesItem item={clothesItem} />}
        </DayLook>
        <Schedule>
          <h1>일정</h1>
        </Schedule>
      </DayGrid>
    </>
  );
}

export default Day;