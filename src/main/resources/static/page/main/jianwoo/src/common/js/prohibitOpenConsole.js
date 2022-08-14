/*
禁止打开控制台查看源码，
如果是先打开控制台再打开网页，则debuggerOpen将起作用
prohibitOpenConsole.js
*/

! function () {
    function ProhibitOpenConsole() {}

    ProhibitOpenConsole.prototype.keyF12 = function () {
        document.addEventListener("keydown", function (e) {
            var ev = e || window.event;
            if (ev.keyCode == 123) {
                ev.preventDefault() || (ev.returnValue = false)
            }
        })
    }

    ProhibitOpenConsole.prototype.contextmenu = function () {
        document.addEventListener("contextmenu", function (e) {
            var ev = e || window.event;
            ev.preventDefault() || (ev.returnValue = false)
        })
    }

    ProhibitOpenConsole.prototype.debuggerOpen = function () {
        var timer = setInterval(() => {
            var before = new Date().getTime();
            debugger;
            var after = new Date().getTime();
            if (Math.abs(after - before) > 100) {
                clearInterval(timer)
            }
        }, 1000);
    }


    ProhibitOpenConsole.prototype.init = function () {
        this.keyF12();
        this.contextmenu();
        this.debuggerOpen();
    }

    if (['production', 'prod'].includes(process.env.NODE_ENV)) {
        var check = new ProhibitOpenConsole();
        check.init();
    }
}()