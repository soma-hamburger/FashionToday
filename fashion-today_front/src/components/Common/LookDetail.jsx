import React, {useState, useEffect} from 'react';
import { LItem, Title } from '../../styled';
import ClothesItem from './ClothesItem';
import Character from './Character';

const LookDetail = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = () =>setClothesItem(false);

  useEffect(()=>{
    reset();
  }, [item]);
  
  return (
    <LItem>
      <Title onClick={reset}>{item.user}의 {item.recommend.weather && `${item.recommend.weather} 날씨에 어울리는`} 룩</Title>
      <Character item={item} setClothesItem={setClothesItem} isSet={true}/>
      {clothesItem &&  <ClothesItem item={clothesItem} />}
    </LItem>
  );
}

export default LookDetail;