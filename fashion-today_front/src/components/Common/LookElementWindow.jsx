import React, {useState} from 'react';
import { LEWindow, LEImageWrapper, LEImageView, Button, LEInfoView, ColorBox, ColorList, Image } from '../../styled';

const LookElementWindow = ({item}) => {
  const [index, setIndex] = useState(0);

  const prevIndex = () => {
    if(index === 0) setIndex(item.image.length-1);
    else setIndex(index-1);
  }

  const nextIndex = () => {
    if(index === item.image.length-1) setIndex(0);
    else setIndex(index+1);
  }

  const ColorView = item.color.map((c, index)=>
    <ColorBox color={c} key={index}/>);

  return (
    <LEWindow>
      <LEImageView>
        <LEImageWrapper>
          <Image src={item.image[index]} alt={index}/>
        </LEImageWrapper>
        { item.image.length > 1 ? 
          <>
            <Button onClick={prevIndex} small="true">prev</Button>
            <Button onClick={nextIndex} small="true">next</Button>
          </>
          : null
        }
      </LEImageView>
      <LEInfoView>
        종류 : {item.category}<br/>
        키워드 : {item.key_word}<br/>
        용도 : {item.purpose}<br/>
        <ColorList childNum={item.color.length}>
          색깔 : 
          <ColorBox color={item.main_color}/>
          {ColorView}
        </ColorList>
        좋아요 : {item.like}
      </LEInfoView>
    </LEWindow>
  );
}

export default LookElementWindow;