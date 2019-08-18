import React from 'react';
import { A, Title, Image, Keyword } from '../../styled';
import { LookImageWrapper, LookTemplate } from '../../styled/looks';

const LookPrewindow = ({item}) => {
  console.log(item);

  const KeywordView = item.key_word.map((k, index)=>
      <Keyword key={index}>{k}</Keyword>
    )

  return (
    <LookTemplate>
      <LookImageWrapper>
        <Image src={item.look_picture[0]} alt={item.id} br={"10px"}/>
      </LookImageWrapper>
      <A to={`looks/${item.id}`} >
        <Title>{item.title} {KeywordView}</Title>
      </A>
    </LookTemplate>
  );
}

export default LookPrewindow;