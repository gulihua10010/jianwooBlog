<template>
    <div class="art-tags right-side">
        <p>标签云集</p>
        <div>
            <span v-for="(item,idx) in this.tagsList"
                  :style="'border: 1px rgba('+item.r+','+item.g+','+item.b+',1) solid;background-color: rgba('+item.r+','+item.g+','+item.b+',.1)'"
                  >
                <span @mousemove="mousemove($event, item.id)" @mouseleave="mouseleave($event)">
                    <a :href="'/index?tag='+item.id">{{ item.name + " (" + item.count + ")" }}</a>
                </span>
                <a :style="'position: absolute;left:' + titleAltLeft + 'px;top:'+titleAltTop+'px;'" class="tit_alt"
                   v-if="this.showTitleAlt === item.id">{{ item.count + '篇文章'}}</a>
         </span>
        </div>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";

export default {
    name: "Tags",
    data() {
        return {
            tagsList: [],
            showTitleAlt: 0,
            titleAltLeft: 0,
            titleAltTop: 0,
        }
    },
    mounted() {
        this.getList();

    },
    created() {

    },
    methods: {
        getList: function () {
            postJson("/tags/query/all/list", {}).then((res) => {
                if (res.data && res.data.length > 0) {
                    for (let i = 0; i < res.data.length; i++) {
                        var tag = {};
                        tag.name = res.data[i].name;
                        tag.id = res.data[i].id;
                        tag.count = res.data[i].count;
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

            });
        },

        mouseleave: function (e) {
            this.showTitleAlt = 0
        },
        mousemove: function (e, oid) {
            var pagex = e.pageX;
            var pagey = e.pageY;
            this.titleAltLeft = pagex;
            this.titleAltTop = pagey - 230;
            this.showTitleAlt = oid
            // console.log(this.showTitleAlt)
            // console.log(this.titleAltLeft, this.titleAltTop);
        }
    },
    watch: {}
}
</script>

<style scoped>

</style>