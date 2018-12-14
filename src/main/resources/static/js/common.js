(function($) {

    // --------------------------------------
    // common
    // --------------------------------------

    $.extend({
        namespace2fn: function(name, fun) {
            if (name) {
                $.fn[name] = fun ? fun : function() {
                    arguments.callee.$ = this;
                    return arguments.callee;
                };
            }
            return this;
        },
        namespace2win: function() {
            var a = arguments,
                o = null,
                i, j, d;
            for (i = 0; i < a.length; i = i + 1) {
                d = a[i].split(".");
                o = window;
                for (j = (d[0] == "window") ? 1 : 0; j < d.length; j = j + 1) {
                    o[d[j]] = o[d[j]] || {};
                    o = o[d[j]];
                }
            }
            return o;
        },
        formatDate: function(date, format) {

            var o = {
                "M+": date.getMonth() + 1, // month
                "d+": date.getDate(), // day
                "h+": date.getHours(), // hour
                "m+": date.getMinutes(), // minute
                "s+": date.getSeconds(), // second
                "q+": Math.floor((date.getMonth() + 3) / 3), // quarter
                "S": date.getMilliseconds() // millisecond
            }
            if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(format))
                    format = format.replace(RegExp.$1,
                        RegExp.$1.length == 1 ? o[k] :
                        ("00" + o[k]).substr(("" + o[k]).length));
            return format;
        },
        getUrlVariable: function(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == variable) { return pair[1]; }
            }
            return false;
        }
    });

    $.fn.serializeObject = function(param) {
        var obj = param || {};
        $(this).each(function() {
            var a = $(this).serializeArray();
            a.forEach(function(i) {
                if (i.value) {
                    var v = obj[i.name];
                    if (v) {
                        if (v instanceof Array) {
                            v.push(i.value);
                        } else {
                            var arr = [];
                            arr.push(v, i.value);
                            obj[i.name] = arr;
                        }
                    } else {
                        obj[i.name] = i.value;
                    }
                }
            });
        });

        return obj;
    };

    // --------------------------------------
    // constant
    // --------------------------------------

    _RESPONSE_STATUS = {
        NO_LOGIN: -1,
        NO_PERMISSION: -2,
        SUCCESS: 1,
        FAIL: 2,
        FAIL_VALID: 3,
        ERROR: 0
    };

    $.namespace2win('tonto.constant');

    // --------------------------------------
    // messager
    // --------------------------------------

    /*
     * 基于layer组件提供弹框消息，参考 http://www.layui.com/doc/modules/layer.html
     * 
     */

    $.extend({
        infoMessage: function(message, top) {
            layer.msg(message, { icon: 6, offset: top ? top : undefined });
        },
        failMessage: function(message, top) {
            layer.msg(message, { icon: 2, offset: top ? top : undefined });
        },
        errorMessage: function(message, top) {
            layer.msg(message, { icon: 5, offset: top ? top : undefined });
        },
        successMessage: function(message, top) {
            layer.msg(message, { icon: 1, offset: top ? top : undefined });
        },
        doAlert: function(msg, icon, fun, top) {
            if (typeof fun === 'number' || typeof fun === 'string') {
                top = fun;
            }
            layer.alert(msg, { icon: icon, offset: top ? top : undefined }, function(index) {
                layer.close(index);
                if (typeof fun === 'function') fun();
            });
        },
        successAlert: function(msg, fun, top) {
            $.doAlert(msg, 1, fun, top);
        },
        failAlert: function(msg, fun, top) {
            $.doAlert(msg, 2, fun, top);
        },
        errorAlert: function(msg, fun, top) {
            $.doAlert(msg, 5, fun, top);
        },
        infoAlert: function(msg, fun, top) {
            $.doAlert(msg, 6, fun, top);
        },
        isLayer: function() {
            if (parent && parent.layer && parent.layer.getFrameIndex(window.name)) {
                return true;
            } else {
                return false;
            }
        },
        openPageLayer: function(content, options) {
            options = options || {};

            if (typeof options == "string") {
                options = {
                    title: options
                }
            } else if (typeof options == "function") {
                options = {
                    success: options
                };
            }

            options = $.extend(options, {
                type: 1,
                title: options.title || '',
                maxmin: true, //开启最大化最小化按钮
                area: $.getOpenLayerSize(options.width, options.height),
                content: content,
                success: options.success
            });

            layer.open(options);
        },
        openUrlLayerOrLocate: function(url, options) {
            if (options && options.data) {
                url = $.wrapGetUrl(url, options.data);
            }

            if ($.isLayer()) {
                window.location = url;
            }

            options = options || {};

            if (typeof options == "string") {
                options = {
                    title: options
                }
            } else if (typeof options == "function") {
                options = {
                    success: options
                };
            }

            options = $.extend(options, {
                type: 2,
                title: options.title || '',
                maxmin: true, //开启最大化最小化按钮
                area: $.getOpenLayerSize(options.width, options.height),
                content: url,
                success: options.success
            })

            layer.open(options);
        },
        getOpenLayerSize: function(w, h) {
            w = w || 0.8;
            h = h || 0.9;

            var ww = $(window).width();
            var wh = $(window).height();

            if (w > ww) {
                w = ww * 0.8;
            } else if (w <= 1) {
                w = ww * w;
            }

            if (h > wh) {
                h = wh * 0.9;
            } else if (h <= 1) {
                h = wh * h;
            }

            return [w + "px", h + "px"];
        }
    });


    // --------------------------------------
    // ajax
    // --------------------------------------

    $.extend({
        ajaxUnLoginHandler: function(callback) {
            // ajax请求返回未登录状态处理
            // 暂时跳转主页面到登录页面，有时间可以做弹出登录窗口登录，成功后继续执行ajax请求处理

            $.failAlert("请先登录", function() {
                top.location.href = "/health/login";
            })

        },
        ajaxResponseCheck: function(response) {
            if (typeof response === 'string') {
                response = JSON.parse(response)
            }
            var resStatus = response.status,
                status = _RESPONSE_STATUS;

            if (status.NO_LOGIN === resStatus) {
                $.ajaxUnLoginHandler(callback);
            } else if (status.NO_PERMISSION === resStatus) {
                $.errorMessage(response.message || "您没有权限访问该页面或执行该操作");
            } else if (status.ERROR === resStatus) {
                $.errorMessage(response.message || "访问页面或执行操作错误");
            } else if (status.FAIL === resStatus) {
                $.errorMessage(response.message || "操作失败");
            } else if (status.FAIL_VALID === resStatus) {
                $.errorMessage(response.message || "验证不成功，操作失败");
            } else {
                return true;
            }
            return false;
        },
        wrapAjaxSuccessCallback: function(callback, submitBtn) {

            if (callback && typeof callback != 'function' && !submitBtn) {
                submitBtn = callback;
            }

            if (submitBtn) {
                $(submitBtn).each(function() {
                    var that = $(this);
                    that.data("loading", true);
                    var text = that.text();
                    that.data("orginText", text);
                    that.text(text + '中...').prop('disabled', true).addClass('disabled');
                });
            }

            // 包装Ajax成功回调方法，过滤返回内容
            return function(response) {
                if (submitBtn) {
                    $(submitBtn).each(function() {
                        var that = $(this);
                        var text = that.text();
                        that.removeClass('disabled').prop('disabled', false).text(that.data("orginText"));
                    });
                }

                if (typeof response === 'string') {
                    response = JSON.parse(response)
                }
                var resStatus = response.status,
                    status = _RESPONSE_STATUS;

                if (status.NO_LOGIN === resStatus) {
                    $.ajaxUnLoginHandler(callback);
                } else if (status.NO_PERMISSION === resStatus) {
                    $.errorMessage(response.message || "您没有权限访问该页面或执行该操作");
                } else if (status.ERROR === resStatus) {
                    $.errorMessage(response.message || "访问页面或执行操作错误");
                } else if (status.FAIL === resStatus) {
                    $.errorMessage(response.message || "操作失败");
                } else if (status.FAIL_VALID === resStatus) {
                    $.errorMessage(response.message || "验证不成功，操作失败");
                } else {
                    if (callback && typeof callback === 'function') {
                        callback(response.result);
                    }
                }
            }
        },
        postJsonAjax: function(url, data, callback, submitBtn) {

            if (callback && typeof callback != 'function' && !submitBtn) {
                submitBtn = callback;
            }

            // 发送json格式ajax请求
            $.sendAjax({
                type: "POST",
                url: url,
                dataType: "json",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(result) {
                    if (callback && typeof callback === 'function')
                        callback(result);
                },
                submitBtn: submitBtn
            });
        },
        sendAjax: function(options) {
            // 发送ajax请求 对应$.ajax()
            var callback = options.success;
            options.success = $.wrapAjaxSuccessCallback(callback, options.submitBtn);
            $.ajax(options);
        },
        getAjax: function(url, callback, submitBtn) {
            // 对应$.get()
            $.get(url, $.wrapAjaxSuccessCallback(callback, submitBtn));
        },
        postAjax: function(url, data, callback, submitBtn) {
            // 对应$.post()
            if (typeof data === 'function') {
                callback = data;
                data = null;
            }
            $.post(url, data, $.wrapAjaxSuccessCallback(callback, submitBtn));
        },
        postFormAjax: function(url, args) {
            // 提交表单形式ajax
            var form = $("<form method='post' action='" + url + "'></form>");
            $.each(args, function(key, value) {
                if (!$.isArray(value)) {
                    value = [value];
                }

                value.forEach(function(v) {
                    var input = $("<input type='hidden'>");
                    input.attr({ "name": key });
                    input.val(v);
                    form.append(input);
                });
            });
            form.appendTo(document.body);
            form.submit();
            document.body.removeChild(form[0]);
        },
        wrapGetUrl: function(url, data) {
            if (data) {
                var i = url.indexOf("?");
                if (i > 0) {
                    if (i < (url.length - 1)) {
                        url += "&";
                    }
                } else {
                    url += "?";
                }

                for (var o in data) {
                    var d = data[o];
                    if (d) {
                        if (!$.isArray(d)) {
                            d = [d];
                        }
                        d.forEach(function(x) {
                            url += o + "=" + x + "&";
                        });
                    }
                }
            }
            return url;
        }
    });

    // --------------------------------------
    // 事件分发器
    // --------------------------------------

    $.namespace2win('tonto.event');

    var event_Dispatcher = function() {

    }

    event_Dispatcher.prototype.addEventListener = function(event, callback) {
        var map = this.listenerMap || (this.listenerMap = {});
        var listeners = map[event] || (map[event] = new Array());
        listeners.push(callback);
    }

    event_Dispatcher.prototype.distribute = function(event, data) {
        var map = this.listenerMap || (this.listenerMap = {});
        var listeners = map[event];
        if (listeners) {
            for (var i = 0; i < listeners.length; i += 1) {
                listeners[i].call(this, data);
            }
        }
    }

    tonto.event.Dispatcher = event_Dispatcher;

    // ------------------------------------------
    //
    // 页面处理
    //
    // -----------------------------------------

    $.fn.showInfoDescription = function(items, isHorizontal) {
        _showInfoDescription(items, $(this), isHorizontal);
    }


    // -----------------------------------------
    //
    // cache
    //
    // -----------------------------------------

    window.mycache = {};

    $.extend({
        putCache: function(key, value) {
            window.mycache[key] = value;
        },
        getCache: function(key) {
            return window.mycache[key];
        }
    });


    // -----------------------------------------
    //
    // 其他
    //
    // -----------------------------------------


    $.extend({
        beautifyInput: function(input, icon, isBefore) {
            var a = $(input);
            a.wrap('<div class="input-group"></div>');
            var b = '<span class="input-group-addon"><i class="' + icon + '"></i></span>';
            if (isBefore) {
                a.before(b);
            } else {
                a.after(b);
            }
            return a;
        },
        formatSensitive: function(str, a, b, c) {
            if (typeof str !== 'string') {
                return "";
            }
            var l = str.length;
            if (a === undefined || typeof a !== 'number') {
                a = Math.floor(l / 3.5);
            }
            if (b === undefined || typeof b !== 'number') {
                b = Math.floor(l / 3.5);
            }
            return str.substring(0, a) + (c ? c : "*****") + str.substring(l - b);
        }
    });

    // ------------------------------------------
    //
    // 常用业务控件和方法
    //
    // -----------------------------------------

    $.extend({
        initComponment: function(container) {
            _initEnumConstant(container);
            _initValidator(container);
            _initTable(container);
            _initForm(container);
            _initAttachment(container);
            _initCommon(container);
        }
    })

    $.initComponment();

})(jQuery);

