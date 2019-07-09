import React from 'react';
import { LItem, A } from '../../styled';
import { Switch,Route} from 'react-router-dom';
import ClothesItem from './ClothesItem';
import Character from './Character';

const LookDetail = ({item, match}) => {
  return (
    <LItem>
      <A to={match.url}>{item.user}의 {item.weather && `${item.weather} 날씨에 어울리는`} 룩<br/></A>
      {item.id}<br/>
      <Switch>
        <Route exact path={match.url} render={(props)=><Character {...props} item={item}/>} />
        <Route path={`${match.url}/:clothesid`} component={ClothesItem} />
      </Switch>
    </LItem>
  );
}

export default LookDetail;