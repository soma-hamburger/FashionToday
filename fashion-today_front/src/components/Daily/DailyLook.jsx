import React, {useState,useEffect} from 'react';
import { Title, Button, Image } from '../../styled';
import LookElementWindow from '../Common/LookElementWindow';
import LookElementTable from '../Common/LookElementTable';
import { DailyGrid, DailyImage, DailyInfo } from '../../styled/daily';
import { Comment } from '../../styled/looks';

const DailyLook = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);

  const reset = ()=>setClothesItem(false);

  useEffect(()=>reset(), [item]);

  return (
    <DailyGrid>
      <DailyImage>
        <Image src={item.look_picture[0]}/>
      </DailyImage>
      <DailyInfo>
        <Title onClick={reset}>{item.title}</Title>
        <Comment>{item.comment}</Comment>
        <LookElementTable item={item} setClothesItem={setClothesItem} isSet={true}/>
        {clothesItem &&  <LookElementWindow item={clothesItem} />}
        <p>
          <Button color="gray" small="true">Look 선택</Button>
          <Button color="gray" small="true">Look 킵</Button>
        </p>
      </DailyInfo>
    </DailyGrid>
  );
}

export default DailyLook;