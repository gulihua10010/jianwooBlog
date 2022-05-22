layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable

    table.render({
        elem: '#access-table'
        , url: "/api/admin/dynamic/access/query?v=1"
        , cols: [[
            {field: 'desc', title: '访问记录', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });
    table.render({
        elem: '#comm-table'
        , url: "/api/admin/dynamic/comment/query?v=1"
        , cols: [[
            {field: 'desc', title: '评论记录', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });
    exports('dynamic', {})
});