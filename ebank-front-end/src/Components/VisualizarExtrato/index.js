import React from 'react';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { useLocation } from 'react-router-dom';
import AppToolbar from '../AppToolbar';
import BotaoVoltar from '../BotaoVoltar';
import useGeneratePDF from '../../Hooks/gerarPDF';

const ExtratoDetalhes = ({ extrato }) => (
  <Card title='Detalhes do Extrato' className='p-mb-4'>
    <div className='p-grid'>
      <div className='p-col-12 p-md-6'>
        <p>
          <strong>Data de Geração:</strong>{' '}
          {new Date(extrato.dataHoraGeracao).toLocaleString()}
        </p>
        <p>
          <strong>Nome do Usuário:</strong> {extrato.nomeUsuario}
        </p>
        <p>
          <strong>Descrição:</strong> {extrato.descricao}
        </p>
      </div>
      <div className='p-col-12 p-md-6'>
        <p>
          <strong>Agência:</strong> {extrato.numAgencia}
        </p>
        <p>
          <strong>Conta:</strong> {extrato.numConta}
        </p>
        <p>
          <strong>Período:</strong>{' '}
          {new Date(extrato.periodoInicio).toLocaleDateString()} -{' '}
          {new Date(extrato.periodoFim).toLocaleDateString()}
        </p>
      </div>
    </div>
  </Card>
);

const TransacaoCard = ({ transacao }) => (
  <Card
    key={transacao.id}
    title={`Transação: ${transacao.id}`}
    className='p-mb-4'
  >
    <div className='p-grid'>
      <div className='p-col-12 p-md-6'>
        <p>
          <strong>Data:</strong> {new Date(transacao.dataHora).toLocaleString()}
        </p>
        <p>
          <strong>Tipo:</strong> {transacao.tipo}
        </p>
        <p>
          <strong>Valor:</strong> R$ {transacao.valor.toFixed(2)}
        </p>
      </div>
      {transacao.contaDestinatario && (
        <div className='p-col-12 p-md-6'>
          <p>
            <strong>Conta Destinatária:</strong>{' '}
            {transacao.contaDestinatario.numeroConta}
          </p>
          <p>
            <strong>Tipo de Conta:</strong>{' '}
            {transacao.contaDestinatario.tipoConta}
          </p>
        </div>
      )}
    </div>
  </Card>
);

const VisualizarExtrato = () => {
  const location = useLocation();
  const { extrato } = location.state || {};

const gerarPDF = useGeneratePDF()

  return (
    <div className='p-m-4'>
      <AppToolbar />
      <div className='p-d-flex p-jc-between p-ai-center p-mb-4 p-mt-4'>
        <BotaoVoltar />
        <Button
          className='p-button-success'
          label='DOWNLOAD PDF'
          onClick={() => gerarPDF('comprovante', 'comprovante.pdf')}
        />
      </div>
      {extrato && (
        <div id='comprovante'>
          <ExtratoDetalhes extrato={extrato} />
          <Card title='Transações' className='p-mb-4'>
            {extrato.transacoes && extrato.transacoes.length > 0 ? (
              extrato.transacoes.map((transacao) => (
                <TransacaoCard key={transacao.id} transacao={transacao} />
              ))
            ) : (
              <p>Não há transações para exibir.</p>
            )}
          </Card>
        </div>
      )}
    </div>
  );
};

export default VisualizarExtrato;
