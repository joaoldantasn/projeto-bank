import { DataTable } from 'primereact/datatable';

import { Column } from 'primereact/column';
import { Dialog } from 'primereact/dialog';

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';

import AgenciaService from '../../Services/AgenciaService.js';
import AppToolbar from '../AppToolbar/index.jsx';
import NomeToolbar from '../NomeToolbar/index.jsx';

const AgenciaList = () => {
  const [agencias, setAgencias] = useState([]);
  const [selectedAgencia, setSelectedAgencia] = useState(null);
  const [displayDialog, setDisplayDialog] = useState(false);

  useEffect(() => {
    async function fetchAgencias() {
      try {
        const response = await AgenciaService.getAllAgenciasComContas();

        setAgencias(response.data);
      } catch (error) {
        console.error('Erro ao buscar agências:', error);
      }
    }
    fetchAgencias();
  }, []);

  const handleRowClick = (agencia) => {
    setSelectedAgencia(agencia);
    setDisplayDialog(true);
  };

  const handleCloseDialog = () => {
    setDisplayDialog(false);
    setSelectedAgencia(null);
  };

  return (
    <div>
      <AppToolbar></AppToolbar>
      <NomeToolbar></NomeToolbar>

      <DataTable
        header={
          <div
            style={{ display: 'flex', justifyContent: 'center', marginTop: 50 }}
          >
            <h1>Agências {selectedAgencia?.codAgencia}</h1>
          </div>
        }
        value={agencias}
        onRowClick={(e) => handleRowClick(e.data)}
      >
        <Column field='idAgencia' header='ID da Agência'></Column>
        <Column field='codAgencia' header='Código da Agência'></Column>
        <Column field='telefone' header='Telefone'></Column>
        <Column field='endereco.logradouro' header='Endereço'></Column>
      </DataTable>

      <Dialog
        header={
          <div style={{ display: 'flex', justifyContent: 'center' }}>
            <h2>Contas da Agência {selectedAgencia?.codAgencia}</h2>
          </div>
        }
        visible={displayDialog}
        onHide={handleCloseDialog}
        style={{ width: '80vw' }}
        footer={
          <div>
            <Button
              label='Fechar'
              icon='pi pi-times'
              onClick={handleCloseDialog}
            />
          </div>
        }
      >
        {selectedAgencia && (
          <Card>
            <DataTable
              value={selectedAgencia.usuarios.flatMap((usuario) =>
                usuario.contas.map((conta) => ({
                  ...conta,
                  nomeUsuario: usuario.nomeUsuario,
                  codAgencia: selectedAgencia.codAgencia,
                }))
              )}
              paginator
              rows={10}
              rowsPerPageOptions={[5, 10, 25]}
              className='datatable-responsive'
            >
              <Column style={{ fontSize: '20px' }} field='idConta' header='#' />
              <Column
                style={{ fontSize: '20px' }}
                field='codAgencia'
                header='Código da Agência'
              />
              <Column
                style={{ fontSize: '20px' }}
                field='numeroConta'
                header='Número da Conta'
              />
              <Column
                style={{ fontSize: '20px' }}
                field='nomeUsuario'
                header='Nome'
              />
              <Column
                style={{ fontSize: '20px' }}
                field='tipoConta'
                header='Tipo de Conta'
              />

              <Column
                style={{ fontSize: '20px' }}
                field='ativa'
                header='Ativa?'
                body={(rowData) => (rowData.ativa ? 'Sim' : 'Não')}
              />
              <Column
                style={{ fontSize: '20px' }}
                field='saldo'
                header='Saldo Conta'
                body={(rowData) => `R$ ${rowData.saldo.toFixed(2)}`}
              />
            </DataTable>
          </Card>
        )}
      </Dialog>
    </div>
  );
};

export default AgenciaList;
