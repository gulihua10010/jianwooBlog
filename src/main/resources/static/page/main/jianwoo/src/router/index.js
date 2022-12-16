import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/views/layout/Layout'
import Home from '@/views/Home'
import Detail from '@/views/Detail'
import NotFound from '@/views/404'
import {getJson} from "@/common/js/getJson";
import MessageBoard from "@/views/MessageBoard";

const routes = [
    {
        path: '/',
        name: 'main',
        component: Layout,
        redirect : '/index',
        metaInfo: {
            title: '简窝博客',
            meta: [
                {charset: 'utf-8'},
                {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
            ]
        },
        children:[
            {
                path: '/',
                name: 'index',
                component: Home,
                meta: {
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
                path: '/index',
                name: 'index',
                component: Home,
                meta: {
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
                component: Home,
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
                component: Detail,
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
                },
                meta: [
                    {title: '简窝博客'},
                    {charset: 'utf-8'},
                    {name: 'viewport', content: 'width=device-width, initial-scale=1, maximum-scale=1'},
                    {menuOid : process.env.VUE_APP_MENU_MESSAGE_BOARD_OID,},
                ]
            },
        ],

    },
    {
        path: "/:pathMath(.*)",
        component: () => NotFound
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})


router.beforeEach(async (to, from, next) => {

    let webConfig = localStorage.getItem('webConfig')
    let _json;
    if (webConfig) {
        _json = JSON.parse(webConfig)
        setConfig(_json)
    } else {
        getJson("/config/page/comm/query", {}).then((res) => {
            setConfig(res.data)
            let _jsonstr = JSON.stringify(res.data)
            localStorage.setItem("webConfig", _jsonstr);
            localStorage.setItem("title", res.data.title);
        })
    }

    next();
})

var setConfig = function (config) {
    window.document.title = config.title
    let head = document.getElementsByTagName('head');
    let meta_keyword = document.createElement('meta');
    if (document.querySelector('meta[name="keywords"]')) {
        document.querySelector('meta[name="keywords"]').setAttribute('content', config.keywords)
    } else {
        meta_keyword.setAttribute('name', 'keywords')
        meta_keyword.setAttribute('content', config.keywords)
        head[0].appendChild(meta_keyword)
    }
    let meta_description = document.createElement('meta');
    if (document.querySelector('meta[name="description"]')) {
        document.querySelector('meta[name="description"]').setAttribute('content', config.desc)
    } else {
        meta_description.setAttribute('name', 'description')
        meta_description.setAttribute('content', config.desc)
        head[0].appendChild(meta_description)
    }
    if (document.querySelector('meta[name="author"]')) {
        document.querySelector('meta[name="author"]').setAttribute('content', config.author)
    } else {
        meta_description.setAttribute('name', 'author')
        meta_description.setAttribute('content', config.author)
        head[0].appendChild(meta_description)
    }
}

async function getTerminalType(){
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        return  'mobile';
    } else if (/(Android)/i.test(navigator.userAgent)) {
        return  'mobile';
    } else {
        return 'pc';
    }
}

export default router
