<template>
    <div>
        <el-container v-if="width > minWidth"
                      style="display: none"
                      class="main-app"
                      @dragover="dragOver($event)">
            <el-header height="auto">
                <div class="topimg" v-if="topImg !== '' && topImg !== undefined">
                    <img :src=" topImg ">
                </div>
                <div class="menu">
                    <Menu/>
                </div>
            </el-header>
            <el-container style="justify-content: center" id="container-app">
                <el-main style="flex-grow: 0;overflow: hidden;">
                    <App></App>
                </el-main>
                <el-aside class="aside-right" style="overflow: hidden">
                    <RightModel/>
                </el-aside>
            </el-container>

            <el-footer>
                <Footer/>
            </el-footer>
        </el-container>
        <!--        适配手机设备-->
        <el-container v-else>
            <el-container style="justify-content: center">
                <el-aside class="mobile-menu">
                    <MenuMobile/>
                </el-aside>

                <el-main style="flex-grow: 0;overflow: hidden;word-break: break-word;">
                    <App></App>
                </el-main>
                <el-footer>
                    <Footer/>
                </el-footer>
            </el-container>
        </el-container>

        <el-dialog v-model="showAnnounce" custom-class="announce" title="公告" width="70%" height="60%">
            <div>
                <div class="art-det-info" id="art-det-info">
                    <div><strong>{{ announce.title }}</strong></div>
                    <div>

                            <span class="art-author">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zuozhe"></use>
                                </svg>:{{ announce.pushBy }}
                            </span>&nbsp;
                        <span class="art-date">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-24gl-calendar"></use>
                                </svg>:{{ announce.pushTimeDesc }}
                            </span>

                    </div>
                </div>
                <div class="art-txt" v-html="announce.content">
                </div>
            </div>
            <div class="announce-footer" v-for="(item, index) in pages">
                <span :class="'footer-page-no ' + (currentIndex === index ? ' page-active' : '')"
                      @click="showAnnounceInfo(index)">{{ item }} </span>
            </div>


            <template #footer>
                <span class="dialog-footer">
                    <el-button type="primary" class="primary" @click="notShow">不再提示</el-button>
                    <el-button @click="showAnnounce = false">取消</el-button>
                </span>
            </template>
        </el-dialog>
        <meting-js server="netease" type="playlist" id="7813663401" fixed="true" autoplay="true" loop="all" order="list"
                   preload="auto" list-folded="true" list-max-height="500px" lrc-type="1">
        </meting-js>
    </div>
</template>

<script>
import Menu from '@/components/Menu.vue'
import MenuMobile from '@/components/MenuMobile.vue'
import Footer from '@/components/Footer.vue'
import RightModel from '@/components/RightModel.vue'
import {postJson} from "@/common/js/postJson";
import App from '@/App.vue'
import router from "@/router";

