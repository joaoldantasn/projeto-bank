import React, { useState, useRef } from 'react';
import { InputText } from 'primereact/inputtext';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import AppToolbar from '../../Components/AppToolbar';
import BotaoVoltar from '../../Components/BotaoVoltar';

const CadastroForm = () => {
    const [cpf, setCpf] = useState('');
    const [nomeUsuario, setNomeUsuario] = useState('');
    const [telefone, setTelefone] = useState('');
    const [senha, setSenha] = useState('');
    const [role, setRole] = useState('USER');
    const [idAgencia, setIdAgencia] = useState('');
    const [endereco, setEndereco] = useState({
        cep: '',
        logradouro: '',
        cidade: '',
        bairro: '',
        numero: ''
    });
    const [conta, setConta] = useState({
        numeroConta: '',
        saldo: '',
        chavePix: '',
        tipoConta: ''
    });

    const toast = useRef(null);

    const handleChangeEndereco = (e, field) => {
        setEndereco({ ...endereco, [field]: e.target.value });
    };

    const handleChangeConta = (e, field) => {
        setConta({ ...conta, [field]: e.target.value });
    };

    const handleSubmit = () => {
        const usuario = {
            cpf,
            nomeUsuario,
            telefone,
            senha,
            role,
            idAgencia,
            endereco,
            contas: [conta]
        };

        // Exibe a mensagem de sucesso no Toast
        toast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Usuário criado!', life: 3000 });

        console.log('JSON Gerado:', JSON.stringify(usuario, null, 2));
    };

    return (
        <div>
            <AppToolbar></AppToolbar>
            <Card title="Cadastro de Usuário" style={{ width: '50%', margin: 'auto' }}>
                <Toast ref={toast} />
                <div className="p-field">
                    <label htmlFor="cpf">CPF</label>
                    <InputText
                        id="cpf"
                        style={{ width: '100%' }}
                        value={cpf}
                        onChange={(e) => setCpf(e.target.value)}
                    />
                </div>

                <div className="p-field">
                    <label htmlFor="nomeUsuario">Nome</label>
                    <InputText
                        id="nomeUsuario"
                        style={{ width: '100%' }}
                        value={nomeUsuario}
                        onChange={(e) => setNomeUsuario(e.target.value)}
                    />
                </div>

                <div className="p-field">
                    <label htmlFor="telefone">Telefone</label>
                    <InputText
                        id="telefone"
                        style={{ width: '100%' }}
                        value={telefone}
                        onChange={(e) => setTelefone(e.target.value)}
                    />
                </div>

                <div className="p-field">
                    <label htmlFor="senha">Senha</label>
                    <InputText
                        id="senha"
                        type="password"
                        style={{ width: '100%' }}
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                    />
                </div>

                <div className="p-field">
                    <label htmlFor="role">Role</label>
                    <InputText
                        id="role"
                        style={{ width: '100%' }}
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                    />
                </div>

                <div className="p-field">
                    <label htmlFor="idAgencia">ID Agência</label>
                    <InputText
                        id="idAgencia"
                        style={{ width: '100%' }}
                        value={idAgencia}
                        onChange={(e) => setIdAgencia(e.target.value)}
                    />
                </div>

                <Card title="Endereço" className="p-mt-3">
                    <div className="p-field">
                        <label htmlFor="cep">CEP</label>
                        <InputText
                            id="cep"
                            style={{ width: '100%' }}
                            value={endereco.cep}
                            onChange={(e) => handleChangeEndereco(e, 'cep')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="logradouro">Logradouro</label>
                        <InputText
                            id="logradouro"
                            style={{ width: '100%' }}
                            value={endereco.logradouro}
                            onChange={(e) => handleChangeEndereco(e, 'logradouro')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="cidade">Cidade</label>
                        <InputText
                            id="cidade"
                            style={{ width: '100%' }}
                            value={endereco.cidade}
                            onChange={(e) => handleChangeEndereco(e, 'cidade')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="bairro">Bairro</label>
                        <InputText
                            id="bairro"
                            style={{ width: '100%' }}
                            value={endereco.bairro}
                            onChange={(e) => handleChangeEndereco(e, 'bairro')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="numero">Número</label>
                        <InputText
                            id="numero"
                            style={{ width: '100%' }}
                            value={endereco.numero}
                            onChange={(e) => handleChangeEndereco(e, 'numero')}
                        />
                    </div>
                </Card>

                <Card title="Conta" className="p-mt-3">
                    <div className="p-field">
                        <label htmlFor="numeroConta">Número da Conta</label>
                        <InputText
                            id="numeroConta"
                            style={{ width: '100%' }}
                            value={conta.numeroConta}
                            onChange={(e) => handleChangeConta(e, 'numeroConta')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="saldo">Saldo</label>
                        <InputText
                            id="saldo"
                            style={{ width: '100%' }}
                            value={conta.saldo}
                            onChange={(e) => handleChangeConta(e, 'saldo')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="chavePix">Chave Pix</label>
                        <InputText
                            id="chavePix"
                            style={{ width: '100%' }}
                            value={conta.chavePix}
                            onChange={(e) => handleChangeConta(e, 'chavePix')}
                        />
                    </div>

                    <div className="p-field">
                        <label htmlFor="tipoConta">Tipo de Conta</label>
                        <InputText
                            id="tipoConta"
                            style={{ width: '100%' }}
                            value={conta.tipoConta}
                            onChange={(e) => handleChangeConta(e, 'tipoConta')}
                        />
                    </div>
                </Card>
                <BotaoVoltar></BotaoVoltar>
                <Button label="SALVAR" onClick={handleSubmit} className="p-mt-3 p-ml-3" />
            </Card>
        </div>
    );
};

export default CadastroForm;
