import { useState, useEffect } from 'react';

export function useUsuario() {
    const [usuario, setUsuario] = useState(null);

    const recuperarUsuarioDoLocalStorage = () => {
        const usuarioString = localStorage.getItem('usuario');
        try {
            const usuarioObj = JSON.parse(usuarioString);
            if (usuarioObj && typeof usuarioObj === 'object') {
                setUsuario(usuarioObj);
                return usuarioObj;
            } else {
                console.error('O conteúdo recuperado não é um objeto válido.');
                return null;
            }
        } catch (error) {
            console.error('Erro ao parsear o usuário do localStorage:', error);
            return null;
        }
    };

    useEffect(() => {
        recuperarUsuarioDoLocalStorage();
    }, []);

    return usuario;
}
