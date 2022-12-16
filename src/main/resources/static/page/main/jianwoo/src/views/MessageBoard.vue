<template>
    <div>
        <div class="index-main">
            <div class="detail">
                <div class="breadcrumb-position">
                    <span>当前位置:&nbsp;<strong>留言板</strong>
                    </span>
                </div>
                <el-card class="box-card comment-user-info">
                    <div class="title">
                        用户信息
                        <span style="font-weight: 400;font-size: 12px;" class="position-ip">(IP属地: {{
                                !userInfo.userRegion ? '未知' : userInfo.userRegion
                            }})</span>
                    </div>
                    <el-form :inline="true" :rules="rules"
                             :model="userInfo" ref="userInfo"
                    >
                        <div>
                            <el-form-item label="昵称" style="width: 40%" prop="userNick">
                                <el-input v-model="userInfo.userNick" placeholder="昵称格式为[中文数字英文和$_]."/>
                            </el-form-item>
                            <el-form-item label="邮箱" style="width: 40%" prop="contactEmail">
                                <el-input v-model="userInfo.contactEmail" placeholder="当有人回复您的留言, 用于接收通知"/>
                            </el-form-item>
                        </div>
                        <div>
                            <el-form-item label="&nbsp;&nbsp;&nbsp;&nbsp;QQ" style="width: 40%" prop="contactQq">
                                <el-input v-model="userInfo.contactQq"/>
                            </el-form-item>
                            <el-form-item label="&nbsp;&nbsp;微信" style="width: 40%" prop="contactWechat">
                                <el-input v-model="userInfo.contactWechat"/>
                            </el-form-item>
                        </div>
                    </el-form>


                    <el-input v-if="canBeMessage"
                              v-model="messageText"
                              id="comment-input"
                              placeholder="请输入留言, 5-20个字符, 请文明留言, 禁止发表灌水, 违规留言"
                              class="comment-input"
                              @input="messageInput"
                              :rows="5"
                              type="textarea"
                              prop="messageText"
                    />
                    <div v-if="canBeMessage" class="comment-content-btns">
                        <img
                                id="comment-emoji"
                                style="width:20px;height:20px;cursor:pointer;"
                                src="../static/img/emoji.png" @click.stop="() => {}"
                                @click="showEmojiPanel"
                        />
                        <emoji-panel @emojiClick="appendEmoji" v-if="isShowEmojiPanel"
                                     @click.stop="() => {}"></emoji-panel>
                        <button @click="submitMessage()" class="comm-btn">发表</button>
                    </div>
                </el-card>
                <el-card class="comment-content box-card" v-if="count > 0">
            <span class="title" v-if="!this.$route.query.msgOid">
                博客留言({{ count }})
            </span>
                    <span class="title" v-else>
                相关留言
            </span>
                    <span>
                <el-switch
                        v-model="messageQueryParam.orderWay" @change="order"
                        style="float: right;--el-switch-on-color: var(--theme_color); --el-switch-off-color: #ddd"
                        active-text="默认" active-value="10"
                        inactive-text="最新留言" inactive-value="20"
                        v-if="!this.$route.query.msgOid"
                />
            </span>

                    <div class="comment-list" v-for="(msg, index) in messageList">
                        <div :class="'media-body ' + (this.showMsg && this.showMsgOid === msg.oid ? showMsgClass : '')"
                             :id="'comm-' + msg.oid">
                            <a href="javascript:" class="media-left" style="float: left;">
                                <el-avatar :size="46" :src="msg.avatarSrc">
                                    <img
                                            src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                                    />
                                </el-avatar>
                            </a>
                            <div class="pad-btm">
                    <span class="fontColor">
                        <a href="javascript:">{{ msg.userNick }}</a>
                        <a href="javascript:" v-if="msg.flagAdmin"><span class="flag_admin_info">博主</span></a>
                         &nbsp;&nbsp;
                        <br class="mobile_br">
                        <a href="javascript:" style="color:#777" class="position-ip">
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-position"></use>
                            </svg>
                            IP属地: {{ !msg.userRegion ? '未知' : msg.userRegion }}
                        </a>
                    </span>
                                <span class="min-font comm-time">
                        <a href="javascript:">{{ msg.pushTimeDesc }}</a>
                    </span>
                            </div>
                            <div class="message-text">{{ this.entitiestoUtf16(msg.content) }}</div>
                            <div class="comment-footer fontColor">
                                <span class="after-goods" @click="hasPraise" v-if="msg.isPraise"
                                      style="color:red">
                                    <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-dianzan_kuai"></use>
                                    </svg>
                                    <span class="comm-btn-word" style="color:red">&nbsp;赞</span>({{ msg.praiseCount }})</span>
                                <span class="before-goods jwhover" @click="doPraise(msg)" v-else>
                                        <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-zan"></use>
                                        </svg><span class="comm-btn-word">&nbsp;赞</span>({{ msg.praiseCount }})
                                </span>
                                &nbsp;&nbsp;
                                <span @click="replyClick(msg, index)" class="jwhover">
                                    <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-pinglunxiao"></use>
                                    </svg>&nbsp;<span class="comm-btn-word">{{
                                        this.showReply && this.showReplyOid === msg.oid
                                                ? "取消回复"
                                                : ("回复")
                                    }}</span>{{"(" + msg.replyCount + ")"}}
                                </span>
                                &nbsp;&nbsp;
                                <span @click="editClick(msg, index)" class="jwhover"
                                      v-if="msg.flagEdit && !msg.flagDelete">
                                    <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-bianji1"></use>
                                    </svg>&nbsp;<span class="comm-btn-word">{{
                                        this.showEdit && this.showEditOid === msg.oid
                                                ? "取消编辑" : "编辑"
                                    }}</span>
                                </span>

                                &nbsp;&nbsp;
                                <span @click="delClick(msg, index, this.messageList, true)" class="jwhover"
                                      style="color:red"
                                      v-if="msg.flagEdit && !msg.flagDelete">
                                    <svg class="icon" aria-hidden="true">
                                     <use xlink:href="#icon-shanchu"></use>
                                    </svg>&nbsp;<span class="comm-btn-word" style="color:red">删除</span>
                                </span>
                            </div>
                            <el-input
                                    v-if="this.showReply && this.showReplyOid === msg.oid"
                                    v-model="msg.replyText"
                                    :id="'reply-input-' + msg.oid"
                                    :placeholder="!msg.replyText ? '回复' + msg.userNick : ''"
                                    class="comment-reply-input focused"
                                    @input="replyInput(msg)"
                                    :rows="5"
                                    type="textarea"
                            ></el-input>
                            <div v-if="this.showReply && this.showReplyOid === msg.oid" class="comment-reply-btns">
                                <img
                                        id="emoji-btn"
                                        style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                        src="../static/img/emoji.png" @click.stop="() => {}"
                                        @click="showReplyEmojiPanel"
                                />
                                <emoji-panel @emojiClick="appendReplyEmoji($event, msg)"
                                             v-if="this.isShowReplyEmojiPanel && this.showReplyOid === msg.oid"
                                             @click.stop="() => {}"></emoji-panel>
                                <button @click="submitReply(msg)" class="comm-btn">发表</button>
                            </div>
                            <el-input
                                    v-if="this.showEdit && this.showEditOid === msg.oid"
                                    v-model="msg.editContent"
                                    :id="'edit-input-' + msg.oid"
                                    placeholder="编辑留言"
                                    class="comment-reply-input focused"
                                    @input="editInput(msg)"
                                    :rows="5"
                                    type="textarea"
                            ></el-input>
                            <div v-if="this.showEdit && this.showEditOid === msg.oid" class="comment-reply-btns">
                                <img
                                        id="emoji-btn"
                                        style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                        src="../static/img/emoji.png" @click.stop="() => {}"
                                        @click="showEditEmojiPanel"
                                />
                                <emoji-panel @emojiClick="appendEditEmoji($event, msg)"
                                             v-if="this.isShowEditEmojiPanel && this.showEditOid === msg.oid"
                                             @click.stop="() => {}"></emoji-panel>
                                <button @click="submitEdit(msg)" class="comm-btn">更新</button>
                            </div>
                        </div>
                        <div class="comment-content-reply">
                            <div v-for="(rep, repIndex) in msg.replyList">
                                <div :class="'media-body ' + (this.showMsg && this.showMsgOid === rep.oid ? showMsgClass : '')"
                                     :id="'rep-' + rep.oid">
                                    <a href="javascript:" class="media-left" style="float: left;">
                                        <el-avatar :size="46" :src="rep.avatarSrc">
                                            <img
                                                    src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                                            />
                                        </el-avatar>
                                    </a>
                                    <div class="pad-btm">
                            <span class="fontColor">
                                <a href="javascript:">{{ rep.userNick }}</a>
                                <a href="javascript:">&nbsp;回复&nbsp;@{{ rep.parentUserName }}</a>
                                <a href="javascript:" v-if="rep.flagAdmin"><span class="flag_admin_info">博主</span></a>
 &nbsp;&nbsp;
                                <br class="mobile_br">
                                 <a href="javascript:" style="color:#777" class="position-ip">
                                    <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-position"></use>
                                    </svg>
                                    IP属地: {{ !rep.userRegion ? '未知' : rep.userRegion }}
                                </a>

                            </span>
                                        <span class="min-font comm-time">
                                <a href="javascript:">{{ rep.pushTimeDesc }}</a>
                            </span>
                                    </div>
                                    <div class="message-text">{{ this.entitiestoUtf16(rep.content) }}</div>
                                    <div class="comment-footer fontColor">
                                         <span class="after-goods" @click="hasPraise" v-if="rep.isPraise"
                                          style="color:red">
                                            <svg class="icon" aria-hidden="true">
                                                 <use xlink:href="#icon-dianzan_kuai"></use>
                                            </svg>
                                            <span class="comm-btn-word" style="color:red">&nbsp;赞</span>({{ rep.praiseCount }})</span>
                                        <span class="before-goods jwhover" @click="doPraise(rep)" v-else>
                                            <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-zan"></use>
                                            </svg><span class="comm-btn-word">&nbsp;赞</span>({{ rep.praiseCount }})
                                        </span>
                                        &nbsp;&nbsp;
                                        <span @click="replyClick(rep, repIndex)" class="jwhover">
                                            <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-pinglunxiao"></use>
                                            </svg>&nbsp;<span class="comm-btn-word">{{
                                                            this.showReply && this.showReplyOid === rep.oid
                                                        ? "取消回复"
                                                        : ("回复")
                                            }}</span>{{"(" + rep.replyCount + ")"}}
                                        </span>
                                        &nbsp;&nbsp;
                                        <span @click="editClick(rep, repIndex)" class="jwhover"
                                              v-if="rep.flagEdit && !rep.flagDelete">
                                            <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-bianji1"></use>
                                            </svg>&nbsp;<span class="comm-btn-word">{{
                                                this.showEdit && this.showEditOid === rep.oid
                                                        ? "取消编辑" : "编辑"
                                            }}</span>
                                        </span>
                                        &nbsp;&nbsp;
                                        <span @click="delClick(rep, repIndex, msg.replyList, false, msg)" class="jwhover"
                                              style="color:red"
                                              v-if="rep.flagEdit && !rep.flagDelete">
                                            <svg class="icon" aria-hidden="true">
                                                <use xlink:href="#icon-shanchu"></use>
                                            </svg>&nbsp;<span class="comm-btn-word" style="color:red">删除</span>
                                        </span>

                                    </div>

                                    <el-input
                                            v-if="this.showReply && this.showReplyOid === rep.oid"
                                            v-model="rep.replyText"
                                            :id="'reply-input-' + rep.oid"
                                            :placeholder="!rep.replyText ? '回复' + rep.userNick : ''"
                                            class="comment-reply-input focused"
                                            @input="replyInput(rep)"
                                            :rows="5"
                                            type="textarea"
                                    ></el-input>

                                    <div v-if="this.showReply && this.showReplyOid === rep.oid"
                                         class="comment-reply-btns">
                                        <img
                                                id="emoji-btn"
                                                style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                                src="../static/img/emoji.png" @click.stop="() => {}"
                                                @click="showReplyEmojiPanel"
                                        />
                                        <emoji-panel @emojiClick="appendReplyEmoji($event, rep)"
                                                     v-if="this.isShowReplyEmojiPanel && this.showReplyOid === rep.oid"
                                                     @click.stop="() => {}"></emoji-panel>
                                        <button @click="submitReply(rep)" class="comm-btn">发表</button>
                                    </div>
                                    <el-input
                                            v-if="this.showEdit && this.showEditOid === rep.oid"
                                            v-model="rep.editContent"
                                            :id="'edit-input-' + rep.oid"
                                            placeholder="编辑留言"
                                            class="comment-reply-input focused"
                                            @input="editInput(rep)"
                                            :rows="5"
                                            type="textarea"
                                    ></el-input>
                                    <div v-if="this.showEdit && this.showEditOid === rep.oid"
                                         class="comment-reply-btns">
                                        <img
                                                id="emoji-btn"
                                                style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                                src="../static/img/emoji.png" @click.stop="() => {}"
                                                @click="showEditEmojiPanel"
                                        />
                                        <emoji-panel @emojiClick="appendEditEmoji($event, rep)"
                                                     v-if="this.isShowEditEmojiPanel && this.showEditOid === rep.oid"
                                                     @click.stop="() => {}"></emoji-panel>
                                        <button @click="submitEdit(rep)" class="comm-btn">更新</button>
                                    </div>
                                </div>
                            </div>
                            <div class="more-list" v-loading="msg.loading" @click="moreReply(msg)"
                                 v-if="(msg.replyCount > (msg.replyList ? msg.replyList.length : 0)) && this.showMsgOid === -1">
                                <svg class="icon jwhover" aria-hidden="true">
                                    <use xlink:href="#icon-gengduo"></use>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="more-list" v-loading="loading" @click="moreMsg()"
                         v-if="(this.rootMsgCount > (messageList ? messageList.length : 0)) && this.showMsgOid === -1">
                        <svg class="icon jwhover" aria-hidden="true">
                            <use xlink:href="#icon-gengduo"></use>
                        </svg>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";
