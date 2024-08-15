import React, { useState, useEffect, useRef } from 'react';
import { Card } from 'primereact/card';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { Toast } from 'primereact/toast';
import { TabView, TabPanel } from 'primereact/tabview';
import PainelDeposito from '../PainelDeposito';
import PainelTransferencia from '../PainelTransferencia';
import PainelSaque from '../PainelSaque';
import PainelDataExtrato from '../PainelDataExtrato';
import AppToolbar from '../AppToolbar';
import SaldoToolbar from '../SaldoToolbar';
import NomeToolbar from '../NomeToolbar';
import PainelTransferenciaPix from '../PainelTransferenciaPix';

export default function TabViewCliente() {
    
    const toast = useRef(null);
    const [activeIndex, setActiveIndex] = useState(0);

    return (
        <div>
            <Toast ref={toast} />
            <AppToolbar></AppToolbar>
            <NomeToolbar></NomeToolbar>
            <SaldoToolbar></SaldoToolbar>
            <Card>
                <div>
                    <TabView activeIndex={activeIndex} onTabChange={(e) => setActiveIndex(e.index)}>

                        <TabPanel header="SAQUE">
                            <PainelSaque></PainelSaque>
                        </TabPanel>
                        <TabPanel header="DEPOSITO">
                            <PainelDeposito></PainelDeposito>
                        </TabPanel>
                        <TabPanel header="EXTRATO">
                            <PainelDataExtrato></PainelDataExtrato>
                        </TabPanel>
                        <TabPanel header="TRANSFERÊNCIA">
                            <PainelTransferencia></PainelTransferencia>
                        </TabPanel>
                        <TabPanel header="PIX">
                            <PainelTransferenciaPix></PainelTransferenciaPix>
                        </TabPanel>

                    </TabView>
                </div>
            </Card>
        </div>
    );
}
