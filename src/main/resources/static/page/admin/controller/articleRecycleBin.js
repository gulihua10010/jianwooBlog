layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , $ = layui.$
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
    ;

    table.render({
        elem: '#article-table'
        , url: "/api/admin/article/recycle/list"
        , cols: [[
            {type: 'checkbox', fixed: 'right'}
            , {type: 'numbers', width: 40, title: 'SEQ',}
            , {field: 'title', title: '文章标题', align: 'center'}
            , {field: 'author', width: 120, title: '作者', sort: true, align: 'center'}
            , {field: 'type', width: 170, title: '类型', sort: true, align: 'center'}
            , {field: 'publishDate', title: '删除时间', width: 200, align: 'center'}
            , {title: '操作', width: 360, align: 'left', fixed: 'right', toolbar: '#table-content-art'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });

    //监听搜索
    form.on('submit(art-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('article-table', {
            where: field
        });
    });

    //点击事件
    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('article-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定要永久删除这些文章！(此操作不可恢复)?', function (index) {
                ajaxPost(
                    "/api/admin/article/recycle/delete/list",
                    JSON.stringify({entityOidList: entityOidArr}),
                    "删除成功",
                    function () {
                        table.reload('article-table')
                    }
                );
            });
        }
        , restore: function () {
            var checkStatus = table.checkStatus('article-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定要恢复这些文章?', function (index) {
                ajaxPost(
                    "/api/admin/article/recycle/restore/draft/list",
                    JSON.stringify({entityOidList: entityOidArr}),
                    "恢复成功",
                    function () {
                        table.reload('article-table')
                    }
                );
            });
        }
    }

    $('.layui-btn.layuiadmin-btn-art').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //监听工具条
    table.on('tool(content-art)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定要永久删除这篇文章！(此操作不可恢复)?', function (index) {
                ajaxPost(
                    "/api/admin/article/recycle/delete",
                    JSON.stringify({entityOid: data.oid}),
                    "删除成功",
                    function () {
                        obj.del();
                        layer.close(index);
                    }
                );

            });
        } else if (obj.event === 'view') {
            admin.popup({
                title: '回收站文章预览 - ' + data.title
                , area: ['700px', '600px']
                , id: 'LAY-popup-article-recycleBin'
                , success: function (layero, index) {
                    view(this.id).render('article/recycleBinView', data).done(function () {
                        form.render(null, 'LAY-popup-article-recycleBin');

                    });
                }
            });
        } else if (obj.event === 'restore') {
            layer.confirm('确定把这篇文章恢复至草稿吗？', function (index) {
                ajaxPost(
                    "/api/admin/article/recycle/restore/draft",
                    JSON.stringify({entityOid: data.oid}),
                    "恢复成功",
                    function () {
                        table.reload('article-table');
                    }
                );

            });
        }

    });


    exports('articleRecycleBin', {})
});