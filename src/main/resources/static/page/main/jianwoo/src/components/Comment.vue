<template>
    <div>
        <el-card class="box-card" id="comment">
            <div class="title">
                用户信息
                <span style="font-weight: 400;font-size: 12px;">(IP属地: {{
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
                        <el-input v-model="userInfo.contactEmail" placeholder="当有人回复您的评论, 用于接收通知"/>
                    </el-form-item>
                </div>
                <div>
                    <el-form-item label="&nbsp;&nbsp;QQ" style="width: 40%" prop="contactQq">
                        <el-input v-model="userInfo.contactQq"/>
                    </el-form-item>
                    <el-form-item label="&nbsp;&nbsp;微信" style="width: 40%" prop="contactWechat">
                        <el-input v-model="userInfo.contactWechat"/>
                    </el-form-item>
                </div>
            </el-form>


            <el-input v-if="canBeComment"
                      v-model="commentText"
                      id="comment-input"
                      placeholder="请输入评论, 5-20个字符, 请文明评论, 禁止发表灌水, 违规评论"
                      class="comment-input"
                      @input="commentInput"
                      :rows="5"
                      type="textarea"
                      prop="commentText"
            />
            <div v-if="canBeComment" class="comment-content-btns">
                <img
                        id="comment-emoji"
                        style="width:20px;height:20px;cursor:pointer;"
                        src="../static/img/emoji.png" @click.stop="() => {}"
                        @click="showEmojiPanel"
                />
                <emoji-panel @emojiClick="appendEmoji" v-if="isShowEmojiPanel" @click.stop="() => {}"></emoji-panel>
                <button @click="submitComment()" class="comm-btn">发表</button>
            </div>
        </el-card>
        <el-card class="comment-content box-card" v-if="count > 0">
            <span class="title" v-if="!this.$route.query.commOid">
                热门评论({{ count }})
            </span>
            <span class="title" v-else>
                相关评论
            </span>
            <span>
                <el-switch
                        v-model="commentQueryParam.orderWay" @change="order"
                        style="float: right;--el-switch-on-color: #B40BB7; --el-switch-off-color: #ddd"
                        active-text="默认" active-value="10"
                        inactive-text="最新评论" inactive-value="20"
                        v-if="!this.$route.query.commOid"
                />
            </span>

            <div class="comment-list" v-for="(comm, index) in commentList">
                <div :class="'media-body ' + (this.showComm && this.showCommOid === comm.oid ? showCommClass : '')"
                     :id="'comm-' + comm.oid">
                    <a href="javascript:" class="media-left" style="float: left;">
                        <el-avatar :size="46" :src="comm.avatarSrc">
                            <img
                                    src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                            />
                        </el-avatar>
                    </a>
                    <div class="pad-btm">
                    <span class="fontColor">
                        <a href="javascript:">{{ comm.userNick }}</a>
                         &nbsp;&nbsp;
                        <span v-if="comm.floorNumber">{{ comm.floorNumber }}楼</span>
                         &nbsp;&nbsp;
                        <a href="javascript:" style="color:#777">
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-position"></use>
                            </svg>
                            IP属地: {{ !comm.userRegion ? '未知' : comm.userRegion }}
                        </a>

                    </span>
                        <span class="min-font comm-time">
                        <a href="javascript:">{{ comm.commentTimeDesc }}</a>
                    </span>
                    </div>
                    <div class="message-text">{{ this.entitiestoUtf16(comm.content) }}</div>
                    <div class="comment-footer fontColor">
                        <span class="after-goods" @click="hasPraise" v-if="comm.isPraise"
                              style="color:red">
                        <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-dianzan_kuai"></use>
                        </svg>&nbsp;赞({{ comm.praiseCount }})
                            </span>
                        <span class="before-goods jwhover" @click="doPraise(comm)" v-else>
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zan"></use>
                            </svg>&nbsp;赞({{ comm.praiseCount }})
                        </span>
                        &nbsp;&nbsp;
                        <span @click="replyClick(comm, index)" class="jwhover">
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-pinglunxiao"></use>
                                </svg>&nbsp;{{
                                this.showReply && this.showReplyOid === comm.oid
                                        ? "取消回复"
                                        : ("评论(" + comm.replyCount + ")")
                            }}
                        </span>
                        &nbsp;&nbsp;
                        <span @click="editClick(comm, index)" class="jwhover" v-if="comm.flagEdit && !comm.flagDelete">
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-bianji1"></use>
                                </svg>&nbsp;{{
                                this.showEdit && this.showEditOid === comm.oid
                                        ? "取消编辑" : "编辑"
                            }}
                        </span>

                        &nbsp;&nbsp;
                        <span @click="delClick(comm, index, this.commentList, true)" class="jwhover" style="color:red"
                              v-if="comm.flagEdit && !comm.flagDelete">
                            <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-shanchu"></use>
                                </svg>&nbsp;删除
                        </span>

                    </div>
                    <el-input
                            v-if="this.showReply && this.showReplyOid === comm.oid"
                            v-model="comm.replyText"
                            :id="'reply-input-' + comm.oid"
                            :placeholder="!comm.replyText ? '回复' + comm.userNick : ''"
                            class="comment-reply-input focused"
                            @input="replyInput(comm)"
                            :rows="5"
                            type="textarea"
                    ></el-input>
                    <div v-if="this.showReply && this.showReplyOid === comm.oid" class="comment-reply-btns">
                        <img
                                id="emoji-btn"
                                style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                src="../static/img/emoji.png" @click.stop="() => {}"
                                @click="showReplyEmojiPanel"
                        />
                        <emoji-panel @emojiClick="appendReplyEmoji($event, comm)"
                                     v-if="this.isShowReplyEmojiPanel && this.showReplyOid === comm.oid"
                                     @click.stop="() => {}"></emoji-panel>
                        <button @click="submitReply(comm)" class="comm-btn">发表</button>
                    </div>
                    <el-input
                            v-if="this.showEdit && this.showEditOid === comm.oid"
                            v-model="comm.editContent"
                            :id="'edit-input-' + comm.oid"
                            placeholder="编辑评论"
                            class="comment-reply-input focused"
                            @input="editInput(comm)"
                            :rows="5"
                            type="textarea"
                    ></el-input>
                    <div v-if="this.showEdit && this.showEditOid === comm.oid" class="comment-reply-btns">
                        <img
                                id="emoji-btn"
                                style="width:20px;height:20px;cursor:pointer;margin:0 30px;"
                                src="../static/img/emoji.png" @click.stop="() => {}"
                                @click="showEditEmojiPanel"
                        />
                        <emoji-panel @emojiClick="appendEditEmoji($event, comm)"
                                     v-if="this.isShowEditEmojiPanel && this.showEditOid === comm.oid"
                                     @click.stop="() => {}"></emoji-panel>
                        <button @click="submitEdit(comm)" class="comm-btn">更新</button>
                    </div>
                </div>
                <div class="comment-content-reply">
                    <div v-for="(rep, repIndex) in comm.replyList">
                        <div :class="'media-body ' + (this.showComm && this.showCommOid === rep.oid ? showCommClass : '')"
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
                                &nbsp;&nbsp;
                                 <a href="javascript:" style="color:#777">
                                    <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-position"></use>
                                    </svg>
                                    IP属地: {{ !rep.userRegion ? '未知' : rep.userRegion }}
                                </a>
                            </span>
                                <span class="min-font comm-time">
                                <a href="javascript:">{{ rep.commentTimeDesc }}</a>
                            </span>
                            </div>
                            <div class="message-text">{{ this.entitiestoUtf16(rep.content) }}</div>
                            <div class="comment-footer fontColor">
                                <span class="after-goods" @click="hasPraise" v-if="rep.isPraise"
                                      style="color:red">
                                <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-dianzan_kuai"></use>
                                </svg>
                                &nbsp;赞({{ rep.praiseCount }})
                                </span>
                                <span class="before-goods jwhover" @click="doPraise(rep)" v-else>
                                    <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-zan"></use>
                                    </svg>&nbsp;赞({{ rep.praiseCount }})
                                </span>
                                &nbsp;&nbsp;
                                <span @click="replyClick(rep, repIndex)" class="jwhover">
                                    <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-pinglunxiao"></use>
                                    </svg>&nbsp;{{
                                        this.showReply && this.showReplyOid === rep.oid
                                                ? "取消回复"
                                                : ("评论(" + rep.replyCount + ")")
                                    }}
                                </span>
                                &nbsp;&nbsp;
                                <span @click="editClick(rep, repIndex)" class="jwhover"
                                      v-if="rep.flagEdit && !rep.flagDelete">
                                    <svg class="icon" aria-hidden="true">
                                    <use xlink:href="#icon-bianji1"></use>
                                    </svg>&nbsp;{{
                                        this.showEdit && this.showEditOid === rep.oid
                                                ? "取消编辑" : "编辑"
                                    }}
                                </span>
                                &nbsp;&nbsp;
                                <span @click="delClick(rep, repIndex, comm.replyList, false, comm)" class="jwhover"
                                      style="color:red"
                                      v-if="rep.flagEdit && !rep.flagDelete">
                                    <svg class="icon" aria-hidden="true">
                                         <use xlink:href="#icon-shanchu"></use>
                                    </svg>&nbsp;删除
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

                            <div v-if="this.showReply && this.showReplyOid === rep.oid" class="comment-reply-btns">
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
                                    placeholder="编辑评论"
                                    class="comment-reply-input focused"
                                    @input="editInput(rep)"
                                    :rows="5"
                                    type="textarea"
                            ></el-input>
                            <div v-if="this.showEdit && this.showEditOid === rep.oid" class="comment-reply-btns">
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
                    <div class="more-list" v-loading="comm.loading" @click="moreReply(comm)"
                         v-if="(comm.replyCount > (comm.replyList ? comm.replyList.length : 0)) && this.showCommOid === -1">
                        <svg class="icon jwhover" aria-hidden="true">
                            <use xlink:href="#icon-gengduo"></use>
                        </svg>
                    </div>
                </div>
            </div>
            <div class="more-list" v-loading="loading" @click="moreComm()"
                 v-if="(this.rootCommCount > (commentList ? commentList.length : 0)) && this.showCommOid === -1">
                <svg class="icon jwhover" aria-hidden="true">
                    <use xlink:href="#icon-gengduo"></use>
                </svg>
            </div>
        </el-card>

    </div>
</template>

<script>
import {postJson} from "@/common/js/postJson";
import EmojiPanel from "@/components/EmojiPanel.vue";


export default {
    name: "Comment",
    props: {
        artOid: {
            type: String,
        },
        thisArtCanBeComm: {
            type: Boolean,
        },
    },
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

        const validateComment = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请填写评论内容.'))
            } else {
                if (value.length < 5 || value.length > 200) {
                    callback(new Error('评论内容为5-200个字符.'))
                } else {
                    callback()
                }
            }
        }

        return {
            commentList: [],
            count: 0,
            rootCommCount: 0,
            commentQueryParam: {
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
            commentText: "",
            canBeComment: true,
            canBeCommentGlobal: true,
            // thisArtCanBeComm: true,
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
            commOid: -1,
            showComm: false,
            showCommOid: -1,
            showCommClass: '',
            showCommFirst: false,
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

                commentText: [
                    {validator: validateComment, trigger: ['blur', 'change']},
                ],

            },

        }
    },
    mounted() {
        this.initQueryParams();
        var that = this;
        this.getCommentList(this.commentQueryParam, function () {
            that.$nextTick(function () {
                if (!that.showCommFirst && that.showCommOid !== -1) {
                    if (document.getElementById('rep-' + that.showCommOid)) {
                        document.getElementById('rep-' + that.showCommOid).scrollIntoView()
                    } else if (document.getElementById('comm-' + that.showCommOid)) {
                        document.getElementById('comm-' + that.showCommOid).scrollIntoView()
                    }
                    that.showComm = true;
                    that.showCommFirst = true;
                    that.showCommClass = 'hidd-comm-backgroud';

                }

            })

        });


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
        getCommentList: function (param, callback) {
            postJson("/comment/article/query/page/list", param).then((res) => {
                if (param.parentOid === 0 && param.replyRootOid === null
                        && param.page === 1) {
                    this.commentList = res.commentList;
                    this.count = res.count;
                    this.rootCommCount = res.rootCommCount;
                    if (res.userInfo) {
                        this.userInfo = res.userInfo;
                    }
                    if (this.showCommFirst) {
                        this.showComm = false;
                        this.showCommClass = '';

                    }

                }
                typeof callback === 'function' && callback(res);

            });
        },
        getCanBeComment: function (param) {
            postJson("/config/query", {key: 'GLOBAL_COMMENT_ALLOW'}).then((res) => {
                this.canBeCommentGlobal = res.data.value === 'TRUE';
                if (this.canBeComment) {
                    this.canBeComment = this.canBeCommentGlobal;
                }
            });
        },
        replyClick: function (item) {
            if (item.flagDelete) {
                this.$message({
                    showClose: true,
                    message: '该评论已删除!',
                    grouping: true,
                    type: 'warning'
                })
                return;
            }
            if (!this.canBeComment) {
                var msg = "该文章已关闭评论!"
                if (!this.canBeCommentGlobal) {
                    msg = "网站的评论系统已经关闭!"
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
        delClick: function (item, index, commList, isRootComm, comm) {
            if (this.showEdit) {
                this.showEdit = false;
                this.showEditOid = -1;
            }
            if (this.showReply) {
                this.showReply = false;
                this.showReplyOid = -1;
            }
            this.$confirm(
                    '确定要删除此条评论吗?',
                    'Warning',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
            ).then(() => {
                let param = {};
                param.commOid = item.oid
                postJson("/comment/remove", param).then((res) => {
                    if (res.status === '000000') {
                        this.$message({
                            showClose: true,
                            message: '删除成功!',
                            grouping: true,
                            type: 'success'
                        });
                        if (isRootComm && item.replyList && item.replyList.length > 0) {
                            item.content = '该评论已经删除';
                            item.flagDelete = true;
                        } else {
                            commList.splice(index, 1);

                        }
                        if (isRootComm){
                            this.count = this.count - 1
                        }else {
                            comm.replyCount = comm.replyCount - 1
                            this.count = this.count - 1
                        }
                    }


                });

            }).catch(() => {
            });


        },
        commentInput(item) {
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
        editInput(comm) {
            if (!comm.content) {
                return;
            }
            let item = comm.content;
            let oid = comm.oid;
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

        submitComment: function () {
            // console.log(this.utf16toEntities(this.commentText))
            this.$refs['userInfo'].validate((valid) => {
                if (valid) {

                    if (this.commentText === '') {
                        this.$message({
                            showClose: true,
                            message: '请填写评论内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (this.commentText.length < 5 || this.commentText.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '评论内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.postComment(this.utf16toEntities(this.commentText), 0, function () {
                                that.commentText = '';

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
                            message: '请填写评论内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (reply.replyText.length < 5 || reply.replyText.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '评论内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.postComment(this.utf16toEntities(reply.replyText), reply.oid, function () {
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
        submitEdit: function (comm) {
            // console.log(reply)
            this.$refs['userInfo'].validate((valid) => {
                if (valid) {

                    if (!comm.editContent) {
                        this.$message({
                            showClose: true,
                            message: '请填写评论内容.',
                            grouping: true,
                            type: 'error'
                        })
                    } else {
                        if (comm.editContent.length < 5 || comm.editContent.length > 200) {
                            this.$message({
                                showClose: true,
                                message: '评论内容为5-200个字符.',
                                grouping: true,
                                type: 'error'
                            })
                        } else {
                            let that = this;
                            this.updateComment(comm.oid, this.utf16toEntities(comm.editContent), function () {
                                that.showEdit = false;
                                comm.content = comm.editContent;
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
            this.commentText += this.entitiestoUtf16(code);
        },
        showReplyEmojiPanel() {
            this.isShowReplyEmojiPanel = !this.isShowReplyEmojiPanel;
            this.isShowEmojiPanel = false;
            this.isShowEditEmojiPanel = false;
        },
        appendReplyEmoji(code, comm) {
            if (comm.replyText === undefined) {
                comm.replyText = ""
            }
            comm.replyText += this.entitiestoUtf16(code);
        },
        showEditEmojiPanel() {
            this.isShowEditEmojiPanel = !this.isShowEditEmojiPanel;
            this.isShowEmojiPanel = false;
            this.isShowReplyEmojiPanel = false;
        },
        appendEditEmoji(code, comm) {
            if (comm.content === undefined) {
                comm.content = ""
            }
            comm.content += this.entitiestoUtf16(code);
        },
        postComment(text, pid, callback) {
            let param = {};
            param.artId = this.artOid;
            param.commentParentId = pid;
            param.commentText = text;
            param.userNick = this.userInfo.userNick;
            param.contactQq = this.userInfo.contactQq;
            param.contactWechat = this.userInfo.contactWechat;
            param.contactEmail = this.userInfo.contactEmail;
            postJson("/comment/create", param).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '评论成功!',
                        grouping: true,
                        type: 'success'
                    });
                    this.getCommentList(this.commentQueryParam);
                    typeof callback === 'function' && callback();
                }


            });
        },
        updateComment(oid, text, callback) {
            let param = {};
            param.oid = oid;
            param.artId = this.artOid;
            param.commentText = text;
            param.userNick = this.userInfo.userNick;
            param.contactQq = this.userInfo.contactQq;
            param.contactWechat = this.userInfo.contactWechat;
            param.contactEmail = this.userInfo.contactEmail;
            postJson("/comment/update", param).then((res) => {
                if (res.status === '000000') {
                    this.$message({
                        showClose: true,
                        message: '评论更新成功!',
                        grouping: true,
                        type: 'success'
                    });
                    typeof callback === 'function' && callback();
                }


            });
        },
        order: function (v) {
            this.commentQueryParam.page = 1;
            this.getCommentList(this.commentQueryParam);

        },
        initQueryParams() {
            if (this.$route.query.commOrderWay) {
                this.commentQueryParam.orderWay = this.$route.query.commOrderWay;
            }
            if (this.$route.query.commOid) {
                this.showCommOid = Number(this.$route.query.commOid);
                this.commentQueryParam.refOid = this.showCommOid;
            }
        },
        doPraise: function (item) {
            if (item.flagDelete) {
                this.$message({
                    showClose: true,
                    message: '该评论已删除!',
                    grouping: true,
                    type: 'warning'
                })
                return;
            }
            postJson("/comment/praise/add", {commOid: item.oid}).then((res) => {
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
                message: '您已经赞过该评论啦!',
                grouping: true,
                type: 'warning'
            })
        },
        moreReply: function (comm) {
            comm.loading = true;
            var replyParam = comm.replyParam;
            if (!replyParam) {
                replyParam = {};
            }

            replyParam.artOid = this.artOid;
            replyParam.replyRootOid = comm.oid;
            replyParam.parentOid = null;
            replyParam.orderWay = '10';
            if (!replyParam.page) {
                replyParam.page = 1
            }
            replyParam.page = replyParam.page + 1;
            this.getCommentList(replyParam, (res) => {
                if (res.commentList) {
                    if (!comm.replyList) {
                        comm.replyList = [];
                    }
                    comm.replyList.push(...res.commentList);

                }
                comm.loading = false;
            });
        },
        moreComm: function () {
            this.loading = true;
            if (!this.commentQueryParam.page) {
                this.commentQueryParam.page = 1
            }
            this.commentQueryParam.page = this.commentQueryParam.page + 1;
            this.getCommentList(this.commentQueryParam, (res) => {
                if (res.commentList) {
                    this.commentList.push(...res.commentList);

                }
                this.loading = false;
            });
        }
    },
    watch: {
        thisArtCanBeComm: function (newVal, oldVal) {
            this.canBeComment = this.thisArtCanBeComm
            this.getCanBeComment();
        }
    }
}
</script>

<style lang="scss">

.show-comm {
    -webkit-animation: comm 1s;
    -moz-animation: comm 1s;
    animation: comm 1s;

}

@-webkit-keyframes comm {
    0% {
        background-color: rgba(230, 247, 255, .1)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)

    }
    100% {
        background-color: rgba(230, 247, 255, .7)

    }
}

@-moz-keyframes comm {
    0% {
        background-color: rgba(230, 247, 255, .1)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)

    }
    100% {
        background-color: rgba(230, 247, 255, .7)


    }
}

@keyframes comm {
    0% {
        background-color: rgba(230, 247, 255, .1)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)

    }
    100% {
        background-color: rgba(230, 247, 255, .7)


    }
}


.hidd-comm-backgroud {
    -webkit-animation: hidd-comm 1s;
    -moz-animation: hidd-comm 1s;
    animation: hidd-comm 1s;

}

@-webkit-keyframes hidd-comm {
    0% {
        background-color: rgba(230, 247, 255, .7)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)
    }
    100% {
        background-color: rgba(230, 247, 255, .1)
    }
}

@-moz-keyframes hidd-comm {
    0% {
        background-color: rgba(230, 247, 255, .7)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)
    }
    100% {
        background-color: rgba(230, 247, 255, .1)
    }
}

@keyframes hidd-comm {
    0% {
        background-color: rgba(230, 247, 255, .7)
    }
    50% {
        background-color: rgba(230, 247, 255, .5)
    }
    100% {
        background-color: rgba(230, 247, 255, .1)
    }
}

.fontColor a, .min-font a, .fontColor span {
    color: #555;
    text-decoration: none;
    font-size: 14px;
    /*line-height: 46px;*/
}

.pad-btm {
    padding-top: 17px;
    height: 50px;

}

.comment-content, .comment-user-info {
    display: block;
    padding: 20px 15px;
    margin: 10px 0;
    border-radius: 3px;
    background-color: #fff;
    text-align: left;
}

.comment-content .avatar {
    width: 46px;
    height: 46px;
}

.comment-content .comm-time {
    float: right;
    margin-right: 5px;
}

.comment-content .media-body, .comment-content-reply .media-body {
    margin-bottom: 20px;
}

.comment-content .media-body .pad-btm {
    padding-bottom: 0;
}

.comment-content .media-left, .comment-content-reply .media-left {
    float: left;
    margin-right: 10px;
}

.comment-content .media-left img {
    border-radius: 50%;
}

.message-text {
    padding-top: 0px;
    margin-left: 55px;
    margin-top: 10px;
    /*border: 1px black solid;*/
    margin-bottom: 5px;
}

.message-content-btn {
    text-align: center;
}

/*.comment-content .layui-btn{height: auto;line-height: 26px; padding: 5px 30px; font-size: 16px;}*/
.comment-content-reply {
    margin-left: 40px;
    padding-left: 10px;
    border-left: #ccc solid 1px;
}

.comment-input {
    margin-top: 20px;
}

.comment-footer {
    padding: 0.5rem 0rem;
    padding-left: calc(40px + 1em);
    display: flex;
    align-items: center;

}

.comment-icon {
    width: 20px;
    height: 20px;
    cursor: pointer;
    margin-right: 5px;
}

.comment-input {
    width: 100%;
    /*min-height: 100px;*/
    /*height: 10rem;*/
    padding: 0.5rem;

}

.comment-content-btns {
    display: flex;
    margin-top: 10px;
    position: relative;

}

.comment-content-btns button {
    margin-left: auto;
    background-color: rgba(166, 10, 169, .98);
    border: none;
    color: white;
    padding: 0.5rem 1.5rem;
    border-radius: 8%;
    cursor: pointer;
}

.comment-content-btns button:hover {
    background-color: #A52581;
    border-color: #A52581;
}

.comment-content-btns button:active {
    background-color: #A52581;
    border-color: #A52581;
}

/*.comment-content-btns button:focus{*/
/*    background-color: #A52581;*/
/*    border-color: #A52581;*/
/*}*/

[id^=reply-input-] {
    width: 90%;
}

.comment-reply-input {
    min-height: 2rem;
    width: 95% !important;
    //height: 4rem;
    //margin: 0.5rem 0rem;
    padding: 0.5rem;
    margin-left: calc(40px + 1em);
    //resize: both;
    overflow: hidden;
}

.comment-reply-input::before {
    content: attr(placeholder);
    position: absolute;
    pointer-events: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    color: #8a919f;
}

.comment-reply-btns {
    display: flex;
    position: relative;

}

.comment-reply-btns .emoji-btn {
    margin-left: calc(40px + 1em);
}

.comment-reply-btns button {
    margin-left: auto;
    background-color: rgba(166, 10, 169, .98);
    border: none;
    color: white;
    padding: 0.5rem 1.5rem;
    border-radius: 8%;
    cursor: pointer;
}

.comment-reply-btns button:hover {
    background-color: #A52581;
    border-color: #A52581;
}

.comment-reply-btns button:active {
    background-color: #A52581;
    border-color: #A52581;
}


.more-list {
    width: 100%;
    margin: auto;
    text-align: center;
}

.comment-list {
    margin-top: 10px;
}
.comm-btn {
    height: 35px;
}
</style>