

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

//임시로 로그인한척
import { useSessionStore } from '@/stores/store'
//임시끝

const app = createApp(App)

app.use(createPinia())
app.use(router)

//임시로 로그인한척2
const sessionStore = useSessionStore();
sessionStore.setData('철수');
const data = sessionStore.loadSessionData();
//임시끝

app.mount('#app')
