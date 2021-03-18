/**
 * upfile 1.1v
 * The tinymce-plugins is used to upfile
 *
 * https://github.com/Five-great/tinymce-plugins
 *
 * Copyright 2020, Five(Li Hailong) The Chengdu, China https://www.fivecc.cn/
 *
 * Licensed under MIT
 */
tinymce.PluginManager.add('upfile', function (editor, url) {
    var pluginName = '上传文件';
    window.upfile = {}; //扔外部公共变量，也可以扔一个自定义的位置

    var baseURL = tinymce.baseURL || '.';
    var iframe1 = baseURL + '/plugins/upfile/upfiles.html';
    upfile.file_callback = editor.getParam('file_callback', undefined, 'function');
    upfile.tinymce = tinymce;
    upfile.res = {};
    var openDialog = function () {
        return editor.windowManager.openUrl({
            title: pluginName,
            size: 'large',
            width: 450,
            height: 450,
            url: iframe1,
            buttons: [
                {
                    type: 'cancel',
                    text: 'Close'
                },
                {
                    type: 'custom',
                    text: 'Save',
                    name: 'save',
                    primary: true
                },
            ],
            onAction: function (api, details) {
                switch (details.name) {
                    case 'save':
                        var res = upfile.res;
                        var html = '<span  class="attachment" contenteditable="false"><a href="' + res.url + '" target="_blank" title="' + res.text + '" >' + res.text + '<a></span>';
                        editor.insertContent(html);
                        upfile.res = {};
                        api.close();
                        break;
                    default:
                        break;
                }

            }
        });
    };

    editor.ui.registry.getAll().icons.upfile || editor.ui.registry.addIcon('upfile', '<svg t="1616034901576" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1315" width="26" height="26"><path d="M842.512 240.352v-0.192a27.68 27.68 0 0 0-0.176-1.744l-0.032-0.24a27.584 27.584 0 0 0-5.184-12.816l-0.368-0.48a27.936 27.936 0 0 0-1.328-1.6l-0.56-0.592-0.608-0.64-152.48-149.776a27.68 27.68 0 0 0-16.48-8.08h-0.064a27.52 27.52 0 0 0-1.888-0.16h-0.432L662.032 64H203.776C188.432 64 176 76.432 176 91.792v833.76c0 15.36 12.432 27.792 27.776 27.792h611.008c15.344 0 27.776-12.432 27.776-27.792v-683.68a28.64 28.64 0 0 0-0.048-1.52z m-152.704-82.304l57.04 56.032h-57.04V158.048z m97.2 739.712H231.52V119.584h402.72v122.304c0 15.36 12.448 27.792 27.776 27.792h124.96V897.76z m-78.304-384.368H323.84c-15.36 0-27.776 12.432-27.776 27.792 0 15.36 12.432 27.792 27.776 27.792h384.88a27.792 27.792 0 0 0 0-55.584z m-412.656-88.944c0 15.36 12.432 27.808 27.776 27.808h384.88a27.792 27.792 0 0 0 0-55.6H323.84c-15.36 0-27.776 12.432-27.776 27.792z m412.656 205.648H323.84c-15.36 0-27.776 12.432-27.776 27.792 0 15.36 12.432 27.792 27.776 27.792h384.88a27.792 27.792 0 0 0 0-55.584z m0 116.736H323.84a27.776 27.776 0 1 0 0 55.584h384.88a27.792 27.792 0 0 0 0-55.584z" p-id="1316"></path></svg>');

    editor.ui.registry.addButton('upfile', {
        icon: 'upfile',
        tooltip: pluginName,
        onAction: function () {
            openDialog();
        }
    });
    editor.ui.registry.addMenuItem('upfile', {
        icon: 'upfile',
        text: '文件上传...',
        onAction: function () {
            openDialog();
        }
    });
    return {
        getMetadata: function () {
            return {
                name: pluginName,
                url: "https://github.com/Five-great/tinymce-plugins",
            };
        }
    };
});
