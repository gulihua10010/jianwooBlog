layui.define(['layer', 'form', 'element', 'upload', 'tinymce'], function (exports) {
    var form = layui.form
        , $ = layui.jquery
        , upload = layui.upload
        , element = layui.element
        , tinymce = layui.tinymce
        , admin = layui.admin

    ;
    var edit;

    function getEdit(onChange, callback) {
        if (edit) return edit;
        edit = tinymce.render({
            elem: "#article-content-text"
        }, function (opt, edit) {
            typeof callback === "function" && callback(opt, edit);

        },function (editor) {
            editor.on('keypress', function (){
                typeof onChange === "function" && onChange(editor);
            });
        });
        return edit;
    }

    $('.choosed').on('click', 'a', function () {
        $(this).parent().remove();
    })
    $('.tags').on('click', '.publish-tags-content', function () {
        var flag = 0, choosed = $('.choosed>span');
        for (var i = 0; i < choosed.length; i++) {
            var obj = $(choosed[i]).clone();
            obj.find('a').remove();
            if (obj.text() == $(this).text()) {
                flag = 1;
            }
        }
        if (!flag) {
            var choosed = $('.choosed');
            var add = $('<span></span>');
            var tagschoosed = $(this).clone();
            tagschoosed.html($(this).html() + '<a>×</a>').appendTo(choosed);
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
        $('#hometop1').show();
        $('#passw-content').hide();
        $('#passw-content-confirm').hide();
        form.render();
    });
    form.on('radio(secret)', function (data) {
        $('#hometop1').show();
        $('#passw-content').hide();
        $('#passw-content-confirm').hide();
        form.render();
    });
    form.on('radio(passw)', function (data) {
        $('#hometop1').hide();
        $('#passw-content').show();
        $('#passw-content-confirm').show();
        form.render();
    });

    var index;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#art-img'
            , url: '/api/file/upload'
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

    $('#btn-ispublic-cancel').click(function () {
        $('#ispublic-form').css("display", "none");
        $('#pwd-tips').text('')
        status1 = 0;
        return false;

    })
    $('#btn-ispublic-sure').click(function () {
        var pass = $('#passw-content').val();
        var repass = $('#passw-content-confirm').val();
        var isPublic = $('#ispublic-form .ispublic-radio:checked').val()
        var patt = /(.+){6,12}$/;
        if ((isEmpty(pass)) && isPublic == -1) {
            $('#pwd-tips').text('密码不能为空').css('color', 'red').css('display', 'block')

        } else if (!patt.test(pass)) {
            $('#pwd-tips').text('密码必须6到12位').css('color', 'red').css('display', 'block')
        } else if ((pass != repass) && isPublic == -1) {
            $('#pwd-tips').text('两次密码不一致').css('color', 'red').css('display', 'block')
        } else if (status1 == 1) {
            $('#ispublic-form').css("display", "none");
            $('#pwd-tips').text('')
            status1 = 0;
            $('#published-public').text($('#ispublic-form .ispublic-radio:checked').attr('title'));

        }

        return false;
    })

    form.on("submit(submit-article)", function (data) {
        var field = data.field;
        var btnstatus = 0;
        if ($(this).attr('id') === 'published-sure') {
            btnstatus = 1;
        } else if ($(this).attr('id') === 'save-draft') {
            btnstatus = 0;
        } else if ($(this).attr('id') === 'remove-del') {
            btnstatus = -1;
        }

        var articleContent = edit.getContent();
        // console.log("articleContent")
        // console.log(articleContent)
        // return ;
        var contentText = articleContent.replace(new RegExp('\n', 'g'), '');
        if (btnstatus === 1) {
            if (contentText == '<!DOCTYPE html><html><head></head><body></body></html>') {
                alertFail('提示', '文章内容不可为空！', '#article-content')
                return false;
            }
            if (isEmpty(field.typeId) || field.typeId == -1) {
                alertFail('提示', '请选择类别！', '#type-select')
                return false;

            }

        }


        var tagsId = [];
        var publishTemp = 1;
        var passw = '';

        var data = $('.choosed>span')
        for (var i = 0; i < data.length; i++) {
            tagsId[i] = data.eq(i).data('id')
        }
        publishTemp = field.isPublic
        if (publishTemp == 1) {
            if ($('#hometop').prop('checked')) {
                publishTemp = 2;

            }

        } else if (publishTemp == -1) {
            passw = $('#passw-content').val();
        }
        var iscomment = 0;
        if ($('#is-comment').prop('checked')) {
            iscomment = 1;
        }

        var tags = tagsId;
        var published = publishTemp == undefined ? 1 : publishTemp;
        var password = passw;

        var tempArtOid = $('#tempData').val();

        if (btnstatus === 1) {
            ajaxPost('/api/admin/article/submit',
                JSON.stringify({
                    title: field.title,
                    author: field.author,
                    articleContent: articleContent,
                    tags: tags,
                    type: field.typeId,
                    imgSrc: field.imgsrc,
                    visitType: published,
                    password: password,
                    isComment: iscomment,
                    tempArtOid: tempArtOid,
                    subToken: field.subToken

                }),
                '发布成功',
                function () {
                    admin.events.refreshWithoutEvent();
                });
        } else if (btnstatus === 0) {
            ajaxPost('/api/admin/article/save/draft',
                JSON.stringify({
                    title: field.title,
                    author: field.author,
                    articleContent: articleContent,
                    tags: tags,
                    type: field.typeId,
                    imgSrc: field.imgsrc,
                    visitType: published,
                    password: password,
                    isComment: iscomment,
                    tempArtOid: tempArtOid,
                    subToken: field.subToken


                }),
                '保存成功',
                function () {
                    admin.events.refreshWithoutEvent();
                });
        } else if (btnstatus === -1) {
            layer.confirm('确定把这文章移除到回收站吗？', function (index) {
                ajaxPost('/api/admin/article/save/recycle',
                    JSON.stringify({
                        title: field.title,
                        author: field.author,
                        articleContent: articleContent,
                        tags: tags,
                        type: field.typeId,
                        imgSrc: field.imgsrc,
                        visitType: published,
                        password: password,
                        isComment: iscomment,
                        tempArtOid: tempArtOid,
                        subToken: field.subToken


                    }),
                    '移动至回收站成功',
                    function () {
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


    exports('articlePublish', {'getEdit': getEdit})
});