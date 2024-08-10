import React from 'react';
import { Toolbar } from 'primereact/toolbar';

const NomeToolbar = () => {
    // Definir os itens da esquerda na Toolbar
    const leftContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0'}}>Nome</h2>
        </React.Fragment>
    );

    // Definir os itens da direita na Toolbar
    const rightContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0', color: 'var(--green-500)' }}>Pedro Alvares Cabral</h2>
        </React.Fragment>
    );

    return (
        <Toolbar left={leftContents} right={rightContents} />
    );
}

export default NomeToolbar;
