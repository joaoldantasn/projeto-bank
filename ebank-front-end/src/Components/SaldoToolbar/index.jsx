import React, { useEffect, useState } from 'react';
import { Toolbar } from 'primereact/toolbar';

import { useUsuario } from '../../Hooks/useUsuario';
import ContaService from '../../Services/ContaService';

export default function SaldoToolbar() {
  const usuario = useUsuario();
  const [conta, setConta] = useState(null);

  useEffect(() => {
    // Função para buscar os dados da conta
    async function getConta() {
      if (usuario) {
        try {
          const response = await ContaService.getById(usuario.idUsuario);
          setConta(response);
          console.log('🚀 ~ submeter ~ data:', response);
        } catch (error) {
          console.error('Erro ao buscar conta:', error);
        }
      }
    }

    // Buscar dados da conta na montagem do componente e ao alterar o usuário
    getConta();

    // Atualizar os dados da conta periodicamente (a cada 10 segundos, por exemplo)
    const intervalId = setInterval(getConta, 3000);

    // Limpar o intervalo ao desmontar o componente
    return () => clearInterval(intervalId);
  }, [usuario]);

  // Definir os itens da esquerda na Toolbar
  const leftContents = (
    <React.Fragment>
      <h2 style={{ margin: '0 1rem 0 0' }}>Saldo</h2>
    </React.Fragment>
  );

  // Definir os itens da direita na Toolbar
  const rightContents = (
    <React.Fragment>
      <h2 style={{ margin: '0 1rem 0 0', color: 'var(--green-500)' }}>
        R$ {conta ? conta.saldo : 'Carregando...'}
      </h2>
    </React.Fragment>
  );

  return <Toolbar left={leftContents} right={rightContents} />;
}
