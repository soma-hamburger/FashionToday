import React, {useState,useEffect} from 'react';
import { A, LItem, Title } from '../../styled';
import Character from '../Common/Character';
import ClothesItem from '../Common/ClothesItem';

const LookItem = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = ()=>setClothesItem(false);

  useEffect(()=>{
    reset();
  }, [item]);
  
  return (
    <LItem>
      <A to={`looks/${item.id}`} ><Title>{item.user}의 {item.weather && `${item.weather} 날씨에 어울리는`} 룩</Title></A>
      <Character item={item} setClothesItem={setClothesItem}/>
      {clothesItem &&  <ClothesItem item={clothesItem} />}
    </LItem>
  );
}

export default LookItem;