function _initCommon() {

    // 关键词搜索框添加绑定回车函数
    $('.tonto - btn - search').each(function() {
        var btn = $(this);
        $("body").bind('keypress', function(event) {
            if (event.keyCode == "13") {
                btn.click();
            }
        });
    });

    $('.tonto-select').each(function() {
        var that = $(this);
        var selected = that.attr("selectedvalue");
        if (selected) {
            that.val(selected);
        }
    });

    $('.tonto-datepicker-date').each(function() {
        $.beautifyInput(this, "fa fa-calendar", false);
        laydate.render({
            elem: this,
            type: "date",
            calendar: true, //开启公历节日
            theme: 'molv', //墨绿主题
            showBottom: true, //是否出现底部栏
            trigger: 'click' //绑定多个
        });
    });

    $('.tonto-datepicker-year').each(function() {
        $.beautifyInput(this, "fa fa-calendar", false);
        laydate.render({
            elem: this,
            type: "year",
            calendar: true, //开启公历节日
            theme: 'molv', //墨绿主题
            showBottom: true, //是否出现底部栏
            trigger: 'click' //绑定多个
        });
    });

    $('.tonto-datepicker-datetime').each(function() {
        $.beautifyInput(this, "fa fa-calendar", false);
        laydate.render({
            elem: this,
            type: "datetime",
            calendar: true, //开启公历节日
            theme: 'molv', //墨绿主题
            showBottom: true, //是否出现底部栏
            trigger: 'click' //绑定多个
        });
    });

    $('.tonto-datepicker-time').each(function() {
        $.beautifyInput(this, "fa fa-clock-o", false);
        laydate.render({
            elem: this,
            type: "time",
            calendar: true, //开启公历节日
            theme: 'molv', //墨绿主题
            showBottom: true, //是否出现底部栏
            trigger: 'click' //绑定多个
        });
    });

    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue', // 注意square和blue的对应关系
        radioClass: 'iradio_square-blue'
        //increaseArea: '10%' // optional
    });

    // 必须在icheck后面，否则需要更改源代码适用icheck
    $('.tonto-multiple-select').each(function() {
        $(this).multiselect({
            nonSelectedText: $(this).attr("placeholder") || "请选择", //未选择时显示文本
            allSelectedText: "全部", //全部选择时显示文本
            nSelectedText: "个选项", //超过显示数目时显示文本
            numberDisplayed: $(this).attr("number-displayed") || 2, //显示最大选项数目
            includeSelectAllOption: true, //是否有全选按钮
            selectAllText: "全选", //全选时显示文本
            selectAllNumber: false //全选时，不显示全选个数
            //enableFiltering: true,      // 查询过滤
            //filterPlaceholder: '输入查询内容', // 没有查询条件时显示文本
        });
    });

    $.extend({
        createTreeSelectComponment: function(input, type) {
            var $input = $(input);
            var $wrap = $('<div class="input-group"/>');
            var name = $input.attr("name") || $input.attr("id");
            $input.attr("name", "_" + name);
            var $hideinput = $('<input type="text" style="display:none" name="' + name + '" id="' + name + '"  />');
            var $removeBtn = $('<span class="input-group-addon" style="cursor:pointer"><i class="glyphicon glyphicon-remove"> </i></span>');
            var initValue = $input.attr("selectedvalue") || $input.val();

            $input.attr("readonly", true);
            $input.css("background", "#fff");

            $input.wrap($wrap);
            $input.after($removeBtn);
            $input.after($hideinput);

            var com = {
                input: $input,
                removeBtn: $removeBtn,
                name: name,
                valueInput: $hideinput,
                current: null,
                initValue: initValue,
                type: type,
                treedata: null,
                changedCallback: null,
                setCurrent: function(val) {
                    var that = this;

                    if (!that.current && !val) {
                        return;
                    }
                    if (that.current && val && that.current.value == val.value) {
                        return;
                    }

                    that.current = val;
                    that.input.val(val ? val.name : "");
                    that.valueInput.val(val ? val.value : "");

                    if (that.changedCallback) {
                        that.changedCallback(val);
                    }
                },
                getCurrent: function() {
                    var that = this;
                    if (!that.valueInput.val()) {
                        that.current = null;
                        return null;
                    }
                    return that.current;
                },
                setEnabled: function(enabled) {
                    if (enabled) {
                        this.input.attr('disabled', false);
                        this.valueInput.attr('disabled', false);
                        this.input.css("background", "#fff");
                    } else {
                        this.input.attr('disabled', true);
                        this.valueInput.attr('disabled', true);
                        this.input.css("background", "#eee");
                    }
                },
                setData: function(datalist) {
                    var that = this;
                    that.datalist = datalist;
                    var initValue = that.initValue;
                    var current;

                    var g = function(ns, all) {
                        if (!all) {
                            all = ns;
                            ns = $.grep(all, function(a, b) {
                                return a.parentValue === "-";
                            });
                        }
                        var nodes = [];
                        ns.forEach(function(n) {
                            var node = {
                                text: n.name,
                                data: n
                            };

                            if (n.value === initValue) {
                                current = n;
                            }

                            var parentValue = n.value;
                            var children = $.grep(all, function(a, b) {
                                return a.parentValue == parentValue;
                            });

                            if (children && children.length > 0) {
                                node.nodes = g(children, all);
                            }

                            nodes.push(node);
                        });
                        return nodes;
                    }

                    that.treedata = g(datalist);
                    that.setCurrent(current);

                    that.removeBtn.on("click", function() {
                        that.setCurrent(null);
                    });

                    that.input.click(function() {
                        layer.open({
                            type: 1,
                            title: "",
                            content: "<div class='tonto-tree-div'></div>",
                            area: ['350px', '460px'],
                            success: function(layero, index) {
                                $tree = $(layero).find('.tonto-tree-div');

                                $tree.treeview({
                                    data: that.treedata,
                                    levels: 1
                                });

                                $tree.on('nodeSelected', function(event, node) {
                                    var data = node.data;

                                    if (data.description === '不可选') {
                                        $.infoMessage("您不能选择该选项");
                                        return;
                                    }

                                    that.setCurrent(data);
                                    layer.close(index);
                                });
                            }
                        });
                    });
                }
            }

            $input.data("Tree_Select", com);
            $hideinput.data("Tree_Select", com);

            return com;
        }
    });

}

