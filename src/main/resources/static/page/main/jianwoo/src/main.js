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
// import './common/js/rem'
import './common/js/cursor'
import './common/js/canvas-nest.min.js'
const app = createApp(App)
installElementPlus(app)
app.use(store)
    .use(router)
    .mount('#app')

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(MetaInfo)

require('es6-promise').polyfill()
Es6Promise.polyfill()
