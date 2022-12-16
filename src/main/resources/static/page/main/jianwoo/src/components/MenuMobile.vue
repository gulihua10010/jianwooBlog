<template>
    <el-button type="primary" class="menu-btn" v-if="isCollapse" @click="collapse()" circle ><el-icon><Expand /></el-icon></el-button>
    <el-button type="primary" class="menu-btn" v-if="!isCollapse" @click="collapse()" circle ><el-icon><Fold /></el-icon></el-button>
    <el-menu
            :class="'el-menu-vertical-mobile ' + ( menuClass)"
            text-color="#000"
            active-text-color="var(--theme_color_dark)">
        <el-menu-item>
            <a href="/"  :class="'menu-item-jw ' + (!this.currMenu ? ' el-menu-activity': '')">
                <i class="el-icon-menu"></i>
                <span >首页</span>
            </a>
        </el-menu-item>
        <template v-for="(item,idx) in menulist" :key="idx">
            <el-submenu>
                <template #title>
                    <a :href="item.url" v-if="item.isCategory === false" :class="'menu-item-jw '+ (currMenu && item.ids.indexOf(currMenu) !== -1 ? ' el-menu-activity':'')">
                        <i :class="item.icon" v-if="item.icon"></i>{{item.title}}
                    </a>
                    <span v-if="item.isCategory === true" @click="jump1(item.id)" :class="'menu-item-jw '+ (currMenu && item.ids.indexOf(currMenu) !== -1 ? ' el-menu-activity':'')">
                        <i :class="item.icon" v-if="item.icon"></i>
                        {{item.title}}
                    </span>
                </template>
                <el-menu-item v-for="(subMenu,idx1) in item.subList"  :key="idx1">
                    <a :href="subMenu.url" v-if="subMenu.isCategory === false" :class="'menu-item-jw '+ (currMenu === subMenu.id ? ' el-menu-activity':'')">{{subMenu.title}}</a>
                    <span v-if="subMenu.isCategory === true" @click="jump2(subMenu.id)" :class="'menu-item-jw '+ (currMenu === subMenu.id ? ' el-menu-activity':'')">{{subMenu.title}}</span>
                </el-menu-item>
            </el-submenu>
        </template>

    </el-menu>
</template>

<script>
import {getJson} from "@/common/js/getJson";
import {isCollapsed} from "../../../../admin/lib/extend/tinymce/tinymce/tinymce";

export default {
    name: "Menu",
    data() {
        return {
            currMenu : '',
            menuClass : '',
            menuActivity : false,
            isCollapse : true,
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

        jump1:function (id) {
            this.searchForm.category1 = id;
            this.currMenu = id;
            // this.$parent.search(this.searchForm);
            this.collapse();
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
            this.collapse();
            // this.$parent.search(this.searchForm);
            this.$router.push({
                path: '/index',
                query: {
                    category2: this.searchForm.category2,
                }
            });
        },
        collapse:function () {
            if (this.isCollapse) {
                this.menuClass = ' menu-show'
            }else {
                this.menuClass = ' menu-hidden'
            }
            this.isCollapse = !this.isCollapse
        }
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
<style>
.el-menu a,.el-menu span{
    text-decoration: none;
    color:#000;

}
.menu-item-jw {
    display: inline-block;
    line-height: 25px;
    padding: 0 10px;

}
.el-menu-activity, .el-menu-activity > span {
    color:var(--theme_color_dark) !important;

}
.menu-btn{
    background-color: var(--theme_color) !important;
    color: #fff;
    border: none !important;
    position: absolute;
    left: 10px;
    top: 10px;


}
.menu-btn :hover {
    background-color: var(--theme_color_dark);
    color: #fff;
}

.el-menu-vertical-mobile{
    /*display: none;*/
    /*position: absolute;*/
    top: 30px;
    width: 0;
}

.el-menu{
    overflow: hidden;
}

.menu-hidden{
    animation:mobile-menu-hidden 1s ease;
    -webkit-animation:mobile-menu-hidden 1s ease;
    animation-iteration-count:1;
    -webkit-animation-iteration-count: 1;
    animation-fill-mode:forwards;
    -webkit-animation-fill-mode: forwards
}

.menu-show{
    animation:mobile-menu-show 1s ease;
    -webkit-animation:mobile-menu-show 1s ease;
    animation-iteration-count:1;
    -webkit-animation-iteration-count: 1;
    animation-fill-mode:forwards;
    -webkit-animation-fill-mode: forwards
}

@keyframes mobile-menu-hidden {
    0% {
        width: 100%
    }
    100% {
        width: 0px
    }
}
@-webkit-keyframes mobile-menu-hidden {
    0% {
        width: 100%
    }
    100% {
        width: 0px
    }
}


@keyframes mobile-menu-show {
    0% {
        width: 0
    }
    100% {
        width: 100%
    }
}
@-webkit-keyframes mobile-menu-show {
    0% {
        width: 0
    }
    100% {
        width: 100%
    }
}
</style>