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

    var defaultConfig = {
            maxColspan: 2,
            firstLabelSize: 3,
            inputSize: 3,
            labelSize: 2
        },
        currentColspan = 0;

    options = $.extend(defaultConfig, options);

    for (var i = 0; i < columns.length;) {
        var column = columns[i++],
            fieldBuilder = _FieldBuilderContainer[column.inputType],
            colspan = fieldBuilder.generateEditFormColspan(column, options);

        // 附件独占一行
        if (currentColspan + colspan <= options.maxColspan) {
            if (currentColspan == 0) {
                html += '<div class="form-group">\n';
            }

            result = fieldBuilder.generateEditFormHtml(column, currentColspan == 0 ? true : false, options);
            html += result.html;

            if (result.colspan == 0) {
                continue;
            } else if (result.back === true) {
                i--;
                currentColspan = currentColspan > 0 ? maxColspan : 0;
            } else {
                currentColspan += result.colspan;
            }
        } else {
            i--;
            if (currentColspan == 0) {
                console && console.log("域[name:" + column.name + "]生成colspan大于最大colspan");
                continue;
            } else {
                currentColspan = options.maxColspan;
            }
        }

        if (currentColspan >= options.maxColspan) {
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

    var defaultConfig = {
            maxColspan: 2,
            firstLabelSize: 3,
            inputSize: 3,
            labelSize: 2
        },
        currentColspan = 0;

    options = $.extend(defaultConfig, options);

    for (var i = 0; i < columns.length;) {
        var column = columns[i++],
            fieldBuilder = _FieldBuilderContainer[column.inputType],
            colspan = fieldBuilder.generateViewFormColspan(column, options);

        // 附件独占一行
        if (currentColspan + colspan <= options.maxColspan) {
            if (currentColspan == 0) {
                html += '<div class="form-group">\n';
            }

            result = fieldBuilder.generateViewFormHtml(column, currentColspan == 0 ? true : false, options);
            html += result.html;

            if (result.colspan == 0) {
                continue;
            } else if (result.back === true) {
                i--;
                currentColspan = currentColspan > 0 ? maxColspan : 0;
            } else {
                currentColspan += result.colspan;
            }
        } else {
            i--;
            if (currentColspan == 0) {
                console && console.log("域[name:" + column.name + "]生成colspan大于最大colspan");
                continue;
            } else {
                currentColspan = options.maxColspan;
            }
        }

        if (currentColspan >= options.maxColspan) {
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

    // 注入域构建器
    if (that.columns) {
        that.columns.forEach(function(column) {
            column.fieldBuilder = _FieldBuilderContainer[column.inputType];
            if (!column.fieldBuilder && console) {
                console.log("找不到对应的域构造器[inputType:" + column.inputType + "]");
            }

            column.viewDisplay = column.viewDisplay || "show";
            column.editDisplay = column.editDisplay || "show";
        });
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

                that.columns.forEach(function(column) {
                    if (column.fieldBuilder.formDataHandler(column, formData, that) === false) {
                        return false;
                    }
                });

                var beforeSubmit = that.config.beforeSubmit;
                if (beforeSubmit && typeof beforeSubmit === 'function') {
                    return beforeSubmit(formData);
                }
            },
            successCallback: that.config.successCallback
        });
    }

    // 初始化依赖关系
    that.dependency = {};
    if (that.columns) {
        that.columns.forEach(function(column) {
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

        var cache = {};
        for (var o in that.dependency) {
            var depend = that.dependency[o][0];
            if (cache[depend.dependColumn]) {
                continue;
            }
            var dc = that.getColumn(depend.dependColumn);
            dc.fieldBuilder.dependTrigger(dc, that);
            cache[depend.dependColumn] = 1;
        }

        if (that.config.pattern == 'view') {
            that.editBtn.hide();
        }
    }
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
        // 如果列依赖不成立时，列数据应该为空
        for (var o in that.dependency) {
            var depends = that.dependency[o];
            var tar = depends[0].target;

            if (!that.isDependencySatisfy(depends, that.data)) {
                that.data[tar] = null;
            }
        }
    }

    if (that.columns) {
        that.columns.forEach(function(column) {
            column.fieldBuilder.setDataHandler(column, data, that);
        });
    }

    if (that.config.pattern == 'edit') {
        that.toEdit();
    } else {
        that.fillViewBody();
    }
}

_Model.prototype.fillViewBody = function() {
    var that = this,
        data = that.data;
    if (that.columns) {
        that.filling = true;
        that.columns.forEach(function(column) {
            column.fieldBuilder.fillView(column, data, that);
        });

        that.filling = false;
        that.checkViewDependency();
    }
}

_Model.prototype.fillEditBody = function() {
    var that = this,
        data = that.data;
    if (that.columns) {
        that.filling = true;
        that.columns.forEach(function(column) {
            column.fieldBuilder.fillEdit(column, data, that);
        });
        that.filling = false;
        that.checkEditDependency();
    }
}

_Model.prototype.toAdd = function() {
    var that = this;
    that.data = null;
    that.addBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
}

_Model.prototype.toEdit = function() {
    var that = this;
    that.editBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
}

_Model.prototype.toView = function() {
    var that = this;
    that.editBtn.show();
    that.addBtn.show();
    that.viewBody.show();
    that.editBody.hide();
}

_Model.prototype.isInDependencyValues = function(val, vals) {
    // 是否在依赖值内
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
    // 是否满足依赖
    for (var i = 0; i < dependencies.length; i++) {
        var dep = dependencies[i];
        if (!this.isInDependencyValues(data[dep.dependColumn], dep.dependValue)) {
            return false;
        }
    }
    return true;
}

_Model.prototype.checkViewDependency = function() {
    // 检查VIEW页面依赖
    var that = this,
        data = that.data;
    for (var o in that.dependency) {
        var depends = that.dependency[o],
            targetColumn = that.getColumn(depends[0].target);
        if (!that.isDependencySatisfy(depends, data)) {
            targetColumn.fieldBuilder.hideView(targetColumn, that);
        } else {
            targetColumn.fieldBuilder.showView(targetColumn, that);
        }
    }
}

_Model.prototype.checkEditDependency = function() {
    // 检查EDIT页面依赖
    var that = this;
    for (var o in that.dependency) {
        var dependencies = that.dependency[o];
        var isOk = true;

        for (var i = 0; i < dependencies.length; i++) {
            var depend = dependencies[i],
                dependCol = that.getColumn(depend.dependColumn),
                val = dependCol.fieldBuilder.getEditValue(dependCol, that);

            if (!that.isInDependencyValues(val, depend.dependValue)) {
                isOk = false;
                break;
            }
        }

        var targetCol = that.getColumn(dependencies[0].target);
        if (isOk) {
            targetCol.fieldBuilder.showEdit(targetCol, that);
        } else {
            targetCol.fieldBuilder.hideEdit(targetCol, that);
        }
    }
}

var _FieldBuilderContainer = {};
var _FieldBuilder = function(name, interfaces) {
    var that = this;
    that.name = name;
    var defaultInterfaces = {
        setDataHandler: function(column, data, model) {
            // 插入数据时候调用
            if (typeof column.setDataHandler === 'function') {
                return column.setDataHandler(column, data, model);
            }

            if (data && column.separator) {
                var v = data[column.name];
                if (v) {
                    data[column.name] = v.split(column.separator);
                }
            }
        },
        formDataHandler: function(column, formData, model) {
            // 提交表单数据调用
            if (typeof column.formDataHandler === 'function') {
                return column.formDataHandler(column, formData, model);
            }
        },
        dependTrigger: function(column, model) {
            // 依赖域变化注册，监听依赖域变更
            if (typeof column.dependTrigger === 'function') {
                return column.dependTrigger(column, model);
            }
            model.editBody.find("[name='" + column.name + "']").change(function() {
                if (model.filling === false) {
                    model.checkEditDependency();
                }
            });
        },
        getEditValue: function(column, model) {
            // 获取域EDIT页面值
            if (typeof column.getEditValue === 'function') {
                return column.getEditValue(column, model);
            }
            return model.editBody.find("[name='" + column.name + "']").val();
        },
        hideView: function(column, model) {
            if (column.viewDisplay === "hide") {
                return;
            }

            // VIEW页面列隐藏时候调用
            if (typeof column.hideView === 'function') {
                column.hideView(column, model);
                column.viewDisplay = "hide";
                return;
            }

            var p = model.viewBody.find("[name='" + column.name + "']");
            if (!p || p.length == 0) return;
            var d = p.parent(),
                f = d.parent();
            d.hide();
            d.prev().hide();
            if (f.children(":visible").length == 0) {
                f.hide();
            }

            column.viewDisplay = "hide";
        },
        showView: function(column, model) {
            if (column.viewDisplay === "show") {
                return;
            }

            // VIEW页面列显示时候调用
            if (typeof column.showView === 'function') {
                column.showView(column, model);
                column.viewDisplay = "show";
                return;
            }

            var p = model.viewBody.find("[name='" + column.name + "']");
            if (!p || p.length == 0) return;
            var d = p.parent();
            d.show();
            d.prev().show();
            d.parent().show();

            column.viewDisplay = "show";
        },
        fillView: function(column, data, model) {
            // VIEW页面填充值时候调用
            if (typeof column.fillView === 'function') {
                return column.fillView(column, data, model);
            }

            var p = model.viewBody.find("[name='" + column.name + "']");
            if (!p || p.length == 0) return;
            var v = data ? data[column.name] : null;

            if (v) {
                p.removeClass("text-muted");
                p.text(v);
            } else {
                p.addClass("text-muted");
                p.text("无");
            }
        },
        hideEdit: function(column, model) {
            if (column.editDisplay === "hide") {
                return;
            }

            // EDIT页面列隐藏时候调用
            if (typeof column.hideEdit === 'function') {
                column.hideEdit(column, model);
                column.editDisplay = "hide";
                return;
            }

            var p = model.editBody.find("[name='" + column.name + "']");
            if (!p || p.length == 0) return;
            var d = p.parent(),
                f = d.parent();
            d.hide();
            d.prev().hide();
            if (f.children(":visible").length == 0) {
                f.hide();
            }
            column.editDisplay = "hide";
        },
        showEdit: function(column, model) {
            if (column.editDisplay === "show") {
                return;
            }

            // EDIT页面列隐藏时候调用
            if (typeof column.showEdit === 'function') {
                column.showEdit(column, model);
                column.editDisplay = "show";
                return;
            }

            var p = model.editBody.find("[name='" + column.name + "']");
            if (!p || p.length == 0) return;
            var d = p.parent();
            d.show();
            d.prev().show();
            d.parent().show();
            column.editDisplay = "show";
        },
        fillEdit: function(column, data, model) {
            // EDIT页面填充值时候调用
            if (typeof column.fillEdit === 'function') {
                return column.fillEdit(column, data, model);
            }

            var input = model.editBody.find("[name='" + column.name + "']");
            if (!input && input.length == 0) return;

            var v = data ? data[column.name] : null,
                isP = input.is("p");

            if (v) {
                if (isP) {
                    input.removeClass("text-muted");
                    input.text(v);
                } else {
                    input.val(v);
                }
            } else {
                if (isP) {
                    input.addClass("text-muted");
                    input.text("无");
                } else {
                    input.val("");
                }
            }
        },
        generateViewFormColspan: function(column, options) {
            if (typeof column.generateViewFormColspan === 'function') {
                return column.generateViewFormColspan(column, options);
            }
            return column.colspan || 1;
        },
        generateViewFormHtml: function(column, isFirst, options) {
            if (typeof column.generateViewFormHtml === 'function') {
                return column.generateViewFormHtml(column, isFirst, options);
            }
            var colspan = column.colspan || 1,
                html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
            html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
            html += '<p name="' + column.name + '" class="form-control-static description"></p>\n';
            html += '</div>\n';
            return {
                colspan: colspan,
                html: html
            };
        },
        generateEditFormColspan: function(column, options) {
            if (typeof column.generateEditFormColspan === 'function') {
                return column.generateEditFormColspan(column, options);
            }
            return column.colspan || 1;
        },
        generateEditFormHtml: function(column, isFirst, options) {
            if (typeof column.generateEditFormHtml === 'function') {
                return column.generateEditFormHtml(column, isFirst, options);
            }
            var colspan = column.colspan || 1,
                html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
            html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
            html += '<input name="' + column.name + '" placeholder="请输入' + column.title + '" type="text" class="form-control"/>\n';
            html += '</div>\n';
            return {
                colspan: colspan,
                html: html
            };
        }
    }

    interfaces = $.extend(defaultInterfaces, interfaces);

    if (_FieldBuilderContainer[name]) {
        console && console.log("存在相同名称的域构建器[name:" + name + "]");
    }

    for (var o in interfaces) {
        that[o] = interfaces[o];
    }

    _FieldBuilderContainer[name] = that;
}

// 文本域构建器
var _textFieldBuilder = new _FieldBuilder("TEXT", {});

// 大文本域构建器
var _textAreaFieldBuilder = new _FieldBuilder("TEXTAREA", {
    generateViewFormColspan: function(column, options) {
        if (typeof column.generateViewFormColspan === 'function') {
            return column.generateViewFormColspan(column, options);
        }
        return column.colspan || options.maxColspan;
    },
    generateViewFormHtml: function(column, isFirst, options) {
        if (typeof column.generateViewFormHtml === 'function') {
            return column.generateViewFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || options.maxColspan;
        html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<pre name="' + column.name + '" style="min-height:150px" class="form-control-static description"></pre>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || options.maxColspan;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || options.maxColspan;
        html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<textarea name="' + column.name + '" rows="' + (column.rows || 5) + '" placeholder="请输入' + column.title + '" class="form-control"></textarea>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 日期域构建器
var _dateFieldBuilder = new _FieldBuilder("DATE", {
    setDataHandler: function(column, data, model) {
        // 插入数据时候调用
        if (typeof column.setDataHandler === 'function') {
            return column.setDataHandler(column, data, model);
        }

        var v = data && data[column.name];
        if (typeof v === 'number') {
            data[column.name] = dateFormat(v);
        }
    },
    hideEdit: function(column, model) {
        if (column.editDisplay === "hide") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.hideEdit === 'function') {
            column.hideEdit(column, model);
            column.editDisplay = "hide";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent(),
            f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }
        column.editDisplay = "hide";
    },
    showEdit: function(column, model) {
        if (column.editDisplay === "show") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.showEdit === 'function') {
            column.showEdit(column, model);
            column.editDisplay = "show";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent();
        d.show();
        d.prev().show();
        d.parent().show();
        column.editDisplay = "show";
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<input name="' + column.name + '" autocomplete="off" placeholder="请输入' + column.title + '" type="text" class="form-control tonto-datepicker-date"/>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 时间域构建器
var _timeFieldBuilder = new _FieldBuilder("TIME", {
    setDataHandler: function(column, data, model) {
        // 插入数据时候调用
        if (typeof column.setDataHandler === 'function') {
            return column.setDataHandler(column, data, model);
        }

        var v = data && data[column.name];
        if (typeof v === 'number') {
            data[column.name] = datetimeFormat(v);
        }
    },
    hideEdit: function(column, model) {
        if (column.editDisplay === "hide") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.hideEdit === 'function') {
            column.hideEdit(column, model);
            column.editDisplay = "hide";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent(),
            f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }
        column.editDisplay = "hide";
    },
    showEdit: function(column, model) {
        if (column.editDisplay === "show") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.showEdit === 'function') {
            column.showEdit(column, model);
            column.editDisplay = "show";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent();
        d.show();
        d.prev().show();
        d.parent().show();
        column.editDisplay = "show";
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<input name="' + column.name + '" autocomplete="off" placeholder="请输入' + column.title + '" type="text" class="form-control tonto-datepicker-datetime"/>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 下拉框域构建器
var _selectFieldBuilder = new _FieldBuilder("SELECT", {
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var p = model.viewBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var v = data ? data[column.name] : null;
        if (column.enum && v) {
            v = $.getConstantEnumValue(column.enum, v);
        }

        if (v) {
            p.removeClass("text-muted");
            p.text(v);
        } else {
            p.addClass("text-muted");
            p.text("无");
        }
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        var input = model.editBody.find("[name='" + column.name + "']");
        if (!input && input.length == 0) return;

        var ov = data ? data[column.name] : null,
            isP = input.is("p"),
            v = column.enum && ov ? $.getConstantEnumValue(column.enum, ov) : null;

        if (isP) {
            if (v) {
                input.removeClass("text-muted");
                input.text(v);
            } else {
                input.addClass("text-muted");
                input.text("无");
            }
        } else {
            if (ov) {
                input.val(ov);
            } else {
                input.find("option:first").prop("selected", 'selected');
            }
        }
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<select name="' + column.name + '" class="form-control tonto-select-constant" enumcode="' + column.enum + '">\n';
        if (column.nullable !== false) {
            html += '<option value="">请选择</option>\n';
        }
        html += '</select>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };

    }
});

// 附件域构建器
var _attachmentFieldBuilder = new _FieldBuilder("ATTACHMENT", {
    setDataHandler: function(column, data, model) {
        // 插入数据时候调用
        if (typeof column.setDataHandler === 'function') {
            return column.setDataHandler(column, data, model);
        }

        // 解析的附件
        if (!data) return;

        var filename = column.fileName,
            v = data[column.name];
        data[filename] = $.parseAttachmentData(data[filename]);
        if (v) {
            data[column.name] = v.split(column.separator || ",");
        }
    },
    formDataHandler: function(column, formData, model) {
        // 提交表单数据调用
        if (typeof column.formDataHandler === 'function') {
            return column.formDataHandler(column, formData, model);
        }

        var maxFileCount = column.maxFileCount || 5,
            fileName = column.fileName,
            fileCount = 0,
            i = 0;

        // 原表单文件数据只有最后一个，这里需要手动从插件中获取File Object添加到表单数据中
        for (; i < formData.length; i++) {
            if (formData[i].name == fileName) {
                break;
            }
        }

        formData.splice(i, 1);

        if (column.editDisplay !== "hide") {
            // 有附件时，需要替换某些参数
            var previews = column.inputAttachment.fileinput('getPreview');
            var attachments = "";
            if (previews && previews.config && previews.config.length > 0) {
                previews.config.forEach(function(p) {
                    attachments += p.key + ",";
                    fileCount++;
                });
            }

            // 动态加入已经上传的附件ID
            formData.push({
                name: column.name,
                value: attachments,
                type: "text",
                required: false
            });

            // 动态加入未上传的文件数据
            var files = column.inputAttachment.fileinput('getFileStack');
            if (files) {
                files.forEach(function(file) {
                    formData.push({
                        name: fileName,
                        value: file,
                        type: "file",
                        required: false
                    });
                    fileCount++;
                });
            }

            if (fileCount > maxFileCount) {
                $.errorAlert("附件数量不能超过" + maxFileCount + "个");
                return false;
            }
        }
    },
    dependTrigger: function(column, model) {
        // 依赖域变化注册，监听依赖域变更
        if (typeof column.dependTrigger === 'function') {
            return column.dependTrigger(column, model);
        }
        // 不能被依赖
    },
    getEditValue: function(column, model) {
        // 获取域EDIT页面值
        if (typeof column.getEditValue === 'function') {
            return column.getEditValue(column, model);
        }
        console && console.log("附件不应该被依赖");
    },
    hideView: function(column, model) {
        if (column.viewDisplay === "hide") {
            return;
        }

        // VIEW页面列隐藏时候调用
        if (typeof column.hideView === 'function') {
            column.hideView(column, model);
            column.viewDisplay = "hide";
            return;
        }

        var d = model.viewBody.find("[name='" + column.name + "']");
        if (!d || d.length == 0) return;
        var f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }

        column.viewDisplay = "hide";
    },
    showView: function(column, model) {
        if (column.viewDisplay === "show") {
            return;
        }

        // VIEW页面列显示时候调用
        if (typeof column.showView === 'function') {
            column.showView(column, model);
            column.viewDisplay = "show";
            return;
        }

        var d = model.viewBody.find("[name='" + column.name + "']");
        if (!d || d.length == 0) return;
        d.show();
        d.prev().show();
        d.parent().show();
        column.viewDisplay = "show";
    },
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var name = column.name,
            atts = data && data[column.fileName];

        if (atts) {
            var attDiv = model.viewBody.find('[name="' + name + '"]');
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
    },
    hideEdit: function(column, model) {
        if (column.editDisplay === "hide") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.hideEdit === 'function') {
            column.hideEdit(column, model);
            column.editDisplay = "hide";
            return;
        }

        var i = model.editBody.find("[name='" + column.fileName + "']");
        if (!i || i.length == 0) return;
        var d = i.parent().parent().parent().parent().parent();
        var f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }
        column.editDisplay = "hide";
    },
    showEdit: function(column, model) {
        if (column.editDisplay === "show") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.showEdit === 'function') {
            column.showEdit(column, model);
            column.editDisplay = "show";
            return;
        }

        var i = model.editBody.find("[name='" + column.fileName + "']");
        if (!i || i.length == 0) return;
        var d = i.parent().parent().parent().parent().parent();
        d.show();
        d.prev().show();
        d.parent().show();
        column.editDisplay = "show";
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        var name = column.fileName,
            atts = data ? data[name] : null,
            fileInput = model.formBody.find('[name="' + name + '"]');

        var initialPreview = [];
        var initialPreviewConfig = [];
        if (atts) {
            atts.forEach(function(att) {
                initialPreview.push(att.url);
                initialPreviewConfig.push({
                    caption: att.filename,
                    size: att.size,
                    key: att.id
                });
            });
        }

        if (column.inputAttachment) {
            column.inputAttachment.fileinput('destroy');
        }

        column.inputAttachment = $(fileInput).fileinput({
            language: 'zh',
            uploadUrl: '/common/upload/files',
            showUpload: false,
            layoutTemplates: {
                actionUpload: '' //去除上传预览缩略图中的上传图片；
            },
            uploadAsync: false,
            maxFileCount: 4,
            allowedFileExtensions: column.allowedFileExtensions || ["jpeg", "jpg", "png", "gif"],
            overwriteInitial: false,
            ajaxDelete: false, // 扩展定义配置，不进行后台删除操作
            initialPreview: initialPreview,
            initialPreviewAsData: true, // allows you to set a raw markup
            initialPreviewFileType: 'image', // image is the default and can be overridden in config below
            initialPreviewConfig: initialPreviewConfig
        });
    },
    generateViewFormColspan: function(column, options) {
        if (typeof column.generateViewFormColspan === 'function') {
            return column.generateViewFormColspan(column, options);
        }
        return column.colspan || options.maxColspan;
    },
    generateViewFormHtml: function(column, isFirst, options) {
        if (typeof column.generateViewFormHtml === 'function') {
            return column.generateViewFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || options.maxColspan;
        var html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div name="' + column.name + '" class="col-sm-' + ((options.maxColspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '"></div>\n';
        return {
            colspan: colspan,
            html: html
        };
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || options.maxColspan;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || options.maxColspan;
        var html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div name="' + column.name + '" class="col-sm-' + ((options.maxColspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<input type="file" name="' + column.fileName + '" multiple>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 单选构建器
var _radioFieldBuilder = new _FieldBuilder("RADIO", {
    getEditValue: function(column, model) {
        // 获取域EDIT页面值
        if (typeof column.getEditValue === 'function') {
            return column.getEditValue(column, model);
        }

        return model.editBody.find("input[name='" + column.name + "']:checked").val();
    },
    dependTrigger: function(column, model) {
        // 依赖域变化注册，监听依赖域变更
        if (typeof column.dependTrigger === 'function') {
            return column.dependTrigger(column, model);
        }
        // 这里使用icheck 所以调用ifChecked事件
        model.editBody.find("input[name='" + column.name + "']").on('ifChecked', function() {
            if (model.filling === false) {
                model.checkEditDependency();
            }
        });
    },
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var p = model.viewBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var v = data ? data[column.name] : null;
        if (column.enum && v) {
            v = $.getConstantEnumValue(column.enum, v);
        }

        if (v) {
            p.removeClass("text-muted");
            p.text(v);
        } else {
            p.addClass("text-muted");
            p.text("无");
        }
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        var input = model.editBody.find("[name='" + column.name + "']");
        if (!input && input.length == 0) return;

        var ov = data ? data[column.name] : null,
            isP = input.is("p"),
            v = column.enum && ov ? $.getConstantEnumValue(column.enum, ov) : null;

        if (isP) {
            if (v) {
                input.removeClass("text-muted");
                input.text(v);
            } else {
                input.addClass("text-muted");
                input.text("无");
            }
        } else {
            input.each(function() {
                var a = $(this);
                if (a.val() == ov) {
                    a.iCheck('check');
                }
            });
        }
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<div name="' + column.name + '" class="tonto-radio-constant" enumcode="' + column.enum + '"></div>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 多选选构建器
var _checkBoxFieldBuilder = new _FieldBuilder("CHECKBOX", {
    setDataHandler: function(column, data, model) {
        // 插入数据时候调用
        if (typeof column.setDataHandler === 'function') {
            return column.setDataHandler(column, data, model);
        }

        // 解析的附件
        var v = data && data[column.name];
        if (v) {
            data[column.name] = v.split(column.separator || ",");
        }
    },
    getEditValue: function(column, model) {
        // 获取域EDIT页面值
        if (typeof column.getEditValue === 'function') {
            return column.getEditValue(column, model);
        }

        return model.editBody.find("input[name='" + column.name + "']:checked").val();
    },
    dependTrigger: function(column, model) {
        // 依赖域变化注册，监听依赖域变更
        if (typeof column.dependTrigger === 'function') {
            return column.dependTrigger(column, model);
        }
        // 这里使用icheck 所以调用ifChecked事件
        model.editBody.find("input[name='" + column.name + "']").on('ifChecked', function() {
            if (model.filling === false) {
                model.checkEditDependency();
            }
        });
    },
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var p = model.viewBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var v = data ? data[column.name] : null;


        if (v) {
            var t = "";
            v.forEach(function(a) {
                t += column.enum ? $.getConstantEnumValue(column.enum, a) : a;
                t += ",";
            });

            if (t.length > 0) {
                t = t.substring(0, t.length - 1);
            }

            p.removeClass("text-muted");
            p.text(t);
        } else {
            p.addClass("text-muted");
            p.text("无");
        }
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        var input = model.editBody.find("[name='" + column.name + "']");
        if (!input && input.length == 0) return;

        if (input.is("p")) {
            var ov = data ? data[column.name] : null,
                v = column.enum && ov ? $.getConstantEnumValue(column.enum, ov) : null,
                t = "";
            if (v) {
                v.forEach(function(a) {
                    t += column.enum ? $.getConstantEnumValue(column.enum, a) : a;
                    t += ",";
                });
            }

            if (t.length > 0) {
                t = t.substring(0, t.length - 1);
            }

            input.removeClass("text-muted");
            input.text(t);
        } else {
            var v = data ? data[column.name] : null;
            if (v) {
                v.forEach(function(a) {
                    model.editBody.find("input[name='" + column.name + "'][value='" + a + "']").iCheck('check');
                });
            }
        }
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<div name="' + column.name + '" class="tonto-checkbox-constant" enumcode="' + column.enum + '"></div>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 科室单位域构建器
var _unitFieldBuilder = new _FieldBuilder("UNIT", {
    dependTrigger: function(column, model) {
        // 依赖域变化注册，监听依赖域变更
        if (typeof column.dependTrigger === 'function') {
            return column.dependTrigger(column, model);
        }
        column.unitComponment && column.unitComponment.addUnitChangedListener(function() {
            if (model.filling === false) {
                model.checkEditDependency();
            }
        });
    },
    getEditValue: function(column, model) {
        // 获取域EDIT页面值
        if (typeof column.getEditValue === 'function') {
            return column.getEditValue(column, model);
        }

        var unit = column.unitComponment && column.unitComponment.getCurrent();
        return unit ? unit.id : null;
    },
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var p = model.viewBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var v = data ? data[column.viewName] : null;

        if (v) {
            p.removeClass("text-muted");
            p.text(v);
        } else {
            p.addClass("text-muted");
            p.text("无");
        }
    },
    hideEdit: function(column, model) {
        if (column.editDisplay === "hide") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.hideEdit === 'function') {
            column.hideEdit(column, model);
            column.editDisplay = "hide";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent(),
            f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }
        column.editDisplay = "hide";
    },
    showEdit: function(column, model) {
        if (column.editDisplay === "show") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.showEdit === 'function') {
            column.showEdit(column, model);
            column.editDisplay = "show";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent();
        d.show();
        d.prev().show();
        d.parent().show();
        column.editDisplay = "show";
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        if (!column.unitComponment) {
            column.unitComponment = model.editBody.find("[name='" + column.name + "']").data("unitComponment");
        }

        column.unitComponment && column.unitComponment.setCurrent(data ? {
            id: data[column.name],
            name: data[column.viewName]
        } : null);
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<input type="text" class="form-control ' + (column.unitType || 'tonto-select-unit') + '" name="' + column.name + '" placeholder="请选择' + column.title + '"></input>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

// 考核周期域构建器
var _cycleFieldBuilder = new _FieldBuilder("CYCLE", {
    dependTrigger: function(column, model) {
        // 依赖域变化注册，监听依赖域变更
        if (typeof column.dependTrigger === 'function') {
            return column.dependTrigger(column, model);
        }
        column.cycleComponment && column.cycleComponment.addUnitChangedListener(function() {
            if (model.filling === false) {
                model.checkEditDependency();
            }
        });
    },
    getEditValue: function(column, model) {
        // 获取域EDIT页面值
        if (typeof column.getEditValue === 'function') {
            return column.getEditValue(column, model);
        }

        var cycle = column.cycleComponment && column.cycleComponment.getCurrent();
        return cycle ? cycle.id : null;
    },
    fillView: function(column, data, model) {
        // VIEW页面填充值时候调用
        if (typeof column.fillView === 'function') {
            return column.fillView(column, data, model);
        }

        var p = model.viewBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var v = data ? data[column.viewName] : null;

        if (v) {
            p.removeClass("text-muted");
            p.text(v);
        } else {
            p.addClass("text-muted");
            p.text("无");
        }
    },
    hideEdit: function(column, model) {
        if (column.editDisplay === "hide") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.hideEdit === 'function') {
            column.hideEdit(column, model);
            column.editDisplay = "hide";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent(),
            f = d.parent();
        d.hide();
        d.prev().hide();
        if (f.children(":visible").length == 0) {
            f.hide();
        }
        column.editDisplay = "hide";
    },
    showEdit: function(column, model) {
        if (column.editDisplay === "show") {
            return;
        }

        // EDIT页面列隐藏时候调用
        if (typeof column.showEdit === 'function') {
            column.showEdit(column, model);
            column.editDisplay = "show";
            return;
        }

        var p = model.editBody.find("[name='" + column.name + "']");
        if (!p || p.length == 0) return;
        var d = p.parent().parent();
        d.show();
        d.prev().show();
        d.parent().show();
        column.editDisplay = "show";
    },
    fillEdit: function(column, data, model) {
        // EDIT页面填充值时候调用
        if (typeof column.fillEdit === 'function') {
            return column.fillEdit(column, data, model);
        }

        if (!column.cycleComponment) {
            column.cycleComponment = model.editBody.find("[name='" + column.name + "']").data("cycleComponment");
        }

        column.cycleComponment && column.cycleComponment.setCurrent(data ? {
            id: data[column.name],
            cycleName: data[column.viewName]
        } : null);
    },
    generateEditFormColspan: function(column, options) {
        if (typeof column.generateEditFormColspan === 'function') {
            return column.generateEditFormColspan(column, options);
        }
        return column.colspan || 1;
    },
    generateEditFormHtml: function(column, isFirst, options) {
        if (typeof column.generateEditFormHtml === 'function') {
            return column.generateEditFormHtml(column, isFirst, options);
        }
        var colspan = column.colspan || 1,
            html = '<label for="' + column.name + '" class="col-sm-' + (isFirst ? options.firstLabelSize : options.labelSize) + ' control-label">' + column.title + '：</label>\n';
        html += '<div class="col-sm-' + ((colspan - 1) * (options.inputSize + options.labelSize) + options.inputSize) + '">\n';
        html += '<input type="text" class="form-control ' + (column.cycleType || 'tonto-select-assess-cycle') + '" name="' + column.name + '" placeholder="请选择' + column.title + '"></input>\n';
        html += '</div>\n';
        return {
            colspan: colspan,
            html: html
        };
    }
});

if (!window.toton) window.toton = {};
window.tonto.Model = _Model;
window.tonto.FieldBuilder = _FieldBuilder;