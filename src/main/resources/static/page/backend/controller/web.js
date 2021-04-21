layui.define(['table', 'form', 'upload'], function (exports) {
    var $ = layui.jquery
        , view = layui.view
        , admin = layui.admin
        , form = layui.form
        , upload = layui.upload;

    layui.form.on('submit(submit-webconf)', function (formData) {
        var field = formData.field; //获取提交的字段
        var data = [];
        $('.web-form').each(function (i) {
            var type = $(this).data('key');
            var validateType = $(this).attr('data-validateType');
            var validateValue = $(this).attr('data-validateValue');
            var valueType = $(this).attr('data-valueType');
            var mandatory = $(this).attr('data-mandatory');
            var oldValue = $(this).attr('data-oldValue');
            var key = $(this).attr('name');
            var title = $(this).attr('title');
            var o = {
                key: key,
                title: title,
                formType: type,
                validateType: validateType,
                validateValue: validateValue,
                mandatory: mandatory,
                valueType: valueType,
                value: field[key]
            };
            if (type === 'INPUT_CHECKBOX') {
                o.value = field[key] === '1' ? 'true' : 'false';
                oldValue = oldValue === 'true' ? 'true' : 'false';
            }


            if (oldValue !== o.value) {
                data.push(o)
            }
        })


        ajaxPost(
            '/api/admin/webconf/update',
            JSON.stringify({
                list: data
            }),
            "更新成功",
            function () {
                admin.events.refresh();
                form.render();
            }
        );

    });


    layui.form.on('submit(submit-clearCache)', function (formData) {
        var field = formData.field; //获取提交的字段
        // console.log(field)
        var temp = field.temp === '1' ? 1 : 0;
        var log = field.log === '1' ? 1 : 0;
        var cache = field.cache === '1' ? 1 : 0;

        if (!temp && !log && !cache) {
            alertFail('提示', '你未选择操作的项！');
            return false;
        }
        ajaxPost(
            '/api/admin/cache/clear',
            JSON.stringify({
                temp: temp,
                cache: cache,
                log: log
            }),
            "清除成功",
            function () {
                admin.events.refresh();
                form.render();
            }
        );
    });


    exports('web', {})
});