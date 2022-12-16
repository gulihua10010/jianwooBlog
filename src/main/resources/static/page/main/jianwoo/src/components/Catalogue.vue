<template>
    <nav class="container">
        <span class="title" v-show="isCompleted">{{ title }}</span>
        <ul class="catalogue">
            <li
                    v-for="(h, index) in headers"
                    :key="index"
                    :class="[h.className, {active: curHeader === index}]"
            >
                <a
                        :href="`#${h.id}`"
                        @click.prevent="scroll(h.id, index)"
                >{{ h.content }}</a>
            </li>
        </ul>
    </nav>
</template>

<script>
import debounce from 'lodash.debounce'
import smooth from 'smoothscroll-polyfill'

export default {
    name: 'ArticleCatalogue',
    props: {
        selector: {
            type: [String, Object],
            default: null,
            required: true
        },
        title: {
            type: String,
            default: '目录'
        },
        asyncData: {
            type: Boolean,
            default: true
        },
        article: {
            type: Object,
            default: null
        }
    },
    data() {
        return {
            isCompleted: false,
            positions: [],
            headers: [],
            curHeader: 0
        }
    },
    mounted() {
        // if (this.asyncData) {
        //     this.$parent.$emit('hook:updated', this.init)
        // } else {
        //     this.init()
        // }
    },
    methods: {
        init() {
            smooth.polyfill() // 补丁
            this.getContent()
            this.bindScroll()
        },
        getContent() {
            let dom = document.querySelector(this.selector)
            let headers = dom.querySelectorAll('h1, h2, h3')
            this.positions = Array.from(headers).map((h, index) => {
                // 遍历标题，重置id并映射目录结构
                const id = h.id = `header-${index + 1}`
                this.headers.push({
                    id,
                    className: `title-${h.nodeName[1]}`,
                    content: h.textContent
                })
                return getElementPosition(h) // 获得位置信息便于后面比较
            })
            this.isCompleted = true
        },
        scroll(id, index) {
            const el = document.getElementById(id)
            this.curHeader = index
            el.scrollIntoView({
                block: 'start',
                behavior: 'smooth'
            })
        },
        onScroll: debounce(function () {
            // 获取当前滚动位置
            const scrollY = Math.max(
                    window.pageYOffset,
                    document.documentElement.scrollTop,
                    document.body.scrollTop
            ) + 70

            this.positions.forEach((cur, index) => {
                if (
                        index === this.positions.length - 1
                        && scrollY > cur.y
                ) {
                    this.curHeader = index
                } else {
                    const next = this.positions[index + 1]
                    // 当前位置在当前和下一个标题中间
                    if (scrollY >= cur.y && scrollY < next.y) {
                        this.curHeader = index
                    }
                }
            })
        }, 100),
        bindScroll() {
            window.addEventListener('scroll', this.onScroll)
        }
    },
    beforeUnmount() {
        window.removeEventListener('scroll', this.onScroll)
    },
    setup() {
        function init_() {
            this.init()
        }
        return {
            init_
        }
    },
    watch: {
        article(newVal, oldVal) {
            let that = this;
            this.$nextTick(function () {
                this.init()
            })
        }
    }
}

/**
 * 获取dom元素的坐标
 */
function getElementPosition(el) {
    const docEl = document.documentElement
    const docRect = docEl.getBoundingClientRect()
    const elRect = el.getBoundingClientRect()
    return {
        x: elRect.left - docRect.left,
        y: elRect.top - docRect.top,
    }
}
</script>

<style scoped lang="stylus">
$grey = #EBEDEF
$blue = #34A0EF

// 通用样式
a, a:visited {
    color: black;
    background-color: inherit;
    text-decoration: none;
}

.container
    position sticky
    top 0
    padding 0
    margin  0
    width 250px
    height 500px
    font-size 0.9em
    overflow-y scroll

    a
        display block
        position relative
        padding 8px 20px
        white-space nowrap
        overflow hidden
        text-overflow ellipsis

    // 激活样式

    .active
        background-color $grey

        & > a
            color $blue

.catalogue
    position relative
    width 100%
    padding 0
    list-style none

    li:hover
        background-color $grey

        & > a
            color $blue

    &::before
        content ""
        position absolute
        left 7px
        width 2px
        height 100%
        background-color $grey

    /**
     * 分别对应三级标题的样式
     */
    // 标题前面的小圆点
    point($scale, $left)
        &::before
            content ""
            position absolute
            left $left
            top 50%
            transform translateY(-50%)
            background-color black
            width $scale
            height $scale
            border-radius $scale

    .title-1 a
        font-weight bold
        point(6px, 5px)

    .title-2 a
        padding-left 40px
        point(5px, 25px)

    .title-3 a
        padding-left 60px
        point(4px, 45px)
</style>