function _initValidator() {
    // --------------------------------------
    // Validate
    // --------------------------------------

    // this.optional(element) 指定了表单不为空才判断

    // 自然数
    $.validator.addMethod("naturalNumber", function(value, element) { return this.optional(element) || (/^[1-9]\d{0,9}$/.test(value)); }, "请输入大于0小于9999999999的整数");
    // 身份证
    $.validator.addMethod("identity", function(value, element) {
        var id = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;

        if (!id.test(value))
            return this.optional(element) || false;

        var y = value.substring(6, 10) * 1;
        var m = value.substring(10, 12) * 1 - 1;
        var d = value.substring(12, 14) * 1;

        var date = new Date(y, m, d);

        return this.optional(element) || (date.getFullYear() == y && date.getMonth() == m && date.getDate() == d);
    }, "身份证格式错误");
    // 邮编
    $.validator.addMethod("zip", function(value, element) { return this.optional(element) || (/^[0-9]\d{5}$/.test(value)); }, "邮编格式错误");
    // 账号
    $.validator.addMethod("account", function(value, element) { return this.optional(element) || (/^\w{5,30}$/.test(value)); }, "账号格式错误");
    // 手机
    $.validator.addMethod("cellphone", function(value, element) { return this.optional(element) || (/^[1][3,4,5,7,8][0-9]{9}$/.test(value)); }, "手机号码格式错误");
    // 电话（包括手机和座机）
    $.validator.addMethod("phone", function(value, element) { return this.optional(element) || (/((^\d{3,4}-?)?\d{7,8}(-(\d{3,}))?$)|(^[1][3,4,5,7,8][0-9]{9}$)/.test(value)); }, "电话号码格式错误");

    // 日期
    $.validator.addMethod("date", function(value, element) {
        var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
        if (r == null)
            return this.optional(element);

        var d = new Date(r[1], r[3] - 1, r[4]);
        return this.optional(element) || (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
    }, "日期格式不正确");

    // 大于
    $.validator.addMethod("largeThan", function(value, element, $name) {
        if ($name) {
            var minVal = $($name).val();
            if (minVal) {
                if (("string" === typeof(minVal) && /\d+\.?\d*/.test(minVal)) || "number" === typeof(minVal))
                    return this.optional(element) || value >= minVal * 1;
            }
        }
        return true;
    }, "输入值不能小于最小值");

    $.validator.addMethod("maxEngLength", function(value, element, maxlength) { return this.optional(element) || (value.replace(/[^\x00-\xff]/g, 'xx').length <= maxlength); }, "输入长度不能超过{0}");
    $.validator.addMethod("minEngLength", function(value, element, minlength) { return this.optional(element) || (value.replace(/[^\x00-\xff]/g, 'xx').length >= minlength); }, "输入长度不能少于{0}");

    $.extend($.fn, {
        createElementValidater: function(config, requiredStyle) {
            // 创建元素验证器
            for (var i = 0; this.length > i; i++) {
                var target = $(this[i]);

                var name = target.attr("name");
                var rules = config && config.rules && config.rules[name] || {};
                var messages = config && config.messages && config.messages[name] || {};

                var title = $("label[for='" + name + "']").text();
                title = $.trim(title);
                if (title && title.endsWith(":")) {
                    title = title.substring(0, title.length - 1);
                }

                var type = target.attr("data-type");
                if (type) {
                    if (type.indexOf(" ") != -1) {
                        var ts = type.split(" ");
                        for (var j = 0; j < ts.length; j++) {
                            rules[ts[j]] = true;
                        }
                    } else {
                        rules[type] = true;
                    }
                }

                if (target.hasClass("required") || target.attr("required") == "required") {
                    rules.required = true;

                    if (requiredStyle) {
                        target.addRequiredStyle();
                    }

                    if (!messages.required) {
                        var placeholder = target.attr("placeholder");
                        if (placeholder) {
                            messages.required = placeholder;
                        } else {
                            var domType = target[0].type;
                            if (domType == "text" || domType == "password") {
                                messages.required = "请输入" + title;
                            } else {
                                messages.required = "请选择" + title;
                            }
                        }
                    }
                }

                var area = target.attr("data-area");
                if (area) {
                    var numbers = area.split(",");
                    if (numbers.length > 0) {
                        if (numbers[0])
                            rules.min = numbers[0] * 1;
                        if (numbers.length > 1) {
                            if (numbers[1])
                                rules.max = numbers[1] * 1;
                        }
                    }
                }

                var length = target.attr("data-length");
                if (length) {
                    var lengths = length.split(",");
                    if (lengths) {
                        if (lengths.length > 0) {
                            if (lengths[0])
                                rules.minlength = lengths[0] * 1;
                            if (lengths.length > 1) {
                                if (lengths[1])
                                    rules.maxlength = lengths[1] * 1;
                            }
                        }
                    }
                }

                var length = target.attr("data-eng-length");
                if (length) {
                    var lengths = length.split(",");
                    if (lengths) {
                        if (lengths.length > 0) {
                            if (lengths[0])
                                rules.minEngLength = lengths[0] * 1;
                            if (lengths.length > 1) {
                                if (lengths[1])
                                    rules.maxEngLength = lengths[1] * 1;
                            }
                        }
                    }
                }

                var equalTo = target.attr("equalTo");
                if (equalTo) {
                    rules["equalTo"] = equalTo;
                }

                var largeThan = target.attr("large-than");
                if (largeThan) {
                    rules["largeThan"] = largeThan;
                    if (!messages.largeThan) {

                        var thanTarget = $(largeThan);
                        var thanName = thanTarget.attr("name");
                        var thanTitle = $("label[for='" + thanName + "']").text();
                        thanTitle = $.trim(thanTitle);
                        if (thanTitle && thanTitle.endsWith(":")) {
                            thanTitle = thanTitle.substring(0, thanTitle.length - 1);
                        }

                        if (thanTitle) {
                            messages.largeThan = "输入值必须大于" + thanTitle + "的值";
                        }
                    }
                }

                rules.messages = messages;
                target.rules("add", rules);
            }
        },
        createFormValidater: function(config) {
            // 创建表单验证器
            var validater = this.validate(config);
            this.find("input[type='text']:enabled,input[type='password']:enabled,input[type='hidden']:enabled,select:enabled,textarea:enabled").createElementValidater(config);
            this.data("validater", validater);
            return validater;
        },
        validateElement: function(element) {
            // 验证某个元素
            var validater = $(this).data("validater");
            return validater ? validater.element(element) : true;
        },
        addRequiredStyle: function() {
            // 添加必填样式
            var target = $(this);
            var inputGroupParent = target.parent(".input-group");
            if (inputGroupParent.length > 0) {
                inputGroupParent.children(":last-child").css("border-right", "2px solid red");
            } else {
                target.css("border-right", "2px solid red");
            }
        },
        removeRequiredStyle: function() {
            // 移除必填样式
            var target = $(this);
            var inputGroupParent = target.parent(".input-group");
            if (inputGroupParent.length > 0) {
                inputGroupParent.children(":last-child").css("border-right", "");
            } else {
                target.css("border-right", "");
            }
        }
    });
}

function _initTable() {
    /*
     * options 参数配置基于bootstrp table，参考
     * http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/ 修改参数： url:
     * 可以为方法，返回具体url字符串
     * 
     * 其中对table tree做了处理，可以获取指定form中的request param
     */
    var _tonto_table = function(el, options) {

        var defaultOptions = $.fn.bootstrapTable.defaults;

        if (typeof options === 'string')
            options = {
                url: options
            };

        if (!options.ajax) {
            options.ajax = function(request) {
                if (typeof url === 'function') {
                    request.url = request.url();
                }
                if (options.joinArrayValue) {
                    var d = request.data;
                    if (d) {
                        for (var o in d) {
                            var v = d[o];
                            if (v instanceof Array) {
                                d[o] = v.join(",");
                            }
                        }
                    }
                }
                $.sendAjax(request);
            }
        }

        if (!options.classes) {
            if (window.screen.height <= 900 || window.screen.width <= 1600) {
                options.classes = "table table-hover table-condensed";
            }
        }

        if (options.columns) {
            options.columns.forEach(function(item) {

                // 对列统一处理

                if (!$.isArray(item)) {
                    item = [item];
                }

                item.forEach(function(col) {
                    // formatter 定义数据类型转换，例如time，date等，在这里定义
                    if (col.formatter && typeof col.formatter === 'string') {
                        if (col.formatter == 'date') {
                            col.formatter = function(value, row, index) {
                                if (value) {
                                    if (!isNaN(value)) {
                                        return $.formatDate(new Date(value), "yyyy-MM-dd");
                                    }
                                    return value;
                                }
                                return "";
                            }
                        } else if (col.formatter == 'datetime') {
                            col.formatter = function(value, row, index) {
                                if (value) {
                                    if (!isNaN(value)) {
                                        return $.formatDate(new Date(value), "yyyy-MM-dd hh:mm:ss");
                                    }
                                    return value;
                                }
                                return "";
                            }
                        } else if (col.formatter == 'boolean') {
                            col.formatter = function(value, row, index) {
                                return (value === true || value === "true") ? "是" : "否";
                            }
                        }
                    }

                    // 枚举情况
                    if (col.enumcode && !col.formatter) {
                        col.formatter = $.getEnumColumnFormatter(window._constant_cache, col.enumcode);
                    }
                });
            });
        }

        var selfOptions = {
            sidePagination: 'server',
            dataField: 'data',
            totalField: 'total',
            treeParentField: 'parentId',
            pageList: [10, 20, 30],
            pageSize: 10
        }

        options = $.extend(selfOptions, options);

        if (options.searchbar) {
            var q = options.queryParams;

            options.queryParams = function(params) {
                if (q) {
                    params = q(params);
                }

                $(options.searchbar).serializeObject(params);

                return params;
            };
        }

        if (options.treeView) {

            // 对树状table支持，需要treetable.js

            var rh = options.responseHandler;
            options.responseHandler = function(res) {
                // 因为treetable插件中写死了使用parentId，这里需要对返回结果处理下(可以改写插件)
                if (rh)
                    res = rh(res);
                res = res || [];

                var isArray = $.isArray(res);

                var dataField = options.dataField || defaultOptions.dataField;
                var totalField = options.totalField || defaultOptions.totalField;

                var data = isArray ? res : res[dataField];

                if ($.isArray(data)) {
                    var idArr = [];
                    data.forEach(function(item) {
                        item.parentId = item[options.treeParentField];
                        // 如果支持搜索，则会有部分父节点没搜索出来（当然你可以只查询过滤叶节点），
                        // 在这里会把没有父节点的节点parentId = null，因而造成数据可能会不完整，使用时候注意
                        if (options.treeParentFilter && item.parentId) {
                            var treeId = options.treeId || defaultOptions.treeId;
                            var b = false;
                            for (var i = 0; i < data.length; i++) {
                                var a = data[i];
                                if (a[treeId] == item.parentId) {
                                    b = true;
                                    break;
                                }
                            }

                            if (!b) {
                                item.parentId = null;
                            }
                        }
                    });
                }

                if ($.isArray(res)) {
                    var x = {};
                    x[dataField] = res;
                    x[totalField] = res.length;
                    return x;
                } else {
                    return res;
                }
            };

            if (options.data) {
                options.responseHandler(options.data);
            }
        }

        if (!options.responseHandler) {
            // 用于判断对返回数据的简单处理，当结果是一个数组时对其封装为table能接收的格式
            options.responseHandler = function(res) {
                res = res || [];
                if ($.isArray(res)) {
                    var dataField = options.dataField || defaultOptions.dataField;
                    var totalField = options.totalField || defaultOptions.totalField;

                    var x = {};
                    x[dataField] = res;
                    x[totalField] = res.length;
                    return x;
                } else {
                    return res;
                }
            }
        }

        var $table = $(el);
        $table.bootstrapTable(options);
        return $table.data('bootstrap.table');
    };

    $.extend({
        /**
         * 创建boostrap table
         */
        createTable: function(el, options) {
            var tables = [];
            $(el).each(function(index) {
                tables.push(new _tonto_table($(this), options));
            });

            return tables.length == 1 ? tables[0] : tables;
        },
        /**
         * 获取常量formatter方法，用于bootstrap table column *
         */
        getEnumColumnFormatter: function(enumTypeMap, type) {
            if (enumTypeMap && type) {
                return function(value, row, index) {
                    var data = enumTypeMap[type];
                    if (data) {
                        for (var i = 0; i < data.length; i++)
                            if (data[i].key == value)
                                return data[i].value;
                    }
                    return "";
                };
            }
        },
        tableColumnFormatter: function(text, type, icon) {
            return function() { return '<a class="' + type + '" href="javascript:void(0);" ><i class="glyphicon glyphicon-' + (icon ? icon : 'cog') + '"></i>' + text + '</a>' };
        },
        addColumnFormatter: function(text) {
            return function() { return '<a class="add" href="javascript:void(0);" ><i class="glyphicon glyphicon-plus"></i>' + (text ? text : '新增') + '</a>' };
        },
        removeColumnFormatter: function(text) {
            return function() { return '<a class="remove" href="javascript:void(0);" ><i class="glyphicon glyphicon-remove"></i>' + (text ? text : '删除') + '</a>' };
        },
        editColumnFormatter: function(text) {
            return function() { return '<a class="edit" href="javascript:void(0);" ><i class="glyphicon glyphicon-edit"></i>' + (text ? text : '修改') + '</a>' };
        },
        viewColumnFormatter: function(text) {
            return function() { return '<a class="view" href="javascript:void(0);" ><i class="glyphicon glyphicon-eye-open"></i>' + (text ? text : '查看') + '</a>' };
        },
        confirmColumnFormatter: function(text) {
            return function() { return '<a class="confirm" href="javascript:void(0);" ><i class="glyphicon glyphicon-edit"></i>' + (text ? text : '确认') + '</a>' };
        },
        checkColumnFormatter: function(text) {
            return function() { return '<a class="check" href="javascript:void(0);" ><i class="glyphicon glyphicon-eye-open"></i>' + (text ? text : '确认') + '</a>' };
        }
    });

    $.fn.createTable = function() {
        var tables = [];
        this.each(function() {
            tables.push(new _tonto_table($(this), options));
        });
        return tables.length == 1 ? tables[0] : tables;
    };

    // 页码本地化

    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatLoadingMessage: function() {
            return '正在努力地加载数据中，请稍候……';
        },
        formatRecordsPerPage: function(pageNumber) {
            return pageNumber;
        },
        formatShowingRows: function(pageFrom, pageTo, totalRows) {
            return '显示' + pageFrom + ' - ' + pageTo + '条 ，共 ' + totalRows + ' 条';
        },
        formatSearch: function() {
            return '搜索';
        },
        formatNoMatches: function() {
            return '没有找到匹配的记录';
        },
        formatPaginationSwitch: function() {
            return '隐藏/显示分页';
        },
        formatRefresh: function() {
            return '刷新';
        },
        formatToggle: function() {
            return '切换';
        },
        formatColumns: function() {
            return '列';
        },
        formatExport: function() {
            return '导出数据';
        },
        formatClearFilters: function() {
            return '清空过滤';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

    // 修改排序部分代码，使sortName起效
    if ($.fn.bootstrapTable) {
        var BootstrapTable = $.fn.bootstrapTable.Constructor;
        BootstrapTable.prototype.onSort = function(event) {
            var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
                $this_ = this.$header.find('th').eq($this.index()),
                sortName = this.header.sortNames[$this.index()];

            this.$header.add(this.$header_).find('span.order').remove();

            if (this.options.sortName === $this.data('field')) {
                this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
            } else {
                this.options.sortName = sortName || $this.data('field');
                this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
            }
            this.trigger('sort', this.options.sortName, this.options.sortOrder);

            $this.add($this_).data('order', this.options.sortOrder);

            // Assign the correct sortable arrow
            this.getCaret();

            if (this.options.sidePagination === 'server') {
                this.initServer(this.options.silentSort);
                return;
            }

            this.initSort();
            this.initBody();
        };

        BootstrapTable.prototype.getCaret = function() {
            var that = this;

            $.each(this.$header.find('th'), function(i, th) {
                var sortName = that.header.sortNames[i];
                $(th).find('.sortable').removeClass('desc asc').addClass((sortName || $(th).data('field')) === that.options.sortName ? that.options.sortOrder : 'both');
            });
        };
    }
}


/**
 * 自动加载常量下拉框 <class = tonto-select-constant>
 */
function _initEnumConstant(container, enumcodes, callback) {
    var tc = $("#tonto_constant_value");
    if (tc.length > 0) {
        window._constant_cache = $.parseJSON(tc.text());
    }

    $.extend({
        // 获取常量
        getConstantEnum: function(enumcode) {
            if (enumcode) {
                return window._constant_cache[enumcode];
            }
        },
        getConstantEnumItem: function(enumcode, key) {
            var items = window._constant_cache[enumcode];
            if (items) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].key == key) {
                        return items[i];
                    }
                }
            }
            return null;
        },
        getConstantEnumValue: function(enumcode, key) {
            var items = window._constant_cache[enumcode];
            if (items) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].key == key) {
                        return items[i] && items[i].value;
                    }
                }
            }
            return null;
        }
    });

    // 下拉框
    var constants = container ? container.find(".tonto-select-constant") : $(".tonto-select-constant");
    constants.each(function() {
        var $s = $(this);
        var enumcode = $s.attr("enumcode");
        if (enumcode) {
            if ($s.is("select")) {
                var enumvalues = window._constant_cache[enumcode];
                if (enumvalues) {
                    enumvalues.forEach(function(a) {
                        $s.append("<option value='" + a.key + "'>" + a.value + "</option>");
                    });
                }
                var selectedvalue = $s.attr("selectedvalue");
                if (selectedvalue) {
                    if ($s.attr("multiple")) {
                        $s.val(selectedvalue.split(","));
                    } else {
                        $s.val(selectedvalue);
                    }
                }
            } else if ($s.is("p")) {
                var code = $s.attr("enum-code-value");
                if (code) {
                    $s.html($.getConstantEnumValue(enumcode, code));
                }
            }
        }
    });

    // 单选Radio
    constants = container ? container.find(".tonto-radio-constant") : $(".tonto-radio-constant");
    constants.each(function() {
        var $s = $(this);
        var enumcode = $s.attr("enumcode");
        if (enumcode) {
            var name = $s.attr("name") || $s.attr("id");
            var selectedvalue = $s.attr("selectedvalue");
            var enumvalues = window._constant_cache[enumcode];
            if (enumvalues) {
                var checked = false;
                enumvalues.forEach(function(a) {
                    if ((selectedvalue && selectedvalue == a.key) || (!selectedvalue && !checked)) {
                        $s.append('<label><input type="radio" checked="checked" name="' + name + '" value="' + a.key + '">&nbsp;&nbsp;' + a.value + '&nbsp;&nbsp;</label>');
                        checked = true;
                    } else {
                        $s.append('<label><input type="radio" name="' + name + '" value="' + a.key + '">&nbsp;&nbsp;' + a.value + '&nbsp;&nbsp;</label>');
                    }
                });
            }
        }
    });

    // 多选checkbox
    constants = container ? container.find(".tonto-checkbox-constant") : $(".tonto-checkbox-constant");
    constants.each(function() {
        var $s = $(this);
        var enumcode = $s.attr("enumcode");
        if (enumcode) {
            var name = $s.attr("name") || $s.attr("id");
            var selectedvalue = $s.attr("selectedvalue");
            if (selectedvalue) {
                selectedvalue = selectedvalue.split(",");
            }
            var enumvalues = window._constant_cache[enumcode];
            var isChecked = function(key) {
                if (selectedvalue) {
                    for (var i = 0; i < selectedvalue.length; i++) {
                        if (key == selectedvalue[i]) {
                            return true;
                        }
                    }
                }
                return false;
            }
            if (enumvalues) {
                enumvalues.forEach(function(a) {
                    if (isChecked(a.key)) {
                        $s.append('<label><input type="checkbox" checked="checked" name="' + name + '" value="' + a.key + '">&nbsp;&nbsp;' + a.value + '&nbsp;&nbsp;</label>');
                    } else {
                        $s.append('<label><input type="checkbox" name="' + name + '" value="' + a.key + '">&nbsp;&nbsp;' + a.value + '&nbsp;&nbsp;</label>');
                    }
                });
            }
        }
    });
}


