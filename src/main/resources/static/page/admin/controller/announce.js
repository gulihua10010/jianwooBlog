layui.define(['laytable', 'form', 'tinymce'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , $ = layui.jquery
        , layer = layui.layer
        , tinymce = layui.tinymce

    ;
    table.render({
        elem: '#announce-table'
        , url: "/api/admin/announcement/query/list?v=1"
        , cols: [[
            {type: 'checkbox', fixed: 'right'},
            {type: 'numbers', width: 40, title: 'SEQ',},
            {field: 'title', title: '标题', align: 'center'},
            {field: 'pushBy', width: 100, title: '发布者', align: 'left'},
            {field: 'pushTimeDesc', width: 200, title: '发布时间', align: 'left'},
            {field: 'expiationTime', width: 200, title: '过期时间', align: 'center'},
            {
                field: 'status', width: 100, title: '状态', align: 'center', templet: function (d) {
                    return d.status === '00' ? '有效' : (d.status === '91' ? '作废' : '');
                }
            },
            {title: '操作', width: 250, align: 'left', fixed: 'right', toolbar: '#table-content-announce'}


        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });



    var edit;

    function getEdit(onChange, callback) {
        if (edit) return edit;
        edit = tinymce.render({
            elem: "#announce-content"
        }, function (opt, edit) {
            typeof callback === "function" && callback(opt, edit);

        },function (editor) {
            editor.on('keyup', function (){
                typeof onChange === "function" && onChange(editor);
            });
        });
        return edit;
    }













    //监听搜索
    form.on('submit(announce-search)', function (data) {
        var field = data.field;
        field.page = 1;
        //执行重载
        table.reload('announce-table', {
            where: field
        });
    });
    form.on('switch(announce-void)', function (data) {
        var isChecked = data.elem.checked
        table.reload('announce-table', {
            where: {
                status: isChecked ? '00' : '',
                title: $('#title').val(),
            }
        });

    });


    //监听工具条
    table.on('tool(announce)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定把此公告删除吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/remove",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "删除成功",
                    function () {
                        obj.del();
                        layer.close(index);
                    }
                );

            });
        } else if (obj.event === 'void') {
            layer.confirm('确定把此公告作废吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/void",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "作废成功",
                    function () {
                        layer.close(index);
                        table.reload('announce-table')
                    }
                );

            });
        } else if (obj.event === 'revert') {
            layer.confirm('确定把此作废公告恢复吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/revert",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "恢复成功",
                    function () {
                        layer.close(index);
                        table.reload('announce-table')

                    }
                );

            });
        }

    });

    layui.form.on('submit(submit-announce-create)', function (formData) {
        var field = formData.field; //获取提交的字段
        var contentText = edit.getContent();

        if (contentText == '<!DOCTYPE html><html><head></head><body></body></html>') {
            alertFail('提示', '公告内容不可为空！', '#announce-content')
            return false;
        }
        field.content = contentText;

        ajaxPost(
            '/api/admin/announcement/create',
            1,
            JSON.stringify(field),
            "创建成功",
            function () {
                location.hash = '/announce/list'

            }
        );

    });

    layui.form.on('submit(submit-announce-edit)', function (formData) {
        var field = formData.field; //获取提交的字段
        var contentText = edit.getContent();

        if (contentText == '<!DOCTYPE html><html><head></head><body></body></html>') {
            alertFail('提示', '公告内容不可为空！', '#announce-content')
            return false;
        }
        field.content = contentText;
        ajaxPost(
            '/api/admin/announcement/update',
            1,
            JSON.stringify(field),
            "更新成功",
            function () {
                location.hash = '/announce/list'
            }
        );

    });

    $('.layui-btn.layuiadmin-btn-announce').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //点击事件
    var active = {
        batchDel: function () {
            var checkStatus = table.checkStatus('announce-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定把这些公告删除吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/list/remove",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "删除成功",
                    function () {
                        table.reload('announce-table')
                    }
                );
            });
        },
        batchVoid: function () {
            var checkStatus = table.checkStatus('announce-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            for (let i = 0; i < checkData.length; i++) {
                if (checkData[i].status === '91') {
                    return layer.msg('请选择状态为作废数据');

                }
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定把这些公告作废吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/list/void",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "作废成功",
                    function () {
                        table.reload('announce-table')
                    }
                );
            });
        },
        batchRevert: function () {
            var checkStatus = table.checkStatus('announce-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            for (let i = 0; i < checkData.length; i++) {
                if (checkData[i].status === '00') {
                    return layer.msg('请选择状态为有效数据');

                }
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定把这些作废公告恢复吗？', function (index) {
                ajaxPost(
                    "/api/admin/announcement/list/revert",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "恢复成功",
                    function () {
                        table.reload('announce-table')
                    }
                );
            });
        }
    }


    exports('announce', {'getEdit': getEdit})
});