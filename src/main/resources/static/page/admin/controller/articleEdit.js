layui.define(['layer', 'form', 'element', 'layupload', 'tinymce'], function (exports) {
    var form = layui.form
        , $ = layui.jquery
        , upload = layui.layupload
        , laytpl = layui.laytpl
        , admin = layui.admin
        , tinymce = layui.tinymce

    ;
    var artOid = layui.router().search.id;
    var edit;

    function getEdit(onChange, callback) {
        if (edit) return edit;
        edit = tinymce.render({
            elem: "#article-content-text-edit"
        }, function (opt, edit) {
            typeof callback === "function" && callback(opt, edit);

        }, function (editor) {
            editor.on('keyup', function (){
                typeof onChange === "function" && onChange(editor);
            });
        });

        return edit;
    }

    $('.choosed').on('click', 'a', function () {
        $(this).parent().remove();
    })
    $('.tags').on('click', '.publish-tags-content', function () {
        var flag = 0, choosed = $('.choosed > span');
        for (var i = 0; i < choosed.length; i++) {
            var obj = $(choosed[i]).clone();
            obj.find('a').remove();
            if (obj.data('text') === $(this).data('text')) {
                flag = 1;
                break;
            }
        }
        if (!flag) {
            var $choosed = $('.choosed');
            var tagschoosed = $(this).clone();
            tagschoosed.html($(this).html() + '<a>×</a>').appendTo($choosed);
        }

    })
    var status1 = 0;
    $('#edit').click(function () {
        if (status1 == 0) {
            $('#ispublic-form').css("display", "block");
            status1 = 1;
        } else if (status1 == 1) {
            $('#ispublic-form').css("display", "none");
            $('#pwd-tips').text('')
            status1 = 0;
        }
    })


    form.on('radio(public)', function (data) {
        // $('#hometop1').show();
        $('#passw-content').hide();
        $('#passw-content-confirm').hide();
        form.render();
    });
    form.on('radio(secret)', function (data) {
        // $('#hometop1').show();
        $('#passw-content').hide();
        $('#passw-content-confirm').hide();
        form.render();
    });
    form.on('radio(passw)', function (data) {
        // $('#hometop1').hide();
        $('#passw-content').show();
        $('#passw-content-confirm').show();
        form.render();
    });

    form.on('checkbox(original)', function (data) {
        if ($('#is-original').prop('checked')) {
            $('#originalUrl').hide()
        }else {
            $('#originalUrl').show()
        }
        form.render();
    });

    //上传
    var index;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        , url: '/api/admin/file/upload'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#breimg').attr('src', result); //图片链接（base64）
            });
            index = layer.load(0, {shade: false, offset: '400px'}, {time: 3000});
        }
        , done: function (res) {
            layer.close(index);
            // console.log(res);
            // //如果上传失败
            $('result').text(res.status);
            //    alert(res.status);
            if (res.status == '000000') {
                layer.msg('上传成功', {
                    title: '提示'
                    //不自动关闭
                    , time: 1000
                    , icon: 6
                    , offset: '400px'
                });
                $('#imgsrc').val(res.file.url);
            } else {
                alertFail("上传失败", res.msg)
            }
            //上传成功
        }
        , error: function () {
            layer.close(index);
            layer.msg('上传出错：1', {
                title: '提示'
                //不自动关闭
                , time: 1000
                , icon: 5
                , offset: '400px'
            });
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    //页面其他操作


    $('#btn-ispublic-cancel').click(function () {
        $('#ispublic-form').css("display", "none");
        $('#pwd-tips').text('')
        status1 = 0;
        return false;

    })

    function validatePass()
    {
        var pass = $('#passw-content').val();
        var repass = $('#passw-content-confirm').val();
        var isPublic = $('#ispublic-form .ispublic-radio:checked').val()
        var patt = /(.+){6,12}$/;
        if ((isEmpty(pass)) && isPublic == '11') {
            $('#pwd-tips').text('密码不能为空').css('color', 'red').css('display', 'block')
            return false;
        } else if (!patt.test(pass) && isPublic == '11') {
            $('#pwd-tips').text('密码必须6到12位').css('color', 'red').css('display', 'block')
            return false;
        } else if ((pass != repass) && isPublic == '11') {
            $('#pwd-tips').text('两次密码不一致').css('color', 'red').css('display', 'block')
            return false;
        }
        return true;
    }
    $('#btn-ispublic-sure').click(function () {
            if (!validatePass()){
                return  false;
            }

          if (status1 == 1) {
            $('#ispublic-form').css("display", "none");
            $('#pwd-tips').text('')
            status1 = 0;
            $('#published-public').text($('#ispublic-form .ispublic-radio:checked').attr('title'));

        }
        return false;
    })

    form.on("submit(submit-article)", function (data) {
        var field = data.field;
        var btnType = 0;
        if ($(this).attr('id') == 'published-update') {
            btnType = 1;
        } else if ($(this).attr('id') == 'published-save') {
            btnType = 2;
        } else if ($(this).attr('id') == 'published-pub') {
            btnType = 3;
        } else if ($(this).attr('id') == 'remove-del') {
            btnType = -1;
        }
        var articleContent = edit.getContent();
        var contentText = articleContent.replace(new RegExp('\n', 'g'), '');
        contentText = contentText.replace('<!DOCTYPE html>', '');
        contentText = contentText.replace(new RegExp('<html(/)?>', 'g'), '');
        contentText = contentText.replace(new RegExp('<head(/)?>', 'g'), '');
        contentText = contentText.replace(new RegExp('<body(/)?>', 'g'), '');

        if ((btnType === 1 || btnType === 2 || btnType === 3) && isEmpty(contentText)) {
            alertFail('提示', '文章内容不可为空！', '#article-content')
            return false;
        }
        if ((btnType === 1 || btnType === 3) && (isEmpty(field.categoryId) || field.categoryId == -1)) {
            alertFail('提示', '请选择类别！', '#type-select')
            return false;

        }
        if ((btnType === 1 || btnType === 3) && !$('#is-original').prop('checked')) {
            if (isEmpty($('#original-url').val())) {
                alertFail('提示', '非原创时转载源链接不能为空！', '#original-url')
                return false;
            }

        }

        var pass = $('#passw-content').val();
        var repass = $('#passw-content-confirm').val();
        var isPublic = $('#ispublic-form .ispublic-radio:checked').val()
        var patt = /(.+){6,12}$/;
        if ((isEmpty(pass)) && isPublic == '11') {
            alertFail('提示', '密码不能为空！', '#type-select')
            return false;
        } else if (!patt.test(pass) && isPublic == '11') {
            alertFail('提示', '密码必须6到12位！', '#type-select')
            return false;
        } else if ((pass != repass) && isPublic == '11') {
            alertFail('提示', '两次密码不一致！', '#type-select')
            return false;
        }


        // console.log(field)
        var tagsId = [];
        var publishTemp = '20';
        var passw = '';

        var data = $('.choosed>span');
        for (var i = 0; i < data.length; i++) {
            tagsId[i] = data.eq(i).data('id');
        }
        publishTemp = field.isPublic
        var topPlaceFlag = false;
        if ($('#hometop').prop('checked')) {
            topPlaceFlag = true;
        }
        if (publishTemp == '11') {
            passw = $('#passw-content').val();
        }
        var iscomment = 0;
        if ($('#is-comment').prop('checked')) {
            iscomment = 1;

        }
        var flagOriginal = 0;
        if ($('#is-original').prop('checked')) {
            flagOriginal = 1;

        }
        var tags = tagsId;
        var published = publishTemp === undefined ? '20' : publishTemp;
        var password = passw;
        var tempArtOid = $('#tempData').val();

        // return

        if (btnType === 1) {
            ajaxPost(
                '/api/admin/article/update',
                1,
                JSON.stringify({
                    requestId: field.subToken,
                    artOid: artOid,
                    title: field.title,
                    author: field.author,
                    articleContent: articleContent,
                    tagOidList: tags,
                    categoryId: field.categoryId,
                    imgSrc: field.imgsrc,
                    originalUrl: field.originalUrl,
                    accessType: published,
                    password: password,
                    isComment: iscomment !== 0,
                    flagOriginal: flagOriginal !== 0,
                    tempArtOid: tempArtOid,
                    topPlaceFlag: topPlaceFlag,
                }),
                "更新成功",
                function () {
                    admin.events.refreshWithoutEvent();
                }
            );

        } else if (btnType === 2) {
            ajaxPost('/api/admin/article/draft/save',
                1,
                JSON.stringify({
                    requestId: field.subToken,
                    artOid: artOid,
                    title: field.title,
                    author: field.author,
                    articleContent: articleContent,
                    tagOidList: tags,
                    categoryId: field.categoryId,
                    imgSrc: field.imgsrc,
                    originalUrl: field.originalUrl,
                    accessType: published,
                    password: password,
                    isComment: iscomment !== 0,
                    flagOriginal: flagOriginal !== 0,
                    tempArtOid: tempArtOid,
                    topPlaceFlag: topPlaceFlag,
                }),
                "保存成功",
                function () {
                    admin.events.refreshWithoutEvent();
                }
            )
        } else if (btnType === 3) {
            ajaxPost('/api/admin/article/draft/publish',
                1,
                JSON.stringify({
                    requestId: field.subToken,
                    artOid: artOid,
                    title: field.title,
                    author: field.author,
                    articleContent: articleContent,
                    tagOidList: tags,
                    categoryId: field.categoryId,
                    imgSrc: field.imgsrc,
                    originalUrl: field.originalUrl,
                    accessType: published,
                    password: password,
                    isComment: iscomment !== 0,
                    flagOriginal: flagOriginal !== 0,
                    tempArtOid: tempArtOid,
                    topPlaceFlag: topPlaceFlag,

                }),
                "发布成功",
                function () {
                    admin.events.refreshWithoutEvent();
                }
            )
        } else if (btnType === -1) {
            layer.confirm('确定把这文章移除到回收站吗？', function (index) {
                ajaxPost('/api/admin/article/save/remove/recycle',
                    1,
                    JSON.stringify({
                        requestId: field.subToken,
                        artOid: artOid,
                        title: field.title,
                        author: field.author,
                        articleContent: articleContent,
                        tagOidList: tags,
                        categoryId: field.categoryId,
                        imgSrc: field.imgsrc,
                        originalUrl: field.originalUrl,
                        accessType: published,
                        password: password,
                        isComment: iscomment !== 0,
                        flagOriginal: flagOriginal !== 0,
                        tempArtOid: tempArtOid,
                        topPlaceFlag: topPlaceFlag,

                    }),
                    '移动至回收站成功',
                    function () {
                        //重点在这里
                        //关闭当前的tab
                        // parent.layui.admin.events.closeThisTabs()
                        admin.events.refreshWithoutEvent();

                    });
            });
        }


    })

    $('#cancel').click(function () {
        alertAsk('确认清除所有输入的数据，恢复初始状态?', function () {
            admin.events.refreshWithoutEvent();
        })
    })


    $('#articlePreview').click(function () {
        var title = $('#art-title').val();
        var author = $('#art-author').val();
        var articleContent = edit.getContent();
        var type = $('.article-type select:selected').text();
        $('#artpre-title').val(title);
        $('#artpre-author').val(author);
        $('#artpre-content').val(articleContent);
        $('#artpre-type').val(type);

        $('#form-articlePreview').submit();
        // window.alert = function () {
        //     return false;
        // };
    })

    exports('articleEdit', {'getEdit': getEdit})
});