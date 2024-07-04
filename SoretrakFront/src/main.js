import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import MaterialDashboard from "./material-dashboard";
import PrimeVue from 'primevue/config';
import PrimeVueStyled from 'primevue/config';
// Import PrimeVue CSS files



const appInstance = createApp(App);
appInstance.use(store);
appInstance.use(router);
appInstance.use(MaterialDashboard);
appInstance.use(PrimeVue);
appInstance.use(PrimeVueStyled);
appInstance.mount("#app");
