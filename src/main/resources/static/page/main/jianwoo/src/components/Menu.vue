<template>
    <div class="index-menu">
        <div>
            <ul>
                <li><a href="/" :class="{'menu-activity': !this.currMenu}" >首页</a></li>
                <li v-for="(item,idx) in menulist">
                    <a :href="item.url" v-if="item.isCategory === false" @click="jumpOther(item.id)" :class="{'menu-activity': currMenu && item.ids.indexOf(currMenu) !== -1 }" >{{item.title}}</a>
                    <span v-if="item.isCategory === true" @click="jump1(item.id)" :class="{'menu-activity': currMenu && item.ids.indexOf(currMenu) !== -1 }" >{{item.title}}</span>
                    <ul class="submenu" v-for="(subMenu,idx) in item.subList">
                        <li>
                            <a :href="subMenu.url" v-if="subMenu.isCategory === false">{{subMenu.title}}</a>
                            <span v-if="subMenu.isCategory === true" @click="jump2(subMenu.id)">{{subMenu.title}}</span>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <el-form-item class="input-search" :inline="true">
            <el-input v-model="searchForm.keywords" placeholder="搜索文章" class = "search-input"/>
            <el-button type="primary" class="search-btn" @click="search()"><svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-sousuoxiao"></use>
            </svg></el-button>

        </el-form-item>
    </div>
</template>

<script>
import {getJson} from "@/common/js/getJson";

export default {
    name: "Menu",
    data() {
        return {
            currMenu : '',
            menuActivity : false,
            searchForm: {
                keywords: '',
                page: 1,
                limit: 20,
                tags: [],
                category1: '',
                category2: '',
            },
            menulist : [],
        }
    },
    mounted() {

    },
    created() {
        if (this.$route.query.category1) {
            this.currMenu = Number(this.$route.query.category1);

        }
        if (this.$route.query.category2) {
            this.currMenu = Number(this.$route.query.category2);

        }
        var meta = this.$route.meta;
        if (meta){
            for (let k in meta)
            {
                if (meta[k].menuOid){
                    this.currMenu = Number(meta[k].menuOid);
                    break;
                }
            }
        }
        this.getMenuList();
    },
    methods: {
        getMenuList: function () {
            getJson("/menu/query/list", {}).then((res) => {
                this.menulist = res.data || [];


            });
        },
        getKeywords: function () {
            return this.searchForm.keywords;
        },
        getCategory2: function () {
            return this.searchForm.category2;
        },
        search:function () {

            // this.$parent.search(this.searchForm);
            this.$router.push({
                path: '/index',
                query: {
                    keywords: this.searchForm.keywords,
                }
            });

        },
        jumpOther:function (id) {
            this.currMenu = id;
        },
        jump1:function (id) {
            this.searchForm.category1 = id;
            this.currMenu = id;
            // this.$parent.search(this.searchForm);
            this.$router.push({
                path: '/index',
                query: {
                    category1: this.searchForm.category1,
                }
            });
        },

        jump2:function (id) {
            this.searchForm.category2 = id;
            this.currMenu = id;
            // this.$parent.search(this.searchForm);
            this.$router.push({
                path: '/index',
                query: {
                    category2: this.searchForm.category2,
                }
            });
        },
    },
    watch: {}
}
</script>

<style scoped>
.search-btn{
    height: 40px;
    background-color: var(--theme_color);
    border-color: var(--theme_color);
    display: inline-block;
    width:70px;
}
.search-btn:hover{
    background-color: var(--theme_color_dark);
    border-color: var(--theme_color_dark);
}
.search-btn:active{
    background-color: var(--theme_color_dark);
    border-color: var(--theme_color_dark);
}
.search-btn:focus{
    background-color: var(--theme_color_dark);
    border-color: var(--theme_color_dark);
}
.search-input{
    height: 40px;
    display: inline-block;
    width:100px;
}
</style>