import React from 'react';
//import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { Button } from 'primereact/button';

import AgenciaList from '../../Components/TabViewAllClientes';


export default function Admin() {

    const history = useHistory();

    function submeter() {
        history.push('/novo/usuario');
    }

    return (
        <div>
            <AgenciaList></AgenciaList>
            <Card>
                <Button className={'p-ml-3 p-mt-3'} label='NOVO USUÁRIO' icon='pi pi-user' onClick={submeter} />
            </Card>
        </div>

    );
}