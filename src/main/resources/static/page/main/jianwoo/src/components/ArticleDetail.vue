<template>
    <div>
        <div class="index-main">
            <div class="detail">
                <div class="position">
                    <span>当前位置:<a
                            href="/index">首页>></a>
                        <span v-if="article.parentCategoryOid">
                             <a :href="'#/index?category1=' + article.parentCategoryOid"
                             >{{ article.parentCategory }}</a>>>
                        </span>
                        <span v-if="article.categoryOid">
                            <a :href="'#/index?category2=' + article.categoryOid"
                            >{{ article.category }}</a>>>
                        </span>
                        {{ article.title }}
                    </span>
                </div>
                <el-card class="art-con lock-box" v-if="article.isLock">
                    <div class="lock-panel">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-suoding"></use>
                        </svg>
                    </div>
                    <div class="lock-con">
                        <div class="art-det-info" id="art-det-info">
                            <div><span>{{ article.title }}</span></div>
                            <div>

                            <span class="art-author">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zuozhe"></use>
                                </svg>:{{ article.author }}
                            </span>
                                <span class="art-date">
                                    <svg class="icon" aria-hidden="true">
                                        <use xlink:href="#icon-24gl-calendar"></use>
                                    </svg>:{{ article.publishTimeDesc }}
                                </span>
                                <span class="art-region">
                                    <svg class="icon" aria-hidden="true">
                                        <use xlink:href="#icon-position"></use>
                                    </svg>:{{ article.publishRegion }}
                                </span>
                                <span class="art-type">
                                    <svg class="icon" aria-hidden="true">
                                        <use xlink:href="#icon-leibie"></use>
                                    </svg>:{{ article.category }}
                                </span>
                                <span class="art-readers">
                                    <svg class="icon" aria-hidden="true">
                                        <use xlink:href="#icon-yuedu1"></use>
                                    </svg>:{{ article.readCount }}
                                </span>

                            </div>
                        </div>
                        <div class="art-txt">
                            {{ article.text }}
                        </div>
                    </div>

                </el-card>
                <el-card class="art-con" v-if="!article.isLock">
                    <div class="art-det-info" id="art-det-info">
                        <div><span>{{ article.title }}</span></div>
                        <div>

                            <span class="art-author">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zuozhe"></use>
                                </svg>:{{ article.author }}
                            </span>
                            <span class="art-date">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-24gl-calendar"></use>
                                </svg>:{{ article.publishTimeDesc }}
                            </span>
                            <span class="art-region">
                                    <svg class="icon" aria-hidden="true">
                                        <use xlink:href="#icon-position"></use>
                                    </svg>:{{ !article.publishRegion ? '未知' : article.publishRegion }}
                                </span>
                            <span class="art-type">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-leibie"></use>
                                </svg>:{{ article.category }}
                            </span>
                            <span class="art-readers">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-yuedu1"></use>
                                </svg>:{{ article.readCount }}
                            </span>

                        </div>
                    </div>

                    <div class="art-txt" id="container" v-html="article.content">
                    </div>
                </el-card>
            </div>
            <el-card class="art-foot" v-if="!article.isLock">
                <div class="artd-tags" v-if="tagsList">
                    标签:
                    <span v-for="(item,idx) in this.tagsList"
                          :style="'border: 1px rgba('+item.r+','+item.g+','+item.b+',1) solid;background-color: rgba('+item.r+','+item.g+','+item.b+',.1)'">
                        {{ item.name }}
                    </span>
                </div>
                <div class="art-copyright" v-if="article.flagOriginal">
                    <span><b>版权声明:</b>本站原创文章，由{{ article.author }}在{{ article.publishDateStr }}发表</span>
                    <span><b>转载请注明:</b>{{ article.title }} | 简窝博客</span>
                </div>
                <div class="art-copyright" v-else>
                    <span><b>转载源链接:</b>本文为转载文章，源地址: {{ article.originalUrl }}</span>
                </div>


            </el-card>
            <el-card class="prev-next-art box-card" v-if="!article.isLock">
                <div class="title">
                    猜你喜欢
                </div>
                <span v-for="(item,idx) in this.recommendList">
                <a :href="'#/detail?id=' + item.oid">{{ item.title }}</a><br>
            </span>
            </el-card>
            <el-card class="art-goods" v-if="!article.isLock">
                <div class="goods-add " @click="hasPraise" v-if="article.isPraise" style="color: red">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-dianzan_kuai"></use>
                    </svg>
                    <br>{{ article.praiseCount }}
                </div>
                <div v-else class="goods-add before-goods-add" @click="doPraise(article)">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-zan"></use>
                    </svg>
                    <br>
                    {{ article.praiseCount }}
                </div>
            </el-card>
            <Comment :artOid="this.$route.query.id" :thisArtCanBeComm="article.isComment"
                     v-if="!article.isLock">
            </Comment>
            <div class="page-foot"></div>
        </div>

        <el-dialog v-model="showVerifyDialog" title="密码验证" width="400px">
            <el-form :inline="true" :rules="rules"
                     :model="verifyInfo" ref="verifyInfo">
                <el-form-item style="width: 100%" prop="password">
                    <el-input v-model="verifyInfo.password" type="password" autocomplete="off"/>
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                <el-button @click="showVerifyDialog = false">取消</el-button>
                <el-button type="primary" class="primary" @click="doVerify">验证</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";
