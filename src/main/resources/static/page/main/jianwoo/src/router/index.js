import {createRouter, createWebHashHistory} from 'vue-router'
import index from '@/views/index'
import detail from '@/views/Detail'
import NotFound from '@/views/404'
import {getJson} from "@/common/js/getJson";
import MessageBoard from "@/views/MessageBoard";

const routes = [
    {
        path: '/',
        name: 'main',
        component: index,
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        }
    },
    {
        path: '/index',
        name: 'index',
        component: index,
        meta:{
            title: '简窝博客',

        },
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        }
    },
    {
        path: '/home',
        name: 'home',
        component: index,
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        }
    },
    {
        path: '/detail',
        name: 'detail',
        component: detail,
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        }
    },
    {
        path: '/message/board',
        name: 'MessageBoard',
        component: MessageBoard,
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        }
    },
    {
        path: "/:catchAll(.*)",
        component: () => NotFound
    }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})


router.beforeEach((to, from, next) => {
    let webConfig = localStorage.getItem('webConfig')
    let _json ;
    if (webConfig)
    {
        _json = JSON.parse(webConfig)
        setConfig(_json)
    }
    else {
        getJson("/config/page/comm/query", {}).then((res) => {
            setConfig(res.data)
            let _jsonstr = JSON.stringify(res.data)
            localStorage.setItem("webConfig", _jsonstr);
        })
    }

    next()
})

var setConfig = function(config) {
    window.document.title = config.title
    let head = document.getElementsByTagName('head');
    let meta_keyword=document.createElement('meta');
    if(document.querySelector('meta[name="keywords"]')){
        document.querySelector('meta[name="keywords"]').setAttribute('content',config.keywords)
    }else{
        meta_keyword.setAttribute('name','keywords')
        meta_keyword.setAttribute('content',config.keywords)
        head[0].appendChild(meta_keyword)
    }
    let meta_description=document.createElement('meta');
    if(document.querySelector('meta[name="description"]')){
        document.querySelector('meta[name="description"]').setAttribute('content', config.desc)
    }else{
        meta_description.setAttribute('name','description')
        meta_description.setAttribute('content', config.desc)
        head[0].appendChild(meta_description)
    }
    if(document.querySelector('meta[name="author"]')){
        document.querySelector('meta[name="author"]').setAttribute('content', config.author)
    }else{
        meta_description.setAttribute('name','author')
        meta_description.setAttribute('content', config.author)
        head[0].appendChild(meta_description)
    }
}

export default router
