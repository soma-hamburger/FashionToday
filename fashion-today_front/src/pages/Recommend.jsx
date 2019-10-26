import React from 'react';
import { Route, Switch } from 'react-router-dom';
import RecommendList from '../components/Recommend/RecommendList';
import RecommendSubmit from '../components/Recommend/RecommendSubmit';
import '../style/Recommend.scss';

const Recommend = () => (
  <div className="Recommend">
    <Switch>
      <Route exact path="/recommend" component={RecommendList} />
      <Route path="/recommend/:userid" component={RecommendSubmit} />
    </Switch>
  </div>
);
export default Recommend;