import Comment from "@/components/Comment";
import Prism from 'prismjs'

export default {
    name: "Detail",
    data() {
        const validatePass = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请填写文章密码.'))
            } else {
                callback()

            }
        }

        return {
            article: {},
            entityOid: null,
            recommendList: [],
            tagsList: [],

            keyMap: {
                pid: "parentOid",
                id: "oid",
                isAdmin: "admin_comment"
            },
            showVerifyDialog: false,
            verifyInfo: {
                password: null,
            },
            isVerify: false,

            rules: {
                password: [
                    {validator: validatePass, required: true, trigger: ['blur', 'change']},
                ],

            },
        }
    },
    components: {
        Comment,
    },
    mounted() {
        this.initParam();
        this.getArticle();
        this.getRecommendList();
        this.registerAccessIp();
        var that = this;
        if (this.$route.query.jump) {
            that.$nextTick(function () {
                if (document.getElementById('comment')) {
                    document.getElementById('comment').scrollIntoView()
                }

            })
        }

    },
    created() {

    },
    methods: {
        initParam: function () {
            var isSameId = true;
            if (this.$route.query.id) {
                if (this.$route.query.id != this.entityOid) {
                    isSameId = false;
                }
                this.entityOid = this.$route.query.id;
            } else {
                this.$router.push("/index");
                return;
            }
            return isSameId;
        },
        getArticle: function () {
            postJson("/article/query/detail", {entityOid: this.entityOid}).then((res) => {
                this.article = res.data || {};
                if (res.data.isLock === true) {
                    this.getVerifyCache();
                    if (!this.isVerify) {
                        this.showVerifyDialog = true;
                    }
                } else if (res.data && res.data.tags && res.data.tags.length > 0) {
                    for (let i = 0; i < res.data.tags.length; i++) {
                        var tag = {};
                        tag.name = res.data.tags[i].name;
                        tag.id = res.data.tags[i].id;
                        var color = Math.random();
                        var r = parseInt((color * (i + 1) * 1234) % 254);
                        var g = parseInt((color * (i + 1) * 4321) % 254);
                        var b = parseInt((color * (i + 1) * 2222) % 254);
                        tag.r = r;
                        tag.g = g;
                        tag.b = b;
                        this.tagsList.push(tag);
                    }
                }

                this.renderCode();

            });
        },

        getRecommendList: function () {
            postJson("/article/query/detail/recommend/list", {entityOid: this.entityOid}).then((res) => {
                this.recommendList = res.recommendList;

            });
        },
        doPraise: function (article) {
            postJson("/article/praise/add", {artOid: article.id}).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '点赞成功!',
                        grouping: true,
                        type: 'success'
                    })
                    article.praiseCount = article.praiseCount + 1;
                    article.isPraise = true;
                }
            });
        },
        hasPraise: function () {
            this.$message({
                showClose: true,
                message: '您已经赞过该文章啦!',
                grouping: true,
                type: 'warning'
            })
        },
        registerAccessIp: function () {
            let artList = sessionStorage.getItem('hasRegIp');
            let artOids = [];
            if (artList) {
                artOids = JSON.parse(artList);
            }
            if (artOids.indexOf(this.entityOid) < 0) {
                postJson("/access/article/read", {artOid: this.entityOid}).then((res) => {
                    artOids.push(this.entityOid);
                    let _jsonstr = JSON.stringify(artOids);
                    sessionStorage.setItem("hasRegIp", _jsonstr);
                });
            }

        },
        doVerify: function () {
            this.$refs['verifyInfo'].validate((valid) => {
                if (valid) {
                    this.doVerifyQuery();
                }
            });
        },
        doVerifyQuery: function () {
            postJson("/article/password/verify", {
                artOid: this.entityOid,
                password: this.verifyInfo.password
            }).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '验证成功!',
                        grouping: true,
                        type: 'success'
                    })
                    this.article = res.data || {};
                    this.showVerifyDialog = false;
                    let artList = sessionStorage.getItem('verifyInfo');
                    let artVerifies = [];
                    if (artList) {
                        artVerifies = JSON.parse(artList);
                    }
                    var verifyInfo = {};
                    verifyInfo.artOid = this.entityOid;
                    verifyInfo.password = this.verifyInfo.password;
                    artVerifies.push(verifyInfo);
                    let _jsonstr = JSON.stringify(artVerifies);
                    sessionStorage.setItem("verifyInfo", _jsonstr);
                    this.renderCode();


                    if (res.data && res.data.tags && res.data.tags.length > 0) {
                        for (let i = 0; i < res.data.tags.length; i++) {
                            var tag = {};
                            tag.name = res.data.tags[i].name;
                            tag.id = res.data.tags[i].id;
                            var color = Math.random();
                            var r = parseInt((color * (i + 1) * 1234) % 254);
                            var g = parseInt((color * (i + 1) * 4321) % 254);
                            var b = parseInt((color * (i + 1) * 2222) % 254);
                            tag.r = r;
                            tag.g = g;
                            tag.b = b;
                            this.tagsList.push(tag);
                        }
                    }

                }


            });
        },
        getVerifyCache: function () {
            let artList = sessionStorage.getItem('verifyInfo');
            let artVerifies = [];
            if (artList) {
                artVerifies = JSON.parse(artList);
            }
            for (let i = 0; i < artVerifies.length; i++) {
                if (artVerifies[i].artOid === this.entityOid) {
                    this.verifyInfo.password = artVerifies[i].password;
                    this.isVerify = true;
                    break;
                }
            }
            if (this.isVerify) {
                this.doVerifyQuery();
            }
        },
        renderCode: function () {
            setTimeout(() => {
                        Prism.highlightAll()
                    }
                    , 0);
            this.$nextTick(function () {
                const container = document?.getElementById('container')
                const codeBlocks = container?.getElementsByTagName('pre')
                if (codeBlocks && codeBlocks.length > 0) {
                    for (let i = 0; i < codeBlocks.length; i++) {
                        let item = codeBlocks[i];
                        item.style.whiteSpace = 'pre-wrap'
                        // Add pre-mac element for Mac Style UI
                        const preMac = document.createElement('div')
                        preMac.classList.add('pre-mac')
                        preMac.innerHTML = '<span></span><span></span><span></span>'
                        item.parentElement.insertBefore(preMac, item)
                    }
                }
                var that = this;
                container.addEventListener('click', function (ev) {
                    var target = ev.target || ev.srcElement;
                    console.log(target.parentNode.className)
                    if (target.parentNode.className === 'copy-to-clipboard-button') {
                        that.$message({
                            showClose: true,
                            message: '复制成功!',
                            grouping: true,
                            type: 'success'
                        })
                    }
                });


            })

        }
    },
    watch: {
        '$route'(to, from) { //监听URL地址栏参数变化
            var isSameId = this.initParam();
            if (!isSameId) {
                this.getArticle();
                this.getRecommendList();
            }
        }
    }
}
</script>
<style>
/*.el-dialog{*/
/*    z-index: 2002!important;*/
/*}*/
/*.el-overlay{*/
/*    z-index: 2002!important;*/
/*}*/
</style>

<style scoped>
.dialog-footer .primary {
    background-color: rgba(166, 10, 169, .98);
    border: none;
    color: white;
    padding: 0.5rem 1.5rem;
    border-radius: 8%;
    cursor: pointer;
    margin-left: 10px;
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