/**
 * 加载form表单验证 <class = tonto-form-validate>
 */
function _initForm(container) {


    /**
     * 与ajax-form-submit结合处理子窗口提交form后回调（例如关闭子窗口并刷新父窗口表格）
     */
    $.extend({
        setLayerSubmitHandler: function(layero, index, submitSuccess, msg) {
            var forms = layer.getChildFrame('form', index);
            if (forms && forms.length > 0) {
                forms.each(function() {
                    $(this)[0].submitSuccessHandler = function(data) {
                        if (typeof submitSuccess == 'string') {
                            msg = submitSuccess;
                            submitSuccess = null;
                        }

                        if (msg) {
                            $.successMessage(msg);
                        }

                        if (submitSuccess) {
                            submitSuccess(data);
                        }
                    };
                });
            }
        }
    });

    $.fn.setFormSubmitHandler = function(submitSuccess, msg) {
        var form = $(this);

        if (typeof submitSuccess == 'string') {
            msg = submitSuccess;
            submitSuccess = null;
        }

        form[0].submitSuccessHandler = function(data) {
            if (msg) {
                $.successMessage(msg);
            }
            if (submitSuccess) {
                submitSuccess(data);
            }
        };
    };

    $.fn.createForm = function(formOptions, validateOptions) {
        var submitForm = $(this);
        var submitBtn = submitForm.find('button[type="submit"],input[type="submit"]')

        submitBtn.each(function() {
            var that = $(this);
            that.on('click', function(e) {
                if (that.data("loading")) {
                    return;
                }
                // ie处理placeholder提交问题
                if ($.browser && $.browser.msie) {
                    submitForm.find('[placeholder]').each(function() {
                        var $input = $(this);
                        if ($input.val() == $input.attr('placeholder')) {
                            $input.val('');
                        }
                    });
                }
                return true;
            });
        });

        var config = {
            debug: true,
            // 不要设置true，只有不想启用时候去设置false
            // 是否在获取焦点时验证
            // onfocusout: false,
            // 在keyup时验证.
            onkeyup: false,
            // 当鼠标掉级时验证
            onclick: false,
            backurl: submitForm.attr("callback-url"),
            // 给未通过验证的元素加效果,闪烁等
            // highlight : false,
            showErrors: function(errorMap, errorList) {
                $.each(errorList, function(i, v) {
                    // 在此处用了layer的方法,显示效果更美观
                    layer.tips(v.message, v.element, { time: 2000, tips: [3, 'red'] });
                    return false;
                });
            }
        };

        if (formOptions) {
            if (typeof formOptions === 'function') {
                config.formOptions = {
                    successCallback: formOptions
                }
            } else if (typeof formOptions === 'object') {
                config.formOptions = formOptions;
            }
        }

        if (validateOptions) {
            if (typeof validateOptions === 'object') {
                config = $.extend(config, validateOptions);
            }
        }

        config.submitHandler = function(a) {
            var form = $(a);
            var formConfig = {
                url: submitBtn.data('action') ? submitBtn.data('action') : form.attr('action'),
                dataType: 'json',
                beforeSubmit: function(arr, $form, options) {
                    submitBtn.each(function() {
                        var that = $(this);
                        that.data("loading", true);
                        var text = that.text();
                        that.data("orginText", text);
                        that.text(text + '中...').prop('disabled', true).addClass('disabled');
                    });

                    if (typeof formConfig.beforeCallback === 'function') {
                        return formConfig.beforeCallback(arr, $form, options);
                    }
                },
                success: function(data) {
                    submitBtn.each(function() {
                        var that = $(this);
                        var text = that.text();
                        that.removeClass('disabled').prop('disabled', false).text(that.data("orginText"));
                    });

                    var resStatus = data.status,
                        status = _RESPONSE_STATUS;

                    if (status.NO_LOGIN === resStatus) {
                        ajaxUnLoginHandler(form.data("submitSuccessHandler"));
                    } else if (status.NO_PERMISSION === resStatus) {
                        $.errorMessage(data.message || "您没有权限访问该页面或执行该操作");
                    } else if (status.ERROR === resStatus) {
                        $.errorMessage(data.message || "访问页面或执行操作错误");
                    } else if (status.FAIL === resStatus) {
                        $.errorMessage(data.message || "操作失败");
                    } else if (status.FAIL_VALID === resStatus) {
                        error = data.result;
                        if ($.isArray(error)) {
                            error.forEach(function(item) {
                                var el = item[1];
                                var errorMsg = item[2];
                                form.find("#" + el + ",[name='" + el + "']").each(function() {
                                    layer.tips(errorMsg, $(this), { time: 2000, tips: [3, 'red'] });
                                });
                            });
                        }
                    } else if (status.SUCCESS === resStatus) {
                        var handler = formConfig.successCallback || form[0].submitSuccessHandler || form.data("submitSuccessHandler");
                        if (handler) {
                            handler(data.result ? data.result : data);
                        } else {
                            if (config.backurl) {
                                layer.alert("操作成功", function(index) {
                                    layer.close(index);
                                    window.location = config.backurl;
                                });
                            }
                        }
                    }
                },
                error: function(xhr, e, statusText) {
                    $.errorMessage("系统异常");
                },
                complete: function() {
                    submitBtn.data("loading", false);
                }
            };

            if (config.formOptions) {
                formConfig = $.extend(formConfig, config.formOptions);
            }

            form.ajaxSubmit(formConfig);
        }

        submitForm.createFormValidater(config);
    }

    var forms = container ? $(container).find(".tonto-form-validate") : $(".tonto-form-validate");

    forms.each(function() {
        var submitForm = $(this);
        submitForm.createForm();
    });
}


