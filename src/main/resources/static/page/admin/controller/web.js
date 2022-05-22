layui.define(['form'], function (exports) {
    var $ = layui.jquery
        , view = layui.view
        , admin = layui.admin
        , form = layui.form;

    layui.form.on('submit(submit-webconf)', function (formData) {
        var field = formData.field; //获取提交的字段
        var data = [];
        $('.web-form').each(function (i) {
            var key = $(this).attr('name');
            var type = $(this).data('key');
            if (type === 'INPUT_CHECKBOX') {
                field[key] = $('input[name=' + key + ']').is(':checked');
            }
            if (field[key] === undefined) {
                return;
            }

            var validateType = $(this).attr('data-validateType');
            var validateValue = $(this).attr('data-validateValue');
            var valueType = $(this).attr('data-valueType');
            var required = $(this).attr('data-required');
            var oldValue = $(this).attr('data-oldValue');
            var title = $(this).attr('title');
            var o = {
                key: key,
                titleDsp: title,
                formType: type,
                validateType: validateType,
                validateValue: validateValue,
                required: required,
                valueType: valueType,
                value: field[key]
            };
            if (type === 'INPUT_CHECKBOX') {
                // o.value = field[key] === '1' ? 'true' : 'false';
                o.value = o.value.toString();
                oldValue = oldValue.toLowerCase()  === 'true' ? 'true' : 'false';

            }

            if (valueType === 'F') {
                oldValue = formatFloat(oldValue);
                o.value = formatFloat(o.value);
            }
            if (valueType === 'I') {
                oldValue = formatInt(oldValue);
                o.value = formatInt(o.value);
            }
            if (oldValue !== o.value) {
                data.push(o)
            }


        })
        if (data.length === 0) {
            return alertWarning('提示', '页面未作任何修改')
        }


        function formatFloat(v) {
            if (!v) return ''
            return parseFloat(v);
        }

        function formatInt(v) {
            if (!v) return ''
            return parseInt(v);
        }

        ajaxPost(
            '/api/admin/webconf/update',
            1,
            JSON.stringify({
                list: data
            }),
            "更新成功",
            function () {
                admin.events.refresh();
                form.render();
            }, function (data) {
                if (data && data.key) {
                    //针对webconf的验证,验证失败,会返回key
                    var input_dom = 'input[name=' + data.key + ']';
                    if ($(input_dom).length === 0) {
                        input_dom = 'textarea[name=' + data.key + ']'
                    }
                    setTimeout(function () {
                        $(input_dom).focus();
                        $(input_dom).addClass('layui-form-danger')
                    }, 7)

                }
            }
        );

    });


    layui.form.on('submit(submit-clearCache)', function (formData) {
        var field = formData.field; //获取提交的字段
        // console.log(field)
        var temp = field.temp === '1' ? 1 : 0;
        var log = field.log === '1' ? 1 : 0;
        var cache = field.cache === '1' ? 1 : 0;
        var memory = field.memory === '1' ? 1 : 0;

        if (!temp && !log && !cache && !memory) {
            alertFail('提示', '你未选择操作的项！');
            return false;
        }
        ajaxPost(
            '/api/admin/cache/clear',
            1,
            JSON.stringify({
                temp: temp,
                cache: cache,
                log: log,
                memory: memory,
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