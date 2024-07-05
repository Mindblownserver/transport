import axios from 'axios';
import moment from 'moment';

class AxiosInstance {
  constructor(baseURL) {
    this.instance = axios.create({
      baseURL,
      transformResponse: [function (data) {
        // Parse JSON response with date reviver function
        return JSON.parse(data, (key, value) => {
          if (typeof value === 'string' && value.match(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.\d{3}[+-]\d{2}:\d{2}$/)) {
            return moment.utc(value).toDate(); // Convert to JavaScript Date object using Moment.js
          }
          return value;
        });
      }],
    });
  }
  getCentres(){
    return this.instance.get("/api/centre");
  }
  getDeleg(){
    return this.instance.get("/api/deleg")
  }
  getTypeLignes(){
    return this.instance.get("/api/ligne/type")
  }

  get(url, config) {
    return this.instance.get(url, config);
  }

  post(url, data, config) {
    return this.instance.post(url, data, config);
  }

  // You can add other HTTP methods (put, delete, etc.) similarly
  put(url, data, config) {
    return this.instance.put(url, data, config);
  }

  delete(url, config) {
    return this.instance.delete(url, config);
  }

  // Method to set new baseURL
  setBaseURL(newBaseURL) {
    this.instance.defaults.baseURL = newBaseURL;
  }

  // Method to add headers
  addHeader(key, value) {
    this.instance.defaults.headers.common[key] = value;
  }
}
export default AxiosInstance;