layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form
        , view = layui.view
        , admin = layui.admin
        , $ = layui.jquery
        , layer = layui.layer
    ;
    table.render({
        elem: '#msg-table'
        , url: "/api/admin/msg/query/list?v=1"
        , cols: [[
            {
                field: 'content', title: '内容', align: 'center', width: '70%', templet: function (d) {
                    return format(d)
                }
            },
            {field: 'sendTimeDesc', title: '时间', align: 'center', templet: function (d) {
                    return formatTime(d)
                }},
            , {title: '操作', width: 100, align: 'left', fixed: 'right', toolbar: '#table-content-msg'}


        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无消息'}

    });

    var format = function (d) {


        if (d.isRead === '0') {
            return '<span style="font-weight:bold" class="msgId" data-id="' + d.oid + '">' + d.content + '</span>'
        }
        return d.content;

    }
    var formatTime = function (d) {


        if (d.isRead === '0') {
            return '<span style="font-weight:bold" class="msgId" data-id="' + d.oid + '">' + d.sendTimeDesc + '</span>'
        }
        return d.sendTimeDesc;

    }

    table.on('row(msg)', function(obj){
        if (obj.data.isRead === '0'){
            ajaxPost(
                "/api/admin/msg/read",
                1,
                JSON.stringify({entityOid: obj.data.oid}),
                "",
                function () {
                    $(obj.tr).find('.msgId').css('font-weight','normal')
                }
            );
        }

    });



    form.on('submit(msg-all-read)', function (data) {
        layer.confirm('确定把全部消息标记为已读吗？', function (index) {

            ajaxPost(
                "/api/admin/msg/all/read",
                1,
                JSON.stringify({}),
                "标记成功",
                function () {
                }
            );
        });
    });


    exports('msg', {})
});