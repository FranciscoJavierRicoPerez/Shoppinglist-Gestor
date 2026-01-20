import './Core/assets/main.css'
import 'primeicons/primeicons.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import PrimeVue from 'primevue/config'
import Aura from '@primevue/themes/aura'
import ToastService from 'primevue/toastservice'

import App from './Core/App.vue'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

import router from './Core/router'

import 'primeflex/primeflex.css'

const app = createApp(App)

app.use(PrimeVue, {
  theme: {
    preset: Aura,
  },
})
app.use(createPinia())
app.use(router)
app.use(ToastService)
app.mount('#app')
