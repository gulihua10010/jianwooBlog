<template>
    <div class="emoji-panel-wrap">
            <span
                    v-for="(item,index) in emojis"
                    :text="item"
                    v-bind:key="index"
                    @click="handleEmojiClick(index)"
            >{{ item }}</span>
    </div>
</template>
<script>

export default {
    data() {
        return {
                                                                                                                                    emojis: "😂😀😃😄😆😅🙄😇😷😌😞😉😛😝😜😋😚😙😗😎😒😱😏😔😣😑😳😘😍🤑🤐🤓🤒🤕🤗🤔🙁🙂🙃🤩🤣🤤🤧🤠🤥😶😯😫😩😡😠😕😟😦😲😵😨😰😥😪😢😓😭😬😁😖🤩🤣🤤🤢🤧🤠🤥🤪🤫🤬🤭🤮🤯🤨🥰🥳🥺🥴🥶🧐🤙👐🙌👋👏🙏👍🖖🤘✊👊👎👌👈👉👆👇✋💪💅🏻👀💘💕💞👼👶👵👸👩👧👦👨👮👲👳👷🎅🦸👨💆💇🙅🙆💁🙇🙎🙋🙍"
        };
    },
    mounted() {
    },
    methods: {
        handleEmojiClick(index) {
            let code = this.utf16toEntities(this.emojis[index], this.emojis[index+1])
            this.$emit("emojiClick", code);
        },
        utf16toEntities(high, low) {
            let H = high.charCodeAt(0); // 取出高位
            let L = low.charCodeAt(0); // 取出低位
            let code = (H - 0xd800) * 0x400 + 0x10000 + L - 0xdc00; // 转换算法
            let s = `&#${code};`;
            return s;
        },

    }
};
</script>
<style scoped>
.emoji-panel-wrap {
    box-sizing: border-box;
    border: 1px solid #cccccc;
    border-radius: 5px;
    background-color: #ffffff;
    width: 508px;
    height: 200px;
    position: absolute;
    left: 0px;
    top: 32px;
    overflow-y: scroll;
    padding: 10px 5px;
}

.emoji-panel-wrap span {
    font-size: 32px;
}
</style>