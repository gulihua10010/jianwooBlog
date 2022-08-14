<template>
    <div>
        <div class="index-position" v-if="this.nav.navTags.length > 0 || this.nav.breakNav1.name !== ''">
        <span>当前位置:<a href="/index">首页>></a>
            <span v-if="this.nav.navTags.length > 0">
                标签>> <span v-for="(item,idx) in this.nav.navTags">
                <a :href="item.url">{{ item.name }}</a>
                      </span>
            </span>
            <span v-if="this.nav.breakNav1.name !== ''">
                <a :href="this.nav.breakNav1.url">{{ this.nav.breakNav1.name }}</a>  >>
            </span>
            <span v-if="this.nav.breakNav2.name !== ''">
                <a :href="this.nav.breakNav2.url">{{ this.nav.breakNav2.name }}</a>  >>
            </span>
            <span v-if="this.nav.breakNav3.name !== ''">
                <a :href="this.nav.breakNav3.url">{{ this.nav.breakNav3.name }}</a>  >>
            </span>
        </span>
        </div>
        <div class="index-main">

            <div class="article-list">
                <div class="article-breviary" v-for="(item,idx) in this.articleList">
                    <div class="art-bre-tit">
                        <span class="art-top" v-if="item.topPlaceFlag === true">置顶</span>
                        <span class="art-type" v-if="item.category">{{ item.category }}</span>
                        <span class="art-password" v-if="item.permission === '11'">需要密码</span>
                        <a class="art-title" :href="'/#/detail?id=' + item.oid" @mousemove="mousemove($event,item.oid)"
                           @mouseleave="mouseleave($event,item.oid)"> {{ item.title }}</a>
                        <span v-if="showTitleAlt === item.oid"
                              :style="'color:red;left:' + titleAltLeft + 'px;top:' + titleAltTop + 'px;'"
                              class="tit_alt">{{ item.title }}</span>
                    </div>
                    <div class="art-bre-top">
                        <div class="art-bre-left"
                             @click="jump(item.oid)">
                            <el-image :src="item.imgSrc ? item.imgSrc : 'https://cdn.jianwoo.cn/static%2Fjianwoo%2Fjw.jpg'" :alt="item.title" lazy>
                                <template #error>
                                    <div class="image-slot">
                                       <img src="https://cdn.jianwoo.cn/static%2Fjianwoo%2Fjw.jpg" :alt="item.title">
                                    </div>
                                </template>
                            </el-image>
                            <div></div>
                        </div>
                        <div class="art-bre-right">
                            <span class="article-content-bre">{{ item.desc }}</span>
                        </div>
                    </div>
                    <div class="art-bre-info">
                        <div>
                            <span class="art-author">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zuozhe"></use>
                                </svg>{{ item.author }}
                            </span>
                            <span class="art-date">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-24gl-calendar"></use>
                                </svg>&nbsp;{{ item.publishTimeDesc }}
                            </span>
                            <span class="art-readers">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-yuedu1"></use>
                                </svg>&nbsp;{{ item.readCount }}</span>
                            <a class="art-comment"
                               :href="'/#/detail?id=' + item.oid + '&jump=comment'">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-pinglunxiao"></use>
                                </svg>&nbsp;{{ item.commentCount }}条评论</a>
                            &nbsp;
                            <span class="art-goods " style="color: red"  @click="hasPraise" v-if="item.isPraise">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-dianzan_kuai"></use>
                                </svg>&nbsp;{{ item.praiseCount }}
                            </span>
                            <span class="art-goods before-goods-add" @click="doPraise(item)" v-else>
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zan"></use>
                                </svg>&nbsp;{{ item.praiseCount }}
                            </span>
                            <span style="display: none" class="artid">{{ item.oid }}</span>

                        </div>
                        <div><a :href="'/#/detail?id=' + item.oid">阅读全文>></a></div>
                    </div>
                </div>
                <div style="text-align: center;">
                    <el-pagination
                            v-if="paginationShow"
                            @current-change="handleCurrentChange"
                            v-model:current-page="searchForm.page"
                            :page-size="searchForm.limit"
                            background
                            layout="prev, pager, next"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";

