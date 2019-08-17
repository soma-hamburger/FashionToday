import React, {useState,useEffect} from 'react';
import {Title, Button, Image, Keyword } from '../../styled';
import LookElementWindow from '../Common/LookElementWindow';
import LookElementTable from '../Common/LookElementTable';
import { DayLookView, DayLookImageView, DayLookInfoView, DayLEView, DayLookImageWrapper, Recommend, DayComment } from '../../styled/calendar';

const DayLook = ({item}) => {
  const [clothesItem, setClothesItem] = useState(false);
  const [index, setIndex] = useState(0);

  const prevIndex = () => {
    if(index === 0) setIndex(item.look_picture.length-1);
    else setIndex(index-1);
  }

  const nextIndex = () => {
    if(index === item.look_picture.length-1) setIndex(0);
    else setIndex(index+1);
  }

  const reset = () => setClothesItem(false);

  useEffect(()=>reset(), [item]);

  const KeywordView = item.key_word.map((k, index)=>
      <Keyword key={index}>{k}</Keyword>
    )

  return (
    <DayLookView>
      <DayLookImageView>
        <DayLookImageWrapper>
          <Image src={item.look_picture[index]}/>
        </DayLookImageWrapper>
        { item.look_picture.length > 1 ? 
          <>
            <Button onClick={prevIndex} small="true">prev</Button>
            <Button onClick={nextIndex} small="true">next</Button>
          </>
          : null
        }
      </DayLookImageView>
      <DayLookInfoView> 
        <Title onClick={reset}>{item.title} {KeywordView}</Title>
        <DayComment>{item.comment}</DayComment>
        <LookElementTable item={item} setClothesItem={setClothesItem} isSet={true}/>
        <Recommend>추천인: {item.recommend.recommender} 추천날짜: {item.make_date}</Recommend>
        {item.social.share ? "공유됨" : <Button color="gray" small="true">공유</Button>}
      </DayLookInfoView>
      <DayLEView>
        {clothesItem &&  <LookElementWindow item={clothesItem} />}
      </DayLEView>
    </DayLookView>
  );
}

export default DayLook;