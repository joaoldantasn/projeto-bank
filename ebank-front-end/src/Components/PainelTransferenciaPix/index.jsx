import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';
import ComprovanteModal from '../comprovante';
import useGeneratePDF from '../../Hooks/gerarPDF';
import ContaService from '../../Services/ContaService';
import { useUsuario } from '../../Hooks/useUsuario';
export default function PainelTransferenciaPix() {
  const usuario = useUsuario();
  const [senha, setSenha] = useState('');
  const [conta, setConta] = useState(null);
  const [chavePix, setChavePix] = useState('');
  const [transferencia, setTransferencia] = useState(0);
  const [comprovante, setComprovante] = useState(null);
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    console.log();
  });

  const toast = useRef(null);
  const showSuccess = () => {
    toast.current.show({
      severity: 'success',
      summary: 'Enviado com Sucesso!',
      detail: 'Obrigado',
      life: 5000,
    });
  };

  const showError = () => {
    toast.current.show({
      severity: 'error',
      summary: 'Valor invalido!',
      detail: 'Erro',
      life: 5000,
    });
  };

  async function checkChavePix() {
    await ContaService.getContaByChavePix(chavePix)
      .then((response) => {
        setConta(response);
        console.log(response);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  async function submeter() {
    if (transferencia > 0) {
      if (conta != null && chavePix !== '') {
        TrasacaoService.postTransferirPix(
          usuario.idUsuario,
          chavePix,
          transferencia
        ).then((response) => {
          setComprovante(response.data);
          console.log('🚀 ~ submeter ~ data:', response.data);
        });
        showSuccess();
      }
    } else {
      showError();
    }
  }
  const rowStyle = {
    width: '30%',
    display: 'flex',
    justifyContent: 'space-between',
    marginBottom: '1rem',
  };

  return (
    <Card subTitle='TRANSFERENCIA'>
      <Toast ref={toast} />
      <Card subTitle='VALOR'>
        <InputText
          // className={nomeObrigatorio}
          style={{ width: '25%' }}
          value={transferencia}
          onChange={(e) => setTransferencia(e.target.value)}
        />
      </Card>

      <Card subTitle='CHAVE PIX'>
        <InputText
          // className={nomeObrigatorio}
          style={{ width: '25%' }}
          value={chavePix}
          onChange={(e) => setChavePix(e.target.value)}
        />
        <Button label='Verificar chave' onClick={checkChavePix} />
      </Card>

      {conta != null ? (
        <Card subTitle='Transferir para'>
          <div style={rowStyle}>
            <div>
              <h3>Conta</h3>
              <p>{conta.numeroConta}</p>
            </div>
            <div>
              <h3>Tipo Conta</h3>
              <p>{conta.tipoConta}</p>
            </div>
          </div>

          {/* <div className='card flex justify-content-center'>
          <Password
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            toggleMask
          />
        </div> */}
        </Card>
      ) : null}
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
