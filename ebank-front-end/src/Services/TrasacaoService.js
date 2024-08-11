import axios from 'axios';

const headers = {
    'headers': {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
    }
}

class TrasacaoService {

    postDepositar(contaID, valor) {

        return axios.post('http://localhost:8080/api/transacao/depositar?contaId=' + contaID + '&valor=' + valor, headers);
    }

    postSacar(contaID, valor) {

        return axios.post('http://localhost:8080/api/transacao/sacar?contaId=' + contaID + '&valor=' + valor, headers);
    }

    postTransferir(contaOrigemId, contaDestinoId, valor) {

        return axios.post('http://localhost:8080/api/transacao/transferir?contaOrigemId=' + contaOrigemId + '&contaDestinoId=' + contaDestinoId + '&valor=' + valor, headers);
    }

    getExtrato(id, startDate, endDate) {

        return axios.get('http://localhost:8080/api/conta/extrato/' + id + '?startDate=' + startDate + '&endDate=' + endDate, headers);
    }

    postTransferirPix(contaOrigemId, chavePix, valor) {

        return axios.post('http://localhost:8080/api/transacao/transferir/pix?contaOrigemId=' + contaOrigemId + '&chavePix=' + chavePix + '&valor=' + valor, headers);
    }

}

export default new TrasacaoService();