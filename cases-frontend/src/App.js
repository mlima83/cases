import React from 'react';
import logo from './logo.svg';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import './App.css';
import Home from './components/Home/Home';
import CasesCrud from './components/Cases/CasesCrud';

function App() {
  return (
    <BrowserRouter>
        <Switch>
            <Route path="/" exact={true} component={Home} />
            <Route path="/home" component={Home} />
            <Route path="/cases" component={CasesCrud} />
        </Switch>
    </ BrowserRouter>
  );
}

export default App;
