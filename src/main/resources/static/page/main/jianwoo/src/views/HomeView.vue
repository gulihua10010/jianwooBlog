<template>
    <div class="home">
        <img alt="Vue logo" src="../assets/logo.png">
        <HelloWorld msg="Welcome to Your Vue.js App1111111111111111"/>
    </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import CONFIG from '../common/js/config.js'
import {getJson} from '@/common/js/getJson'
import {postJson} from "@/common/js/postJson";
export default {
    name: 'HomeView',
    components: {
        HelloWorld
    },
    data() {
        return {
            searchForm: {
                keywords: '',
                page: 1,
                limit: 20,
            },
            list: [],
            total: 0,
        }
    },
    created() {
        this.getList();
    },
    methods: {
        getList: function () {
            postJson("/api/article/query/page/list", this.searchForm).then((res) => {
                if (res.status === '000000') {
                    this.list = res.data
                    this.total = res.count
                } else {
                    var msg = 'API调用出错！请稍后重试！';
                    if (res.msg) {
                        msg = res.msg
                    }

                    this.$message({
                        showClose: true,
                        message: msg,
                        type: 'error'
                    });
                }

            });
        },
        handleCurrentChange: function () {
            this.getList();

        },

    }

}
</script>
