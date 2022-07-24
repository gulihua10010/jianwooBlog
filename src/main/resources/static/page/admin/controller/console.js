layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form;

    table.render({
        elem: '#published-table'
        , url: "/api/admin/console/recent/article/published/query?v=1"
        , cols: [[
            {field: 'desc', title: '文章概览', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });
    table.render({
        elem: '#draft-table'
        , url: "/api/admin/console/recent/article/draft/query?v=1"
        , cols: [[
            {field: 'desc', title: '文章概览', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });
    table.render({
        elem: '#comment-table'
        , url: "/api/admin/console/recent/comment/query?v=1"
        , cols: [[
            {field: 'desc', title: '评论概览', align: 'center'}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });


    form.on('submit(JW-submit)', function (formData) {
        var field = formData.field;
        var title = field.title;
        var author = field.author;
        var articleContent = field.content;
        ajaxPost(
            '/api/admin/article/save/draft',
            1,
            {
                requestId: field.subToken,
                title: title,
                author: author,
                articleContent: articleContent,
            },
            "保存成功",
            function () {
                location.reload()
            }
        );

    });

    exports('console', {})
});