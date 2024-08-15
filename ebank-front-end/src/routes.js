import React from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import Cliente from './Pages/Cliente';
import Admin from './Pages/Admin';
import VisualizarExtrato from './Components/VisualizarExtrato';
import AgenciaList from './Components/TabViewAllClientes';
import NovoUsuario from './Pages/NovoUsuario';
import Login from './Pages/Login';
import Home from './Pages/Home';
import PrivateRoute from './privateRoutes';

export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path='/home' exact component={Home} />
                <Route path='/login' exact component={Login} />
                <Route path='/extrato' exact component={VisualizarExtrato} />
                <PrivateRoute path='/user' component={Cliente} roles={['USER']} />
                <PrivateRoute path='/admin' component={Admin} roles={['ADMIN']} />
                <PrivateRoute path='/agencias' component={AgenciaList} roles={['ADMIN']} />
                <PrivateRoute path='/novo/usuario' component={NovoUsuario} roles={['ADMIN']} />
                <Redirect to='/' />
            </Switch>
        </BrowserRouter>
    );
}
