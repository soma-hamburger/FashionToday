import React, {useState,useEffect} from 'react';
import {Link} from 'react-router-dom';
import { SLink, Button, ClothesPicture } from '../../styled';
import { TopArray, BottomArray } from '../../dummyAPI';
import ClothesItem from '../Common/ClothesItem';

const ClothesList = () => {
  const [clothesType, setClothesType] = useState("Top");
  const [clothesItem, setClothesItem] = useState(false);
  
  let ClothesArray;
  let ClothesView;

  const setClothes = () => {
    if(clothesType === "Top") {
      ClothesArray = TopArray;
    } else if(clothesType === "Bottom") {
      ClothesArray = BottomArray;
    }

    ClothesView = ClothesArray.map((item, index)=>{
      return (
        <ClothesPicture onClick={()=>setClothesItem(item)} key={index}>
          {item.picture}
          {item.id}
        </ClothesPicture>
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
      <div>
        {ClothesView}
      </div>
      {clothesItem && <ClothesItem item={clothesItem}/>}
      <SLink small="true" color="gray" to ={`/closet/add`}>Add Clothes</SLink>
    </>
  );
}

export default ClothesList;