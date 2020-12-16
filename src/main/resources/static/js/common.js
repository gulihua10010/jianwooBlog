var $jq;
var layer;
layui.define(['layer'], function (exports) {
    "use strict";

    $jq = layui.jquery,
        layer = layui.layer;

    var common = {
        /**
         * 抛出一个异常错误信息
         * @param {String} msg
         */
        throwError: function (msg) {
            throw new Error(msg);

        },
        /**
         * 弹出一个错误提示
         * @param {String} msg
         */
        msgError: function (msg) {
            layer.msg(msg, {
                icon: 5
            });

        }
    };

    exports('common', common);
});


function alert_fail(title = '', msg = '') {

    var index = layer.load(0, {shade: false, offset: '400px'}, {time: 1000}); //0代表加载的风格，支持0-2
    setTimeout(function () {
        layer.close(index);
        layer.msg(msg, {
            title: title
            //不自动关闭
            , time: 1000
            , icon: 5
            , offset: '400px'
        });

    }, 1000);
    setTimeout(function () {
        layer.closeAll();
    }, 2000)

}


function alert_success_url(title = '', msg = '', url) {


    var index = layer.load(0, {shade: false, offset: '400px'}, {time: 3000}); //0代表加载的风格，支持0-2
    setTimeout(function () {
        layer.close(index);
        layer.msg(msg, {
            title: title
            //不自动关闭
            , time: 1000
            , icon: 6
            , offset: '400px'
        });

    }, 1000)

    setTimeout(function () {
        window.location.href = url;
        layer.closeAll();
    }, 2000)

}

function alert_success(title = '', msg = '') {
    var index = layer.load(0, {shade: false, offset: '400px'}, {time: 3000}); //0代表加载的风格，支持0-2
    setTimeout(function () {
        layer.close(index);
        layer.msg(msg, {
            title: title
            //不自动关闭
            , time: 1000
            , icon: 6
            , offset: '400px'
        });

    }, 1000)

}

function alert_ask(msg = '', yes) {
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

function alert_ask_btn(msg = '', btn1 = '确定', btn2 = '自定义', btn3 = '取消', left, right) {
    //询问框
    var index = layer.confirm(msg, {
        extend: 'myskin/alertask.css',
        skin: 'layer-ext-myskin',
        btn: [btn1, btn2, btn3] //按钮
        , offset: '400px'
    }, function () {
        left();
        layer.closeAll()
    }, function () {
        right();
    })

}

function formatDate(time) {
    var date = new Date(time);

    var year = date.getFullYear(),
        month = date.getMonth() + 1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
        (month < 10 ? '0' + month : month) + '-' +
        (day < 10 ? '0' + day : day) + ' ' +
        (hour < 10 ? '0' + hour : hour) + ':' +
        (min < 10 ? '0' + min : min) + ':' +
        (sec < 10 ? '0' + sec : sec);

    return newTime;
}

function encodeUnicode(str) {
    var res = [];
    for (var i = 0; i < str.length; i++) {
        res[i] = ('00' + str.charCodeAt(i).toString(16)).slice(-4)

    }
    return "\\u" + res.join('\\u')
}

function decodeUnicode(str) {
    str = str.replace(/\\/g, '%');
    return unescape(str)
}

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = {};
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/kyw/user/login
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如：kyw/user/login.php
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    return (localhostPaht + projectName);
}

function ajaxform(formdata, url, $, success, error) {
    if (error == null || error == undefined) {
        error = function () {
        }
    }
    $.ajax({
        data: formdata,
        type: 'post',
        async: true,
        dataType: 'json',
        url: url,
        contentType: "application/json",
        beforeSend: function () {
            loading = layer.load(2, {
                shade: [0.2, '#000']
            })
        },
        success: function (res) {
            layer.close(loading);
            console.log(res)
            if (res.status == 200) {
                layer.msg(res.msg, {icon: 1, time: 1000});
                setTimeout(function () {
                    success(res);
                }, 1000)

            } else {
                layer.msg(res.msg, {icon: 2, time: 1000});
                error();
            }

        }, error: function (res) {
            layer.close(loading);
            layer.msg('未知错误', {icon: 2, time: 1000});
            error();
        }


    })
}

