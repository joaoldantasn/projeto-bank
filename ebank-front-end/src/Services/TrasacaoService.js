import axios from 'axios';

const headers = {
    'headers': {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
    }
}

class TrasacaoService {

     postDepositar(contaID, valor) {

        return axios.post('http://localhost:8080/api/transacao/depositar?contaId=' + contaID + '&valor=' + valor,null, {headers});
    }

    postSacar(contaID, valor) {

        return axios.post('http://localhost:8080/api/transacao/sacar?contaId=' + contaID + '&valor=' + valor,null, {headers});
    }

    postTransferir(contaOrigemId, contaDestinoId, valor) {

        return axios.post('http://localhost:8080/api/transacao/transferir?contaOrigemId=' + contaOrigemId + '&numeroConta=' + contaDestinoId + '&valor=' + valor,null,{headers})
    }

    getExtrato(id, startDate, endDate) {

        return axios.get('http://localhost:8080/api/conta/extrato/' + id + '?startDate=' + startDate + '&endDate=' + endDate, {headers}).then(response => {
            console.log('🚀 ~ TrasacaoService ~ returnaxios.get ~ response:', response.data)
            
            return response.data;
        })
        .catch(error => console.log(error));
    }

    postTransferirPix(contaOrigemId, chavePix, valor) {

        return axios.post('http://localhost:8080/api/transacao/transferir/pix?contaOrigemId=' + contaOrigemId + '&chavePix=' + chavePix + '&valor=' + valor,null, {headers});
    }

}

export default new TrasacaoService();