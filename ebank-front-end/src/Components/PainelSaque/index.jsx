import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';
import ComprovanteModal from '../comprovante';
import { useUsuario } from '../../Hooks/useUsuario';

export default function PainelSaque() {
  const usuario = useUsuario();

  const [comprovante, setComprovante] = useState(null);
  const [visible, setVisible] = useState(false);
  const [saque, setSaque] = useState(0);

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

  async function submeter() {
    if (saque > 0) {
      TrasacaoService.postSacar(usuario.idUsuario, saque)
        .then((response) => {
          setComprovante(response.data);
          console.log('🚀 ~ submeter ~ data:', response.data);
        })
        .catch((err) => {
          console.log(err.message);
        });
      showSuccess();
    } else {
      showError();
    }
  }

  return (
    <Card>
      <Toast ref={toast} />
      <Card subTitle='VALOR'>
        <InputText
          // className={nomeObrigatorio}
          style={{ width: '25%' }}
          value={saque}
          onChange={(e) => setSaque(e.target.value)}
        />
      </Card>
      {/* <Card subTitle='SENHA' >
                <div className="card flex justify-content-center">

                    <Password value={senha}
                        onChange={(e) => setSenha(e.target.value)} toggleMask />
                </div>
            </Card> */}
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
