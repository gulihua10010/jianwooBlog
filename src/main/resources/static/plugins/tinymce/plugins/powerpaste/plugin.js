/* Ephox PowerPaste plugin
 *
 * Copyright 2010-2016 Ephox Corporation.  All rights reserved.
 *
 * Version: 2.1.10-115
 */

!function () {
    var a = {}, b = function (b) {
        for (var c = a[b], e = c.deps, f = c.defn, g = e.length, h = new Array(g), i = 0; i < g; ++i) h[i] = d(e[i]);
        var j = f.apply(null, h);
        if (void 0 === j) throw"module [" + b + "] returned undefined";
        c.instance = j
    }, c = function (b, c, d) {
        if ("string" != typeof b) throw"module id must be a string";
        if (void 0 === c) throw"no dependencies for " + b;
        if (void 0 === d) throw"no definition function for " + b;
        a[b] = {deps: c, defn: d, instance: void 0}
    }, d = function (c) {
        var d = a[c];
        if (void 0 === d) throw"module [" + c + "] was undefined";
        return void 0 === d.instance && b(c), d.instance
    }, e = function (a, b) {
        for (var c = a.length, e = new Array(c), f = 0; f < c; ++f) e.push(d(a[f]));
        b.apply(null, b)
    }, f = {};
    f.bolt = {module: {api: {define: c, require: e, demand: d}}};
    var g = c, h = function (a, b) {
        g(a, [], function () {
            return b
        })
    };
    h("12", Array), h("13", String), g("g", ["12", "13"], function (a, b) {
        var c = function (a) {
            return function (b) {
                return a === b
            }
        }, d = function () {
            var b = a.prototype.indexOf, d = function (a, c) {
                return b.call(a, c)
            }, e = function (a, b) {
                return r(a, c(b))
            };
            return void 0 === b ? e : d
        }(), e = function (a, b) {
            return d(a, b) > -1
        }, f = function (a, b) {
            return r(a, b) > -1
        }, g = function (a, b) {
            for (var c = [], d = 0; d < a.length; d += b) {
                var e = a.slice(d, d + b);
                c.push(e)
            }
            return c
        }, h = function (b, c) {
            for (var d = b.length, e = new a(d), f = 0; f < d; f++) {
                var g = b[f];
                e[f] = c(g, f, b)
            }
            return e
        }, i = function (a, b) {
            for (var c = 0, d = a.length; c < d; c++) {
                var e = a[c];
                b(e, c, a)
            }
        }, j = function (a, b) {
            for (var c = [], d = [], e = 0, f = a.length; e < f; e++) {
                var g = a[e], h = b(g, e, a) ? c : d;
                h.push(g)
            }
            return {pass: c, fail: d}
        }, k = function (a, b) {
            for (var c = [], d = 0, e = a.length; d < e; d++) {
                var f = a[d];
                b(f, d, a) && c.push(f)
            }
            return c
        }, l = function (a, b) {
            if (0 === a.length) return [];
            for (var c = b(a[0]), d = [], e = [], f = 0, g = a.length; f < g; f++) {
                var h = a[f], i = b(h);
                i !== c && (d.push(e), e = []), c = i, e.push(h)
            }
            return 0 !== e.length && d.push(e), d
        }, m = function (a, b, c) {
            return n(z(a), b, c)
        }, n = function (a, b, c) {
            return i(a, function (a) {
                c = b(c, a)
            }), c
        }, o = function (a, b) {
            for (var c = 0, d = a.length; c < d; c++) {
                var e = a[c];
                if (b(e, c, a)) return e
            }
        }, p = function (a, b, c) {
            var d = o(a, b);
            return void 0 !== d ? d : c
        }, q = function (a, c, d) {
            var e = o(a, c);
            if (void 0 === e) throw d || "Could not find element in array: " + b(a);
            return e
        }, r = function (a, b) {
            for (var c = b || v, d = 0, e = a.length; d < e; ++d) if (c(a[d]) === !0) return d;
            return -1
        }, s = a.prototype.push, t = function (a) {
            for (var b = [], c = 0, d = a.length; c < d; ++c) s.apply(b, a[c]);
            return b
        }, u = function (a, b) {
            var c = h(a, b);
            return t(c)
        }, v = c(!0), w = function (a, b) {
            for (var c = b || v, d = 0, e = a.length; d < e; ++d) if (c(a[d], d) !== !0) return !1;
            return !0
        }, x = function (a, b) {
            return a.length === b.length && w(a, function (a, c) {
                return a === b[c]
            })
        }, y = a.prototype.slice, z = function (a) {
            var b = y.call(a, 0);
            return b.reverse(), b
        }, A = function (a, b) {
            return k(a, function (a) {
                return !e(b, a)
            })
        }, B = function (a, c) {
            for (var d = {}, e = 0, f = a.length; e < f; e++) {
                var g = a[e];
                d[b(g)] = c(g, e)
            }
            return d
        }, C = function (a) {
            return [a]
        };
        return {
            map: h,
            each: i,
            partition: j,
            filter: k,
            groupBy: l,
            indexOf: d,
            foldr: m,
            foldl: n,
            find: o,
            findIndex: r,
            findOr: p,
            findOrDie: q,
            flatten: t,
            bind: u,
            forall: w,
            exists: f,
            contains: e,
            equal: x,
            reverse: z,
            chunk: g,
            difference: A,
            mapToObject: B,
            pure: C
        }
    }), g("p", ["12"], function (a) {
        var b = function () {
        }, c = function (a, b) {
            return function () {
                return a(b.apply(null, arguments))
            }
        }, d = function (a) {
            return function () {
                return a
            }
        }, e = function (a) {
            return a
        }, f = function (a, b) {
            return a === b
        }, g = function (b) {
            for (var c = new a(arguments.length - 1), d = 1; d < arguments.length; d++) c[d - 1] = arguments[d];
            return function () {
                for (var d = new a(arguments.length), e = 0; e < d.length; e++) d[e] = arguments[e];
                var f = c.concat(d);
                return b.apply(null, f)
            }
        }, h = function (a) {
            return function () {
                return !a.apply(null, arguments)
            }
        }, i = function (a) {
            return function () {
                throw a
            }
        }, j = function (a) {
            return a()
        }, k = function (a) {
            a()
        };
        return {
            noop: b,
            compose: c,
            constant: d,
            identity: e,
            tripleEquals: f,
            curry: g,
            not: h,
            die: i,
            apply: j,
            call: k
        }
    }), h("14", Object), g("n", ["p", "14"], function (a, b) {
        var c = a.constant(!1), d = a.constant(!0), e = function () {
            return f
        }, f = function () {
            var f = function (a) {
                return a.isNone()
            }, g = function (a) {
                return a()
            }, h = function (a) {
                return a
            }, i = {
                fold: function (a, b) {
                    return a()
                },
                is: c,
                isSome: c,
                isNone: d,
                getOr: h,
                getOrThunk: g,
                getOrDie: function (a) {
                    throw a || "error: getOrDie called on none."
                },
                or: h,
                orThunk: g,
                map: e,
                ap: e,
                each: e,
                bind: e,
                flatten: e,
                exists: c,
                forall: d,
                filter: e,
                equals: f,
                equals_: f,
                toArray: function () {
                    return []
                },
                toString: a.constant("none()")
            };
            return b.freeze && b.freeze(i), i
        }(), g = function (a) {
            var b = function () {
                return a
            }, h = function () {
                return k
            }, i = function (b) {
                return g(b(a))
            }, j = function (b) {
                return b(a)
            }, k = {
                fold: function (b, c) {
                    return c(a)
                },
                is: function (b) {
                    return a === b
                },
                isSome: d,
                isNone: c,
                getOr: b,
                getOrThunk: b,
                getOrDie: b,
                or: h,
                orThunk: h,
                map: i,
                ap: function (b) {
                    return b.fold(e, function (b) {
                        return g(b(a))
                    })
                },
                each: i,
                bind: j,
                flatten: b,
                exists: j,
                forall: j,
                filter: function (b) {
                    return b(a) ? k : f
                },
                equals: function (b) {
                    return b.is(a)
                },
                equals_: function (b, d) {
                    return b.fold(c, function (b) {
                        return d(a, b)
                    })
                },
                toArray: function () {
                    return [a]
                },
                toString: function () {
                    return "some(" + a + ")"
                }
            };
            return k
        }, h = function (a) {
            return null === a || void 0 === a ? f : g(a)
        }, i = function (a, b) {
            return a.equals(b)
        }, j = function (a, b, c) {
            return a.equals_(b, c)
        };
        return {some: g, none: e, from: h, equals: i, equals_: j}
    }), g("c", ["g", "p"], function (a, b) {
        return function (c) {
            var d = function () {
                c.uploadImages()
            }, e = function (d) {
                a.each(d, function (d) {
                    d.fold(function (b, d, e, f) {
                        a.each(c.dom.select('img[src="' + e + '"]'), function (a) {
                            c.dom.setAttrib(a, "src", f.result)
                        })
                    }, b.noop)
                })
            }, f = function (a, b, c, d) {
                return d.result
            };
            return {uploadImages: d, prepareImages: e, getLocalURL: f}
        }
    }), g("y", [], function () {
        return function (a) {
            var b = !1;
            return function () {
                b || (b = !0, a.apply(null, arguments))
            }
        }
    }), h("2", tinymce), g("d", ["2"], function (a) {
        var b = function () {
            return "Your browser security settings may be preventing images from being imported."
        }, c = function () {
            return a.Env.mac && a.Env.webkit ? b() + ' <a href="https://support.ephox.com/entries/59328357-Safari-6-1-and-7-Flash-Sandboxing" style="text-decoration: underline">More information on paste for Safari</a>' : b()
        }, d = function () {
            return 'Safari does not support direct paste of images. <a href="https://support.ephox.com/entries/88543243-Safari-Direct-paste-of-images-does-not-work" style="text-decoration: underline">More information on image pasting for Safari</a>'
        }, e = {
            "cement.dialog.paste.title": "Paste Formatting Options",
            "cement.dialog.paste.instructions": "Choose to keep or remove formatting in the pasted content.",
            "cement.dialog.paste.merge": "Keep Formatting",
            "cement.dialog.paste.clean": "Remove Formatting",
            "cement.dialog.flash.title": "Additional step needed to paste images",
            "cement.dialog.flash.trigger-paste": "Your browser requires you to take one more action to paste the images in your content. Please press the below keys to complete the image paste:",
            "cement.dialog.flash.missing": 'Adobe Flash is required to import images from Microsoft Office. Install the <a href="http://get.adobe.com/flashplayer/" target="_blank">Adobe Flash Player</a>.',
            "cement.dialog.flash.press-escape": 'Press <span class="ephox-polish-help-kbd">"Close"</span> to paste your content without images.',
            "loading.wait": "Please wait...",
            "flash.clipboard.no.rtf": c(),
            "safari.imagepaste": d(),
            "webview.imagepaste": d(),
            "error.code.images.not.found": "The images service was not found: (",
            "error.imageupload": "Image failed to upload: (",
            "error.full.stop": ").",
            "errors.local.images.disallowed": "Local image paste has been disabled. Local images have been removed from pasted content.",
            "flash.crashed": "Images have not been imported as Adobe Flash appears to have crashed. This may be caused by pasting large documents.",
            "errors.imageimport.failed": "Some images failed to import.",
            "errors.imageimport.unsupported": "Unsupported image type.",
            "errors.imageimport.invalid": "Image is invalid."
        }, f = function (a) {
            return e[a]
        }, g = function (b) {
            return a.translate(f(b))
        };
        return {translate: g}
    }), g("s", [], function () {
        return {
            showDialog: function (a, b) {
                var c = function () {
                        win.close()
                    }, d = [{text: "Ok", onclick: c}],
                    e = {title: "Error", spacing: 10, padding: 10, items: [{type: "container", html: b}], buttons: d};
                win = a.windowManager.open(e)
            }
        }
    }), g("15", ["y", "d", "s"], function (a, b, c) {
        return function (d, e) {
            var f = function () {
                return b.translate("error.code.images.not.found") + e + b.translate("error.full.stop")
            }, g = function () {
                return b.translate("error.imageupload") + e + b.translate("error.full.stop")
            }, h = function (a) {
                var b = a.status(), e = 0 === b || b >= 400 || b < 500, h = e ? f : g;
                c.showDialog(d, h())
            }, i = function () {
                return a(h)
            };
            return {instance: i}
        }
    }), g("3g", ["g"], function (a) {
        var b = function (b) {
            var e = c(b), f = function (b) {
                var c = b.split(" "), f = a.map(c, function (a) {
                    return d(e, a)
                });
                return f.join(" ")
            };
            return {resolve: f}
        }, c = function (a) {
            return a.replace(/\./g, "-")
        }, d = function (a, b) {
            return a + "-" + b
        };
        return {create: b, cssNamespace: c, cssClass: d}
    }), g("2d", ["3g"], function (a) {
        var b = a.create("ephox-salmon");
        return {resolve: b.resolve}
    }), g("26", ["p", "2d"], function (a, b) {
        var c = b.resolve("upload-image-in-progress"), d = "data-" + b.resolve("image-blob");
        return {uploadInProgress: a.constant(c), blobId: a.constant(d)}
    }), g("3h", [], function () {
        return function (a, b, c) {
            var d = c || !1, e = function () {
                b(), d = !0
            }, f = function () {
                a(), d = !1
            }, g = function () {
                var a = d ? f : e;
                a()
            }, h = function () {
                return d
            };
            return {on: e, off: f, toggle: g, isOn: h}
        }
    }), g("1b", ["12", "13"], function (a, b) {
        var c = function (c) {
            if (null === c) return "null";
            var d = typeof c;
            return "object" === d && a.prototype.isPrototypeOf(c) ? "array" : "object" === d && b.prototype.isPrototypeOf(c) ? "string" : d
        }, d = function (a) {
            return function (b) {
                return c(b) === a
            }
        };
        return {
            isString: d("string"),
            isObject: d("object"),
            isArray: d("array"),
            isNull: d("null"),
            isBoolean: d("boolean"),
            isUndefined: d("undefined"),
            isFunction: d("function"),
            isNumber: d("number")
        }
    }), g("1c", ["14"], function (a) {
        var b = function () {
            var b = a.keys, c = function (a) {
                var b = [];
                for (var c in a) a.hasOwnProperty(c) && b.push(c);
                return b
            };
            return void 0 === b ? c : b
        }(), c = function (a, c) {
            for (var d = b(a), e = 0, f = d.length; e < f; e++) {
                var g = d[e], h = a[g];
                c(h, g, a)
            }
        }, d = function (a, b) {
            return e(a, function (a, c, d) {
                return {k: c, v: b(a, c, d)}
            })
        }, e = function (a, b) {
            var d = {};
            return c(a, function (c, e) {
                var f = b(c, e, a);
                d[f.k] = f.v
            }), d
        }, f = function (a, b) {
            var d = {}, e = {};
            return c(a, function (a, c) {
                var f = b(a, c) ? d : e;
                f[c] = a
            }), {t: d, f: e}
        }, g = function (a, b) {
            var d = [];
            return c(a, function (a, c) {
                d.push(b(a, c))
            }), d
        }, h = function (a, c) {
            for (var d = b(a), e = 0, f = d.length; e < f; e++) {
                var g = d[e], h = a[g];
                if (c(h, g, a)) return h
            }
        }, i = function (a) {
            return g(a, function (a) {
                return a
            })
        }, j = function (a) {
            return i(a).length
        };
        return {bifilter: f, each: c, map: d, mapToArray: g, tupleMap: e, find: h, keys: b, values: i, size: j}
    }), g("2e", [], function () {
        return {
            ATTRIBUTE: 2,
            CDATA_SECTION: 4,
            COMMENT: 8,
            DOCUMENT: 9,
            DOCUMENT_TYPE: 10,
            DOCUMENT_FRAGMENT: 11,
            ELEMENT: 1,
            TEXT: 3,
            PROCESSING_INSTRUCTION: 7,
            ENTITY_REFERENCE: 5,
            ENTITY: 6,
            NOTATION: 12
        }
    }), g("1d", ["2e"], function (a) {
        var b = function (a) {
            var b = a.dom().nodeName;
            return b.toLowerCase()
        }, c = function (a) {
            return a.dom().nodeType
        }, d = function (a) {
            return a.dom().nodeValue
        }, e = function (a) {
            return function (b) {
                return c(b) === a
            }
        }, f = function (d) {
            return c(d) === a.COMMENT || "#comment" === b(d)
        }, g = e(a.ELEMENT), h = e(a.TEXT), i = e(a.DOCUMENT);
        return {name: b, type: c, value: d, isElement: g, isText: h, isDocument: i, isComment: f}
    }), h("1e", Error), g("1f", [], function () {
        return "undefined" == typeof console && (console = {
            log: function () {
            }
        }), console
    }), g("j", ["1b", "g", "1c", "1d", "1e", "1f"], function (a, b, c, d, e, f) {
        var g = function (b, c, d) {
            if (!(a.isString(d) || a.isBoolean(d) || a.isNumber(d))) throw f.error("Invalid call to Attr.set. Key ", c, ":: Value ", d, ":: Element ", b), new e("Attribute value was not simple");
            b.setAttribute(c, d + "")
        }, h = function (a, b, c) {
            g(a.dom(), b, c)
        }, i = function (a, b) {
            var d = a.dom();
            c.each(b, function (a, b) {
                g(d, b, a)
            })
        }, j = function (a, b) {
            var c = a.dom().getAttribute(b);
            return null === c ? void 0 : c
        }, k = function (a, b) {
            var c = a.dom();
            return !(!c || !c.hasAttribute) && c.hasAttribute(b)
        }, l = function (a, b) {
            a.dom().removeAttribute(b)
        }, m = function (a) {
            var b = a.dom().attributes;
            return void 0 === b || null === b || 0 === b.length
        }, n = function (a) {
            return b.foldl(a.dom().attributes, function (a, b) {
                return a[b.name] = b.value, a
            }, {})
        }, o = function (a, b, c) {
            k(a, c) && !k(b, c) && h(b, c, j(a, c))
        }, p = function (a, c, e) {
            d.isElement(a) && d.isElement(c) && b.each(e, function (b) {
                o(a, c, b)
            })
        };
        return {clone: n, set: h, setAll: i, get: j, has: k, remove: l, hasNone: m, transfer: p}
    }), g("5z", ["g", "j"], function (a, b) {
        var c = function (a, c) {
            var d = b.get(a, c);
            return void 0 === d || "" === d ? [] : d.split(" ")
        }, d = function (a, d, e) {
            var f = c(a, d), g = f.concat([e]);
            b.set(a, d, g.join(" "))
        }, e = function (d, e, f) {
            var g = a.filter(c(d, e), function (a) {
                return a !== f
            });
            g.length > 0 ? b.set(d, e, g.join(" ")) : b.remove(d, e)
        };
        return {read: c, add: d, remove: e}
    }), g("3i", ["g", "5z"], function (a, b) {
        var c = function (a) {
            return void 0 !== a.dom().classList
        }, d = function (a) {
            return b.read(a, "class")
        }, e = function (a, c) {
            return b.add(a, "class", c)
        }, f = function (a, c) {
            return b.remove(a, "class", c)
        }, g = function (b, c) {
            a.contains(d(b), c) ? f(b, c) : e(b, c)
        };
        return {get: d, add: e, remove: f, toggle: g, supports: c}
    }), g("27", ["3h", "j", "3i"], function (a, b, c) {
        var d = function (a, b) {
            c.supports(a) ? a.dom().classList.add(b) : c.add(a, b)
        }, e = function (a) {
            var d = c.supports(a) ? a.dom().classList : c.get(a);
            0 === d.length && b.remove(a, "class")
        }, f = function (a, b) {
            if (c.supports(a)) {
                var d = a.dom().classList;
                d.remove(b)
            } else c.remove(a, b);
            e(a)
        }, g = function (a, b) {
            return c.supports(a) ? a.dom().classList.toggle(b) : c.toggle(a, b)
        }, h = function (b, d) {
            var e = c.supports(b), f = b.dom().classList, g = function () {
                e ? f.remove(d) : c.remove(b, d)
            }, h = function () {
                e ? f.add(d) : c.add(b, d)
            };
            return a(g, h, i(b, d))
        }, i = function (a, b) {
            return c.supports(a) && a.dom().classList.contains(b)
        };
        return {add: d, remove: f, toggle: g, toggler: h, has: i}
    }), h("1g", document), g("k", ["p", "1e", "1f", "1g"], function (a, b, c, d) {
        var e = function (a, b) {
            var e = b || d, f = e.createElement("div");
            if (f.innerHTML = a, !f.hasChildNodes() || f.childNodes.length > 1) throw c.error("HTML does not have a single root node", a), "HTML must have a single root node";
            return h(f.childNodes[0])
        }, f = function (a, b) {
            var c = b || d, e = c.createElement(a);
            return h(e)
        }, g = function (a, b) {
            var c = b || d, e = c.createTextNode(a);
            return h(e)
        }, h = function (c) {
            if (null === c || void 0 === c) throw new b("Node cannot be null or undefined");
            return {dom: a.constant(c)}
        };
        return {fromHtml: e, fromTag: f, fromText: g, fromDom: h}
    }), g("3k", ["g", "1c", "p", "12"], function (a, b, c, d) {
        var e = function (e, f) {
            var g = function () {
                for (var b = new d(arguments.length), f = 0; f < b.length; f++) b[f] = arguments[f];
                if (e.length !== b.length) throw'Wrong number of arguments to struct. Expected "[' + e.length + ']", got ' + b.length + " arguments";
                var g = {};
                return a.each(e, function (a, d) {
                    g[a] = c.constant(b[d])
                }), g
            }, h = function (a, b) {
                for (var d = 0; d < e.length; d++) {
                    var g = f && f[d] || c.tripleEquals, h = e[d];
                    if (!g(a[h](), b[h]())) return !1
                }
                return !0
            }, i = function (a) {
                return b.map(a, function (a) {
                    return a()
                })
            };
            return {nu: g, eq: h, evaluate: i}
        };
        return {product: e}
    }), g("3j", ["3k"], function (a) {
        return function () {
            return a.product(arguments).nu
        }
    }), g("60", ["1b", "g"], function (a, b) {
        var c = function (a) {
            return a.slice(0).sort()
        }, d = function (a, b) {
            throw"All required keys (" + c(a).join(", ") + ") were not specified. Specified keys were: " + c(b).join(", ") + "."
        }, e = function (a) {
            throw"Unsupported keys for object: " + c(a).join(", ")
        }, f = function (c, d) {
            if (!a.isArray(d)) throw"The " + c + " fields must be an array. Was: " + d + ".";
            b.each(d, function (b) {
                if (!a.isString(b)) throw"The value " + b + " in the " + c + " fields was not a string."
            })
        }, g = function (a, b) {
            throw"All values need to be of type: " + b + ". Keys (" + c(a).join(", ") + ") were not."
        }, h = function (a) {
            var d = c(a), e = b.find(d, function (a, b) {
                return b < d.length - 1 && a === d[b + 1]
            });
            if (void 0 !== e && null !== e) throw"The field: " + e + " occurs more than once in the combined fields: [" + d.join(", ") + "]."
        };
        return {sort: c, reqMessage: d, unsuppMessage: e, validateStrArr: f, invalidTypeMessage: g, checkDupes: h}
    }), g("3l", ["g", "1c", "p", "n", "60", "14"], function (a, b, c, d, e, f) {
        return function (g, h) {
            var i = g.concat(h);
            if (0 === i.length) throw"You must specify at least one required or optional field.";
            return e.validateStrArr("required", g), e.validateStrArr("optional", h), e.checkDupes(i), function (j) {
                var k = b.keys(j), l = a.forall(g, function (b) {
                    return a.contains(k, b)
                });
                l || e.reqMessage(g, k);
                var m = a.filter(k, function (b) {
                    return !a.contains(i, b)
                });
                m.length > 0 && e.unsuppMessage(m);
                var n = {};
                return a.each(g, function (a) {
                    n[a] = c.constant(j[a])
                }), a.each(h, function (a) {
                    n[a] = c.constant(f.prototype.hasOwnProperty.call(j, a) ? d.some(j[a]) : d.none())
                }), n
            }
        }
    }), g("2c", ["3j", "3k", "3l"], function (a, b, c) {
        return {immutable: a, immutable2: b, immutableBag: c}
    }), g("3m", [], function () {
        var a = function (a, b) {
            var c = [], d = function (a) {
                return c.push(a), b(a)
            }, e = b(a);
            do e = e.bind(d); while (e.isSome());
            return c
        };
        return {toArray: a}
    }), g("4j", ["p"], function (a) {
        return function (b, c, d) {
            var e = b.isiOS() && /ipad/i.test(d) === !0, f = b.isiOS() && !e,
                g = b.isAndroid() && 3 === b.version.major, h = b.isAndroid() && 4 === b.version.major,
                i = e || g || h && /mobile/i.test(d) === !0, j = b.isiOS() || b.isAndroid(), k = j && !i,
                l = c.isSafari() && b.isiOS() && /safari/i.test(d) === !1;
            return {
                isiPad: a.constant(e),
                isiPhone: a.constant(f),
                isTablet: a.constant(i),
                isPhone: a.constant(k),
                isTouch: a.constant(j),
                isAndroid: b.isAndroid,
                isiOS: b.isiOS,
                isWebView: a.constant(l)
            }
        }
    }), g("4k", [], function () {
        var a = function (a, b, c) {
            return {browser: {current: a, version: b}, os: {current: c}}
        };
        return {create: a}
    }), g("61", [], function () {
        var a = function (a) {
            return function () {
                return a
            }
        }, b = function (b, c, d) {
            for (var e = 0; e < d.length; e++) b["is" + d[e].name] = a(d[e].name === c)
        };
        return {getter: a, attachGetters: b}
    }), g("4l", ["61"], function (a) {
        var b = function (b, c, d) {
            var e = a.attachGetters, f = {};
            return f.current = c, f.version = d, e(f, f.current, b), f
        };
        return {create: b}
    }), h("62", Math), h("63", isFinite), h("64", isNaN), h("65", parseFloat), g("3s", ["62", "63", "64", "65"], function (a, b, c, d) {
        var e = function (a) {
            return function (b, c) {
                var d = typeof c;
                if (d !== a) throw b + " was not a " + a + ". Was: " + c + " (" + d + ")"
            }
        }, f = e("string"), g = function (a, b) {
            f(a, b);
            var c = b.length;
            if (1 !== c) throw a + " was not a single char. Was: " + b
        }, h = e("number"), i = function (b, c) {
            if (h(b, c), c !== a.abs(c)) throw b + " was not an integer. Was: " + c
        }, j = function (a) {
            return !c(d(a)) && b(a)
        }, k = function (a, b) {
            if (i(a, b), b < 0) throw a + " was not a natural number. Was: " + b
        };
        return {vString: f, vChar: g, vInt: i, vNat: k, pNum: j}
    }), g("37", ["3s"], function (a) {
        var b = function (a, b, c) {
            if ("" === b) return !0;
            if (a.length < b.length) return !1;
            var d = a.substr(c, c + b.length);
            return d === b
        }, c = function (a, b) {
            var c = function (a) {
                var b = typeof a;
                return "string" === b || "number" === b
            };
            return a.replace(/\${([^{}]*)}/g, function (a, d) {
                var e = b[d];
                return c(e) ? e : a
            })
        }, d = function (a) {
            var b = function (a, b) {
                for (var c = [], d = 0; d < a.length; d++) c.push(b(a[d]));
                return c
            };
            return function () {
                var c = b(arguments, function (a) {
                    return "string" == typeof a ? a.toLowerCase() : a
                });
                return a.apply(this, c)
            }
        }, e = function (a, c) {
            return b(a, c, 0)
        }, f = d(e), g = function (a, c) {
            return b(a, c, a.length - c.length)
        }, h = d(g), i = function (a, b) {
            return a.substr(0, b)
        }, j = function (a, b) {
            return a.substr(a.length - b, a.length)
        }, k = function (a, b) {
            return function (c, d) {
                return a(c, d) ? b(c, c.length - d.length) : c
            }
        }, l = k(e, j), m = k(g, i), n = function (a, b) {
            return a + b
        }, o = function (a, b) {
            return b + a
        }, p = function (a, b) {
            return function (c, d) {
                return a(c, d) ? c : b(c, d)
            }
        }, q = p(e, o), r = p(g, n), s = function (a) {
            return a.replace(/^\s+|\s+$/g, "")
        }, t = function (a) {
            return a.replace(/^\s+/g, "")
        }, u = function (a) {
            return a.replace(/\s+$/g, "")
        }, v = function (a, b) {
            return a.indexOf(b) != -1
        }, w = d(v), x = function (a) {
            return a.replace(/\"/gm, "&quot;")
        }, y = function (a, b) {
            return a === b
        }, z = d(y), A = function (a) {
            if ("" === a) throw"head on empty string";
            return a.substr(0, 1)
        }, B = function (a) {
            if ("" === a) throw"toe on empty string";
            return a.substr(a.length - 1, a.length)
        }, C = function (a) {
            if ("" === a) throw"tail on empty string";
            return a.substr(1, a.length - 1)
        }, D = function (a) {
            if ("" === a) throw"torso on empty string";
            return a.substr(0, a.length - 1)
        }, E = function (a) {
            return "" === a ? a : A(a).toUpperCase() + C(a)
        }, F = function (b, c) {
            a.vString("str", b), a.vNat("num", c);
            for (var d = "", e = 0; e < c; e++) d += b;
            return d
        }, G = function (b) {
            return function (c, d, e) {
                a.vString("str", c), a.vChar("c", d), a.vNat("width", e);
                var f = c.length;
                return f >= e ? c : b(c, F(d, e - f))
            }
        }, H = G(function (a, b) {
            return b + a
        }), I = G(function (a, b) {
            return a + b
        });
        return {
            supplant: c,
            startsWith: e,
            startsWithIgnoringCase: f,
            endsWith: g,
            endsWithIgnoringCase: h,
            first: i,
            last: j,
            removeLeading: l,
            removeTrailing: m,
            ensureLeading: q,
            ensureTrailing: r,
            trim: s,
            lTrim: t,
            rTrim: u,
            contains: v,
            containsIgnoringCase: w,
            htmlEncodeDoubleQuotes: x,
            equals: y,
            equalsIgnoringCase: z,
            head: A,
            repead: F,
            padLeft: H,
            padRight: I,
            toe: B,
            tail: C,
            torso: D,
            capitalize: E
        }
    }), g("4m", ["37"], function (a) {
        var b = a.contains, c = function (a) {
            return function (c) {
                return b(c, a)
            }
        }, d = function () {
            try {
                var a = new ActiveXObject("ChromeTab.ChromeFrame");
                return !!a
            } catch (b) {
                return !1
            }
        }, e = /.*?version\/\ ?([0-9]+)\.([0-9]+).*/, f = function (a) {
            var d = [{
                name: "Spartan", versionRegexes: [/.*?edge\/ ?([0-9]+)\.([0-9]+)$/], search: function (a) {
                    var c = b(a, "edge/") && b(a, "chrome") && b(a, "safari") && b(a, "applewebkit");
                    return c
                }
            }, {
                name: "ChromeFrame", versionRegexes: [/.*?chromeframe\/([0-9]+)\.([0-9]+).*/, e], search: function (c) {
                    return !!b(c, "chromeframe") && a()
                }
            }, {
                name: "Chrome", versionRegexes: [/.*?chrome\/([0-9]+)\.([0-9]+).*/, e], search: function (a) {
                    return b(a, "chrome") && !b(a, "chromeframe")
                }
            }, {
                name: "IE",
                versionRegexes: [/.*?msie\ ?([0-9]+)\.([0-9]+).*/, /.*?rv:([0-9]+)\.([0-9]+).*/],
                search: function (c) {
                    var d = b(c, "msie") || b(c, "trident"), e = b(c, "chromeframe");
                    return e ? d && !a() : d
                }
            }, {
                name: "Opera",
                versionRegexes: [e, /.*?opera\/([0-9]+)\.([0-9]+).*/],
                search: c("opera")
            }, {
                name: "Firefox",
                versionRegexes: [/.*?firefox\/\ ?([0-9]+)\.([0-9]+).*/],
                search: c("firefox")
            }, {
                name: "Safari", versionRegexes: [e, /.*?cpu os ([0-9]+)_([0-9]+).*/], search: function (a) {
                    return (b(a, "safari") || b(a, "mobile/")) && b(a, "applewebkit")
                }
            }, {name: "Envjs", versionRegexes: [/.*?envjs\/\ ?([0-9]+)\.([0-9]+).*/], search: c("envjs")}], f = [{
                name: "Windows",
                search: c("win"),
                versionRegexes: [/.*?windows\ nt\ ?([0-9]+)\.([0-9]+).*/]
            }, {
                name: "iOS",
                search: function (a) {
                    return b(a, "iphone") || b(a, "ipad")
                },
                versionRegexes: [/.*?version\/\ ?([0-9]+)\.([0-9]+).*/, /.*cpu os ([0-9]+)_([0-9]+).*/, /.*cpu iphone os ([0-9]+)_([0-9]+).*/]
            }, {
                name: "Android",
                search: c("android"),
                versionRegexes: [/.*?android\ ?([0-9]+)\.([0-9]+).*/]
            }, {name: "OSX", search: c("os x"), versionRegexes: [/.*?os\ x\ ?([0-9]+)_([0-9]+).*/]}, {
                name: "Linux",
                search: c("linux")
            }, {name: "Solaris", search: c("sunos")}, {name: "FreeBSD", search: c("freebsd")}];
            return {browsers: d, oses: f}
        };
        return {create: f, chromeFrameChecker: d}
    }), g("4n", [], function () {
        var a = function (a, b) {
            var c = typeof a;
            if ("boolean" === c) return !!a;
            if ("object" === c) {
                var d = a.minimum;
                return b.major > d.major || b.major === d.major && b.minor >= d.minor
            }
            throw"invalid spec"
        };
        return {meetsSpec: a}
    }), g("66", [], function () {
        var a = function (a, b, c) {
            for (var d = 0; d < a.length; d++) {
                var e = a[d];
                if (c(e, d, a)) return e
            }
            return b
        };
        return {findOneInArrayOr: a}
    }), g("4o", ["66"], function (a) {
        var b = function (b, c) {
            var d = a.findOneInArrayOr, e = String(c).toLowerCase();
            return d(b, {name: void 0}, function (a) {
                return a.search(e)
            })
        };
        return {detect: b}
    }), g("4p", [], function () {
        var a = function (a, b) {
            function c(a, b) {
                for (var c = 0; c < a.length; c++) {
                    var d = a[c];
                    if (d.test(b)) return d
                }
            }

            function d(a, b) {
                var d = c(a, b);
                if (!d) return {major: 0, minor: 0};
                var e = function (a) {
                    return Number(b.replace(d, "$" + a))
                };
                return {major: e(1), minor: e(2)}
            }

            var e = String(b).toLowerCase(), f = a.versionRegexes;
            return f ? d(f, e) : {major: 0, minor: 0}
        };
        return {detectVersion: a}
    }), g("2t", ["4j", "4k", "4l", "4m", "4n", "4o", "4p"], function (a, b, c, d, e, f, g) {
        var h = g.detectVersion, i = c.create, j = e.meetsSpec, k = f.detect, l = function (a, b, c, d) {
            return !!a[b] && (a[b][c] ? j(a[b][c], d) : !!a[b].All)
        }, m = function (a, b) {
            var c = b.browser, d = b.os;
            return l(a, d.current, c.current, c.version)
        }, n = function (b, c) {
            var e = d.create(c), f = e.browsers, g = e.oses, j = k(g, b), m = j.name, n = h(j, b), o = k(f, b),
                p = o.name, q = h(o, b), r = i(g, m, n), s = i(f, p, q), t = a(r, s, b), u = function (a) {
                    return l(a, m, p, q)
                };
            return {browser: s, os: r, deviceType: t, isSupported: u}
        }, o = function () {
            return n(navigator.userAgent, d.chromeFrameChecker)
        };
        return {Platform: b, detect: o, doDetect: n, isSupported: l, isSupportedPlatform: m}
    }), g("72", [], function () {
        return Function("return this;")()
    }), g("68", ["72"], function (a) {
        var b = function (b, c) {
            for (var d = c || a, e = 0; e < b.length && void 0 !== d && null !== d; ++e) d = d[b[e]];
            return d
        }, c = function (a, c) {
            var d = a.split(".");
            return b(d, c)
        };
        return {path: b, resolve: c}
    }), g("3p", ["68"], function (a) {
        var b = function (b, c) {
            return a.resolve(b, c)
        }, c = function (a, c) {
            var d = b(a, c);
            if (void 0 === d) throw a + " not available on this browser";
            return d
        };
        return {getOrDie: c}
    }), g("67", ["3p"], function (a) {
        var b = function () {
            var b = a.getOrDie("Node");
            return b
        }, c = function (a, b, c) {
            return 0 !== (a.compareDocumentPosition(b) & c)
        }, d = function (a, d) {
            return c(a, d, b().DOCUMENT_POSITION_PRECEDING)
        }, e = function (a, d) {
            return c(a, d, b().DOCUMENT_POSITION_CONTAINED_BY)
        };
        return {documentPositionPreceding: d, documentPositionContainedBy: e}
    }), g("2h", ["2e", "g", "n", "k", "1e", "1g"], function (a, b, c, d, e, f) {
        var g = 0, h = 1, i = 2, j = 3, k = function () {
            var a = f.createElement("span");
            return void 0 !== a.matches ? g : void 0 !== a.msMatchesSelector ? h : void 0 !== a.webkitMatchesSelector ? i : void 0 !== a.mozMatchesSelector ? j : -1
        }(), l = a.ELEMENT, m = a.DOCUMENT, n = function (a, b) {
            var c = a.dom();
            if (c.nodeType !== l) return !1;
            if (k === g) return c.matches(b);
            if (k === h) return c.msMatchesSelector(b);
            if (k === i) return c.webkitMatchesSelector(b);
            if (k === j) return c.mozMatchesSelector(b);
            throw new e("Browser lacks native selectors")
        }, o = function (a) {
            return a.nodeType !== l && a.nodeType !== m || 0 === a.childElementCount
        }, p = function (a, c) {
            var e = void 0 === c ? f : c.dom();
            return o(e) ? [] : b.map(e.querySelectorAll(a), d.fromDom)
        }, q = function (a, b) {
            var e = void 0 === b ? f : b.dom();
            return o(e) ? c.none() : c.from(e.querySelector(a)).map(d.fromDom)
        };
        return {all: p, is: n, one: q}
    }), g("3n", ["g", "2t", "67", "p", "2h"], function (a, b, c, d, e) {
        var f = function (a, b) {
            return a.dom() === b.dom()
        }, g = function (a, b) {
            return a.dom().isEqualNode(b.dom())
        }, h = function (b, c) {
            return a.exists(c, d.curry(f, b))
        }, i = function (a, b) {
            var c = a.dom(), d = b.dom();
            return c !== d && c.contains(d)
        }, j = function (a, b) {
            return c.documentPositionContainedBy(a.dom(), b.dom())
        }, k = b.detect().browser, l = k.isIE() ? j : i;
        return {eq: f, isEqualNode: g, member: h, contains: l, is: e.is}
    }), g("3f", ["1b", "g", "p", "n", "2c", "3m", "3n", "k"], function (a, b, c, d, e, f, g, h) {
        var i = function (a) {
            return h.fromDom(a.dom().ownerDocument)
        }, j = function (a) {
            var b = i(a);
            return h.fromDom(b.dom().documentElement)
        }, k = function (a) {
            var b = a.dom(), c = b.ownerDocument.defaultView;
            return h.fromDom(c)
        }, l = function (a) {
            var b = a.dom();
            return d.from(b.parentNode).map(h.fromDom)
        }, m = function (a) {
            return l(a).bind(function (c) {
                var e = u(c), f = b.findIndex(e, function (b) {
                    return g.eq(a, b)
                });
                return f > -1 ? d.some(f) : d.none()
            })
        }, n = function (b, d) {
            for (var e = a.isFunction(d) ? d : c.constant(!1), f = b.dom(), g = []; null !== f.parentNode && void 0 !== f.parentNode;) {
                var i = f.parentNode, j = h.fromDom(i);
                if (g.push(j), e(j) === !0) break;
                f = i
            }
            return g
        }, o = function (a) {
            var c = function (c) {
                return b.filter(c, function (b) {
                    return !g.eq(a, b)
                })
            };
            return l(a).map(u).map(c).getOr([])
        }, p = function (a) {
            var b = a.dom();
            return d.from(b.offsetParent).map(h.fromDom)
        }, q = function (a) {
            var b = a.dom();
            return d.from(b.previousSibling).map(h.fromDom)
        }, r = function (a) {
            var b = a.dom();
            return d.from(b.nextSibling).map(h.fromDom)
        }, s = function (a) {
            return b.reverse(f.toArray(a, q))
        }, t = function (a) {
            return f.toArray(a, r)
        }, u = function (a) {
            var c = a.dom();
            return b.map(c.childNodes, h.fromDom)
        }, v = function (a, b) {
            var c = a.dom().childNodes;
            return d.from(c[b]).map(h.fromDom)
        }, w = function (a) {
            return v(a, 0)
        }, x = function (a) {
            return v(a, a.dom().childNodes.length - 1)
        }, y = e.immutable("element", "offset"), z = function (a, b) {
            var c = u(a);
            return c.length > 0 && b < c.length ? y(c[b], 0) : y(a, b)
        };
        return {
            owner: i,
            defaultView: k,
            documentElement: j,
            parent: l,
            findIndex: m,
            parents: n,
            siblings: o,
            prevSibling: q,
            offsetParent: p,
            prevSiblings: s,
            nextSibling: r,
            nextSiblings: t,
            children: u,
            child: v,
            firstChild: w,
            lastChild: x,
            leaf: z
        }
    }), g("28", ["g", "k", "3f", "1g"], function (a, b, c, d) {
        var e = function (a, e) {
            var f = e || d, g = f.createElement("div");
            return g.innerHTML = a, c.children(b.fromDom(g))
        }, f = function (c, d) {
            return a.map(c, function (a) {
                return b.fromTag(a, d)
            })
        }, g = function (c, d) {
            return a.map(c, function (a) {
                return b.fromText(a, d)
            })
        }, h = function (c) {
            return a.map(c, b.fromDom)
        };
        return {fromHtml: e, fromTags: f, fromText: g, fromDom: h}
    }), g("1x", ["3f"], function (a) {
        var b = function (b, c) {
            var d = a.parent(b);
            d.each(function (a) {
                a.dom().insertBefore(c.dom(), b.dom())
            })
        }, c = function (c, d) {
            var f = a.nextSibling(c);
            f.fold(function () {
                var b = a.parent(c);
                b.each(function (a) {
                    e(a, d)
                })
            }, function (a) {
                b(a, d)
            })
        }, d = function (b, c) {
            var d = a.firstChild(b);
            d.fold(function () {
                e(b, c)
            }, function (a) {
                b.dom().insertBefore(c.dom(), a.dom())
            })
        }, e = function (a, b) {
            a.dom().appendChild(b.dom())
        }, f = function (c, d, f) {
            a.child(c, f).fold(function () {
                e(c, d)
            }, function (a) {
                b(a, d)
            })
        }, g = function (a, c) {
            b(a, c), e(c, a)
        };
        return {before: b, after: c, prepend: d, append: e, appendAt: f, wrap: g}
    }), g("29", ["g", "1x"], function (a, b) {
        var c = function (c, d) {
            a.each(d, function (a) {
                b.before(c, a)
            })
        }, d = function (c, d) {
            a.each(d, function (a, e) {
                var f = 0 === e ? c : d[e - 1];
                b.after(f, a)
            })
        }, e = function (c, d) {
            a.each(d.slice().reverse(), function (a) {
                b.prepend(c, a)
            })
        }, f = function (c, d) {
            a.each(d, function (a) {
                b.append(c, a)
            })
        };
        return {before: c, after: d, prepend: e, append: f}
    }), g("5w", [], function () {
        var a = function (a) {
            var b, c = !1;
            return function () {
                return c || (c = !0, b = a.apply(null, arguments)), b
            }
        };
        return {cached: a}
    }), g("5i", ["5w", "k", "1d", "1g"], function (a, b, c, d) {
        var e = function (a) {
            var b = c.isText(a) ? a.dom().parentNode : a.dom();
            return void 0 !== b && null !== b && b.ownerDocument.body.contains(b)
        }, f = a.cached(function () {
            return g(b.fromDom(d))
        }), g = function (a) {
            var c = a.dom().body;
            if (null === c || void 0 === c) throw"Body is not available yet";
            return b.fromDom(c)
        };
        return {body: f, getBody: g, inBody: e}
    }), g("3o", ["g", "5i", "3f"], function (a, b, c) {
        var d = function (a) {
            return h(b.body(), a)
        }, e = function (b, d, e) {
            return a.filter(c.parents(b, e), d)
        }, f = function (b, d) {
            return a.filter(c.siblings(b), d)
        }, g = function (b, d) {
            return a.filter(c.children(b), d)
        }, h = function (b, d) {
            var e = [];
            return a.each(c.children(b), function (a) {
                d(a) && (e = e.concat([a])), e = e.concat(h(a, d))
            }), e
        };
        return {all: d, ancestors: e, siblings: f, children: g, descendants: h}
    }), g("2a", ["3o", "2h"], function (a, b) {
        var c = function (a) {
            return b.all(a)
        }, d = function (c, d, e) {
            return a.ancestors(c, function (a) {
                return b.is(a, d)
            }, e)
        }, e = function (c, d) {
            return a.siblings(c, function (a) {
                return b.is(a, d)
            })
        }, f = function (c, d) {
            return a.children(c, function (a) {
                return b.is(a, d)
            })
        }, g = function (a, c) {
            return b.all(c, a)
        };
        return {all: c, ancestors: d, siblings: e, children: f, descendants: g}
    }), g("16", ["g", "26", "27", "k", "28", "29", "2a"], function (a, b, c, d, e, f, g) {
        var h = function (a) {
            c.remove(a, b.uploadInProgress())
        }, i = function (c) {
            for (var i = 0; i < c.undoManager.data.length; i++) {
                var j = c.undoManager.data[i].content, k = d.fromTag("div");
                f.append(k, e.fromHtml(j));
                var l = g.descendants(k, "." + b.uploadInProgress());
                a.each(l, h), c.undoManager.data[i].content = k.dom().innerHTML
            }
        }, j = function (a, b, c) {
            for (var d = 0; d < a.undoManager.data.length; d++) a.undoManager.data[d].content = a.undoManager.data[d].content.split(b.objurl()).join(c.location)
        };
        return {unwrapHistory: i, resrcHistory: j}
    }), g("2b", ["3p"], function (a) {
        var b = function () {
            return a.getOrDie("URL")
        }, c = function (a) {
            return b().createObjectURL(a)
        }, d = function (a) {
            b().revokeObjectURL(a)
        };
        return {createObjectURL: c, revokeObjectURL: d}
    }), g("17", ["1c", "2b", "n", "2c"], function (a, b, c, d) {
        var e = d.immutable("id", "blob", "objurl", "data");
        return function () {
            var d = {}, f = function (a, b, c, f) {
                var g = e(a, b, c, f);
                return d[a] = g, g
            }, g = function (a) {
                return c.from(d[a])
            }, h = function (a) {
                b.revokeObjectURL(a.objurl())
            }, i = function (b) {
                return c.from(a.find(d, function (a) {
                    return a.data().result === b
                }))
            }, j = function (a) {
                var b = d[a];
                delete d[a], void 0 !== b && h(b)
            }, k = function () {
                a.each(d, h), d = {}
            };
            return {add: f, get: g, remove: j, lookupByData: i, destroy: k}
        }
    }), g("21", ["g", "2c"], function (a, b) {
        return function (c) {
            var d = b.immutable.apply(null, c), e = [], f = function (a) {
                if (void 0 === a) throw"Event bind error: undefined handler";
                e.push(a)
            }, g = function (b) {
                e = a.filter(e, function (a) {
                    return a !== b
                })
            }, h = function () {
                var b = d.apply(null, arguments);
                a.each(e, function (a) {
                    a(b)
                })
            };
            return {bind: f, unbind: g, trigger: h}
        }
    }), g("22", ["1c"], function (a) {
        var b = function (b) {
            var c = a.map(b, function (a) {
                return {bind: a.bind, unbind: a.unbind}
            }), d = a.map(b, function (a) {
                return a.trigger
            });
            return {registry: c, trigger: d}
        };
        return {create: b}
    }), g("18", ["g", "2d", "26", "21", "22", "j", "2a"], function (a, b, c, d, e, f, g) {
        var h = "data-" + b.resolve("image-upload"), i = function (a, b) {
            return g.descendants(a, "img[" + h + '="' + b + '"]')
        }, j = function (a) {
            return g.descendants(a, "img:not([" + h + "])[" + c.blobId() + "]")
        };
        return function () {
            var b = [], c = [], g = e.create({complete: d(["response"])}), k = function (a, c) {
                f.set(a, h, c), b.push(c)
            }, l = function (c) {
                b = a.filter(b, function (a, b) {
                    return a !== c
                }), p() === !1 && o()
            }, m = function (a, b) {
                c.push({success: a, element: b.dom()})
            }, n = function (b, c, d) {
                a.each(c, function (a) {
                    f.remove(a, h), m(d, a)
                }), l(b)
            }, o = function () {
                g.trigger.complete(c), c = []
            }, p = function () {
                return b.length > 0
            }, q = function (c) {
                return a.contains(b, c)
            };
            return {
                findById: i, findAll: j, register: k,
                report: n, inProgress: p, isActive: q, events: g.registry
            }
        }
    }), g("1l", ["1b", "12"], function (a, b) {
        var c = function (a, b) {
            return b
        }, d = function (b, c) {
            var d = a.isObject(b) && a.isObject(c);
            return d ? f(b, c) : c
        }, e = function (a) {
            return function () {
                for (var c = new b(arguments.length), d = 0; d < c.length; d++) c[d] = arguments[d];
                if (0 === c.length) throw"Can't merge zero objects";
                for (var e = {}, f = 0; f < c.length; f++) {
                    var g = c[f];
                    for (var h in g) g.hasOwnProperty(h) && (e[h] = a(e[h], g[h]))
                }
                return e
            }
        }, f = e(d), g = e(c);
        return {deepMerge: f, merge: g}
    }), g("1m", ["1b", "g", "1c", "12"], function (a, b, c, d) {
        var e = function (e) {
            if (!a.isArray(e)) throw"cases must be an array";
            if (0 === e.length) throw"there must be at least one case";
            var f = {};
            return b.each(e, function (b, g) {
                var h = c.keys(b);
                if (1 !== h.length) throw"one and only one name per case";
                var i = h[0], j = b[i];
                if (void 0 !== f[i]) throw"duplicate key detected:" + i;
                if ("cata" === i) throw"cannot have a case named cata (sorry)";
                if (!a.isArray(j)) throw"case arguments must be an array";
                f[i] = function () {
                    var a = arguments.length;
                    if (a !== j.length) throw"Wrong number of arguments to case " + i + ". Expected " + j.length + " (" + j + "), got " + a;
                    for (var b = new d(a), c = 0; c < b.length; c++) b[c] = arguments[c];
                    return {
                        fold: function () {
                            if (arguments.length !== e.length) throw"Wrong number of arguments to fold. Expected " + e.length + ", got " + arguments.length;
                            var a = arguments[g];
                            return a.apply(null, b)
                        }
                    }
                }
            }), f
        };
        return {generate: e}
    }), g("h", ["1l", "1m"], function (a, b) {
        var c = b.generate([{blob: ["id", "blob", "objurl", "data"]}, {url: ["id", "url", "raw"]}]),
            d = function (a, b, c) {
                return a.fold(b, c)
            };
        return a.merge(c, {cata: d})
    }), g("2f", ["p", "n"], function (a, b) {
        var c = function (a) {
            return e(function (b, c) {
                return c(a)
            })
        }, d = function (a) {
            return e(function (b, c) {
                return b(a)
            })
        }, e = function (e) {
            var f = function (b) {
                return e(a.constant(!1), function (a) {
                    return a === b
                })
            }, g = function () {
                return e(a.constant(!1), a.constant(!0))
            }, h = a.not(g), i = function (b) {
                return e(a.constant(b), a.identity)
            }, j = function (b) {
                return e(b, a.identity)
            }, k = function () {
                return e(function (b) {
                    a.die(b)()
                }, a.identity)
            }, l = function (b) {
                return e(a.constant(b), c)
            }, m = function (a) {
                return e(a, c)
            }, n = function (a) {
                return p(function (b) {
                    return c(a(b))
                })
            }, o = n, p = function (a) {
                return e(d, a)
            }, q = function (b) {
                return e(a.constant(!1), b)
            }, r = function (b) {
                return e(a.constant(!0), b)
            }, s = function () {
                return e(b.none, b.some)
            };
            return {
                is: f,
                isValue: g,
                isError: h,
                getOr: i,
                getOrThunk: j,
                getOrDie: k,
                or: l,
                orThunk: m,
                fold: e,
                map: n,
                each: o,
                bind: p,
                exists: q,
                forall: r,
                toOption: s
            }
        };
        return {value: c, error: d}
    }), g("3q", ["g", "27", "3i", "12"], function (a, b, c, d) {
        var e = function (c, d) {
            a.each(d, function (a) {
                b.add(c, a)
            })
        }, f = function (c, d) {
            a.each(d, function (a) {
                b.remove(c, a)
            })
        }, g = function (c, d) {
            a.each(d, function (a) {
                b.toggle(c, a)
            })
        }, h = function (c, d) {
            return a.forall(d, function (a) {
                return b.has(c, a)
            })
        }, i = function (c, d) {
            return a.exists(d, function (a) {
                return b.has(c, a)
            })
        }, j = function (a) {
            for (var b = a.dom().classList, c = new d(b.length), e = 0; e < b.length; e++) c[e] = b.item(e);
            return c
        }, k = function (a) {
            return c.supports(a) ? j(a) : c.get(a)
        };
        return {add: e, remove: f, toggle: g, hasAll: h, hasAny: i, get: k}
    }), g("2g", ["27", "3q"], function (a, b) {
        var c = function (b) {
            return function (c) {
                a.add(c, b)
            }
        }, d = function (b) {
            return function (c) {
                a.remove(c, b)
            }
        }, e = function (a) {
            return function (c) {
                b.remove(c, a)
            }
        }, f = function (b) {
            return function (c) {
                return a.has(c, b)
            }
        };
        return {addClass: c, removeClass: d, removeClasses: e, hasClass: f}
    }), g("2i", ["1b", "n"], function (a, b) {
        return function (c, d, e, f, g) {
            return c(e, f) ? b.some(e) : a.isFunction(g) && g(e) ? b.none() : d(e, f, g)
        }
    }), g("20", ["n", "2a", "2h", "2i"], function (a, b, c, d) {
        var e = function (c) {
            return a.from(b.all(c)[0])
        }, f = function (c, d, e) {
            return a.from(b.ancestors(c, d, e)[0])
        }, g = function (c, d) {
            return a.from(b.siblings(c, d)[0])
        }, h = function (c, d) {
            return a.from(b.children(c, d)[0])
        }, i = function (c, d) {
            return a.from(b.descendants(c, d)[0])
        }, j = function (a, b, e) {
            return d(c.is, f, a, b, e)
        };
        return {first: e, ancestor: f, sibling: g, child: h, descendant: i, closest: j}
    }), g("19", ["g", "h", "p", "n", "2f", "26", "1m", "2c", "j", "27", "2g", "20", "1f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m) {
        var n = h.immutable("image", "blobInfo"),
            o = g.generate([{failure: ["error"]}, {success: ["result", "images", "blob"]}]), p = function (a, b, c) {
                var e = a.isActive(b);
                return a.register(c, b), j.add(c, f.uploadInProgress()), e ? d.none() : d.some(b)
            }, q = function (b, c, d, e) {
                return a.each(c, function (a) {
                    i.set(a, "src", e.location), i.remove(a, f.blobId())
                }), u(b, d)
            }, r = function (b, d, e, g, h, i, j) {
                var l = function () {
                    m.error("Internal error with blob cache", h), j(o.failure({status: c.constant(666)}))
                };
                b.upload(i, h, function (b) {
                    var c = d.findById(g, h);
                    a.each(c, k.removeClass(f.uploadInProgress())), b.fold(function (a) {
                        j(o.failure(a))
                    }, function (a) {
                        q(e, c, h, a).fold(l, function (b) {
                            j(o.success(a, c, b))
                        })
                    }), d.report(h, c, b.isValue())
                })
            }, s = function (a, b, c, d, e, g) {
                var h = a.lookupByData(e.result).getOrThunk(function () {
                    return a.add(b, c, d, e)
                });
                return i.set(g, f.blobId(), h.id()), n(g, h)
            }, t = function (a, b) {
                var c = i.get(b, f.blobId());
                return a.get(c).fold(function () {
                    return e.error(c)
                }, function (a) {
                    return e.value(n(b, a))
                })
            }, u = function (a, b) {
                return a.get(b).fold(function () {
                    return e.error("Internal error with blob cache")
                }, function (c) {
                    return a.remove(b), e.value(c)
                })
            }, v = function (d, f, g) {
                return a.bind(g, function (a) {
                    return b.cata(a, function (a, b, c, g) {
                        var h = l.descendant(f, 'img[src="' + c + '"]');
                        return h.fold(function () {
                            return [e.error("Image that was just inserted could not be found: " + c)]
                        }, function (f) {
                            return [e.value(s(d, a, b, c, g, f))]
                        })
                    }, c.constant([]))
                })
            }, w = function (b, c, d) {
                var e = b.findAll(d);
                return b.inProgress() ? [] : a.map(e, function (a) {
                    return t(c, a)
                })
            };
        return {prepareForUpload: p, handleUpload: r, registerAssets: v, registerBlob: s, findBlobs: w}
    }), g("3r", ["3p"], function (a) {
        return function () {
            var b = a.getOrDie("FormData");
            return new b
        }
    }), g("2j", ["1b", "g", "3r", "2c", "37"], function (a, b, c, d, e) {
        var f = d.immutable("message", "status", "contents"), g = ["jpg", "png", "gif", "jpeg"], h = function (c, d) {
            if (a.isString(c.type) && e.startsWith(c.type, "image/")) {
                var f = c.type.substr("image/".length);
                return b.contains(g, f) ? d + "." + f : d
            }
            return d
        }, i = function (b, c) {
            var d = a.isString(b.name) && !e.endsWith(b.name, ".tmp");
            return d ? b.name : h(b, c)
        }, j = function (a, b, d) {
            var e = c();
            return e.append(a, b, d), {data: e, contentType: !1, processData: !1}
        };
        return {failureObject: f, getFilename: i, buildExtra: j}
    }), g("1u", ["12"], function (a) {
        var b = function (b) {
            return function () {
                var c = a.prototype.slice.call(arguments), d = this;
                setTimeout(function () {
                    b.apply(d, c)
                }, 0)
            }
        };
        return {bounce: b}
    }), g("1v", [], function () {
        return function (a, b) {
            var c = function (c) {
                return a(function (a) {
                    b(function (b) {
                        var d = c(b);
                        a(d)
                    })
                })
            }, d = function (c) {
                return a(function (a) {
                    b(function (b) {
                        c(b).get(a)
                    })
                })
            }, e = function (c) {
                return a(function (a) {
                    b(function (b) {
                        c.get(a)
                    })
                })
            };
            return {get: b, map: c, bind: d, anonBind: e}
        }
    }), g("1w", ["g"], function (a) {
        return function (b) {
            var c = function (a) {
                return b(function (b) {
                    b(a)
                })
            }, d = function (c) {
                return b(function (b) {
                    var d = [], e = 0, f = function (a) {
                        return function (f) {
                            d[a] = f, e++, e >= c.length && b(d)
                        }
                    };
                    0 === c.length ? b([]) : a.each(c, function (a, b) {
                        a.get(f(b))
                    })
                })
            }, e = function (b, c) {
                return d(a.map(b, c))
            }, f = function (a, c, d) {
                return b(function (b) {
                    var e = !1, f = !1, g = void 0, h = void 0, i = function () {
                        if (e && f) {
                            var a = d(g, h);
                            b(a)
                        }
                    };
                    a.get(function (a) {
                        g = a, e = !0, i()
                    }), c.get(function (a) {
                        h = a, f = !0, i()
                    })
                })
            }, g = function (a, b) {
                return function (c) {
                    return b(c).bind(a)
                }
            };
            return {nu: b, pure: c, par: d, mapM: e, lift2: f, compose: g}
        }
    }), g("o", ["1u", "1v", "1w"], function (a, b, c) {
        var d = function (c) {
            var e = function (b) {
                c(a.bounce(b))
            };
            return b(d, e)
        };
        return c(d)
    }), g("2u", ["3p"], function (a) {
        return function () {
            var b = a.getOrDie("FileReader");
            return new b
        }
    }), g("73", ["o", "2u"], function (a, b) {
        return function (c) {
            return a.nu(function (a) {
                var d = b();
                d.onload = function (b) {
                    var c = b.target;
                    a(c.result)
                }, d.readAsText(c)
            })
        }
    }), g("74", ["3p"], function (a) {
        return function () {
            var b = a.getOrDie("XMLHttpRequest");
            return new b
        }
    }), g("69", ["1b", "1c", "1l", "73", "o", "74", "n", "2f", "37", "1f"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = {
            "*": "*/*",
            text: "text/plain",
            html: "text/html",
            xml: "application/xml, text/xml",
            json: "application/json, text/javascript"
        }, l = function (l, m, n, o) {
            var p = {url: l, contentType: "application/json", processData: !1, type: "GET"}, q = c.merge(p, o), r = f();
            r.open(q.type.toUpperCase(), q.url, !0), "blob" === q.responseType && (r.responseType = q.responseType), a.isString(q.contentType) && r.setRequestHeader("Content-Type", q.contentType);
            var s = q.dataType, t = a.isString(s) && "*" !== s ? k[s] + ", " + k["*"] + "; q=0.01" : k["*"];
            r.setRequestHeader("Accept", t), void 0 !== q.xhrFields && q.xhrFields.withCredentials === !0 && (r.withCredentials = !0), a.isObject(q.headers) && b.each(q.headers, function (b, c) {
                a.isString(c) || a.isString(b) ? r.setRequestHeader(c, b) : j.error("Request header data was not a string: ", c, " -> ", b)
            });
            var u = function (a, b, c) {
                m(a)
            }, v = function () {
                return "blob" === q.responseType ? g.from(r.response).map(d).getOr(e.pure("no response content")) : e.pure(r.responseText)
            }, w = function () {
                v().get(function (a) {
                    0 === r.status ? n("Unknown HTTP error (possible cross-domain request)", r.status, a) : n('Could not load url "' + l + '": ' + r.statusText, r.status, a)
                })
            }, x = function () {
                try {
                    return h.value(JSON.parse(r.response))
                } catch (a) {
                    return h.error({
                        status: r.status,
                        statusText: "Response was not JSON",
                        responseText: r.responseText
                    })
                }
            }, y = function () {
                var a = "json" === s ? x(r) : h.value(r.response);
                a.fold(w, function (a) {
                    u(a, r.statusText, r)
                })
            }, z = function () {
                0 === r.status ? i.startsWith(q.url, "file:") ? y() : w() : r.status < 100 || r.status >= 400 ? w() : y()
            };
            r.onerror = w, r.onload = z, void 0 === q.data ? r.send() : r.send(q.data)
        };
        return {ajax: l}
    }), g("3u", ["3p"], function (a) {
        var b = function () {
            return a.getOrDie("JSON")
        }, c = function (a) {
            return b().parse(a)
        }, d = function (a, c, d) {
            return b().stringify(a, c, d)
        };
        return {parse: c, stringify: d}
    }), g("3t", ["1l", "69", "3u"], function (a, b, c) {
        var d = function (c, d, e, f) {
            b.ajax(c, d, e, a.merge({dataType: "text", type: "GET"}, f))
        }, e = function (d, e, f, g, h) {
            b.ajax(d, f, g, a.merge({dataType: "text", data: c.stringify(e), type: "POST"}, h))
        };
        return {get: d, post: e}
    }), g("6a", [], function () {
        var a = function (a) {
            var b = "";
            return "" !== a.protocol && (b += a.protocol, b += ":"), "" !== a.authority && (b += "//", b += a.authority), b += a.path, "" !== a.query && (b += "?", b += a.query), "" !== a.anchor && (b += "#", b += a.anchor), b
        };
        return {recompose: a}
    }), g("75", ["1l"], function (a) {
        var b = {
            strictMode: !1,
            key: ["source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor"],
            q: {name: "queryKey", parser: /(?:^|&)([^&=]*)=?([^&]*)/g},
            parser: {
                strict: /^(?:([^:\/?#]+):)?(?:\/\/((?:(([^:@\/]*)(?::([^:@\/]*))?)?@)?([^:\/?#]*)(?::(\d*))?))?((((?:[^?#\/]*\/)*)([^?#]*))(?:\?([^#]*))?(?:#(.*))?)/,
                loose: /^(?:(?![^:@\/]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@\/]*)(?::([^:@\/]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/
            }
        }, c = function (a, b) {
            for (var c = b, d = c.parser[c.strictMode ? "strict" : "loose"].exec(a), e = {}, f = 14; f--;) e[c.key[f]] = d[f] || "";
            return e[c.q.name] = {}, e[c.key[12]].replace(c.q.parser, function (a, b, d) {
                b && (e[c.q.name][b] = d)
            }), e
        }, d = function (d, e) {
            var f = a.merge(b, e);
            return c(d, f)
        };
        return {parse: d}
    }), g("76", ["37"], function (a) {
        var b = function (b) {
            return a.removeTrailing(b, d(b))
        }, c = function (a) {
            return a.match(/(^\/?.*?)(\/|$)/)[1]
        }, d = function (a) {
            return a.substring(a.lastIndexOf("/"))
        }, e = function (d) {
            for (var e = d, f = ""; "" !== e;) if (a.startsWith(e, "../")) e = a.removeLeading(e, "../"); else if (a.startsWith(e, "./")) e = a.removeLeading(e, "./"); else if (a.startsWith(e, "/./")) e = "/" + a.removeLeading(e, "/./"); else if ("/." === e) e = "/"; else if (a.startsWith(e, "/../")) e = "/" + a.removeLeading(e, "/../"), f = b(f); else if ("/.." === e) e = "/", f = b(f); else if ("." === e || ".." === e) e = ""; else {
                var g = c(e);
                e = a.removeLeading(e, g), f += g
            }
            return f
        };
        return {remove: e}
    }), g("77", ["37"], function (a) {
        var b = function (b, c, d) {
            if ("" !== d && "" === b) return "/" + c;
            var e = b.substring(b.lastIndexOf("/") + 1);
            return a.removeTrailing(b, e) + c
        };
        return {merge: b}
    }), g("6b", ["37", "75", "76", "77"], function (a, b, c, d) {
        var e = function (e, f) {
            var g = {strictMode: !0}, h = b.parse(e, g), i = b.parse(f, g), j = {};
            return "" !== i.protocol ? (j.protocol = i.protocol, j.authority = i.authority, j.path = c.remove(i.path), j.query = i.query) : ("" !== i.authority ? (j.authority = i.authority, j.path = c.remove(i.path), j.query = i.query) : ("" === i.path ? (j.path = h.path, "" !== i.query ? j.query = i.query : j.query = h.query) : (a.startsWith(i.path, "/") ? j.path = c.remove(i.path) : (j.path = d.merge(h.path, i.path, e.authority), j.path = c.remove(j.path)), j.query = i.query), j.authority = h.authority), j.protocol = h.protocol), j.anchor = i.anchor, j
        };
        return {transform: e}
    }), g("3v", ["6a", "6b"], function (a, b) {
        var c = function (c, d) {
            var e = b.transform(c, d);
            return a.recompose(e)
        };
        return {resolve: c}
    }), g("2k", ["1b", "1l", "3t", "3u", "2f", "2j", "37", "3v"], function (a, b, c, d, e, f, g, h) {
        return function (i) {
            var j = function () {
                var a = i.url, b = a.lastIndexOf("/"), c = b > 0 ? a.substr(0, b) : a,
                    d = void 0 === i.basePath ? c : i.basePath;
                return g.endsWith(d, "/") ? d : d + "/"
            }, k = j(), l = function (a, b) {
                var c = a.split(/\s+/), d = 1 === c.length && "" !== c[0] ? c[0] : b;
                return h.resolve(k, d)
            }, m = function (g, h, j) {
                var k = g.blob(), m = function (a, b, c) {
                        j(e.error(f.failureObject(a, b, c)))
                    }, n = f.getFilename(k, h), o = i.credentials !== !0 ? {} : {xhrFields: {withCredentials: !0}},
                    p = b.merge(o, f.buildExtra("image", k, n)), q = function (b) {
                        var c;
                        try {
                            var f = d.parse(b);
                            if (!a.isString(f.location)) return void m("JSON response did not contain a string location", 500, b);
                            c = f.location
                        } catch (g) {
                            c = b
                        }
                        var h = l(c, n);
                        j(e.value({location: h}))
                    };
                c.post(i.url, {}, q, m, p)
            };
            return {upload: m}
        }
    }), h("x", setTimeout), g("2l", ["1b", "2f", "2j", "2c", "1f", "x"], function (a, b, c, d, e, f) {
        var g = d.immutable("id", "filename", "blob", "base64");
        return function (d) {
            var h = function (h, i, j) {
                var k = function (a) {
                    j(b.error(a))
                }, l = function (c) {
                    a.isString(c) ? j(b.value({location: c})) : (e.error("Image upload result was not a string"), k(""))
                }, m = c.getFilename(h.blob(), i), n = g(i, m, h.blob(), h.data().result);
                f(function () {
                    d(n, l, k)
                }, 0)
            };
            return {upload: h}
        }
    }), g("1a", ["2j", "2k", "2l"], function (a, b, c) {
        var d = function (a) {
            return b(a)
        }, e = function (a) {
            return c(a)
        }, f = function (b, c, d) {
            return a.failureObject(b, c, d)
        }, g = function (b, c) {
            return a.getFilename(b, c)
        }, h = function (b, c, d) {
            return a.buildExtra(b, c, d)
        };
        return {direct: d, custom: e, failureObject: f, getFilename: g, buildExtra: h}
    }),g("b", ["g", "p", "n", "c", "15", "16", "17", "18", "19", "1a", "j", "k"], function (a, b, c, d, e, f, g, h, i, j, k, l) {
        var m = function (d, m) {
            var n = g(), o = h(), p = (e(), e(d, m.url)), q = j.direct(m), r = function () {
                return l.fromDom(d.getBody())
            }, s = function (b, c, e) {
                a.each(c, function (a) {
                    k.set(a, "data-mce-src", b.location)
                }), f.resrcHistory(d, e, b)
            };
            o.events.complete.bind(function (a) {
                f.unwrapHistory(d)
            });
            var t = function (a, b, c) {
                i.handleUpload(q, o, n, r(), a, b, function (a) {
                    a.fold(function (a) {
                        c(a)
                    }, s)
                })
            }, u = function (a, b) {
                i.prepareForUpload(o, a.blobInfo().id(), a.image()).each(function (c) {
                    t(c, a.blobInfo(), b)
                })
            }, v = function (b) {
                var c = p.instance(), d = i.registerAssets(n, r(), b);
                a.each(d, function (a) {
                    a.fold(function (a) {
                        console.error(a)
                    }, function (a) {
                        u(a, c)
                    })
                })
            }, w = function () {
                var b = p.instance(), d = i.findBlobs(o, n, r());
                a.each(d, function (a) {
                    a.fold(function (a) {
                        o.report(a, c.none(), !1)
                    }, function (a) {
                        u(a, b)
                    })
                })
            }, x = function (a) {
                w(), v(a)
            }, y = function (a, b, c, d) {
                return c
            };
            return {uploadImages: x, prepareImages: b.noop, getLocalURL: y}
        }, n = function (a) {
            var c = d(a);
            return {uploadImages: b.noop, prepareImages: c.prepareImages, getLocalURL: c.getLocalURL}
        };
        return function (a, b) {
            return b ? m(a, b) : n(a)
        }
    }),g("3", ["b", "c"], function (a, b) {
        return function (c) {
            var d = !c.uploadImages && c.settings.images_upload_url ? {
                url: c.settings.images_upload_url,
                basePath: c.settings.images_upload_base_path,
                credentials: c.settings.images_upload_credentials
            } : null;
            return c.uploadImages ? b(c) : a(c, d)
        }
    }),g("1h", [], function () {
        var a = function (a, b) {
            return function () {
                return a.apply(b, arguments)
            }
        }, b = function (a) {
            return a.ownerDocument.defaultView ? a.ownerDocument.defaultView.getComputedStyle(a, null) : a.currentStyle || {}
        }, c = function (a) {
            "undefined" != typeof console && console.log && console.log(a)
        }, d = function (a) {
            var b = Array.prototype.slice.call(a).reverse();
            return function (a) {
                for (var c = a, d = 0; d < b.length; d++) {
                    var e = b[d];
                    c = e(c)
                }
                return c
            }
        }, e = function (a) {
            return tinymce.each(Array.prototype.slice.call(arguments, 1), function (b) {
                for (var c in b) a[c] = b[c]
            }), a
        };
        return {
            each: tinymce.each,
            trim: tinymce.trim,
            bind: a,
            extend: e,
            ephoxGetComputedStyle: b,
            log: c,
            compose: d
        }
    }),g("e", ["1h"], function (a) {
        var b = (tinymce.each, function (b, c, d, e) {
            var f, g, h, i, j, k = b.selection, l = b.dom, m = b.getBody();
            if (j = d.isText(), d.reset(), e.clipboardData && e.clipboardData.getData("text/html")) {
                e.preventDefault();
                var n = e.clipboardData.getData("text/html"), o = n.match(/<html[\s\S]+<\/html>/i),
                    p = null === o ? n : o[0];
                return c(p)
            }
            if (!l.get("_mcePaste")) {
                if (f = l.add(m, "div", {
                    id: "_mcePaste",
                    "class": "mcePaste"
                }, '\ufeff<br _mce_bogus="1">'), i = m != b.getDoc().body ? l.getPos(b.selection.getStart(), m).y : m.scrollTop, l.setStyles(f, {
                    position: "absolute",
                    left: -1e4,
                    top: i,
                    width: 1,
                    height: 1,
                    overflow: "hidden"
                }), tinymce.isIE) return h = l.doc.body.createTextRange(), h.moveToElementText(f), h.execCommand("Paste"), l.remove(f), "\ufeff" === f.innerHTML ? (b.execCommand("mcePasteWord"), void e.preventDefault()) : (c(j ? f.innerText : f.innerHTML), tinymce.dom.Event.cancel(e));
                var q = function (a) {
                    a.preventDefault()
                };
                l.bind(b.getDoc(), "mousedown", q), l.bind(b.getDoc(), "keydown", q), tinymce.isGecko && (h = b.selection.getRng(!0), h.startContainer == h.endContainer && 3 == h.startContainer.nodeType && (nodes = l.select("p,h1,h2,h3,h4,h5,h6,pre", f), 1 == nodes.length && l.remove(nodes.reverse(), !0))), g = b.selection.getRng(), f = f.firstChild, h = b.getDoc().createRange(), h.setStart(f, 0), h.setEnd(f, 1), k.setRng(h), window.setTimeout(function () {
                    var d = "", e = l.select("div.mcePaste");
                    a.each(e, function (b) {
                        var c = b.firstChild;
                        c && "DIV" == c.nodeName && c.style.marginTop && c.style.backgroundColor && l.remove(c, 1), a.each(l.select("div.mcePaste", b), function (a) {
                            l.remove(a, 1)
                        }), a.each(l.select("span.Apple-style-span", b), function (a) {
                            l.remove(a, 1)
                        }), a.each(l.select("br[_mce_bogus]", b), function (a) {
                            l.remove(a)
                        }), d += b.innerHTML
                    }), a.each(e, function (a) {
                        l.remove(a)
                    }), g && k.setRng(g), c(d), l.unbind(b.getDoc(), "mousedown", q), l.unbind(b.getDoc(), "keydown", q)
                }, 0)
            }
        }), c = function (a, c, d) {
            return function (e) {
                b(a, c, d, e)
            }
        }, d = function (a, c, d) {
            return function (e) {
                (tinymce.isOpera || navigator.userAgent.indexOf("Firefox/2") > 0) && ((tinymce.isMac ? e.metaKey : e.ctrlKey) && 86 == e.keyCode || e.shiftKey && 45 == e.keyCode) && b(a, c, d, e)
            }
        };
        return {getOnPasteFunction: c, getOnKeyDownFunction: d}
    }),g("1i", [], function () {
        var a = function (a, b) {
            var c, d = b.getDoc(), e = "ephoxInsertMarker", f = b.selection, g = b.dom;
            f.setContent('<span id="' + e + '">&nbsp;</span>'), c = g.get(e);
            for (var h = d.createDocumentFragment(); a.firstChild && !g.isBlock(a.firstChild);) h.appendChild(a.firstChild);
            for (var i = d.createDocumentFragment(); a.lastChild && !g.isBlock(a.lastChild);) i.appendChild(a.lastChild);
            if (c.parentNode.insertBefore(h, c), g.insertAfter(i, c), a.firstChild) {
                if (g.isBlock(a.firstChild)) {
                    for (; !g.isBlock(c.parentNode) && c.parentNode !== g.getRoot();) c = g.split(c.parentNode, c);
                    g.is(c.parentNode, "td,th") || c.parentNode === g.getRoot() || (c = g.split(c.parentNode, c))
                }
                g.replace(a, c)
            } else g.remove(c)
        };
        return {insert: a}
    }),g("1j", ["1h"], function (a) {
        var b = {strip_class_attributes: "all", retain_style_properties: "none"},
            c = {strip_class_attributes: "none", retain_style_properties: "valid"}, d = function (a, d) {
                if (a && "string" != typeof a) return a;
                switch (a) {
                    case"clean":
                        return b;
                    case"merge":
                        return c;
                    default:
                        return d
                }
            }, e = function (b, c, e) {
                var f = d(b, c);
                return f = a.extend(f, {base_64_images: e})
            }, f = function (a, d, f) {
                var g = e(a, b, f), h = e(d, c, f), i = h, j = function (a) {
                    i = a ? g : h
                }, k = function (a) {
                    return i[a]
                };
                return {setWordContent: j, get: k}
            };
        return {create: f}
    }),g("6c", ["1h"], function (a) {
        var b = function (a) {
            return a.specified !== !1 || "name" === a.nodeName && "" !== a.value
        }, c = function (a, b) {
            return a && b ? function (c, d) {
                return b(c, a(c, d))
            } : a || b
        }, d = function (d) {
            var e, f, g = 0, h = function () {
                return e
            }, i = function () {
                return f()
            };
            f = function () {
                return e = {}, g = 0, a.each(d.attributes, function (a) {
                    var c = a.nodeName, d = a.value;
                    b(a) && null !== d && void 0 !== d && (e[c] = d, g++)
                }), void 0 === e.style && d.style.cssText && (e.style = d.style.cssText, g++), f = h, e
            };
            var j, k, l = function () {
                return f(), g
            }, m = function (a) {
                j || (k = f), j = c(j, a), f = function () {
                    return f = k, o(function (a, b) {
                        var c = j(a, b);
                        null === c ? (d.removeAttribute(a), delete e[a], g--) : c !== b && ("class" === a ? d.className = c : d.setAttribute(a, c), e[a] = c)
                    }), f = h, e
                }
            }, n = function (a) {
                return f()[a]
            }, o = function (b) {
                a.each(f(), function (a, c) {
                    b(c, a)
                })
            };
            return {get: n, each: o, filter: m, getAttributes: i, getAttributeCount: l}
        };
        return {manager: d}
    }),g("3w", ["6c", "1h"], function (a, b, c) {
        var d = "startElement", e = "endElement", f = "text", g = "comment", h = a.manager, i = function (a) {
            return a.replace(/-(.)/g, function (a, b) {
                return b.toUpperCase()
            })
        }, j = function (a) {
            return a.replace(/([A-Z])/g, function (a, b) {
                return "-" + b.toLowerCase()
            })
        }, k = !1, l = function (a, c, d) {
            var e, f, g;
            a.style.length;
            g = c || a.getAttribute("style"), void 0 !== g && null !== g && g.split || (g = a.style.cssText), b.each(g.split(";"), function (a) {
                var c = a.indexOf(":");
                c > 0 && (e = b.trim(a.substring(0, c)), e.toUpperCase() === e && (e = e.toLowerCase()), e = j(e), f = b.trim(a.substring(c + 1)), k || (k = 0 === e.indexOf("mso-")), d(e, f))
            }), k || (f = a.style["mso-list"], f && d("mso-list", f))
        }, m = function (a, c, j) {
            var k, m, n, o, p;
            switch (a.nodeType) {
                case 1:
                    c ? k = e : (k = d, o = h(a), p = {}, l(a, j, function (a, b) {
                        p[a] = b
                    })), m = "HTML" !== a.scopeName && a.scopeName && a.tagName && a.tagName.indexOf(":") <= 0 ? (a.scopeName + ":" + a.tagName).toUpperCase() : a.tagName;
                    break;
                case 3:
                    k = f, n = a.nodeValue;
                    break;
                case 8:
                    k = g, n = a.nodeValue;
                    break;
                default:
                    b.log("WARNING: Unsupported node type encountered: " + a.nodeType)
            }
            var q = function () {
                return o && o.getAttributes(), a
            }, r = function () {
                return m
            }, s = function () {
                return k
            }, t = function () {
                return n
            }, u = function () {
                return "Type: " + k + ", Tag: " + m + " Text: " + n
            }, v = function (a) {
                return o.get(a.toLowerCase())
            }, w = function (a) {
                k === d && o.filter(a)
            }, x = function (c) {
                if (s() === d) {
                    var e = "";
                    b.each(p, function (b, d) {
                        var f = c(d, b);
                        null === f ? (a.style.removeProperty ? a.style.removeProperty(i(d)) : a.style.removeAttribute(i(d)), delete p[d]) : (e += d + ": " + f + "; ", p[d] = f)
                    }), e = e ? e : null, w(function (a, b) {
                        return "style" === a ? e : b
                    }), a.style.cssText = e
                }
            }, y = function () {
                return o.getAttributeCount()
            }, z = function (a) {
                o.each(a)
            }, A = function (a) {
                return p[a]
            }, B = function (a) {
                b.each(p, function (b, c) {
                    a(c, b)
                })
            }, C = function () {
                return b.ephoxGetComputedStyle(a)
            }, D = function () {
                return k === f && /^[\s\u00A0]*$/.test(n)
            };
            return {
                getNode: q,
                tag: r,
                type: s,
                text: t,
                toString: u,
                getAttribute: v,
                filterAttributes: w,
                filterStyles: x,
                getAttributeCount: y,
                attributes: z,
                getStyle: A,
                styles: B,
                getComputedStyle: C,
                isWhitespace: D
            }
        }, n = function (a, c, d, e) {
            var f = e.createElement(a), g = "";
            return b.each(c, function (a, b) {
                f.setAttribute(b, a)
            }), b.each(d, function (a, b) {
                g += b + ":" + a + ";", f.style[i(b)] = a
            }), m(f, !1, "" !== g ? g : null)
        }, o = function (a, b) {
            return m(b.createElement(a), !0)
        }, p = function (a, b) {
            return m(b.createComment(a), !1)
        }, q = function (a, b) {
            return m(b.createTextNode(a))
        }, r = o("HTML", window.document);
        return {
            START_ELEMENT_TYPE: d,
            END_ELEMENT_TYPE: e,
            TEXT_TYPE: f,
            COMMENT_TYPE: g,
            FINISHED: r,
            token: m,
            createStartElement: n,
            createEndElement: o,
            createComment: p,
            createText: q
        }
    }),g("2m", ["3w"], function (a) {
        var b = function (b) {
            var c = b.createDocumentFragment(), d = c, e = function (a) {
                g(a), c = a
            }, f = function () {
                c = c.parentNode
            }, g = function (a) {
                c.appendChild(a)
            }, h = function (c) {
                var d = function (a) {
                    var b = a.getNode().cloneNode(!1);
                    e(b)
                }, h = function (a, c) {
                    var d = b.createTextNode(a.text());
                    g(d)
                };
                switch (c.type()) {
                    case a.START_ELEMENT_TYPE:
                        d(c);
                        break;
                    case a.TEXT_TYPE:
                        h(c);
                        break;
                    case a.END_ELEMENT_TYPE:
                        f();
                        break;
                    case a.COMMENT_TYPE:
                        break;
                    default:
                        throw{message: "Unsupported token type: " + c.type()}
                }
            };
            return {dom: d, receive: h}
        };
        return {create: b}
    }),g("2n", ["3w"], function (a) {
        var b = function (b, c) {
            var d;
            c = c || window.document, d = c.createElement("div"), c.body.appendChild(d), d.style.position = "absolute", d.style.left = "-10000px", d.innerHTML = b, nextNode = d.firstChild || a.FINISHED;
            var e = [];
            endNode = !1;
            var f = function (b, c) {
                return b === a.FINISHED ? b : b ? a.token(b, c) : void 0
            }, g = function () {
                var b = nextNode, g = endNode;
                return !endNode && nextNode.firstChild ? (e.push(nextNode), nextNode = nextNode.firstChild) : endNode || 1 !== nextNode.nodeType ? nextNode.nextSibling ? (nextNode = nextNode.nextSibling, endNode = !1) : (nextNode = e.pop(), endNode = !0) : endNode = !0, b === a.FINISHED || nextNode || (c.body.removeChild(d), nextNode = a.FINISHED), f(b, g)
            }, h = function () {
                return void 0 !== nextNode
            };
            return {hasNext: h, next: g}
        };
        return {tokenize: b}
    }),g("3x", ["3w", "1h"], function (a, b) {
        var c = function (c, d) {
            var e = function (e, f, g) {
                var h, i, j, k = !1, l = function () {
                    d && d(w), k = !1, i = [], j = []
                }, m = function (a) {
                    b.each(a, function (a) {
                        e.receive(a)
                    })
                }, n = function (a) {
                    k ? j.push(a) : e.receive(a)
                }, o = function (b) {
                    d && i.push(b), c(w, b), b === a.FINISHED && r()
                }, p = function () {
                    k = !0
                }, q = function () {
                    m(i), l()
                }, r = function () {
                    u(), m(j), l()
                }, s = function (a) {
                    h = h || [], h.push(a)
                }, t = function () {
                    return h && h.length > 0
                }, u = function () {
                    b.each(h, function (a) {
                        n(a)
                    }), v()
                }, v = function () {
                    h = []
                }, w = {
                    document: g || window.document,
                    settings: f || {},
                    emit: n,
                    receive: o,
                    startTransaction: p,
                    rollback: q,
                    commit: r,
                    defer: s,
                    hasDeferred: t,
                    emitDeferred: u,
                    dropDeferred: v
                };
                return l(), w
            };
            return e
        }, d = function (a) {
            return c(function (c, d) {
                d.filterAttributes(b.bind(a, c)), c.emit(d)
            })
        };
        return {createFilter: c, createAttributeFilter: d}
    }),g("2o", ["3x", "3w"], function (a, b) {
        var c = /^(P|H[1-6]|T[DH]|LI|DIV|BLOCKQUOTE|PRE|ADDRESS|FIELDSET|DD|DT|CENTER)$/, d = function (a) {
            return c.test(a.tag())
        }, e = function () {
            return null
        }, f = !1;
        return a.createFilter(function (a, c) {
            var g = function () {
                f || (a.emit(b.createStartElement("P", {}, {}, a.document)), f = !0)
            };
            switch (c.type()) {
                case b.TEXT_TYPE:
                    g(), a.emit(c);
                    break;
                case b.END_ELEMENT_TYPE:
                    f && (d(c) || c === b.FINISHED) ? (a.emit(b.createEndElement("P", a.document)), f = !1) : "BR" === c.tag() && a.emit(c);
                    break;
                case b.START_ELEMENT_TYPE:
                    "BR" === c.tag() ? (c.filterAttributes(e), c.filterStyles(e), a.emit(c)) : "IMG" === c.tag() && c.getAttribute("alt") && (g(), a.emit(b.createText(c.getAttribute("alt"), a.document)))
            }
            c === b.FINISHED && a.emit(c)
        })
    }),g("3y", ["3w"], function (a) {
        var b = function () {
            if (navigator.userAgent.indexOf("Gecko") > 0 && navigator.userAgent.indexOf("WebKit") < 0) return !1;
            var b = document.createElement("div");
            try {
                b.innerHTML = '<p style="mso-list: Ignore;">&nbsp;</p>'
            } catch (c) {
                return !1
            }
            return "Ignore" === a.token(b.firstChild).getStyle("mso-list")
        }, c = b(), d = function (a) {
            return "A" === a.tag() || "SPAN" === a.tag()
        }, e = function (a) {
            var b = a.getStyle("mso-list");
            return b && "skip" !== b
        }, f = function (b, c) {
            return b.type() === a.START_ELEMENT_TYPE ? 0 === b.getAttributeCount() || c && 1 === b.getAttributeCount() && null !== b.getAttribute("style") && void 0 !== b.getAttribute("style") : b.type() === a.END_ELEMENT_TYPE
        };
        return {hasNoAttributes: f, supportsCustomStyles: c, spanOrA: d, hasMsoListStyle: e}
    }),g("42", ["3w", "1h"], function (a, b) {
        var c = [{regex: /^\(?[dc][\.\)]$/, type: {tag: "OL", type: "lower-alpha"}}, {
                regex: /^\(?[DC][\.\)]$/,
                type: {tag: "OL", type: "upper-alpha"}
            }, {
                regex: /^\(?M*(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})[\.\)]$/,
                type: {tag: "OL", type: "upper-roman"}
            }, {
                regex: /^\(?m*(cm|cd|d?c{0,3})(xc|xl|l?x{0,3})(ix|iv|v?i{0,3})[\.\)]$/,
                type: {tag: "OL", type: "lower-roman"}
            }, {regex: /^\(?[0-9]+[\.\)]$/, type: {tag: "OL"}}, {
                regex: /^([0-9]+\.)*[0-9]+\.?$/,
                type: {tag: "OL", variant: "outline"}
            }, {regex: /^\(?[a-z]+[\.\)]$/, type: {tag: "OL", type: "lower-alpha"}}, {
                regex: /^\(?[A-Z]+[\.\)]$/,
                type: {tag: "OL", type: "upper-alpha"}
            }], d = {
                "\u2022": {tag: "UL", type: "disc"},
                "\xb7": {tag: "UL", type: "disc"},
                "\xa7": {tag: "UL", type: "square"}
            }, e = {o: {tag: "UL", type: "circle"}, "-": {tag: "UL", type: "disc"}, "\u25cf": {tag: "UL", type: "disc"}},
            f = function (a, b) {
                var c = {tag: a.tag, type: a.type, variant: b};
                return a.start && (c.start = a.start), a.type || delete c.type, c
            }, g = function (a, g, i) {
                var j, k, l, m = null;
                return a && (j = a.text, k = a.symbolFont), j = b.trim(j), m = e[j], m ? m = f(m, j) : k ? (m = d[j], m = m ? f(m, j) : {
                    tag: "UL",
                    variant: j
                }) : (b.each(c, function (a) {
                    if (a.regex.test(j)) {
                        if (g && h(a.type, g, !0)) return m = a.type, m.start = parseInt(j), !1;
                        m || (m = a.type), m.start = parseInt(j)
                    }
                }), m && !m.variant && (l = "(" === j.charAt(0) ? "()" : ")" === j.charAt(j.length - 1) ? ")" : ".", m = f(m, l))), m && "OL" === m.tag && i && ("P" !== i.tag() || /^MsoHeading/.test(i.getAttribute("class"))) && (m = null), m
            }, h = function (a, b, c) {
                return a === b || a && b && a.tag === b.tag && a.type === b.type && (c || a.variant === b.variant)
            }, i = function (b, c) {
                return b.type() == a.START_ELEMENT_TYPE && (font = b.getStyle("font-family"), font ? c = "Wingdings" === font || "Symbol" === font : /^(P|H[1-6]|DIV)$/.test(b.tag()) && (c = !1)), c
            };
        return {guessListType: g, eqListType: h, checkFont: i}
    }),g("3z", ["3w", "42", "1h"], function (a, b, c) {
        var d = function (d, e) {
            var f, g, h, i = !1, j = function (a) {
                var b = a.style.fontFamily;
                b && (i = "Wingdings" === b || "Symbol" === b)
            };
            if (d.type() === a.START_ELEMENT_TYPE && e.openedTag && "SPAN" === d.tag()) {
                for (f = e.openedTag.getNode(), j(f), f.childNodes.length > 1 && "A" === f.firstChild.tagName && "" === f.firstChild.textContent && (f = f.childNodes[1]); f.firstChild && ("SPAN" === f.firstChild.tagName || "A" === f.firstChild.tagName);) f = f.firstChild, j(f);
                if (f = f.firstChild, !f || 3 !== f.nodeType) return f && "IMG" === f.tagName;
                if (g = f.value, c.trim(g) || (f = f.parentNode.nextSibling, g = f ? f.value : ""), !f || c.trim(f.parentNode.textContent) != g) return !1;
                if (h = b.guessListType({
                    text: g,
                    symbolFont: i
                }, null, e.originalToken)) return f.nextSibling && "SPAN" === f.nextSibling.tagName && /^[\u00A0\s]/.test(f.nextSibling.firstChild.value) && ("P" === e.openedTag.tag() || "UL" === h.tag)
            }
            return !1
        }, e = function (a, b) {
            var c, d = 0;
            for (c = a.parentNode; null !== c && void 0 !== c && c !== b.parentNode;) d += c.offsetLeft, c = c.offsetParent;
            return d
        }, f = function (a) {
            var b = {};
            return function (c, d) {
                var e, f = c + "," + d;
                return b.hasOwnProperty(f) ? b[f] : (e = a.call(null, c, d), b[f] = e, e)
            }
        }, g = function (a) {
            var b = a.indexOf(".");
            if (b >= 0 && c.trim(a.substring(b + 1)) === className) return match = results[2], !1
        }, h = f(function (a, b) {
            var d, e, f, h, i = /([^{]+){([^}]+)}/g;
            for (i.lastIndex = 0; null !== (d = i.exec(a)) && !e;) c.each(d[1].split(","), g(selector));
            return !!e && (f = document.createElement("p"), f.setAttribute("style", e), h = c.ephoxGetComputedStyle(f), !!h && "" + h.marginLeft)
        }), i = function () {
            var a, b, c = function (c, d, f, g) {
                var i, j, k = 1;
                return g && /^([0-9]+\.)+[0-9]+\.?$/.test(g.text) ? g.text.replace(/([0-9]+|\.$)/g, "").length + 1 : (i = b || parseInt(h(f, d.getAttribute("class"))), j = e(c.getNode(), d.getNode()), i ? a ? j += a : 0 === j && (a = i, j += i) : i = 48, b = i = Math.min(j, i), k = Math.max(1, Math.floor(j / i)) || 1)
            };
            return {guessIndentLevel: c}
        }, j = function () {
            var b = !1, c = "", d = function (d) {
                return b && d.type() === a.TEXT_TYPE ? (c += d.text(), !0) : d.type() === a.START_ELEMENT_TYPE && "STYLE" === d.tag() ? (b = !0, !0) : d.type() === a.END_ELEMENT_TYPE && "STYLE" === d.tag() && (b = !1, !0)
            };
            return {check: d}
        };
        return {isListWithoutCommentsOrStyles: d, indentGuesser: i, styles: j}
    }),g("40", ["3w", "42"], function (a, b) {
        var c = ["disc", "circle", "square"], d = function (a, b) {
            return "UL" === a.tag && c[b - 1] === a.type && (a = {tag: "UL"}), a
        };
        return function (c, e) {
            var f, g = [], h = [], i = 0, j = function (b, d) {
                var h = {}, j = {};
                i++, d && b.type && (h = {"list-style-type": b.type}), b.start && b.start > 1 && (j = {start: b.start}), g.push(b), c.emit(a.createStartElement(b.tag, j, h, e)), f = b
            }, k = function () {
                c.emit(a.createEndElement(g.pop().tag, e)), i--, f = g[g.length - 1]
            }, l = function () {
                for (; i > 0;) m(), k();
                c.commit()
            }, m = function () {
                var b = h ? h.pop() : "P";
                "P" != b && c.emit(a.createEndElement(b, e)), c.emit(a.createEndElement("LI", e))
            }, n = function (d, g, i) {
                var l = {};
                if (d) {
                    var m = d.getStyle("margin-left");
                    void 0 !== m && (l["margin-left"] = m)
                } else l["list-style-type"] = "none";
                f && !b.eqListType(f, g) && (k(), i && (c.emit(a.createStartElement("P", {}, {}, e)), c.emit(a.createText("\xa0", e)), c.emit(a.createEndElement("P", e))), j(g, !0)), c.emit(a.createStartElement("LI", {}, l, e)), d && "P" != d.tag() ? (h.push(d.tag()), d.filterStyles(function () {
                    return null
                }), c.emit(d)) : h.push("P")
            }, o = function (b, f, g, l) {
                if (g) {
                    for (i || (i = 0); i > b;) m(), k();
                    if (g = d(g, b), i == b) m(), n(f, g, l); else for (b > 1 && h.length > 0 && "P" !== h[h.length - 1] && (c.emit(a.createEndElement(h[h.length - 1], e)), h[h.length - 1] = "P"); i < b;) j(g, i == b - 1), n(i == b ? f : void 0, g)
                }
            }, p = function () {
                return i
            }, q = function () {
                return f
            };
            return {
                openList: j,
                closelist: k,
                closeAllLists: l,
                closeItem: m,
                openLI: n,
                openItem: o,
                getCurrentListType: q,
                getCurrentLevel: p
            }
        }
    }),g("41", ["3y", "3w", "3z", "42", "1h"], function (a, b, c, d, e) {
        var f = function (a, b) {
            e.log("Unexpected token in list conversion: " + b.toString()), a.rollback()
        }, g = function (a, b, c) {
            return b == c ? a : null
        }, h = function (c, d, f) {
            f.type() === b.TEXT_TYPE && "" === e.trim(f.text()) ? c.defer(f) : d.skippedPara || f.type() !== b.START_ELEMENT_TYPE || "P" !== f.tag() || a.hasMsoListStyle(f) ? j(c, d, f) : (d.openedTag = f, c.defer(f), d.nextFilter = i)
        }, i = function (d, e, f) {
            f.type() !== b.START_ELEMENT_TYPE || "SPAN" !== f.tag() || 0 !== e.spanCount.length || !a.supportsCustomStyles && c.isListWithoutCommentsOrStyles(f, e) || a.hasMsoListStyle(f) ? f.type() === b.END_ELEMENT_TYPE ? "SPAN" === f.tag() ? (d.defer(f), e.spanCount.pop()) : "P" === f.tag() ? (d.defer(f), e.skippedPara = !0, e.openedTag = null, e.nextFilter = h) : (e.nextFilter = j, e.nextFilter(d, e, f)) : f.isWhitespace() ? d.defer(f) : (e.nextFilter = j, e.nextFilter(d, e, f)) : (d.defer(f), e.spanCount.push(f))
        }, j = function (d, e, f) {
            var g = function () {
                e.emitter.closeAllLists(), d.emitDeferred(), e.openedTag = null, d.emit(f), e.nextFilter = j
            };
            if (f.type() === b.START_ELEMENT_TYPE && a.hasMsoListStyle(f) && "LI" !== f.tag()) {
                var h = (f.getStyle("mso-list"), / level([0-9]+)/.exec(f.getStyle("mso-list")));
                h && h[1] ? (e.itemLevel = parseInt(h[1], 10) + e.styleLevelAdjust, e.nextFilter === j ? d.emitDeferred() : d.dropDeferred(), e.nextFilter = l, d.startTransaction(), e.originalToken = f, e.commentMode = !1) : g()
            } else !a.supportsCustomStyles && (f.type() === b.COMMENT_TYPE && "[if !supportLists]" === f.text() || c.isListWithoutCommentsOrStyles(f, d)) ? (f.type() === b.START_ELEMENT_TYPE && "SPAN" === f.tag() && e.spanCount.push(f), e.nextFilter = l, d.startTransaction(), e.originalToken = e.openedTag, e.commentMode = !0, e.openedTag = null, d.dropDeferred()) : f.type() === b.END_ELEMENT_TYPE && a.spanOrA(f) ? (d.defer(f), e.spanCount.pop()) : f.type() === b.START_ELEMENT_TYPE ? a.spanOrA(f) ? (d.defer(f), e.spanCount.push(f)) : (e.openedTag && (e.emitter.closeAllLists(), d.emitDeferred()), e.openedTag = f, d.defer(f)) : g()
        }, k = function (a, c, d) {
            d.type() === b.END_ELEMENT_TYPE && c.originalToken.tag() === d.tag() && (c.nextFilter = h, c.styleLevelAdjust = -1), a.emit(d)
        }, l = function (a, c, d) {
            if (d.type() == b.START_ELEMENT_TYPE && "Ignore" === d.getStyle("mso-list") && (c.nextFilter = m), d.type() === b.START_ELEMENT_TYPE && "SPAN" === d.tag()) c.spanCount.push(d), (c.commentMode && "" === d.getAttribute("style") || null === d.getAttribute("style")) && (c.nextFilter = m); else if ("A" === d.tag()) d.type() === b.START_ELEMENT_TYPE ? c.spanCount.push(d) : c.spanCount.pop(); else if (d.type() === b.TEXT_TYPE) if (c.commentMode) c.nextFilter = m, c.nextFilter(a, c, d); else {
                var g = c.originalToken, h = c.spanCount;
                c.emitter.closeAllLists(), a.emit(g), e.each(h, e.bind(a.emit, a)), a.emit(d), a.commit(), c.originalToken = g, c.nextFilter = k
            } else (c.commentMode || d.type() !== b.COMMENT_TYPE) && f(a, d)
        }, m = function (c, d, e) {
            e.type() === b.TEXT_TYPE ? e.isWhitespace() || (d.nextFilter = n, d.bulletInfo = {
                text: e.text(),
                symbolFont: d.symbolFont
            }) : a.spanOrA(e) ? e.type() === b.START_ELEMENT_TYPE ? d.spanCount.push(e) : d.spanCount.pop() : e.type() === b.START_ELEMENT_TYPE && "IMG" === e.tag() ? (d.nextFilter = n, d.bulletInfo = {
                text: "\u2202",
                symbolFont: !0
            }) : f(c, e)
        }, n = function (c, d, e) {
            e.type() === b.START_ELEMENT_TYPE && a.spanOrA(e) ? (d.spanCount.push(e), d.nextFilter = o) : e.type() === b.END_ELEMENT_TYPE && a.spanOrA(e) ? (d.spanCount.pop(), d.nextFilter = p) : e.type() === b.END_ELEMENT_TYPE && "IMG" === e.tag() || f(c, e)
        }, o = function (c, d, e) {
            e.type() === b.END_ELEMENT_TYPE && (a.spanOrA(e) && d.spanCount.pop(), d.nextFilter = p)
        }, p = function (c, h, i) {
            var j = function (a) {
                if (h.nextFilter = q, h.commentMode && (h.itemLevel = h.indentGuesser.guessIndentLevel(i, h.originalToken, h.styles.styles, h.bulletInfo)), h.listType = d.guessListType(h.bulletInfo, g(h.emitter.getCurrentListType(), h.emitter.getCurrentLevel(), h.itemLevel), h.originalToken), h.listType) {
                    for (h.emitter.openItem(h.itemLevel, h.originalToken, h.listType, h.skippedPara), c.emitDeferred(); h.spanCount.length > 0;) c.emit(h.spanCount.shift());
                    a && c.emit(i)
                } else e.log("Unknown list type: " + h.bulletInfo.text + " Symbol font? " + h.bulletInfo.symbolFont), c.rollback()
            };
            i.type() === b.TEXT_TYPE || i.type() === b.START_ELEMENT_TYPE ? j(!0) : i.type() === b.COMMENT_TYPE ? j("[endif]" !== i.text()) : i.type() === b.END_ELEMENT_TYPE ? a.spanOrA(i) && h.spanCount.pop() : f(c, i)
        }, q = function (a, c, d) {
            d.type() === b.END_ELEMENT_TYPE && d.tag() === c.originalToken.tag() ? (c.nextFilter = h, c.skippedPara = !1) : a.emit(d)
        }, r = j;
        return {initial: r}
    }),g("2p", ["3x", "3y", "3w", "3z", "40", "41", "42", "1h"], function (a, b, c, d, e, f, g, h) {
        var i = {}, j = function (a) {
            i.nextFilter = f.initial, i.itemLevel = 0, i.originalToken = null, i.commentMode = !1, i.openedTag = null, i.symbolFont = !1, i.listType = null, i.indentGuesser = d.indentGuesser(), i.emitter = e(a, a.document), i.styles = d.styles(), i.spanCount = [], i.skippedPara = !1, i.styleLevelAdjust = 0, i.bulletInfo = void 0
        };
        j({});
        var k = function (a) {
            j(a)
        }, l = function (a, b) {
            i.styles.check(b) || (i.symbolFont = g.checkFont(b, i.symbolFont), i.nextFilter(a, i, b))
        };
        return a.createFilter(l, k)
    }),g("2q", ["1h"], function (a) {
        var b = function (a) {
            var b = a, c = 65279 === b.charCodeAt(b.length - 1);
            return c ? b.substring(0, b.length - 1) : a
        }, c = function (a) {
            return /<(h[1-6r]|p|div|address|pre|form|table|tbody|thead|tfoot|th|tr|td|li|ol|ul|caption|blockquote|center|dl|dt|dd|dir|fieldset)/.test(a) ? a.replace(/(?:<br>&nbsp;[\s\r\n]+|<br>)*(<\/?(h[1-6r]|p|div|address|pre|form|table|tbody|thead|tfoot|th|tr|td|li|ol|ul|caption|blockquote|center|dl|dt|dd|dir|fieldset)[^>]*>)(?:<br>&nbsp;[\s\r\n]+|<br>)*/g, "$1") : a
        }, d = function (a) {
            return a.replace(/<br><br>/g, "<BR><BR>")
        }, e = function (a) {
            return a.replace(/<br>/g, " ")
        }, f = function (a) {
            return a.replace(/<BR><BR>/g, "<br>")
        }, g = [b], h = tinymce.isIE && document.documentMode >= 9 ? [f, e, d, c].concat(g) : g, i = a.compose(h);
        return {all: i, textOnly: b}
    }),g("43", ["3x"], function (a) {
        var b = /^(mso-.*|tab-stops|tab-interval|language|text-underline|text-effect|text-line-through|font-color|horiz-align|list-image-[0-9]+|separator-image|table-border-color-(dark|light)|vert-align|vnd\..*)$/,
            c = function (a) {
                return function (c, d) {
                    var e = !1;
                    switch (a) {
                        case"all":
                        case"*":
                            e = !0;
                            break;
                        case"valid":
                            e = !b.test(c);
                            break;
                        case void 0:
                        case"none":
                            e = "list-style-type" === c;
                            break;
                        default:
                            e = ("," + a + ",").indexOf("," + c + ",") >= 0
                    }
                    return e ? d : null
                }
            };
        return a.createFilter(function (a, b) {
            var d = a.settings.get("retain_style_properties");
            b.filterStyles(c(d)), a.emit(b)
        })
    }),g("44", ["3x", "3w"], function (a, b) {
        return a.createFilter(function (a, c) {
            a.seenList ? a.emit(c) : a.inferring ? ("LI" === c.tag() && (c.type() === b.START_ELEMENT_TYPE ? a.inferring++ : (a.inferring--, a.inferring || (a.needsClosing = !0))), a.emit(c)) : ("OL" === c.tag() || "UL" === c.tag() ? a.seenList = !0 : "LI" === c.tag() && (a.inferring = 1, a.needsClosing || a.emit(b.createStartElement("UL", {}, {}, a.document))), !a.needsClosing || a.inferring || c.isWhitespace() || (a.needsClosing = !1, a.emit(b.createEndElement("UL", a.document))), a.emit(c))
        })
    }),g("45", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return "name" === a || "id" === a ? null : b
        })
    }),g("46", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            var c;
            if ("class" === a) switch (c = this.settings.get("strip_class_attributes")) {
                case"mso":
                    return 0 === b.indexOf("Mso") ? null : b;
                case"none":
                    return b;
                default:
                    return null
            }
            return b
        })
    }),g("47", ["3x", "3y", "3w"], function (a, b, c) {
        var d = [], e = [], f = !1, g = function (a, b) {
            var e, f, g = 1;
            for (e = b + 1; e < a; e++) if (f = d[e], f && "SPAN" === f.tag()) if (f.type() === c.START_ELEMENT_TYPE) g++; else if (f.type() === c.END_ELEMENT_TYPE && (g--, 0 === g)) return void (d[e] = null)
        }, h = function (a) {
            if (f) {
                var h, i, j = d.length;
                for (i = 0; i < j; i++) h = d[i], h && (h.type() === c.START_ELEMENT_TYPE && "SPAN" === h.tag() && b.hasNoAttributes(h) ? g(j, i) : a.emit(h))
            }
            d = [], e = [], f = !1
        }, i = function (a, b) {
            if (d.push(b), e = e || [], b.type() === c.START_ELEMENT_TYPE) e.push(b); else if (b.type() === c.END_ELEMENT_TYPE && (e.pop(), 0 === e.length)) return void h(a, b)
        };
        return a.createFilter(function (a, e) {
            var g = ",FONT,EM,STRONG,SAMP,ACRONYM,CITE,CODE,DFN,KBD,TT,B,I,U,S,SUB,SUP,INS,DEL,VAR,SPAN,";
            d = d || [];
            var h = function (a) {
                return !(g.indexOf("," + a.tag() + ",") >= 0 && b.hasNoAttributes(a, !0))
            };
            0 === d.length ? e.type() === c.START_ELEMENT_TYPE ? h(e) ? a.emit(e) : i(a, e) : a.emit(e) : (f || (f = h(e)), i(a, e))
        })
    }),g("48", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return "style" === a && "" === b ? null : b
        })
    }),g("49", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return "lang" === a ? null : b
        })
    }),g("4a", ["3x", "3w"], function (a, b) {
        return a.createFilter(function (a, c) {
            if ("IMG" === c.tag()) {
                if (c.type() === b.END_ELEMENT_TYPE && a.skipEnd) return void (a.skipEnd = !1);
                if (c.type() === b.START_ELEMENT_TYPE) {
                    if (/^file:/.test(c.getAttribute("src"))) return void (a.skipEnd = !0);
                    if (a.settings.get("base_64_images") && /^data:image\/.*;base64/.test(c.getAttribute("src"))) return void (a.skipEnd = !0)
                }
            }
            a.emit(c)
        })
    }),g("4b", ["3x"], function (a) {
        return a.createFilter(function (a, b) {
            "META" !== b.tag() && "LINK" !== b.tag() && a.emit(b)
        })
    }),g("4c", ["3x", "3y", "3w"], function (a, b, c) {
        var d = function (a) {
            return !b.hasNoAttributes(a) && !/^OLE_LINK/.test(a.getAttribute("name"))
        }, e = [];
        return a.createFilter(function (a, b) {
            var f;
            b.type() === c.START_ELEMENT_TYPE && "A" === b.tag() ? (e.push(b), d(b) && a.defer(b)) : b.type() === c.END_ELEMENT_TYPE && "A" === b.tag() ? (f = e.pop(), d(f) && a.defer(b), 0 === e.length && a.emitDeferred()) : a.hasDeferred() ? a.defer(b) : a.emit(b)
        })
    }),g("4d", ["3x", "3w"], function (a, b) {
        var c = !1;
        return a.createFilter(function (a, d) {
            "SCRIPT" === d.tag() ? c = d.type() === b.START_ELEMENT_TYPE : c || (d.filterAttributes(function (a, b) {
                return /^on/.test(a) || "language" === a ? null : b
            }), a.emit(d))
        })
    }),g("2r", ["43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d"], function (a, b, c, d, e, f, g, h, i, j, k) {
        return [k, c, h, a, g, f, d, j, e, i, b]
    }),g("4e", ["3x"], function (a) {
        return a.createFilter(function (a, b) {
            b.filterAttributes(function (a, c) {
                return "align" === a ? null : "UL" !== b.tag() && "OL" !== b.tag() || "type" !== a ? c : null
            }), a.emit(b)
        })
    }),g("4f", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return /^xmlns(:|$)/.test(a) ? null : b
        })
    }),g("4g", ["3x"], function (a) {
        return a.createFilter(function (a, b) {
            b.tag && /^([OVWXP]|U[0-9]+|ST[0-9]+):/.test(b.tag()) || a.emit(b)
        })
    }),g("4h", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return "href" === a && (b.indexOf("#_Toc") >= 0 || b.indexOf("#_mso") >= 0) ? null : b
        })
    }),g("4i", ["3x"], function (a) {
        return a.createAttributeFilter(function (a, b) {
            return /^v:/.test(a) ? null : b
        })
    }),g("2s", ["4e", "4f", "4g", "4h", "4i", "2p"], function (a, b, c, d, e, f) {
        return [c, f, d, e, b, a]
    }),g("1k", ["2m", "2n", "2o", "2p", "2q", "2r", "2s"], function (a, b, c, d, e, f, g) {
        var h = function (a, b, c, d) {
            var e, f = b;
            for (e = a.length - 1; e >= 0; e--) f = a[e](f, c, d);
            return f
        }, i = function (c, d, e, f) {
            var g = a.create(e), i = b.tokenize(c, e);
            for (pipeline = h(f, g, d, e); i.hasNext();) pipeline.receive(i.next());
            return g.dom
        }, j = function (a, b, c) {
            var d = e.all(a), h = l(d);
            b.setWordContent(h);
            var j = f;
            return h && (j = g.concat(f)), i(d, b, c, j)
        }, k = function (a, b, d) {
            var f = e.textOnly(a);
            return i(f, b, d, [c])
        }, l = function (a) {
            return a.indexOf("<o:p>") >= 0 || a.indexOf("p.MsoNormal, li.MsoNormal, div.MsoNormal") >= 0 || a.indexOf("MsoListParagraphCxSpFirst") >= 0 || a.indexOf("<w:WordDocument>") >= 0
        };
        return {filter: j, filterPlainText: k, isWordContent: l}
    }),g("f", ["1i", "1j", "1k", "x"], function (a, b, c, d) {
        return function (e, f) {
            var g = function (g) {
                var h = function (d) {
                    var f = {content: g};
                    e.fire("PastePreProcess", f);
                    var h = b.create(d || e.settings.powerpaste_word_import, d || e.settings.powerpaste_html_import, !0),
                        i = c.filter(f.content, h, e.getDoc());
                    e.fire("PastePostProcess", i), e.undoManager.transact(function () {
                        a.insert(i, e)
                    })
                }, i = function (a) {
                    return "clean" === a || "merge" === a
                }, j = function () {
                    var a, b = function () {
                        a.close(), h("clean")
                    }, c = function () {
                        a.close(), h("merge")
                    }, g = [{text: f("cement.dialog.paste.clean"), onclick: b}, {
                        text: f("cement.dialog.paste.merge"),
                        onclick: c
                    }], i = {
                        title: f("cement.dialog.paste.title"),
                        spacing: 10,
                        padding: 10,
                        items: [{type: "container", html: f("cement.dialog.paste.instructions")}],
                        buttons: g
                    };
                    a = e.windowManager.open(i), d(function () {
                        a && a.getEl().focus()
                    }, 1)
                };
                c.isWordContent(g) && !i(e.settings.powerpaste_word_import) ? j() : i(e.settings.powerpaste_html_import) ? h() : j()
            };
            return {showDialog: g}
        }
    }),g("4", ["d", "e", "f"], function (a, b, c) {
        return function (d, e, f) {
            var g, h, i = this, j = c(d, a.translate), k = function (a) {
                return function (b) {
                    a(b)
                }
            };
            g = b.getOnPasteFunction(d, j.showDialog, e), d.on("paste", k(g)), h = b.getOnKeyDownFunction(d, j.showDialog, e), d.on("keydown", k(h)), d.addCommand("mceInsertClipboardContent", function (a, b) {
                j.showDialog(b.content || b)
            }), d.settings.paste_preprocess && d.on("PastePreProcess", function (a) {
                d.settings.paste_preprocess.call(i, i, a)
            })
        }
    }),g("1s", [], function () {
        var a = 0, b = function (b) {
            var c = new Date, d = c.getTime(), e = Math.floor(1e9 * Math.random());
            return a++, b + "_" + e + a + String(d)
        };
        return {generate: b}
    }),g("1n", ["g", "1s", "2t", "h", "o", "2u", "2b", "j"], function (a, b, c, d, e, f, g, h) {
        var i = c.detect(), j = function (a) {
            var b = g.createObjectURL(a);
            return k(a, b)
        }, k = function (a, c) {
            return e.nu(function (e) {
                var g = f();
                g.onload = function (f) {
                    var g = b.generate("image"), h = f.target, i = d.blob(g, a, c, h);
                    e(i)
                }, g.readAsDataURL(a)
            })
        }, l = function (a) {
            return 0 === a.length ? e.pure([]) : e.mapM(a, j)
        }, m = function (a) {
            return a.raw().target.files || a.raw().dataTransfer.files
        }, n = function (b) {
            return 1 === b.length && a.contains(b, "Files")
        }, o = function (b) {
            return !a.contains(b, "text/_moz_htmlcontext")
        }, p = function (b) {
            return a.contains(b, "Files")
        }, q = function (a) {
            return !0
        }, r = function () {
            return i.browser.isChrome() || i.browser.isSafari() || i.browser.isOpera() ? p : i.browser.isFirefox() ? o : i.browser.isIE() ? n : q
        }, s = r(), t = function (c) {
            var f = a.map(c, function (a) {
                var c = b.generate("image");
                return d.url(c, h.get(a, "src"), a)
            });
            return e.pure(f)
        };
        return {multiple: l, toFiles: m, isFiles: s, fromImages: t, single: j, singleWithUrl: k}
    }),g("i", ["1n"], function (a) {
        var b = function (b) {
            return a.multiple(b)
        }, c = function (b) {
            return a.single(b)
        }, d = function (b, c) {
            return a.singleWithUrl(b, c)
        };
        return {multiple: b, single: c, singleWithUrl: d}
    }),g("5", ["g", "h", "i", "j", "k", "d", "f", "2"], function (a, b, c, d, e, f, g, h) {
        return function (i, j, k, l) {
            var m, n = /^image\/(jpe?g|png|gif|bmp)$/i;
            i.on("dragstart dragend", function (a) {
                m = "dragstart" === a.type
            }), i.on("dragover dragend dragleave", function (a) {
                a.preventDefault()
            });
            var o = function (a) {
                var b = {};
                if (a) {
                    if (a.getData) {
                        var c = a.getData("Text");
                        c && c.length > 0 && (b["text/plain"] = c)
                    }
                    if (a.types) for (var d = 0; d < a.types.length; d++) {
                        var e = a.types[d];
                        b[e] = a.getData(e)
                    }
                }
                return b
            }, p = function (a, b) {
                return b in a && a[b].length > 0
            }, q = function (a) {
                return !r(a) && (p(a, "text/html") || p(a, "text/plain"))
            }, r = function (a) {
                var b = a["text/plain"];
                return !!b && 0 === b.indexOf("file://")
            }, s = function (b) {
                var c = b.target.files || b.dataTransfer.files;
                return a.filter(c, function (a) {
                    return n.test(a.type)
                })
            }, t = function (c) {
                return a.map(c, function (a) {
                    var c = e.fromTag("img"), f = b.cata(a, l.getLocalURL, function (a, b, c) {
                        return b
                    });
                    return d.set(c, "src", f), c.dom().outerHTML
                }).join("")
            }, u = function (a) {
                c.multiple(a).get(function (a) {
                    var b = t(a);
                    i.insertContent(b, {merge: i.settings.paste_merge_formats !== !1}), l.uploadImages(a)
                })
            };
            i.on("drop", function (a) {
                if (!m) {
                    if (h.dom.RangeUtils && h.dom.RangeUtils.getCaretRangeFromPoint) {
                        var b = h.dom.RangeUtils.getCaretRangeFromPoint(a.clientX, a.clientY, i.getDoc());
                        b && i.selection.setRng(b)
                    }
                    var c = s(a);
                    if (c.length > 0) return u(c), void a.preventDefault();
                    var d = o(a.dataTransfer);
                    if (q(d)) {
                        var e = g(i, f.translate);
                        e.showDialog(d["text/html"] || d["text/plain"]), a.preventDefault()
                    }
                }
            })
        }
    }),g("4r", ["g", "2c", "1f"], function (a, b, c) {
        var d = ["officeStyles", "htmlStyles", "isWord", "proxyBin", "isInternal", "backgroundAssets"],
            e = function (b, c) {
                var e = {};
                return a.each(d, function (a) {
                    var d = c[a]().or(b[a]());
                    d.each(function (b) {
                        e[a] = b
                    })
                }), f(e)
            }, f = b.immutableBag([], d);
        return {nu: f, merge: e}
    }),g("2w", ["n", "1m"], function (a, b) {
        var c = b.generate([{error: ["message"]}, {paste: ["elements", "assets", "correlated"]}, {cancel: []}, {incomplete: ["elements", "assets", "correlated", "message"]}]),
            d = function (a, b, c, d, e) {
                return a.fold(b, c, d, e)
            }, e = function (b, e) {
                return d(b, a.none, a.none, a.none, function (b, f, g, h) {
                    return d(e, a.none, function (b, d, e) {
                        return a.some(c.incomplete(b, d, e, h))
                    }, a.none, a.none)
                }).getOr(e)
            };
        return {error: c.error, paste: c.paste, cancel: c.cancel, incomplete: c.incomplete, cata: d, carry: e}
    }),g("4q", ["4r", "2w", "1v", "2c"], function (a, b, c, d) {
        var e = d.immutableBag(["response", "bundle"], []), f = function (a) {
            return l(function (b) {
                var c = e(a);
                b(c)
            })
        }, g = function (a, b) {
            a(e(b))
        }, h = function (a) {
            return f({response: a.response(), bundle: a.bundle()})
        }, i = function (c) {
            return f({response: b.error(c), bundle: a.nu({})})
        }, j = function () {
            return f({response: b.cancel(), bundle: a.nu({})})
        }, k = function () {
            return f({response: b.paste([], [], []), bundle: a.nu({})})
        }, l = function (a) {
            var b = function (b) {
                a(b)
            };
            return c(l, b)
        };
        return {call: g, sync: l, pure: f, pass: h, done: e, error: i, initial: k, cancel: j}
    }),g("23", ["n"], function (a) {
        var b = function (a) {
            for (var b = [], c = function (a) {
                b.push(a)
            }, d = 0; d < a.length; d++) a[d].each(c);
            return b
        }, c = function (b, c) {
            for (var d = 0; d < b.length; d++) {
                var e = c(b[d], d);
                if (e.isSome()) return e
            }
            return a.none()
        }, d = function (b, c) {
            for (var d = [], e = 0; e < b.length; e++) {
                var f = b[e];
                if (!f.isSome()) return a.none();
                d.push(f.getOrDie())
            }
            return a.some(c.apply(null, d))
        };
        return {cat: b, findMap: c, liftN: d}
    }),g("2v", ["4q", "4r", "2w", "g", "p", "23", "2c"], function (a, b, c, d, e, f, g) {
        var h = g.immutable("steps", "input", "label", "capture"), i = function (a, b) {
            return f.findMap(a, function (a) {
                return a.getAvailable(b).map(function (b) {
                    return h(a.steps(), b, a.label(), a.capture())
                })
            })
        }, j = function (a, b, c) {
            var d = i(a, c);
            return d.getOrThunk(function () {
                var a = b.getAvailable(c);
                return h(b.steps(), a, b.label(), b.capture())
            })
        }, k = function (d, f) {
            var g = e.curry(a.pass, d), h = function () {
                return f().map(function (e) {
                    var f = b.merge(d.bundle(), e.bundle()), g = c.carry(d.response(), e.response());
                    return a.done({response: g, bundle: f})
                })
            };
            return c.cata(d.response(), g, h, g, h)
        }, l = function (b, c) {
            var e = d.foldl(b, function (a, b) {
                return a.bind(function (a) {
                    var d = function () {
                        return b(c, a)
                    };
                    return k(a, d)
                })
            }, a.initial());
            return e.map(function (a) {
                return a.response()
            })
        };
        return {choose: j, run: l}
    }),g("4s", [], function () {
        var a = function () {
            var a = !1, b = function () {
                return a
            }, c = function () {
                a = !0
            }, d = function () {
                a = !1
            };
            return {isBlocked: b, block: c, unblock: d}
        };
        return {create: a}
    }),g("4t", [], function () {
        var a = function (a, b) {
            return {control: a, instance: b}
        };
        return {create: a}
    }),g("2x", ["4s", "4t"], function (a, b) {
        var c = function (c) {
            var d = a.create(), e = function () {
                d.isBlocked() || c.apply(null, arguments)
            };
            return b.create(d, e)
        };
        return {tap: c}
    }),g("2y", ["2t", "p", "x"], function (a, b, c) {
        var d = a.detect(), e = function (a, b, c) {
            b.control.block(), a.dom().execCommand("paste"), c(), b.control.unblock()
        }, f = function (a, b, d) {
            c(d, 1)
        }, g = d.browser.isIE() && d.browser.version.major <= 10, h = g ? e : f, i = function (a, b, c) {
            return h(a, b, c)
        };
        return {willBlock: b.constant(g), run: i}
    }),g("1o", ["2v", "2w", "g", "p", "2x", "21", "22", "2y"], function (a, b, c, d, e, f, g, h) {
        return function (i, j) {
            var k = g.create({cancel: f([]), error: f(["message"]), insert: f(["elements", "assets", "correlated"])}),
                l = e.tap(function (d) {
                    h.willBlock() && (l.control.block(), d.preventDefault());
                    var e = a.choose(i, j, d);
                    e.capture() && d.preventDefault();
                    var f = c.map(e.steps(), function (a) {
                        return a(l.control)
                    }), g = a.run(f, e.input());
                    g.get(function (a) {
                        b.cata(a, function (a) {
                            k.trigger.error(a)
                        }, function (a, b, c) {
                            k.trigger.insert(a, b, c)
                        }, function () {
                            k.trigger.cancel()
                        }, function (a, b, c, d) {
                            k.trigger.insert(a, b, c), k.trigger.error(d)
                        })
                    })
                });
            return {paste: l.instance, isBlocked: l.control.isBlocked, destroy: d.noop, events: k.registry}
        }
    }),g("2z", ["p"], function (a) {
        var b = function (a) {
            return function (b) {
                return function (c, d, e) {
                    return b.block(), a(c, d, e).map(function (a) {
                        return b.unblock(), a
                    })
                }
            }
        }, c = function (b) {
            return a.constant(b)
        };
        return {blocking: b, normal: c}
    }),g("4u", ["68", "i", "o", "2b", "n"], function (a, b, c, d, e) {
        var f = function (a) {
            return void 0 !== a.convertURL ? a.convertURL : void 0 !== a.msConvertURL ? a.msConvertURL : void 0
        }, g = function (g) {
            var h = a.resolve("window.clipboardData.files"), i = f(g);
            if (void 0 !== h && void 0 !== i && h.length > 0) {
                var j = c.mapM(h, function (a) {
                    var c = d.createObjectURL(a);
                    return i.apply(g, [a, "specified", c]), b.singleWithUrl(a, c)
                });
                return e.some(j)
            }
            return e.none()
        };
        return {convert: g}
    }),g("30", ["4u", "o", "p", "n"], function (a, b, c, d) {
        var e = function () {
            var c = d.none(), e = function (b) {
                c = a.convert(b)
            }, f = function (a) {
                return c.fold(function () {
                    return b.nu(function (a) {
                        a([])
                    })
                }, function (a) {
                    return a
                }).get(a)
            }, g = function () {
                c = d.none()
            };
            return {convert: e, listen: f, clear: g}
        }, f = function () {
            return {
                convert: d.none, listen: function (a) {
                    a([])
                }, clear: c.noop
            }
        };
        return {background: e, ignore: f}
    }),h("4v", RegExp),g("31", ["n", "23", "4v"], function (a, b, c) {
        var d = function (a) {
            return void 0 !== a && void 0 !== a.types && null !== a.types
        }, e = function (a, c) {
            return b.findMap(a, function (a) {
                return f(c, a)
            })
        }, f = function (d, e) {
            var f = new c(e, "i");
            return b.findMap(d, function (b) {
                return null !== b.match(f) ? a.some({type: b, flavor: e}) : a.none()
            })
        };
        return {isValidData: d, getPreferredFlavor: e, getFlavor: f}
    }),g("4x", ["1l", "1m"], function (a, b) {
        var c = b.generate([{none: []}, {error: ["message "]}, {blob: ["blob"]}]), d = function (a, b, c, d) {
            return a.fold(b, c, d)
        };
        return a.merge(c, {cata: d})
    }),g("78", ["3p"], function (a) {
        return function (b, c) {
            var d = a.getOrDie("Blob");
            return new d(b, c)
        }
    }),g("79", ["3p"], function (a) {
        return function (b) {
            var c = a.getOrDie("Uint8Array");
            return new c(b)
        }
    }),g("7a", ["3p"], function (a) {
        var b = function (b) {
            var c = a.getOrDie("requestAnimationFrame");
            c(b)
        }, c = function (b) {
            var c = a.getOrDie("atob");
            return c(b)
        };
        return {atob: c, requestAnimationFrame: b}
    }),g("6d", ["4x", "78", "79", "7a", "n", "2f", "37", "12", "62"], function (a, b, c, d, e, f, g, h, i) {
        var j = function (a, e) {
            for (var f = 1024, g = d.atob(a), j = g.length, k = i.ceil(j / f), l = new h(k), m = 0; m < k; ++m) {
                for (var n = m * f, o = i.min(n + f, j), p = new h(o - n), q = n, r = 0; q < o; ++r, ++q) p[r] = g[q].charCodeAt(0);
                l[m] = c(p)
            }
            return b(l, {type: e})
        }, k = function (a) {
            return g.startsWith(a, "data:image/") && a.indexOf(";base64,") > "data:image/".length
        }, l = function (b) {
            if (!k(b)) return a.none();
            var c = b.indexOf(";"), d = b.substr("data:".length, c - "data:".length),
                e = b.substr(c + ";base64,".length);
            try {
                var f = a.blob(j(e, d));
                return f
            } catch (g) {
                return a.error(g)
            }
        };
        return {convert: l}
    }),g("4w", ["6d"], function (a) {
        var b = function (b) {
            return a.convert(b)
        };
        return {toBlob: b}
    }),g("4y", ["2w", "g", "h", "p", "2c", "j", "k", "1d", "2a", "1f"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = e.immutable("asset", "image"), l = function (a, e) {
            var g = [];
            return b.each(a, function (a, b) {
                c.cata(a, function (c, d, h, i) {
                    var j = e[b];
                    f.set(j, "src", h), g.push(k(a, j))
                }, d.noop)
            }), g
        }, m = function (a, e) {
            var g = [], j = b.bind(a, function (a) {
                return "img" === h.name(a) ? [a] : i.descendants(a, "img")
            });
            return b.each(e, function (a) {
                c.cata(a, function (c, d, e, h) {
                    b.each(j, function (b) {
                        f.get(b, "src") === e && g.push(k(a, b))
                    })
                }, d.noop)
            }), g
        }, n = function (d) {
            var e = [], h = [], i = [];
            return b.each(d, function (a) {
                return c.cata(a, function (b, c, d, j) {
                    var l = g.fromTag("img");
                    f.set(l, "src", d), e.push(l), h.push(a), i.push(k(a, l))
                }, function (a, b, c) {
                    j.error("Internal error: Paste operation produced an image URL instead of a Data URI: ", b)
                })
            }), a.paste(e, h, i)
        };
        return {createImages: n, findImages: m, updateSources: l}
    }),g("1y", ["g", "29", "3f"], function (a, b, c) {
        var d = function (b) {
            b.dom().textContent = "", a.each(c.children(b), function (a) {
                e(a)
            })
        }, e = function (a) {
            var b = a.dom();
            null !== b.parentNode && b.parentNode.removeChild(b)
        }, f = function (a) {
            var d = c.children(a);
            d.length > 0 && b.before(a, d), e(a)
        };
        return {empty: d, remove: e, unwrap: f}
    }),g("32", ["4w", "4x", "4q", "4y", "2w", "g", "i", "n", "2c", "j", "k", "1d", "1y", "29", "2a"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o) {
        var p = i.immutable("blob", "image"), q = function (c, d) {
            var e = a.toBlob(d);
            return b.cata(e, h.none, h.none, function (a) {
                return h.some(p(a, c))
            })
        }, r = function (a) {
            var b = k.fromTag("div");
            return n.append(b, a), o.descendants(b, "img[src]")
        }, s = function (a) {
            return 0 === a.indexOf("data:") && a.indexOf("base64") > -1
        }, t = function (a) {
            return 0 === a.indexOf("blob:")
        }, u = function (a) {
            return s(a) || t(a)
        }, v = function (a) {
            var b = j.get(a, "src");
            return u(b)
        }, w = function (a) {
            return f.bind(r(a), function (a) {
                var b = j.get(a, "src");
                return u(b) ? q(a, b).toArray() : []
            })
        }, x = function (a) {
            var b = f.filter(a, function (a) {
                return "img" !== l.name(a) || !v(a)
            });
            return e.incomplete(b, [], [], "errors.local.images.disallowed")
        };
        return function (a) {
            return function (b, h) {
                return c.sync(function (b) {
                    var i = function () {
                        c.call(b, {response: h.response(), bundle: h.bundle()})
                    }, j = function (a) {
                        var i = w(a), j = f.map(i, function (a) {
                            return a.blob()
                        });
                        g.multiple(j).get(function (g) {
                            var j = f.map(i, function (a) {
                                return a.image()
                            }), k = d.updateSources(g, j);
                            c.call(b, {response: e.paste(a, g, k), bundle: h.bundle()})
                        })
                    }, k = function (a) {
                        var d = f.filter(r(a), v);
                        f.each(d, m.remove), c.call(b, {
                            response: d.length > 0 ? x(a) : h.response(),
                            bundle: h.bundle()
                        })
                    }, l = function (b, c, d, e) {
                        a.allowLocalImages === !1 ? k(b) : 0 === c.length ? j(b) : i()
                    };
                    e.cata(h.response(), i, l, i, l)
                })
            }
        }
    }),g("4z", ["4q", "2w", "o", "1f"], function (a, b, c, d) {
        var e = function (c) {
            var e = function (e, f) {
                return c.proxyBin().fold(function () {
                    return d.error(e), a.pure({response: b.cancel(), bundle: {}})
                }, f)
            };
            return {handle: e}
        }, f = function (a) {
            return c.nu(function (b) {
                a.backgroundAssets().fold(function () {
                    b([])
                }, function (a) {
                    a.listen(b)
                })
            })
        }, g = function (a) {
            var b = j(a);
            return b && h(a) || !b && i(a)
        }, h = function (a) {
            return a.officeStyles().getOr(!0)
        }, i = function (a) {
            return a.htmlStyles().getOr(!1)
        }, j = function (a) {
            return a.isWord().getOr(!1)
        }, k = function (a) {
            return a.isInternal().getOr(!1)
        };
        return {proxyBin: e, backgroundAssets: f, merging: g, mergeOffice: h, mergeNormal: i, isWord: j, isInternal: k}
    }),g("5c", ["3g"], function (a) {
        var b = a.create("ephox-cement");
        return {resolve: b.resolve}
    }),g("50", ["5c", "1l", "p", "n", "27", "k", "28", "1x", "29"], function (a, b, c, d, e, f, g, h, i) {
        return function (j, k) {
            var l = k.translations, m = function (a, c, e) {
                e(d.some(b.merge(c, {officeStyles: a, htmlStyles: a})))
            }, n = function (b, c) {
                var k = function () {
                    t(), m(!1, b, c)
                }, n = function () {
                    t(), m(!0, b, c)
                }, o = f.fromTag("div");
                e.add(o, a.resolve("styles-dialog-content"));
                var p = f.fromTag("p"), q = g.fromHtml(l("cement.dialog.paste.instructions"));
                i.append(p, q), h.append(o, p);
                var r = {
                    text: l("cement.dialog.paste.clean"),
                    tabindex: 0,
                    className: a.resolve("clean-styles"),
                    click: k
                }, s = {
                    text: l("cement.dialog.paste.merge"),
                    tabindex: 1,
                    className: a.resolve("merge-styles"),
                    click: n
                }, t = function () {
                    v.destroy()
                }, u = function () {
                    c(d.none()), t()
                }, v = j(!0);
                v.setTitle(l("cement.dialog.paste.title")), v.setContent(o), v.setButtons([r, s]), v.show(), v.events.close.bind(u)
            }, o = function (a, b) {
                var c = a ? "officeStyles" : "htmlStyles", d = k[c];
                "clean" === d ? m(!1, k, b) : "merge" === d ? m(!0, k, b) : n(k, b)
            };
            return {get: o, destroy: c.noop}
        }
    }),g("33", ["4q", "4r", "4z", "50", "2w"], function (a, b, c, d, e) {
        var f = function (f, g) {
            var h = d(f, g);
            return function (d, f) {
                var g = f.bundle(), i = f.response();
                return a.sync(function (d) {
                    h.get(c.isWord(g), function (c) {
                        var g = c.fold(function () {
                            return {response: e.cancel(), bundle: f.bundle()}
                        }, function (a) {
                            return {response: i, bundle: b.nu({officeStyles: a.officeStyles, htmlStyles: a.htmlStyles})}
                        });
                        a.call(d, g)
                    })
                })
            }
        }, g = function (d, e) {
            return function (g, h) {
                return c.isInternal(h.bundle()) ? a.pure({
                    response: h.response(),
                    bundle: b.nu({officeStyles: !0, htmlStyles: !0})
                }) : f(d, e)(g, h)
            }
        }, h = function (c, d) {
            return function (e, f) {
                return a.pure({response: f.response(), bundle: b.nu({officeStyles: c, htmlStyles: d})})
            }
        };
        return {fixed: h, fromConfig: f, fromConfigIfExternal: g}
    }),g("7r", ["2t", "p", "k", "1g"], function (a, b, c, d) {
        var e = function (a) {
                for (var b = []; null !== a.nextNode();) b.push(c.fromDom(a.currentNode));
                return b
            }, f = function (a) {
                try {
                    return e(a)
                } catch (b) {
                    return []
                }
            }, g = a.detect().browser, h = g.isIE() || g.isSpartan() ? f : e, i = b.constant(b.constant(!0)),
            j = function (a, b) {
                var c = b.fold(i, function (a) {
                    return function (b) {
                        return a(b.nodeValue)
                    }
                });
                c.acceptNode = c;
                var e = d.createTreeWalker(a.dom(), NodeFilter.SHOW_COMMENT, c, !1);
                return h(e)
            };
        return {find: j}
    }),g("7b", ["n", "7r", "37", "1g"], function (a, b, c, d) {
        var e = function (d) {
            return b.find(d, a.some(function (a) {
                return c.startsWith(a, "[if gte vml 1]")
            }))
        };
        return {find: e}
    }),g("6q", [], function () {
        var a = function (a) {
            return void 0 !== a.style
        };
        return {isSupported: a}
    }),h("11", window),g("5d", ["1b", "g", "1c", "n", "j", "5i", "k", "1d", "6q", "37", "1e", "1f", "11"], function (a, b, c, d, e, f, g, h, i, j, k, l, m) {
        var n = function (b, c, d) {
            if (!a.isString(d)) throw l.error("Invalid call to CSS.set. Property ", c, ":: Value ", d, ":: Element ", b), new k("CSS value must be a string: " + d);
            i.isSupported(b) && b.style.setProperty(c, d)
        }, o = function (a, b) {
            i.isSupported(a) && a.style.removeProperty(b)
        }, p = function (a, b, c) {
            var d = a.dom();
            n(d, b, c)
        }, q = function (a, b) {
            var d = a.dom();
            c.each(b, function (a, b) {
                n(d, b, a)
            })
        }, r = function (a, b) {
            var d = a.dom();
            c.each(b, function (a, b) {
                a.fold(function () {
                    o(d, b)
                }, function (a) {
                    n(d, b, a)
                })
            })
        }, s = function (a, b) {
            var c = a.dom(), d = m.getComputedStyle(c), e = d.getPropertyValue(b),
                g = "" !== e || f.inBody(a) ? e : t(c, b);
            return null === g ? void 0 : g
        }, t = function (a, b) {
            return i.isSupported(a) ? a.style.getPropertyValue(b) : ""
        }, u = function (a, b) {
            var c = a.dom(), e = t(c, b);
            return d.from(e).filter(function (a) {
                return a.length > 0
            })
        }, v = function (a, b, c) {
            var d = g.fromTag(a);
            p(d, b, c);
            var e = u(d, b);
            return e.isSome()
        }, w = function (a, b) {
            var c = a.dom();
            o(c, b), e.has(a, "style") && "" === j.trim(e.get(a, "style")) && e.remove(a, "style")
        }, x = function (a, b) {
            var c = e.get(a, "style"), d = b(a), f = void 0 === c ? e.remove : e.set;
            return f(a, "style", c), d
        }, y = function (a, b) {
            var c = a.dom(), d = b.dom();
            i.isSupported(c) && i.isSupported(d) && (d.style.cssText = c.style.cssText)
        }, z = function (a) {
            return a.dom().offsetWidth
        }, A = function (a, b, c) {
            u(a, c).each(function (a) {
                u(b, c).isNone() && p(b, c, a)
            })
        }, B = function (a, c, d) {
            h.isElement(a) && h.isElement(c) && b.each(d, function (b) {
                A(a, c, b)
            })
        };
        return {
            copy: y,
            set: p,
            preserve: x,
            setAll: q,
            setOptions: r,
            remove: w,
            get: s,
            getRaw: u,
            isValidValue: v,
            reflow: z,
            transfer: B
        }
    }),g("6r", ["1b", "g", "p", "n", "5i", "3n", "k", "2i"], function (a, b, c, d, e, f, g, h) {
        var i = function (a) {
            return n(e.body(), a)
        }, j = function (b, e, f) {
            for (var h = b.dom(), i = a.isFunction(f) ? f : c.constant(!1); h.parentNode;) {
                h = h.parentNode;
                var j = g.fromDom(h);
                if (e(j)) return d.some(j);
                if (i(j)) break
            }
            return d.none()
        }, k = function (a, b, c) {
            var d = function (a) {
                return b(a)
            };
            return h(d, j, a, b, c)
        }, l = function (a, b) {
            var c = a.dom();
            return c.parentNode ? m(g.fromDom(c.parentNode), function (c) {
                return !f.eq(a, c) && b(c)
            }) : d.none()
        }, m = function (a, e) {
            var f = b.find(a.dom().childNodes, c.compose(e, g.fromDom));
            return d.from(f).map(g.fromDom)
        }, n = function (a, b) {
            var c = function (a) {
                for (var e = 0; e < a.childNodes.length; e++) {
                    if (b(g.fromDom(a.childNodes[e]))) return d.some(g.fromDom(a.childNodes[e]));
                    var f = c(a.childNodes[e]);
                    if (f.isSome()) return f
                }
                return d.none()
            };
            return c(a.dom())
        };
        return {first: i, ancestor: j, closest: k, sibling: l, child: m, descendant: n}
    }),g("6s", ["j", "k", "1x", "29", "1y", "3f"], function (a, b, c, d, e, f) {
        var g = function (a, c) {
            return b.fromDom(a.dom().cloneNode(c))
        }, h = function (a) {
            return g(a, !1)
        }, i = function (a) {
            return g(a, !0)
        }, j = function (c, d) {
            var e = b.fromTag(d), f = a.clone(c);
            return a.setAll(e, f), e
        }, k = function (a, b) {
            var c = j(a, b), e = f.children(i(a));
            return d.append(c, e), c
        }, l = function (a, b) {
            var g = j(a, b);
            c.before(a, g);
            var h = f.children(a);
            return d.append(g, h), e.remove(a), g
        };
        return {shallow: h, shallowAs: j, deep: i, copy: k, mutate: l}
    }),g("5a", ["n", "j", "27", "5d", "k", "1d", "6r", "6s", "2a", "37"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = function (a, b) {
            var c = f.value(a), d = e.fromTag("div"), h = c.indexOf("]>");
            return d.dom().innerHTML = c.substr(h + "]>".length), g.descendant(d, function (a) {
                return f.name(a) === b
            })
        }, l = function (b) {
            return f.isComment(b) ? k(b, "v:shape") : a.none()
        }, m = function (a) {
            return l(a).map(function (a) {
                var f = b.get(a, "o:spid"), g = void 0 === f ? b.get(a, "id") : f, h = e.fromTag("img");
                return c.add(h, "rtf-data-image"), b.set(h, "data-image-id", g.substr("_x0000_".length)), b.set(h, "data-image-type", "code"), d.setAll(h, {
                    width: d.get(a, "width"),
                    height: d.get(a, "height")
                }), h
            })
        }, n = function (d) {
            if ("img" === f.name(d)) {
                var e = b.get(d, "src");
                if (void 0 !== e && null !== e && j.startsWith(e, "file://")) {
                    var g = h.shallow(d), i = e.split(/[\/\\]/), k = i[i.length - 1];
                    return b.set(g, "data-image-id", k), b.remove(g, "src"), b.set(g, "data-image-type", "local"), c.add(g, "rtf-data-image"), a.some(g)
                }
                return a.none()
            }
            return a.none()
        }, o = function (a) {
            return p(a).length > 0
        }, p = function (a) {
            return i.descendants(a, ".rtf-data-image")
        };
        return {local: n, vshape: m, find: p, exists: o, scour: l}
    }),g("6e", ["7b", "g", "5a", "n", "23", "2c", "j", "28", "2a", "1f"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = f.immutable("img", "vshape"), l = function (a) {
            var b = n(a);
            return b._rawElement = a.dom(), b
        }, m = function (a) {
            var b = n(a);
            return b._rawElement = a.dom(), b
        }, n = function (a) {
            return g.clone(a)
        }, o = function (d) {
            var f = h.fromHtml(d), g = b.bind(f, function (a) {
                return i.descendants(a, "img")
            }), j = b.bind(f, a.find), k = e.cat(b.map(j, c.scour)), l = b.map(g, function (a) {
                return p(a, k)
            });
            return e.cat(l)
        }, p = function (a, c) {
            var e = g.get(a, "v:shapes"), f = d.from(b.find(c, function (a) {
                return g.get(a, "id") === e
            }));
            return f.isNone() && j.log("WARNING: unable to find data for image", a.dom()), f.map(function (b) {
                return q(a, b)
            })
        }, q = function (a, b) {
            return k(l(a), m(b))
        };
        return {extract: o}
    }),g("7c", ["1b", "g", "p", "n", "j", "27"], function (a, b, c, d, e, f) {
        var g = function (b, c) {
            var d = c.style;
            if (e.has(b, "width") && e.has(b, "height") && a.isString(d)) {
                var f = d.match(/rotation:([^;]*)/);
                null === f || "90" !== f[1] && "-90" !== f[1] || e.setAll(b, {
                    width: e.get(b, "height"),
                    height: e.get(b, "width")
                })
            }
        }, h = function (a, b) {
            var c = b["o:spid"], d = void 0 === c ? b.id : c;
            g(a, b), f.add(a, "rtf-data-image"), e.set(a, "data-image-id", d.substr("_x0000_".length)), e.set(a, "data-image-type", "code")
        }, i = function (a, b, c) {
            return c.img()[a] === b
        }, j = function (a, f, g) {
            var h = e.get(f, g), j = c.curry(i, g, h), k = b.find(a, j);
            return d.from(k).map(function (a) {
                return e.remove(f, g), a
            })
        }, k = function (a, c, d) {
            b.each(c, function (b) {
                j(a, b, d).each(function (a) {
                    h(b, a.vshape())
                })
            })
        };
        return {rotateImage: g, insertRtfCorrelation: k}
    }),g("7s", ["2t", "n", "1e"], function (a, b, c) {
        return function (d, e) {
            var f = function (a) {
                if (!d(a)) throw new c("Can only get " + e + " value of a " + e + " node");
                return j(a).getOr("")
            }, g = function (a) {
                try {
                    return h(a)
                } catch (c) {
                    return b.none()
                }
            }, h = function (a) {
                return d(a) ? b.from(a.dom().nodeValue) : b.none()
            }, i = a.detect().browser, j = i.isIE() && 10 === i.version.major ? g : h, k = function (a, b) {
                if (!d(a)) throw new c("Can only set raw " + e + " value of a " + e + " node");
                a.dom().nodeValue = b
            };
            return {get: f, getOption: j, set: k}
        }
    }),g("7d", ["1d", "7s"], function (a, b) {
        var c = b(a.isComment, "comment"), d = function (a) {
            return c.get(a)
        }, e = function (a) {
            return c.getOption(a)
        }, f = function (a, b) {
            c.set(a, b)
        };
        return {get: d, getOption: e, set: f}
    }),g("7t", ["1x"], function (a) {
        var b = function (b, c, d) {
            b.dom().styleSheet ? b.dom().styleSheet.cssText = c : a.append(b, d)
        };
        return {setCss: b}
    }),g("8f", [], function () {
        var a = function (a) {
            return a.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&")
        };
        return {escape: a}
    }),g("7u", ["1c", "8f", "4v"], function (a, b, c) {
        var d = function (a, d, e) {
            var f = new c("url\\(\\s*['\"]?" + b.escape(d) + "(.*?)['\"]?\\s*\\)", "g");
            return a.replace(f, 'url("' + e + '$1")')
        }, e = function (b, c) {
            var e = b;
            return a.each(c, function (a, b) {
                e = d(e, b, a)
            }), e
        };
        return {replace: d, replaceMany: e}
    }),g("7e", ["j", "k", "1x", "20", "7t", "7u", "12"], function (a, b, c, d, e, f, g) {
        var h = function (c) {
            var d = b.fromTag("style", c.dom());
            return a.set(d, "type", "text/css"), d
        }, i = function (a, c, d) {
            e.setCss(a, c, b.fromText(c, d.dom()))
        }, j = function (a, b, e) {
            var g = h(e), j = void 0 === b ? a : f.replaceMany(a, b);
            i(g, j, e);
            var k = d.descendant(e, "head").getOrDie();
            c.append(k, g)
        }, k = function (a) {
            var b = a.dom().styleSheets;
            return g.prototype.slice.call(b)
        };
        return {stylesheets: k, inject: j}
    }),g("7v", ["g", "2c"], function (a, b) {
        var c = b.immutable("selector", "style", "raw"), d = function (b) {
            var d = b.cssRules;
            return a.map(d, function (a) {
                var b = a.selectorText, d = a.style.cssText;
                if (void 0 === d) throw"WARNING: Browser does not support cssText property";
                return c(b, d, a.style)
            })
        }, e = function (b) {
            return a.bind(b, d)
        };
        return {extract: d, extractAll: e}
    }),g("7f", ["7v"], function (a) {
        var b = function (b) {
            return a.extract(b)
        }, c = function (b) {
            return a.extractAll(b)
        };
        return {extract: b, extractAll: c}
    }),function (a, b, c) {
        a("7g", [], function () {
            var a = function () {
                var a, b, c;
                return a = function (a) {
                    var c, d, e, f, g = [];
                    for (c = a.split(","), e = 0, f = c.length; e < f; e += 1) d = c[e], d.length > 0 && g.push(b(d));
                    return g
                }, b = function (a) {
                    var b, c = a, d = {a: 0, b: 0, c: 0}, e = [], f = /(\[[^\]]+\])/g, g = /(#[^\s\+>~\.\[:]+)/g,
                        h = /(\.[^\s\+>~\.\[:]+)/g,
                        i = /(::[^\s\+>~\.\[:]+|:first-line|:first-letter|:before|:after)/gi,
                        j = /(:[\w-]+\([^\)]*\))/gi, k = /(:[^\s\+>~\.\[:]+)/g, l = /([^\s\+>~\.\[:]+)/g;
                    return b = function (b, f) {
                        var g, h, i, j, k, l;
                        if (b.test(c)) for (g = c.match(b), h = 0, i = g.length; h < i; h += 1) d[f] += 1, j = g[h], k = c.indexOf(j), l = j.length, e.push({
                            selector: a.substr(k, l),
                            type: f,
                            index: k,
                            length: l
                        }), c = c.replace(j, Array(l + 1).join(" "))
                    }, function () {
                        var a = function (a) {
                            var b, d, e, f;
                            if (a.test(c)) for (b = c.match(a), d = 0, e = b.length; d < e; d += 1) f = b[d], c = c.replace(f, Array(f.length + 1).join("A"))
                        }, b = /\\[0-9A-Fa-f]{6}\s?/g, d = /\\[0-9A-Fa-f]{1,5}\s/g, e = /\\./g;
                        a(b), a(d), a(e)
                    }(), function () {
                        var a = /:not\(([^\)]*)\)/g;
                        a.test(c) && (c = c.replace(a, "     $1 "))
                    }(), function () {
                        var a, b, d, e, f = new RegExp("{[^]*", "gm");
                        if (f.test(c)) for (a = c.match(f), b = 0, d = a.length; b < d; b += 1) e = a[b], c = c.replace(e, Array(e.length + 1).join(" "))
                    }(), b(f, "b"), b(g, "a"), b(h, "b"), b(i, "c"), b(j, "b"), b(k, "b"), c = c.replace(/[\*\s\+>~]/g, " "), c = c.replace(/[#\.]/g, " "), b(l, "c"), e.sort(function (a, b) {
                        return a.index - b.index
                    }), {
                        selector: a,
                        specificity: "0," + d.a.toString() + "," + d.b.toString() + "," + d.c.toString(),
                        specificityArray: [0, d.a, d.b, d.c],
                        parts: e
                    }
                }, c = function (a, c) {
                    var d, e, f;
                    if ("string" == typeof a) {
                        if (a.indexOf(",") !== -1) throw"Invalid CSS selector";
                        d = b(a).specificityArray
                    } else {
                        if (!Array.isArray(a)) throw"Invalid CSS selector or specificity array";
                        if (4 !== a.filter(function (a) {
                            return "number" == typeof a
                        }).length) throw"Invalid specificity array";
                        d = a
                    }
                    if ("string" == typeof c) {
                        if (c.indexOf(",") !== -1) throw"Invalid CSS selector";
                        e = b(c).specificityArray
                    } else {
                        if (!Array.isArray(c)) throw"Invalid CSS selector or specificity array";
                        if (4 !== c.filter(function (a) {
                            return "number" == typeof a
                        }).length) throw"Invalid specificity array";
                        e = c
                    }
                    for (f = 0; f < 4; f += 1) {
                        if (d[f] < e[f]) return -1;
                        if (d[f] > e[f]) return 1
                    }
                    return 0
                }, {calculate: a, compare: c}
            }();
            return "undefined" != typeof exports && (exports.calculate = a.calculate, exports.compare = a.compare), a
        })
    }(f.bolt.module.api.define, f.bolt.module.api.require, f.bolt.module.api.demand),g("6f", ["7c", "g", "p", "2c", "j", "7d", "5d", "1y", "2a", "3f", "7e", "7f", "7g"], function (a, b, c, d, e, f, g, h, i, j, k, l, m) {
        var n = d.immutable("selector", "raw"), o = function (b, c, d, e, f) {
            var g = i.descendants(c, "img");
            t(c), a.insertRtfCorrelation(d, g, e);
            var h = f.mergeInline() ? s : p;
            h(b, c)
        }, p = c.noop, q = function (a, c) {
            var d = {};
            return b.each(a, function (e) {
                if (void 0 !== a[e]) {
                    var f = c.dom().style;
                    b.contains(f, e) || (d[e] = a[e])
                }
            }), d
        }, r = function (a, c) {
            var d = b.bind(c, function (c) {
                var d = i.descendants(a, c.selector());
                return b.each(d, function (a) {
                    var b = q(c.raw(), a);
                    g.setAll(a, b)
                }), d
            });
            b.each(d, function (a) {
                e.remove(a, "class")
            })
        }, s = function (a, c) {
            var d = k.stylesheets(a), e = l.extractAll(d), f = function (a) {
                return a.selector().indexOf(",") !== -1
            }, g = function (a) {
                return !f(a)
            }, h = function (a) {
                var c = a.selector().split(",");
                return b.map(c, function (b) {
                    var c = b.trim();
                    return n(c, a.raw())
                })
            }, i = b.flatten(b.map(b.filter(e, f), h)), j = b.filter(e, g), o = j.concat(i);
            o.sort(function (a, b) {
                return m.compare(a.selector(), b.selector())
            }).reverse(), r(c, o)
        }, t = function (a) {
            var c = j.children(a);
            b.each(c, function (a) {
                f.getOption(a).each(function (b) {
                    "StartFragment" !== b && "EndFragment" !== b || h.remove(a)
                })
            })
        };
        return {doMergeInlineStyles: r, process: o}
    }),g("71", ["n", "k"], function (a, b) {
        var c = function (c) {
            var d = c.dom();
            try {
                var e = d.contentWindow ? d.contentWindow.document : d.contentDocument;
                return void 0 !== e && null !== e ? a.some(b.fromDom(e)) : a.none()
            } catch (f) {
                return console.log("Error reading iframe: ", d), console.log("Error was: " + f), a.none()
            }
        }, d = function (a) {
            var b = c(a);
            return b.fold(function () {
                return a
            }, function (a) {
                return a
            })
        };
        return {doc: d}
    }),g("5x", ["71", "5i"], function (a, b) {
        var c = function (c, d) {
            if (!b.inBody(c)) throw"Internal error: attempted to write to an iframe that is not in the DOM";
            var e = a.doc(c), f = e.dom();
            f.open(), f.writeln(d), f.close()
        };
        return {write: c}
    }),g("25", ["p", "k"], function (a, b) {
        var c = function (b, c, d, e, f, g, h) {
            return {
                target: a.constant(b),
                x: a.constant(c),
                y: a.constant(d),
                stop: e,
                prevent: f,
                kill: g,
                raw: a.constant(h)
            }
        }, d = function (d, e) {
            return function (f) {
                if (d(f)) {
                    var g = b.fromDom(f.target), h = function () {
                        f.stopPropagation()
                    }, i = function () {
                        f.preventDefault()
                    }, j = a.compose(i, h), k = c(g, f.clientX, f.clientY, h, i, j, f);
                    e(k)
                }
            }
        }, e = function (b, c, e, f, g) {
            var i = d(e, f);
            return b.dom().addEventListener(c, i, g), {unbind: a.curry(h, b, c, i, g)}
        }, f = function (a, b, c, d) {
            return e(a, b, c, d, !1)
        }, g = function (a, b, c, d) {
            return e(a, b, c, d, !0)
        }, h = function (a, b, c, d) {
            a.dom().removeEventListener(b, c, d)
        };
        return {bind: f, capture: g}
    }),g("w", ["p", "25"], function (a, b) {
        var c = a.constant(!0), d = function (a, d, e) {
            return b.bind(a, d, c, e)
        }, e = function (a, d, e) {
            return b.capture(a, d, c, e)
        };
        return {bind: d, capture: e}
    }),g("6g", ["p", "5x", "5d", "w", "k", "1x", "1y", "x"], function (a, b, c, d, e, f, g, h) {
        return function (i) {
            var j = function (j, k, l) {
                var m = e.fromTag("div"), n = e.fromTag("iframe");
                c.setAll(m, {display: "none"});
                var o = d.bind(n, "load", function () {
                    o.unbind(), b.write(n, j);
                    var c = n.dom().contentWindow.document;
                    if (void 0 === c) throw"sandbox iframe load event did not fire correctly";
                    var d = e.fromDom(c), f = c.body;
                    if (void 0 === f) throw"sandbox iframe does not have a body";
                    var i = e.fromDom(f), p = k(d, i);
                    g.remove(m), h(a.curry(l, p), 0)
                });
                f.append(m, n), f.append(i, m)
            };
            return {play: j}
        }
    }),g("6h", ["k", "28", "1x", "29", "1y", "3f"], function (a, b, c, d, e, f) {
        var g = function (a) {
            return a.dom().innerHTML
        }, h = function (g, h) {
            var i = f.owner(g), j = i.dom(), k = a.fromDom(j.createDocumentFragment()), l = b.fromHtml(h, j);
            d.append(k, l), e.empty(g), c.append(g, k)
        }, i = function (b) {
            var d = a.fromTag("div"), e = a.fromDom(b.dom().cloneNode(!0));
            return c.append(d, e), g(d)
        };
        return {get: g, set: h, getOuter: i}
    }),g("51", ["4q", "4r", "6e", "6f", "2w", "p", "6g", "k", "28", "6h", "1g"], function (a, b, c, d, e, f, g, h, i, j, k) {
        var l = "data-textbox-image", m = function (a) {
            return void 0 === a || null === a || 0 === a.length
        }, n = function (a) {
            var b = 1;
            return a.replace(/(<img[^>]*)src=".*?"/g, function (a, c, d) {
                return c + l + '="' + b++ + '"'
            })
        }, o = function (a, b) {
            var c = g(h.fromDom(k.body));
            return function (e, g) {
                c.play(e, function (c, e) {
                    return d.process(c, e, a, l, {mergeInline: f.constant(b)}), j.get(e)
                }, g)
            }
        }, p = function (d, f, g) {
            return a.sync(function (h) {
                var j = c.extract(d), k = o(j, f);
                k(g, function (c) {
                    var d = i.fromHtml(c);
                    a.call(h, {response: e.paste(d, [], []), bundle: b.nu({})})
                })
            })
        }, q = function () {
            return a.pure({response: e.paste([], [], []), bundle: b.nu({})})
        }, r = function (a) {
            var b = a.indexOf("</html>");
            return b > -1 ? a.substr(0, b + "</html>".length) : a
        }, s = function (b, c, d) {
            var f = r(b.data()), g = n(f);
            return d.cleanDocument(g, c).fold(function () {
                return a.pure({response: e.error("errors.paste.word.notready"), bundle: {}})
            }, function (a) {
                return m(a) ? q() : p(g, c, a)
            })
        };
        return {handle: s}
    }),g("52", ["4q", "4r", "4y", "g", "i"], function (a, b, c, d, e) {
        var f = function (f) {
            var g = d.filter(f, function (a) {
                return "file" === a.kind && /image/.test(a.type)
            }), h = d.map(g, function (a) {
                return a.getAsFile()
            });
            return a.sync(function (d) {
                e.multiple(h).get(function (e) {
                    var f = c.createImages(e);
                    a.call(d, {response: f, bundle: b.nu({})})
                })
            })
        };
        return {handle: f}
    }),g("7w", [], function () {
        return {
            validStyles: function () {
                return /^(mso-.*|tab-stops|tab-interval|language|text-underline|text-effect|text-line-through|font-color|horiz-align|list-image-[0-9]+|separator-image|table-border-color-(dark|light)|vert-align|vnd\..*)$/
            }, specialInline: function () {
                return /^(font|em|strong|samp|acronym|cite|code|dfn|kbd|tt|b|i|u|s|sub|sup|ins|del|var|span)$/
            }
        }
    }),g("83", [], function () {
        var a = function (a) {
            return g(function (b, c, d, e, f, g) {
                return b(a)
            })
        }, b = function (a) {
            return g(function (b, c, d, e, f, g) {
                return c(a)
            })
        }, c = function (a) {
            return g(function (b, c, d, e, f, g) {
                return d(a)
            })
        }, d = function (a) {
            return g(function (b, c, d, e, f, g) {
                return e(a)
            })
        }, e = function () {
            return g(function (a, b, c, d, e, f) {
                return e()
            })
        }, f = function (a) {
            return g(function (b, c, d, e, f, g) {
                return g(a)
            })
        }, g = function (a) {
            var b = function (b) {
                return a(function (a) {
                    return 0 === b.toLowerCase().indexOf(a.toLowerCase())
                }, function (a) {
                    return a.test(b.toLowerCase())
                }, function (a) {
                    return b.toLowerCase().indexOf(a.toLowerCase()) >= 0
                }, function (a) {
                    return b.toLowerCase() === a.toLowerCase()
                }, function () {
                    return !0
                }, function (a) {
                    return !a.matches(b)
                })
            };
            return {fold: a, matches: b}
        }, h = function (a, b, c, d, e, f, g) {
            return a.fold(b, c, d, e, f, g)
        };
        return {starts: a, pattern: b, contains: c, exact: d, all: e, not: f, cata: h}
    }),g("7x", ["p", "1d", "83"], function (a, b, c) {
        var d = function (b, d, e, f) {
            var g = f.name, h = void 0 !== f.condition ? f.condition : a.constant(!0),
                i = void 0 !== f.value ? f.value : c.all();
            return g.matches(e) && i.matches(d) && h(b)
        }, e = function (c, d) {
            var e = b.name(c), f = d.name, g = void 0 !== d.condition ? d.condition : a.constant(!0);
            return f.matches(e) && g(c)
        };
        return {keyval: d, name: e}
    }),g("8g", ["g", "1c", "p", "j"], function (a, b, c, d) {
        var e = function (b, c) {
            var d = {};
            return a.each(b.dom().attributes, function (a) {
                c(a.value, a.name) || (d[a.name] = a.value)
            }), d
        }, f = function (c, e, f) {
            a.each(e, function (a) {
                d.remove(c, a)
            }), b.each(f, function (a, b) {
                d.set(c, b, a)
            })
        }, g = function (c, d, e) {
            var g = a.map(c.dom().attributes, function (a) {
                return a.name
            });
            b.size(d) !== g.length && f(c, g, d)
        };
        return {filter: e, clobber: g, scan: c.constant({})}
    }),g("8h", ["g", "1c", "j", "5d", "37"], function (a, b, c, d, e) {
        var f = function (b) {
            var c = {}, d = void 0 !== b && null !== b ? b.split(";") : [];
            return a.each(d, function (a) {
                var b = a.split(":");
                2 === b.length && (c[e.trim(b[0])] = e.trim(b[1]))
            }), c
        }, g = function (a, b) {
            return a.dom().style.getPropertyValue(b)
        }, h = function (b, c) {
            var d = b.dom().style, e = void 0 === d ? [] : d, f = {};
            return a.each(e, function (a) {
                var d = g(b, a);
                c(d, a) || (f[a] = d)
            }), f
        }, i = function (a, b, c) {
            d.set(a, b, c)
        }, j = function (b, c, d) {
            var e = b.dom().getAttribute("style"), g = f(e), h = {};
            return a.each(c, function (a) {
                var b = g[a];
                void 0 === b || d(b, a) || (h[a] = b)
            }), h
        }, k = function (c) {
            var d = b.keys(c);
            return a.map(d, function (a) {
                return a + ": " + c[a]
            }).join("; ")
        }, l = function (a, d, e) {
            c.set(a, "style", "");
            var f = b.size(d), g = b.size(e);
            if (0 === f && 0 === g) c.remove(a, "style"); else if (0 === f) c.set(a, "style", k(e)); else {
                b.each(d, function (b, c) {
                    i(a, c, b)
                });
                var h = c.get(a, "style"), j = g > 0 ? k(e) + "; " : "";
                c.set(a, "style", j + h)
            }
        };
        return {filter: h, clobber: l, scan: j}
    }),g("7y", ["8g", "8h", "p", "k"], function (a, b, c, d) {
        var e = ["mso-list"], f = function (a, c) {
            var d = b.scan(a, e, c), f = b.filter(a, c);
            b.clobber(a, f, d)
        }, g = function (b, c) {
            var d = a.filter(b, c);
            a.clobber(b, d, {})
        }, h = function (a) {
            var d = b.filter(a, c.constant(!1));
            b.clobber(a, d, {})
        }, i = function (a, b) {
            f(d.fromDom(a), b)
        }, j = function (a, b) {
            g(d.fromDom(a), b)
        };
        return {style: f, attribute: g, styleDom: i, attributeDom: j, validateStyles: h}
    }),g("7i", ["g", "1l", "7x", "7y", "p", "j", "27", "3q", "1y", "2a"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = function (b, d, e) {
            b(e, function (b, f) {
                return a.exists(d, function (a) {
                    return c.keyval(e, b, f, a)
                })
            })
        }, l = function (l, m) {
            var n = b.merge({styles: [], attributes: [], classes: [], tags: []}, m), o = j.descendants(l, "*");
            a.each(o, function (b) {
                k(d.style, n.styles, b), k(d.attribute, n.attributes, b), a.each(n.classes, function (c) {
                    var d = f.has(b, "class") ? h.get(b) : [];
                    a.each(d, function (a) {
                        c.name.matches(a) && g.remove(b, a)
                    })
                })
            });
            var p = j.descendants(l, "*");
            a.each(p, function (b) {
                var d = a.exists(n.tags, e.curry(c.name, b));
                d && i.remove(b)
            })
        }, m = function (d, f) {
            var g = b.merge({tags: []}, f), h = j.descendants(d, "*");
            a.each(h, function (b) {
                var d = a.exists(g.tags, e.curry(c.name, b));
                d && i.unwrap(b)
            })
        }, n = function (d, f) {
            var g = b.merge({tags: []}, f), h = j.descendants(d, "*");
            a.each(h, function (b) {
                var d = a.find(g.tags, e.curry(c.name, b));
                void 0 !== d && null !== d && d.mutate(b)
            })
        }, o = function (b) {
            var c = j.descendants(b, "*");
            a.each(c, function (a) {
                d.validateStyles(a)
            })
        };
        return {remover: l, unwrapper: m, transformer: n, validator: o}
    }),g("86", ["1c", "5d", "k"], function (a, b, c) {
        var d = "startElement", e = "endElement", f = "text", g = "comment", h = function (a, h, i) {
            var j, k, l, m = c.fromDom(a);
            switch (a.nodeType) {
                case 1:
                    h ? j = e : (j = d, b.setAll(m, i || {})), k = "HTML" !== a.scopeName && a.scopeName && a.tagName && a.tagName.indexOf(":") <= 0 ? (a.scopeName + ":" + a.tagName).toUpperCase() : a.tagName;
                    break;
                case 3:
                    j = f, l = a.nodeValue;
                    break;
                case 8:
                    j = g, l = a.nodeValue;
                    break;
                default:
                    console.log("WARNING: Unsupported node type encountered: " + a.nodeType)
            }
            var n = function () {
                return a
            }, o = function () {
                return k
            }, p = function () {
                return j
            }, q = function () {
                return l
            };
            return {getNode: n, tag: o, type: p, text: q}
        }, i = function (b, c, d, e) {
            var f = e.createElement(b);
            return a.each(c, function (a, b) {
                f.setAttribute(b, a)
            }), h(f, !1, d)
        }, j = function (a, b) {
            return h(b.createElement(a), !0)
        }, k = function (a, b) {
            return h(b.createComment(a), !1)
        }, l = function (a, b) {
            return h(b.createTextNode(a))
        }, m = j("HTML", window.document);
        return {
            START_ELEMENT_TYPE: d,
            END_ELEMENT_TYPE: e,
            TEXT_TYPE: f,
            COMMENT_TYPE: g,
            FINISHED: m,
            token: h,
            createStartElement: i,
            createEndElement: j,
            createComment: k,
            createText: l
        }
    }),g("7z", ["86"], function (a) {
        var b = function (b) {
            var c = b.createDocumentFragment(), d = c, e = function (a) {
                g(a), c = a
            }, f = function () {
                c = c.parentNode, null === c && (c = d)
            }, g = function (a) {
                c.appendChild(a)
            }, h = function (c) {
                var d = function (a) {
                    var b = a.getNode().cloneNode(!1);
                    e(b)
                }, h = function (a, c) {
                    var d = b.createTextNode(a.text());
                    g(d)
                };
                switch (c.type()) {
                    case a.START_ELEMENT_TYPE:
                        d(c);
                        break;
                    case a.TEXT_TYPE:
                        h(c);
                        break;
                    case a.END_ELEMENT_TYPE:
                        f();
                        break;
                    case a.COMMENT_TYPE:
                        break;
                    default:
                        throw{message: "Unsupported token type: " + c.type()}
                }
            };
            return {dom: d, receive: h, label: "SERIALISER"}
        };
        return {create: b}
    }),g("80", ["86"], function (a) {
        var b = function (b, c) {
            var d;
            c = c || window.document, d = c.createElement("div"), c.body.appendChild(d), d.style.position = "absolute", d.style.left = "-10000px", d.innerHTML = b, nextNode = d.firstChild || a.FINISHED;
            var e = [];
            endNode = !1;
            var f = function (b, c) {
                return b === a.FINISHED ? b : b ? a.token(b, c) : void 0
            }, g = function () {
                var b = nextNode, g = endNode;
                return !endNode && nextNode.firstChild ? (e.push(nextNode), nextNode = nextNode.firstChild) : endNode || 1 !== nextNode.nodeType ? nextNode.nextSibling ? (nextNode = nextNode.nextSibling, endNode = !1) : (nextNode = e.pop(), endNode = !0) : endNode = !0, b === a.FINISHED || nextNode || (c.body.removeChild(d), nextNode = a.FINISHED), f(b, g)
            }, h = function () {
                return void 0 !== nextNode
            };
            return {hasNext: h, next: g}
        };
        return {tokenise: b}
    }),g("7j", ["7z", "80"], function (a, b) {
        var c = function (a, b, c) {
            var d, e = c;
            for (d = b.length - 1; d >= 0; d--) e = b[d](e, {}, a);
            return e
        }, d = function (d, e, f) {
            for (var g = a.create(d), h = b.tokenise(e, d), i = c(d, f, g); h.hasNext();) {
                var j = h.next();
                i.receive(j)
            }
            return g.dom
        };
        return {build: c, run: d}
    }),g("6m", ["g", "7i", "7j", "k", "6h", "1y", "3f"], function (a, b, c, d, e, f, g) {
        var h = function (a) {
            return function (c) {
                b.remover(c, a)
            }
        }, i = function (a) {
            return function (c) {
                b.unwrapper(c, a)
            }
        }, j = function (a) {
            return function (c) {
                b.transformer(c, a)
            }
        }, k = function () {
            return function (a) {
                b.validator(a)
            }
        }, l = function (a) {
            return function (b) {
                var d = e.get(b), h = g.owner(b), i = c.run(h.dom(), d, a);
                f.empty(b), b.dom().appendChild(i)
            }
        }, m = function (b, c, f) {
            var g = d.fromTag("div", b.dom());
            return g.dom().innerHTML = c, a.each(f, function (a) {
                a(g)
            }), e.get(g)
        }, n = function (a, b) {
            return a.indexOf("<o:p>") >= 0 || b.browser.isSpartan() && a.indexOf('v:shapes="') >= 0 || b.browser.isSpartan() && a.indexOf("mso-") >= 0 || a.indexOf("mso-list") >= 0 || a.indexOf("p.MsoNormal, li.MsoNormal, div.MsoNormal") >= 0 || a.indexOf("MsoListParagraphCxSpFirst") >= 0 || a.indexOf("<w:WordDocument>") >= 0
        };
        return {removal: h, unwrapper: i, transformer: j, validate: k, pipeline: l, isWordContent: n, go: m}
    }),g("7l", ["g", "86"], function (a, b) {
        return function (c, d, e) {
            return function (e, f, g) {
                var h = function (b) {
                    a.each(b, i)
                }, i = function (a) {
                    e.receive(a)
                }, j = function (a) {
                    c(l, a, k)
                }, k = function (a, c) {
                    return b.token(c, a.type() === b.END_ELEMENT_TYPE, {})
                }, l = {emit: i, emitTokens: h, receive: j, document: window.document};
                return d(l), l
            }
        }
    }),g("8u", ["8h", "86", "p", "j", "5d", "k"], function (a, b, c, d, e, f) {
        var g = function (a, b) {
            var c = f.fromDom(a.getNode());
            return d.get(c, b)
        }, h = function (a, b) {
            var c = f.fromDom(a.getNode());
            return e.get(c, b)
        }, i = function (a) {
            return a.type() === b.TEXT_TYPE && /^[\s\u00A0]*$/.test(a.text())
        }, j = function (b) {
            var d = f.fromDom(b.getNode()), e = a.scan(d, ["mso-list"], c.constant(!1));
            return e["mso-list"]
        };
        return {getAttribute: g, getStyle: h, isWhitespace: i, getMsoList: j}
    }),g("96", ["g", "n"], function (a, b) {
        var c = function (c, e) {
            var f = a.find(c, function (a) {
                return "UL" === a.tag || e && d(a, e, !0)
            });
            return void 0 !== f ? b.some(f) : c.length > 0 ? b.some(c[0]) : b.none()
        }, d = function (a, b, c) {
            return a === b || a && b && a.tag === b.tag && a.type === b.type && (c || a.variant === b.variant)
        };
        return {guessFrom: c, eqListType: d}
    }),g("8l", [], function () {
        var a = function (a, b) {
            if (void 0 === a || void 0 === b) throw console.trace(), "brick";
            a.nextFilter.set(b)
        }, b = function (b) {
            return function (c, d, e) {
                a(d, b)
            }
        }, c = function (a, b, c) {
            var d = b.nextFilter.get();
            d(a, b, c)
        }, d = function (b) {
            return function (d, e, f) {
                a(e, b), c(d, e, f)
            }
        }, e = function (a, b) {
            return a.nextFilter.get() === b
        };
        return {next: a, go: c, jump: d, isNext: e, setNext: b}
    }),g("8t", ["g", "8u", "96", "8l", "p", "2c", "j", "k"], function (a, b, c, d, e, f, g, h) {
        var i = function (a, b) {
            return g.has(h.fromDom(b.getNode()), "data-list-level")
        }, j = function (a) {
            var b = parseInt(g.get(a, "data-list-level"), 10), c = g.get(a, "data-list-emblems"), d = JSON.parse(c);
            return g.remove(a, "data-list-level"), g.remove(a, "data-list-emblems"), {
                level: e.constant(b),
                emblems: e.constant(d)
            }
        }, k = f.immutable("level", "token", "type"), l = function (c) {
            return !a.contains(["P"], c.tag()) || /^MsoHeading/.test(b.getAttribute(c, "class"))
        }, m = function (a, b, d, e) {
            var f = d.getCurrentListType(), g = d.getCurrentLevel(), h = g == e.level() ? f : null;
            return c.guessFrom(e.emblems(), h).filter(function (a) {
                return !("OL" === a.tag && l(b))
            })
        }, n = function (a, b, c) {
            var d = m(c.listType.get(), a, c.emitter, b);
            return d.each(c.listType.set), k(b.level(), c.originalToken.get(), c.listType.get())
        }, o = function (a) {
            return function (b, c, e) {
                var f = j(h.fromDom(e.getNode()));
                f.level();
                c.originalToken.set(e);
                var g = n(e, f, c);
                c.emitter.openItem(g.level(), g.token(), g.type()), d.next(c, a.inside())
            }
        };
        return {predicate: i, action: o}
    }),g("8v", ["p"], function (a) {
        return function (b, c, d) {
            return {pred: b, action: c, label: a.constant(d)}
        }
    }),g("8w", ["g", "p", "n"], function (a, b, c) {
        var d = function (a, b) {
            return function (a, c, d) {
                return b(a, c, d)
            }
        };
        return function (e, f, g) {
            var h = d(e + " :: FALLBACK --- ", g), i = function (g, i, j) {
                var k = c.from(a.find(f, function (a) {
                    return a.pred(i, j)
                })), l = k.fold(b.constant(h), function (a) {
                    var b = a.label();
                    return void 0 === b ? a.action : d(e + " :: " + b, a.action)
                });
                l(g, i, j)
            };
            return i.toString = function () {
                return "Handlers for " + e
            }, i
        }
    }),g("8i", ["8t", "86", "8u", "8v", "8w", "8l"], function (a, b, c, d, e, f) {
        var g = function (a) {
            var c = function (b, c, d) {
                f.next(c, a.outside())
            }, g = function (a, c) {
                return c.type() === b.END_ELEMENT_TYPE && a.originalToken.get() && c.tag() === a.originalToken.get().tag()
            };
            return e("Inside.List.Item", [d(g, c, "Closing open tag")], function (a, b, c) {
                a.emit(c)
            })
        }, h = function (g) {
            var h = function (a, b, c) {
                b.emitter.closeAllLists(), a.emit(c), f.next(b, g.outside())
            }, i = function (a, d) {
                return d.type() === b.TEXT_TYPE && c.isWhitespace(d)
            };
            return e("Outside.List.Item", [d(a.predicate, a.action(g), "Data List ****"), d(i, function (a, b, c) {
                a.emit(c)
            }, "Whitespace")], h)
        };
        return {inside: g, outside: h}
    }),g("97", ["2c"], function (a) {
        var b = a.immutable("state", "result"), c = a.immutable("state", "value"),
            d = a.immutable("level", "type", "types", "items");
        return {state: d, value: c, result: b}
    }),g("9j", ["97", "n"], function (a, b) {
        var c = function (c) {
            var d = c.items().slice(0);
            if (d.length > 0 && "P" !== d[d.length - 1]) {
                var e = d[d.length - 1];
                d[d.length - 1] = "P";
                var f = a.state(c.level(), c.type(), c.types(), d);
                return a.value(f, b.some(e))
            }
            return a.value(c, b.none())
        }, d = function (c, d) {
            var e = c.items().slice(0), f = void 0 !== d && "P" !== d ? b.some(d) : b.none();
            f.fold(function () {
                e.push("P")
            }, function (a) {
                e.push(a)
            });
            var g = a.state(c.level(), c.type(), c.types(), e);
            return a.value(g, f)
        };
        return {start: d, finish: c}
    }),g("9k", ["97"], function (a) {
        var b = function (b, c, d) {
            for (var e = [], f = b; c(f);) {
                var g = d(f);
                f = g.state(), e = e.concat(g.result())
            }
            return a.result(f, e)
        }, c = function (a, c, d) {
            var e = function (a) {
                return a.level() < c
            };
            return b(a, e, d)
        }, d = function (a, c, d) {
            var e = function (a) {
                return a.level() > c
            };
            return b(a, e, d)
        };
        return {moveRight: c, moveLeft: d, moveUntil: b}
    }),g("9v", ["8u"], function (a) {
        var b = function (b) {
            var c = a.getStyle(b, "margin-left");
            return void 0 !== c && "0px" !== c ? {"margin-left": c} : {}
        }, c = function (a) {
            var c = {"list-style-type": "none"};
            return a ? b(a) : c
        };
        return {from: c}
    }),g("9l", ["7y", "86", "96", "97", "9j", "9v", "p"], function (a, b, c, d, e, f, g) {
        var h = function (a, c, e) {
            var f = c.start && c.start > 1 ? {start: c.start} : {}, h = a.level() + 1, i = c, j = a.types().concat([c]),
                k = [g.curry(b.createStartElement, c.tag, f, e)], l = d.state(h, i, j, a.items());
            return d.result(l, k)
        }, i = function (a) {
            var c = a.types().slice(0), e = [g.curry(b.createEndElement, c.pop().tag)], f = a.level() - 1,
                h = c[c.length - 1], i = d.state(f, h, c, a.items());
            return d.result(i, e)
        }, j = function (a, b) {
            var c = i(a), e = h(c.state(), b, b.type ? {"list-style-type": b.type} : {});
            return d.result(e.state(), c.result().concat(e.result()))
        }, k = function (h, i, k) {
            var l = {}, m = f.from(i), n = h.type() && !c.eqListType(h.type(), k) ? j(h, k) : d.result(h, []),
                o = [g.curry(b.createStartElement, "LI", l, m)], p = e.start(n.state(), i && i.tag()),
                q = p.value().map(function (b) {
                    return a.styleDom(i.getNode(), g.constant(!0)), [g.constant(i)]
                }).getOr([]);
            return d.result(p.state(), n.result().concat(o).concat(q))
        }, l = function (a) {
            var c = g.curry(b.createEndElement, "LI"), f = e.finish(a), h = f.value().fold(function () {
                return [c]
            }, function (a) {
                return [g.curry(b.createEndElement, a), c]
            });
            return d.result(f.state(), h)
        };
        return {open: h, openItem: k, close: i, closeItem: l}
    }),g("98", ["g", "86", "97", "9j", "9k", "9l", "p", "n"], function (a, b, c, d, e, f, g, h) {
        var i = function (b) {
            if (0 === b.length) throw"Compose must have at least one element in the list";
            var d = b[b.length - 1], e = a.bind(b, function (a) {
                return a.result()
            });
            return c.result(d.state(), e)
        }, j = function (a) {
            var b = f.closeItem(a), c = f.close(b.state());
            return i([b, c])
        }, k = function (a, b, c, d) {
            var e = a.level() === c - 1 && b.type ? {"list-style-type": b.type} : {}, g = f.open(a, b, e),
                h = f.openItem(g.state(), g.state().level() == c ? d : void 0, b);
            return i([g, h])
        }, l = function (a, b, d) {
            var e = a.level() > 0 ? f.closeItem(a) : c.result(a, []), g = f.openItem(e.state(), d, b);
            return i([e, g])
        }, m = function (a, b, c, d) {
            return e.moveRight(a, c, function (a) {
                return k(a, b, c, d)
            })
        }, n = function (a, b) {
            return e.moveLeft(a, b, j)
        }, o = function (a, e, f, i) {
            var j = f > 1 ? d.finish(a) : c.value(a, h.none()), k = j.value().map(function (a) {
                return [g.curry(b.createEndElement, a)]
            }).getOr([]), l = (f - j.state().level(), m(j.state(), e, f, i));
            return c.result(l.state(), k.concat(l.result()))
        }, p = function (a, b, d, e) {
            var f = a.level() > b ? n(a, b) : c.result(a, []),
                g = f.state().level() === b ? l(f.state(), e, d) : o(f.state(), e, b, d);
            return i([f, g])
        }, q = n;
        return {openItem: p, closeAllLists: q}
    }),g("8x", ["g", "97", "98"], function (a, b, c) {
        var d = ["disc", "circle", "square"], e = function (a, b) {
            return "UL" === a.tag && d[b - 1] === a.type && (a = {tag: "UL"}), a
        };
        return function (d, f) {
            var g = b.state(0, void 0, [], []), h = function (b) {
                a.each(b.result(), function (a) {
                    var b = a(f);
                    d.emit(b)
                })
            }, i = function () {
                var a = c.closeAllLists(g, 0);
                g = a.state(), h(a)
            }, j = function (a, b, d) {
                if (d) {
                    var f = e(d, a), i = c.openItem(g, a, b, f);
                    g = i.state(), h(i)
                }
            }, k = function () {
                return g.level()
            }, l = function () {
                return g.type()
            };
            return {closeAllLists: i, openItem: j, getCurrentListType: l, getCurrentLevel: k}
        }
    }),g("z", [], function () {
        var a = function (b) {
            var c = b, d = function () {
                return c
            }, e = function (a) {
                c = a
            }, f = function () {
                return a(d())
            };
            return {get: d, set: e, clone: f}
        };
        return a
    }),g("8j", ["8x", "p", "z"], function (a, b, c) {
        var d = {
            getCurrentListType: function () {
                return e().getCurrentListType()
            }, getCurrentLevel: function () {
                return e().getCurrentLevel()
            }, closeAllLists: function () {
                return e().closeAllLists.apply(void 0, arguments)
            }, openItem: function () {
                return e().openItem.apply(void 0, arguments)
            }
        }, e = function () {
            return {
                getCurrentListType: b.constant({}),
                getCurrentLevel: b.constant(1),
                closeAllLists: b.identity,
                openItem: b.identity
            }
        };
        return function (f) {
            var g = c(f), h = c(null), i = c(null), j = function (c) {
                g.set(f), h.set(null), i.set(null), _emitter = a(c, c.document), e = b.constant(_emitter)
            };
            return {reset: j, nextFilter: g, originalToken: h, listType: i, emitter: d}
        }
    }),g("8k", ["86"], function (a) {
        return function () {
            var b = !1, c = "", d = function (d) {
                return b && d.type() === a.TEXT_TYPE ? (c += d.text(), !0) : d.type() === a.START_ELEMENT_TYPE && "STYLE" === d.tag() ? (b = !0, !0) : d.type() === a.END_ELEMENT_TYPE && "STYLE" === d.tag() && (b = !1, !0)
            };
            return {check: d}
        }
    }),g("81", ["7l", "8i", "8j", "8k", "8l", "1g"], function (a, b, c, d, e, f) {
        var g = {
            inside: function () {
                return i
            }, outside: function () {
                return j
            }
        }, h = d(), i = b.inside(g), j = b.outside(g), k = c(j);
        return a(function (a, b, c) {
            h.check(b) || e.go(a, k, b)
        }, k.reset, "list.filters")
    }),h("8o", parseInt),g("8y", ["g", "1l", "64", "8o"], function (a, b, c, d) {
        var e = [{regex: /^\(?[dc][\.\)]$/, type: {tag: "OL", type: "lower-alpha"}}, {
            regex: /^\(?[DC][\.\)]$/,
            type: {tag: "OL", type: "upper-alpha"}
        }, {
            regex: /^\(?M*(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})[\.\)]$/,
            type: {tag: "OL", type: "upper-roman"}
        }, {
            regex: /^\(?m*(cm|cd|d?c{0,3})(xc|xl|l?x{0,3})(ix|iv|v?i{0,3})[\.\)]$/,
            type: {tag: "OL", type: "lower-roman"}
        }, {regex: /^\(?[0-9]+[\.\)]$/, type: {tag: "OL"}}, {
            regex: /^([0-9]+\.)*[0-9]+\.?$/,
            type: {tag: "OL", variant: "outline"}
        }, {regex: /^\(?[a-z]+[\.\)]$/, type: {tag: "OL", type: "lower-alpha"}}, {
            regex: /^\(?[A-Z]+[\.\)]$/,
            type: {tag: "OL", type: "upper-alpha"}
        }], f = {
            "\u2022": {tag: "UL", type: "disc"},
            "\xb7": {tag: "UL", type: "disc"},
            "\xa7": {tag: "UL", type: "square"}
        }, g = {
            o: {tag: "UL", type: "circle"},
            "-": {tag: "UL", type: "disc"},
            "\u25cf": {tag: "UL", type: "disc"},
            "\ufffd": {tag: "UL", type: "circle"}
        }, h = function (a, b) {
            return void 0 !== a.variant ? a.variant : "(" === b.charAt(0) ? "()" : ")" === b.charAt(b.length - 1) ? ")" : "."
        }, i = function (a) {
            var b = a.split("."), e = function () {
                if (0 === b.length) return a;
                var c = b[b.length - 1];
                return 0 === c.length && b.length > 1 ? b[b.length - 2] : c
            }(), f = d(e, 10);
            return c(f) ? {} : {start: f}
        }, j = function (c, d) {
            var j = g[c] ? [g[c]] : [], k = d && f[c] ? [f[c]] : d ? [{tag: "UL", variant: c}] : [],
                l = a.bind(e, function (a) {
                    return a.regex.test(c) ? [b.merge(a.type, i(c), {variant: h(a.type, c)})] : []
                }), m = j.concat(k).concat(l);
            return a.map(m, function (a) {
                return void 0 !== a.variant ? a : b.merge(a, {variant: c})
            })
        };
        return {extract: j}
    }),g("7o", [], function () {
        var a = function (a) {
            return a.dom().textContent
        }, b = function (a, b) {
            a.dom().textContent = b
        };
        return {get: a, set: b}
    }),g("8m", ["g", "8y", "8h", "p", "n", "5d", "6h", "1d", "6r", "7o", "3f", "62", "64", "8o"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n) {
        var o = 18, p = function (a) {
            var b = c.scan(a, ["mso-list"], d.constant(!1));
            return b["mso-list"]
        }, q = function (a) {
            var b = p(a), c = / level([0-9]+)/.exec(b);
            return c && c[1] ? e.some(n(c[1], 10)) : e.none()
        }, r = function (a, c) {
            var d = j.get(a).trim(), f = b.extract(d, c);
            return f.length > 0 ? e.some(f) : e.none()
        }, s = function (a) {
            return i.child(a, v)
        }, t = function (a) {
            return i.child(a, h.isComment).bind(k.nextSibling).filter(function (a) {
                return "span" === h.name(a)
            })
        }, u = function (a) {
            return i.descendant(a, function (a) {
                var b = h.isElement(a) ? c.scan(a, ["mso-list"], d.constant(!1)) : [];
                return !!b["mso-list"]
            })
        }, v = function (b) {
            return h.isElement(b) && f.getRaw(b, "font-family").exists(function (b) {
                return a.contains(["wingdings", "symbol"], b.toLowerCase())
            })
        }, w = function (a) {
            return f.getRaw(a, "margin-left").bind(function (a) {
                var b = n(a, 10);
                return m(b) ? e.none() : e.some(l.max(1, l.ceil(b / o)))
            })
        };
        return {
            getMsoList: p,
            extractLevel: q,
            extractEmblem: r,
            extractSymSpan: s,
            extractMsoIgnore: u,
            extractCommentSpan: t,
            isSymbol: v,
            deduceLevel: w
        }
    }),h("8n", JSON),g("82", ["g", "8m", "n", "j", "7r", "1d", "1y", "3f", "8n", "8o"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = function (a, b, c) {
            d.set(a, "data-list-level", b);
            var e = i.stringify(c);
            d.set(a, "data-list-emblems", e)
        }, l = function (b) {
            var d = e.find(b, c.none());
            a.each(d, g.remove)
        }, m = function (b, c, e, f) {
            k(b, c, e), l(b), a.each(f, g.remove), d.remove(b, "style"), d.remove(b, "class")
        }, n = function (a) {
            return b.extractLevel(a).bind(function (c) {
                return b.extractSymSpan(a).bind(function (d) {
                    return b.extractEmblem(d, !0).map(function (b) {
                        var e = function () {
                            m(a, c, b, [d])
                        };
                        return {mutate: e}
                    })
                })
            })
        }, o = function (a) {
            return b.extractLevel(a).bind(function (c) {
                return b.extractCommentSpan(a).bind(function (d) {
                    return b.extractEmblem(d, b.isSymbol(d)).map(function (b) {
                        var e = function () {
                            m(a, c, b, [d])
                        };
                        return {mutate: e}
                    })
                })
            })
        }, p = function (a) {
            return b.extractLevel(a).bind(function (c) {
                return b.extractCommentSpan(a).bind(function (d) {
                    return b.extractEmblem(d, b.isSymbol(d)).map(function (b) {
                        var e = function () {
                            m(a, c, b, [d])
                        };
                        return {mutate: e}
                    })
                })
            })
        }, q = function (a) {
            return "p" !== f.name(a) ? c.none() : b.extractLevel(a).bind(function (c) {
                return b.extractMsoIgnore(a).bind(function (d) {
                    return b.extractEmblem(d, !1).map(function (b) {
                        var e = function () {
                            m(a, c, b, [h.parent(d).getOr(d)])
                        };
                        return {mutate: e}
                    })
                })
            })
        }, r = function (a) {
            return "p" !== f.name(a) ? c.none() : b.extractMsoIgnore(a).bind(function (c) {
                var d = h.parent(c).getOr(c), e = b.isSymbol(d);
                return b.extractEmblem(c, e).bind(function (c) {
                    return b.deduceLevel(a).map(function (b) {
                        var e = function () {
                            m(a, b, c, [d])
                        };
                        return {mutate: e}
                    })
                })
            })
        }, s = function (a) {
            return n(a).orThunk(function () {
                return o(a)
            }).orThunk(function () {
                return p(a)
            }).orThunk(function () {
                return q(a)
            }).orThunk(function () {
                return r(a)
            })
        };
        return {find: s}
    }),g("7k", ["6m", "81", "82", "83"], function (a, b, c, d) {
        var e = a.transformer({
            tags: [{
                name: d.pattern(/^(p|h\d+)$/), mutate: function (a) {
                    c.find(a).each(function (a) {
                        a.mutate()
                    })
                }
            }]
        });
        return {filter: b, preprocess: e}
    }),g("6n", ["6r"], function (a) {
        var b = function (b) {
            return a.first(b).isSome()
        }, c = function (b, c, d) {
            return a.ancestor(b, c, d).isSome()
        }, d = function (b, c, d) {
            return a.closest(b, c, d).isSome()
        }, e = function (b, c) {
            return a.sibling(b, c).isSome()
        }, f = function (b, c) {
            return a.child(b, c).isSome()
        }, g = function (b, c) {
            return a.descendant(b, c).isSome()
        };
        return {any: b, ancestor: c, closest: d, sibling: e, child: f, descendant: g}
    }),g("84", ["g", "j", "6h", "1d", "6n"], function (a, b, c, d, e) {
        var f = function (a) {
            return "img" !== d.name(a)
        }, g = function (a) {
            var b = a.dom().attributes, c = void 0 !== b && null !== b && b.length > 0;
            return "span" !== d.name(a) || c
        }, h = function (b) {
            return !k(b) || g(b) && e.descendant(b, function (b) {
                var c = !k(b),
                    e = !a.contains(["font", "em", "strong", "samp", "acronym", "cite", "code", "dfn", "kbd", "tt", "b", "i", "u", "s", "sub", "sup", "ins", "del", "var", "span"], d.name(b));
                return d.isText(b) || c || e
            })
        }, i = function (a) {
            return "ol" === d.name(a) || "ul" === d.name(a)
        }, j = function (a) {
            var c = b.get(a, "src");
            return /^file:/.test(c)
        }, k = function (a) {
            return void 0 === a.dom().attributes || null === a.dom().attributes || (0 === a.dom().attributes.length || 1 === a.dom().attributes.length && "style" === a.dom().attributes[0].name)
        }, l = function (a) {
            return 0 === c.get(a).length
        };
        return {isNotImage: f, hasContent: h, isList: i, isLocal: j, hasNoAttributes: k, isEmpty: l}
    }),g("8p", ["1d", "7s"], function (a, b) {
        var c = b(a.isText, "text"), d = function (a) {
            return c.get(a)
        }, e = function (a) {
            return c.getOption(a)
        }, f = function (a, b) {
            c.set(a, b)
        };
        return {get: d, getOption: e, set: f}
    }),g("85", ["g", "1c", "n", "j", "5d", "k", "6h", "1x", "29", "1d", "1y", "8p", "3f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m) {
        var n = function (b, c) {
            var d = f.fromTag(b);
            h.before(c, d);
            var e = c.dom().attributes;
            a.each(e, function (a) {
                d.dom().setAttribute(a.name, a.value)
            });
            var g = m.children(c);
            return i.append(d, g), k.remove(c), d
        }, o = function (a) {
            0 === g.get(a).length && h.append(a, f.fromTag("br"))
        }, p = function (a) {
            return m.prevSibling(a).bind(function (a) {
                return j.isText(a) && 0 === l.get(a).trim().length ? p(a) : "li" === j.name(a) ? c.some(a) : c.none()
            })
        }, q = function (b) {
            m.parent(b).each(function (c) {
                var d = j.name(c);
                a.contains(["ol", "ul"], d) && p(b).fold(function () {
                    var a = f.fromTag("li");
                    e.set(a, "list-style-type", "none"), h.wrap(b, a)
                }, function (a) {
                    h.append(a, b)
                })
            })
        }, r = function (a) {
            var c = n("span", a), f = {face: "font-family", size: "font-size", color: "color"},
                g = {"font-size": {1: "8pt", 2: "10pt", 3: "12pt", 4: "14pt", 5: "18pt", 6: "24pt", 7: "36pt"}};
            b.each(f, function (a, b) {
                if (d.has(c, b)) {
                    var f = d.get(c, b), h = void 0 !== g[a] && void 0 !== g[a][f] ? g[a][f] : f;
                    e.set(c, a, h), d.remove(c, b)
                }
            })
        };
        return {changeTag: n, addBrTag: o, properlyNest: q, fontToSpan: r}
    }),g("7h", ["7w", "6m", "7k", "84", "85", "p", "27", "5d", "3f", "1d", "83"], function (a, b, c, d, e, f, g, h, i, j, k) {
        var l = b.unwrapper({tags: [{name: k.pattern(/^([OVWXP]|U[0-9]+|ST[0-9]+):/i)}]}), m = [b.pipeline([c.filter])],
            n = b.removal({
                attributes: [{name: k.pattern(/^v:/)}, {
                    name: k.exact("href"),
                    value: k.contains("#_toc")
                }, {
                    name: k.exact("href"),
                    value: k.contains("#_mso")
                }, {name: k.pattern(/^xmlns(:|$)/)}, {name: k.exact("type"), condition: d.isList}]
            }), o = b.removal({attributes: [{name: k.exact("id")}, {name: k.exact("name")}]}), p = b.removal({
                tags: [{name: k.exact("script")}, {name: k.exact("meta")}, {name: k.exact("link")}, {
                    name: k.exact("style"),
                    condition: d.isEmpty
                }],
                attributes: [{name: k.starts("on")}, {name: k.exact('"')}, {name: k.exact("lang")}, {name: k.exact("language")}],
                styles: [{name: k.all(), value: k.pattern(/OLE_LINK/i)}]
            }), q = function (a) {
                return !g.has(a, "ephox-limbo-transform")
            }, r = function (a) {
                return function (b) {
                    return i.parent(b).exists(function (b) {
                        return j.name(b) === a && 1 === i.children(b).length
                    })
                }
            }, s = b.removal({
                styles: [{
                    name: k.not(k.pattern(/width|height|list-style-type/)),
                    condition: q
                }, {name: k.pattern(/width|height/), condition: d.isNotImage}]
            }), t = b.removal({classes: [{name: k.not(k.exact("rtf-data-image"))}]}),
            u = b.removal({styles: [{name: k.pattern(a.validStyles())}]}),
            v = b.removal({classes: [{name: k.pattern(/mso/i)}]}), w = b.unwrapper({
                tags: [{name: k.exact("img"), condition: d.isLocal}, {
                    name: k.exact("a"),
                    condition: d.hasNoAttributes
                }]
            }), x = b.unwrapper({tags: [{name: k.exact("a"), condition: d.hasNoAttributes}]}),
            y = b.removal({attributes: [{name: k.exact("style"), value: k.exact(""), debug: !0}]}),
            z = b.removal({attributes: [{name: k.exact("class"), value: k.exact(""), debug: !0}]}),
            A = b.unwrapper({tags: [{name: k.pattern(a.specialInline()), condition: f.not(d.hasContent)}]}),
            B = b.unwrapper({tags: [{name: k.exact("p"), condition: r("li")}]}),
            C = b.transformer({tags: [{name: k.exact("p"), mutate: e.addBrTag}]}), D = function (a) {
                var b = e.changeTag("span", a);
                g.add(b, "ephox-limbo-transform"), h.set(b, "text-decoration", "underline")
            }, E = b.transformer({tags: [{name: k.pattern(/ol|ul/), mutate: e.properlyNest}]}), F = b.transformer({
                tags: [{name: k.exact("b"), mutate: f.curry(e.changeTag, "strong")}, {
                    name: k.exact("i"),
                    mutate: f.curry(e.changeTag, "em")
                }, {name: k.exact("u"), mutate: D}, {
                    name: k.exact("s"),
                    mutate: f.curry(e.changeTag, "strike")
                }, {name: k.exact("font"), mutate: e.fontToSpan, debug: !0}]
            }), G = b.removal({classes: [{name: k.exact("ephox-limbo-transform")}]}),
            H = b.removal({attributes: [{name: k.exact("href"), value: k.starts("file:///"), debug: !0}]});
        return {
            unwrapWordTags: l,
            removeWordAttributes: n,
            parseLists: m,
            removeExcess: p,
            cleanStyles: s,
            cleanClasses: t,
            mergeStyles: u,
            mergeClasses: v,
            removeLocalImages: w,
            removeVacantLinks: x,
            removeEmptyStyle: y,
            removeEmptyClass: z,
            pruneInlineTags: A,
            unwrapSingleParagraphsInlists: B,
            addPlaceholders: C,
            nestedListFixes: E,
            inlineTagFixes: F,
            cleanupFlags: G,
            removeLocalLinks: H,
            removeAnchors: o,
            none: f.noop
        }
    }),g("6k", ["g", "5a", "7h", "6m", "7k", "7l", "p", "k"], function (a, b, c, d, e, f, g, h) {
        var i = function (a) {
            return a.browser.isIE() && a.browser.version.major >= 11
        }, j = function (a) {
            return f(function (b, c, d) {
                var e = a(h.fromDom(c.getNode())).fold(function () {
                    return c
                }, function (a) {
                    return d(c, a.dom())
                });
                b.emit(e)
            }, g.noop, "image filters")
        }, k = function (a, e, f) {
            var g = f.browser.isFirefox() || f.browser.isSpartan() ? b.local : b.vshape,
                h = i(f) ? c.none : d.pipeline([j(g)]), k = g === b.local ? c.none : c.removeLocalImages,
                l = a ? h : c.none;
            return {annotate: [l], local: [k]}
        }, l = function (a, b) {
            var d = i(b) && a;
            return d ? [c.unwrapSingleParagraphsInlists] : []
        }, m = function (a, b, d) {
            var e = [c.mergeStyles, c.mergeClasses], f = [c.cleanStyles, c.cleanClasses];
            return b ? e : f
        }, n = function (a, b, c) {
            return i(c) || !a ? [] : [e.preprocess]
        }, o = function (a, b, d) {
            if (!a) return [c.none];
            var e = [c.unwrapWordTags], f = i(d) ? [] : c.parseLists;
            return e.concat(f).concat([c.removeWordAttributes])
        }, p = function (a, b, d) {
            return a ? [c.removeAnchors] : [c.none]
        }, q = function (b, d, e) {
            var f = k(b, d, e);
            return a.flatten([n(b, d, e), f.annotate, [c.inlineTagFixes], o(b, d, e), [c.nestedListFixes], [c.removeExcess], p(b, d, e), f.local, m(b, d, e), [c.removeLocalLinks, c.removeVacantLinks], [c.removeEmptyStyle], [c.removeEmptyClass], [c.pruneInlineTags], [c.addPlaceholders], l(b, e), [c.cleanupFlags]])
        };
        return {derive: q}
    }),g("8q", [], function () {
        return ["body", "p", "div", "article", "aside", "figcaption", "figure", "footer", "header", "nav", "section", "ol", "ul", "li", "table", "thead", "tbody", "tfoot", "caption", "tr", "td", "th", "h1", "h2", "h3", "h4", "h5", "h6", "blockquote", "pre", "address"]
    }),g("87", ["8q", "g", "p", "j", "3n", "5d", "k", "1x", "29", "1d", "3o", "6r", "1y", "2a", "20", "8p", "3f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q) {
        return function () {
            var r = function (a) {
                return g.fromDom(a.dom().cloneNode(!1))
            }, s = function (c) {
                return !!j.isElement(c) && ("body" === j.name(c) || b.contains(a, j.name(c)))
            }, t = function (a) {
                return !!j.isElement(a) && b.contains(["br", "img", "hr"], j.name(a))
            }, u = function (a, b) {
                return a.dom().compareDocumentPosition(b.dom())
            }, v = function (a, b) {
                var c = d.clone(a);
                d.setAll(b, c)
            };
            return {
                up: c.constant({selector: o.ancestor, closest: o.closest, predicate: l.ancestor, all: q.parents}),
                down: c.constant({selector: n.descendants, predicate: k.descendants}),
                styles: c.constant({get: f.get, getRaw: f.getRaw, set: f.set, remove: f.remove}),
                attrs: c.constant({get: d.get, set: d.set, remove: d.remove, copyTo: v}),
                insert: c.constant({
                    before: h.before,
                    after: h.after,
                    afterAll: i.after,
                    append: h.append,
                    appendAll: i.append,
                    prepend: h.prepend,
                    wrap: h.wrap
                }),
                remove: c.constant({unwrap: m.unwrap, remove: m.remove}),
                create: c.constant({nu: g.fromTag, clone: r, text: g.fromText}),
                query: c.constant({comparePosition: u, prevSibling: q.prevSibling, nextSibling: q.nextSibling}),
                property: c.constant({
                    children: q.children,
                    name: j.name,
                    parent: q.parent,
                    isText: j.isText,
                    isElement: j.isElement,
                    getText: p.get,
                    setText: p.set,
                    isBoundary: s,
                    isEmptyTag: t
                }),
                eq: e.eq,
                is: e.is
            }
        }
    }),g("8z", ["2c"], function (a) {
        return a.immutable("word", "pattern")
    }),g("90", ["2c"], function (a) {
        var b = a.immutable("element", "offset"), c = a.immutable("element", "deltaOffset"),
            d = a.immutable("element", "start", "finish"), e = a.immutable("begin", "end"),
            f = a.immutable("element", "text");
        return {point: b, delta: c, range: d, points: e, text: f}
    }),g("9n", ["p", "n"], function (a, b) {
        var c = a.constant(!1), d = a.constant(!0), e = function (a, b) {
            return h(function (c, d, e) {
                return c(a, b)
            })
        }, f = function (a, b) {
            return h(function (c, d, e) {
                return d(a, b)
            })
        }, g = function (a, b) {
            return h(function (c, d, e) {
                return e(a, b)
            })
        }, h = function (e) {
            var f = function () {
                return e(d, c, c)
            }, g = function () {
                return e(b.none, b.none, function (a, c) {
                    return b.some(a)
                })
            }, h = function (a) {
                return e(c, c, function (b, c) {
                    return c.eq(b, a)
                })
            }, i = function () {
                return e(a.constant(0), a.constant(1), function (a, b) {
                    return b.property().getText(a).length
                })
            };
            return {isBoundary: f, fold: e, toText: g, is: h, len: i}
        }, i = function (a, b, c, d) {
            return a.fold(b, c, d)
        };
        return {text: g, boundary: e, empty: f, cata: i}
    }),g("9o", ["g", "p"], function (a, b) {
        var c = function (c, d, e, f) {
            var g = a.findIndex(c, b.curry(f, d)), h = g > -1 ? g : 0, i = a.findIndex(c, b.curry(f, e)),
                j = i > -1 ? i + 1 : c.length;
            return c.slice(h, j)
        };
        return {boundAt: c}
    }),g("9p", ["g"], function (a) {
        var b = function (b, c) {
            var d = a.findIndex(b, c);
            return b.slice(0, d)
        };
        return {sliceby: b}
    }),g("9r", ["1m"], function (a) {
        var b = a.generate([{include: ["item"]}, {excludeWith: ["item"]}, {excludeWithout: ["item"]}]),
            c = function (a, b, c, d) {
                return a.fold(b, c, d)
            };
        return {include: b.include, excludeWith: b.excludeWith, excludeWithout: b.excludeWithout, cata: c}
    }),g("9q", ["g", "9r"], function (a, b) {
        var c = function (a, c) {
            return d(a, function (a) {
                return c(a) ? b.excludeWithout(a) : b.include(a)
            })
        }, d = function (c, d) {
            var e = [], f = [];
            return a.each(c, function (a) {
                var c = d(a);
                b.cata(c, function () {
                    f.push(a)
                }, function () {
                    f.length > 0 && e.push(f), e.push([a]), f = []
                }, function () {
                    f.length > 0 && e.push(f), f = []
                })
            }), f.length > 0 && e.push(f), e
        };
        return {splitby: c, splitbyAdv: d}
    }),g("9b", ["9o", "9p", "9q"], function (a, b, c) {
        var d = function (b, c, d, e) {
            return a.boundAt(b, c, d, e)
        }, e = function (a, b) {
            return c.splitby(a, b)
        }, f = function (a, b) {
            return c.splitbyAdv(a, b)
        }, g = function (a, c) {
            return b.sliceby(a, c)
        };
        return {splitby: e, splitbyAdv: f, sliceby: g, boundAt: d}
    }),g("92", ["g", "p", "n", "90", "9b"], function (a, b, c, d, e) {
        var f = function (b) {
            return a.foldr(b, function (a, b) {
                return b.len() + a
            }, 0)
        }, g = function (a, b) {
            return e.sliceby(a, function (a) {
                return a.is(b)
            })
        }, h = function (a, b) {
            return a.fold(c.none, function (a) {
                return c.some(d.range(a, b, b + 1))
            }, function (e) {
                return c.some(d.range(e, b, b + a.len()))
            })
        }, i = function (c) {
            return a.bind(c, function (a) {
                return a.fold(b.constant([]), b.constant([]), function (a) {
                    return [a]
                })
            })
        };
        return {count: f, dropUntil: g, gen: h, justText: i}
    }),g("9w", ["g", "90", "9n", "92"], function (a, b, c, d) {
        var e = function (b, d, f) {
            if (b.property().isText(d)) return [c.text(d, b)];
            if (b.property().isEmptyTag(d)) return [c.empty(d, b)];
            if (b.property().isElement(d)) {
                var g = b.property().children(d), h = b.property().isBoundary(d) ? [c.boundary(d, b)] : [],
                    i = void 0 !== f && f(d) ? [] : a.bind(g, function (a) {
                        return e(b, a, f)
                    });
                return h.concat(i).concat(h)
            }
            return []
        }, f = function (b, c, d) {
            var f = e(b, c, d), g = function (a, b) {
                return a
            };
            return a.map(f, function (a) {
                return a.fold(g, g, g)
            })
        }, g = function (a, c, f, g, h) {
            var i = e(a, g, h), j = d.dropUntil(i, c), k = d.count(j);
            return b.point(g, k + f)
        }, h = function (a, c, d, e) {
            return a.property().parent(c).fold(function () {
                return b.point(c, d)
            }, function (b) {
                return g(a, c, d, b, e)
            })
        }, i = function (a, c, d, e, f) {
            return a.up().predicate(c, e).fold(function () {
                return b.point(c, d)
            }, function (b) {
                return g(a, c, d, b, f)
            })
        };
        return {typed: e, items: f, extractTo: i, extract: h}
    }),g("9x", ["g", "p", "9w"], function (a, b, c) {
        var d = "\n", e = " ", f = function (a, b) {
            return "img" === b.property().name(a) ? e : d
        }, g = function (e, g, h) {
            var i = c.typed(e, g, h);
            return a.map(i, function (a) {
                return a.fold(b.constant(d), f, e.property().getText)
            }).join("")
        };
        return {from: g}
    }),g("9d", ["g", "p"], function (a, b) {
        var c = function (c, d, e) {
            var f = {len: void 0 !== e ? e : 0, list: []}, g = a.foldl(c, function (a, c) {
                var e = d(c, a.len);
                return e.fold(b.constant(a), function (b) {
                    return {len: b.finish(), list: a.list.concat([b])}
                })
            }, f);
            return g.list
        };
        return {make: c}
    }),g("9e", ["g", "n"], function (a, b) {
        var c = function (a, b) {
            return b >= a.start() && b <= a.finish()
        }, d = function (d, e) {
            var f = a.find(d, function (a) {
                return c(a, e)
            });
            return b.from(f)
        }, e = function (b, c) {
            return a.findIndex(b, function (a) {
                return a.start() === c
            })
        }, f = function (a, b) {
            var c = a[a.length - 1] && a[a.length - 1].finish() === b;
            return c ? a.length + 1 : -1
        }, g = function (a, b, c) {
            var d = e(a, b), g = e(a, c), h = g > -1 ? g : f(a, c);
            return d > -1 && h > -1 ? a.slice(d, h) : []
        }, h = function (c, d) {
            return b.from(a.find(c, d))
        };
        return {get: d, find: h, inUnit: c, sublist: g}
    }),g("9g", ["g", "1l", "p"], function (a, b, c) {
        var d = function (d, e) {
            return a.map(d, function (a) {
                return b.merge(a, {start: c.constant(a.start() + e), finish: c.constant(a.finish() + e)})
            })
        };
        return {translate: d}
    }),g("9f", ["g", "9e", "9g"], function (a, b, c) {
        var d = function (a, b, d) {
            var e = d(a, b);
            return c.translate(e, a.start())
        }, e = function (c, e, f) {
            return 0 === e.length ? c : a.bind(c, function (c) {
                var g = a.bind(e, function (a) {
                    return b.inUnit(c, a) ? [a - c.start()] : []
                });
                return g.length > 0 ? d(c, g, f) : [c]
            })
        };
        return {splits: e}
    }),g("94", ["9d", "9e", "9f", "9g"], function (a, b, c, d) {
        var e = function (b, c, d) {
            return a.make(b, c, d)
        }, f = function (a, c) {
            return b.get(a, c)
        }, g = function (a, c) {
            return b.find(a, c)
        }, h = function (a, b, d) {
            return c.splits(a, b, d)
        }, i = function (a, b) {
            return d.translate(a, b)
        }, j = function (a, c, d) {
            return b.sublist(a, c, d)
        };
        return {generate: e, get: f, find: g, splits: h, translate: i, sublist: j}
    }),g("9y", ["90", "9w", "92", "94"], function (a, b, c, d) {
        var e = function (e, f, g, h) {
            var i = b.typed(e, f, h), j = d.generate(i, c.gen), k = d.get(j, g);
            return k.map(function (b) {
                return a.point(b.element(), g - b.start())
            })
        };
        return {find: e}
    }),g("9m", ["9w", "9x", "9y"], function (a, b, c) {
        var d = function (b, c, d) {
            return a.typed(b, c, d)
        }, e = function (b, c, d) {
            return a.items(b, c, d)
        }, f = function (b, c, d, e) {
            return a.extract(b, c, d, e)
        }, g = function (b, c, d, e, f) {
            return a.extractTo(b, c, d, e, f)
        }, h = function (a, b, d, e) {
            return c.find(a, b, d, e)
        }, i = function (a, c, d) {
            return b.from(a, c, d)
        };
        return {extract: f, extractTo: g, all: e, from: d, find: h, toText: i}
    }),g("99", ["g", "9m", "9n", "9b", "9r"], function (a, b, c, d, e) {
        var f = function (f, g, h) {
            var i = a.bind(g, function (a) {
                return b.from(f, a, h)
            }), j = d.splitbyAdv(i, function (a) {
                return c.cata(a, function () {
                    return e.excludeWithout(a)
                }, function () {
                    return e.excludeWith(a)
                }, function () {
                    return e.include(a)
                })
            });
            return a.filter(j, function (a) {
                return a.length > 0
            })
        };
        return {group: f}
    }),g("9s", ["g", "n"], function (a, b) {
        var c = function (c, d, e) {
            var f = [d].concat(c.up().all(d)), g = [e].concat(c.up().all(e)), h = a.find(f, function (b) {
                return a.exists(g, function (a) {
                    return c.eq(a, b)
                })
            });
            return b.from(h)
        };
        return {common: c}
    }),g("9t", ["g"], function (a) {
        var b = ["table", "tbody", "thead", "tfoot", "tr", "ul", "ol"];
        return function (c) {
            var d = c.property(), e = function (b, c) {
                return d.parent(b).map(d.name).map(function (b) {
                    return !a.contains(c, b)
                }).getOr(!1)
            }, f = function (a) {
                return d.isText(a) && e(a, b)
            };
            return {validateText: f}
        }
    }),g("9a", ["g", "p", "9m", "9s", "9t"], function (a, b, c, d, e) {
        var f = function (c, d, e) {
            return a.findIndex(d, b.curry(c.eq, e))
        }, g = function (a, b, c, d, e) {
            return b < d ? a.slice(b + c, d + e) : a.slice(d + e, b + c)
        }, h = function (h, i, j, k, l) {
            return h.eq(i, k) ? [i] : d.common(h, i, k).fold(function () {
                return []
            }, function (d) {
                var m = [d].concat(c.all(h, d, b.constant(!1))), n = f(h, m, i), o = f(h, m, k),
                    p = n > -1 && o > -1 ? g(m, n, j, o, l) : [], q = e(h);
                return a.filter(p, q.validateText)
            })
        };
        return {range: h}
    }),g("91", ["99", "9a"], function (a, b) {
        var c = function (a, c, d, e, f) {
            return b.range(a, c, d, e, f)
        }, d = function (b, c, d) {
            return a.group(b, c, d)
        };
        return {range: c, group: d}
    }),g("9z", [], function () {
        var a = function (a) {
            var b = /^[a-zA-Z]/.test(a) ? "" : "e", c = a.replace(/[^\w]/gi, "-");
            return b + c
        };
        return {css: a}
    }),g("a0", ["g"], function (a) {
        var b = function (b, c) {
            if (0 === c.length) return [b];
            var d = a.foldl(c, function (a, c) {
                if (0 === c) return a;
                var d = b.substring(a.prev, c);
                return {prev: c, values: a.values.concat([d])}
            }, {prev: 0, values: []}), e = c[c.length - 1];
            return e < b.length ? d.values.concat(b.substring(e)) : d.values
        };
        return {splits: b}
    }),g("9u", ["9z", "a0"], function (a, b) {
        var c = function (a, c) {
            return b.splits(a, c)
        }, d = function (b) {
            return a.css(b)
        };
        return {cssSanitise: d, splits: c}
    }),g("9c", ["g", "n", "90", "94", "9u"], function (a, b, c, d, e) {
        var f = function (f, g, h) {
            var i = f.property().getText(g), j = a.filter(e.splits(i, h), function (a) {
                return a.length > 0
            });
            if (j.length <= 1) return [c.range(g, 0, i.length)];
            f.property().setText(g, j[0]);
            var k = d.generate(j.slice(1), function (a, d) {
                var e = f.create().text(a), g = c.range(e, d, d + a.length);
                return b.some(g)
            }, j[0].length), l = a.map(k, function (a) {
                return a.element()
            });
            return f.insert().afterAll(g, l), [c.range(g, 0, j[0].length)].concat(k)
        };
        return {subdivide: f}
    }),g("93", ["g", "p", "9c", "94"], function (a, b, c, d) {
        var e = function (e, f, g) {
            var h = a.bind(g, function (a) {
                return [a.start(), a.finish()]
            }), i = function (a, b) {
                return c.subdivide(e, a.element(), b)
            }, j = d.splits(f, h, i), k = function (c) {
                var f = d.sublist(j, c.start(), c.finish()), g = a.map(f, function (a) {
                    return a.element()
                }), h = a.map(g, e.property().getText).join("");
                return {elements: b.constant(g), word: c.word, exact: b.constant(h)}
            };
            return a.map(g, k)
        };
        return {separate: e}
    }),g("8s", [], function () {
        var a = function () {
            return "\ufeff"
        };
        return {zeroWidth: a}
    }),g("89", ["8s", "p"], function (a, b) {
        var c = "\\w'\\-\\u0100-\\u017F\\u00C0-\\u00FF" + a.zeroWidth() + "\\u2018\\u2019", d = "[^" + c + "]",
            e = "[" + c + "]";
        return {chars: b.constant(c), wordbreak: b.constant(d), wordchar: b.constant(e)}
    }),g("8a", ["4v"], function (a) {
        return function (b, c, d, e) {
            var f = function () {
                return new a(b, e.getOr("g"))
            };
            return {term: f, prefix: c, suffix: d}
        }
    }),g("8c", ["p", "n", "89", "8a"], function (a, b, c, d) {
        var e = function (c) {
            return d(c, a.constant(0), a.constant(0), b.none())
        }, f = function (a) {
            var e = "((?:^'?)|(?:" + c.wordbreak() + "+'?))" + a + "((?:'?$)|(?:'?" + c.wordbreak() + "+))",
                f = function (a) {
                    return a.length > 1 ? a[1].length : 0
                }, g = function (a) {
                    return a.length > 2 ? a[2].length : 0
                };
            return d(e, f, g, b.none())
        };
        return {token: e, word: f}
    }),g("8b", ["8c"], function (a) {
        var b = function (a) {
            return a.replace(/[-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&")
        }, c = function (c) {
            var d = b(c);
            return a.word(d)
        }, d = function (c) {
            var d = b(c);
            return a.token(d)
        };
        return {sanitise: b, word: c, token: d}
    }),g("7n", ["89", "8a", "8b", "8c"], function (a, b, c, d) {
        var e = function (a) {
            return c.word(a)
        }, f = function (a) {
            return c.token(a)
        }, g = function (a, c, d, e) {
            return b(a, c, d, e)
        }, h = function (a) {
            return d.word(a)
        }, i = function (a) {
            return d.token(a)
        }, j = function (a) {
            return c.sanitise(a)
        }, k = function () {
            return a.chars()
        }, l = function () {
            return a.wordbreak()
        }, m = function () {
            return a.wordchar()
        };
        return {
            safeword: e,
            safetoken: f,
            custom: g,
            unsafeword: h,
            unsafetoken: i,
            sanitise: j,
            chars: k,
            wordbreak: l,
            wordchar: m
        }
    }),g("9h", ["p"], function (a) {
        var b = function (b, c) {
            for (var d = c.term(), e = [], f = d.exec(b); f;) {
                var g = f.index + c.prefix(f), h = f[0].length - c.prefix(f) - c.suffix(f);
                e.push({start: a.constant(g), finish: a.constant(g + h)}), d.lastIndex = g + h, f = d.exec(b)
            }
            return e
        };
        return {all: b}
    }),g("9i", ["g", "1l", "9h", "12"], function (a, b, c, d) {
        var e = function (a) {
            var b = d.prototype.slice.call(a, 0);
            return b.sort(function (a, b) {
                return a.start() < b.start() ? -1 : b.start() < a.start() ? 1 : 0
            }), b
        }, f = function (d, f) {
            var g = a.bind(f, function (e) {
                var f = c.all(d, e.pattern());
                return a.map(f, function (a) {
                    return b.merge(e, {start: a.start, finish: a.finish})
                })
            });
            return e(g)
        };
        return {search: f}
    }),g("95", ["9h", "9i"], function (a, b) {
        var c = function (b, c) {
            return a.all(b, c)
        }, d = function (a, c) {
            return b.search(a, c)
        };
        return {findall: c, findmany: d}
    }),g("8r", ["g", "n", "8z", "90", "91", "92", "93", "7n", "94", "95"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = function (a, c) {
            return i.generate(c, function (c, e) {
                var f = e + a.property().getText(c).length;
                return b.from(d.range(c, e, f))
            })
        }, l = function (b, c, d, h) {
            var i = e.group(b, c, h), l = a.bind(i, function (c) {
                var e = f.justText(c), h = a.map(e, b.property().getText).join(""), i = j.findmany(h, d), l = k(b, e);
                return g.separate(b, l, i)
            });
            return l
        }, m = function (b, d, e, f) {
            var g = a.map(e, function (a) {
                var b = h.safeword(a);
                return c(a, b)
            });
            return l(b, d, g, f)
        }, n = function (a, b, d, e) {
            var f = c(d, h.safetoken(d));
            return l(a, b, [f], e)
        };
        return {safeWords: m, safeToken: n, run: l}
    }),g("88", ["8r"], function (a) {
        var b = function (b, c, d, e) {
            return a.run(b, c, d, e)
        }, c = function (b, c, d, e) {
            return a.safeWords(b, c, d, e)
        }, d = function (b, c, d, e) {
            return a.safeToken(b, c, d, e)
        };
        return {safeWords: c, safeToken: d, run: b}
    }),g("7m", ["87", "88"], function (a, b) {
        var c = a(), d = function (a, d, e) {
            return b.run(c, a, d, e)
        }, e = function (a, d, e) {
            return b.safeWords(c, a, d, e)
        }, f = function (a, d, e) {
            return b.safeToken(c, a, d, e)
        };
        return {safeWords: e, safeToken: f, run: d}
    }),g("1z", ["20"], function (a) {
        var b = function (b) {
            return a.first(b).isSome()
        }, c = function (b, c, d) {
            return a.ancestor(b, c, d).isSome()
        }, d = function (b, c) {
            return a.sibling(b, c).isSome()
        }, e = function (b, c) {
            return a.child(b, c).isSome()
        }, f = function (b, c) {
            return a.descendant(b, c).isSome()
        }, g = function (b, c, d) {
            return a.closest(b, c, d).isSome()
        };
        return {any: b, ancestor: c, sibling: d, child: e, descendant: f, closest: g}
    }),g("6l", ["g", "n", "7m", "7n", "2c", "j", "5d", "k", "1x", "29", "1d", "1z", "7o", "3f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n) {
        var o = /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[\-;:&=\.\+\$,\w]+@)?[A-Za-z0-9\.\-]+|(?:www\.|[\-;:&=\+\$,\w\.]+@)[A-Za-z0-9\.\-]+)(:[0-9]+)?((?:\/[\+~%\/\.\w\-_]*)?\??(?:[\-_.~*+=!&;:'%@?^${}()\w,]*)#?(?:[\-_.~*+=!&;:'%@?^${}()\w,\/]*))?)/g,
            p = o.source, q = 3, r = 9, s = function (a) {
                var b = e.immutable("word", "pattern"), f = d.unsafetoken(p), g = b("__INTERNAL__", f);
                return c.run(a, [g])
            }, t = function (a) {
                return !l.closest(a, "a")
            }, u = function (a) {
                return b.from(a[0]).filter(t).map(function (b) {
                    var c = h.fromTag("a");
                    return i.before(b, c), j.append(c, a), f.set(c, "href", m.get(c)), c
                })
            }, v = function (b) {
                var c = s(b);
                a.each(c, function (a) {
                    var b = a.exact();
                    (b.indexOf("@") < 0 || w(b)) && u(a.elements())
                })
            }, w = function (a) {
                var b = a.indexOf("://");
                return b >= q && b <= r
            }, x = function (b) {
                a.each(b, function (a) {
                    k.isElement(a) && g.getRaw(a, "position").isSome() && g.remove(a, "position")
                })
            }, y = function (b) {
                var c = a.filter(b, function (a) {
                    return "li" === k.name(a)
                });
                if (c.length > 0) {
                    var d = n.prevSiblings(c[0]), e = h.fromTag("ul");
                    if (i.before(b[0], e), d.length > 0) {
                        var f = h.fromTag("li");
                        i.append(e, f), j.append(f, d)
                    }
                    j.append(e, c)
                }
            };
        return {links: v, position: x, list: y}
    }),g("55", ["g", "6k", "6l", "6m", "6h", "3f"], function (a, b, c, d, e, f) {
        var g = function (b) {
            var d = f.children(b);
            a.each([c.links, c.position, c.list], function (a) {
                a(d)
            })
        }, h = function (a, c, f, h, i) {
            g(f);
            var j = e.get(f), k = b.derive(i, h, c);
            return d.go(a, j, k)
        };
        return {go: h, preprocess: g}
    }),g("6i", ["4q", "4r", "4y", "2w", "2t", "55", "2f", "28", "1f"], function (a, b, c, d, e, f, g, h, i) {
        var j = e.detect(), k = function (a, b, c, d) {
            try {
                var e = f.go(a, j, b, c, d), k = void 0 !== e && null !== e && e.length > 0, l = k ? h.fromHtml(e) : [];
                return g.value(l)
            } catch (m) {
                return i.error(m), g.error("errors.paste.process.failure")
            }
        }, l = function (e, f, g, h, i) {
            var j = k(e, f, h, g);
            return j.fold(function (b) {
                return a.error(b)
            }, function (e) {
                return a.sync(function (f) {
                    i.get(function (g) {
                        var h = c.findImages(e, g);
                        a.call(f, {response: d.paste(e, g, h), bundle: b.nu({})})
                    })
                })
            })
        };
        return {transfer: l}
    }),g("53", ["6i", "g", "o", "n", "1y", "3f"], function (a, b, c, d, e, f) {
        var g = function (b, c, d, e, f) {
            return a.transfer(b, c, e, d, f)
        }, h = function (g, h, i) {
            var j = !1, k = !0, l = function (a, b) {
                return void 0 === h || b ? d.none() : h.findClipboardTags(f.children(a))
            }, m = l(i, j).getOr([]);
            b.each(m, e.remove);
            var n = c.nu(function (a) {
                a([])
            });
            return a.transfer(g, i, j, k, n)
        };
        return {internal: h, external: g}
    }),g("6j", ["g", "k", "6h", "7o"], function (a, b, c, d) {
        var e = function (a) {
            var e = b.fromTag("div");
            return d.set(e, a), c.get(e)
        }, f = function (b) {
            var c = b.trim().split(/\n{2,}|(?:\r\n){2,}/), d = a.map(c, function (a) {
                return a.split(/\n|\r\n/).join("<br />")
            });
            return 1 === d.length ? d[0] : a.map(d, function (a) {
                return "<p>" + a + "</p>"
            }).join("")
        };
        return {encode: e, convert: f}
    }),g("54", ["4q", "6j", "4r", "31", "2w", "n", "23", "28", "11"], function (a, b, c, d, e, f, g, h, i) {
        var j = function (a) {
            return a.length > 0
        }, k = function (a) {
            return d.isValidData(a) ? g.findMap(a.types, function (b) {
                return "text/plain" === b ? f.some(a.getData(b)) : f.none()
            }) : f.none()
        }, l = function () {
            var a = i.clipboardData;
            return void 0 !== a ? f.some(a.getData("text")) : f.none()
        }, m = function (a) {
            var c = b.encode(a), d = b.convert(c), f = h.fromHtml(d);
            return e.paste(f, [], [])
        }, n = function (b) {
            return a.sync(function (f) {
                var g = d.isValidData(b) ? k : l, h = g(b).filter(j).fold(e.cancel, m);
                a.call(f, {response: h, bundle: c.nu({})})
            })
        };
        return {handle: n}
    }),g("34", ["4q", "4r", "4z", "51", "52", "53", "54", "2w", "2t", "55", "p", "k", "29", "3f", "1f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o) {
        var p = i.detect(), q = function (a, b, c) {
            return g.handle(a)
        }, r = function (b, c) {
            var d = function (b, d, e) {
                var f = l.fromTag("div");
                m.append(f, b), j.preprocess(f);
                var g = n.children(f);
                return a.pure({response: h.paste(g, d, e), bundle: c.bundle()})
            }, e = k.curry(a.pass, c);
            return h.cata(c.response(), e, d, e, d)
        }, s = function (a, b) {
            return function (d, e) {
                var g = e.bundle();
                return c.proxyBin(g).handle("There was no proxy bin setup. Ensure you have run proxyStep first.", function (c) {
                    var d = n.owner(a);
                    return f.internal(d, b, c)
                })
            }
        }, t = function (a, b) {
            return function (d, e) {
                var g = e.bundle();
                return c.proxyBin(g).handle("There was no proxy bin setup. Ensure you have run proxyStep first.", function (d) {
                    var e = c.merging(g), h = c.isWord(g), i = c.isInternal(g), j = c.backgroundAssets(g),
                        k = n.owner(a);
                    return i ? f.internal(k, b, d) : f.external(k, d, e, h, j)
                })
            }
        }, u = function () {
            return function (b, c) {
                return a.error("errors.local.images.disallowed")
            }
        }, v = function () {
            return function (b, c) {
                if (p.browser.isSafari()) {
                    var d = p.deviceType.isWebView() ? "webview.imagepaste" : "safari.imagepaste";
                    return a.error(d)
                }
                return e.handle(b)
            }
        }, w = function (a) {
            return function (b, e) {
                var f = c.mergeOffice(e.bundle());
                return d.handle(b, f, a)
            }
        }, x = function (b, c) {
            return a.cancel()
        }, y = function (c) {
            return function (d, e) {
                var f = b.merge(e.bundle(), b.nu(c));
                return a.pure({response: e.response(), bundle: f})
            }
        };
        return {plain: q, autolink: r, noImages: u, images: v, internal: s, external: t, gwt: w, setBundle: y, none: x}
    }),g("56", ["5c", "p"], function (a, b) {
        var c = a.resolve("smartpaste-eph-bin");
        return {binStyle: b.constant(c)}
    }),g("57", ["6m", "j", "6h", "6n"], function (a, b, c, d) {
        var e = function (a, c) {
            return d.descendant(a, function (a) {
                return !!b.has(a, "style") && b.get(a, "style").indexOf("mso-") > -1
            })
        }, f = function (b, d) {
            var e = c.get(b);
            return a.isWordContent(e, d)
        }, g = function (a, b) {
            var c = a.browser, d = c.isIE() && c.version.major >= 11 ? e : f;
            return d(b, a)
        };
        return {isWord: g}
    }),g("35", ["4q", "4r", "56", "2t", "57", "27", "3f"], function (a, b, c, d, e, f, g) {
        var h = d.detect();
        return function (d, i, j, k, l) {
            return function (m, n) {
                var o = l(), p = n.response();
                return a.sync(function (l) {
                    var n = d(j);
                    n.events.after.bind(function (d) {
                        var j = d.container();
                        i(j), f.add(j, c.binStyle());
                        var m = e.isWord(h, j), n = g.children(j), q = k.findClipboardTags(n, m).isSome();
                        a.call(l, {
                            response: p,
                            bundle: b.nu({isWord: m, isInternal: q, proxyBin: j, backgroundAssets: o})
                        })
                    }), o.convert(m.data()), n.run()
                })
            }
        }
    }),g("8d", ["78", "79", "12", "62", "13", "8o"], function (a, b, c, d, e, f) {
        var g = function (a) {
            for (var b = new c(a.length / 2), e = 0; e < a.length; e += 2) {
                var g = a.substr(e, 2), h = d.floor(e / 2);
                b[h] = f(g, 16)
            }
            return b
        }, h = function (c, d) {
            if (0 === c.length) throw"Zero length content passed to Hex conversion";
            var e = g(c), f = b(e);
            return a([f], {type: d})
        };
        return {convert: h}
    }),g("7q", ["1m"], function (a) {
        var b = a.generate([{unsupported: ["id", "message"]}, {supported: ["id", "contentType", "blob"]}]),
            c = function (a, b, c) {
                return a.fold(b, c)
            };
        return {unsupported: b.unsupported, supported: b.supported, cata: c}
    }),g("8e", ["p", "n"], function (a, b) {
        var c = "{\\pict{", d = "i", e = "{\\shp{", f = "s", g = function (a, b, c) {
            return b.indexOf(a, c)
        }, h = function (c, d, e, f, g) {
            return c === -1 || d === -1 ? b.none() : b.some({
                start: a.constant(c),
                end: a.constant(d),
                bower: e,
                regex: a.constant(f),
                idRef: a.constant(g)
            })
        }, i = function (a, b, c) {
            return function () {
                return a.substring(b, c)
            }
        }, j = function (a, b) {
            if (b === -1) return b;
            var c, d, e = 0, f = a.length;
            do if (c = a.indexOf("{", b), d = a.indexOf("}", b), d > c && c !== -1 ? (b = c + 1, ++e) : (c > d || c < 0) && d !== -1 && (b = d + 1, --e), b > f || d === -1) return -1; while (e > 0);
            return b
        }, k = function (a, b, c, e) {
            var f = i(a, c, e), g = /[^a-fA-F0-9]([a-fA-F0-9]+)\}$/;
            return h(c, e, f, g, d)
        }, l = function (a, b, c, d) {
            var e = i(a, c, d), g = /([a-fA-F0-9]{64,})(?:\}.*)/;
            return h(c, d, e, g, f)
        }, m = function (d, f) {
            var h = g(c, d, f), i = j(d, h), m = g(e, d, f), n = j(d, m), o = a.curry(l, d, f, m, n),
                p = a.curry(k, d, f, h, i);
            return h === -1 && m === -1 ? b.none() : h === -1 ? o() : m === -1 ? p() : m < h && n > i ? p() : h < m && i > n ? o() : h < m ? p() : m < h ? o() : b.none()
        };
        return {identify: m, endBracket: j}
    }),g("7p", ["8d", "7q", "8e", "n", "2f"], function (a, b, c, d, e) {
        var f = function (a, b) {
            return c.identify(a, b)
        }, g = function (a) {
            return a.indexOf("\\pngblip") >= 0 ? e.value("image/png") : a.indexOf("\\jpegblip") >= 0 ? e.value("image/jpeg") : e.error("errors.imageimport.unsupported")
        }, h = function (a) {
            return a.replace(/\r/g, "").replace(/\n/g, "")
        }, i = function (a, b) {
            var c = a.match(b);
            return c && c[1] && c[1].length % 2 === 0 ? e.value(c[1]) : e.error("errors.imageimport.invalid")
        }, j = function (a) {
            var b = a.match(/\\shplid(\d+)/);
            return null !== b ? d.some(b[1]) : d.none()
        }, k = function (c) {
            var d = c.bower(), e = c.regex();
            return j(d).map(function (f) {
                var h = c.idRef() + f;
                return g(d).fold(function (a) {
                    return b.unsupported(h, a)
                }, function (c) {
                    return i(d, e).fold(function (a) {
                        return b.unsupported(h, a)
                    }, function (d) {
                        return b.supported(h, c, a.convert(d, c))
                    })
                })
            })
        }, l = function (a) {
            for (var b = [], c = function () {
                return a.length
            }, d = function (a) {
                var c = k(a);
                return b = b.concat(c.toArray()), a.end()
            }, e = 0; e < a.length;) e = f(a, e).fold(c, d);
            return b
        }, m = function (a) {
            var b = h(a);
            return l(b)
        };
        return {nextBower: f, extractId: j, extractContentType: g, extractHex: i, images: m}
    }),g("6o", ["7p", "7q", "2f"], function (a, b, c) {
        var d = function (b) {
            return a.images(b)
        }, e = function (a) {
            return b.cata(a, function (a, b) {
                return a
            }, function (a, b, c) {
                return a
            })
        }, f = function (a) {
            return b.cata(a, function (a, b) {
                return c.error(b)
            }, function (a, b, d) {
                return c.value(d)
            })
        };
        return {images: d, toId: e, toBlob: f}
    }),g("6p", ["6o", "4y", "g", "i", "n", "j", "27", "1f"], function (a, b, c, d, e, f, g, h) {
        var i = function (b, d, f) {
            var g = c.find(d, function (c) {
                return a.toId(c) === b
            });
            return e.from(g)
        }, j = function (a, b, c) {
            return e.from(b[c])
        }, k = {local: j, code: i}, l = function (b) {
            var d = [], e = function (b) {
                return !c.exists(d, function (c) {
                    return a.toId(c) === b
                })
            };
            return c.bind(b, function (b) {
                var c = a.toId(b);
                return e(c) ? (d.push(b), [b]) : []
            })
        }, m = function (i, j, m, n) {
            var o = l(j), p = [], q = !1, r = c.bind(i, function (b, c) {
                var d = f.get(b, "data-image-type"), i = f.get(b, "data-image-id");
                g.remove(b, "rtf-data-image"), f.remove(b, "data-image-type"), f.remove(b, "data-image-id");
                var j = void 0 !== k[d] ? k[d] : e.none;
                return j(i, o, c).fold(function () {
                    return h.log("WARNING: unable to find data for image ", b.dom()), []
                }, function (c) {
                    return a.toBlob(c).fold(function (a) {
                        return q = !0, f.set(b, "alt", n(a)), []
                    }, function (a) {
                        return p.push(b), [a]
                    })
                })
            });
            d.multiple(r).get(function (a) {
                var c = b.updateSources(a, p);
                m(a, c, q)
            })
        };
        return {convert: m}
    }),g("59", ["6o", "6p", "5a", "21", "22", "3f"], function (a, b, c, d, e, f) {
        return function (g) {
            var h = g.translations, i = e.create({
                insert: d(["elements", "assets", "correlated"]),
                incomplete: d(["elements", "assets", "correlated", "message"])
            }), j = function (d, e, g, j, k) {
                var l = a.images(j), m = c.find(d);
                b.convert(m, l, function (a, b, c) {
                    var h = f.children(d), j = a.concat(e), l = b.concat(g);
                    c ? i.trigger.incomplete(h, j, l, "errors.imageimport.failed") : i.trigger.insert(h, j, l), k()
                }, h)
            };
            return {events: i.registry, processRtf: j}
        }
    }),g("58", ["59", "g", "5a", "21", "22", "1y", "3f"], function (a, b, c, d, e, f, g) {
        return function (h, i) {
            var j = e.create({
                error: d(["message"]),
                insert: d(["elements", "assets", "correlated"]),
                incomplete: d(["elements", "assets", "correlated", "message"])
            }), k = a(i);
            k.events.incomplete.bind(function (a) {
                j.trigger.incomplete(a.elements(), a.assets(), a.correlated(), a.message())
            }), k.events.insert.bind(function (a) {
                j.trigger.insert(a.elements(), a.assets(), a.correlated())
            });
            var l = function (a, d, e) {
                var l = function (b) {
                    k.processRtf(a, d, e, b.rtf(), b.hide())
                }, m = function (h) {
                    var i = c.find(a);
                    b.each(i, f.remove), j.trigger.incomplete(g.children(a), d, e, h)
                }, n = function () {
                    var h = c.find(a);
                    b.each(h, f.remove), j.trigger.insert(g.children(a), d, e)
                }, o = function (a) {
                    m(a.message())
                };
                if (i.allowLocalImages === !0 && i.enableFlashImport === !0) {
                    var p = h(i);
                    p.events.response.bind(l), p.events.cancel.bind(n), p.events.failed.bind(o), p.events.error.bind(o), p.open()
                } else m("errors.local.images.disallowed")
            };
            return {events: j.registry, gordon: l}
        }
    }),g("36", ["4q", "4r", "58", "59", "2w", "g", "5a", "p", "n", "z", "k", "29", "1y", "3f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n) {
        return function (o, p) {
            var q = c(o, p), r = d(p), s = j(i.none()), t = function (c) {
                s.get().each(function (d) {
                    a.call(d, {response: c, bundle: b.nu({})})
                })
            };
            return q.events.insert.bind(function (a) {
                t(e.paste(a.elements(), a.assets(), a.correlated()))
            }), q.events.incomplete.bind(function (a) {
                t(e.incomplete(a.elements(), a.assets(), a.correlated(), a.message()))
            }), q.events.error.bind(function (a) {
                var b = a.message(), c = e.error(b);
                t(c)
            }), r.events.insert.bind(function (a) {
                t(e.paste(a.elements(), a.assets(), a.correlated()))
            }), r.events.incomplete.bind(function (a) {
                t(e.incomplete(a.elements(), a.assets(), a.correlated(), a.message()))
            }), function (b, c) {
                return a.sync(function (d) {
                    var j = function () {
                        a.call(d, {response: c.response(), bundle: c.bundle()})
                    }, o = function (a, c, o) {
                        s.set(i.some(d));
                        var u = k.fromTag("div");
                        l.append(u, a), b.rtf().fold(function () {
                            g.exists(u) ? q.gordon(u, c, o) : j()
                        }, function (a) {
                            if (p.allowLocalImages === !0) r.processRtf(u, c, o, a, h.noop); else {
                                var b = g.find(u), d = n.children(u);
                                b.length > 0 ? (f.each(b, m.remove), t(e.incomplete(d, c, o, "errors.local.images.disallowed"))) : t(e.paste(d, c, o))
                            }
                        })
                    };
                    e.cata(c.response(), j, o, j, o)
                })
            }
        }
    }),g("1p", ["2z", "30", "31", "32", "33", "34", "35", "36", "2t", "p", "n", "2c", "37", "11"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n) {
        var o = l.immutable("data", "rtf"), p = i.detect(), q = "^image/", r = "file", s = [q, r], t = "html",
            u = "rtf", v = function (a) {
                return m.contains(a, "<html") && (m.contains(a, 'xmlns:o="urn:schemas-microsoft-com:office:office"') || m.contains(a, 'xmlns:x="urn:schemas-microsoft-com:office:excel"'))
            }, w = function (a) {
                var b = a.clipboardData;
                return c.isValidData(b) ? c.getFlavor(b.types, t).bind(function (c) {
                    var d = b.getData(c.type);
                    return v(d) ? k.some(o(d, C(a))) : k.none()
                }) : k.none()
            }, x = function (a) {
                return o(a, k.none())
            }, y = function (a) {
                if (p.browser.isIE() || p.browser.isFirefox()) return k.none();
                if (c.isValidData(a.clipboardData)) {
                    var b = a.clipboardData;
                    return c.getPreferredFlavor(s, b.types).map(function (a) {
                        return b.items;
                    })
                }
                return k.none()
            }, z = function (a, b) {
                var d = c.isValidData(b.clipboardData) ? b.clipboardData.types : [];
                return c.getFlavor(d, a.clipboardType()).map(function (a) {
                    return o(b, k.none())
                })
            }, A = function (a) {
                return p.browser.isIE() ? k.some(n.clipboardData) : c.isValidData(a.clipboardData) ? k.some(a.clipboardData) : k.none()
            }, B = function (a) {
                if (p.browser.isSpartan()) return k.none();
                var b = a.clipboardData, d = c.isValidData(b) ? b.types : [];
                return 1 === d.length && "text/plain" === d[0] ? k.some(b) : k.none()
            }, C = function (a) {
                var b = a.clipboardData;
                return c.isValidData(b) ? c.getFlavor(b.types, u).bind(function (a) {
                    var c = b.getData(a.type);
                    return c.length > 0 ? k.some(c) : k.none()
                }) : k.none()
            }, D = function (a, b, c, d) {
                return {label: j.constant(a), getAvailable: b, steps: j.constant(c), capture: j.constant(d)}
            }, E = function (c, d, h, i) {
                var k = j.curry(z, i);
                return D("Within Textbox.io (tables) pasting", k, [a.normal(g(d, c, h, i, b.ignore)), a.normal(e.fixed(!0, !0)), a.normal(f.internal(h, i))], !1)
            }, F = function (c, i, j, k, l, m, n) {
                return D("Outside of Textbox.io pasting (could be internal but another browser)", x, [a.normal(g(k, j, l, n, b.background)), a.normal(e.fromConfigIfExternal(c, i)), a.normal(f.external(l, n)), a.blocking(h(m, i)), a.normal(d(i))], !1)
            }, G = function (b, c, d, g) {
                return D("GWT pasting", w, [a.normal(f.setBundle({isWord: !0})), a.normal(e.fromConfig(c, d)), a.normal(f.gwt(b)), a.blocking(h(g, d))], !0)
            }, H = function (b) {
                return D("Image pasting", y, [a.normal(b.allowLocalImages === !1 ? f.noImages(j.noop) : f.images())], !0)
            }, I = function () {
                return D("Only plain text is available to paste", B, [a.normal(f.plain), a.normal(f.autolink)], !0)
            }, J = function () {
                return D("Plain text pasting", A, [a.normal(f.plain), a.normal(f.autolink)], !0)
            }, K = function () {
                return D("There is no valid way to paste", k.some, [a.normal(f.none)], !1)
            };
        return {internal: E, pastiche: F, gwt: G, image: H, text: J, onlyText: I, none: K}
    }),g("5b", ["5c", "2t", "j", "27", "k", "6h", "29"], function (a, b, c, d, e, f, g) {
        var h = function () {
            var a = b.detect(), c = a.os.isOSX();
            return c ? ["\u2318"] : ["Ctrl"]
        }, i = function (a) {
            return e.fromHtml("<p>" + a("cement.dialog.flash.press-escape") + "</p>")
        }, j = function (b) {
            var c = e.fromTag("div");
            d.add(c, a.resolve("flashbin-helpcopy"));
            var f = h(), j = e.fromHtml("<p>" + b("cement.dialog.flash.trigger-paste") + "</p>"),
                k = e.fromHtml('<div><span class="ephox-polish-help-kbd">' + f + '</span> + <span class="ephox-polish-help-kbd">V</span></div>');
            return d.add(k, a.resolve("flashbin-helpcopy-kbd")), g.append(c, [j, k, i(b)]), c
        }, k = function (b) {
            var c = e.fromTag("div");
            d.add(c, a.resolve("flashbin-helpcopy"));
            var f = e.fromHtml("<p>" + b("cement.dialog.flash.missing") + "</p>");
            return g.append(c, [f, i(b)]), c
        }, l = function (b) {
            var h = e.fromTag("div");
            d.add(h, a.resolve("flashbin-loading"));
            var i = e.fromTag("div");
            d.add(i, a.resolve("flashbin-loading-spinner"));
            var j = e.fromTag("p"), k = b("loading.wait");
            return f.set(j, k), c.set(j, "aria-label", k), g.append(h, [i, j]), h
        };
        return {paste: j, noflash: k, indicator: l}
    }),h("5e", navigator),g("38", ["5b", "5c", "2t", "p", "27", "5d", "k", "1x", "29", "5e"], function (a, b, c, d, e, f, g, h, i, j) {
        var k = c.detect(), l = function () {
            return new ActiveXObject("ShockwaveFlash.ShockwaveFlash")
        }, m = function () {
            try {
                var a = k.browser.isIE() ? l() : j.plugins["Shockwave Flash"];
                return void 0 !== a
            } catch (b) {
                return !1
            }
        }, n = function (b, c, e, f) {
            var g = a.noflash(f);
            return h.append(b, g), {reset: d.noop}
        }, o = function (b, c, d, e) {
            var g = a.paste(e), h = a.indicator(e);
            i.append(b, [h, g, c.element()]);
            var j = function () {
                f.setAll(h, {height: "0", padding: "0"})
            }, k = function () {
                f.set(g, "display", "block"), f.set(h, "display", "none"), d()
            }, l = function () {
                f.set(g, "display", "none"), f.set(h, "display", "block"), f.remove(h, "height"), f.remove(h, "padding"), d()
            };
            return c.events.spin.bind(l), c.events.reset.bind(k), c.events.hide.bind(j), {reset: k}
        };
        return function (a, c, f) {
            var h = g.fromTag("div"), i = "flashbin-wrapper-" + (k.os.isOSX() ? "cmd" : "ctrl");
            e.add(h, b.resolve(i));
            var j = m() ? o : n, l = j(h, a, c, f.translations);
            return {element: d.constant(h), reset: l.reset}
        }
    }),h("5k", clearInterval),h("5m", setInterval),g("5f", ["n", "21", "22", "z", "5k", "5m"], function (a, b, c, d, e, f) {
        var g = function () {
            var g = d(a.none()), h = c.create({crashed: b([]), timeout: b([])}), i = function (b, c, d, i) {
                var j = c, k = f(function () {
                    d() ? e(k) : j <= 0 ? (h.trigger.timeout(), e(k)) : i() && (e(k), h.trigger.crashed()), j--
                }, b);
                g.set(a.some(k))
            }, j = function () {
                g.get().each(function (a) {
                    e(a)
                })
            };
            return {start: i, stop: j, events: h.registry}
        };
        return {responsive: g}
    }),g("5g", ["1b", "g", "5k", "5m"], function (a, b, c, d) {
        return function (e, f, g) {
            var h = function (c) {
                return b.forall(f, function (b) {
                    return a.isFunction(c[b])
                })
            }, i = function () {
                var b = e.dom();
                a.isFunction(b.PercentLoaded) && 100 === b.PercentLoaded() && h(b) && (l(), g())
            }, j = !0, k = d(i, 500), l = function () {
                j && (c(k), j = !1)
            };
            return {stop: l}
        }
    }),g("6t", ["72"], function (a) {
        var b = function (a, b) {
            return void 0 !== a[b] && null !== a[b] || (a[b] = {}), a[b]
        }, c = function (c, d) {
            for (var e = d || a, f = c.split("."), g = 0; g < f.length; ++g) e = b(e, f[g]);
            return e
        };
        return {namespace: c}
    }),g("5h", ["6t"], function (a) {
        var b = function (b) {
            var c = a.namespace(b);
            c.callbacks = {};
            var d = 0, e = function () {
                var a = "callback_" + d;
                return d++, a
            }, f = function (a) {
                return b + ".callbacks." + a
            }, g = function (a, b) {
                var d = e();
                return c.callbacks[d] = function () {
                    b || j(d), a.apply(null, arguments)
                }, f(d)
            }, h = function (a) {
                return g(a, !1)
            }, i = function (a) {
                return g(a, !0)
            }, j = function (a) {
                var b = a.substring(a.lastIndexOf(".") + 1);
                void 0 !== c.callbacks[b] && delete c.callbacks[b]
            };
            return c.ephemeral = h, c.permanent = i, c.unregister = j, c
        };
        return {install: b}
    }),g("5j", ["p", "n", "3n", "k", "6n", "3f", "1g"], function (a, b, c, d, e, f, g) {
        var h = function (a) {
            a.dom().focus()
        }, i = function (a) {
            a.dom().blur()
        }, j = function (a) {
            var b = f.owner(a).dom();
            return a.dom() === b.activeElement
        }, k = function (a) {
            var c = void 0 !== a ? a.dom() : g;
            return b.from(c.activeElement).map(d.fromDom)
        }, l = function (b) {
            var d = f.owner(b), g = k(d).filter(function (d) {
                return e.closest(d, a.curry(c.eq, b))
            });
            g.fold(function () {
                h(b)
            }, a.noop)
        }, m = function (a) {
            return k(f.owner(a)).filter(function (b) {
                return a.dom().contains(b.dom())
            })
        };
        return {hasFocus: j, focus: h, blur: i, active: k, search: m, focusInside: l}
    }),h("5l", clearTimeout),h("5n", unescape),g("39", ["5f", "5g", "5c", "g", "1c", "1s", "2t", "5h", "p", "n", "21", "22", "5i", "27", "5d", "k", "5j", "1x", "5k", "5l", "1f", "5m", "x", "5n", "11"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y) {
        var z = h.install("ephox.flash"), A = g.detect(), B = j.none();
        return function (g) {
            var h = l.create({
                response: k(["rtf"]),
                spin: k([]),
                cancel: k([]),
                error: k(["message"]),
                reset: k([]),
                hide: k([]),
                failed: k(["message"])
            }), j = !1, s = p.fromTag("div");
            n.add(s, c.resolve("flashbin-target"));
            var v = a.responsive();
            v.events.crashed.bind(function () {
                h.trigger.failed("flash.crashed")
            }), v.events.timeout.bind(function () {
                h.trigger.failed("flash.crashed")
            });
            var C = function (a) {
                v.stop(), w(function () {
                    "" === a ? h.trigger.error("flash.crashed") : h.trigger.response(x(a))
                }, 0)
            }, D = function () {
                if (N.stop(), !j) {
                    j = !0;
                    try {
                        var a = L.dom();
                        e.each(J, function (b, c) {
                            var d = a[c];
                            if (void 0 === d) throw'Flash object does not have the method "' + c + '"';
                            d.call(a, b)
                        }), h.trigger.reset(), S(), P()
                    } catch (b) {
                        u.log("Flash dialog - Error during load: ", b)
                    }
                }
            }, E = z.permanent(D), F = function () {
                return !m.inBody(L)
            }, G = function () {
                return !L.dom().SetVariable
            }, H = function () {
                v.start(1e3, 10, F, G), h.trigger.spin()
            }, I = function (a) {
                v.stop(), h.trigger.error(a)
            }, J = {
                setSpinCallback: z.permanent(H),
                setPasteCallback: z.permanent(C),
                setEscapeCallback: z.permanent(h.trigger.cancel),
                setErrorCallback: z.permanent(I),
                setStartPasteCallback: z.permanent(i.noop)
            }, K = function () {
                var a = g.replace(/^https?:\/\//, "//"), b = "onLoad=" + E,
                    c = '    <param name="allowscriptaccess" value="always">    <param name="wmode" value="opaque">    <param name="FlashVars" value="' + b + '">';
                if (A.browser.isIE() && 10 === A.browser.version.major) {
                    var d = f.generate("flash-bin");
                    return p.fromHtml('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="' + d + '"><param name="movie" value="' + a + '">' + c + "</object>")
                }
                return p.fromHtml('<object type="application/x-shockwave-flash" data="' + a + '">' + c + "</object>")
            }, L = K(), M = function () {
                o.setAll(L, {width: "2px", height: "2px"})
            };
            M();
            var N = b(L, e.keys(J), D);
            r.append(s, L);
            var O = function () {
                return s
            }, P = function () {
                A.browser.isFirefox() && y.getSelection().removeAllRanges(), q.focus(L)
            }, Q = null, R = function () {
                n.add(s, c.resolve("flash-activate")), o.remove(L, "height"), o.remove(L, "width"), h.trigger.hide()
            }, S = function () {
                t(Q), n.remove(s, c.resolve("flash-activate")), M()
            }, T = function () {
                Q = w(R, 3e3), h.trigger.spin(), o.set(s, "display", "block"), P()
            }, U = function () {
                o.set(s, "display", "none"), B.each(function (a) {
                    d.each(a, function (a) {
                        a.unbind()
                    })
                })
            }, V = function () {
                U(), d.each(e.values(J), function (a) {
                    z.unregister(a)
                }), z.unregister(E), N.stop()
            };
            return {focus: P, element: O, activate: T, deactivate: U, destroy: V, events: h.registry}
        }
    }),g("1q", ["38", "39", "21", "22", "w", "k", "11"], function (a, b, c, d, e, f, g) {
        return function (h, i) {
            var j = i.translations, k = d.create({
                response: c(["rtf", "hide"]),
                cancel: c([]),
                error: c(["message"]),
                failed: c(["message"])
            }), l = function () {
                var c = b(i.swf);
                c.deactivate();
                var d = f.fromDom(g), l = e.bind(d, "mouseup", c.focus), m = function () {
                    q()
                }, n = function () {
                    m(), k.trigger.cancel()
                };
                c.events.cancel.bind(n), c.events.response.bind(function (a) {
                    k.trigger.response(a.rtf(), m)
                }), c.events.error.bind(function (a) {
                    m(), k.trigger.error(a.message())
                }), c.events.failed.bind(function (a) {
                    m(), k.trigger.failed(a.message())
                });
                var o = h();
                o.setTitle(j("cement.dialog.flash.title"));
                var p = a(c, o.reflow, i);
                p.reset(), o.setContent(p.element()), o.events.close.bind(n), o.show(), c.activate();
                var q = function () {
                    l.unbind(), c.destroy(), o.destroy()
                }
            };
            return {open: l, events: k.registry}
        }
    }),g("5p", [], function () {
        var a = function (a, b) {
            return d(function (c, d, e) {
                return d(a, b)
            })
        }, b = function (a) {
            return d(function (b, c, d) {
                return b(a)
            })
        }, c = function (a) {
            return d(function (b, c, d) {
                return d(a)
            })
        }, d = function (a) {
            return {fold: a}
        };
        return {on: a, before: b, after: c}
    }),g("5o", ["5p", "2c", "k"], function (a, b, c) {
        var d = b.immutable("start", "soffset", "finish", "foffset"),
            e = b.immutable("start", "soffset", "finish", "foffset"), f = b.immutable("start", "finish"),
            g = function (b) {
                var d = c.fromDom(b.startContainer), e = c.fromDom(b.endContainer);
                return f(a.on(d, b.startOffset), a.on(e, b.endOffset))
            };
        return {read: d, general: e, write: f, writeFromNative: g}
    }),g("5q", [], function () {
        var a = function (a, b) {
            if (a.getSelection) return b(a, a.getSelection());
            throw"No selection model supported."
        };
        return {run: a}
    }),g("6v", ["3n", "3f"], function (a, b) {
        var c = function (c, d, e, f) {
            var g = b.owner(c), h = g.dom().createRange();
            h.setStart(c.dom(), d), h.setEnd(e.dom(), f);
            var i = a.eq(c, e) && d === f;
            return h.collapsed && !i
        };
        return {after: c}
    }),g("6u", ["5o", "6v", "k", "3f"], function (a, b, c, d) {
        var e = function (a) {
            return b.after(c.fromDom(a.anchorNode), a.anchorOffset, c.fromDom(a.focusNode), a.focusOffset)
        }, f = function (b, d) {
            var f = c.fromDom(d.startContainer), g = c.fromDom(d.endContainer);
            return e(b) ? a.read(g, d.endOffset, f, d.startOffset) : a.read(f, d.startOffset, g, d.endOffset)
        }, g = function (a) {
            return e(a)
        }, h = function (a, b, c, e) {
            return function (f) {
                if (f.extend) f.collapse(a.dom(), b), f.extend(c.dom(), e); else {
                    var g = d.owner(a).dom().createRange();
                    g.setStart(c.dom(), e), g.setEnd(a.dom(), b), f.removeAllRanges(), f.addRange(g)
                }
            }
        }, i = function (a, c, d, e) {
            return b.after(a, c, d, e)
        }, j = function () {
            return {flip: f, isRtl: g}
        }, k = function () {
            return {flip: h, isRtl: i}
        };
        return {read: j, write: k}
    }),g("5s", ["5o", "6u", "n", "6v", "k"], function (a, b, c, d, e) {
        var f = function (b, d) {
            var f = h(b, d.start(), d.finish());
            if (f.collapsed === !0) {
                var g = h(b, d.finish(), d.start());
                return g.collapsed === !0 ? c.none() : c.some(a.general(e.fromDom(g.endContainer), g.endOffset, e.fromDom(g.startContainer), g.startOffset))
            }
            return c.none()
        }, g = function (a, b) {
            var c = h(a, b.start(), b.finish());
            return c.collapsed === !0 ? h(a, b.finish(), b.start()) : c
        }, h = function (a, b, c) {
            var d = m(a);
            return b.fold(function (a) {
                d.setStartBefore(a.dom())
            }, function (a, b) {
                d.setStart(a.dom(), b)
            }, function (a) {
                d.setStartAfter(a.dom())
            }), c.fold(function (a) {
                d.setEndBefore(a.dom())
            }, function (a, b) {
                d.setEnd(a.dom(), b)
            }, function (a) {
                d.setEndAfter(a.dom())
            }), d
        }, i = function (a, b) {
            return h(a, b.start(), b.finish())
        }, j = function (a, b, c, e, f) {
            var g = d.after(b, c, e, f), h = a.document.createRange();
            return g ? (h.setStart(e.dom(), f), h.setEnd(b.dom(), c)) : (h.setStart(b.dom(), c), h.setEnd(e.dom(), f)), h
        }, k = function (a, b) {
            var c = i(a, b);
            return function (a) {
                a.addRange(c)
            }
        }, l = function (a, c) {
            var d = f(a, c);
            return d.fold(function () {
                return k(a, c)
            }, function (a) {
                return b.write().flip(a.start(), a.soffset(), a.finish(), a.foffset())
            })
        }, m = function (a) {
            return a.document.createRange()
        };
        return {create: m, build: l, toNative: i, forceRange: g, toExactNative: j}
    }),g("5r", ["g", "5s", "k", "1d", "2a", "2h"], function (a, b, c, d, e, f) {
        var g = function (a, b, c) {
            return a.selectNodeContents(c.dom()), a.compareBoundaryPoints(b.END_TO_START, b) < 1 && a.compareBoundaryPoints(b.START_TO_END, b) > -1
        }, h = function (b, c, d, h) {
            var i = b.document.createRange(), j = f.is(c, h) ? [c] : [], k = j.concat(e.descendants(c, h));
            return a.filter(k, function (a) {
                return g(i, d, a)
            })
        }, i = function (a, e, f) {
            var g = b.forceRange(a, e), i = c.fromDom(g.commonAncestorContainer);
            return d.isElement(i) ? h(a, i, g, f) : []
        };
        return {find: i}
    }),g("6w", ["g", "5o", "5p", "1d"], function (a, b, c, d) {
        var e = function (b, e) {
            var f = d.name(b);
            return "input" === f ? c.after(b) : a.contains(["br", "img"], f) ? 0 === e ? c.before(b) : c.after(b) : c.on(b, e)
        }, f = function (a) {
            var d = a.start().fold(c.before, e, c.after), f = a.finish().fold(c.before, e, c.after);
            return b.write(d, f)
        };
        return {beforeSpecial: e, preprocess: f}
    }),g("6x", ["g", "k", "1g"], function (a, b, c) {
        var d = function (d, e) {
            var f = e || c, g = f.createDocumentFragment();
            return a.each(d, function (a) {
                g.appendChild(a.dom())
            }), b.fromDom(g)
        };
        return {fromElements: d}
    }),g("5t", ["5o", "6u", "5s", "6w", "n", "k", "6x"], function (a, b, c, d, e, f, g) {
        var h = function (a) {
            return function (b, e) {
                var f = d.preprocess(a), g = c.build(b, f);
                void 0 !== e && null !== e && (e.removeAllRanges(), g(e))
            }
        }, i = function (a) {
            return function (b, d) {
                var e = c.create(b);
                e.selectNodeContents(a.dom()), d.removeAllRanges(), d.addRange(e)
            }
        }, j = function (a, b) {
            var c = b.getRangeAt(0), d = b.getRangeAt(b.rangeCount - 1), e = a.document.createRange();
            return e.setStart(c.startContainer, c.startOffset), e.setEnd(d.endContainer, d.endOffset), e
        }, k = function (c, d) {
            var e = f.fromDom(d.startContainer), g = f.fromDom(d.endContainer);
            return b.read().isRtl(c) ? b.read().flip(c, d) : a.read(e, d.startOffset, g, d.endOffset)
        }, l = function (a, b) {
            return void 0 !== b && null !== b && b.rangeCount > 0 ? e.from(j(a, b)) : e.none()
        }, m = function (a, b) {
            var c = l(a, b);
            return c.map(function (a) {
                return k(b, a)
            })
        }, n = function (a) {
            return function (b, c) {
                var d = l(b, c);
                d.each(function (c) {
                    o(b, c, a)
                })
            }
        }, o = function (a, b, c) {
            var d = g.fromElements(c, a.document);
            b.deleteContents(), b.insertNode(d.dom())
        }, p = function (a, b) {
            return function (e, f) {
                var g = d.preprocess(a), h = c.toNative(e, g);
                o(e, h, b)
            }
        }, q = function (a, b, d, e) {
            return function (f, g) {
                var h = c.toExactNative(f, a, b, d, e);
                h.deleteContents()
            }
        }, r = function (a, b, d, e) {
            return function (g, h) {
                var i = c.toExactNative(g, a, b, d, e), j = i.cloneContents();
                return f.fromDom(j)
            }
        }, s = function (a, b, d, f) {
            return function (g, h) {
                var i = c.toExactNative(g, a, b, d, f), j = i.getClientRects(),
                    k = j.length > 0 ? j[0] : i.getBoundingClientRect();
                return k.width > 0 || k.height > 0 ? e.some(k) : e.none()
            }
        }, t = function (a) {
            return function (b, d) {
                var f = c.create(b);
                f.selectNode(a.dom());
                var g = f.getBoundingClientRect();
                return g.width > 0 || g.height > 0 ? e.some(g) : e.none()
            }
        }, u = function (a, b) {
            a.getSelection().removeAllRanges()
        }, v = function (a, b, d, e) {
            return function (f, g) {
                var h = c.toExactNative(f, a, b, d, e);
                return h.toString()
            }
        };
        return {
            get: m,
            set: h,
            selectElementContents: i,
            replace: n,
            replaceRange: p,
            deleteRange: q,
            cloneFragment: r,
            rectangleAt: s,
            bounds: t,
            clearSelection: u,
            stringAt: v
        }
    }),g("3a", ["5o", "5p", "5q", "5r", "5s", "5t", "3n", "k"], function (a, b, c, d, e, f, g, h) {
        var i = function (a) {
            return c.run(a, f.get)
        }, j = function (a, b) {
            c.run(a, f.set(b))
        }, k = function (c, d, e, f, g) {
            var h = a.write(b.on(d, e), b.on(f, g));
            j(c, h)
        }, l = function (a, b) {
            c.run(a, f.selectElementContents(b))
        }, m = function (a, b) {
            c.run(a, f.replace(b))
        }, n = function (a, b, d) {
            c.run(a, f.replaceRange(b, d))
        }, o = function (a, b, d, e, g) {
            c.run(a, f.deleteRange(b, d, e, g))
        }, p = function (a, b, d, e, g) {
            return c.run(a, f.cloneFragment(b, d, e, g))
        }, q = function (a, b, c, d) {
            return g.eq(a, c) && b === d
        }, r = function (a, b, d, e, g) {
            return c.run(a, f.rectangleAt(b, d, e, g))
        }, s = function (a, b) {
            return c.run(a, f.bounds(b))
        }, t = function (a, b, c) {
            return d.find(a, b, c)
        }, u = function (c, d, e, f, g, h) {
            var i = a.write(b.on(d, e), b.on(f, g));
            return t(c, i, h)
        }, v = function (b, c) {
            var d = e.forceRange(b, c);
            return a.general(h.fromDom(d.startContainer), d.startOffset, h.fromDom(d.endContainer), d.endOffset)
        }, w = function (a) {
            c.run(a, f.clearSelection)
        }, x = function (a, b, d, e, g) {
            return c.run(a, f.stringAt(b, d, e, g))
        };
        return {
            get: i,
            set: j,
            setExact: k,
            selectElementContents: l,
            replace: m,
            replaceRange: n,
            deleteRange: o,
            isCollapsed: q,
            cloneFragment: p,
            rectangleAt: r,
            bounds: s,
            findWithin: t,
            findWithinExact: u,
            deriveExact: v,
            clearAll: w,
            stringAt: x
        }
    }),g("5u", ["p", "3n"], function (a, b) {
        return function (c, d, e, f) {
            var g = b.eq(c, e) && d === f;
            return {
                startContainer: a.constant(c),
                startOffset: a.constant(d),
                endContainer: a.constant(e),
                endOffset: a.constant(f),
                collapsed: a.constant(g)
            }
        }
    }),g("3b", ["5u", "k", "1x", "1y", "3f"], function (a, b, c, d, e) {
        return function (f) {
            var g = b.fromTag("br"), h = function (a, b) {
                a.dom().focus()
            }, i = function (a) {
                var b = e.owner(a);
                return b.dom().defaultView
            }, j = function (b, d) {
                var e = i(d);
                e.focus(), c.append(d, g), f.set(e, a(g, 0, g, 0))
            }, k = function () {
                d.remove(g)
            };
            return {cleanup: k, toOn: h, toOff: j}
        }
    }),g("3c", ["p"], function (a) {
        return function () {
            var b = function (a, b) {
                a.dom().focus()
            }, c = function (a, b) {
                b.dom().focus()
            }, d = a.identity;
            return {toOn: b, toOff: c, cleanup: d}
        }
    }),g("6y", ["g", "k", "1x", "29", "1y", "3f"], function (a, b, c, d, e, f) {
        var g = function (a, b) {
            f.nextSibling(a).filter(b).each(function (b) {
                var c = f.children(b);
                d.append(a, c), e.remove(b)
            }), i(a, b)
        }, h = function (a, g) {
            var h = f.children(a), i = b.fromTag("div", f.owner(a).dom());
            d.append(i, h), c.before(a, i), e.remove(a)
        }, i = function (b, c) {
            var d = f.children(b);
            a.each(d, function (a) {
                c(a) && h(a, b)
            })
        };
        return {consolidate: g}
    }),g("6z", ["3g"], function (a) {
        var b = a.create("ephox-sloth");
        return {resolve: b.resolve}
    }),g("70", ["5d"], function (a) {
        var b = function (a, b) {
            return function (d) {
                return "rtl" === c(d) ? b : a
            }
        }, c = function (b) {
            return "rtl" === a.get(b, "direction") ? "rtl" : "ltr"
        };
        return {onDirection: b, getDirection: c}
    }),g("5v", ["1s", "2c", "6y", "6z", "j", "27", "3q", "5d", "70", "k", "6h", "1x", "1y", "20", "3f"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o) {
        var p = d.resolve("bin"), q = p + a.generate(""), r = i.onDirection("-100000px", "100000px");
        return function (a) {
            var d = j.fromTag("div");
            e.setAll(d, {contenteditable: "true", "aria-hidden": "true"}), h.setAll(d, {
                position: "fixed",
                top: "0px",
                width: "100px",
                height: "100px",
                overflow: "hidden",
                opacity: "0"
            }), g.add(d, [p, q]);
            var i = function (a) {
                m.empty(d), h.set(d, "left", r(a)), l.append(a, d)
            }, s = function () {
                var b = n.ancestor(d, "body");
                b.each(function (b) {
                    a.toOff(b, d)
                })
            }, t = function (a) {
                return f.has(a, q)
            }, u = function () {
                c.consolidate(d, t);
                var a = b.immutable("elements", "html", "container"), e = o.children(d), f = k.get(d);
                return a(e, f, d)
            }, v = function () {
                m.remove(d)
            }, w = function () {
                return d
            };
            return {attach: i, focus: s, contents: u, container: w, detach: v}
        }
    }),g("3d", ["p", "2x", "21", "22", "2y", "5v", "3f"], function (a, b, c, d, e, f, g) {
        return function (h, i) {
            var j = f(h), k = function () {
                h.cleanup();
                var a = j.contents();
                j.detach(), n.trigger.after(a.elements(), a.html(), j.container())
            }, l = b.tap(function () {
                n.trigger.before(), j.attach(i), j.focus(), e.run(g.owner(i), l, k)
            }), m = function () {
                l.instance()
            }, n = d.create({before: c([]), after: c(["elements", "html", "container"])}), o = a.noop;
            return {instance: a.constant(m), destroy: o, events: n.registry}
        }
    }),g("1r", ["2t", "3a", "21", "22", "3b", "3c", "3d", "1d"], function (a, b, c, d, e, f, g, h) {
        var i = a.detect(), j = {
            set: function (a, c) {
                b.setExact(a, c.startContainer(), c.startOffset(), c.endContainer(), c.endOffset())
            }
        }, k = function (a) {
            var b = i.browser.isIE() && "body" !== h.name(a) ? f : e;
            return b(j)
        };
        return function (a) {
            var b = d.create({after: c(["container"])}), e = k(a), f = g(e, a);
            f.events.after.bind(function (c) {
                e.toOn(a, c.container()), b.trigger.after(c.container())
            });
            var h = function () {
                f.instance()()
            };
            return {run: h, events: b.registry}
        }
    }),g("5y", ["w", "k", "1g"], function (a, b, c) {
        var d = function (d) {
            if ("complete" === c.readyState || "interactive" === c.readyState) d(); else var e = a.bind(b.fromDom(c), "DOMContentLoaded", function () {
                d(), e.unbind()
            })
        };
        return {execute: d}
    }),g("3e", ["2t", "5h", "p", "5w", "n", "5x", "5i", "5d", "w", "k", "1x", "5y", "1y"], function (a, b, c, d, e, f, g, h, i, j, k, l, m) {
        var n = b.install("ephox.keurig.init"), o = e.none(), p = a.detect(), q = p.browser,
            r = q.isIE() || q.isSpartan() || p.deviceType.isiOS() || p.deviceType.isAndroid(),
            s = r ? c.noop : d.cached(function (a) {
                var b = j.fromTag("div");
                if (void 0 === a) throw"baseUrl was undefined";
                var c = j.fromTag("iframe");
                h.setAll(b, {display: "none"});
                var d = i.bind(c, "load", function () {
                    var g = function (a) {
                        o = e.some(a), q.isSafari() || m.remove(b)
                    }, h = n.ephemeral(g), i = a + "/wordimport.js";
                    f.write(c, '<script type="text/javascript" src="' + i + '"></script><script type="text/javascript">function gwtInited () {parent.window.' + h + "(com.ephox.keurig.WordCleaner.cleanDocument);}</script>"), d.unbind()
                });
                l.execute(function () {
                    k.append(g.body(), b), k.append(b, c)
                })
            }), t = function (a, b) {
                return o.map(function (c) {
                    return c(a, b)
                })
            }, u = function () {
                return o.isSome()
            };
        return {load: s, cleanDocument: t, ready: u}
    }),g("1t", ["3e"], function (a) {
        return function (b) {
            return a.ready() || a.load(b), {cleanDocument: a.cleanDocument}
        }
    }),g("l", ["1o", "1p", "1q", "1r", "g", "1s", "1t", "p", "n"], function (a, b, c, d, e, f, g, h, i) {
        return function (j, k, l, m) {
            var n = g(m.baseUrl), o = h.curry(c, k), p = d, q = function () {
                    return {clipboardType: f.generate("clipboard-type"), findClipboardTags: i.none}
                }, r = void 0 !== m.intraFlag ? m.intraFlag : q(),
                s = e.flatten([void 0 !== m.intraFlag ? [b.internal(l, p, j, r)] : [], [b.onlyText()], [b.gwt(n, k, m, o)], [b.image(m)]]),
                t = b.pastiche(k, m, l, p, j, o, r);
            return a(s, t)
        }
    }),g("m", ["1o", "1p"], function (a, b) {
        return function () {
            return a([b.text()], b.none())
        }
    }),g("q", [], function () {
        return {officeStyles: "prompt", htmlStyles: "clean"}
    }),g("r", ["j", "k", "1x", "1y", "1z", "20", "1g"], function (a, b, c, d, e, f, g) {
        var h = "powerpaste-styles", i = "#" + h, j = function (d) {
            if (!e.any(i)) {
                var g = "<style>.ephox-cement-flashbin-helpcopy-kbd {font-size: 3em !important; text-align: center !important; vertical-align: middle !important; margin: .5em 0} .ephox-cement-flashbin-helpcopy-kbd .ephox-polish-help-kbd {font-size: 3em !important; vertical-align: middle !important;} .ephox-cement-flashbin-helpcopy a {text-decoration: underline} .ephox-cement-flashbin-loading-spinner {background-image: url(" + d + ") !important; width: 96px !important; height:96px !important; display: block; margin-left: auto !important; margin-right: auto !important; margin-top: 2em !important; margin-bottom: 2em !important;} .ephox-cement-flashbin-loading p {text-align: center !important; margin: 2em 0 !important} .ephox-cement-flashbin-target {height: 1px !important;} .ephox-cement-flashbin-target.ephox-cement-flash-activate {height: 150px !important; width: 100% !important;} .ephox-cement-flashbin-target object {height: 1px !important;} .ephox-cement-flashbin-target.ephox-cement-flash-activate object {height: 150px !important; width: 100% !important;} .ephox-cement-flashbin-helpcopy p {white-space: normal;}</style>",
                    j = b.fromHtml(g);
                a.set(j, "type", "text/css"), a.set(j, "id", h);
                var k = f.first("head").getOrDie("Head element could not be found.");
                c.append(k, j)
            }
        }, k = function () {
            if (e.any(i)) {
                var a = f.first("head").getOrDie("Head element could not be found."),
                    b = f.descendant(a, i).getOrDie("The style element could not be removed");
                d.remove(b)
            }
        };
        return {injectStyles: j, removeStyles: k}
    }),g("v", ["g", "k", "j", "1g"], function (a, b, c, d) {
        var e = function (a) {
            var b = d.createElement("div");
            b.appendChild(a.cloneNode(!0));
            var c = b.innerHTML;
            return b = a = null, c
        }, f = function (d) {
            a.each(a.map(d.getElementsByTagName("*"), b.fromDom), function (a) {
                c.has(a, "data-mce-style") && !c.has(a, "style") && c.set(a, "style", c.get(a, "data-mce-style"))
            })
        };
        return {nodeToString: e, restoreStyleAttrs: f}
    }),g("t", ["21", "22", "v", "j", "k", "1x", "1y", "20"], function (a, b, c, d, e, f, g, h) {
        return function (i) {
            var j = function () {
                var j, k = "", l = "", m = [], n = null, o = b.create({close: a([])}), p = function (a) {
                    k = a
                }, q = function (a) {
                    var b = c.nodeToString(a.dom());
                    l = [{type: "container", html: b}], n = a
                }, r = function (a) {
                    var b = [];
                    a.forEach(function (a, c, d) {
                        b.push({text: a.text, onclick: a.click})
                    }), m = b
                }, s = function (a) {
                    o.trigger.close()
                }, t = function () {
                    j.off("close", s), j.close("close")
                }, u = function () {
                    0 === m.length && (m = [{
                        text: "Close", onclick: function () {
                            j.close()
                        }
                    }]);
                    var a = {
                        title: k,
                        spacing: 10,
                        padding: 10,
                        minWidth: 300,
                        minHeight: 100,
                        layout: "flex",
                        items: l,
                        buttons: m
                    };
                    j = i.windowManager.open(a);
                    var b = e.fromDom(j.getEl()),
                        c = h.descendant(b, "." + d.get(n, "class")).getOrDie("We must find this element or we cannot continue");
                    f.before(c, n), g.remove(c), j.on("close", s)
                }, v = function () {
                    t()
                }, w = function () {
                    t()
                }, x = function () {
                };
                return {
                    events: o.registry,
                    setTitle: p,
                    setContent: q,
                    setButtons: r,
                    show: u,
                    hide: v,
                    destroy: w,
                    reflow: x
                }
            };
            return {createDialog: j}
        }
    }),g("24", ["2c", "n"], function (a, b) {
        var c = a.immutable("url", "html"), d = function (a) {
            return /^https?:\/\/[\w\?\-\/+=.&%@~#]+$/i.test(a)
        }, e = function (a) {
            return d(a) && /.(gif|jpe?g|png)$/.test(a)
        }, f = function (a) {
            var d = /^<a href="([^"]+)">([^<]+)<\/a>$/.exec(a);
            return b.from(d).bind(function (d) {
                var e = c(d[1], a);
                return d[1] === d[2] ? b.some(e) : b.none()
            })
        };
        return {isAbsoluteUrl: d, isImageUrl: e, parseUrlFromLinkHtml: f}
    }),g("u", ["g", "n", "23", "24"], function (a, b, c, d) {
        var e = function (a) {
            return "extra" in a.undoManager
        }, f = function (a, c, d) {
            return e(a) ? (a.undoManager.extra(function () {
                k(a, c)
            }, d), b.some(!0)) : b.none()
        }, g = function (a, b) {
            return f(a, b.html(), function () {
                a.insertContent('<img src="' + b.url() + '">')
            })
        }, h = function (a, b) {
            return f(a, b.html(), function () {
                a.execCommand("mceInsertLink", !1, b.url())
            })
        }, i = function (a, c) {
            return d.parseUrlFromLinkHtml(c).bind(function (c) {
                var e = a.selection.isCollapsed() === !1 && d.isAbsoluteUrl(c.url());
                return e ? h(a, c) : b.none()
            })
        }, j = function (a, c) {
            return d.parseUrlFromLinkHtml(c).bind(function (c) {
                return d.isImageUrl(c.url()) ? g(a, c) : b.none()
            })
        }, k = function (a, c) {
            return a.insertContent(c, {merge: a.settings.paste_merge_formats !== !1, paste: !0}), b.some(!0)
        }, l = function (a, b, d) {
            var e = function (c) {
                return c(a, b)
            };
            return c.findMap(d, e)
        };
        return {until: l, linkSelection: i, insertImage: j, insertContent: k}
    }),g("8", [], function () {
        var a = function (a, b) {
            return a.hasEventListeners(b)
        }, b = function (a, b) {
            return a.fire("PastePreProcess", {content: b}).content
        }, c = function (a, b) {
            var c = a.dom.add(a.getBody(), "div", {style: "display:none"}, b),
                d = a.fire("PastePostProcess", {node: c}).node.innerHTML;
            return a.dom.remove(c), d
        }, d = function (c, d) {
            return a(c, "PastePreProcess") ? b(c, d) : d
        }, e = function (b, d) {
            return a(b, "PastePostProcess") ? c(b, d) : d
        }, f = function (a, b) {
            return e(a, d(a, b))
        }, g = function (a) {
            var b = a.settings, c = a.plugins.powerpaste;
            b.paste_preprocess && a.on("PastePreProcess", function (d) {
                b.paste_preprocess.call(a, c, d)
            }), b.paste_postprocess && a.on("PastePostProcess", function (d) {
                b.paste_postprocess.call(a, c, d)
            })
        };
        return {process: f, registerEvents: g}
    }),g("6", ["l", "m", "g", "n", "o", "p", "d", "q", "r", "s", "t", "u", "8", "v", "w", "k", "x", "2"], function (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r) {
        return function (s, t, u, v, w) {
            var x, y, z, A, B;
            B = (v ? v.jsUrl : u) + "/js", y = (v ? v.swfUrl : u) + "/flash/textboxpaste.swf", z = (v ? v.imgUrl : u) + "/img/spinner_96.gif", A = (v ? v.cssUrl : u) + "/css/editorcss.css";
            var C = function (a) {
                return a.settings.smart_paste !== !1
            }, D = function (a) {
                return function (b, c) {
                    return b.undoManager.transact(function () {
                        l.insertContent(b, c), n.restoreStyleAttrs(b.getBody()), w.prepareImages(a)
                    }), d.some(!0)
                }
            }, E = function (a, b, c) {
                var d = C(a) ? [l.linkSelection, l.insertImage] : [];
                l.until(a, b, d.concat([D(c)]))
            }, F = function () {
                x && s.selection.moveToBookmark(x), x = null
            };
            s.on("init", function (d) {
                i.injectStyles(z), s.dom.loadCSS(A);
                var l = {
                    baseUrl: B,
                    swf: y,
                    officeStyles: s.settings.powerpaste_word_import || h.officeStyles,
                    htmlStyles: s.settings.powerpaste_html_import || h.htmlStyles,
                    translations: g.translate,
                    allowLocalImages: s.settings.powerpaste_allow_local_images !== !1,
                    enableFlashImport: s.settings.powerpaste_enable_flash_import !== !1,
                    preprocessor: function (a) {
                        return e.pure(a)
                    }
                }, r = k(s), u = p.fromDom(s.getBody()), v = function (a) {
                    a.events.cancel.bind(function () {
                        F()
                    }), a.events.error.bind(function (a) {
                        F(), s.notificationManager ? s.notificationManager.open({
                            text: g.translate(a.message()),
                            type: "error"
                        }) : j.showDialog(s, g.translate(a.message()))
                    }), a.events.insert.bind(function (a) {
                        var b = c.map(a.elements(), function (a) {
                            return n.nodeToString(a.dom())
                        }).join("");
                        s.focus(), q(function () {
                            F(), E(s, m.process(s, b), a.assets()), w.uploadImages(a.assets())
                        }, 1)
                    })
                }, C = a(u, r.createDialog, f.noop, l), D = b();
                c.each([C, D], v), o.bind(u, "paste", function (a) {
                    x || (x = s.selection.getBookmark());
                    var b = t.isText() ? D : C;
                    b.paste(a.raw()), t.reset(), q(function () {
                        s.windowManager.windows[0] && s.windowManager.windows[0].getEl() && s.windowManager.windows[0].getEl().focus()
                    }, 1)
                })
            }), s.on("remove", function (a) {
                1 === r.editors.length && i.removeStyles()
            })
        }
    }),g("7", ["y", "z"], function (a, b) {
        var c = function (a) {
            return tinymce.util.VK.metaKeyPressed(a) && 86 == a.keyCode && a.shiftKey
        };
        return function (d) {
            var e = b(d.settings.paste_as_text), f = b(!1);
            d.on("keydown", function (a) {
                c(a) && (f.set(!0), tinymce.Env.ie && tinymce.Env.ie < 10 && (a.preventDefault(), d.fire("paste", {})))
            });
            var g = a(function () {
                var a = d.translate("Paste is now in plain text mode. Contents will now be pasted as plain text until you toggle this option off.");
                d.notificationManager.open({text: a, type: "info"})
            }), h = function () {
                var a = this, b = !e.get();
                a.active(b), e.set(b), d.fire("PastePlainTextToggle", {state: b}), b === !0 && 0 != d.settings.paste_plaintext_inform && g(), d.focus()
            }, i = function () {
                f.set(!1)
            }, j = function () {
                return f.get() || e.get()
            };
            return {toggle: h, reset: i, isText: j}
        }
    }),g("10", [], function () {
        var a = 0, b = 1, c = -1, d = function (a) {
            return parseInt(a, 10)
        }, e = function (a) {
            return function () {
                return a
            }
        }, f = function (a, b, c) {
            return {major: e(a), minor: e(b), patch: e(c)}
        }, g = function (a) {
            var b = /([0-9]+)\.([0-9]+)\.([0-9]+)(?:(\-.+)?)/.exec(a);
            return b ? f(d(b[1]), d(b[2]), d(b[3])) : f(0, 0, 0)
        }, h = function (d, e) {
            var f = d - e;
            return 0 === f ? a : f > 0 ? b : c
        }, i = function (b, c) {
            var d = h(b.major(), c.major());
            if (d !== a) return d;
            var e = h(b.minor(), c.minor());
            if (e !== a) return e;
            var f = h(b.patch(), c.patch());
            return f !== a ? f : a
        };
        return {nu: f, parse: g, compare: i}
    }),g("9", ["10"], function (a) {
        var b = function (a) {
            var b = [a.majorVersion, a.minorVersion].join(".");
            return b.split(".").slice(0, 3).join(".")
        }, c = function (c) {
            return c ? a.parse(b(c)) : null
        }, d = function (b, d) {
            return a.compare(c(b), a.parse(d)) < 0
        };
        return {getVersion: c, isLessThan: d}
    }),g("a", ["11"], function (a) {
        var b = function (a, b) {
            return function () {
                var c = a.console;
                c && b in c && c[b].apply(c, arguments)
            }
        };
        return {log: b(a, "log"), error: b(a, "error"), warn: b(a, "warm")}
    }),g("1", ["3", "4", "5", "6", "7", "8", "9", "a", "2"], function (a, b, c, d, e, f, g, h, i) {
        return function (j) {
            return g.isLessThan(i, "4.0.0") ? (h.error('The "powerpaste" plugin requires at least 4.0.0 version of TinyMCE.'), function () {
            }) : function (g, h) {
                var k = e(g), l = function () {
                    var b = a(g);
                    d(g, k, h, j, b), g.settings.powerpaste_block_drop || c(g, h, j, b)
                }, m = function () {
                    b(g, k, j)
                }, n = function () {
                    var a = this;
                    a.active(k.isText()), g.on("PastePlainTextToggle", function (b) {
                        a.active(b.state)
                    })
                };
                i.Env.ie && i.Env.ie < 10 ? m() : l();
                var o = function (a) {
                    g.dom.bind(a, "drop dragstart dragend dragover dragenter dragleave dragdrop draggesture", function (a) {
                        return i.dom.Event.cancel(a)
                    })
                };
                g.settings.powerpaste_block_drop && g.on("init", function (a) {
                    o(g.getBody()), o(g.getDoc())
                }), f.registerEvents(g), g.addButton("pastetext", {
                    icon: "pastetext",
                    tooltip: "Paste as text",
                    onclick: k.toggle,
                    onPostRender: n
                }), g.addMenuItem("pastetext", {
                    text: "Paste as text",
                    selectable: !0,
                    onclick: k.toggle,
                    onPostRender: n
                })
            }
        }
    }),g("0", ["1", "2"], function (a, b) {
        return function (c) {
            b.PluginManager.requireLangPack("powerpaste", "ar,ca,cs,da,de,el,es,fa,fi,fr_FR,he_IL,hr,hu_HU,it,ja,kk,ko_KR,nb_NO,nl,pl,pt_BR,pt_PT,ro,ru,sk,sl_SI,sv_SE,th_TH,tr,uk,zh_CN,zh_TW"), b.PluginManager.add("powerpaste", a(c))
        }
    }),d("0")()
}();
