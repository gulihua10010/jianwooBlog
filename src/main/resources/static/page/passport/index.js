layui.extend({
    setter: '../admin/config' //配置文件
    , admin: '../admin/lib/admin' //核心模块
    , view: '../admin/lib/view' //核心模块
    , common: '../admin/controller/common' //通用模块
}).define(['setter', 'admin', 'common'], function (exports) {
    var setter = layui.setter
        , admin = layui.admin
        , view = layui.view

        //入口页面
        , entryPage = function (fn) {
            var router = layui.router()
                , container = view(setter.container)
                , pathURL = admin.correctRouter(router.path.join('/'))
                , path = router.path
                , hash = location.hash
                , isIndPage;

            //检查是否属于独立页面
            layui.each(setter.indPage, function (index, item) {
                if (pathURL === item) {
                    return isIndPage = true;
                }
            });

            //将模块根路径设置为 controller 目录
            layui.config({
                base: setter.base + '../passport/controller/'
            });



            var html = hash;
            if (html.indexOf('?') !== -1)
            {
                html = html.substr(0, html.indexOf('?'))
            }

            if (!html)
            {
                html = 'login';
            }
            html = html.replace("#", "")
            //默认读取主页
            if (!path.length || path.length ===1 && path[0] === '') path = [html];


            container.render(path.join('/')).done(function () {
                admin.pageType = 'alone';
            });

        }

        ;




    //初始主体结构
    layui.link(
        setter.base + '../admin/style/admin.css?v=' + (admin.v + '-1')
        , function () {
            entryPage()
        }
        , 'layuiAdmin'
    );

    //监听Hash改变
    window.onhashchange = function () {
        entryPage();
        //执行 {setter.MOD_NAME}.hash 下的事件
        // var router = {
        //     prevRouter: admin.prevRouter,
        //     currRouter: layui.router()
        // }
        layui.event.call(this, setter.MOD_NAME, 'hash({*})', layui.router());

    };

    //扩展 lib 目录下的其它模块
    layui.each(setter.extend, function (index, item) {
        var mods = {};
        mods[item] = '{/}' + setter.base + '../admin/lib/extend/' + item;
        layui.extend(mods);
    });


    //对外输出
    exports('index', {
    });
});
