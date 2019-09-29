import React from 'react';
import { Title, Button, Image } from '../../styled';
import {
  // eslint-disable-next-line max-len
  DayLookView, DayLookImageView, DayLookInfoView, DayLookImageWrapper, Recommend, DayComment,
} from '../../styled/calendar';

const DayLook = ({ item }) => {
  return (
    <DayLookView>
      <DayLookImageView>
        <DayLookImageWrapper>
          <Image src={item.look_picture[0]} />
        </DayLookImageWrapper>
      </DayLookImageView>
      <DayLookInfoView>
        <Title>{item.title}</Title>
        <DayComment>{item.comment}</DayComment>
        <Recommend>추천인: {item.recommend.recommender} 추천날짜: {item.make_date}</Recommend>
        {item.social.share ? '공유됨' : <Button color="gray" small="true">공유</Button>}
      </DayLookInfoView>
    </DayLookView>
  );
};

export default DayLook;
