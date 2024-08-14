import React, { useState, useEffect } from 'react';
import { Toolbar } from 'primereact/toolbar';
import { useUsuario } from '../../Hooks/useUsuario';

export default function NomeToolbar() {
    const usuario = useUsuario();

    // Definir os itens da esquerda na Toolbar
    const leftContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0' }}>Nome</h2>
        </React.Fragment>
    );

    // Definir os itens da direita na Toolbar
    const rightContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0', color: 'var(--green-500)' }}>
                {usuario ? usuario.nomeUsuario : 'Carregando...'}
            </h2>
        </React.Fragment>
    );

    return (
        <Toolbar left={leftContents} right={rightContents} />
    );
}
