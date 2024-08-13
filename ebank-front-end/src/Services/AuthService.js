import axios from 'axios';

class AuthService {
    postLogin(auth) {
        return axios.post('http://localhost:8080/auth/login', auth)
            .then(response => {
                const token = response.data.token;
                localStorage.setItem('token', token);
            })
            .catch(error => console.log(error));
    }

}

export default new AuthService();
