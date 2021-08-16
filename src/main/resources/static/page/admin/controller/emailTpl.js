layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , $ = layui.jquery
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
                admin.events.refresh();
                form.render();
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
                admin.events.refresh();
                form.render();
            }
        );

    });

    $('#render-tpl').click(function (){
        var content = $('#content').val();
        var testJsonData = $('#testJsonData').val();
        ajaxPost(
            '/api/admin/email/tpl/update',
            1,
            JSON.stringify(field),
            "更新成功",
            function () {
                admin.events.refresh();
                form.render();
            }
        );
    })


    exports('emailTpl', {})
});