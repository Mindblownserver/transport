import axios from 'axios';
import authHeader from './auth-header';

const API_URL = process.env.VUE_APP_API_BASE_URL || 'http://localhost:8081/';
const BASE_URL = process.env.VUE_APP_BASE_URL || 'http://localhost:8081/';

export default {

  async login(user) {
    if (!user.username || !user.password) {
      console.error('Username or password is missing');
      throw new Error('Username and password are required');
    }

    console.log('Attempting to login with:', user);

    try {
      const response = await axios.post(API_URL + 'auth/login', {
        username: user.username,
        password: user.password
      }, 
      {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        }
      });

      console.log('Login response:', response.data);

      if (response.data.accessToken) {
        localStorage.setItem('user_free', JSON.stringify(response.data));
        
      } else {
        console.warn('No access token in response');
      }

      return response.data;
    } catch (error) {
      console.error('Login error:', error.response || error.message);
      throw new Error(error.response?.data?.message || 'Login failed');
    }
  },

  async logout() {
    try {
      const response = await axios.post(API_URL + "logout", {}, { headers: authHeader() });
      console.log('Logout response:', response.data);
      localStorage.removeItem('user_free');
    } catch (error) {
      console.error('Logout error:', error.response || error.message);
      throw new Error(error.response?.data?.message || 'Logout failed');
    }
  },

  async register(user) {
    if (!user.username || !user.password || !user.confirmPassword || !user.name) {
      console.error('Missing registration details');
      throw new Error('All registration fields are required');
    }

    console.log('Attempting to register with:', user);

    try {
      const response = await axios.post(API_URL + 'register', {
        name: user.name,
        username: user.username,
        password: user.password,
        password_confirmation: user.confirmPassword
      }, {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        }
      });

      console.log('Register response:', response.data);

      if (response.data.accessToken) {
        localStorage.setItem('user_free', JSON.stringify(response.data.accessToken));
      }

      return response.data;
    } catch (error) {
      console.error('Register error:', error.response || error.message);
      throw new Error(error.response?.data?.message || 'Registration failed');
    }
  },

  async passwordForgot(userEmail) {
    if (!userEmail) {
      console.error('Email is missing');
      throw new Error('Email is required');
    }

    console.log('Attempting password forgot with:', userEmail);

    try {
      const response = await axios.post(API_URL + 'password-forgot', {
        redirect_url: BASE_URL + 'password-reset',
        email: userEmail
      }, {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        }
      });

      console.log('Password forgot response:', response.status);
      return response.status;
    } catch (error) {
      console.error('Password forgot error:', error.response || error.message);
      throw new Error(error.response?.data?.message || 'Password forgot failed');
    }
  },

  async passwordReset(passwordDTO) {
    if (!passwordDTO.newPassword || !passwordDTO.confirmPassword || !passwordDTO.email || !passwordDTO.token) {
      console.error('Missing password reset details');
      throw new Error('All password reset fields are required');
    }

    console.log('Attempting password reset with:', passwordDTO);

    try {
      const response = await axios.post(API_URL + 'password-reset', {
        password: passwordDTO.newPassword,
        password_confirmation: passwordDTO.confirmPassword,
        email: passwordDTO.email,
        token: passwordDTO.token
      }, {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        }
      });

      console.log('Password reset response:', response.status);
      return response.status;
    } catch (error) {
      console.error('Password reset error:', error.response || error.message);
      throw new Error(error.response?.data?.message || 'Password reset failed');
    }
  }
};
