import React, {useState, useEffect} from 'react';
import { LookInfo } from '../../dummyAPI';
import { Button, Keyword, Image, Title, Input } from '../../styled';
import { LookView, LookImageView, LookImageWrapper, LookInfoView, LEView, Comment, Social, CommentBox } from '../../styled/looks';
import { Recommend } from '../../styled/calendar';
import LookElementTable from '../Common/LookElementTable';
import LookElementWindow from '../Common/LookElementWindow';

const LookWindow = ({match}) => {
  console.log(match.params.lookid);
  const item = LookInfo;
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

  const CommentView = item.social.comments.map((c, index)=>
    <CommentBox>{c.user} : {c.text}</CommentBox>
  )

  return (
    <LookView>
      <LookImageView>
        <LookImageWrapper>
          <Image src={item.look_picture[index]}/>
        </LookImageWrapper>
        { item.look_picture.length > 1 ? 
          <>
            <Button onClick={prevIndex} small="true">prev</Button>
            <Button onClick={nextIndex} small="true">next</Button>
          </>
          : null
        }
      </LookImageView>
      <LookInfoView> 
        <Title onClick={reset}>{item.title} {KeywordView}</Title>
        <Comment>{item.comment}</Comment>
        <LookElementTable item={item} setClothesItem={setClothesItem} isSet={true}/>
        <Recommend>추천인: {item.recommend.recommender} 추천날짜: {item.make_date}</Recommend>
        <Social>
          좋아요: {item.social.like}<br/>
          좋아요한 사람 : {item.social.like_user.map((u)=><span> {u} </span>)}<br/>
          { CommentView }
          댓글 쓰기: <Input width={"280px"}/>
        </Social>
      </LookInfoView>
      <LEView>
        {clothesItem &&  <LookElementWindow item={clothesItem} />}
      </LEView>
    </LookView>
  );
}

export default LookWindow;