import React from 'react';
import { Switch, Route} from 'react-router-dom';
import ClothesList from '../components/Closet/ClothesList';
import AddClothes from '../components/Closet/AddClothes';
import ClothesItem from '../components/Closet/ClothesItem';
import { SLink } from '../styled';

const Closet = ({match}) => {
  return (
    <>
      <SLink to ={match.url} color="blue">Closet</SLink>
      <Switch>
        <Route path={`${match.url}/item/:clothesid`} component={ClothesItem}/>
        <Route path={`${match.url}/add`} component={AddClothes}/>
        <Route path={match.url} component={ClothesList}/>
      </Switch>
    </>
  );
}

export default Closet;