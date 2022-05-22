layui.define(['form', 'layupload'], function (exports) {
    var form = layui.form
        , admin = layui.admin
        , view = layui.view
        , upload = layui.layupload
        , $ = layui.jquery
    ;

    layui.form.on('submit(submit-user-edit)', function (formData) {
        var field = formData.field; //获取提交的字段
        ajaxPost(
            '/api/admin/user/profile/edit/update',
            1,
            JSON.stringify(field),
            "更新成功",
            function () {
                admin.events.refresh();

            }
        );

    });


    layui.form.on('submit(submit-user-password)', function (formData) {
        var field = formData.field; //获取提交的字段
        ajaxPost(
            '/api/admin/user/password/change',
            1,
            JSON.stringify(field),
            "更新成功",
            function () {
                layui.data(layui.setter.tableName, {
                    key: "password"
                    ,remove: true
                });
                var redirect = layui.router().href;
                view.exit(redirect);


            }
        );

    });


    //普通图片上传
    var uploadInst = upload.render({
        elem: '#avatarBtn'
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
                $('#avatarSrc').val(res.file.url);
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

    exports('user', {})
});