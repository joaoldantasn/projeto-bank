import axios from 'axios';

class UsuarioService {

    getUsuarioByCPF(cpf) {
        const token = localStorage.getItem('token');
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };

        return axios.get(`http://localhost:8080/api/usuarios?cpf=` + cpf, { headers })

    }

    // getUsuarioById(id) {
    //     const token = localStorage.getItem('token');
    //     const headers = {
    //         'Authorization': `Bearer ${token}`,
    //         'Content-Type': 'application/json'
    //     };

    //     return axios.get(`http://localhost:8080/api/usuarios/` + id, { headers })
    //         .then(response => {
    //             return response.data;
    //         })
    //         .catch(error => console.log(error));
    // }

}

export default new UsuarioService();