// ------------------------------------------
//
// 附件处理
//
// -----------------------------------------

function _initAttachment() {
    $.extend({
        loadAttachment: function(id, callback) {
            $.getAjax("/common/attachment/" + id, function(data) {
                if (typeof callback === 'function') {
                    callback($.parseAttachmentData(data));
                }
            });
        },
        loadAttachments: function(ids, callback) {
            $.postAjax("/common/attachment", { id: ids }, function(data) {
                var result = {};
                if (data && data.length > 0) {
                    data.forEach(function(i) {
                        result[i.id] = $.parseAttachmentData(i);
                    });
                }
                if (typeof callback === 'function') {
                    callback(result);
                }
            });
        },
        parseAttachmentData(data) {
            if (data) {
                if ($.isArray(data)) {
                    var result = [];
                    data.forEach(function(item) {
                        result.push({
                            id: item.id,
                            name: item.name,
                            filename: item.name + item.suffix,
                            url: "/file/" + item.pelativePath,
                            size: item.size,
                            type: item.type
                        });
                    });
                    return result;
                } else {
                    return {
                        id: data.id,
                        name: data.name,
                        filename: data.name + data.suffix,
                        url: "/file/" + data.pelativePath,
                        size: data.size,
                        type: data.type
                    }
                }
            }
            return null;
        }
    });
}

