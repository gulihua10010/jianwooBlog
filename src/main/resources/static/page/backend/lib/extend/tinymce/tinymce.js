//  http://tinymce.ax-z.cn/   中文文档

layui.define(['jquery'], function (exports) {
    var $ = layui.$

    var admin = layui.admin || {}

    var modFile = layui.cache.modules['tinymce'];

    var modPath = modFile.substr(0, modFile.lastIndexOf('.'))

    var setter = layui.setter || {}//兼容layuiadmin

    var response = setter.response || {}//兼容layuiadmin

    //  ----------------  以上代码无需修改  ----------------

    var plugin_filename = 'tinymce.min.js'//插件路径，不包含base_url部分

    var settings = {
        base_url: modPath
        , images_upload_url: '/api/file/upload'//图片上传接口，可在option传入，也可在这里修改，option的值优先
        , language: 'zh_CN'//语言，可在option传入，也可在这里修改，option的值优先
        , height: 500
        , max_height: 550
        , resize: true
        , paste_merge_formats: true
        , paste_data_images: true
        , powerpaste_word_import: 'propmt'
        , powerpaste_html_import: 'propmt'
        , response: {//后台返回数据格式设置
            statusName: response.statusName || 'code'//返回状态字段
            , msgName: response.msgName || 'msg'//返回消息字段
            , dataName: 'file' //返回的数据
            , urlField: 'url'  // 上传接口返回的文件路由
            , filenameField: 'fileName'  // 上传接口返回的文件名
            , statusCode: response.statusCode || {
                ok: 0//数据正常
            }
        }
        , success: function (res, succFun) {//图片上传完成回调 根据自己需要修改
            if (res[this.response.statusName] === this.response.statusCode.ok) {
                // console.log(res)
                // succFun(res.file.url,{ text: res.msg });

                succFun(this.response.urlField ? res[this.response.dataName][this.response.urlField] : this.response.dataName
                    , this.response.filenameField ? res[this.response.dataName][this.response.filenameField] : this.response.filenameField);
            } else {
                createNotify(res[this.response.msgName], 'error');
            }
        }
    };

    //  ----------------  以下代码无需修改  ----------------

    var t = {};

    var elem;

    //初始化
    t.render = function (options, callback) {

        initTinymce();

        var option = initOptions(options, callback)

            , edit = t.get(option.elem);

        if (edit) {
            edit.destroy();
        }

        tinymce.init(option);

        elem = option.elem;

        return t.get(option.elem);
    };

    t.init = t.render

    // 获取ID对应的编辑器对象
    t.get = function (elem) {

        initTinymce();

        if (elem && /^#|\./.test(elem)) {
            var id = elem.substr(1);
            var edit = tinymce.editors[id];
            return edit
        } else {
            return false;
        }
    }

    //重载
    t.reload = function (elem, option, callback) {

        var options = {}

        if (typeof elem == 'string') {
            option.elem = elem
            options = $.extend({}, option)
        } else if (typeof elem == 'object' && typeof elem.elem == 'string') {
            options = $.extend({}, elem)
            callback = option
        }

        var optionCache = layui.sessionData('layui-tinymce')[options.elem]

        delete optionCache.init_instance_callback

        $.extend(optionCache, options)

        return t.render(optionCache, callback)
    }

    function createNotify(msg, type) {
        if (!elem) {
            return;
        }

        if (type !== 'success' && type !== 'info' && type !== 'warning' && type !== 'error') {
            type = 'info';
        }
        var editor = t.get(elem)
        if (editor) {
            editor.notificationManager.open({
                text: msg,
                type: type,
            });
        }
    }

    function initOptions(option, callback) {

        var form = option.form || {}

        var file_field = form.name || 'file' //文件字段名

        var form_data = form.data || {} //其他表单数据 {key:value, ...}

        option.suffix = isset(option.suffix) ? option.suffix : (plugin_filename.indexOf('.min') > -1 ? '.min' : '')

        option.base_url = isset(option.base_url) ? option.base_url : settings.base_url

        option.language = isset(option.language) ? option.language : settings.language

        option.max_height = isset(option.max_height) ? option.max_height : settings.max_height

        option.height = isset(option.height) ? option.height : settings.height

        option.selector = isset(option.selector) ? option.selector : option.elem

        option.quickbars_selection_toolbar = isset(option.quickbars_selection_toolbar) ? option.quickbars_selection_toolbar : 'cut copy | bold italic underline strikethrough '

        option.plugins = isset(option.plugins) ? option.plugins
            : 'code quickbars print preview searchreplace autolink fullscreen image link media ' +
            'codesample table charmap hr advlist lists wordcount imagetools indent2em importword ' +
            'powerpaste layout letterspacing lineheight upfile attachment';

        option.toolbar = isset(option.toolbar) ? option.toolbar : 'code undo redo importword layout lineheight letterspacing upfile attachment| forecolor backcolor bold italic underline strikethrough | indent2em alignleft aligncenter alignright alignjustify outdent indent | link bullist numlist image table codesample | formatselect fontselect fontsizeselect | image';

        option.resize = isset(option.resize) ? option.resize : false;

        option.elementpath = isset(option.elementpath) ? option.elementpath : false;

        option.branding = isset(option.branding) ? option.branding : false;

        option.paste_data_images = isset(option.paste_data_images) ? option.paste_data_images : false;

        option.paste_merge_formats = isset(option.paste_merge_formats) ? option.paste_merge_formats : false;

        option.powerpaste_word_import = isset(option.powerpaste_word_import) ? option.powerpaste_word_import : settings.powerpaste_word_import;

        option.powerpaste_html_import = isset(option.powerpaste_html_import) ? option.powerpaste_html_import : settings.powerpaste_html_import;

        option.contextmenu_never_use_native = isset(option.contextmenu_never_use_native) ? option.contextmenu_never_use_native : true;

        option.menubar = isset(option.menubar) ? option.menubar : 'file edit insert format table';

        option.menu = isset(option.menu) ? option.menu : {
            file: {title: '文件', items: 'newdocument | print preview fullscreen | wordcount'},
            edit: {title: '编辑', items: 'undo redo | cut copy paste pastetext selectall | searchreplace'},
            format: {
                title: '格式',
                items: 'bold italic underline strikethrough superscript subscript | formats | forecolor backcolor | removeformat'
            },
            table: {title: '表格', items: 'inserttable tableprops deletetable | cell row column'},
        };
        option.init_instance_callback = isset(option.init_instance_callback) ? option.init_instance_callback : function (inst) {
            if (typeof callback == 'function') callback(option, inst)
        };

        option.images_upload_url = isset(option.images_upload_url) ? option.images_upload_url : settings.images_upload_url;

        option.images_upload_handler = isset(option.images_upload_handler) ? option.images_upload_handler : function (blobInfo, succFun, failFun) {

            var formData = new FormData();
            formData.append(file_field, blobInfo.blob());
            if (typeof form_data == 'object') {
                for (var key in form_data) {
                    formData.append(key, form_data[key]);
                }
            }
            doUpload(option.images_upload_url, formData, succFun);
        }

        option.file_picker_callback = isset(option.file_picker_callback) ? option.file_picker_callback : function (succFun, value, meta) { //自定义文件上传函数
            var filetype = '.pdf, .txt, .zip, .rar, .7z, .doc, .docx, .xls, .xlsx, .ppt, .pptx, .mp3, .mp4';
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', filetype);
            input.click();
            input.onchange = function () {
                var file = this.files[0];
                var data = new FormData();
                data.append("file", file);
                doUpload(option.images_upload_url, data, succFun);


            }
        },
            option.file_callback = isset(option.file_callback) ? option.file_callback : function (file, succFun) { //文件上传  file:文件对象 succFun(url|string,obj) 成功回调
                var data = new FormData();
                data.append("file", file);
                doUpload(option.images_upload_url, data, succFun);

            },
            option.attachment_assets_path = isset(option.attachment_assets_path) ? option.attachment_assets_path : settings.base_url + '/plugins/attachment/icons',
            option.attachment_upload_handler = isset(option.attachment_upload_handler) ? option.attachment_upload_handler : function (file, succFun, failFun, progressCallback) {
                var data = new FormData();
                data.append("file", file);
                doUpload(option.images_upload_url, data, succFun);


            }

        layui.sessionData('layui-tinymce', {
            key: option.selector,
            value: option
        })
        return option
    }

    var doUpload = function (url, data, succFun) {
        if (isEmpty(url)) {
            createNotify("上传接口未配置", 'error');
            return console.error('images_upload_url未配置');
        }
        var ajaxOpt = {
            url: url,
            dataType: 'json',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            success: function (res) {
                settings.success(res, succFun)
            },
            error: function (res) {
                createNotify("错误：" + res.msg, 'error');
            }
        };
        if (typeof admin.req == 'function') {
            admin.req(ajaxOpt);
        } else {
            $.ajax(ajaxOpt);
        }
    }

    var xhrOnProgress = function (fun) {
        xhrOnProgress.onprogress = fun;
        return function () {
            var xhr = $.ajaxSettings.xhr();
            if (typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            if (xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }

    function initTinymce() {
        if (typeof tinymce == 'undefined') {
            $.ajax({//获取插件
                url: settings.base_url + '/' + plugin_filename,
                dataType: 'script',
                cache: true,
                async: false,
            });
        }
    }

    function isset(value) {
        return typeof value !== 'undefined' && value !== null
    }

    function isEmpty(value) {
        if (typeof value === 'undefined' || value === null || value === '') {
            return true
        } else if (value instanceof Array && value.length === 0) {
            return true
        } else if (typeof value === 'object' && Object.keys(value).length === 0) {
            return true
        }
        return false
    }

    exports('tinymce', t);

});
