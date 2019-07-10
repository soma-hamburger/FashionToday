import React, {useState, useEffect} from 'react';
import { LItem, Title } from '../../styled';
import { Switch,Route} from 'react-router-dom';
import ClothesItem from './ClothesItem';
import Character from './Character';

const LookDetail = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = ()=>setClothesItem(false);

  useEffect(()=>{
    reset();
  }, [item]);
  
  return (
    <LItem>
      <Title onClick={reset}>{item.user}의 {item.weather && `${item.weather} 날씨에 어울리는`} 룩</Title>
      <Character item={item} setClothesItem={setClothesItem}/>
      {clothesItem &&  <ClothesItem item={clothesItem} />}
    </LItem>
  );
}

export default LookDetail;