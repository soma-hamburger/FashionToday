import React, {useState,useEffect} from 'react';
import { SLink, Button, Image } from '../../styled';
import { ClosetTable, PictureWrapper} from '../../styled/closet';
import { ClothesArray } from '../../dummyAPI';
import ClothesItem from '../Common/LookElementWindow';

const ClothesTable = () => {
  const [clothesType, setClothesType] = useState("Top");
  const [clothesItem, setClothesItem] = useState(false);
  
  let ClothesView;

  const setClothes = () => {

    ClothesView = ClothesArray.map((item, index)=>{
      return (
        <PictureWrapper key={index} >
          <Image onClick={()=>setClothesItem(item)} src={item.image} alt={item.id}/>
        </PictureWrapper>
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

export default ClothesTable;