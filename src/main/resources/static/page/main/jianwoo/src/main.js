import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import './static/css/jw_main.css'
import './static/js/iconfont.js'
import hljs from 'highlight.js';
import '@/common/js/prohibitOpenConsole.js';
import {lineNumbersBlock, copyContent} from '@/common/js/highlight-line-number.js';
import 'highlight.js/styles/atom-one-dark.css'
import 'babel-polyfill'
import Es6Promise from 'es6-promise'
import MetaInfo from 'vue-meta-info'
import {ElMessage} from "element-plus";

const app = createApp(App)
installElementPlus(app)
app.use(store).use(router).mount('#app')

//按需加载代码高亮组件
hljs.registerLanguage('bash', require('highlight.js/lib/languages/bash'))
hljs.registerLanguage('java', require('highlight.js/lib/languages/java'))
hljs.registerLanguage('javascript', require('highlight.js/lib/languages/javascript'))
hljs.registerLanguage('python', require('highlight.js/lib/languages/python'))
hljs.registerLanguage('sql', require('highlight.js/lib/languages/sql'))
hljs.registerLanguage('html', require('highlight.js/lib/languages/xml'))
hljs.registerLanguage('css', require('highlight.js/lib/languages/css'))
hljs.registerLanguage('xml', require('highlight.js/lib/languages/xml'))
hljs.registerLanguage('c', require('highlight.js/lib/languages/c'))
hljs.registerLanguage('c++', require('highlight.js/lib/languages/cpp'))
hljs.registerLanguage('json', require('highlight.js/lib/languages/json'))
//富文本插件代码块的配置属性

app.config.globalProperties.languages=[
    {language: 'bash', label: 'Bash'},
    {language: 'java',label: 'Java'},
    {language: 'javascript',label: 'JavaScript'},
    {language: 'python', label: 'Python'},
    {language: 'sql', label: 'SQL'},
    {language: 'html',label: 'HTML'},
    {language: 'css',label: 'CSS'},
    {language: 'xml',label: 'XML'},
    { language: 'c', label: 'C' },
    {language: 'c++', label: 'C++'},
    {language: 'json',label: 'JSON'}
]

//创建v-highlight全局指令
app.directive('highlight', function (el) {
        let blocks = el.querySelectorAll('pre code');
    blocks.forEach((block)=>{
            if(block.getAttribute("highlighted")=="true"){
                return
            }
        block.setAttribute("highlighted","true")
            let code = block.innerHTML// block.innerHTML="<div><span style='margin-right: 10px;padding: 3px;border: #107ded solid 1px;color:#107ded;border-radius: 5px'>"+ "test"+"</span><button>复制</button>"+block.innerHTML+"</div>"
            hljs.highlightElement(block)
        lineNumbersBlock(block)
            block.innerHTML="<div><div style='padding: 5px 0px 10px 20px'><span style='margin-right: 10px;padding: 5px;border: rgba(16, 125, 237,0.5) solid 1px;color:#107ded;border-radius: 5px'>"+ '代码' +"</span><button class='el-button el-button--default el-button--mini' style='border-radius: 5px'>复制</button></div>"+block.innerHTML+"</div>"
            let copyButton = block.querySelector('button')
            if(copyButton!=null){
                copyButton.onclick = function (){
                    copyContent(code)
                    ElMessage({
                        showClose: true,
                        message: '复制成功!',
                        grouping: true,
                        type: 'success'
                    })
                }
            }
        })
})

app.use(MetaInfo)

require('es6-promise').polyfill()
Es6Promise.polyfill()
