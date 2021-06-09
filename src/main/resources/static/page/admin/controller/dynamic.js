layui.define(['table', 'form'], function (exports) {
    var table = layui.table

    table.render({
        elem: '#visit-table'
        , url: "/api/admin/dynamic/visit/query"
        , cols: [[
            {field: 'desc', title: '访问记录', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , where: {
            access_token: layui.data('layuiAdmin').access_token
        }
        , page: true
        , text: {none: '无数据'}

    });
    table.render({
        elem: '#comm-table'
        , url: "/api/admin/dynamic/comment/query"
        , cols: [[
            {field: 'desc', title: '访问记录', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , where: {
            access_token: layui.data('layuiAdmin').access_token
        }
        , page: true
        , text: {none: '无数据'}

    });
    exports('dynamic', {})
});