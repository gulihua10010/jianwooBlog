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
            {field: 'content', title: '评论记录', align: 'center', templet: function (d) {
                    return formatComm(d)
                }}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });
    table.render({
        elem: '#msg-board-table'
        , url: "/api/admin/dynamic/message/board/query?v=1"
        , cols: [[
            {field: 'content', title: '留言记录', align: 'center', templet: function (d) {
                    return formatMsg(d)
                }}

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: true
        , text: {none: '无数据'}

    });

    function formatComm(d) {
        return "来自<span class=\"dynamic-ip\">"+formatArea(d.area)+" (ip:"+format(d.ip)+")</span> 的网友 <span class=\"dynamic-user\">["+format(d.user)+"]</span> 在 <span class=\"dynamic-date\">"+d.commentTimeDesc+"</span> 评论了文章 \n"
            + "                <span class=\"dynamic-title\"><a href=\"#/article/edit/id="+d.artOid+"\">"+d.artTitle+"</a></span>: <span class=\"dynamic-comm\">"+entitiestoUtf16(d.content)+"</span>"
    }

    function formatMsg(d) {
        return "来自<span class=\"dynamic-ip\">"+formatArea(d.area)+" (ip:"+format(d.ip)+")</span> 的网友 <span class=\"dynamic-user\">["+format(d.user)+"]</span> 在 <span class=\"dynamic-date\">"+d.pushTimeDesc+"</span> 给博客留了言 \n"
            + "                : <span class=\"dynamic-comm\">"+entitiestoUtf16(d.content)+"</span>"
    }

    formatArea = function (s) {
        if (!s) {
            return "未知地区";
        }
        return s.trim();
    }

    exports('dynamic', {})
});