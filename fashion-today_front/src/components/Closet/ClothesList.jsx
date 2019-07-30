import React, {useState,useEffect} from 'react';
import { SLink, Button } from '../../styled';
import { ClosetTable, ClothesPicture, PictureWrapper} from '../../styled/closet';
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
        <PictureWrapper key={index} >
          <ClothesPicture onClick={()=>setClothesItem(item)} src={item.picture} alt={item.id}/>
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

export default ClothesList;