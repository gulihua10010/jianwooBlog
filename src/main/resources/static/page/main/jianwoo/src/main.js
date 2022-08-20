import '@/common/js/prohibitOpenConsole.js';
import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import './static/css/jw_main.css'
import './static/js/iconfont.js'
import 'highlight.js/styles/atom-one-dark.css'
import 'babel-polyfill'
import Es6Promise from 'es6-promise'
import MetaInfo from 'vue-meta-info'
import 'amfe-flexible/index.js'

const app = createApp(App)
installElementPlus(app)
app.use(store)
    .use(router)
    .mount('#app')



app.use(MetaInfo)

require('es6-promise').polyfill()
Es6Promise.polyfill()
