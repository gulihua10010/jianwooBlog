layui.define(['laytable', 'form', 'laytpl', 'element'], function (exports) {
    var table = layui.laytable
        , $ = layui.$
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , laytpl = layui.laytpl
        , element = layui.element;

    ;

    table.render({
        elem: '#message-board-table'
        , url: "/api/admin/message/board/query/list?v=1"
        , cols: [[
            {type: 'checkbox', fixed: 'right'}
            , {type: 'numbers', width: 40, title: 'SEQ',}
            , {field: 'userNick', width: 80, title: '用户', align: 'center'}
            , {field: 'pushTimeDesc', width: 200, title: '时间', sort: true, align: 'center'}
            , {field: 'replyTo', title: '回复至', width: 80, align: 'center'}
            , {
                field: 'content', title: '内容', align: 'left', templet: function (d) {
                    return format(d)
                }
            }
            , {title: '操作', width: 300, align: 'center', fixed: 'right', toolbar: '#table-content-message-board'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , where: {
            oid : layui.router().search.oid
        }
        , page: true
        , autoSort: false //禁用前端自动排序。
        , text: {none: '无数据'}

    });

    function format(d) {
        return entitiestoUtf16(d.content)
    }



    //触发排序事件
    table.on('sort(content-msg-board)', function (obj) {

        table.reload('message-board-table', {
            initSort: obj
            , where: {
                sortField: obj.field
                , sortOrder: obj.type
            }
        });
    });

    //监听搜索
    form.on('submit(message-board-search)', function (data) {
        var field = data.field
        field.page = 1;
        //执行重载
        table.reload('message-board-table', {
            where: field
        });
    });

    $(".search-message-board").on('keypress', function (e) {
        if (e.keyCode === 13) {
            $(".search-btn").trigger("click");
        }
    });

    form.on('switch(message-board-unread)', function (data) {
        var isChecked = data.elem.checked
        table.reload('message-board-table', {
            where: {
                unread: isChecked ? 1 : 0,
                title: $('#title').val(),
            }
        });

    });




    //点击事件
    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('message-board-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定删除这些勾选的留言吗?', function (index) {
                ajaxPost(
                    "/api/admin/message/board/remove/list",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "删除成功",
                    function () {
                        table.reload('message-board-table')
                    }
                );
            });
        },
        read: function () {
            var checkStatus = table.checkStatus('message-board-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定将勾选留言标记为已读？', function (index) {
                ajaxPost(
                    "/api/admin/message/board/read/list",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "标记成功",
                    function () {
                        table.reload('message-board-table')
                    }
                );
            });
        }
    }

    $('.layui-btn.layuiadmin-btn-message-board').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //监听工具条
    table.on('tool(content-message-board)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此条留言？', function (index) {
                ajaxPost(
                    "/api/admin/message/board/remove",
                    1,
                    JSON.stringify({entityOid: data.oid}),
                    "删除成功",
                    function () {
                        obj.del();
                        layer.close(index);
                    }
                );

            });
        } else if (obj.event === 'reply') {
            var userNick = '博主';
            admin.popup({
                title: '回复留言'
                , area: ['450px', '300px']
                , id: 'LAY-popup-message-board-reply'
                , success: function (layero, index) {
                    view(this.id).render('web/messageBoard/reply', data).done(function () {
                        form.render(null, 'LAY-popup-message-board-reply');
                        form.on('submit(JW-message-board-reply-submit)', function (formData) {
                            var field = formData.field; //获取提交的字段
                            var avatarSrc = "/static/comm/img/avatar/" + Math.ceil(Math.random() * 10) + ".jpg";

                            //提交 Ajax 成功后，静态更新表格中的数据
                            ajaxPost(
                                "/api/admin/message/board/reply",
                                1,
                                JSON.stringify({
                                    requestId: field.subToken,
                                    content: field.content,
                                    parentOid: data.oid,
                                    userNick: userNick,
                                    artOid: data.artOid,
                                    avatarSrc: avatarSrc,
                                }),
                                "回复成功",
                                function () {
                                    table.reload('message-board-table'); //数据刷新
                                    layer.close(index); //关闭弹层
                                }
                            );

                        })
                    });
                }
            });
        } else if (obj.event === 'view') {
            admin.popup({
                title: '留言查看'
                , area: ['900px', '600px']
                , id: 'LAY-popup-message-board-view'
                , success: function (layero, index) {
                    view(this.id).render('web/messageBoard/view', data.oid).done(function () {
                        form.render(null, 'LAY-popup-message-board-view');

                    });
                }
            });
        }

    });

    exports('messageBoard', {})
});