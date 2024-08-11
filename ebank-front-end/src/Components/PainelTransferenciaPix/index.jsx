import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';

export default function PainelTransferenciaPix() {

    const [senha, setSenha] = useState('');
    const idContaInicio = 1;
    const [chavePix, setChavePix] = useState('');
    const [transferencia, setTransferencia] = useState('');

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
        TrasacaoService.postTransferirPix(idContaInicio, chavePix, transferencia);
        showSuccess();
    }

    return (
        <Card subTitle='TRANSFERENCIA' >
            <Toast ref={toast} />
            <Card subTitle='VALOR' >
                <InputText
                    // className={nomeObrigatorio}
                    style={{ width: '25%' }}
                    value={transferencia}
                    onChange={(e) => setTransferencia(e.target.value)}
                />
            </Card>

            <Card subTitle='CHAVE PIX' >
                <InputText
                    // className={nomeObrigatorio}
                    style={{ width: '25%' }}
                    value={chavePix}
                    onChange={(e) => setChavePix(e.target.value)}
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