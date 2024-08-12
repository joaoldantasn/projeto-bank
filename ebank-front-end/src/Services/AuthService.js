import axios from 'axios';

const headers = {
    'headers': {
        //'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
    }
}

class AuthService {

    postLogin(auth) {

        return axios.post('http://localhost:8080/auth/login', auth, headers)
            .then(Response => {
                const token = Response.data.token
                console.log(token)
                localStorage.setItem('token', token)
            })
            .catch(error => console.log(error))
    }

}

export default new AuthService();