import EmojiPanel from "@/components/EmojiPanel.vue";

export default {
    name: "MessageBoardComp",
    components: {
        EmojiPanel,
    },
    data() {
        const validateEmail = (rule, value, callback) => {
            var patt = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (value === '') {
                callback(new Error('请填写邮箱.'))
            } else {
                if (!patt.test(value)) {
                    callback(new Error('邮箱格式不正确.'))
                } else {
                    if (value.length > 30) {
                        callback(new Error('邮箱不能大于30个字符.'))
                    } else {
                        callback()
                    }
                }
            }
        }
        const validateNick = (rule, value, callback) => {
            var patt = /^[\u4e00-\u9FFF\d\w_$]+$/;
            if (value === '') {
                callback(new Error('请填写昵称.'))
            } else {
                if (!patt.test(value)) {
                    callback(new Error('昵称只能为[中文数字英文和$_].'))
                } else {
                    if (value.length > 20) {
                        callback(new Error('昵称不能大于20个字符.'))
                    } else {
                        callback()
                    }
                }
            }
        }
        const validateQq = (rule, value, callback) => {
            var patt = /^[1-9][0-9]{4,}$/;
            if (value && !patt.test(value)) {
                callback(new Error('QQ号码格式不正确.'))
            } else {
                callback()
            }
        }
        const validateWeixin = (rule, value, callback) => {
            var patt = /^[a-zA-Z][a-zA-Z\d_-]{5,19}$/;
            if (value && !patt.test(value)) {
                callback(new Error('微信号格式不正确.'))
            } else {
                callback()
            }
        }

        const validateMessage = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请填写留言内容.'))
            } else {
                if (value.length < 5 || value.length > 200) {
                    callback(new Error('留言内容为5-200个字符.'))
                } else {
                    callback()
                }
            }
        }

        return {
            messageList: [],
            count: 0,
            rootMsgCount: 0,
            messageQueryParam: {
                artOid: this.artOid,
                parentOid: 0,
                replyRootOid: null,
                refOid: null,
                page: 1,
                orderWay: '10',
            },
            showReply: false,
            showReplyOid: -1,
            replyText: '',
            messageText: "",
            canBeMessage: true,
            canBeMessageGlobal: true,
            userInfo: {
                userNick: '',
                userRegion: '',
                contactEmail: '',
                contactQq: '',
                contactWechat: '',
            },
            isShowEmojiPanel: false,
            isShowReplyEmojiPanel: false,
            lastLength: 0,
            msgOid: -1,
            showMsg: false,
            showMsgOid: -1,
            showMsgClass: '',
            showMsgFirst: false,
            loading: false,
            showEdit: false,
            showEditOid: -1,
            isShowEditEmojiPanel: false,
            rules: {
                contactEmail: [
                    {validator: validateEmail, required: true, trigger: ['blur', 'change']},
                ],
                userNick: [
                    {validator: validateNick, required: true, trigger: ['blur', 'change']},
                ],
                contactQq: [
                    {validator: validateQq, trigger: ['blur', 'change']},
                ],
                contactWechat: [
                    {validator: validateWeixin, trigger: ['blur', 'change']},
                ],

                messageText: [
                    {validator: validateMessage, trigger: ['blur', 'change']},
                ],

            },

        }
    },

    mounted() {
        this.initQueryParams();
        var that = this;
        this.getmessageList(this.messageQueryParam, function () {
            that.$nextTick(function () {
                if (!that.showMsgFirst && that.showMsgOid !== -1) {
                    if (document.getElementById('rep-' + that.showMsgOid)) {
                        document.getElementById('rep-' + that.showMsgOid).scrollIntoView()
                    } else if (document.getElementById('msg-' + that.showMsgOid)) {
                        document.getElementById('msg-' + that.showMsgOid).scrollIntoView()
                    }
                    that.showMsg = true;
                    that.showMsgFirst = true;
                    that.showMsgClass = 'hidd-comm-backgroud';

                }

            })

        });
        window.document.title = "留言板_简窝博客"

        this.getCanBeMessage();
        window.addEventListener(
                'click',
                () => {
                    if (this.isShowEmojiPanel) {
                        this.isShowEmojiPanel = false;
                    }
                    if (this.isShowReplyEmojiPanel) {
                        this.isShowReplyEmojiPanel = false;
                    }
                    if (this.isShowEditEmojiPanel) {
                        this.isShowEditEmojiPanel = false;
                    }

                },
                false
        );
    },
    created() {

    },
    methods: {
        getmessageList: function (param, callback) {
            postJson("/message/board/query/page/list", param).then((res) => {
                if (param.parentOid === 0 && param.replyRootOid === null
                        && param.page === 1) {
                    this.messageList = res.messageList;
                    this.count = res.count;
                    this.rootMsgCount = res.rootMsgCount;
                    if (res.userInfo) {
                        this.userInfo = res.userInfo;
                    }
                    if (this.showMsgFirst) {
                        this.showMsg = false;
                        this.showMsgClass = '';

                    }

                }
                typeof callback === 'function' && callback(res);

            });
        },
        getCanBeMessage: function (param) {
            postJson("/config/query", {key: 'GLOBAL_MESSAGE_ALLOW'}).then((res) => {
                this.canBeMessageGlobal = res.data && res.data.value === 'TRUE';
                if (this.canBeMessage) {
                    this.canBeMessage = this.canBeMessageGlobal;
                }
            });
        },
        replyClick: function (item) {
            if (item.flagDelete) {
                this.$message({
                    showClose: true,
                    message: '该留言已删除!',
                    grouping: true,
                    type: 'warning'
                })
                return;
            }
            if (!this.canBeMessage) {
                var msg = ""
                if (!this.canBeMessageGlobal) {
                    msg = "网站的留言系统已经关闭!"
                }
                this.$message({
                    showClose: true,
                    message: msg,
                    grouping: true,
                    type: 'warning'
                })
                return;
            }
            if (this.showEdit) {
                this.showEdit = false;
                this.showEditOid = -1;
            }
            if (this.showReplyOid === item.oid || this.showReplyOid === -1) {
                this.showReply = !this.showReply;
                this.showReplyOid = this.showReplyOid === -1 ? item.oid : -1;
            } else {
                // this.showReply = true
                this.showReplyOid = item.oid;

            }


        },
        editClick: function (item) {
            if (this.showReply) {
                this.showReply = false;
                this.showReplyOid = -1;
            }
            if (this.showEditOid === item.oid || this.showEditOid === -1) {
                this.showEdit = !this.showEdit;
                this.showEditOid = this.showEditOid === -1 ? item.oid : -1;
            } else {
                // this.showReply = true
                this.showEditOid = item.oid;

            }
            item.editContent = item.content;


        },
        delClick: function (item, index, msgList, isRootMsg, msg) {
            if (this.showEdit) {
                this.showEdit = false;
                this.showEditOid = -1;
            }
            if (this.showReply) {
                this.showReply = false;
                this.showReplyOid = -1;
            }
            this.$confirm(
                    '确定要删除此条留言吗?',
                    'Warning',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
            ).then(() => {
                let param = {};
                param.msgOid = item.oid
                postJson("/message/board/remove", param).then((res) => {
                    if (res.status === '000000') {
                        this.$message({
                            showClose: true,
                            message: '删除成功!',
                            grouping: true,
                            type: 'success'
                        });
                        if (isRootMsg && item.replyList && item.replyList.length > 0) {
                            item.content = '该留言已经删除';
                            item.flagDelete = true;
                        } else {
                            msgList.splice(index, 1);

                        }
                        if (isRootMsg){
                            this.count = this.count - 1
                        }else {
                            msg.replyCount = msg.replyCount - 1
                            this.count = this.count - 1
                        }
                    }


                });

            }).catch(() => {
            });


        },
        messageInput(item) {
            if (!item) {
                return;
            }
            const currentLength = item.length;
            const el = document.getElementById("comment-input");
            // 判断字数如果比之前少了，说明内容正在减少，需要清除高度样式，重新获取
            if (currentLength < this.lastLength) {
                el.style.height = "";
            }
            const currentHeight = el.scrollHeight;
            // 如果内容高度发生了变化，再去设置高度值
            if (currentHeight > el.offsetHeight) {
                el.style.height = currentHeight + "px";
            }
            this.lastLength = currentLength;
        },
        replyInput(reply) {
            if (!reply.replyText) {
                return;
            }
            let item = reply.replyText;
            let oid = reply.oid;
            const currentLength = item.length;
            const el = document.getElementById("reply-input-" + oid);

            // 判断字数如果比之前少了，说明内容正在减少，需要清除高度样式，重新获取
            if (currentLength < this.lastLength) {
                el.style.height = "";
            }
            const currentHeight = el.scrollHeight;
            // 如果内容高度发生了变化，再去设置高度值
            if (currentHeight > el.offsetHeight) {
                el.style.height = currentHeight + "px";
            }
            this.lastLength = currentLength;
        },
        editInput(msg) {
            if (!msg.content) {
                return;
            }
            let item = msg.content;
            let oid = msg.oid;
            const currentLength = item.length;
            const el = document.getElementById("edit-input-" + oid);

            // 判断字数如果比之前少了，说明内容正在减少，需要清除高度样式，重新获取
            if (currentLength < this.lastLength) {
                el.style.height = "";
            }
            const currentHeight = el.scrollHeight;
            // 如果内容高度发生了变化，再去设置高度值
            if (currentHeight > el.offsetHeight) {
                el.style.height = currentHeight + "px";
            }
            this.lastLength = currentLength;
        },
        // 表情转码
        utf16toEntities(str) {
            const patt = /[\ud800-\udbff][\udc00-\udfff]/g; // 检测utf16字符正则
            str = str.replace(patt, char => {
                let H;
                let L;
                let code;
                let s;
                if (char.length === 2) {
                    H = char.charCodeAt(0); // 取出高位
                    L = char.charCodeAt(1); // 取出低位
                    code = (H - 0xd800) * 0x400 + 0x10000 + L - 0xdc00; // 转换算法
                    s = `&#${code};`;
                } else {
                    s = char;
                }
                return s;
            });
            return str;
        },
        // 表情解码
        entitiestoUtf16(strObj) {
            const patt = /&#\d+;/g;
            const arr = strObj.match(patt) || [];
            let H;
            let L;
            let code;
            for (let i = 0; i < arr.length; i += 1) {
                code = arr[i];
                code = code.replace("&#", "").replace(";", "");
                // 高位
                H = Math.floor((code - 0x10000) / 0x400) + 0xd800;
                // 低位
                L = ((code - 0x10000) % 0x400) + 0xdc00;
                code = `&#${code};`;
                const s = String.fromCharCode(H, L);
                strObj = strObj.replace(code, s);
            }
            return strObj;
        },

        submitMessage: function () {
            // console.log(this.utf16toEntities(this.messageText))
            this.$refs['userInfo'].validate((valid) => {
                if (valid) {

                    if (this.messageText === '') {
                        this.$message({
                            showClose: true,
                            message: '请填写留言内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (this.messageText.length < 5 || this.messageText.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '留言内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.postMessage(this.utf16toEntities(this.messageText), 0, function () {
                                that.messageText = '';

                            })
                        }
                    }
                } else {
                    this.$message({
                        showClose: true,
                        message: '请填写用户基础信息.',
                        grouping: true,
                        type: 'error'
                    })
                    return false;
                }
            });
        },
        submitReply: function (reply) {
            // console.log(reply)
            this.$refs['userInfo'].validate((valid) => {
                if (valid) {

                    if (!reply.replyText) {
                        this.$message({
                            showClose: true,
                            message: '请填写留言内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (reply.replyText.length < 5 || reply.replyText.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '留言内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.postMessage(this.utf16toEntities(reply.replyText), reply.oid, function () {
                                reply.replyText = '';
                                that.showReply = false;
                                that.showReplyOid = -1;
                            })
                        }
                    }
                } else {
                    this.$message({
                        showClose: true,
                        message: '请填写用户基础信息.',
                        grouping: true,
                        type: 'error'
                    })
                    return false;
                }
            });
        },
        submitEdit: function (msg) {
            // console.log(reply)
            this.$refs['userInfo'].validate((valid) => {
                if (valid) {

                    if (!msg.editContent) {
                        this.$message({
                            showClose: true,
                            message: '请填写留言内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (msg.editContent.length < 5 || msg.editContent.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '留言内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.updateMessage(msg.oid, this.utf16toEntities(msg.editContent), function () {
                                that.showEdit = false;
                                msg.content = msg.editContent;
                                that.showEditOid = -1;
                            })
                        }
                    }
                } else {
                    this.$message({
                        showClose: true,
                        message: '请填写用户基础信息.',
                        grouping: true,
                        type: 'error'
                    })
                    return false;
                }
            });
        },
        showEmojiPanel() {
            this.isShowEmojiPanel = !this.isShowEmojiPanel;
            this.isShowReplyEmojiPanel = false;
            this.isShowEditEmojiPanel = false;
        },
        appendEmoji(code) {
            this.messageText += this.entitiestoUtf16(code);
        },
        showReplyEmojiPanel() {
            this.isShowReplyEmojiPanel = !this.isShowReplyEmojiPanel;
            this.isShowEmojiPanel = false;
            this.isShowEditEmojiPanel = false;
        },
        appendReplyEmoji(code, msg) {
            if (msg.replyText === undefined) {
                msg.replyText = ""
            }
            msg.replyText += this.entitiestoUtf16(code);
        },
        showEditEmojiPanel() {
            this.isShowEditEmojiPanel = !this.isShowEditEmojiPanel;
            this.isShowEmojiPanel = false;
            this.isShowReplyEmojiPanel = false;
        },
        appendEditEmoji(code, msg) {
            if (msg.content === undefined) {
                msg.content = ""
            }
            msg.content += this.entitiestoUtf16(code);
        },
        postMessage(text, pid, callback) {
            let param = {};
            param.msgParentId = pid;
            param.content = text;
            param.userNick = this.userInfo.userNick;
            param.contactQq = this.userInfo.contactQq;
            param.contactWechat = this.userInfo.contactWechat;
            param.contactEmail = this.userInfo.contactEmail;
            postJson("/message/board/create", param).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '留言成功!',
                        grouping: true,
                        type: 'success'
                    });
                    this.getmessageList(this.messageQueryParam);
                    typeof callback === 'function' && callback();
                }


            });
        },
        updateMessage(oid, text, callback) {
            let param = {};
            param.oid = oid;
            param.content = text;
            param.userNick = this.userInfo.userNick;
            param.contactQq = this.userInfo.contactQq;
            param.contactWechat = this.userInfo.contactWechat;
            param.contactEmail = this.userInfo.contactEmail;
            postJson("/message/board/update", param).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '留言更新成功!',
                        grouping: true,
                        type: 'success'
                    });
                    typeof callback === 'function' && callback();
                }


            });
        },
        order: function (v) {
            this.messageQueryParam.page = 1;
            this.getmessageList(this.messageQueryParam);

        },
        initQueryParams() {
            if (this.$route.query.msgOrderWay) {
                this.messageQueryParam.orderWay = this.$route.query.msgOrderWay;
            }
            if (this.$route.query.msgOid) {
                this.showMsgOid = Number(this.$route.query.msgOid);
                this.messageQueryParam.refOid = this.showMsgOid;
            }
        },
        doPraise: function (item) {
            if (item.flagDelete) {
                this.$message({
                    showClose: true,
                    message: '该留言已删除!',
                    grouping: true,
                    type: 'warning'
                })
                return;
            }
            postJson("/message/board/praise/add", {msgOid: item.oid}).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '点赞成功!',
                        grouping: true,
                        type: 'success'
                    })
                    item.praiseCount = item.praiseCount + 1;
                    item.isPraise = true;
                }
            });
        },
        hasPraise: function () {
            this.$message({
                showClose: true,
                message: '您已经赞过该留言啦!',
                grouping: true,
                type: 'warning'
            })
        },
        moreReply: function (msg) {
            msg.loading = true;
            var replyParam = msg.replyParam;
            if (!replyParam) {
                replyParam = {};
            }

            replyParam.replyRootOid = msg.oid;
            replyParam.parentOid = null;
            replyParam.orderWay = '10';
            if (!replyParam.page) {
                replyParam.page = 1
            }
            replyParam.page = replyParam.page + 1;
            this.getmessageList(replyParam, (res) => {
                if (res.messageList) {
                    if (!msg.replyList) {
                        msg.replyList = [];
                    }
                    msg.replyList.push(...res.messageList);

                }
                msg.loading = false;
            });
        },
        moreMsg: function () {
            this.loading = true;
            if (!this.messageQueryParam.page) {
                this.messageQueryParam.page = 1
            }
            this.messageQueryParam.page = this.messageQueryParam.page + 1;
            this.getmessageList(this.messageQueryParam, (res) => {
                if (res.messageList) {
                    this.messageList.push(...res.messageList);

                }
                this.loading = false;
            });
        }
    },
    watch: {}
}
</script>

<style scoped>

</style>