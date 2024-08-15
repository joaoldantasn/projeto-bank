import axios from 'axios';

class ContaService {

    getContas() {
        const token = localStorage.getItem('token');
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };

        return axios.get(`http://localhost:8080/api/conta`, { headers })
            .then(response => {
                return response.data;
            })
            .catch(error => console.log(error));
    }

    getById(id) {
        const token = localStorage.getItem('token');
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };

        return axios.get(`http://localhost:8080/api/conta/${id}`, { headers })
            .then(response => {
                return response.data;
            })
            .catch(error => console.log(error));
    }

    getContaByChavePix(chave) {
        const token = localStorage.getItem('token');
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };

        return axios.get(`http://localhost:8080/api/conta/pix?chave=${chave}`, { headers })
            .then(response => {
                console.log('🚀 ~ ContaService ~ getContaByChavePix ~ response:', response.data)
                return response.data;   
            })
         
            .catch(error => console.log(error));
    }

}

export default new ContaService();
