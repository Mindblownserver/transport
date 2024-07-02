import { createApp } from 'vue'
import App from './App.vue'
import * as mobiscroll from '@mobiscroll/vue';
import router from "./router/router.js"
import '@mobiscroll/vue/dist/css/mobiscroll.min.css';


createApp(App).use(mobiscroll).use(router).mount('#app')
