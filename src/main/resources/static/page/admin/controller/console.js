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
            {
                field: 'content', title: '评论概览', align: 'center', templet: function (d) {
                    return formatComm(d)
                }
            }

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });

    table.render({
        elem: '#message-board-table'
        , url: "/api/admin/console/recent/message/board/query?v=1"
        , cols: [[
            {
                field: 'content', title: '留言概览', align: 'center', templet: function (d) {
                    return formatMsg(d)
                }
            }

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });


    function formatComm(d) {
    return "网友 <span class=\"console-user\">["+format(d.user)+"]</span> 在 <span class=\"console-time\">"+d.commentTimeDesc+"</span> 评论了文章 \n"
        + "                <span class=\"console-title\"><a href=\"#/article/edit/id="+d.artOid +"\">"+d.artTitle+"</a></span>: <span class=\"console-comm\">"+entitiestoUtf16(d.content)+"</span>";
    }

    function formatMsg(d) {
    return "网友 <span class=\"console-user\">["+format(d.user)+"]</span> 在 <span class=\"console-time\">"+d.pushTimeDesc+"</span> 给博客留了言 \n"
            + "                : <span class=\"console-msg\">"+entitiestoUtf16(d.content)+"</span>"
    }

    formatArea = function (s) {
        if (!s) {
            return "未知地区";
        }
        return s.trim();
    }

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