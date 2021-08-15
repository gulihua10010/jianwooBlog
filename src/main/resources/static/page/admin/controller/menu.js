layui.define(['form', 'mouseRightMenu'], function (exports) {
    var $ = layui.jquery
        , mouseRightMenu = layui.mouseRightMenu
        , view = layui.view
        , admin = layui.admin
        , form = layui.form;

    document.oncontextmenu = function () {
        return false;
    }

    menuEdit();

    function menuEdit() {
        var index = [];
        $(function () {
            $('.sortable').sortable({
                cursor: 'move',
                items: '.sorts',
                update: function (event, ui) {

                    for (var i = 0; i < $('.sorts').length; i++) {

                        var indextext = $('.sorts').eq(i).data("id")
                        index[i] = indextext;
                    }
                    // console.log(index)
                    ajaxPost(
                        "/api/admin/menu/sort",
                        1,
                        JSON.stringify({
                            entityOidList: index,
                        }),
                        "更新成功"
                    );

                },
            }).disableSelection();
        })
        var handle = null;
        $('.menu_content').mouseover(function () {
            var text = $(this).data("data");
            var tipSpan = $(this).children("span");
            handle = setTimeout(function () {
                if (text != null && text != undefined && text != '') {
                    layer.tips(text, tipSpan)
                }
            }, 500);

        }).mouseout(function () {
            clearTimeout(handle);
        });

        $('.menu_content').bind("contextmenu", function (e) {
            var data = {oid: $(this).data('id'), text: $(this).data('data')}
            if (data.text == null || data.text == undefined || data.text == '') {
                return false;
            }
            var menu_data = [
                {'data': data, 'type': 1, 'title': '编辑'},
                {'data': data, 'type': 2, 'title': '删除'},
            ]
            mouseRightMenu.open(menu_data, false, function (d) {
                if (d.type === 1) {
                    var id = d.data.oid
                    admin.popup({
                        title: '菜单编辑'
                        , area: ['600px', '400px']
                        , id: 'LAY-popup-menu-edit'
                        , success: function (layero, index) {
                            view(this.id).render('menu/edit', id).done(function () {
                                form.render(null, 'LAY-popup-menu-edit');
                                form.on('submit(menu-edit-submit)', function (formData) {
                                    var field = formData.field; //获取提交的字段
                                    ajaxPost(
                                        "/api/admin/menu/update",
                                        1,
                                        JSON.stringify({
                                            name: field.name,
                                            text: field.text,
                                            icon: field.icon,
                                            url: field.url,
                                            valid: field.valid === 1 ? 1 : 0,
                                            subToken: field.subToken,
                                            oid: id
                                        }),
                                        "更新成功",
                                        function () {
                                            layer.close(index); //关闭弹层
                                            admin.events.refresh();
                                        }
                                    );
                                })
                            });
                        }
                    });

                } else if (d.type === 2) {
                    var id = d.data.oid;
                    ajaxApiPost(
                        "/api/admin/menu/validate/submenu",
                        1,
                        JSON.stringify({
                            entityOid: id
                        }),
                        function (data) {
                            if (data.isSuccess == "N") {
                                layer.confirm(data.resultMsg, function (index) {
                                    layer.close(index);
                                });
                            } else {
                                ajaxApiPost(
                                    "/api/admin/menu/validate/article/exist",
                                    1,
                                    JSON.stringify({
                                        entityOid: id
                                    }),
                                    function (data) {
                                        if (data.isSuccess == "N") {
                                            layer.confirm(data.resultMsg, function (index) {
                                                layer.close(index);
                                            });
                                        } else {
                                            layer.confirm("确定要删除此菜单嘛？", function (index) {
                                                ajaxPost(
                                                    "/api/admin/menu/remove",
                                                    1,
                                                    JSON.stringify({
                                                        entityOid: id
                                                    }),
                                                    "删除成功",
                                                    function () {
                                                        layer.close(index);
                                                        admin.events.refresh();
                                                    }
                                                );
                                            });

                                        }

                                    }
                                );
                            }

                        }
                    );
                }
            })
        });


    }

    // 添加菜单
    $('.addMenu').click(function () {
        var pid = $(this).data("id");
        admin.popup({
            title: '添加菜单'
            , area: ['500px', '400px']
            , id: 'LAY-popup-menu-add'
            , success: function (layero, index) {
                view(this.id).render('menu/add').done(function () {
                    form.render(null, 'LAY-popup-menu-add');
                    form.on('submit(menu-add-submit)', function (formData) {
                        var field = formData.field;
                        ajaxPost(
                            "/api/admin/menu/add",
                            1,
                            JSON.stringify({
                                name: field.name,
                                text: field.text,
                                icon: field.icon,
                                url: field.url,
                                parentOid: pid,
                                subToken: field.subToken,

                            }),
                            "添加成功",
                            function () {
                                layer.close(index); //关闭弹层
                                admin.events.refresh();
                            }
                        );
                    })
                });
            }
        });

    })

    exports('menu', {})
});