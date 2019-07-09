import React from 'react';
import { Switch, Route} from 'react-router-dom';
import AddClothes from '../components/Closet/AddClothes';
import ClothesList from '../components/Closet/ClothesList';
import ClothesWindow from '../components/Closet/ClothesWindow';
import { SLink } from '../styled';

const Closet = ({match}) => {
  return (
    <>
      <SLink to ={match.url} color="blue">Closet</SLink>
      <Switch>
        <Route path={`${match.url}/item/:clothesid`} component={ClothesWindow}/>
        <Route path={`${match.url}/add`} component={AddClothes}/>
        <Route path={match.url} component={ClothesList}/>
      </Switch>
    </>
  );
}

export default Closet;