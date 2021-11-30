layui.define(['form'], function (exports) {
    var form = layui.form
        , admin = layui.admin
        , view = layui.view
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

    exports('user', {})
});