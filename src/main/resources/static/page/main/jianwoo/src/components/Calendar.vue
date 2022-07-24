<template>
    <div class="calendar">
        <table>
            <thead>
            <tr>
                <td colspan="7"><a> {{ this.year }} 年 {{ this.month + 1 }} 月</a></td>
            </tr>
            </thead>
            <tbody id="cal-tb">
            <tr class="cal-week">
                <td><a>日</a></td>
                <td><a>一</a></td>
                <td><a>二</a></td>
                <td><a>三</a></td>
                <td><a>四</a></td>
                <td><a>五</a></td>
                <td><a>六</a></td>
            </tr>
            <tr v-for="(item,idx) in this.days">
                <td v-for="(jItem,j) in item">
                    <a v-if="jItem.day && !jItem.isPublish" :class="jItem.isToday ? 'cal-today ' : ''"
                       >{{ jItem.day }}</a>
                    <a v-if="jItem.day && jItem.isPublish" :class="jItem.isPublish ? 'cal-artpub': ''"
                       @mousemove="mousemove($event, jItem.day)" @mouseleave="mouseleave($event)"
                       :href="'/#/index?publishDate=' + jItem.publishDate">{{ jItem.day }}</a>
                    <span :style="'position: absolute;left:' + titleAltLeft + 'px;top:'+titleAltTop+'px;'" class="tit_alt"
                       v-if="this.showTitleAlt === jItem.day">{{ jItem.count + '篇文章'}}</span>
                </td>

            </tr>
            </tbody>
            <tfoot id="cal-foot" v-if="!loading">
            <tr>
                <td colspan="7"><a class='pre-mon' @click="pre()">&lt;&lt;&nbsp;{{(this.month===0?12:this.month)}}月</a>
                    <a class='next-mon' @click="next()">>>&nbsp;{{(this.month+2===13?1:this.month+2)}}月</a></td>
            </tr>
            </tfoot>
        </table>

    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";

export default {
    name: "Calendar",
    data() {
        return {
            year: new Date().getFullYear(),
            month: new Date().getMonth(),
            date: new Date().getDate(),
            weekDays: 7,
            weeks: 5,
            tYear: new Date().getFullYear(),
            tMonth: new Date().getMonth(),
            tDate: new Date().getDate(),
            dayOfFirstWeek: new Date().getDay(),
            days: [],
            articlePushDay: {},
            showTitleAlt: 0,
            titleAltLeft: 0,
            titleAltTop: 0,
            loading: true,
        }
    },
    mounted() {

    },
    created() {
        this.queryMonthArticles(this.year, this.month + 1, ()=>{
            this.initDays(this.year, this.month);
        })

    },
    methods: {
        initDays: function (year, mon) {
            var date = new Date(year, mon, 1);
            var dayOfFirstWeek = date.getDay();
            var day = 1;
            var dayObj = {};
            var dayOfMonth = this.getDayOfMonth(year, mon + 1);
            for (var i = 0; i <= 5; i++) {
                var days_ = [];
                for (var j = 0; j < 7; j++) {
                    if (dayOfFirstWeek > j && i === 0) {
                        dayObj = {
                            day: '',
                            isToday: false,
                            isCurrentMonth: mon === this.month,
                            isSelected: false,
                            isPublish: false,
                            count: 0,
                            publishDate: '',
                        }
                    } else {
                        if (day > dayOfMonth) {
                            dayObj = {
                                day: '',
                                isToday: false,
                                isCurrentMonth: mon === this.month,
                                isSelected: false,
                                isPublish: false,
                                count: 0,
                                publishDate: '',
                            }
                        }else {
                            var isPublish = false;
                            var count = 0;
                            var publishDate = '';
                            if (this.articlePushDay[day])
                            {
                                isPublish = true;
                                count = this.articlePushDay[day].count;
                                publishDate = this.articlePushDay[day].date;
                            }
                            dayObj = {
                                day: day,
                                isToday: year === this.tYear && mon === this.tMonth && this.tDate === day,
                                isCurrentMonth: year === this.tYear && mon === this.tMonth,
                                isSelected: false,
                                isPublish: isPublish,
                                count: count,
                                publishDate: publishDate,
                            }
                            day++;

                        }
                    }
                    days_.push(dayObj)

                }
                this.days.push(days_);
            }
            this.loading = false;

        },
        queryMonthArticles: function (year, mon, callback) {
            this.articlePushDay = {};
            var mon_ = mon.toString();
            if ( mon_.length === 1){
                mon_= '0' + mon_;
            }
            postJson("/article/month/date/publish/query", {month: year.toString() + '-' + mon_}).then((res) => {
                if (res.data && res.data.length > 0) {
                    for (let i = 0; i < res.data.length; i++) {
                        this.articlePushDay[res.data[i].day] = res.data[i];
                    }
                }
                typeof callback === 'function' && callback();
            });
        },
        getDayOfMonth: function (year, mon) {
            var day = new Date(year, mon, 0);
            return day.getDate();
        },
        mouseleave: function (e) {
            this.showTitleAlt = 0
        },
        mousemove: function (e, day) {
            var pagex = e.pageX;
            var pagey = e.pageY;
            this.titleAltLeft = pagex;
            this.titleAltTop = pagey - 230;
            this.showTitleAlt = day
            // console.log(this.showTitleAlt)
            // console.log(this.titleAltLeft, this.titleAltTop);
        },
        pre: function () {
            this.loading = true;
            if (--this.month < 0){
                this.month = 11;
                this.year--;
            }
            this.days = [];
            this.queryMonthArticles(this.year, this.month + 1, ()=>{
                this.initDays(this.year, this.month);
            })
        },
        next: function () {
            this.loading = true;
            if (++this.month > 11){
                this.month = 0;
                this.year++;
            }
            this.days = [];
            this.queryMonthArticles(this.year, this.month + 1, ()=>{
                this.initDays(this.year, this.month);
            })
        },
    },
    watch: {}
}
</script>

<style scoped>

</style>