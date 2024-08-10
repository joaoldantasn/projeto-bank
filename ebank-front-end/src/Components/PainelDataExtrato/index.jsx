
import React, { useState } from "react";
import { Calendar } from 'primereact/calendar';
import { Card } from "primereact/card";
import { Button } from "primereact/button";

export default function PainelDataExtrato() {
    const [date, setDate] = useState(null);

    return (
        <Card title='Escolha uma data' >
            <div style={{ width: '25%' }} className="card flex flex-wrap gap-3 p-fluid">
                <div className="flex-auto">
                    <label htmlFor="buttondisplay" className="font-bold block mb-2">
                    </label>
                    <Calendar id="buttondisplay" value={date} onChange={(e) => setDate(e.value)} showIcon />
                </div>

            </div>
            <Card>
                <Button className={''} label="ENVIAR" onClick={null} />
            </Card>
        </Card>
    )
}
