import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import './static/css/jw_main.css'
import './static/js/iconfont.js'

const app = createApp(App)
installElementPlus(app)
app.use(store).use(router).mount('#app')