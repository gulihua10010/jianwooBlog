<template>
    <div>
        <div v-html="footHtml"></div>
        <br/>
        <span id="copyright">Copyright © 2018-{{year}}<span></span></span>
        <span><a :href="domain">{{ name }}</a> </span>
        <span><a :href="recordUrl"> {{record}}</a></span>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";

export default {
    name: "Footer",
    data() {
        return {
            footHtml:'',
            record:'',
            recordUrl:'',
            domain:'',
            name:'',
            year:new Date().getFullYear(),

        }
    },
    mounted() {
        this.getFootHtml();
        this.getRecord();
        this.getRecordUrl();
        this.getDomain();
        this.getWebName();
    },
    created() {

    },
    methods: {
        getFootHtml: function (param) {
            let webInfo = this.getWebInfoCache();
            if (webInfo.footHtml){
                this.footHtml = webInfo.footHtml;
            }else {
                postJson("/main/config/query", {key: 'FOOT_HTML'}).then((res) => {
                    this.footHtml = res.data.value;
                    webInfo.footHtml = this.footHtml;
                    let _jsonstr = JSON.stringify(webInfo);
                    sessionStorage.setItem("webInfo", _jsonstr);
                });
            }

        },
        getRecord: function (param) {
            let webInfo = this.getWebInfoCache();

            if (webInfo.record){
                this.record = webInfo.record;
            }else {
                postJson("/main/config/query", {key: 'RECORD'}).then((res) => {
                    this.record = res.data.value;
                    webInfo.record = this.record;
                    let _jsonstr = JSON.stringify(webInfo);
                    sessionStorage.setItem("webInfo", _jsonstr);
                });
            }
        },
        getRecordUrl: function (param) {
            let webInfo = this.getWebInfoCache();

            if (webInfo.recordUrl){
                this.recordUrl = webInfo.recordUrl;
            }else {
                postJson("/main/config/query", {key: 'RECORD_URL'}).then((res) => {
                    this.recordUrl = res.data.value;
                    webInfo.recordUrl = this.recordUrl;
                    let _jsonstr = JSON.stringify(webInfo);
                    sessionStorage.setItem("webInfo", _jsonstr);
                });
            }
        },

        getDomain: function (param) {
            let webInfo = this.getWebInfoCache();

            if (webInfo.domain){
                this.domain = webInfo.domain;
            }else {
                postJson("/main/config/query", {key: 'DOMAIN'}).then((res) => {
                    this.domain = res.data.value;
                    webInfo.domain = this.domain;
                    let _jsonstr = JSON.stringify(webInfo);
                    sessionStorage.setItem("webInfo", _jsonstr);
                });
            }
        },
        getWebName: function (param) {
            let webInfo = this.getWebInfoCache();

            if (webInfo.name){
                this.name = webInfo.name;
            }else {
                postJson("/main/config/query", {key: 'TITLE'}).then((res) => {
                    this.name = res.data.value;
                    webInfo.name = this.name;
                    let _jsonstr = JSON.stringify(webInfo);
                    sessionStorage.setItem("webInfo", _jsonstr);
                });
            }
        },
        getWebInfoCache:function () {
            let webInfoStr = sessionStorage.getItem('webInfo');
            let webInfo = {};
            if (webInfoStr) {
                webInfo = JSON.parse(webInfoStr);
            }
            return webInfo;
        }
    },
    watch: {}
}
</script>

<style scoped>

</style>