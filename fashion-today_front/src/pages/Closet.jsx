import React from 'react';
import { Switch, Route } from 'react-router-dom';
import ReactRouterPropTypes from 'react-router-prop-types';
import AddClothes from '../components/Closet/AddClothes';
import ClothesTable from '../components/Closet/ClothesTable';

const Closet = ({ match }) => (
  <>
    <Switch>
      <Route path={`${match.url}/add`} component={AddClothes} />
      <Route path={match.url} component={ClothesTable} />
    </Switch>
  </>
);

Closet.propTypes = {
  match: ReactRouterPropTypes.match.isRequired,
};
export default Closet;
