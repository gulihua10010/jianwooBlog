layui.define(['form', 'mouseRightMenu'], function (exports) {
    var $ = layui.jquery
        , mouseRightMenu = layui.mouseRightMenu
        , view = layui.view
        , admin = layui.admin
        , form = layui.form;

    computeInputWidth()

    doRenderTags('.tags-content ul > li')
    document.oncontextmenu = function () {
        return false;
    }
    var token;
    ajaxApiPost(
        "/api/admin/request/token/generate",
        1,
        JSON.stringify({
            pageId: 'T14',
        }),
        function (res) {
            token = res.token;
        }
    );


    $('.tags-content ul > li').bind("contextmenu", function (e) {
        var data = {oid: $(this).data('id'), text: $(this).data('text')}
        var menu_data = [
            {'data': data, 'type': 1, 'title': '编辑'},
            {'data': data, 'type': 2, 'title': '删除'},

        ]
        mouseRightMenu.open(menu_data, false, function (d) {
            if (d.type === 1) {
                admin.popup({
                    title: '标签编辑'
                    , area: ['450px', '200px']
                    , id: 'LAY-popup-tags-edit'
                    , success: function (layero, index) {
                        view(this.id).render('article/tagsEdit', d.data.oid).done(function () {
                            form.render(null, 'LAY-popup-tags-edit');
                            form.on('submit(tags-edit-submit)', function (formData) {
                                var field = formData.field; //获取提交的字段
                                ajaxPost(
                                    '/api/admin/tag/update',
                                    1,
                                    JSON.stringify({requestId: field.subToken,tagText: field.text, oid: d.data.oid}),
                                    "更新成功",
                                    function () {
                                        admin.events.refresh();
                                    }
                                );
                            })
                        });
                    }
                });


            } else if (d.type === 2) {
                alertAsk('确定要删除吗?', function () {
                    ajaxPost(
                        '/api/admin/tag/remove',
                        1,
                        JSON.stringify({entityOid: d.data.oid}),
                        "删除成功",
                        function () {
                            admin.events.refresh();
                        }
                    );
                    // $a.css('display','none');
                    // layer.closeAll();
                })
            }
        })
        return false;
    });

    // $('.added-tags-block').html("<span class='tags-show'>"+11+"</span>")
    $("#tags-add-text").unbind("input propertychange").bind('input propertychange', function () {
        if ($(this).prop('comStart')) return; // 中文输入过程中不截断
        var val = $(this).val();

        doProcessTagsInput(val);
    }).on('compositionstart', function () {
        $(this).prop('comStart', true);
    }).on('compositionend', function () {
        $(this).prop('comStart', false);
        var val = $(this).val();
        doProcessTagsInput(val);
    });

    var count = 0;
    var tagsArr = [];

    function doProcessTagsInput(val) {
        var validVal = doProcessInputVal(val)
        var lastVal = validVal.substr(validVal.length - 1);
        //解决复制粘贴字符中包含','的问题
        var tag = validVal.substr(0, validVal.length - 1).replace(/,+/g, "");
        validVal = tag + lastVal;

        $('#tags-add-text').val(validVal);

        if (lastVal == validVal && lastVal == ',') {
            $('#tags-add-text').val(tag);
            return;
        }

        var bodyWidth = $('body').css('width');
        var inputY = $('#tags-add-text').offset().left;
        var cnt = getWordsCnt(validVal);
        var isBr = false;
        if (inputY + cnt * 9.2 > parseInt(bodyWidth) - 50) {
            $('#tags-add-text').before('<br class="tags-show">');
            computeInputWidth()
            isBr = true;
        }
        //解决复制粘贴大于10个字符,并且最后一个字符是','的问题
        if (validVal.length == 11 && lastVal != ',' || validVal.length > 11) {
            alertFail("提示", "标签最大支持10个字符！");
            $('#tags-add-text').val(validVal.substr(0, 10));
            return;
        }

        if (lastVal == ',') {
            var t = $('<span class="tags-show">' + html2Escape(tag) + '</span>')
            // $('.tags-show-block').appendTo('<span class="tags-show">' + tag + '</span>');
            // $('.tags-show-block').append(t);
            if ((inputY + cnt * 9.2 > parseInt(bodyWidth) - 60) && !isBr) {
                $('#tags-add-text').before('<br class="tags-show">');
                isBr = true;
            }
            $('#tags-add-text').before(t);
            doRenderTag('.tags-show:last', count++);
            $('#tags-add-text').val('');
            computeInputWidth()
            tagsArr.push(tag);
            // console.log(parseInt(t.css('width'))/cnt)
        }


    }

    var firstTime = new Date();
    var delCount = 0;
    var timeOut = 0;
    $("#tags-add-text").on('keydown', function (e) {
        var keyCode = e.keyCode || e.where;
        if (isEmpty($('#tags-add-text').val()) && keyCode == 8) {
            if (delCount == 1) {
                var secondTime = new Date();
                delCount++;
                if (secondTime - firstTime < 1000) {
                    $('.tags-show-block .tags-show:last').remove()
                    delCount = 0;
                    clearTimeout(timeOut)
                    tagsArr.pop()
                }
            }

            if (delCount == 0 && $('.tags-show').size() > 0) {
                firstTime = new Date();
                delCount++;
                var bc = $('.tags-show-block .tags-show:last').css("background-color")
                $('.tags-show-block .tags-show:last').css("background-color", bc.replace('0.1', '0.3'))
                timeOut = setTimeout(function () {
                    delCount = 0;
                    var bc = $('.tags-show-block .tags-show:last').css("background-color")
                    $('.tags-show-block .tags-show:last').css("background-color", bc.replace('0.3', '0.1'))
                }, 1000)
            }

            computeInputWidth()
        }
    })


    function doProcessInputVal(val) {
        val = val.replace(/\r\n/g, "");
        val = val.replace(/\s/g, "");
        val = val.replace(/\t/g, "");
        return val;
    }


    function doRenderTags(doms) {
        var tags = $(doms);
        for (var i = 0; i < tags.length; i++) {
            doRenderTag(tags[i], i)
        }
    }

    function doRenderTag(dom, i) {

        var color = Math.random();
        var r = parseInt((color * (i + 1) * 1234) % 254);
        var g = parseInt((color * (i + 1) * 4321) % 254);
        var b = parseInt((color * (i + 1) * 2222) % 254);
        $(dom).css("background-color", "rgba(" + r + "," + g + "," + b + ",0.1)")
        $(dom).css("border", "  1px rgba(" + r + "," + g + "," + b + ",1) solid")
    }

    function computeInputWidth() {
        var bodyWidth = $('body').css('width');
        // var spanWidth = $('.tags-show-block').css('width');
        var inputY = $('#tags-add-text').offset().left;
        // $('.tags-show-block').css('width',(parseInt(spanWidth)+25)+'px');
        $('.add-tags-dynamic-input').css('width', (parseInt(bodyWidth) - inputY - 40) + 'px');
    }

    $('#add-tags').click(function () {
        ajaxPost(
            '/api/admin/tag/create/list',
            1,
            JSON.stringify({requestId: token,tagList: tagsArr}),
            "添加成功",
            function () {
                admin.events.refresh()
            }
        );


    })

    exports('tags', {})
});