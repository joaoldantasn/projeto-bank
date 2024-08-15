import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import Home from "./Pages/Home";
import Cliente from "./Pages/Cliente";
import Admin from "./Pages/Admin";


//import { isAuthenticatedAdmin, isAuthenticatedDiscente, isAuthenticatedProfSaude, isAuthenticatedPsicologo, isAuthenticatedServidor } from "./auth";

// const PrivateRouteAdmin = ({ component: Component, ...rest }) => (
//   <Route
//     {...rest}
//     render={props =>
//       isAuthenticatedAdmin() ? (
//         <Component {...props} />
//       ) : (
//         <Redirect to={{ pathname: "/", state: { from: props.location } }} />
//       )
//     }
//   />
// );

// const PrivateRouteProfSaude = ({ component: Component, ...rest }) => (
//   <Route
//     {...rest}
//     render={props =>
//       isAuthenticatedProfSaude() ? (
//         <Component {...props} />
//       ) : (
//         <Redirect to={{ pathname: "/", state: { from: props.location } }} />
//       )
//     }
//   />
// );



export default function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/user" component={Cliente} />
        <Route path="/admin" component={Admin} />
        <Route path="/agencias" component={Admin} />

        {/* <Route path="/publica/MateriaisOnline" component={MateriaisOnline} /> */}

       
        {/* <PrivateRouteProfSaude path="/profissionalDeSaude/reportesGrafico" component={ReportesGraficosProfSaude} />

        <PrivateRouteAdmin path="/Admin/perfil" component={PerfilAdmin} /> */}
       
      </Switch>
    </BrowserRouter>
  );
}

