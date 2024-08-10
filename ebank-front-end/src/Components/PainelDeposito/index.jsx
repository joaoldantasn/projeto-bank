import React, { useState } from "react";
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';

export default function PainelDeposito() {

    const [senha, setSenha] = useState('');

    return (
        <Card >
            <Card subTitle='VALOR' >
                <InputText
                    // className={nomeObrigatorio}
                    style={{ width: '25%' }}
                // value={nome}
                // onChange={(e) => setNome(e.target.value)}
                />
            </Card>
            <Card subTitle='SENHA' >
                <div className="card flex justify-content-center">

                    <Password value={senha}
                        onChange={(e) => setSenha(e.target.value)} toggleMask />
                </div>
            </Card>
            <Card>
                <Button className={''} label="ENVIAR" onClick={null} />
            </Card>

        </Card>

    );
}