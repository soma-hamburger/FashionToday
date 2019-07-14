import React, {useState,useEffect} from 'react';
import { SLink, Button, ClothesPicture, ClosetTable } from '../../styled';
import { ClothesArray } from '../../dummyAPI';
import ClothesItem from '../Common/ClothesItem';

const ClothesList = () => {
  const [clothesType, setClothesType] = useState("Top");
  const [clothesItem, setClothesItem] = useState(false);
  
  let RecentArray;
  let ClothesView;

  const setClothes = () => {
    if(clothesType === "Top") {
      RecentArray = ClothesArray.filter(Clothes => Clothes.class === "top");
    } else if(clothesType === "Bottom") {
      RecentArray = ClothesArray.filter(Clothes => Clothes.class === "bottom");
    }

    ClothesView = RecentArray.map((item, index)=>{
      return (
        <ClothesPicture onClick={()=>setClothesItem(item)} key={index} src={item.picture} alt={item.id}/>
      )
    });
  }

  setClothes();

  const reset = ()=>{
    setClothes();
    setClothesItem(false);
  }

  useEffect(reset, [clothesType]);

  const setTop = () => setClothesType("Top");
  const setBottom = () => setClothesType("Bottom");

  return (
    <>
      {clothesType} : 
      <Button onClick={setTop} small="true">Top</Button>
      <Button onClick={setBottom} small="true">Bottom</Button>
      <ClosetTable>
        {ClothesView}
      </ClosetTable>
      {clothesItem && <ClothesItem item={clothesItem}/>}
      <SLink small="true" color="gray" to ={`/closet/add`}>Add Clothes</SLink>
    </>
  );
}

export default ClothesList;