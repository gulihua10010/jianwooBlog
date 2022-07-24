layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , $ = layui.jquery
        , layer = layui.layer
    ;
    table.render({
        elem: '#black-table'
        , url: "/api/admin/black/ip/query/list?v=1"
        , cols: [[
            {type: 'checkbox', fixed: 'right'},
            {field: 'ip', title: 'IP', align: 'center'},
            {field: 'createdTimeDesc', title: '创建时间', align: 'center'},
            , {title: '操作', width: 100, align: 'left', fixed: 'right', toolbar: '#table-content-black'}


        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });

    //监听搜索
    form.on('submit(black-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('black-table', {
            where: field
        });
    });



    //监听工具条
    table.on('tool(black)', function (obj) {
        var data = obj.data;
        if (obj.event === 'remove') {
            layer.confirm('确定把此IP从黑名单列表移出吗？', function (index) {
                ajaxPost(
                    "/api/admin/black/ip/remove",
                    1,
                    JSON.stringify({ip: data.ip}),
                    "移出成功",
                    function () {
                        layer.close(index);
                        table.reload('black-table')
                    }
                );

            });
        }

    });


    $('.layui-btn.layuiadmin-btn-black').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //点击事件
    var active = {
        removeList: function () {
            var checkStatus = table.checkStatus('black-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var ipArr = [];
            for (i in checkData) {
                ipArr.push(checkData[i].ip);
            }

            layer.confirm('确定把这些IP从黑名单列表移出吗？', function (index) {
                ajaxPost(
                    "/api/admin/black/ip/remove/list",
                    1,
                    JSON.stringify({ipList: ipArr}),
                    "移出成功",
                    function () {
                        table.reload('black-table')
                    }
                );
            });
        },
        add: function () {
            admin.popup({
                title: '添加黑名单IP'
                , area: ['600px', '300px']
                , id: 'LAY-popup-black-ip-add'
                , success: function (layero, index) {
                    view(this.id).render('web/blackIp/add').done(function () {

                        form.render(null, 'LAY-popup-black-ip-add');
                        //监听提交
                        form.on('submit(black-add-submit)', function (data) {
                            var field = data.field; //获取提交的字段
                            var ipArr = field.ipList.split(",");
                            ajaxPost(
                                '/api/admin/black/ip/add/list',
                                1,
                                JSON.stringify({
                                    requestId: field.subToken,
                                    ipList: ipArr,
                                }),
                                "添加成功",
                                function () {
                                    table.reload('black-table'); //数据刷新 007
                                    layer.close(index); //关闭弹层
                                }
                            );
                        });
                    });
                }
            });
        },
    }


    exports('blackIp', {})
});