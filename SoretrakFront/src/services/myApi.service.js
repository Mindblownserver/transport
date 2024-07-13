import AxiosInstance from "@/utility/MyApiInstance";
const myApi = new AxiosInstance("http://localhost:8080/") // extraire url vers une variable .env
export default myApi;