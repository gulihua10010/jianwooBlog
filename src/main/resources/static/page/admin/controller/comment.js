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
        elem: '#comment-table'
        , url: "/api/admin/comment/query?v=1"
        , cols: [[
            {type: 'checkbox', fixed: 'right'}
            , {type: 'numbers', width: 40, title: 'SEQ',}
            , {field: 'artTitle', width: 200, sort: true, title: '文章标题', align: 'center'}
            , {field: 'userName', width: 80, title: '用户',  align: 'center'}
            , {field: 'commentTimeDesc', width: 200, title: '时间', sort: true, align: 'center'}
            , {field: 'replyTo', title: '回复至', width: 80, align: 'center'}
            , {field: 'content', title: '内容', align: 'left'}
            , {title: '操作', width: 300, align: 'center', fixed: 'right', toolbar: '#table-content-com'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });

    //触发排序事件
    table.on('sort(content-comm)', function(obj){

        table.reload('comment-table', {
            initSort: obj
            ,where: {
                sortField: obj.field
                ,sortOrder: obj.type
            }
        });
    });

    //监听搜索
    form.on('submit(contcomm-search)', function (data) {
        var field = data.field;
        //执行重载
        table.reload('comment-table', {
            where: field
        });
    });

    $(".search-comm").on('keypress', function (e) {
        if (e.keyCode === 13) {
            $(".search-btn").trigger("click");
        }
    });

    form.on('switch(comment-unread)', function (data) {
        var isChecked = data.elem.checked
        table.reload('comment-table', {
            where: {unread: isChecked ? 1 : 0}
        });

    });

    //点击事件
    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('comment-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定删除吗，将删除全部评论和回复评论？', function (index) {
                ajaxPost(
                    "/api/admin/comment/remove/list",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "删除成功",
                    function () {
                        table.reload('comment-table')
                    }
                );
            });
        },
        read: function () {
            var checkStatus = table.checkStatus('comment-table')
                , checkData = checkStatus.data; //得到选中的数据

            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }
            var entityOidArr = [];
            for (i in checkData) {
                entityOidArr.push(checkData[i].oid);
            }

            layer.confirm('确定将勾选评论标记为已读？', function (index) {
                ajaxPost(
                    "/api/admin/comment/read/list",
                    1,
                    JSON.stringify({entityOidList: entityOidArr}),
                    "标记成功",
                    function () {
                        table.reload('comment-table')
                    }
                );
            });
        }
    }

    $('.layui-btn.layuiadmin-btn-comm').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //监听工具条
    table.on('tool(content-comm)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此条评论？', function (index) {
                ajaxPost(
                    "/api/admin/comment/remove",
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
            var username = '博主';
            admin.popup({
                title: '回复评论'
                , area: ['450px', '300px']
                , id: 'LAY-popup-comment-reply'
                , success: function (layero, index) {
                    view(this.id).render('comment/reply', data).done(function () {
                        form.render(null, 'LAY-popup-comment-reply');
                        form.on('submit(JW-comment-reply-submit)', function (formData) {
                            var field = formData.field; //获取提交的字段
                            var headImgUrl = "/static/comm/img/headimg/" + Math.ceil(Math.random() * 10) + ".jpg";

                            //提交 Ajax 成功后，静态更新表格中的数据
                            ajaxPost(
                                "/api/admin/comment/reply",
                                1,
                                JSON.stringify({
                                    content: field.content,
                                    parentOid: data.oid,
                                    username: username,
                                    artOid: data.artOid,
                                    headImgUrl: headImgUrl,
                                    subToken: field.subToken
                                }),
                                "回复成功",
                                function () {
                                    table.reload('comment-table'); //数据刷新
                                    layer.close(index); //关闭弹层
                                }
                            );

                        })
                    });
                }
            });
        } else if (obj.event === 'view') {
            admin.popup({
                title: '评论查看'
                , area: ['900px', '600px']
                , id: 'LAY-popup-comment-view'
                , success: function (layero, index) {
                    view(this.id).render('comment/view', data.oid).done(function () {
                        layui.sessionData('comment-view-history', {
                            key: 'history'
                            , value: [data.oid]
                        });
                        layui.sessionData('comment-view-history', {
                            key: 'index'
                            , value: 0
                        });
                        form.render(null, 'LAY-popup-comment-view');

                    });
                }
            });
        }

    });

    //评论列表 渲染、操作

    var commToken;//subToken
    var artOid = layui.router().search.id;
    if (artOid === undefined || artOid === null) {
        artOid = $('#artOid').val();
    }
    renderComm = function (artOid, call) {

        ajaxApiPost(
            "/api/admin/token/generate",
            1,
            JSON.stringify({
                pageId: 'C14',
            }),
            function (res) {
                commToken = res.token;
            }
        );

        ajaxApiPost(
            "/api/admin/comment/query/article/list",
            1,
            JSON.stringify({
                entityOid: artOid,
            }),
            function (res) {
                var data = res.commentList;
                var getTpl = document.getElementById('commentTemplate').innerHTML
                    , view = document.getElementById('commentView');
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
                element.render()
                form.render()
                $('.commentView-card').show();
                if (data.length === 0) {
                    $('.commentView-card').hide()
                }
                typeof call === 'function' && call();


            }
        );

    }

    function stopBubble(e) {
        if (e && e.stopPropagation) {
            e.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
    }

    //评论操作
    var btnShow = false;
    $('.add-comment').click(function () {
        if (!btnShow) {
            $(".comment-input").css('display', 'block');
            btnShow = true;
        } else {
            $(".comment-input").css('display', 'none');
            btnShow = false;
        }
    })


    $('#commentView').on('click', '.reply', function (e) {
        stopBubble(e)
        var poid = $(this).attr('data-id')
        // console.log(poid)
        var username = '博主';

        admin.popup({
            title: '回复评论'
            , area: ['450px', '300px']
            , id: 'LAY-popup-art-comment-reply'
            , success: function (layero, index) {
                view(this.id).render('comment/reply').done(function () {
                    form.render(null, 'LAY-popup-art-comment-reply');
                    form.on('submit(JW-comment-reply-submit)', function (formData) {
                        var field = formData.field;
                        var headImgUrl = "/static/comm/img/headimg/" + Math.ceil(Math.random() * 10) + ".jpg";
                        ajaxPost(
                            "/api/admin/comment/reply",
                            1,
                            JSON.stringify({
                                content: field.content,
                                parentOid: poid,
                                username: username,
                                artOid: artOid,
                                headImgUrl: headImgUrl,
                                subToken: field.subToken
                            }),
                            "回复成功",
                            function () {
                                renderComm(artOid);
                                layer.close(index); //关闭弹层
                            }
                        );
                    })
                });
            }
        });

    });

    $('.art-comment').on('click', '.comment-btn', function () {
        var username = '博主';
        var qq = '00000000';
        var headImgUrl = "/static/comm/img/headimg/" + Math.ceil(Math.random() * 10) + ".jpg";
        var commentext = $('#comm-content').val();
        var replyId = 0;
        if (isEmpty(commentext)) {
            alertFail("提示", "评论内容不能为空")
            return false;
        }

        ajaxPost(
            "/api/admin/comment/reply",
            1,
            JSON.stringify({
                content: commentext,
                username: username,
                contactQq: qq,
                artOid: artOid,
                parentOid: replyId,
                headImgUrl: headImgUrl,
                subToken: commToken
            }),
            "评论成功",
            function () {
                renderComm(artOid);
                $('#comm-content').val('');
                $(".comment-input").css('display', 'none');
                btnShow = false;
            }
        );


    })


    $('#commentView').on('click', '.del', function (e) {
        stopBubble(e)
        var oid = $(this).attr('data-id');
        alertAsk('确定要删除此评论(所有回复评论也将会全部删除)?', function () {
            ajaxPost(
                "/api/admin/comment/remove",
                1,
                JSON.stringify({entityOid: oid}),
                "删除成功",
                function () {
                    renderComm(artOid);
                }
            );


        })


    });


    exports('comment', {})
});