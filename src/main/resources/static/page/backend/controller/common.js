/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */

layui.define("form", function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , laytpl = layui.laytpl
        , setter = layui.setter
        , view = layui.view
        , admin = layui.admin
        , form = layui.form;


    //公共业务的逻辑处理可以写在此处，切换任何页面都会执行

    ajaxPost = function (url, data, msg, success, failed) {
        var index = layer.load(0, {shade: false, offset: '400px'});
        admin.req({
            url: url
            , type: 'post'
            , data: data
            , dataType: 'json'
            , async: true
            , success: function (data) {
                if (data.status == '000000') {
                    layer.close(index);
                    alertSuccess('提示', msg)
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
        if (input_dom != undefined) {
            $(input_dom).css('border', '1px solid rgb(255,87,34)')
            scrollPosition(input_dom, 0)
        }

    }

    alertAsk = function (msg = '', yes) {
        //询问框
        layer.confirm(msg, {
            btn: ['确定', '取消'] //按钮
            , offset: '400px'
        }, function () {
            yes();
            layer.closeAll()

        }, function () {

        })
    }

    isEmpty = function (field) {
        if (field == undefined || field == null || field.trim() == '') {
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


    //富文本编辑器

    tinymceInit = function (id, content = "", power_paste = "propmt") {
        console.log("===== tinymce init")
        tinymce.init({
            selector: id,
            max_height: 550,
            height: 500,
            convert_urls: false,
            branding: false,
            plugins: [
                "advlist  autolink   link image lists   print preview hr anchor  ",
                "searchreplace wordcount     code   insertdatetime media nonbreaking",
                "table contextmenu directionality emoticons   textcolor paste fullpage  powerpaste toc   codesample uploadvideo importcss textcolor colorpicker uploadimage"
            ],
            toolbar1: "undo redo | cut copy paste | bold italic underline strikethrough |" +
                " alignleft aligncenter alignright alignjustify |   formatselect fontselect fontsizeselect",
            toolbar2: " searchreplace | bullist numlist | outdent indent blockquote | link unlink   uploadimage uploadvideo  codesample image |" +
                " inserttime preview | forecolor backcolor",
            toolbar3: "table | hr removeformat | subscript superscript |   emoticons |  " +
                "   nonbreaking    restoredraft  |  code      ",
            menubar: false,
            toolbar_items_size: 'small',
            font_formats: "宋体=宋体;微软雅黑=微软雅黑;新宋体=新宋体;微软雅黑='微软雅黑';黑体='黑体';仿宋='仿宋';楷体='楷体';隶书='隶书';幼圆='幼圆';" +
                "Arial='Arial';Times New Roman='Times New Roman'",
            automatic_uploads: true,
            uploadimage_url: "/api/file/upload",
            uploadvideo_url: "/api/file/upload",
            content_css: "/static/comm/css/tinymce.css",
            textcolor_map: [
                "000000", "Black", "993300", "Burnt orange", "333300", "Dark olive", "003300", "Dark green", "003366", "Dark azure", "000080", "Navy Blue",
                "333399", "Indigo", "333333", "Very dark gray", "800000", "Maroon", "FF6600", "Orange", "808000", "Olive", "008000",
                "Green", "008080", "Teal", "0000FF", "Blue", "666699", "Grayish blue", "808080", "Gray", "FF0000", "Red", "FF9900",
                "Amber", "99CC00", "Yellow green", "339966", "Sea green", "33CCCC", "Turquoise", "3366FF", "Royal blue", "800080",
                "Purple", "999999", "Medium gray", "FF00FF", "Magenta", "FFCC00", "Gold", "FFFF00", "Yellow", "00FF00", "Lime",
                "00FFFF", "Aqua", "00CCFF", "Sky blue", "993366", "Red violet", "FFFFFF", "White", "FF99CC", "Pink", "FFCC99", "Peach",
                "FFFF99", "Light yellow", "CCFFCC", "Pale green", "CCFFFF", "Pale cyan", "99CCFF", "Light sky blue", "CC99FF", "Plum"
            ],
            language: 'zh_CN',
            powerpaste_word_import: power_paste,// 参数可以是propmt, merge, clear，效果自行切换对比
            powerpaste_html_import: power_paste,// propmt, merge, clear
            powerpaste_allow_local_images: true,
            paste_data_images: true,
            paste_merge_formats: true,
            init_instance_callback: function (plugin, args) {
                if (content != undefined && content != "") {
                    tinyMCE.activeEditor.setContent(content);
                }
            },
            paste_preprocess: function (plugin, args) {
                function load(src) {
                    loadImageToBlob(src, function (blobFile) {
                        var x = new XMLHttpRequest();
                        x.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                data = this.responseText;
                                // console.log('response data: ' + data);
                            }
                        };
                        x.open('POST', "/api/file/upload");
                        x.send(blobFile);
                    });
                }
            },
            images_upload_handler: function (blobInfo, success, failure) {
                var blob = blobInfo.blob();
                var formData = new FormData();
                formData.append('file', blob);
                $.ajax({
                    url: "/api/file/upload",
                    crossDomain: true,
                    data: formData,
                    dataType: 'json',
                    type: 'POST',
                    contentType: false,
                    processData: false,
                    success: function (res) {
                        success(res.file.url);
                    }
                });
            }
        });
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

    formatStr = function (str) {
        if (undefined === str || str === null) {
            return "";
        }
        return str.trim();
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