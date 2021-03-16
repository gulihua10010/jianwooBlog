layui.define(['table', 'form'], function (exports) {
    var table = layui.table
        , $ = layui.$
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
    ;


    table.render({
        elem: '#article-table'
        , url: "/api/admin/article/effective/list"
        , cols: [[
            {type: 'checkbox', fixed: 'right'}
            , {type: 'numbers', width: 40, title: 'SEQ',}
            , {field: 'title', title: '文章标题', align: 'center'}
            , {field: 'author', width: 120, title: '作者', sort: true, align: 'center'}
            , {field: 'type', width: 170, title: '类型', sort: true, align: 'center'}
            , {field: 'publishDate', title: '发布时间', width: 200, align: 'center'}
            // , {field: 'modifiedDate', title: '最后修改时间', width: 200, align: 'center'}
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

            layer.confirm('确定把这些文章移除到回收站吗？', function (index) {
                ajaxPost(
                    "/api/admin/article/remove/recycle/list",
                    JSON.stringify({entityOidList: entityOidArr}),
                    "移除成功",
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
            layer.confirm('确定把这篇文章移除到回收站吗？', function (index) {
                ajaxPost(
                    "/api/admin/article/remove/recycle",
                    JSON.stringify({entityOid: data.oid}),
                    "移除成功",
                    function () {
                        obj.del();
                        layer.close(index);
                    }
                );

            });
        } else if (obj.event === 'quickEdit') {
            admin.popup({
                title: '快速编辑' + data.title
                , area: ['600px', '500px']
                , id: 'LAY-popup-article-fast-edit'
                , success: function (layero, index) {
                    view(this.id).render('article/quickEdit', data).done(function () {
                        var oid = data.oid;
                        var status = data.status;
                        form.render(null, 'LAY-popup-article-fast-edit');
                        //监听提交
                        form.on('submit(JW-quick-edit-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            var tags = layero.contents().find('.tags:checked');
                            var comment = layero.contents().find('#isComment');
                            var type = layero.contents().find('#type');
                            var tagsId = [];
                            for (var i = 0; i < tags.length; i++) {
                                tagsId[i] = tags.eq(i).val()
                            }
                            var isComment = 0;
                            if (comment.prop('checked')) {
                                isComment = 1;
                            }
                            if (status === 1 && type.val() === undefined || type.val() === -1) {
                                alertFail("提示", "文章类型不能为空")
                                return;
                            }
                            ajaxPost(
                                '/api/admin/article/info/update',
                                JSON.stringify({
                                    artOid: oid,
                                    title: field.title,
                                    author: field.author,
                                    tags: tagsId,
                                    type: type.val(),
                                    visitType: field.isPublic,
                                    password: field.passwContent,
                                    isComment: isComment,
                                    subToken: field.subToken
                                }),
                                "更新成功",
                                function () {
                                    table.reload('article-table'); //数据刷新 007
                                    layer.close(index); //关闭弹层
                                }
                            );
                        });
                    });
                }
            });

        } else if (obj.event === 'publish') {
            layer.confirm('确定把这篇文章发布吗？', function (index) {
                ajaxPost(
                    "/api/admin/article/draft/status/publish",
                    JSON.stringify({entityOid: data.oid}),
                    "发布成功",
                    function () {
                        table.reload('article-table');
                    }
                );

            });
        } else if (obj.event === 'edit') {
            top.layui.index.openTabsPage("/article/edit/id=" + data.oid, '文章编辑');
        }

    });


    exports('articleList', {})
});