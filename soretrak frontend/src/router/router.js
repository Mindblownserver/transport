import { createMemoryHistory, createRouter } from 'vue-router'

import App from '@/App.vue'
import TestView from '@/views/TestView.vue'

const routes = [
  { path: '/', component: App },
  { path: '/test', component: TestView },
]

const router = createRouter({
  history: createMemoryHistory(),
  routes,
})

export default {router};