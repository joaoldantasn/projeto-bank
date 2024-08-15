
import React, { useRef, useState, useEffect } from 'react';
import { Calendar } from 'primereact/calendar';
import { Card } from "primereact/card";
import { Button } from "primereact/button";
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import TrasacaoService from '../../Services/TrasacaoService';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

export default function PainelDataExtrato() {
    const history = useHistory();
    const [dataInicio, setDataInicio] = useState(null);
    const [dataFinal, setDataFinal] = useState(null);
    const [senha, setSenha] = useState('');
    const id = 1
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
       TrasacaoService.getExtrato(id, dataInicio.toISOString(), dataFinal.toISOString())
        showSuccess();
        // fazer tymount e redirecionar para exibir extrato e um botão de download do mesmo.
    }

    return (
        <div>
            <Toast ref={toast} />
            <Card subTitle='ESCOLHA UMA DATA DE INICIO' >
                <div style={{ width: '25%' }} className="card flex flex-wrap gap-3 p-fluid">
                    <div className="flex-auto">
                        <label htmlFor="buttondisplay" className="font-bold block mb-2">
                        </label>
                        <Calendar id="buttondisplay" value={dataInicio} onChange={(e) => setDataInicio(e.value)} showIcon />
                    </div>

                </div>

            </Card>
            <Card subTitle='ESCOLHA UMA DATA DE FINAL' >
                <div style={{ width: '25%' }} className="card flex flex-wrap gap-3 p-fluid">
                    <div className="flex-auto">
                        <label htmlFor="buttondisplay" className="font-bold block mb-2">
                        </label>
                        <Calendar id="buttondisplay" value={dataFinal} onChange={(e) => setDataFinal(e.value)} showIcon />
                    </div>

                </div>
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
        </div>
    )
}
