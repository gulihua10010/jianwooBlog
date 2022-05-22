/**

 @Name: 公共业务
 @Author:gulh

 */

layui.extend({
    tinymce: "{/}" + layui.setter.base + 'lib/extend/tinymce/tinymce'
    , mouseRightMenu: "{/}" + layui.setter.base + 'lib/extend/mouseRightMenu'
    , laytable: "{/}" + layui.setter.base + 'lib/extend/laytable'
    , layupload: "{/}" + layui.setter.base + 'lib/extend/layupload'
    , base64: '../admin/lib/extend/base64'
    , notice: '../admin/lib/extend/notice'
}).define(["form", "base64", "notice"], function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , laytpl = layui.laytpl
        , setter = layui.setter
        , view = layui.view
        , admin = layui.admin
        , form = layui.form
        , base64 = layui.base64
        , notice = layui.notice
    ;
    // 初始化配置，同一样式只需要配置一次，非必须初始化，有默认配置
    notice.options = {
        closeButton: true,//显示关闭按钮
        debug: false,//启用debug
        positionClass: "toast-top-right",//弹出的位置,
        showDuration: "300",//显示的时间
        hideDuration: "1000",//消失的时间
        timeOut: "5000",//停留的时间
        extendedTimeOut: "1000",//控制时间
        showEasing: "swing",//显示时的动画缓冲方式
        hideEasing: "linear",//消失时的动画缓冲方式
        iconClass: 'toast-info', // 自定义图标，有内置，如不需要则传空 支持layui内置图标/自定义iconfont类名
        onclick: true, // 点击关闭回调
    };
    //公共业务的逻辑处理可以写在此处，切换任何页面都会执行

    ajaxPost = function (url, version, data, msg, success, failed) {
        var index;
        if (msg) {
            index = layer.load(0, {shade: false, offset: '400px'});
        }
        admin.req({
            url: doAddUrlVersion(url)
            , type: 'post'
            , data: data
            , dataType: 'json'
            , async: true
            , success: function (data) {
                if (data.status == '000000') {
                    if (msg) {
                        layer.close(index);
                        alertSuccess('提示', msg)
                    }
                    setTimeout(function () {
                        typeof success === 'function' && success(data);
                    }, 1000)

                } else {
                    layer.close(index);
                    alertFail('提示', data.msg);
                    setTimeout(function () {
                        typeof failed === 'function' && failed(data);
                    }, 1000)

                }
            },
            error: function (e, code) {
                layer.close(index);
                alertFail('提示', '请求异常，请重试')
            }
        });

    }


    ajaxPostForm = function (url, version, data, msg, success, failed, complate) {
        var index;
        if (msg) {
            index = layer.load(0, {shade: false, offset: '400px'});
        }
        $.ajax({
            url: doAddUrlVersion(url)
            , type: 'post'
            , data: data
            , dataType: 'json'
            , async: true
            , success: function (data) {
                if (data.status == '000000') {
                    if (msg) {
                        layer.close(index);
                        alertSuccess('提示', msg)
                    }
                    setTimeout(function () {
                        typeof success === 'function' && success();
                    }, 1000)

                } else {
                    layer.close(index);
                    alertFail('提示', data.msg);
                    setTimeout(function () {
                        typeof failed === 'function' && failed();
                    }, 1000)

                }
            },
            error: function (e, code) {
                layer.close(index);
                alertFail('提示', '请求异常，请重试')
            },
            complete: function (xhr, data) {
                typeof complate === 'function' && complate(xhr, data);

            }
        });

    }

    parseUrlParam = function (url) {
        var theRequest = new Object()
        var idx = url.indexOf('?');
        if (idx !== -1) {
            var str = url.substr(idx + 1) //substr()方法返回从参数值开始到结束的字符串；
            var strs = str.split('&')
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split('=')[0]] = strs[i].split('=')[1]
            }
        }
        return theRequest;

    }

    doAddUrlVersion = function (url, version) {
        if (!version) {
            version = 1;
        }
        var idx = url.indexOf('?');
        if (idx === -1) {
            return url + "?v=" + version;
        }
        return url + "&v=" + version

    }

    ajaxApiPost = function (url, version, data, succCallback, failedCallback) {

        admin.req({
            type: 'post',
            data: data,
            dataType: 'json',
            // contentType: "application/json",
            async: true,
            url: doAddUrlVersion(url),
            success: function (data) {
                if (data.status === '000000') {
                    typeof succCallback === 'function' && succCallback(data);
                } else {
                    typeof failedCallback === 'function' && failedCallback(data);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }

        })
    }


    /**
     * 统计区分中英文字符字数
     */
    getWordsCnt = function (str) {
        var n = 0;
        for (var i = 0; i < str.length; i++) {
            var ch = str.charCodeAt(i);
            if (ch > 255) { // 中文字符集
                n += 2;
            } else {
                n++;
            }
        }
        return n;
    }


    //成功提示
    alertSuccess = function (title = '提示', msg = '') {
        layer.msg(msg, {
            title: title
            , time: 1000
            , icon: 6
            , offset: '250px'
        });
    }
    //警告提示
    alertWarning = function (title = '警告', msg = '') {
        layer.msg(msg, {
            title: title
            , time: 1000
            , icon: 7
            , offset: '250px'
        });
    }


    //失败提示
    alertFail = function (title = '错误提示', msg = '', input_dom) {
        layer.msg(msg, {
            title: title
            , time: 1000
            , icon: 5
            , shift: 6
            , offset: '250px'
        });
        if (input_dom !== undefined) {
            setTimeout(function () {
                $(input_dom).focus();
                $(input_dom).addClass('layui-form-danger')
            }, 7)
        }

    }

    alertAsk = function (msg = '', yes, no) {
        //询问框
        layer.confirm(msg, {
            btn: ['确定', '取消'] //按钮
            , offset: '400px'
        }, function () {
            typeof yes === "function" && yes();
            layer.closeAll()

        }, function () {
            typeof no === "function" && no();
        })
    }

    isEmpty = function (field) {
        if (field === undefined || field === null || field.trim() === '') {
            return true;
        }
        return false;
    }

    scrollPosition = function (id, p_top) {

        //获取某个元素的相对偏移，此元素必须是可见的，返回值是top 和left 单位是像素 移动到固定元素上尽可能使用padding
        var offset = $(id).offset();
        $('body,html').animate({
            scrollTop: (offset.top + p_top)
        })
    }


    form.verify({
        maxLength: function (value, item) {
            var len = item.getAttribute('lay-max');
            if (len == undefined || len == null) len = 200;
            if (value.length > len) {
                return '字段长度不能大于' + len + '位';
            }
        },
        number: function (value, item) {
            if (isEmpty(value)) {
                return false;
            }
            if (!value || isNaN(value)) return '只能填写数字';
        },
        integer: function (value, item) {
            if (isEmpty(value)) {
                return false;
            }
            if (!value || isNaN(value)) return '只能填写数字';
            if (value.indexOf('.') !== -1) {
                return '只能填写整数';
            }
        },
        menuName: [/^[_#$@\d\w]*$/, '菜单文本必须是字母、数字、符号\'_#$@\'，不能包含其他特殊字符!'],
        pass: function (value, item) {
            if ($('#passw').is(":checked")) {
                var patt = /(.+){6,12}$/;
                if (!patt.test(value)) {
                    return '密码必须6到12位';
                }
            }

        },
        repass: function (value, item) {
            if ($('#passw').is(":checked") &&
                $('#passw-content').val() != value) {
                return '两次密码不一致';
            }
        },
        loginPass: function (value, item) {
            var patt = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$/;
            if (!patt.test(value)) {
                return '密码必须包含数字,英文,字符中的两种以上,长度6-20!';
            }

        },
        loginRepass: function (value, item) {
            if ($('#newPassword').val() !== value) {
                return '两次密码不一致';
            }
        },
        dateFmt: function (value, item) {
            if (isEmpty(value)) {
                return false;
            }
            var patt = /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/;
            if (!patt.test(value)) {
                return '日期格式不正确';
            }
        },
        dateTimeFmt: function (value, item) {
            if (isEmpty(value)) {
                return false;
            }
            var patt = /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*\s(\d{1}|[0-1]\d{1}|2[0-3])[:]([0-5]\d{1})[:]([0-5]\d{1})$/;
            if (!patt.test(value)) {
                return '日期时间格式不正确';
            }
        },

    })

    /**用正则表达式实现html解码（反转义）（另一种写法）*/
    escape2Html = function (str) {
        var arrEntities = {'lt': '<', 'gt': '>', 'nbsp': ' ', 'amp': '&', 'quot': '"', 'apos': '\''};
        return str.replace(/&(lt|gt|nbsp|amp|quot|apos);/ig, function (all, t) {
            return arrEntities[t];
        });
    }


    /** 用正则表达式实现html编码（转义）（另一种写法）*/
    html2Escape = function (sHtml) {
        return sHtml.replace(/[<>&"']/g, function (c) {
            return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;', '\'': '&apos;'}[c];
        });
    }

    /**
     * 需要导入crypto-js.min.js
     * 1.aes加密
     * 2.base加密
     * @param str 要加密的字符串
     * @param key base64加密后的秘钥
     * @returns {*} 加密后的字符串
     */
    aesEncrypt = function (str, key) {
        return base64.encode(CryptoJS.AES.encrypt(str, CryptoJS.enc.Utf8.parse(base64.decode(key)), {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        }).toString());
    }


    /**
     * 需要导入crypto-js.min.js
     * 解密
     * 1.base解密
     * 2.aes解密
     * @param str 加密后的字符串
     * @param key base64加密后的秘钥
     * @returns {*} 解密后的字符串
     */
    aesDecrypt = function (str, key) {
        return CryptoJS.AES.decrypt(base64.decode(str), CryptoJS.enc.Utf8.parse(base64.decode(key)), {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        }).toString(CryptoJS.enc.Utf8);

    }

    timerMsgNotify = function () {
        view.loading($('body')); //loading
        ajaxPost(
            setter.notifyMsgApi,
            1,
            JSON.stringify({limit: 10}),
            "",
            function (res) {
                if (res.data.length > 0) {
                    for (var i = 0; i < res.data.length; i++) {
                        notice.msg(res.data[i].content, "", {
                            link: res.data[i].link,
                            oid: res.data[i].oid,
                            onclick: function () {
                                window.open(this.link);
                                ajaxPost(
                                    "/api/admin/msg/read",
                                    1,
                                    JSON.stringify({entityOid: this.oid}),
                                    "",
                                    function () {
                                    }
                                );
                            }
                        });
                    }
                }

                view.removeLoad();
            }
        );


    }

    guid = function () {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    getGuid = function () {
        var uuid = layui.data(setter.tableName)["guid"];
        if (!uuid) {
            uuid = guid();
            layui.data(setter.tableName, {
                key: "guid"
                , value: uuid
            });
        }
        return uuid;
    }


    //退出
    admin.events.logout = function () {
        var headers = {};
        headers[setter.request.loginIdSecret] = (layui.data(setter.tableName)[setter.request.loginIdSecret] || '')
        headers[setter.request.tokenName] = (layui.data(setter.tableName)[setter.request.tokenName] || '')
        //执行退出接口
        admin.req({
            url: '/api/passport/login/exit'
            , type: 'post'
            , data: {}
            , headers: headers
            , done: function (res) { //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行

                //清空本地记录的 token，并跳转到登入页
                admin.exit();
            }
            , fail: function (res) {
                alertFail('提示', res[setter.response.msgName]);
            }

        });
    };

    //对外暴露的接口
    exports('common', {});
});