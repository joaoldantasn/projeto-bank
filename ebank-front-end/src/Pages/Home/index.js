import React, { useEffect, useState } from 'react';
import { TabPanel, TabView } from 'primereact/tabview';
import TabViewCliente from '../../Components/TabViewCliente';
import CadastroForm from '../NovoUsuario';
import AgenciaList from '../../Components/TabViewAllClientes';
import { useUsuario } from '../../Hooks/useUsuario';
import { Button } from 'primereact/button';


const Home = () => {

  const usuario = useUsuario();

  const [role, setRole] = useState(''); 
  // 'usuario' ou 'admin'
  useEffect(() => {
    setRole(usuario?.role);
  }, [usuario]);


  return (
    <div className='p-m-3'>
      <h1>Bem-vindo ao EBank</h1>
      <div className="p-d-flex p-jc-between p-mb-3">
                
                <Button label={role} disabled />
            </div>

      <TabView style={{marginTop: 10}}>
        {role === 'ADMIN' && (
          <TabPanel header='Transações'>
            <TabViewCliente />
          </TabPanel>
        )}
      
        {role === 'ADMIN' && (
          <TabPanel header='Agências e Contas'>
            <AgenciaList />
          </TabPanel>
        )}

        {role === 'USER' && (
          <TabPanel  header='Transações'>
            <TabViewCliente />
          </TabPanel>
        )}

        {role === 'ADMIN' && (
          <TabPanel header='Criar Usuário'>
            <CadastroForm />
          </TabPanel>
        )}
      </TabView>
    </div>
  );
};

export default Home;
