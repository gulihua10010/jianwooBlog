/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */

layui.extend({
    tinymce: "{/}" + layui.setter.base + '/lib/extend/tinymce/tinymce'
    , mouseRightMenu: "{/}" + layui.setter.base + '/lib/extend/mouseRightMenu'
}).define("form", function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , laytpl = layui.laytpl
        , setter = layui.setter
        , view = layui.view
        , admin = layui.admin
        , form = layui.form;


    //公共业务的逻辑处理可以写在此处，切换任何页面都会执行

    ajaxPost = function (url, data, msg, success, failed) {
        var index;
        if (msg) {
            index = layer.load(0, {shade: false, offset: '400px'});
        }
        admin.req({
            url: url
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
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.close(index);
                alertFail('提示', '未知错误')
            }
        });

    }


    ajaxApiPost = function (url, data, call) {

        admin.req({
            type: 'post',
            data: data,
            dataType: 'json',
            // contentType: "application/json",
            async: true,
            url: url,
            success: function (data) {
                if (data.status == '000000') {
                    typeof call === 'function' && call(data);
                } else {
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
            , offset: '400px'
        });
    }


    //失败提示
    alertFail = function (title = '提示', msg = '', input_dom) {
        layer.msg(msg, {
            title: title
            , time: 1000
            , icon: 5
            , shift: 6
            , offset: '400px'
        });
        if (input_dom !== undefined) {
            $(input_dom).css('border', '1px solid rgb(255,87,34)')
            scrollPosition(input_dom, 0)
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

    var scrollPosition = function (id, p_top) {

        //获取某个元素的相对偏移，此元素必须是可见的，返回值是top 和left 单位是像素 移动到固定元素上尽可能使用padding
        var offset = $(id).offset();
        $('body,html').animate({
            scrollTop: offset.top + p_top
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


    })

    /**用正则表达式实现html解码（反转义）（另一种写法）*/
    escape2Html = function (str) {
        var arrEntities = {'lt': '<', 'gt': '>', 'nbsp': ' ', 'amp': '&', 'quot': '"'};
        return str.replace(/&(lt|gt|nbsp|amp|quot);/ig, function (all, t) {
            return arrEntities[t];
        });
    }


    /** 用正则表达式实现html编码（转义）（另一种写法）*/
    html2Escape = function (sHtml) {
        return sHtml.replace(/[<>&"]/g, function (c) {
            return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c];
        });
    }

    //退出
    admin.events.logout = function () {
        //执行退出接口
        admin.req({
            url: './json/user/logout.js'
            , type: 'get'
            , data: {}
            , done: function (res) { //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行

                //清空本地记录的 token，并跳转到登入页
                admin.exit();
            }
        });
    };

    //对外暴露的接口
    exports('common', {});
});