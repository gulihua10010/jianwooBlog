layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , $ = layui.jquery
        , layer = layui.layer
    ;
    table.render({
        elem: '#emailTpl-table'
        , url: "/api/admin/email/tpl/query/list?v=1"
        , cols: [[
            {field: 'code', title: '邮件模板编号', align: 'center'},
            {field: 'desc', title: '描述', align: 'left'},
            {field: 'subject', title: '邮件模板主题', align: 'left'},
            {field: 'createTimeDesc', title: '创建时间', align: 'center'},
            , {title: '操作', width: 100, align: 'left', fixed: 'right', toolbar: '#table-content-email'}


        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });

    //监听搜索
    form.on('submit(emailtpl-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('emailTpl-table', {
            where: field
        });
    });



    //监听工具条
    table.on('tool(emailTpl)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定把此邮件模板移除吗？', function (index) {
                ajaxPost(
                    "/api/admin/email/tpl/remove",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "移除成功",
                    function () {
                        obj.del();
                        layer.close(index);
                    }
                );

            });
        }

    });

    layui.form.on('submit(submit-emailTpl-create)', function (formData) {
        var field = formData.field; //获取提交的字段
        ajaxPost(
            '/api/admin/email/tpl/create',
            1,
            JSON.stringify(field),
            "创建成功",
            function () {
                location.hash = '/emailTpl/list'

            }
        );

    });

    layui.form.on('submit(submit-emailTpl-edit)', function (formData) {
        var field = formData.field; //获取提交的字段
        ajaxPost(
            '/api/admin/email/tpl/update',
            1,
            JSON.stringify(field),
            "更新成功",
            function () {
                location.hash = '/emailTpl/list'
            }
        );

    });

    $('#testJsonData').blur(function () {
        var testJsonData = $('#testJsonData').val();
        if (!isEmpty(testJsonData)) {
            validTestJson(testJsonData);
        }
    })


    $('#render-tpl').click(function () {
        var content = $('#content').val();
        var testJsonData = $('#testJsonData').val();
        if (isEmpty(content)) {
            alertFail("提示", "请输入模板内容!", '#content');
            return false;
        }
        if (isEmpty(testJsonData)) {
            alertFail("提示", "请输入测试数据内容!", '#testJsonData');
            return false;
        }

        if (!validTestJson(testJsonData)) {
            return false;
        }

        ajaxApiPost(
            '/api/admin/email/tpl/render',
            1,
            JSON.stringify({
                content: content,
                testJsonData: testJsonData,
            }),
            function (data) {
                admin.popup({
                    title: '查看渲染'
                    , id: 'LAY-popup-art-emailTpl-render'
                    , content: data.data
                });
            },
            function (data) {
                if (data && data.msg)
                {
                    alertFail("提示", data.msg);
                }

            }
        );
    })

    function validTestJson(data) {
        try {
            var d = JSON.parse(data)
        } catch (e) {
            alertFail("错误提示", "测试的JSON数据格式非法: " + e.message);
            return false;
        }
        return true;
    }


    exports('emailTpl', {})
});