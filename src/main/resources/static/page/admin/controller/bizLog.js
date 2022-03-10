layui.define(['laytable', 'form'], function (exports) {
    var table = layui.laytable
        , form = layui.form;

    table.render({
        elem: '#loginLog-table'
        , url: "/api/admin/log/login/query/list?v=1"
        , cols: [[
            {
                field: 'loginId', title: '登录ID', align: 'center', templet: function (d) {
                    return format(d)
                }
            },
            {
                field: 'eventType', title: '事件类型', align: 'center', templet: function (d) {
                    return format(d)
                }
            },
            {
                field: 'triggerTimeDesc', title: '触发时间', align: 'center', templet: function (d) {
                    return format(d)
                }
            },
            {
                field: 'triggerIp', title: '触发IP', align: 'center', templet: function (d) {
                    return format(d)
                }
            },
            {
                field: 'triggerArea', title: '触发地区', align: 'left', templet: function (d) {
                    return format(d)
                }
            },
            {
                field: 'processStatus', title: '处理状态', align: 'center', templet: function (d) {
                    return formatStatus(d)
                }
            },
            {
                field: 'failedReason', title: '失败原因', align: 'left', templet: function (d) {
                    return format(d)
                }
            },

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });
    var formatStatus = function (d) {
        if (d.processStatus === '90') {
            return '<span style="color:green">' + '成功' + '</span>';
        }
        return '<span style="color:red">' + '失败' + '</span>';

    }
    var format = function (d) {
        if (d.processStatus === '91') {
            return '<span style="color:red">' + (!d[d.LAY_COL.field] ? '' : d[d.LAY_COL.field]) + '</span>';
        }
        return !d[d.LAY_COL.field] ? '' : d[d.LAY_COL.field];
    }
    table.render({
        elem: '#bizLog-table'
        , url: "/api/admin/log/biz/query/list?v=1"
        , cols: [[
            {field: 'loginId', title: '登录ID', align: 'center'},
            {field: 'eventType', title: '事件类型', align: 'center'},
            {field: 'optType', title: '操作类型', align: 'center'},
            {field: 'optEntityDesc', title: '操作实体描述', align: 'left'},
            {field: 'triggerTimeDesc', title: '触发时间', align: 'center'},
            {field: 'triggerIp', title: '触发IP', align: 'center'},

        ]]
        , response: {
            statusName: 'status'
            , statusCode: '000000'
        }
        , page: false
        , text: {none: '无数据'}

    });


    exports('bizLog', {})
});