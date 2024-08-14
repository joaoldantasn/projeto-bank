import React from 'react';
import { Card } from 'primereact/card';
import AppToolbar from '../AppToolbar';
import BotaoVoltar from '../BotaoVoltar';
import { Button } from 'primereact/button';

const extrato = {
    dataHoraGeracao: "2024-08-14T20:52:19.674Z",
    nomeUsuario: "Maria Souza",
    descricao: "Extrato",
    numAgencia: 1235,
    numConta: 87654355,
    transacoes: [
        {
            id: 2,
            dataHora: "2023-08-02T11:00:00Z",
            tipo: "TRANSFERENCIA",
            valor: 200.00,
            // descricao: null,
            contaDestinatario: {
                idConta: 3,
                numeroConta: 76543266,
                // valor: 200.75,
                // ativa: true,
                // chavePix: "carlos.pereira@pix.com",
                tipoConta: "POUPANCA"
            }
        }
    ],
    periodoInicio: "2023-08-01T10:00:00Z",
    periodoFim: "2024-08-14T10:00:00Z",
};

export default function VisualizarExtrato() {

    const renderCards = (obj) => {
        return Object.entries(obj).map(([key, value], index) => {
            if (typeof value === 'object' && value !== null && !Array.isArray(value)) {
                // Para objetos aninhados, chama a função recursivamente
                return (
                    <div key={index}>
                        <Card title={key}>
                            {renderCards(value)}
                        </Card>
                    </div>
                );
            } else if (Array.isArray(value)) {
                // Para arrays, percorre cada item e exibe suas propriedades
                return (
                    <div key={index}>
                        <Card title={key}>
                            {value.map((item, i) => (
                                <div key={i}>
                                    {renderCards(item)}
                                </div>
                            ))}
                        </Card>
                    </div>
                );
            } else {
                // Para valores simples, cria um card normalmente
                return (
                    <Card key={index} title={key} subTitle={String(value)} />
                );
            }
        });
    };

    return (
        <div>
            <AppToolbar></AppToolbar>
            <Card>
                <BotaoVoltar></BotaoVoltar>
                <Button className={'p-ml-3'} label="DOWNLOAD" onClick={null} />
            </Card>
            {renderCards(extrato)}
        </div>
    );
}
