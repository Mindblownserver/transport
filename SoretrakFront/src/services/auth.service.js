import axios from 'axios';
import authHeader from './auth-header';
import myApi from "@/services/myApi.service.js"

const API_URL = process.env.VUE_APP_API_BASE_URL
const BASE_URL = process.env.VUE_APP_BASE_URL

export default {

  async login(user) {
    var response = await axios.post(myApi + '/login', {
      username: user.username,
      password: user.password
    }, 
    {
      headers: {
        Accept: "application/vnd.api+json",
        "Content-Type": "application/vnd.api+json",
      }
    });
    if (response.data.access_token) {
      localStorage.setItem('user_free', JSON.stringify(response.data.access_token));
    }
    return response.data;
  },

  async logout() {
    await axios.post(myApi + "/logout", {}, { headers: authHeader() })
    localStorage.removeItem('user_free');
  },

  async register(user) {
    var response = await axios.post(myApi + '/register', {
      name: user.name,
      username: user.username,
      password: user.password,
      password_confirmation: user.confirmPassword
    });
    if (response.data.access_token) {
      localStorage.setItem('user_free', JSON.stringify(response.data.access_token));
    }
    return response.data;
  },

  async passwordForgot(userusername) {

    var response = await axios.post(API_URL + '/password-forgot', {
      redirect_url: BASE_URL + "/password-reset",
      username: userusername
    })
    return response.status;
  },

  async passwordReset(passwordDTO) {

    var response = await axios.post(API_URL + '/password-reset', {
      password: passwordDTO.newPassword,
      password_confirmation: passwordDTO.confirmPassword,
      username: passwordDTO.username,
      token: passwordDTO.token
    })
    return response.status;
  }
}