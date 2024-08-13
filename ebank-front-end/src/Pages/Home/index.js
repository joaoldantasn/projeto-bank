import React, { useRef, useState, useEffect } from 'react';
import { Button } from 'primereact/button';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import logoImg from '../../assets/ebank.png';
import { InputText } from 'primereact/inputtext';
import { Card } from 'primereact/card';
import { Password } from 'primereact/password';
import AuthService from '../../Services/AuthService';
import UsuarioService from '../../Services/UsuarioService';

export default function Home() {
    const history = useHistory();
    const [senha, setSenha] = useState('');
    const [CPF, setCPF] = useState('');
    const [usuario, setUsuario] = useState();

    const retornaUsuario = async () => {
        try {
            const response = await UsuarioService.getUsuarioByCPF(CPF);
            setUsuario(response.data);
            salvarUsuarioNoLocalStorage(usuario);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        retornaUsuario();
    }, [CPF]);

    const salvarUsuarioNoLocalStorage = (usuario) => {
        if (usuario) {
            localStorage.setItem('usuario', JSON.stringify(usuario));
        } else {
            console.error('Usuário inválido. Não foi possível salvar no localStorage.');
        }
    };

    async function submeter() {
        const auth = {
            "cpf": CPF,
            "senha": senha
        };

        try {
            await AuthService.postLogin(auth);
        } catch (error) {
            console.error(error);
        }
        retornaUsuario();

        if (usuario.role == "ADMIN") {
            history.push('/admin')
        }
        if (usuario.role == "USER") {
            history.push('/user')
        }
    }

    return (
        <div className="home-container p-d-flex p-grid p-flex-wrap p-justify-center p-align-center p-p-6" style={{ margin: 0, height: '100%', padding: 0 }}>
            <div className="home-ladoEsquerdo">
                <img src={logoImg} alt="logo" style={{ height: "40em" }} />
            </div>
            <div className="home-ladoDireito">
                <Card subTitle='CPF'>
                    <InputText
                        style={{ width: '100%' }}
                        value={CPF}
                        onChange={(e) => setCPF(e.target.value)}
                    />
                </Card>
                <Card subTitle='SENHA'>
                    <div className="card flex justify-content-center">
                        <Password value={senha} onChange={(e) => setSenha(e.target.value)} toggleMask />
                    </div>
                </Card>
                <Card>
                    <Button label="LOGIN" onClick={submeter} />
                </Card>
            </div>
        </div>
    );
}
