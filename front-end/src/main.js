import { createApp } from 'vue'
//import { createPinia } from 'pinia'
import router from './router'
import {reactive} from 'vue'

import usuario from './components/loadComponents/usuario'
import App from './App.vue'

const app = createApp(App)
//const pinia = createPinia()

//app.use(pinia)
app.use(router)
app.use(reactive)
app.use(usuario)





app.mount('#app')