// ------------------------------------------
//
// 页面处理
//
// -----------------------------------------


// ------------------------------------------
//
// 常用工具方法
//
// -----------------------------------------

/*
 * 时间格式化工具 
 * 把Long类型的1527672756454日期还原yyyy-MM-dd 00:00:00格式日期   
 */
function datetimeFormat(longTypeDate) {
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear(); //年    
    dateTypeDate += "-" + getMonth(date); //月     
    dateTypeDate += "-" + getDay(date); //日    
    dateTypeDate += " " + getHours(date); //时    
    dateTypeDate += ":" + getMinutes(date); //分  
    dateTypeDate += ":" + getSeconds(date); //分  
    return dateTypeDate;
}
/*  
 * 时间格式化工具 
 * 把Long类型的1527672756454日期还原yyyy-MM-dd格式日期   
 */
function dateFormat(longTypeDate) {
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear(); //年    
    dateTypeDate += "-" + getMonth(date); //月     
    dateTypeDate += "-" + getDay(date); //日    
    return dateTypeDate;
}
//返回 01-12 的月份值     
function getMonth(date) {
    var month = "";
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11    
    if (month < 10) {
        month = "0" + month;
    }
    return month;
}
//返回01-30的日期    
function getDay(date) {
    var day = "";
    day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    return day;
}
//小时  
function getHours(date) {
    var hours = "";
    hours = date.getHours();
    if (hours < 10) {
        hours = "0" + hours;
    }
    return hours;
}
//分  
function getMinutes(date) {
    var minute = "";
    minute = date.getMinutes();
    if (minute < 10) {
        minute = "0" + minute;
    }
    return minute;
}
//秒  
function getSeconds(date) {
    var second = "";
    second = date.getSeconds();
    if (second < 10) {
        second = "0" + second;
    }
    return second;
}


//------------------------------------------
//
// 自动化编辑查看代码
//
// -----------------------------------------

function generateHtml(options) {
    var id = options.id,
        name = options.name,
        columns = options.columns,
        icon = options.icon || 'glyphicon glyphicon-user';

    var html =
        '<div class="box box-solid">\n' +
        '<div class="box-header with-border">\n' +
        '    <i class="' + icon + '"></i>\n' +
        '    <h3 class="box-title">' + name + '</h3>\n' +
        '    <div class="box-tools pull-right">\n' +
        '        <a class="btn" id="' + id + '_edit_btn" href="javascript:void(0)"><i class="fa fa-edit"></i>编辑</a>\n' +
        '    </div>\n' +
        '</div>\n';

    html += generateViewFormHtml(options);
    html += generateEditFormHtml(options, true);

    html += '</div>\n';

    return html;
}

function generateEditHtml(options) {
    var id = options.id,
        name = options.name,
        columns = options.columns,
        icon = options.icon || 'glyphicon glyphicon-user';

    var html =
        '<div class="box box-solid">\n' +
        '<div class="box-header with-border">\n' +
        '    <i class="' + icon + '"></i>\n' +
        '    <h3 class="box-title">' + name + '</h3>\n' +
        '    <div class="box-tools pull-right">\n' +
        '    </div>\n' +
        '</div>\n';

    html += generateEditFormHtml(options);
    html += '</div>';
    return html;
}

function generateEditFormHtml(options, hide) {

    var id = options.id,
        columns = options.columns;

    var html =
        '<div id="' + id + '_edit" class="box-body" ' + (hide == true ? 'style="display: none"' : '') + '>\n' +
        '   <form id="' + id + '_form" action="' + options.url + '" method="post" class="form-horizontal edit-body">\n';

    var maxColspan = 2,
        currentColspan = 0,
        firstLabelSize = 3,
        inputSize = 3,
        labelSize = 2;

    for (var i = 0; i < columns.length;) {
        var column = columns[i++];
        var name = column.name,
            type = column.inputType,
            title = column.title,
            colspan = column.colspan || 1;

        if (colspan > maxColspan) colspan = maxColspan;

        if (type == 'ID') continue;

        // 附件独占一行
        var back = false;
        if (type == 'ATTACHMENT' && currentColspan > 0) {
            back = true;
        }

        if (currentColspan + colspan <= maxColspan) {
            if (currentColspan == 0) {
                html += '<div class="form-group">\n';
            }

            html += '<label for="' + name + '" class="col-sm-' + (currentColspan == 0 ? firstLabelSize : labelSize) + ' control-label">' + title + '：</label>\n';
            if (type == 'ATTACHMENT') {
                html += '<div class="col-sm-' + ((maxColspan - 1) * (inputSize + labelSize) + inputSize) + '"></div>\n';
                currentColspan = maxColspan;
            } else {
                html += '<div class="col-sm-' + ((colspan - 1) * (inputSize + labelSize) + inputSize) + '">\n';

                if (type == 'TEXT') {
                    html += '<input name="' + name + '" placeholder="请输入' + title + '" type="text" class="form-control"/>\n';
                } else if (type == 'DISTRICT') {
                    html += '<input name="' + name + '" placeholder="请输入' + title + '" type="text" class="form-control ' + column.districtType + '"/>\n';
                } else if (type == 'NUMBER') {
                    html += '<input name="' + name + '" placeholder="请输入' + title + '" type="number" class="form-control"/>\n';
                } else if (type == 'DATE') {
                    html += '<input name="' + name + '" placeholder="请输入' + title + '" type="text" class="form-control tonto-datepicker-date"/>\n';
                } else if (type == 'SELECT') {
                    html += '<select name="' + name + '" class="form-control tonto-select-constant" enumcode="' + column.enum + '">\n';
                    if (column.nullable !== false) {
                        html += '<option value="">请选择</option>\n';
                    }
                    html += '</select>\n';
                } else if (type == 'TREE') {
                    html += '<input name="' + name + '" placeholder="请选择' + title + '" type="text" class="form-control tonto-select-constant-tree" enumcode="' + column.enum + '"/>\n';
                }

                html += '</div>\n';
                currentColspan += colspan;
            }
        } else {
            back = true;
        }

        if (back) {
            i--;
            currentColspan = maxColspan;
        }

        if (currentColspan >= maxColspan) {
            html += '</div>\n';
            currentColspan = 0;
        }
    }

    if (currentColspan > 0) {
        html += '</div>\n';
        currentColspan = 0;
    }

    html +=
        '   <div class="form-group">\n' +
        '       <div class="col-sm-2 col-sm-offset-3">\n' +
        '           <button type="submit" id="' + id + '_form_submit_btn" class="btn btn-primary btn-block">保存</button>\n' +
        '       </div>\n';

    if (hide == true) {
        html +=
            '       <div class="col-sm-2 col-sm-offset-1">\n' +
            '       <button type="button" id="' + id + '_form_cancel_btn" class="btn btn-default btn-block">取消</button>\n' +
            '       </div>\n';
    }

    html +=
        '   </div>\n' +
        '</form>\n' +
        '</div>\n';
    return html;
}

function generateViewHtml(options) {
    var id = options.id,
        name = options.name,
        columns = options.columns,
        icon = options.icon || 'glyphicon glyphicon-user';

    var html =
        '<div class="box box-solid">\n' +
        '<div class="box-header with-border">\n' +
        '    <i class="' + icon + '"></i>\n' +
        '    <h3 class="box-title">' + name + '</h3>\n' +
        '    <div class="box-tools pull-right">\n' +
        '    </div>\n' +
        '</div>\n';

    html += generateViewFormHtml(options);
    html += '</div>';
    return html;
}

