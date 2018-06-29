$(function() {
    $.getAjax("/health/prescription/factor/list", initFactor);
    $.getAjax("/health/index/item/detail/list", initReport);
});

//---------------- 危险因素部分 -----------------
var factors;

function initFactor(data) {
    factors = data;
    $("#factorInput").bsSuggest({
        idField: "code",
        keyField: "name",
        effectiveFields: ["name"],
        effectiveFieldsAlias: { name: "危险因素" },
        searchFields: ["name", "code"],
        data: {
            value: data
        },
        clearable: false,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        //showBtn: false,        
        showHeader: true
    }).on('onSetSelectValue', function(e, keyword, data) {
        $("#factor").tagsinput('add', data.name);
    });

    $('#factor').on('beforeItemAdd', function(event) {
        var val = event.item;
        for (var i = 0; i < factors.length; i++) {
            var obj = factors[i];
            if (obj.name == val) {
                return;
            }
        }
        $.errorMessage("不存在危险因素：" + val);
        event.cancel = true;
    });
}

function findPrescription() {
    var args = $("#factor").tagsinput("items");

    if (args && args.length == 0) {
        $.errorMessage("请选择查询条件");
        return;
    }

    var url = "/health/prescription/find/factor?";
    args.forEach(function(i) {
        url += "args=" + i + "&";
    });

    $.getAjax(url, showPrescription);
}


function clearInput() {
    $("#factor").tagsinput('removeAll');
    $("#factorInput").val("");
}


function findPrescription2() {
    var data = $("#tableForm").serializeObject();
    $.postJsonAjax("/health/prescription/find/condition", data, showPrescription);
}


function clearInput2() {
    $("#tableForm")[0].reset();
}

function buildPrescriptionContent(prescriptions) {
    var result = [];
    for (var i = 1; i < arguments.length; i++) {
        var x = arguments[i];
        prescriptions.forEach(function(row) {
            if (x == row.type) {
                result.push(row);
            }
        });
    }

    var html = "<ul>";
    result.forEach(function(a) {
        html += "<li>" + a.content + "";
//        if (a.detail) {
//            html += "<br /><font color='#999'>" + a.detail + "</font>";
//        }
        html += "</li>";
    });
    html += "</ul>";
    return html;
}

function showPrescription(result) {
    if (!result) {
        $.infoMessage("没有有用的健康处方");
        return;
    }

    var fs = result.factors;
    var ps = result.prescriptions;

    var html = '<table class="table table-bordered">';
    if (fs && fs.length > 0) {
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle'>危险因素</td><td colspan='2'>";
        html += "<ul>";
        fs.forEach(function(y) {
            if (y.type != 3) {
                html += "<li>" + y.name + "</li>";
            }
        });
        html += "</ul></td>";
    } else {
        $.infoMessage("没有有用的健康处方");
        return;
    }

    if (ps && ps.length > 0) {
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle' rowspan='5'>健康处方</td><td valign='middle' align='center' style='width:140px;vertical-align:middle'>日常生活</td><td>" + buildPrescriptionContent(ps, 1, 8) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle'>饮食习惯</td><td>" + buildPrescriptionContent(ps, 3, 4, 5) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle'>运动锻炼</td><td>" + buildPrescriptionContent(ps, 7) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle'>心理活动</td><td>" + buildPrescriptionContent(ps, 6) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:140px;vertical-align:middle'>其他</td><td>" + buildPrescriptionContent(ps, 10) + "</td></tr>";
    } else {
        $.infoMessage("没有有用的健康处方");
        return;
    }
    html += "</table>";
    $.openPageLayer(html);
}

//---------------- 健康报告部分 -----------------

var items = [];
var itemList;
var itemValueDefinitionList;
var itemStandardList;

var g = function(nodeItems, depth) {
    var nodes = [];

    if ($.isArray(nodeItems)) {
        nodeItems.forEach(function(item) {

            var text = item.name;

            var node = {
                id: item.id,
                name: item.name,
                type: item.itemType,
                key: item.itemKey,
                depth: depth
            };

            if (node.type == 'STANDARD') {
                var valDefs = $.grep(itemValueDefinitionList, function(row, index) {
                    return node.id == row.itemId;
                });

                if (valDefs && valDefs.length > 0) {
                    node.valueDefinition = valDefs[0];

                    if (node.valueDefinition.inputType == 'SELECT') {
                        var standards = $.grep(itemStandardList, function(row, index) {
                            return node.valueDefinition.id == row.valueDefinitionId;
                        });

                        if (standards) {
                            node.standards = standards;
                        }
                    }
                }
            }

            var children = $.grep(itemList, function(row, index) {
                return item.id == row.parentId;
            });

            if (children && children.length > 0) {
                node.nodes = g(children, depth + 1);
            }

            nodes.push(node);
        })
    }
    return nodes;
}

