layui.define(['table', 'form'], function (exports) {
    var table = layui.table
        , form = layui.form;

    table.render({
        elem: '#published-table'
        , url: "/api/admin/console/recent/article/published/query"
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
        , url: "/api/admin/console/recent/article/draft/query"
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
        , url: "/api/admin/console/recent/comment/query"
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
            {
                title: title,
                author: author,
                articleContent: articleContent,
                subToken: field.subToken
            },
            "保存成功",
            function () {
                location.reload()
            }
        );

    });

    exports('console', {})
});