function generateViewFormHtml(options) {
    var id = options.id,
        columns = options.columns;
    var html =
        '<div id="' + id + '_view" class="box-body">\n' +
        '    <form class="form-horizontal">\n';

    var maxColspan = 2,
        currentColspan = 0,
        firstLabelSize = 3,
        inputSize = 3,
        labelSize = 2;

    for (var i = 0; i < columns.length;) {
        var column = columns[i++];
        var name = column.name,
            type = column.inputType,
            title = column.title,
            colspan = column.colspan || 1;

        if (colspan > maxColspan) colspan = maxColspan;

        if (type == 'ID') continue;

        // 附件独占一行
        var back = false;
        if (type == 'ATTACHMENT' && currentColspan > 0) {
            back = true;
        } else if (currentColspan + colspan <= maxColspan) {
            if (currentColspan == 0) {
                html += '<div class="form-group">\n';
            }

            html += '<label for="' + name + '" class="col-sm-' + (currentColspan == 0 ? firstLabelSize : labelSize) + ' control-label">' + title + '：</label>\n';
            if (type == 'ATTACHMENT') {
                html += '<div name="' + name + '" class="col-sm-' + ((maxColspan - 1) * (inputSize + labelSize) + inputSize) + '"></div>\n';
                currentColspan = maxColspan;
            } else {
                html += '<div class="col-sm-' + ((colspan - 1) * (inputSize + labelSize) + inputSize) + '">\n';
                html += '<p name="' + name + '" class="form-control-static description"></p>\n';
                html += '</div>\n';
                currentColspan += colspan;
            }
        } else {
            back = true;
        }

        if (back) {
            i--;
            currentColspan = maxColspan;
        }

        if (currentColspan >= maxColspan) {
            html += '</div>\n';
            currentColspan = 0;
        }
    }

    if (currentColspan > 0) {
        html += '</div>\n';
        currentColspan = 0;
    }

    html +=
        '   </form>\n' +
        '</div>\n';
    return html;
}


var _Model = function(name, column, options) {
    var that = this;
    that.name = name;
    that.editBtn = $("#" + name + "_edit_btn");
    that.addBtn = $("#" + name + "_add_btn");
    that.status = "view";
    that.viewBody = $("#" + name + "_view");
    that.editBody = $("#" + name + "_edit");
    that.formSubmitBtn = $("#" + name + "_form_submit_btn");
    that.formCancelBtn = $("#" + name + "_form_cancel_btn");
    that.formBody = $("#" + name + "_form");

    that.editBtn.click(function() {
        that.toEdit();
    });

    that.addBtn.click(function() {
        that.toAdd();
    });

    that.formCancelBtn.click(function() {
        that.toView();
    });

    that.columns = column;

    // 附件列
    that.attachmentColumn = null;

    that.dependency = {};
    that.checkboxColumn = {};

    if (that.columns) {
        that.columns.forEach(function(column) {
            if (column.inputType === 'ATTACHMENT') {
                that.attachmentColumn = column;
                that.attachmentColumn.disabled = false;
            }

            if (column.inputType === 'CHECKBOX') {
                that.checkboxColumn[column.name] = column;
            }

            if (column.dependency) {
                that.dependency[column.name] = [{
                    target: column.name,
                    dependColumn: column.dependency[0],
                    dependValue: column.dependency.slice(1, column.dependency.length + 1)
                }];
            }
        });

        for (var o in that.dependency) {
            var d = that.dependency[o];
            var fd = d[0].dependColumn;

            var p = that.dependency[fd];
            while (p) {
                d.push(p[0]);
                p = that.dependency[p[0].dependColumn];
            }
        }
    }

    that.config = $.extend({
        pattern: "normal", // edit:只能编辑,view:只能查看
        successCallback: function(data) {
            $.successMessage("保存成功");
            that.setData(data)
            that.toView();
        }
    }, options);

    // 创建表单提交
    if (that.formBody) {
        that.formBody.createForm({
            beforeCallback: function(formData) {
                var extraParam = that.config.extraParam;
                if (extraParam) {
                    if (typeof extraParam === 'function') {
                        extraParam = extraParam();
                    }

                    for (var o in extraParam) {
                        formData.push({
                            name: o,
                            value: extraParam[o],
                            type: "text",
                            required: false
                        });
                    }
                }

                if (that.attachmentColumn) {
                    var fileCount = 0;

                    // 原表单文件数据只有最后一个，这里需要手动从插件中获取File Object添加到表单数据中
                    var i = 0;

                    for (; i < formData.length; i++) {
                        if (formData[i].name == that.attachmentColumn.fileName) {
                            break;
                        }
                    }

                    formData.splice(i, 1);

                    if (that.attachmentColumn.disabled === false) {
                        // 有附件时，需要替换某些参数
                        var previews = that.inputAttachment.fileinput('getPreview');
                        var attachments = "";
                        if (previews && previews.config && previews.config.length > 0) {
                            previews.config.forEach(function(p) {
                                attachments += p.key + ",";
                                fileCount++;
                            });
                        }

                        // 动态加入已经上传的附件ID
                        formData.push({
                            name: that.attachmentColumn.name,
                            value: attachments,
                            type: "text",
                            required: false
                        });

                        var files = that.inputAttachment.fileinput('getFileStack');
                        if (files) {
                            files.forEach(function(file) {
                                formData.push({
                                    name: that.attachmentColumn.fileName,
                                    value: file,
                                    type: "file",
                                    required: false
                                });
                                fileCount++;
                            });
                        }

                        if (fileCount > 4) {
                            $.errorAlert("附件数量不能超过4个");
                            return false;
                        }
                    }
                }

                var beforeSubmit = that.config.beforeSubmit;
                if (beforeSubmit && typeof beforeSubmit === 'function') {
                    return beforeSubmit(formData);
                }
            },
            successCallback: that.config.successCallback
        });
    }

    that.initEditDependency();
}

_Model.prototype.getColumn = function(columnName) {
    for (var i = 0; i < this.columns.length; i++) {
        if (this.columns[i].name == columnName) {
            return this.columns[i];
        }
    }
    return null;
}

_Model.prototype.setData = function(data) {
    var that = this;
    that.data = data;

    if (that.data) {
        for (var o in that.checkboxColumn) {
            var col = that.checkboxColumn[o];
            var x = that.data[col.name];
            if (x) {
                that.data[col.name] = x.split(",");
            }
        }

        // 如果列依赖不成立时，列数据应该为空
        for (var o in that.dependency) {
            var depends = that.dependency[o];
            var tar = depends[0].target;

            if (!that.isDependencySatisfy(depends, that.data)) {
                that.data[tar] = null;
            }
        }

        if (that.attachmentColumn) {
            // 解析的附件
            var filename = that.attachmentColumn.fileName;
            that.data[filename] = $.parseAttachmentData(that.data[filename]);
        }
    }

    if (that.config.pattern == 'edit') {
        that.toEdit();
    } else {
        that.fillViewBody();
    }
}

_Model.prototype.fillViewBody = function() {
    var that = this;
    var data = that.data;
    if (that.columns) {
        that.columns.forEach(function(column) {
            if (column.inputType === 'RADIO') {
                that.viewBody.find("input[name='" + column.name + "'][value='" + that.getColumnValue(column, data) + "']").iCheck('check');
            } else if (column.inputType === 'CHECKBOX') {
                var v = that.getColumnValue(column, data);
                if (v) {
                    v.forEach(function(a) {
                        that.viewBody.find("input[name='" + column.name + "'][value='" + a + "']").iCheck('check');
                    });
                }
            } else {
                var p = that.viewBody.find("[name='" + column.name + "']");

                if (!p || p.length == 0 || column.inputType === 'ATTACHMENT') return;

                var v = that.getColumnValue(column, data);

                if (v) {
                    p.removeClass("text-muted");
                    p.text(v);
                } else {
                    p.addClass("text-muted");
                    p.text("无");
                }
            }
        });

        that.fillAttachment();
        that.checkViewDependency(that.viewBody, data);
    }
}

_Model.prototype.getColumnValue = function(column, data) {
    var v = data ? data[column.name] : null;

    if (column.inputType === 'SELECT') {
        return $.getConstantEnumValue(column.enum, v);
    } else if (column.inputType === 'TREE') {
        return $.getTreeConstantEnumValue(column.enum, v);
    } else if (column.inputType === 'DISTRICT') {
        return $.getDistrictFullName(v);
    } else if (column.inputType === 'DATE') {
        if (typeof v === 'number') {
            v = dateFormat(v);
        }
    }
    return v;
}

_Model.prototype.fillEditBody = function() {
    var that = this;
    var data = that.currentData;
    if (that.columns) {
        that.columns.forEach(function(column) {
            if (column.inputType === 'RADIO') {
                that.editBody.find("input[name='" + column.name + "'][value='" + that.getColumnValue(column, data) + "']").iCheck('check');
            } else if (column.inputType === 'CHECKBOX') {
                var v = that.getColumnValue(column, data);
                if (v) {
                    v.forEach(function(a) {
                        that.editBody.find("input[name='" + column.name + "'][value='" + a + "']").iCheck('check');
                    });
                }
            } else {
                var input = that.editBody.find("[name='" + column.name + "']");
                var ov = data ? data[column.name] : null;
                var v = ov,
                    isP = input.is("p");


                if (!input || column.inputType === 'ATTACHMENT') return;

                if (column.inputType === 'TREE') {
                    if (!isP) {
                        input.data("Tree_Select").setCurrent($.getTreeConstantEnumValue(column.enum, v));
                        return;
                    }
                }

                if (column.inputType === 'DISTRICT') {
                    if (!isP) {
                        input.data("District_Select").setCurrent($.getDistrict(v));
                        return;
                    } else {
                        input.text($.getDistrict(v).name);
                        return;
                    }
                }

                if (column.inputType === 'SELECT') {
                    v = $.getConstantEnumValue(column.enum, v);
                } else if (column.inputType === 'DATE') {
                    if (typeof v === 'number') {
                        v = dateFormat(v);
                    }
                }

                if (v) {
                    if (isP) {
                        input.removeClass("text-muted");
                        input.text(v);
                    } else {
                        if (column.inputType === 'SELECT') {
                            input.val(ov);
                        } else {
                            input.val(v);
                        }
                    }
                } else {
                    if (isP) {
                        input.addClass("text-muted");
                        input.text("无");
                    } else {
                        if (input.is("SELECT")) {
                            input[0].options[0].selected = true;
                        } else {
                            input.val("");
                        }
                    }
                }
            }
        });

        that.checkEditDependency();
    }
}

