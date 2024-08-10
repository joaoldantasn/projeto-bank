import React from 'react';
//import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

import BotaoVoltar from '../../Components/BotaoVoltar';
import { Button } from 'primereact/button';
import BotaoSair from '../../Components/BotaoSair';
import TabViewCliente from '../../Components/TabViewCliente';
import SaldoToolbar from '../../Components/SaldoToolbar';

export default function Cliente() {

    return (
        <div>
            <TabViewCliente></TabViewCliente>
        </div>

    );
}