function uploadImg(upload, elem, url, success, error) {
    upload.render({
        url: url
        , elem: elem
        , exts: 'jpg|png|gif'
        , before: function (input) {
            loading = layer.load(2, {
                shade: [0.2, '#000']
            });
        }
        , done: function (res) {
            layer.close(loading);
            success(res);
        }, error: function () {
            error();
            layer.close(loading);
            layer.msg('上传出错：1', {
                title: '提示'
                //不自动关闭
                , time: 1000
                , icon: 5
                , offset: '400px'
            });
        }
    });
}


function converTime(sec) {
    var sec = parseInt(sec);
    if (sec < 60) {
        return sec + '秒';
    }
    var min = parseInt(sec / 60);
    sec = sec % 60;
    if (min < 60) {
        return min + '分' + sec + '秒';
    }
    var hour = parseInt(min / 60);
    min = min % 60;
    return hour + '时' + min + '分' + sec + '秒';

}

function getRandom() {
    var random = "qwertyuioplkjhgfdsazxcvbnm1234567890";
    var s = '';
    for (var i = 0; i < 10; i++) {
        var r = Math.round(Math.random() * 36);
        s += random[r];
    }
    return s;
}

var isf = 1

function tinymceInit(id, power_paste = "propmt") {
    tinymce.init({
        selector: id,
        max_height: 550,
        height: 250,
        convert_urls: false,
        branding: false,
        plugins: [
            "advlist  autolink   link image lists   print preview hr anchor  ",
            "searchreplace wordcount     code   insertdatetime media nonbreaking",
            "table contextmenu directionality emoticons   textcolor paste fullpage  powerpaste toc   uploadvideo importcss textcolor colorpicker uploadimage"
        ],
        toolbar1: "undo redo | cut copy paste | bold italic underline strikethrough |" +
            " alignleft aligncenter alignright alignjustify |   formatselect fontselect fontsizeselect",
        toolbar2: " searchreplace | bullist numlist | outdent indent blockquote | link unlink   uploadimage uploadvideo  code |" +
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
            $jq.ajax({
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

function clearHtml(str) {
    str = str.replace(/<!DOCTYPE html>/g, "");
    str = str.replace(/<[/]?html>/g, "");
    str = str.replace(/<[/]?body>/g, "");
    str = str.replace(/<[/]?head>/g, "");
    return str;
}

function clearHtmlexpImg(str) {
    str = str.replace(/<!DOCTYPE html>/g, "");
    str = str.replace(/<([^i][^>.]+)>/g, "");
    str = str.replace(/<([^i.]+)>/g, "");
    str = str.replace(/<i>/g, "");
    // str=str.replace(/<[\.]+>/g,"");

    return str;
}


function ajaxPost(url, data, msg, tip = function () {
}) {
    var index = layer.load(0, {shade: false, offset: '400px'}, {time: 3000});

    $jq.ajax({
        type: 'post',
        data: data,
        dataType: 'json',
        contentType: "application/json",
        async: true,
        url: url,
        success: function (data) {
            if (data.status == '000000') {
                layer.close(index);
                layer.msg(msg, {
                    title: "提示"
                    , time: 1000
                    , icon: 6
                    , offset: '400px'
                });
                setTimeout(function () {
                    tip();
                }, 1000)

            } else {
                alert_fail('提示', data.msg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert_fail('提示', '未知错误')
        }

    })
}

function ajaxApiPost(url, data, call) {

    $jq.ajax({
        type: 'post',
        data: data,
        dataType: 'json',
        contentType: "application/json",
        async: true,
        url: url,
        success: function (data) {
            if (data.status == '000000') {
                call(data)
            } else {
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }

    })
}

function log(msg) {
    console.log("====>> " + msg);
}





