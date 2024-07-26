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
            const date = moment.utc(value).toDate();
            return moment(date).format("DD/MM/YYYY HH:mm:ss"); // Convert to JavaScript Date object using Moment.js
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
  getStations(){
    return this.instance.get("/api/stations")
  }
  /**
   * Get Trips
   * @param {Date} date - specify the date of the desired trips 
   */
  getTrips(date){
    return this.instance.get(`/api/trips/date/${date.getDate()}-${date.getMonth()+1}-${date.getFullYear()}`)
  }
  /**
   * Get Statistics of a given date
   * @param {Date} date - specify a date to provide statistics 
   */
  getStatisticsByDate(date){
    return this.instance.get(`api/trips/statistics/${date.getDate()}-${date.getMonth()+1}-${date.getFullYear()}`)
  }
  getVehicules(){
    return this.instance.get("/api/vehicules");
  }
  getAgents(){
    return this.instance.get("/api/agents")
  }
}
export default AxiosInstance;