<template>
    <div class="art-box">
        <div class="artb-title">
            <span :class="this.showTab === 1?'art-titlehover':''" @mousemove="mouseTabmove($event, 1)"
                  @mouseleave="mouseTableave($event)">最新文章</span>
            <span :class="this.showTab === 2?'art-titlehover':''" @mousemove="mouseTabmove($event, 2)"
                  @mouseleave="mouseTableave($event)">随机推荐</span>
            <span :class="this.showTab === 3?'art-titlehover':''" @mousemove="mouseTabmove($event, 3)"
                  @mouseleave="mouseTableave($event)">热门文章</span>
        </div>
        <div class="newest-art" v-if="this.showTab === 1">
            <ul>
                <li v-for="(item,idx) in this.newestList"><span
                        :class="idx===0 ? 'li-no-one' : (idx===1 ? 'li-no-two' : (idx===2 ? 'li-no-three' : 'li-no-othor'))">{{ idx + 1 }}</span><a
                        href="/detail" @mousemove="mousemove($event, item.oid)"  @mouseleave="mouseleave($event)">{{ substr(item.title, 18) }}</a>
                    <a :style="'position: absolute;left:' + titleAltLeft + 'px;top:'+titleAltTop+'px;'" class="tit_alt"
                       v-if="this.showTitleAlt === item.oid">{{ item.title }}</a>
                </li>
            </ul>
        </div>
        <div class="random-art" v-if="this.showTab === 2">
            <ul>
                <li v-for="(item,idx) in this.randomList"><span
                        :class="idx===0 ? 'li-no-one' : (idx===1 ? 'li-no-two' : (idx===2 ? 'li-no-three' : 'li-no-othor'))">{{ idx + 1 }}</span>
                    <a href="/detail" @mousemove="mousemove($event, item.oid)" @mouseleave="mouseleave($event)">{{ substr(item.title, 18) }}</a>
                    <a :style="'position: absolute;left:' + titleAltLeft + 'px;top:'+titleAltTop+'px;'" class="tit_alt"
                       v-if="this.showTitleAlt === item.oid">{{ item.title }}</a>
                </li>
            </ul>
        </div>
        <div class="hot-art" v-if="this.showTab === 3">
            <ul>
                <li v-for="(item,idx) in this.hotList"><span
                        :class="idx===0 ? 'li-no-one' : (idx===1 ? 'li-no-two' : (idx===2 ? 'li-no-three' : 'li-no-othor'))">{{ idx + 1 }}</span><a
                        href="/detail" @mousemove="mousemove($event, item.oid)" @mouseleave="mouseleave($event)">{{ substr(item.title, 18) }}</a>
                    <a class="tit_alt" v-if="this.showTitleAlt === item.oid">{{ item.title }}</a>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";

export default {
    name: "Recommend",
    data() {
        return {
            newestList: [],
            randomList: [],
            hotList: [],
            showTab: 1,
            showTitleAlt: 0,
            titleAltLeft: 0,
            titleAltTop: 0,
            inter: null,
        }
    },
    mounted() {

    },
    created() {
        this.getList();
        var that = this;
        that.inter = setInterval(function () {
            that.showTab = (that.showTab) % 3 + 1;
        }, 2000)
    },
    methods: {
        getList: function () {
            postJson("/article/query/recommend/list", {}).then((res) => {
                this.newestList = res.newestList || [];
                this.randomList = res.randomList || [];
                this.hotList = res.hotList || [];


            });
        },

        mousemove: function (e, oid) {
            var pagex = e.pageX;
            var pagey = e.pageY;
            // console.log(pagex, pagey);
            this.titleAltLeft = pagex;
            this.titleAltTop = pagey - 230;
            this.showTitleAlt = oid
        },
        mouseleave: function (e) {
            this.showTitleAlt = 0
        },
        mouseTabmove: function (e, tab) {
            clearInterval(this.inter);
            this.showTab = tab;

        },
        mouseTableave: function (e) {
            var that = this;
            that.inter = setInterval(function () {
                that.showTab = (that.showTab) % 3 + 1;
            }, 2000)

        },
    },
    setup() {
        function substr(v, len) {
            if (v.length > len) {
                return v.substr(0, len) + '...'
            } else {
                return v
            }
        }

        return {
            substr,
        }
    },
    watch: {}
}
</script>

<style scoped>

</style>