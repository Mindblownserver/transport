import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import MaterialDashboard from "./material-dashboard";
import PrimeVue from 'primevue/config';
import {
    MbscPopup,
    MbscSelect, 
    MbscEventcalendar,
    MbscCalendarNav,
    setOptions,
    MbscDatepicker,
    MbscInput,
    MbscSegmented,
    MbscSegmentedGroup,
    formatDate
} from '@mobiscroll/vue';
import '@mobiscroll/vue/dist/css/mobiscroll.min.css';
import Tooltip from "primevue/tooltip";
import Aura from '@primevue/themes/aura';

const appInstance = createApp(App);
appInstance.directive("tooltip",Tooltip)
appInstance.use(MbscPopup)
appInstance.use(MbscSelect)
appInstance.use(MbscEventcalendar)
appInstance.use(MbscCalendarNav)
appInstance.use(setOptions)
appInstance.use(MbscInput)
appInstance.use(MbscDatepicker)
appInstance.use(MbscSegmented)
appInstance.use(MbscSegmentedGroup)
appInstance.use(formatDate)
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
appInstance.mount("#app");
