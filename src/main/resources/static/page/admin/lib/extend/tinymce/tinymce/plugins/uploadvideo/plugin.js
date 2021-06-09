/**
 * 29/03/2021
 * gulihua http://jianwoo.cn
 */
tinymce.PluginManager.add('uploadvideo', function (editor, url) {
    var pluginName = '插入视频';
    window.upfile = {};
    var baseURL = tinymce.baseURL || '.';
    var iframe = url + '/uploadVideo.html';
    upfile.file_callback = editor.getParam('file_callback', undefined, 'function');
    upfile.tinymce = tinymce;
    upfile.res = {};
    var openDialog = function () {
        return editor.windowManager.openUrl({
            title: pluginName,
            size: 'large',
            width: 500,
            height: 550,
            url: iframe,
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
                        doInsertHtml(res);
                        upfile.res = {};
                        api.close();
                        break;
                    default:
                        break;
                }

            }
        });
    };

    var doInsertHtml = function (uploadFile) {
        if (uploadFile !== undefined && uploadFile !== null) {
            var html = '<video width="' + uploadFile.sizeWidth + '" height="' + uploadFile.sizeHeight + '" controls="controls">' +
                '<source src="' + uploadFile.url + '" type="video/mp4"></video>';
            editor.insertContent(html);

        }

    }
    editor.ui.registry.getAll().icons.upfile || editor.ui.registry.addIcon('uploadvideo', '<svg t="1616997308428" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1305" width="26" height="26"><path d="M621.8 236.8H142.3s-80.3 0-80.3 78v394.5c0 78 80.3 78 80.3 78h479.5s80.3 0 80.3-78V317.1c0.1-80.3-80.3-80.3-80.3-80.3zM950.2 277c-7.1-2.4-14.2-2.4-18.9 2.4L773 402.2c-4.7 4.7-7.1 9.4-7.1 14.2v196.1c0 4.7 2.4 11.8 7.1 14.2l158.3 122.8c2.4 2.4 7.1 4.7 11.8 4.7 2.4 0 4.7 0 9.4-2.4 7.1-2.4 9.4-9.4 9.4-16.5V293.5c0.1-7.1-4.6-14.2-11.7-16.5z" p-id="1306"></path></svg>');

    editor.ui.registry.addButton('uploadvideo', {
        icon: 'uploadvideo',
        tooltip: pluginName,
        onAction: function () {
            openDialog();
        }
    });
    editor.ui.registry.addMenuItem('uploadvideo', {
        icon: 'uploadvideo',
        text: '插入视频',
        onAction: function () {
            openDialog();
        }
    });
    return {
        getMetadata: function () {
            return {
                name: pluginName,
                url: "http://jianwoo.cn",
            };
        }
    };

});