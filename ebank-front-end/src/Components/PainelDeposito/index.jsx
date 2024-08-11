import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';

export default function PainelDeposito() {

    const [deposito, setDeposito] = useState('');
    const [senha, setSenha] = useState('');

    const toast = useRef(null);
    const showSuccess = () => {
        toast.current.show({
            severity: 'success',
            summary: 'Enviado com Sucesso!',
            detail: 'Obrigado',
            life: 5000
        });
    }

    async function submeter() {
        const contaID = 1
        TrasacaoService.postDepositar(contaID, deposito)
        showSuccess();
    }



    return (

        <Card >
            <Toast ref={toast} />
            <Card subTitle='VALOR' >
                <InputText
                    // className={nomeObrigatorio}
                    style={{ width: '25%' }}
                    value={deposito}
                    onChange={(e) => setDeposito(e.target.value)}
                />
            </Card>
            <Card subTitle='SENHA' >
                <div className="card flex justify-content-center">

                    <Password value={senha}
                        onChange={(e) => setSenha(e.target.value)} toggleMask />
                </div>
            </Card>
            <Card>
                <Button className={''} label="ENVIAR" onClick={submeter} />
            </Card>

        </Card>

    );
}