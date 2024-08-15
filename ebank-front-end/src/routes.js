import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import Home from "./Pages/Home";
import Cliente from "./Pages/Cliente";
import Admin from "./Pages/Admin";
import VisualizarExtrato from "./Components/VisualizarExtrato";
import AgenciaList from './Components/TabViewAllClientes';
import NovoUsuario from "./Pages/NovoUsuario";


export default function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/user" component={Cliente} />
        <Route path="/admin" component={Admin} />
        <Route path="/extrato" component={VisualizarExtrato} />
        <Route path="/agencias" component={AgenciaList} />
        <Route path="/novo/usuario" component={NovoUsuario} />

      </Switch>
    </BrowserRouter>
  );
}

