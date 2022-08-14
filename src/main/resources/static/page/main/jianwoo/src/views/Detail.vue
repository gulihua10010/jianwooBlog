<template>
    <div>
        <div class="topimg" style="min-width: 1250px;" v-if="topImg !== '' && topImg !== undefined">
            <img :src=" topImg " style="width: 100%;height: 200px;">

        </div>
        <div class="menu">
            <Menu />
        </div>
        <div class="main">
            <div class="main-content">
                <ArticleDetail ref="articleDetail"/>
                <div class="usermodel">
                    <RightModel />
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            <Footer/>
        </div>
    </div>
</template>


<script>
import Menu from '@/components/Menu.vue'
import Footer from '@/components/Footer.vue'
import ArticleDetail from '@/components/ArticleDetail.vue'
import RightModel from '@/components/RightModel.vue'
import {postJson} from "@/common/js/postJson";

export default {
    name: "Detail",
    components: {
        Menu,
        ArticleDetail,
        RightModel,
        Footer,
    },
    data() {
        return {
            topImg: '',
            topImgParam:{
                key : 'TOP_IMG',
            },
            searchForm: {
                keywords: '',
                tags: [],
                category1: '',
                category2: '',
            },
            msg:[],
            notifyPromise:Promise.resolve(),
        }
    },
    mounted() {

    },
    created() {
        this.getTopImg();
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
        search:function (param) {
            this.$refs['home'].search(param);


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

</style>