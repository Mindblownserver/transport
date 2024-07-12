import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import MaterialDashboard from "./material-dashboard";
import PrimeVue from 'primevue/config';
import * as mobiscroll from '@mobiscroll/vue';
import '@mobiscroll/vue/dist/css/mobiscroll.min.css';
import Tooltip from "primevue/tooltip";
import Aura from '@primevue/themes/aura';

const appInstance = createApp(App);
appInstance.directive("tooltip",Tooltip)
appInstance.use(store);
appInstance.use(router);
appInstance.use(MaterialDashboard);
appInstance.use(PrimeVue,{
    theme: {
    preset: Aura,
    options:{
        darkModeSelector:'.dark-version',
        
        }
    }
});
appInstance.use(mobiscroll);
appInstance.mount("#app");
