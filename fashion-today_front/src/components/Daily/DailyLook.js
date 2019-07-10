import React, {useState,useEffect} from 'react';
import {Link} from 'react-router-dom';
import { LItem, Title } from '../../styled';
import ClothesItem from '../Common/ClothesItem';
import Character from '../Common/Character';

const DailyLook = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = ()=>setClothesItem(false);

  useEffect(()=>{
    reset();
  }, [item]);
  
  return (
    <LItem>
      <Title onClick={reset}>{item.recommend_user}가 추천하는 룩</Title>
      <Character item={item} setClothesItem={setClothesItem}/>
      {clothesItem &&  <ClothesItem item={clothesItem} />}
    </LItem>
  );
}

export default DailyLook;