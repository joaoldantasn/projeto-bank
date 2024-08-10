import React from 'react';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { Avatar } from 'primereact/avatar';
import BotaoSair from '../BotaoSair';

const AppToolbar = () => {
    // Definir os itens da esquerda na Toolbar
    const leftContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0', color: 'var(--green-500)' }}>E-BANK</h2>
        </React.Fragment>
    );

    // Definir os itens da direita na Toolbar
    const rightContents = (
        <React.Fragment>
            <BotaoSair></BotaoSair>
        </React.Fragment>
    );

    return (
        <Toolbar left={leftContents} right={rightContents} />
    );
}

export default AppToolbar;
