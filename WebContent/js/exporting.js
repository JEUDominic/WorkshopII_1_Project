/*
 Highcharts JS v5.0.0 (2016-09-29)
 Exporting module

 (c) 2010-2016 Torstein Honsi

 License: www.highcharts.com/license
*/
(function(q) {
    "object" === typeof module && module.exports ? module.exports = q: q(Highcharts)
})(function(q) { (function(f) {
        var q = f.defaultOptions,
        w = f.doc,
        A = f.Chart,
        y = f.addEvent,
        F = f.removeEvent,
        G = f.fireEvent,
        x = f.createElement,
        B = f.discardElement,
        H = f.css,
        p = f.merge,
        C = f.pick,
        u = f.each,
        v = f.extend,
        I = f.splat,
        z = f.win,
        D = f.SVGRenderer,
        J = f.Renderer.prototype.symbols;
        v(q.lang, {
            printChart: "Print chart",
            downloadPNG: "Download PNG image",
            downloadJPEG: "Download JPEG image",
            downloadPDF: "Download PDF document",
            downloadSVG: "Download SVG vector image",
            contextButtonTitle: "Chart context menu"
        });
        q.navigation = {
            buttonOptions: {
                theme: {},
                symbolSize: 14,
                symbolX: 12.5,
                symbolY: 10.5,
                align: "right",
                buttonSpacing: 3,
                height: 22,
                verticalAlign: "top",
                width: 24
            }
        };
        q.exporting = {
            type: "image/png",
            url: "https://export.highcharts.com/",
            printMaxWidth: 780,
            scale: 2,
            buttons: {
                contextButton: {
                    className: "highcharts-contextbutton",
                    menuClassName: "highcharts-contextmenu",
                    symbol: "menu",
                    _titleKey: "contextButtonTitle",
                    menuItems: [{
                        textKey: "printChart",
                        onclick: function() {
                            this.print()
                        }
                    },
                    {
                        separator: !0
                    },
                    {
                        textKey: "downloadPNG",
                        onclick: function() {
                            this.exportChart()
                        }
                    },
                    {
                        textKey: "downloadJPEG",
                        onclick: function() {
                            this.exportChart({
                                type: "image/jpeg"
                            })
                        }
                    },
                    {
                        textKey: "downloadPDF",
                        onclick: function() {
                            this.exportChart({
                                type: "application/pdf"
                            })
                        }
                    },
                    {
                        textKey: "downloadSVG",
                        onclick: function() {
                            this.exportChart({
                                type: "image/svg+xml"
                            })
                        }
                    }]
                }
            }
        };
        f.post = function(a, b, d) {
            var e;
            a = x("form", p({
                method: "post",
                action: a,
                enctype: "multipart/form-data"
            },
            d), {
                display: "none"
            },
            w.body);
            for (e in b) x("input", {
                type: "hidden",
                name: e,
                value: b[e]
            },
            null, a);
            a.submit();
            B(a)
        };
        v(A.prototype, {
            sanitizeSVG: function(a) {
                return a = a.replace(/zIndex="[^"]+"/g, "").replace(/isShadow="[^"]+"/g, "").replace(/symbolName="[^"]+"/g, "").replace(/jQuery[0-9]+="[^"]+"/g, "").replace(/url\(("|&quot;)(\S+)("|&quot;)\)/g, "url($2)").replace(/url\([^#]+#/g, "url(#").replace(/<svg /, '<svg xmlns:xlink="http://www.w3.org/1999/xlink" ').replace(/ (NS[0-9]+\:)?href=/g, " xlink:href=").replace(/\n/, " ").replace(/<\/svg>.*?$/, "</svg>").replace(/(fill|stroke)="rgba\(([ 0-9]+,[ 0-9]+,[ 0-9]+),([ 0-9\.]+)\)"/g, '$1="rgb($2)" $1-opacity="$3"').replace(/&nbsp;/g, "\u00a0").replace(/&shy;/g, "\u00ad")
            },
            getChartHTML: function() {
                this.inlineStyles();
                return this.container.innerHTML
            },
            getSVG: function(a) {
                var b = this,
                d, e, g, E, k, c = p(b.options, a),
                l = c.exporting.allowHTML;
                w.createElementNS || (w.createElementNS = function(a, b) {
                    return w.createElement(b)
                });
                e = x("div", null, {
                    position: "absolute",
                    top: "-9999em",
                    width: b.chartWidth + "px",
                    height: b.chartHeight + "px"
                },
                w.body);
                g = b.renderTo.style.width;
                k = b.renderTo.style.height;
                g = c.exporting.sourceWidth || c.chart.width || /px$/.test(g) && parseInt(g, 10) || 600;
                k = c.exporting.sourceHeight || c.chart.height || /px$/.test(k) && parseInt(k, 10) || 400;
                v(c.chart, {
                    animation: !1,
                    renderTo: e,
                    forExport: !0,
                    renderer: "SVGRenderer",
                    width: g,
                    height: k
                });
                c.exporting.enabled = !1;
                delete c.data;
                c.series = [];
                u(b.series,
                function(a) {
                    E = p(a.userOptions, {
                        animation: !1,
                        enableMouseTracking: !1,
                        showCheckbox: !1,
                        visible: a.visible
                    });
                    E.isInternal || c.series.push(E)
                });
                a && u(["xAxis", "yAxis"],
                function(b) {
                    u(I(a[b]),
                    function(a, e) {
                        c[b][e] = p(c[b][e], a)
                    })
                });
                d = new f.Chart(c, b.callback);
                u(["xAxis", "yAxis"],
                function(a) {
                    u(b[a],
                    function(b, e) {
                        var c = d[a][e],
                        l = b.getExtremes(),
                        g = l.userMin,
                        l = l.userMax; ! c || void 0 === g && void 0 === l || c.setExtremes(g, l, !0, !1)
                    })
                });
                g = d.getChartHTML();
                c = null;
                d.destroy();
                B(e);
                l && (e = g.match(/<\/svg>(.*?$)/)) && (e = '<foreignObject x="0" y="0" width="200" height="200"><body xmlns="http://www.w3.org/1999/xhtml">' + e[1] + "</body></foreignObject>", g = g.replace("</svg>", e + "</svg>"));
                g = this.sanitizeSVG(g);
                return g = g.replace(/(url\(#highcharts-[0-9]+)&quot;/g, "$1").replace(/&quot;/g, "'")
            },
            getSVGForExport: function(a, b) {
                var d = this.options.exporting;
                return this.getSVG(p({
                    chart: {
                        borderRadius: 0
                    }
                },
                d.chartOptions, b, {
                    exporting: {
                        sourceWidth: a && a.sourceWidth || d.sourceWidth,
                        sourceHeight: a && a.sourceHeight || d.sourceHeight
                    }
                }))
            },
            exportChart: function(b, a) {  
                var b = b || {},  
                d = this.options.exporting,  
                d = this.getSVG(k({  
                    chart: {  
                        borderRadius: 0  
                    }  
                },  
                d.chartOptions, a, {  
                    exporting: {  
                        sourceWidth: b.sourceWidth || d.sourceWidth,  
                        sourceHeight: b.sourceHeight || d.sourceHeight  
                    }  
                })),  
                b = k(this.options.exporting, b);  
                $.ajax({   
                      type: 'POST',   
                      url: b.url,   
                      data: {  
                          filename: b.filename || "chart",  
                          type: b.type,  
                          width: b.width || 0,  
                          scale: b.scale || 2,  
                          svg: d  
                      },   
                      async:false   
                });   
               /* f.post(b.url, { 
                    filename: b.filename || "chart", 
                    type: b.type, 
                    width: b.width || 0, 
                    scale: b.scale || 2, 
                    svg: d 
                }, 
                b.formAttributes,'json')*/  
            },
            print: function() {
                var a = this,
                b = a.container,
                d = [],
                e = b.parentNode,
                g = w.body,
                f = g.childNodes,
                k = a.options.exporting.printMaxWidth,
                c,
                l;
                if (!a.isPrinting) {
                    a.isPrinting = !0;
                    a.pointer.reset(null, 0);
                    G(a, "beforePrint");
                    if (l = k && a.chartWidth > k) c = [a.options.chart.width, void 0, !1],
                    a.setSize(k, void 0, !1);
                    u(f,
                    function(a, b) {
                        1 === a.nodeType && (d[b] = a.style.display, a.style.display = "none")
                    });
                    g.appendChild(b);
                    z.focus();
                    z.print();
                    setTimeout(function() {
                        e.appendChild(b);
                        u(f,
                        function(a, b) {
                            1 === a.nodeType && (a.style.display = d[b])
                        });
                        a.isPrinting = !1;
                        l && a.setSize.apply(a, c);
                        G(a, "afterPrint")
                    },
                    1E3)
                }
            },
            contextMenu: function(a, b, d, e, g, f, k) {
                var c = this,
                l = c.chartWidth,
                h = c.chartHeight,
                m = "cache-" + a,
                n = c[m],
                r = Math.max(g, f),
                t,
                p,
                q,
                v = function(b) {
                    c.pointer.inClass(b.target, a) || p()
                };
                n || (c[m] = n = x("div", {
                    className: a
                },
                {
                    position: "absolute",
                    zIndex: 1E3,
                    padding: r + "px"
                },
                c.container), t = x("div", {
                    className: "highcharts-menu"
                },
                null, n), p = function() {
                    H(n, {
                        display: "none"
                    });
                    k && k.setState(0);
                    c.openMenu = !1
                },
                y(n, "mouseleave",
                function() {
                    q = setTimeout(p, 500)
                }), y(n, "mouseenter",
                function() {
                    clearTimeout(q)
                }), y(w, "mouseup", v), y(c, "destroy",
                function() {
                    F(w, "mouseup", v)
                }), u(b,
                function(a) {
                    if (a) {
                        var b;
                        b = a.separator ? x("hr", null, null, t) : x("div", {
                            className: "highcharts-menu-item",
                            onclick: function(b) {
                                b && b.stopPropagation();
                                p();
                                a.onclick && a.onclick.apply(c, arguments)
                            },
                            innerHTML: a.text || c.options.lang[a.textKey]
                        },
                        null, t);
                        c.exportDivElements.push(b)
                    }
                }), c.exportDivElements.push(t, n), c.exportMenuWidth = n.offsetWidth, c.exportMenuHeight = n.offsetHeight);
                b = {
                    display: "block"
                };
                d + c.exportMenuWidth > l ? b.right = l - d - g - r + "px": b.left = d - r + "px";
                e + f + c.exportMenuHeight > h && "top" !== k.alignOptions.verticalAlign ? b.bottom = h - e - r + "px": b.top = e + f - r + "px";
                H(n, b);
                c.openMenu = !0
            },
            addButton: function(a) {
                var b = this,
                d = b.renderer,
                e = p(b.options.navigation.buttonOptions, a),
                g = e.onclick,
                f = e.menuItems,
                k,
                c,
                l = e.symbolSize || 12;
                b.btnCount || (b.btnCount = 0);
                b.exportDivElements || (b.exportDivElements = [], b.exportSVGElements = []);
                if (!1 !== e.enabled) {
                    var h = e.theme,
                    m = h.states,
                    n = m && m.hover,
                    m = m && m.select,
                    r;
                    delete h.states;
                    g ? r = function(a) {
                        a.stopPropagation();
                        g.call(b, a)
                    }: f && (r = function() {
                        b.contextMenu(c.menuClassName, f, c.translateX, c.translateY, c.width, c.height, c);
                        c.setState(2)
                    });
                    e.text && e.symbol ? h.paddingLeft = C(h.paddingLeft, 25) : e.text || v(h, {
                        width: e.width,
                        height: e.height,
                        padding: 0
                    });
                    c = d.button(e.text, 0, 0, r, h, n, m).addClass(a.className).attr({
                        title: b.options.lang[e._titleKey],
                        zIndex: 3
                    });
                    c.menuClassName = a.menuClassName || "highcharts-menu-" + b.btnCount++;
                    e.symbol && (k = d.symbol(e.symbol, e.symbolX - l / 2, e.symbolY - l / 2, l, l).addClass("highcharts-button-symbol").attr({
                        zIndex: 1
                    }).add(c));
                    c.add().align(v(e, {
                        width: c.width,
                        x: C(e.x, b.buttonOffset)
                    }), !0, "spacingBox");
                    b.buttonOffset += (c.width + e.buttonSpacing) * ("right" === e.align ? -1 : 1);
                    b.exportSVGElements.push(c, k)
                }
            },
            destroyExport: function(a) {
                var b = a ? a.target: this;
                a = b.exportSVGElements;
                var d = b.exportDivElements;
                a && (u(a,
                function(a, d) {
                    a && (a.onclick = a.ontouchstart = null, b.exportSVGElements[d] = a.destroy())
                }), a.length = 0);
                d && (u(d,
                function(a, d) {
                    F(a, "mouseleave");
                    b.exportDivElements[d] = a.onmouseout = a.onmouseover = a.ontouchstart = a.onclick = null;
                    B(a)
                }), d.length = 0)
            }
        });
        D.prototype.inlineToAttributes = "fill stroke strokeLinecap strokeLinejoin strokeWidth textAnchor x y".split(" ");
        D.prototype.inlineBlacklist = [/-/, /^(clipPath|cssText|d|height|width)$/, /^font$/, /[lL]ogical(Width|Height)$/, /perspective/, /TapHighlightColor/, /^transition/];
        D.prototype.unstyledElements = ["clipPath", "defs", "desc"];
        A.prototype.inlineStyles = function() {
            function a(a) {
                return a.replace(/([A-Z])/g,
                function(a, b) {
                    return "-" + b.toLowerCase()
                })
            }
            function b(d) {
                var h, m, n, r = "",
                t, v;
                if (1 === d.nodeType && -1 === q.indexOf(d.nodeName)) {
                    m = z.getComputedStyle(d, null);
                    n = "svg" === d.nodeName ? {}: z.getComputedStyle(d.parentNode, null);
                    k[d.nodeName] || (c || (c = w.createElementNS(f.SVG_NS, "svg"), c.setAttribute("version", "1.1"), w.body.appendChild(c)), t = w.createElementNS(d.namespaceURI, d.nodeName), c.appendChild(t), k[d.nodeName] = p(z.getComputedStyle(t, null)), c.removeChild(t));
                    for (h in m) {
                        t = !1;
                        for (v = g.length; v--&&!t;) t = g[v].test(h) || "function" === typeof m[h];
                        t || n[h] !== m[h] && k[d.nodeName][h] !== m[h] && ( - 1 !== e.indexOf(h) ? d.setAttribute(a(h), m[h]) : r += a(h) + ":" + m[h] + ";")
                    }
                    r && (h = d.getAttribute("style"), d.setAttribute("style", (h ? h + ";": "") + r));
                    "text" !== d.nodeName && u(d.children || d.childNodes, b)
                }
            }
            var d = this.renderer,
            e = d.inlineToAttributes,
            g = d.inlineBlacklist,
            q = d.unstyledElements,
            k = {},
            c;
            b(this.container.querySelector("svg"));
            c.parentNode.removeChild(c)
        };
        J.menu = function(a, b, d, e) {
            return ["M", a, b + 2.5, "L", a + d, b + 2.5, "M", a, b + e / 2 + .5, "L", a + d, b + e / 2 + .5, "M", a, b + e - 1.5, "L", a + d, b + e - 1.5]
        };
        A.prototype.renderExporting = function() {
            var a, b = this.options.exporting,
            d = b.buttons,
            e = this.isDirtyExporting || !this.exportSVGElements;
            this.buttonOffset = 0;
            this.isDirtyExporting && this.destroyExport();
            if (e && !1 !== b.enabled) {
                for (a in d) this.addButton(d[a]);
                this.isDirtyExporting = !1
            }
            y(this, "destroy", this.destroyExport)
        };
        A.prototype.callbacks.push(function(a) {
            a.renderExporting();
            y(a, "redraw", a.renderExporting);
            u(["exporting", "navigation"],
            function(b) {
                a[b] = {
                    update: function(d, e) {
                        a.isDirtyExporting = !0;
                        p(!0, a.options[b], d);
                        C(e, !0) && a.redraw()
                    }
                }
            })
        })
    })(q)
});