export default {
    name: "home",
    data() {
        return {
            nav: {
                breakNav1: {
                    name: '',
                    url: '',
                },
                breakNav2: {
                    name: '',
                    url: '',
                },
                breakNav3: {
                    name: '',
                    url: '',
                },
                navTags: [],
            },
            searchForm: {
                keywords: '',
                page: 1,
                limit: 20,
                tags: [],
                category1: '',
                category2: '',
            },
            articleList: [],
            total: 0,
            titleAltLeft: 0,
            titleAltTop: 0,
            showTitleAlt: 0,
            paginationShow: true,

        }
    },
    props: [
        'searchParams',
    ],
    mounted() {

    },
    created() {

        // if (this.searchParams && this.searchParams.keywords) {
        //     this.searchForm.keywords = this.searchParams.keywords;
        // }
        // if (this.searchParams && this.searchParams.category1) {
        //     this.searchForm.category1 = this.searchParams.category1;
        // }
        // if (this.searchParams && this.searchParams.category2) {
        //     this.searchForm.category2 = this.searchParams.category2;
        // }
        this.initQueryParams();
        this.getList();






    },
    methods: {
        getList: function () {
            this.paginationShow = false;
            postJson("/article/query/page/list", this.searchForm).then((res) => {
                this.paginationShow = true;
                this.articleList = res.data || [];
                this.total = res.count;
                if (res.conditions && res.conditions.length > 0) {
                    this.nav.navTags = [];
                    for (let i = 0; i < res.conditions.length; i++) {
                        var tag = {};
                        tag.name = res.conditions[i].condition
                        tag.url = "/#/index?tag=" + res.conditions[i].conditionId;
                        this.nav.navTags.push(tag)
                    }
                } else {
                    if (res.condition1) {
                        this.nav.breakNav1.name = res.condition1.condition;
                        let key = '';
                        let value = res.condition1.conditionId;
                        if (res.condition1.conditionType === 'CATEGORY1') {
                            key = 'category1';
                        } else if (res.condition1.conditionType === 'KEYWORDS') {
                            key = 'keywords';
                            value = '';
                            if (res.condition1.condition){
                                value = res.condition1.condition.replace('搜索结果: ','');
                            }
                        }
                        this.nav.breakNav1.url = "/#/index?" + key + "=" + value;
                    }
                    if (res.condition2) {
                        this.nav.breakNav2.name = res.condition2.condition;
                        let key = '';
                        if (res.condition2.conditionType === 'CATEGORY2') {
                            key = 'category2';
                        }
                        this.nav.breakNav2.url = "/#/index?" + key + "=" + res.condition2.conditionId;
                    }
                }


            });
        },
        handleCurrentChange: function () {
            var query = this.$route.query;
            var newQuery = {};
            for (let queryKey in query) {
                if (query[queryKey] ) {
                    newQuery[queryKey] = query[queryKey];
                }
            }
            newQuery.page = this.searchForm.page;
            this.$router.push({
                path: '/index',
                query: newQuery
            }).then(() => {
                this.initQueryParams();
                this.getList();
            });


        },
        clearParams: function () {
            this.searchForm = {};
        },
        initQueryParams: function () {
            if (this.$route.query.keywords) {
                this.searchForm.keywords = this.$route.query.keywords;
            }
            if (this.$route.query.category1) {
                this.searchForm.category1 = this.$route.query.category1;
            }
            if (this.$route.query.category2) {
                this.searchForm.category2 = this.$route.query.category2;
            }

            if (this.$route.query.tag) {
                this.searchForm.tags = [];
                this.searchForm.tags.push(this.$route.query.tag);
            }
            if (this.$route.query.page) {
                this.searchForm.page = Number(this.$route.query.page);
            }
            if (this.$route.query.publishDate) {
                this.searchForm.publishDate = this.$route.query.publishDate;
            }
            this.searchForm.limit = 20;

        },
        search: function (params) {
            this.searchForm = params;
            this.searchForm.page = 1;
            this.getList();
        },
        doPraise: function (item) {
            postJson("/article/praise/add", {artOid: item.oid}).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '点赞成功!',
                        grouping: true,
                        type: 'success'
                    })
                    item.praiseCount = item.praiseCount + 1;
                    item.isPraise = true;
                }

            });
        },
        mousemove: function (e, oid) {
            //获取元素距离视口高度
            var top1 = e.currentTarget.getBoundingClientRect().top
            //获取滚动条位置
            var top2 = document.body.scrollTop || document.documentElement.scrollTop
            var titleAltTop = top1 + top2;
            var left1 = e.currentTarget.getBoundingClientRect().left
            var left2 = document.body.scrollTop || document.documentElement.scrollLeft
            var titleAltLeft = left1 + left2;
            var pagex = e.pageX;
            var pagey = e.pageY;
            // console.log(pagex, pagey);
            // console.log(titleAltLeft, titleAltTop);
            // console.log(pagex-titleAltLeft, pagey-titleAltTop);
            this.titleAltLeft = pagex - titleAltLeft;
            this.titleAltTop = pagey - titleAltTop;
            this.showTitleAlt = oid
        },
        mouseleave: function (e, oid) {
            this.showTitleAlt = 0
        },
        jump: function (oid) {
            window.location.href = '/#/detail?id=' + oid
        },
        hasPraise:function () {
            this.$message({
                showClose: true,
                message: '您已经赞过该文章啦!',
                grouping: true,
                type: 'warning'
            })
        }
    },
    watch: {
        '$route' (to, from) { //监听URL地址栏参数变化
            this.clearParams();
            this.initQueryParams();
            this.getList();
        }
    },
}
</script>

<style>
.el-pagination.is-background .el-pager li:not(.disabled) {
    background-color: #fff;
    color: #000;
    border: 1px solid #B40BB7;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #B40BB7 !important;
    color: #fff;
}

.el-pagination.is-background .el-pager li:not(.disabled):hover {
    background-color: #A52581;
    color: #fff;
}
</style>