export default {
    name: "layout",
    components: {
        Menu,
        MenuMobile,
        App,
        RightModel,
        Footer,
    },
    data() {
        return {
            topImg: '',
            topImgParam: {
                key: 'TOP_IMG',
            },
            searchForm: {
                keywords: '',
                tags: [],
                category1: '',
                category2: '',
            },
            showAnnounce: false,
            announceList: [],
            currentIndex: 0,
            totalAnnounce: 0,
            pages: [],
            announce: {},
            announceCache: '',
            msg: [],
            notifyPromise: Promise.resolve(),
            minWidth: 768,
            width: document.documentElement.clientWidth,
        }
    },
    mounted() {
        window.onresize = () => {
            return (() => {
                this.width = document.body.clientWidth
            })()

        }
        document.addEventListener("click", this.bodyCloseMenus);
    },
    created() {
        this.getTopImg();
        if (router.currentRoute.value.path === '/' || router.currentRoute.value.path === 'index' || router.currentRoute.value.path === 'home') {
            this.getAnnounce();
        }
        var that = this;
        that.queryMsgTimer();
        var timer = setInterval(function () {
            that.queryMsgTimer();
        }, 10000);

        this.$nextTick(function () {


            setTimeout(function () {
                window.L2Dwidget
                        .init({
                            model: {
                                jsonPath: 'https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json',
                            },
                            mobile: {"show": true, "scale": 0.5},
                            dialog: {
                                // 开启对话框
                                enable: true,
                                script: {
                                    // 每空闲 10 秒钟，显示一条一言
                                    'every idle 10s': '$hitokoto$',
                                    // 当触摸到星星图案
                                    'hover .topimg': '欢迎来到简窝博客~ (*/ω＼*)',
                                    // 当触摸到角色身体
                                    'tap body': '哎呀！别碰我！',
                                    // 当触摸到角色头部
                                    'tap face': '人家已经不是小孩子了！'
                                }
                            }
                        });
            }, 3000)
        })

        var hiddenProperty = 'hidden' in document ? 'hidden' :
                'webkitHidden' in document ? 'webkitHidden' :
                        'mozHidden' in document ? 'mozHidden' :
                                null;
        var visibilityChangeEvent = hiddenProperty.replace(/hidden/i, 'visibilitychange');
        var onVisibilityChange = function () {
            if (!document[hiddenProperty]) {
                timer = setInterval(function () {
                    that.queryMsgTimer();
                }, 10000);
            } else {
                clearInterval(timer)
            }
        }
        document.addEventListener(visibilityChangeEvent, onVisibilityChange);
    },
    methods: {
        getTopImg: function () {
            postJson("/config/query", this.topImgParam).then((res) => {
                this.topImg = res.data.value;
                this.$nextTick(function () {
                    var app = document.querySelector('#container-app');
                    if (app) {
                        var elTop = app.offsetTop
                        this.$store.commit("changeTop", elTop);
                    }

                })

            });
        },
        search: function (param) {
            this.$refs['home'].search(param);


        },
        getAnnounce: function () {
            postJson("/announce/query/useful/list", {}).then((res) => {
                if (res.data && res.data.length > 0) {
                    this.announceList = res.data;
                    this.announce = this.announceList[0];
                    this.totalAnnounce = this.announceList.length;

                    for (let i = 0; i < this.announceList.length; i++) {
                        if (this.totalAnnounce > 1) {
                            this.pages.push(i + 1)
                        }
                        if (i !== 0) {
                            this.announceCache = this.announceCache + '@'
                        }
                        this.announceCache = this.announceCache + this.announceList[i].oid;
                    }
                    let cache = sessionStorage.getItem('announceCache');
                    if (this.announceCache !== cache) {
                        this.showAnnounce = true;
                    }
                }
            })
        },
        notShow: function () {
            this.showAnnounce = false;
            sessionStorage.setItem("announceCache", this.announceCache);

        },
        showAnnounceInfo: function (i) {
            this.announce = this.announceList[i];
            this.currentIndex = i;
        },
        queryMsgTimer: function () {
            postJson("/msg/timer/newest/query/list", {}).then((res) => {
                if (res.data && res.data.length > 0) {
                    this.msg = res.data;
                    var that = this;
                    for (let i = 0; i < this.msg.length; i++) {
                        this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(() => {
                            this.$notify({
                                title: this.msg[i].title,
                                message: this.msg[i].content,
                                'on-click': function () {
                                    window.open(that.msg[i].link, '_blank')

                                }
                            })
                        });

                    }
                }

            });
        },
        //阻止回弹
        dragOver(e) {
            e.preventDefault();
        }
    },
    watch: {}
}
</script>


<style scoped>
.main-app {
    display: block !important;
}

.dialog-footer .primary {
    margin-left: auto;
    background-color: rgba(166, 10, 169, .98);
    border: none;
    color: white;
    padding: 0.5px 1.5px;
    border-radius: 8%;
    cursor: pointer;
}

.dialog-footer .primary:hover {
    background-color: #A52581;
    border-color: #A52581;
}

.dialog-footer .primary:active {
    background-color: #A52581;
    border-color: #A52581;
}

</style>

<style>
.announce .el-dialog__title {
    font-size: 24px !important;
}
</style>