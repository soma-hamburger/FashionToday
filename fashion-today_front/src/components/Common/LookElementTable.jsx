import React from 'react';
import { Element, List, Category, Keyword, /*ColorBox*/} from '../../styled';

const LookElementTable = ({item, setClothesItem, isSet}) => {
  const setClothes = (e) => {
    if(isSet) setClothesItem(item.look_elements[e.target.getAttribute("id")]);
  }

  const LookElementList = item.look_elements.map((element, index) => {
    console.log(element);

    const KeywordView = element.key_word.map((k, index)=>
      <Keyword key={index}>{k}</Keyword>
    )

    return (
      <Element key={index} small={!isSet}>
        <Category id={index} onClick={isSet ? setClothes : null}>{element.category}</Category>
        {KeywordView}
      </Element>
    );
  });

  return (
    <List>
      {LookElementList}
    </List>
  )
}

export default LookElementTable;