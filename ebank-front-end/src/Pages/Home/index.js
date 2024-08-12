// import React, { useRef, useState, useEffect } from 'react';
// import { Button } from 'primereact/button';
// import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
// import logoImg from '../../assets/ebank.png';
// import { InputText } from 'primereact/inputtext';
// import { Card } from 'primereact/card';
// import { Password } from 'primereact/password';
// import AuthService from '../../Services/AuthService';
// import jwt_decode from "jwt-decode";

// export default function Home() {

//     const history = useHistory();
//     const [senha, setSenha] = useState('');
//     var estiloImg = { height: "40em" };
//     const [CPF, setCPF] = useState('');



//     async function submeter() {

//         const auth =
//         {
//             "cpf": CPF,
//             "senha": senha
//         }

//         AuthService.postLogin(auth)
//         //showSuccess();
//         setTimeout(() => {
//             var token = localStorage.getItem('token')
//             var decoded = jwt_decode(token);
//             const nome = decoded.nomeUsuario;
//             const idUsuario = decoded.idUsuario;
//             console.log(decoded.sub + 'sub')
//             console.log(decoded.exp + 'exp')
//             console.log(nome + ' usuario')
//             console.log(idUsuario + ' idUsuario')
//         }, 2000);
//         //history.push('/publica/QuemSomos')
//     }

//     return (

//         <div className="home-container p-d-flex p-grid p-flex-wrap p-justify-center p-align-center p-p-6" style={{ margin: 0, height: '100%', padding: 0 }}>
//             <div className="home-ladoEsquerdo" >
//                 <img src={logoImg} alt="logo" style={estiloImg} />
//             </div>

//             <div className="home-ladoDireito">

//                 <Card subTitle='CPF' >
//                     <InputText
//                         // className={nomeObrigatorio}
//                         style={{ width: '100%' }}
//                         value={CPF}
//                         onChange={(e) => setCPF(e.target.value)}
//                     />
//                 </Card>
//                 <Card subTitle='SENHA' >
//                     <div className="card flex justify-content-center">

//                         <Password value={senha}
//                             onChange={(e) => setSenha(e.target.value)} toggleMask />
//                     </div>
//                 </Card>
//                 <Card>
//                     <Button className="" label="LOGIN" onClick={submeter} />
//                 </Card>

//             </div>
//         </div>
//     );

// }