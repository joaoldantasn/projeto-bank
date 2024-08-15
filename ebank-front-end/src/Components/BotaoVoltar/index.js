import React from 'react';
import { Button } from 'primereact/button';
import { useHistory } from 'react-router-dom';

export default function BotaoVoltar() {

    const history = useHistory();
    var configBotaoVoltar = "p-button-secondary ";
    const voltar = () => {
        history.push('/home');
    }

    return (
        <Button className={configBotaoVoltar} label="VOLTAR" onClick={voltar} />
    );
}