_Model.prototype.toAdd = function() {
    var that = this;
    that.currentData = null;
    that.addBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
    that.initAttachmentUploader();
}

_Model.prototype.toEdit = function(index) {
    var that = this;
    that.currentData = that.data;

    that.editBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
    that.initAttachmentUploader();
}

_Model.prototype.toView = function() {
    var that = this;
    that.editBtn.show();
    that.addBtn.show();
    that.viewBody.show();
    that.editBody.hide();
}

_Model.prototype.fillAttachment = function() {
    var that = this;
    if (that.attachmentColumn) {
        var name = that.attachmentColumn.name;
        var atts = that.data[that.attachmentColumn.fileName];

        if (atts) {

            // 方案1
            // var attDiv = that.viewBody.find('[name="' + name + '"]');
            // var html = '<label class="col-sm-3 control-label"><i class="icon fa fa-download"></i>附件下载：</label><div class="col-sm-6"><ul class="products-list product-list-in-box">';
            // for (var i = 0; i < atts.length; i++) {
            //     var b = atts[i];
            //     html += '<li class="item"><a target="_blank" href="' + b.url + '" download="' + b.filename + '">' + b.filename + '</a></li>';
            // }
            // html += "</ul></div>";
            // attDiv.html(html);

            // 方案2
            var attDiv = that.viewBody.find('[name="' + name + '"]');
            var html = '<ul class="mailbox-attachments clearfix">';
            for (var i = 0; i < atts.length; i++) {
                var b = atts[i];
                var k = b.filename.lastIndexOf(".");
                var suffix = "";
                if (k >= 0) {
                    suffix = b.filename.substring(k + 1).toLowerCase();
                }

                var header = "";
                if (suffix == "jpeg" || suffix == "jpg" || suffix == "png" || suffix == "gif") {
                    header = '<span class="mailbox-attachment-icon has-img"><img src="' + b.url + '" alt="Attachment"></span>';
                } else {
                    var iconMap = {
                        txt: "fa-file-text-o",
                        xls: "fa-file-excel-o",
                        xlsx: "fa-file-excel-o",
                        pdf: "fa-file-pdf-o",
                        doc: "fa-file-word-o",
                        docx: "fa-file-word-o",
                        rar: "fa-file-zip-o",
                        zip: "fa-file-zip-o"
                    }
                    var icon = iconMap[suffix] || "fa-file-o";
                    header = '<span class="mailbox-attachment-icon"><i class="fa ' + icon + '"></i></span>';
                }

                html +=
                    '<li>' + header +
                    '    <div class="mailbox-attachment-info">' +
                    '        <a target="_blank" href="' + b.url + '" class="mailbox-attachment-name"><i class="fa fa-camera"></i>' + b.filename + '</a>' +
                    '        <span class="mailbox-attachment-size">' + (Math.floor(b.size / 1024) + "KB") + '<a target="_blank" download="' + b.filename + '" href="' + b.url + '" class="btn btn-default btn-xs pull-right"><i class="fa fa-cloud-download"></i></a></span>' +
                    '    </div>' +
                    '</li>';
            }
            html += "</ul>";
            attDiv.html(html);
        }
    }
}

_Model.prototype.initAttachmentUploader = function(fileInput, files) {
    var that = this;
    if (that.attachmentColumn) {
        var name = that.attachmentColumn.fileName;
        var atts = that.currentData ? that.currentData[name] : null;
        var fileInput = that.formBody.find('[name="' + name + '"]');

        var initialPreview = [];
        var initialPreviewConfig = [];
        if (atts) {
            atts.forEach(function(att) {
                initialPreview.push(att.url);
                initialPreviewConfig.push({
                    caption: att.filename,
                    size: att.size,
                    //url:"/common/constant?code=sex",
                    key: att.id
                });
            });
        }

        if (that.inputAttachment) {
            that.inputAttachment.fileinput('destroy');
        }

        that.inputAttachment = $(fileInput).fileinput({
            language: 'zh',
            uploadUrl: '/common/upload/files',
            showUpload: false,
            layoutTemplates: {
                actionUpload: '' //去除上传预览缩略图中的上传图片；
            },
            uploadAsync: false,
            maxFileCount: 4,
            allowedFileExtensions: that.attachmentColumn.allowedFileExtensions || ["jpeg", "jpg", "png", "gif"],
            overwriteInitial: false,
            ajaxDelete: false, // 扩展定义配置，不进行后台删除操作
            initialPreview: initialPreview,
            initialPreviewAsData: true, // allows you to set a raw markup
            initialPreviewFileType: 'image', // image is the default and can be overridden in config below
            initialPreviewConfig: initialPreviewConfig
        });
    }
}

_Model.prototype.isInDependencyValues = function(val, vals) {
    if (val != null && val != undefined && val !== "") {
        if ($.isArray(val)) {
            for (var i = 0; i < val.length; i++) {
                var v = val[i];
                for (var j = 0; j < vals.length; j++) {
                    if (v == vals[s]) {
                        return true;
                    }
                }
            }
        } else {
            for (var i = 0; i < vals.length; i++) {
                if (val == vals[i]) {
                    return true;
                }
            }
        }
    }
    return false;
}

_Model.prototype.isDependencySatisfy = function(dependencies, data) {
    for (var i = 0; i < dependencies.length; i++) {
        var dep = dependencies[i];
        if (!this.isInDependencyValues(data[dep.dependColumn], dep.dependValue)) {
            return false;
        }
    }
    return true;
}

_Model.prototype.checkViewDependency = function(container, data) {
    var that = this;
    for (var o in that.dependency) {
        var depends = that.dependency[o];
        var tar = depends[0].target;
        var tc = that.getColumn(tar);
        var div;

        if (tc.inputType == 'ATTACHMENT') {
            div = container.find("[name='" + tc.fileName + "']:eq(0)").parents(".form-group");
        } else {
            div = container.find("[name='" + tar + "']:eq(0)").parents(".form-group");
        }

        if (!that.isDependencySatisfy(depends, data)) {
            if (!div.hasClass("hidden-column")) {
                div.addClass("hidden-column");
            }
        } else {
            if (div.hasClass("hidden-column")) {
                div.removeClass("hidden-column");
            }
        }
    }
}

_Model.prototype.checkEditDependency = function() {
    var that = this;
    for (var o in that.dependency) {
        var dependencies = that.dependency[o];
        var isOk = true;

        for (var i = 0; i < dependencies.length; i++) {
            var dep = dependencies[i];
            var val;
            var dc = that.getColumn(dep.dependColumn);
            if (dc.inputType == 'RADIO' || dc.inputType == 'CHECKBOX') {
                val = that.editBody.find("input[name='" + dep.dependColumn + "']:checked").val();
            } else {
                val = that.editBody.find("[name='" + dep.dependColumn + "']").val();
            }

            if (!that.isInDependencyValues(val, dep.dependValue)) {
                isOk = false;
                break;
            }
        }

        var tc = that.getColumn(dependencies[0].target);
        var input;
        if (tc.inputType == 'ATTACHMENT') {
            input = that.editBody.find("[name='" + tc.fileName + "']");
        } else {
            input = that.editBody.find("[name='" + tc.name + "']");
        }

        var div = input.parents(".form-group");
        if (isOk) {
            if (div.hasClass("hidden-column")) {
                div.removeClass("hidden-column");
            }

            if (tc.inputType == 'ATTACHMENT') {
                //input.fileinput('enable');
                tc.disabled = false;
            } else {
                input.removeAttr("disabled");
            }

        } else {
            if (!div.hasClass("hidden-column")) {
                div.addClass("hidden-column");
            }

            if (tc.inputType == 'ATTACHMENT') {
                //input.fileinput('disable');
                tc.disabled = true;
            } else {
                input.attr("disabled", true);
            }
        }
    }
}

_Model.prototype.initEditDependency = function() {
    var that = this;
    var cache = {};
    for (var o in that.dependency) {
        var depend = that.dependency[o][0];
        if (cache[depend.dependColumn]) {
            continue;
        }
        var dc = that.getColumn(depend.dependColumn);
        if (dc.inputType == 'RADIO' || dc.inputType == 'CHECKBOX') {
            // 这里使用icheck 所以调用ifChecked事件
            that.editBody.find("input[name='" + depend.dependColumn + "']").on('ifChecked', function() {
                that.checkEditDependency();
            });
        } else {
            that.editBody.find("[name='" + depend.dependColumn + "']").change(function() {
                that.checkEditDependency();
            });
        }
        cache[depend.dependColumn] = 1;
    }
}

if (!window.toton) window.toton = {};
window.tonto.Model = _Model;