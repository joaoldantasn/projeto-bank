import React, { useEffect, useState } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useUsuario } from './Hooks/useUsuario';

const PrivateRoute = ({ component: Component, roles, ...rest }) => {
    const usuario = useUsuario();
    const userRole = usuario?.role;


    return (
        <Route
            {...rest}
            render={(props) =>
                userRole ? (
                    roles.includes(userRole) ? (
                        <Component {...props} />
                    ) : (
                        <Redirect to='/login' />
                    )
                ) : (
                    <Redirect to='/login' />
                )
            }
        />
    );
};

export default PrivateRoute;
