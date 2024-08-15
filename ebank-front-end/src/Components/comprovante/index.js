import React from 'react';

import { Column } from 'primereact/column';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { DataTable } from 'primereact/datatable';

import { Dialog } from 'primereact/dialog';
import useGeneratePDF from '../../Hooks/gerarPDF';

const ComprovanteModal = ({ transacao, visible, onHide }) => {
  const { id, dataHora,nome, tipo, valor, conta, contaDestinatario } = transacao;

  const gerarPDF = useGeneratePDF()
  const cardStyle = {
    width: '30rem',
    padding: '1rem',
  };

  const rowStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    marginBottom: '1rem',
  };

  return (
    <Dialog
      header='Comprovante de Transação'
      visible={visible}
      style={{ width: '35rem' }}
      onHide={onHide}
    >
      <Card id='comprovante' style={cardStyle} header={"Comprovante"}>
        <div style={rowStyle}>
          <div>
            <h3>ID da Transação</h3>
            <p>{id}</p>
          </div>
          <div>
            <h3>Data e Hora</h3>
            <p>{new Date(dataHora).toLocaleString()}</p>
          </div>
        </div>
        <div style={rowStyle}>
          <div>
            <h3>Tipo</h3>
            <p>{tipo}</p>
          </div>
          <div>
            <h3>Valor</h3>
            <p>{`R$ ${valor.toFixed(2)}`}</p>
          </div>
          
        </div>
        <div style={rowStyle}>
          <div>
            <h3>Nome </h3>
            <p>{nome}</p>
          </div>
        
          
        </div>
        <h3>Conta </h3>
        <DataTable value={[conta]} style={{ marginBottom: '1rem' }}>
          <Column field='idConta' header='#' />
          <Column field='numeroConta' header='Número da Conta' />
          <Column field='tipoConta' header='Tipo de Conta' />
        </DataTable>
        {contaDestinatario && (
          <>
            {' '}
            <h3>Conta de Destino</h3>
            <DataTable value={[contaDestinatario]}>
              <Column field='idConta' header='#' />
              <Column field='numeroConta' header='Número da Conta' />
              <Column field='tipoConta' header='Tipo de Conta' />
            </DataTable>
          </>
        )}
        
      </Card><div
          style={{
            display: 'flex',
            justifyContent: 'flex-end',
            marginTop: '1rem',
          }}
        >
          <Button label='Baixar PDF' icon='pi pi-file-pdf' onClick={() => gerarPDF('comprovante', 'comprovante.pdf')} />
        </div>
    </Dialog>
  );
};

export default ComprovanteModal;
