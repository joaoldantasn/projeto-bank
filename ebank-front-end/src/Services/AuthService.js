import axios from 'axios';

const token = localStorage.getItem('token');
const headers = {
  Authorization: `Bearer ${token}`,
  'Content-Type': 'application/json',
};
class AuthService {
  postLogin(auth) {
    return axios
      .post('http://localhost:8080/auth/login', auth)
      .then((response) => {
        const token = response.data.token;
        localStorage.setItem('token', token);
      })
      .catch((error) => console.log(error));
  }
  postNewUser(register) {
    return axios.post('http://localhost:8080/auth/register', register, {
      headers,
    });
  }
}

export default new AuthService();
