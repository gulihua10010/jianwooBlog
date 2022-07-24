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
        }
    },
    mounted() {

    },
    created() {
        this.getTopImg();
    },
    methods: {
        getTopImg: function () {
            postJson("/main/config/query", this.topImgParam).then((res) => {
                this.topImg = res.data.value;

            });
        },
        search:function (param) {
            this.$refs['home'].search(param);


        }
    },
    watch: {}
}
</script>

<style scoped>

</style>