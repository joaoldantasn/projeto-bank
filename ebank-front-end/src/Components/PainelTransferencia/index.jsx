import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';
import Comprovante from '../comprovante';
import ComprovanteModal from '../comprovante';

export default function PainelTransferencia() {
  const [senha, setSenha] = useState('');
  const idContaInicio = 1;
  const [numeroContaDestino, setNumeroContaDestino] = useState('');
  const [transferencia, setTransferencia] = useState('');
  const [comprovante, setComprovante] = useState(null);
  const [visible, setVisible] = useState(false);

  const toast = useRef(null);
  const showSuccess = () => {
    toast.current.show({
      severity: 'success',
      summary: 'Enviado com Sucesso!',
      detail: 'Obrigado',
      life: 5000,
    });
  };

  async function submeter() {
    const contaID = 1;
    TrasacaoService.postTransferir(
      idContaInicio,
      numeroContaDestino,
      transferencia
    ).then((response) => {
      setComprovante(response.data);
      console.log('🚀 ~ submeter ~ data:', response.data);
    });
    showSuccess();
  }

  return (
    <Card>
      <Toast ref={toast} />
      <Card subTitle='VALOR'>
        <InputText
          // className={nomeObrigatorio}
          style={{ width: '25%' }}
          value={transferencia}
          onChange={(e) => setTransferencia(e.target.value)}
        />
      </Card>

      <Card subTitle='CONTA DE DESTINO'>
        <InputText
          // className={nomeObrigatorio}
          style={{ width: '25%' }}
          value={numeroContaDestino}
          onChange={(e) => setNumeroContaDestino(e.target.value)}
        />
      </Card>

      <Card subTitle='SENHA'>
        <div className='card flex justify-content-center'>
          <Password
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            toggleMask
          />
        </div>
      </Card>
      <Card>
        <Button className={''} label='ENVIAR' onClick={submeter} />
      </Card>

      {comprovante && (
        <div>
          <Button
            label='Mostrar Comprovante'
            icon='pi pi-info-circle'
            onClick={() => setVisible(true)}
          />
          <ComprovanteModal
            transacao={comprovante}
            visible={visible}
            onHide={() => setVisible(false)}
          />
        </div>
      )}
    </Card>
  );
}
