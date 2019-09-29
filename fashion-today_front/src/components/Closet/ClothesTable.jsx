import React, { useState, useEffect } from 'react';
import { SLink, Button, Image } from '../../styled';
import { ClosetTable, PictureWrapper } from '../../styled/closet';
import { UserCloset } from '../../defaultAPI';

const ClothesTable = () => {
  const [clothesType, setClothesType] = useState('Top');

  let ClothesView;

  const setClothes = () => {
    ClothesView = UserCloset.map((item, index) => (
      <PictureWrapper key={index}>
        <Image src={item.image} alt={item.id} />
      </PictureWrapper>
    ));
  };

  useEffect(setClothes, [clothesType]);

  const setTop = () => setClothesType('Top');
  const setBottom = () => setClothesType('Bottom');

  return (
    <>
      {clothesType} :
      <Button onClick={setTop} small="true">Top</Button>
      <Button onClick={setBottom} small="true">Bottom</Button>
      <ClosetTable>
        {ClothesView}
      </ClosetTable>
      <SLink small="true" color="gray" to="/closet/add">Add Clothes</SLink>
    </>
  );
};

export default ClothesTable;
