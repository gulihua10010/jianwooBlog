<template>
    <div>
        <div class="topimg" style="min-width: 1250px;" v-if="topImg !== '' && topImg !== undefined">
            <img :src=" topImg " style="width: 100%;height: 200px;">

        </div>
        <div class="menu">
            <Menu/>
        </div>
        <div class="main">
            <div class="main-content">
                <Home ref="home" :searchParams="searchForm"/>
                <div class="usermodel">
                    <RightModel/>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            <Footer/>
        </div>

        <el-dialog v-model="showAnnounce" title="公告" width="70%" height="60%">
            <div>
                <div class="art-det-info" id="art-det-info">
                    <div><span>{{ announce.title }}</span></div>
                    <div>

                            <span class="art-author">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zuozhe"></use>
                                </svg>:{{ announce.pushBy }}
                            </span>
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
    </div>
</template>

<script>
import Menu from '@/components/Menu.vue'
import Home from '@/components/Home.vue'
import Footer from '@/components/Footer.vue'
import RightModel from '@/components/RightModel.vue'
import {postJson} from "@/common/js/postJson";

export default {
    name: "index",
    components: {
        Menu,
        Home,
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
            msg:[],
            notifyPromise:Promise.resolve(),
        }
    },
    mounted() {

    },
    created() {
        this.getTopImg();
        this.getAnnounce();
        var that = this;
        that.queryMsgTimer();
        var timer = setInterval(function () {
            that.queryMsgTimer();
        }, 10000);

        var hiddenProperty = 'hidden' in document ? 'hidden' :
                'webkitHidden' in document ? 'webkitHidden' :
                        'mozHidden' in document ? 'mozHidden' :
                                null;
        var visibilityChangeEvent = hiddenProperty.replace(/hidden/i, 'visibilitychange');
        var onVisibilityChange = function(){
            if (!document[hiddenProperty]) {
                timer = setInterval(function () {
                    that.queryMsgTimer();
                }, 10000);
            }else{
                clearInterval(timer)
            }
        }
        document.addEventListener(visibilityChangeEvent, onVisibilityChange);
    },
    methods: {
        getTopImg: function () {
            postJson("/config/query", this.topImgParam).then((res) => {
                this.topImg = res.data.value;

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
                if (res.data && res.data.length > 0){
                    this.msg= res.data;
                    var that = this;
                    for (let i = 0;i < this.msg.length; i++) {
                        this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(()=>{
                            this.$notify({
                                title: this.msg[i].title,
                                message: this.msg[i].content,
                                'on-click':function () {
                                    window.open(that.msg[i].link,'_blank')

                                }
                            })
                        });

                    }
                }

            });
        }
    },
    watch: {}
}
</script>


<style scoped>
.dialog-footer .primary {
    margin-left: auto;
    background-color: rgba(166, 10, 169, .98);
    border: none;
    color: white;
    padding: 0.5rem 1.5rem;
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