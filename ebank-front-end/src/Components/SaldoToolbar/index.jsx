import React from 'react';
import { Toolbar } from 'primereact/toolbar';

const SaldoToolbar = () => {
    // Definir os itens da esquerda na Toolbar
    const leftContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0'}}>Saldo</h2>
        </React.Fragment>
    );

    // Definir os itens da direita na Toolbar
    const rightContents = (
        <React.Fragment>
            <h2 style={{ margin: '0 1rem 0 0', color: 'var(--green-500)' }}>1024.66</h2>
        </React.Fragment>
    );

    return (
        <Toolbar left={leftContents} right={rightContents} />
    );
}

export default SaldoToolbar;
