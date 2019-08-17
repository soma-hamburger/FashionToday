import React from 'react';
import { Switch, Route} from 'react-router-dom';
import AddClothes from '../components/Closet/AddClothes';
import ClothesTable from '../components/Closet/ClothesTable';

const Closet = ({match}) => {
  return (
    <>
      <Switch>
        <Route path={`${match.url}/add`} component={AddClothes}/>
        <Route path={match.url} component={ClothesTable}/>
      </Switch>
    </>
  );
}

export default Closet;