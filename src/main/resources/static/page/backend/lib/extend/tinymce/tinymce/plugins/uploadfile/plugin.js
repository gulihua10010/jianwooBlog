/**
 * upfile 1.2v
 * The tinymce-plugins is used to upfile
 *
 * https://github.com/Five-great/tinymce-plugins
 *
 * Copyright 2020, Five(Li Hailong) The Chengdu, China https://www.fivecc.cn/
 *
 * Licensed under MIT
 *
 * modify by gulihua http://jianwoo.cn
 * upfile --> uploadfile
 */
tinymce.PluginManager.add('uploadfile', function (editor, url) {
    var pluginName = '上传文件';
    window.upfile = {}; //扔外部公共变量，也可以扔一个自定义的位置
    var baseURL = tinymce.baseURL || '.';
    var path = editor.getParam("attachment_assets_path") || url + '/icons'
    var iframe1 = url + '/uploadFiles.html';
    upfile.file_callback = editor.getParam('file_callback', undefined, 'function');
    upfile.tinymce = tinymce;
    upfile.res = [];
    var processFileType = function (filename) {
        var t = filename.lastIndexOf(".");
        if (t === -1) return "default_file.svg";
        for (var n = filename.substr(t + 1).toLowerCase(), j = 0, o = [{
            ext: "txt",
            icon: "file_type_text"
        }, {ext: "doc", icon: "file_type_word2"}, {ext: "docx", icon: "file_type_word2"}, {
            ext: "pdf",
            icon: "file_type_pdf2"
        }, {ext: "xls", icon: "file_type_excel2"}, {ext: "xlsx", icon: "file_type_excel2"}, {
            ext: "ppt",
            icon: "file_type_powerpoint2"
        }, {ext: "pptx", icon: "file_type_powerpoint2"}, {ext: "ai", icon: "file_type_ai2"}, {
            ext: "psd",
            icon: "file_type_photoshop2"
        }, {ext: "zip", icon: "file_type_zip"}, {ext: "rar", icon: "file_type_zip"}, {
            ext: "jpg",
            icon: "file_type_image"
        }, {ext: "jpeg", icon: "file_type_image"}, {ext: "png", icon: "file_type_image"}, {
            ext: "gif",
            icon: "file_type_image"
        }, {ext: "js", icon: "file_type_js"}, {ext: "xml", icon: "file_type_xml"}, {
            ext: "htm",
            icon: "file_type_html"
        }, {ext: "html", icon: "file_type_html"}, {ext: "json", icon: "file_type_light_json"}, {
            ext: "fon",
            icon: "file_type_light_font"
        }, {ext: "font", icon: "file_type_light_font"}, {ext: "svg", icon: "file_type_image"}, {
            ext: "exe",
            icon: "file_type_binary"
        }, {ext: "bin", icon: "file_type_binary"}, {ext: "dll", icon: "file_type_binary"}, {
            ext: "pkg",
            icon: "file_type_package"
        }, {ext: "wav", icon: "file_type_audio"}, {ext: "mp3", icon: "file_type_audio"}, {
            ext: "mp4",
            icon: "file_type_video"
        }, {ext: "mov", icon: "file_type_video"}, {ext: "avi", icon: "file_type_video"}, {
            ext: "java",
            icon: "file_type_java"
        }, {ext: "cs", icon: "file_type_csharp"}, {ext: "css", icon: "file_type_css"}, {
            ext: "py",
            icon: "file_type_python"
        }, {ext: "sln", icon: "file_type_vscode3"}, {ext: "go", icon: "file_type_go"}, {
            ext: "7z",
            icon: "file_type_zip"
        }, {ext: "sketch", icon: "file_type_sketch"}, {ext: "patch", icon: "file_type_patch"}, {
            ext: "org",
            icon: "file_type_org"
        }, {ext: "md", icon: "file_type_markdown"}, {ext: "jar", icon: "file_type_jar"}, {
            ext: "dmg",
            icon: "file_type_dmg"
        }, {ext: "accdb", icon: "file_type_access"}, {ext: "apk", icon: "file_type_apk"}, {
            ext: "eps",
            icon: "file_type_eps"
        }, {ext: "ico", icon: "file_type_ico"}, {ext: "iso", icon: "file_type_iso"}, {
            ext: "key",
            icon: "file_type_keynote"
        }, {ext: "numbers", icon: "file_type_number"}, {ext: "reg", icon: "file_type_reg"}, {
            ext: "rp",
            icon: "file_type_rp"
        }, {ext: "rtf", icon: "file_type_rtf"}, {ext: "sketch", icon: "file_type_sketch"}, {
            ext: "swf",
            icon: "file_type_swf"
        }, {ext: "url", icon: "file_type_url"}, {ext: "wma", icon: "file_type_wma"}, {
            ext: "xmind",
            icon: "file_type_xmind"
        }, {ext: "cat", icon: "file_type_cat"}]; j < o.length; j++) {
            var a = o[j];
            if (a.ext === n) return a.icon + ".svg"
        }
        return "default_file.svg";
    }
    var openDialog = function () {
        return editor.windowManager.openUrl({
            title: pluginName,
            size: 'large',
            width: 800,
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
                        doInsertHtml(res);
                        upfile.res = [];
                        api.close();
                        break;
                    default:
                        break;
                }

            }
        });
    };
    var doInsertHtml = function (uploadFiles) {
        if (uploadFiles && uploadFiles.length > 0) {
            var html = '<span class="attachments" contenteditable="false">'
            for (let i = 0; i < uploadFiles.length; i++) {
                html += '<span  class="attachment" contenteditable="false" style="display:inline-block;padding: 5px 10px"><img src="' + path + '/' + processFileType(uploadFiles[i].filename) + '" width="30px"><a href="' + uploadFiles[i].url + '" target="_blank" title="' + uploadFiles[i].text + '" >' + uploadFiles[i].text + "(" + computeSize(uploadFiles[i].size) + ")" + '</a></span><br>';
            }
            html += "</span>"
            editor.insertContent(html);
        }
    }
    var computeSize = function (size, decimal) {
        if (void 0 === decimal && (decimal = 2), 0 === size) return "0 B";
        var n = decimal < 0 ? 0 : decimal, i = Math.floor(Math.log(size) / Math.log(1024));
        return parseFloat((size / Math.pow(1024, i)).toFixed(n)) + " " + ["B", "K", "M", "G", "T", "P", "E", "Z", "Y"][i]
    }
    editor.on("drop", function (e) {
        editor.notificationManager.close();
        e.stopPropagation();
        e.preventDefault();
        if (!e.dataTransfer.files) {
            return false;
        }
        var dropFiles = [];
        var allFileLen = e.dataTransfer.files.length; // 所有的文件的数量，给非Chrome浏览器使用的变量
        if (e.dataTransfer.items !== undefined) {
            // Chrome有items属性，对Chrome的单独处理
            for (var i = 0; i < e.dataTransfer.items.length; i++) {
                var item = e.dataTransfer.items[i];
                // 用webkitGetAsEntry禁止上传目录
                if (item.kind === "file" && item.webkitGetAsEntry().isFile) {
                    var file = item.getAsFile();
                    dropFiles.push(file);
                } else {
                    editor.notificationManager.open({
                        text: "不支持文件夹上传",
                        type: "error",
                    });
                    console.log("不支持文件夹上传")
                }
            }
        } else {
            // 非Chrome拖拽文件逻辑
            for (var i = 0; i < allFileLen; i++) {
                var dropFile = e.dataTransfer.files[i];
                if (dropFile.type) {
                    dropFiles.push(dropFile);
                } else {
                    try {
                        var fileReader = new FileReader();
                        fileReader.readAsDataURL(dropFile.slice(0, 3));

                        fileReader.addEventListener('load', function (e) {
                            console.log(e, 'load');
                            dropFiles.push(dropFile);
                        }, false);

                        fileReader.addEventListener('error', function (e) {
                            editor.notificationManager.open({
                                text: "不支持文件夹上传",
                                type: "error",
                            });
                            console.log("不支持文件夹上传")
                        }, false);

                    } catch (e) {
                        editor.notificationManager.open({
                            text: "不支持文件夹上传",
                            type: "error",
                        });
                        console.log("不支持文件夹上传")

                    }
                }
            }
        }
        // console.log(dropFiles)
        if (!(dropFiles.length > 0)) {
            return false;
        }
        var imageFiles = [];
        var otherFiles = [];
        for (let i = 0; i < dropFiles.length; i++) {
            if (isImage(dropFiles[i].name)) {
                imageFiles.push(dropFiles[i]);
            } else {
                otherFiles.push(dropFiles[i]);
            }

        }
        // console.log(dropFiles)
        // console.log(otherFiles)
        sendUpFile(otherFiles)

        // console.log(upfile)
        // console.log(upfile.res.length)
        // doInsertHtml(upfile)
    })

    function sendUpFile(upfileData) {
        if (upfileData === undefined || upfileData.length === 0)
            return false;
        for (let n = 0; n < upfileData.length; n++) {
            upfileData[n].uuid = guid();
            upfileData[n].isUpload = false;
        }
        // console.log(upfileData)

        initFileHtml(upfileData)
        //
        // editor.notificationManager.open({
        //     text: "正在上传中...",
        //     type: "info",
        // });
        for (let i = 0; i < upfileData.length; i++) {
            upfile.file_callback(upfileData[i], function (url, text) {

                upfileData[i].isUpload = true;
                var progressSpan = editor.dom.select("#file-percent-" + upfileData[i].uuid);
                var fileItem = editor.dom.select("#attachment-" + upfileData[i].uuid);
                editor.dom.setHTML(progressSpan, "(" + 100 + "%)")
                editor.dom.setHTML(fileItem, '<img src="' + path + '/' + processFileType(upfileData[i].name) + '" width="30px"><a href="' + url + '" target="_blank" title="' + text + '" >' + text + "(" + computeSize(upfileData[i].size) + ")" + '</a>')
            }, function (percent) {
                if (percent >= 100) percent = 99;
                if (upfileData[i].isUpload) percent = 100;
                var progressSpan = editor.dom.select("#file-percent-" + upfileData[i].uuid);
                editor.dom.setHTML(progressSpan, "(" + percent + "%)")
            })
        }
        // editor.notificationManager.close();
    }

    function isImage(filename) {
        let imgExt = ['jpeg', 'jpg', 'png', 'gif', 'tif', 'tga', 'bmp', 'svg', 'eps', 'exif', 'pcx', 'fpx'];
        var t = filename.lastIndexOf(".");
        if (t === -1) return  false;
        var ext = filename.substr(t + 1).toLowerCase();
        if (imgExt.indexOf(ext) !== -1) {
            return true;
        }
        return false;

    }

    function initFileHtml(uploadFiles) {
        if (uploadFiles && uploadFiles.length > 0) {
            var html = '<span class="attachments" contenteditable="false">'
            for (let i = 0; i < uploadFiles.length; i++) {
                html += '<span  class="attachment" contenteditable="false" id="attachment-' + uploadFiles[i].uuid + '" style="display:inline-block;padding: 5px 10px"><img src="' + path + '/' + 'loading.gif' + '" width="30px"><span  id="file-url-' + uploadFiles[i].uuid + '"  title="' + uploadFiles[i].name + '" >' + uploadFiles[i].name + "<span id='file-percent-" + uploadFiles[i].uuid + "'>(0.00%)</span>" + '</span></span><br>';
            }
            html += "</span>"
            editor.insertContent(html);
        }
    }

    function guid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    editor.ui.registry.getAll().icons.upfile || editor.ui.registry.addIcon('uploadfile', '<svg t="1616034901576" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1315" width="26" height="26"><path d="M842.512 240.352v-0.192a27.68 27.68 0 0 0-0.176-1.744l-0.032-0.24a27.584 27.584 0 0 0-5.184-12.816l-0.368-0.48a27.936 27.936 0 0 0-1.328-1.6l-0.56-0.592-0.608-0.64-152.48-149.776a27.68 27.68 0 0 0-16.48-8.08h-0.064a27.52 27.52 0 0 0-1.888-0.16h-0.432L662.032 64H203.776C188.432 64 176 76.432 176 91.792v833.76c0 15.36 12.432 27.792 27.776 27.792h611.008c15.344 0 27.776-12.432 27.776-27.792v-683.68a28.64 28.64 0 0 0-0.048-1.52z m-152.704-82.304l57.04 56.032h-57.04V158.048z m97.2 739.712H231.52V119.584h402.72v122.304c0 15.36 12.448 27.792 27.776 27.792h124.96V897.76z m-78.304-384.368H323.84c-15.36 0-27.776 12.432-27.776 27.792 0 15.36 12.432 27.792 27.776 27.792h384.88a27.792 27.792 0 0 0 0-55.584z m-412.656-88.944c0 15.36 12.432 27.808 27.776 27.808h384.88a27.792 27.792 0 0 0 0-55.6H323.84c-15.36 0-27.776 12.432-27.776 27.792z m412.656 205.648H323.84c-15.36 0-27.776 12.432-27.776 27.792 0 15.36 12.432 27.792 27.776 27.792h384.88a27.792 27.792 0 0 0 0-55.584z m0 116.736H323.84a27.776 27.776 0 1 0 0 55.584h384.88a27.792 27.792 0 0 0 0-55.584z" p-id="1316"></path></svg>');

    editor.ui.registry.addButton('uploadfile', {
        icon: 'uploadfile',
        tooltip: pluginName,
        onAction: function () {
            openDialog();
        }
    });
    editor.ui.registry.addMenuItem('uploadfile', {
        icon: 'uploadfile',
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
