<template>
    <div class="footer">
        <div v-html="footHtml"></div>
        <span id="copyright">Copyright © 2018-{{ year }}<span></span></span>
        <span><a :href="domain">{{ name }}</a> {{version}} </span>
        <br/>
        <span>
                <a target="_blank" :href="recordUrl"
                   style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
                    <img src="/static/comm/img/beian.png" style="float:left;"/>{{ record }}</a>
        </span>
    </div>
</template>

<script>
import {getJson} from "@/common/js/getJson";
import {postJson} from "@/common/js/postJson";
import config from "@/common/js/config";

export default {
    name: "Footer",
    data() {
        return {
            footHtml: '',
            record: '',
            recordUrl: '',
            domain: '',
            name: '',
            year: new Date().getFullYear(),
            version : 'v' + config.VERSION,

        }
    },
    mounted() {
        this.getFootInfo();
        this.getRecord();
    },
    created() {

    },
    methods: {
        getFootInfo: function () {
            let webInfo = this.getWebInfoCache();
            if (webInfo.cache) {
                this.footHtml = webInfo.footHtml;
                this.record = webInfo.record;
                this.recordUrl = webInfo.recordUrl;
                this.domain = webInfo.domain;
                this.name = webInfo.name;
            } else {
                getJson("/config/page/footer/query", {}).then((res) => {
                    this.footHtml = res.data.footHtml;
                    this.record = res.data.record;
                    this.recordUrl = res.data.recordUrl;
                    this.domain = res.data.domain;
                    this.name = res.data.title;
                    webInfo.footHtml = this.footHtml;
                    webInfo.record = this.record;
                    webInfo.recordUrl = this.recordUrl;
                    webInfo.domain = this.domain;
                    webInfo.name = this.name;
                    let _jsonstr = JSON.stringify(webInfo);
                    localStorage.setItem("webInfo", _jsonstr);
                });
            }

        },
        getRecord: function () {
            let record = localStorage.getItem('record');
            if (record){
                this.record = record;
            }else {
                let key = location.host.toUpperCase().replaceAll(/[.|:]/g,"_");
                postJson("/config/query", {key: key + "_" + "RECORD"}).then((res) => {
                    if (res.data && res.data.value) {
                        this.record = res.data.value;
                        localStorage.setItem("record", this.record);
                    }
                });
            }

        },
        getWebInfoCache: function () {
            let webInfoStr = localStorage.getItem('webInfo');
            let webInfo = {};
            webInfo.cache = false;
            if (webInfoStr) {
                webInfo = JSON.parse(webInfoStr);
                webInfo.cache = true;
            }
            return webInfo;
        }
    },
    watch: {}
}
</script>

<style scoped>

</style>