function initReport(result) {

    itemList = result.item;
    itemValueDefinitionList = result.itemValueDefinition;
    itemStandardList = result.itemStandard;

    var roots = $.grep(itemList, function(row, index) {
        return row.parentId == null;
    });

    items = g(roots);
    buildTable();
}

function buildTable() {

    var trs = [];
    var maxColspan = getMaxColspan();
    for (var i = 0; i < items.length; i++) {
        getRow(items[i], null, trs);
    }

    var html = "<tbody>";
    var maxDepth = 1;
    var maxDepthIndex = 0;
    var j = 0
    for (; j < trs.length; j++) {
        if (trs[j].length > maxDepth) {
            maxDepth = trs[j].length;
            maxDepthIndex = j;
        }
    }
    for (j = 0; j <
        trs.length; j++) {
        var tr = trs[j];
        html += "<tr>";
        var x = 0
        for (; x < tr.length; x++) {
            var td = tr[x];
            var data = td.data;
            var rowspan = td.rowspan;

            var colspan1 = "";
            var colspan2 = "";

            if (!data.nodes || data.nodes.length == 0) {
                if (data.type == 'STANDARD') {
                    colspan2 = 'colspan="' + (maxColspan - data.p - 1) + '"';
                } else {
                    colspan1 = 'colspan="' + (maxColspan - data.p) + '"';
                }
            }

            html += "<td valign='middle' align='center' style='width:140px;vertical-align:middle' rowspan='" + rowspan + "' " + colspan1 + ">" + data.name + "</td>";
            if (data.type == 'STANDARD') {
                html += "<td valign='middle' style='width:400px;vertical-align:middle' rowspan='" + rowspan + "' " + colspan2 + ">" + buildContent(data) + "</td>";
            }
        }
    }
    html += "</tbody>";

    $("#datagrid").html(html);
}

function getMaxColspan(item, p) {
    p = p ? p : 0;
    if (item) {
        var d = item.type == 'STANDARD' ? 2 : 1;
        var max = 0;
        if (item.nodes) {
            for (var i = 0; i < item.nodes.length; i++) {
                var r = getMaxColspan(item.nodes[i], p + d);
                if (r > max) {
                    max = r;
                }
            }
        }
        item.p = p;
        return max + d;
    } else {
        var max = 0;
        for (var i = 0; i < items.length; i++) {
            var r = getMaxColspan(items[i]);
            if (r > max) {
                max = r;
            }
        }
        return max;
    }
}

function getRowspan(node) {
    if (node.nodes) {
        var s = 0;
        for (var i = 0; i < node.nodes.length; i++) {
            var subnode = node.nodes[i];
            s += getRowspan(subnode);
        }
        return s;
    }
    return 1;
}

function getRow(node, tr, trs) {
    if (!tr) {
        tr = [];
        trs.push(tr);
    }
    var rowspan = getRowspan(node);
    tr.push({
        data: node,
        rowspan: rowspan,
        depth: node.depth
    });
    var nodes = node.nodes;
    if (nodes && nodes.length > 0) {
        var first = nodes[0];
        getRow(first, tr, trs);
        for (var i = 1; i < nodes.length; i++) {
            getRow(nodes[i], null, trs);
        }
    }
}

function buildContent(item, value) {
    var vd = item.valueDefinition;
    var html;
    if (vd.inputType == 'INPUT') {
        var input = '<input type="text" class="index-item-text" name="' + item.key + '" value="' + (value ? value : '') + '" />'
        html = vd.template ? vd.template.replace("{?}", input) : input;
        if (vd.unit) {
            html += vd.unit;
        }
    } else if (vd.inputType == 'SELECT') {
        html = '<div class="checkbox">';
        if (item.standards) {
            item.standards.forEach(function(s) {
                if (vd.isSingle) {
                    html += '<label class="radio-inline"> <input type="radio" class="index-item-radio" name=' + item.key + ' value=' + s.standardKey + '>' + s.name + '</label>';
                } else {
                    html += '<label class="checkbox-inline"> <input type="checkbox" class="index-item-checkbox" name=' + item.key + ' value=' + s.standardKey + '>' + s.name + '</label>';
                }
            });
        }
        html += "</div>";
    }

    return html;
}