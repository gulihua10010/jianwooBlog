layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , $ = layui.jquery
        , layer = layui.layer
    ;
    table.render({
        elem: '#email-table'
        , url: "/api/admin/email/query/list?v=1"
        , cols: [[
            {type: 'checkbox', fixed: 'right'},
            {field: 'code', title: '邮件模板编号', align: 'center', templet: function (d) {
                    return formatCode(d)
                }},
            {field: 'emailTos', title: '收件人', align: 'left'},
            {field: 'subject', title: '邮件主题', align: 'left'},
            {
                field: 'subject', title: '邮件主题', align: 'left', templet: function (d) {
                    return formatStatus(d)
                }
            },
            {field: 'sendTimeDesc', title: '发送时间', align: 'center'},
            , {title: '操作', width: 100, align: 'left', fixed: 'right', toolbar: '#table-content-email'}


        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });
    var formatStatus = function (d) {
        if (d.processStatus === '90') {
            return '<span style="color:green">' + '成功' + '</span>';
        }
        return '<span style="color:red">' + '失败' + '</span>';

    }

    var formatCode = function (d) {
        if (!d.code) {
            return '';
        }else if (d.code === '-1') {
            return '';
        }
        return '<a href="/admin#/email/emailTpl/edit/code=' + d.code + '">' + d.code + '</a>';

    }

    //监听搜索
    form.on('submit(email-search)', function (data) {
        var field = data.field;
        field.page = 1;
        var exception= $('#exception').is(':checked') ? '1' : '0';
        var failed= $('#failed').is(':checked') ? '1' : '0';
        field.exception = exception;
        field.failed = failed;
        //执行重载
        table.reload('email-table', {
            where: field
        });
    });
    form.on('switch(email-send-failed)', function (data) {
        var isChecked = data.elem.checked
        table.reload('email-table', {
            where: {
                failed: isChecked ? 1 : 0,
                code: $('#code').val(),
                subject: $('#subject').val(),
                exception: $('#exception').is(':checked') ? '1' : '0',
            }
        });

    });

    form.on('switch(email-exception)', function (data) {
        var isChecked = data.elem.checked
        table.reload('email-table', {
            where: {
                exception: isChecked ? 1 : 0,
                code: $('#code').val(),
                subject: $('#subject').val(),
                failed: $('#failed').is(':checked') ? '1' : '0',
            }
        });

    });

    //监听工具条
    table.on('tool(email)', function (obj) {
        var data = obj.data;
        if (obj.event === 'resend') {
            layer.confirm('确定重新发送邮件吗？', function (index) {
                ajaxPost(
                    "/api/admin/email/resend",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "发送成功",
                    function () {
                        layer.close(index);
                        table.reload('email-table')
                    }
                );

            });
        }

    });


    $('.layui-btn.layuiadmin-btn-email').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //点击事件
    var active = {
        reSend: function () {
            var checkStatus = table.checkStatus('email-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定把这些邮件重发吗？', function (index) {
                ajaxPost(
                    "/api/admin/email/resend/list",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "删除成功",
                    function () {
                        table.reload('email-table')
                    }
                );
            });
        },
    }


    exports('email', {})
});