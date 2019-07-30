import React from 'react';
import { Switch, Route} from 'react-router-dom';
import AddClothes from '../components/Closet/AddClothes';
import ClothesList from '../components/Closet/ClothesList';

const Closet = ({match}) => {
  return (
    <>
      <Switch>
        <Route path={`${match.url}/add`} component={AddClothes}/>
        <Route path={match.url} component={ClothesList}/>
      </Switch>
    </>
  );
}

export default Closet;