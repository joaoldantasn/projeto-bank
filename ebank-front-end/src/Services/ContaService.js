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

}

export default new ContaService();
