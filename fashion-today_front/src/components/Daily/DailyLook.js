import React, {useState,useEffect} from 'react';
import { LItem, Title, Button } from '../../styled';
import ClothesItem from '../Common/ClothesItem';
import Character from '../Common/Character';

const DailyLook = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = ()=>setClothesItem(false);

  useEffect(()=>reset(), [item]);

  return (
    <LItem>
      <Title onClick={reset}>{item.recommend.recommender}가 추천하는 룩</Title>
      <Character item={item} setClothesItem={setClothesItem} isSet={true}/>
      {clothesItem &&  <ClothesItem item={clothesItem} />}
      <p>
        <Button color="gray" small="true">Look 선택</Button>
      </p>
    </LItem>
  );
}

export default DailyLook;