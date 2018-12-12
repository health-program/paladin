$(function() {
    $.getAjax("/open/item/data", initReport);
});


function findPrescription() {
    var data = $("#tableForm").serializeObject();
    $.postJsonAjax("/open/diagnose/trial", data, showPrescription);
}


function clearInput() {
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

    var html = "<ol>";
    result.forEach(function(a) {
        html += "<li>" + a.content + "";
        //        if (a.detail) {
        //            html += "<br /><font color='#999'>" + a.detail + "</font>";
        //        }
        html += "</li>";
    });
    html += "</ol>";
    return html;
}


function buildTerminology(terminologies) {
    var html = "<dl>";
    terminologies.forEach(function(t) {
        if (t.type != 2) {
            var name = t.name;
            if (t.nickName) {
                name += "（" + t.nickName + "）";
            }
            html += '<dd style="padding:4px 10px 0;"><span style="color:#999">' + name + '：</span>' + t.content + '</dd>';
        }
    });

    html += "</dl>";

    return html;
}

function buildImage(factors) {

    var data = [{
            style: "bg-red",
            icon: "iconfont icon-xiangcujiangyou",
            data: [
                { name: "盐", value: "<6克" },
                { name: "油", value: "25~30克" }
            ]
        },
        {
            style: "bg-gray-light",
            icon: "iconfont icon-jinkouniunai",
            data: [
                { name: "奶及奶制品", value: "300克" },
                { name: "大豆及坚果类", value: "25~35克" }
            ]
        },
        {
            style: "bg-blue",
            icon: "iconfont icon--yu",
            data: [
                { name: "畜禽肉", value: "40~75克" },
                { name: "水产品", value: "40~75克" },
                { name: "蛋类", value: "40~50克" }
            ]
        },
        {
            style: "bg-green",
            icon: "iconfont icon-shucai",
            data: [{
                    name: "蔬菜类",
                    value: "300~500克",
                    children: [
                        { name: "深色蔬菜", value: ">1/2" }
                    ]
                },
                { name: "水果类", value: "200~350克" }
            ]
        },
        {
            style: "bg-orange",
            icon: "iconfont icon-wugu",
            data: [{
                name: "谷薯类",
                value: "250~400克",
                children: [
                    { name: "全谷类及杂豆类", value: "50~150克" }, { name: "薯类", value: "50~100克" }
                ]
            }]
        },
        {
            style: "bg-aqua",
            icon: "iconfont icon-water_icon",
            data: [
                { name: "水", value: "1500~1700毫升" },
            ]
        }

    ];

    var html = "<div style='text-align:left'>";
    data.forEach(function(a) {

        html += '<div class="info-box"><span class="info-box-icon ' + a.style + '"><i class="' + a.icon + '" style="font-size: inherit"></i></span>';
        html += '<div class="info-box-content"><ul class="list-unstyled">';

        a.data.forEach(function(b) {
            html += '<li><b>' + b.name + ' </b><span class="pull-right badge bg-blue">' + b.value + '</span></li>';
            if (b.children) {
                html += '<ul>';
                b.children.forEach(function(c) {
                    html += '<li><span style="color:#999">' + c.name + '</span><span class="pull-right badge bg-blue">' + c.value + '</span></li>';
                });
                html += "</ul>";
            }
        });

        html += '</ul></div></div>';
    });

    html += "</div>";
    html += "<p class='text-muted text-center' style='width:230px'>正常居民膳食推荐，可结合健康处方推算适合自己的膳食成分</p>";
    return html;
}

function showPrescription(result) {
    if (!result) {
        $.infoMessage("没有有用的健康处方");
        return;
    }

    var fs = result.factors;
    var ps = result.prescriptions;
    var ts = result.terminologies

    var html = '<table class="table table-bordered">';
    if (fs && fs.length > 0) {
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>危险因素</td><td colspan='3'>";
        fs.forEach(function(y) {
            if (y.type == 2) {
                html += '<span class="label label-warning" style="margin-right:10px">' + y.name + '</span>';
            } 
        });
        html += "</td>";
        
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>疾病</td><td colspan='3'>";
        fs.forEach(function(y) {
        	if(y.type == 1) {
                html += '<span class="label label-danger" style="margin-right:10px">' + y.name + '</span>';
            }
        });
        html += "</td>";
        
    } else {
        $.infoMessage("没有有用的健康处方");
        return;
    }

    if (ps && ps.length > 0) {
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle' rowspan='5'>健康处方</td>";
        html += "<td valign='middle' align='center' style='width:120px;vertical-align:middle'>日常生活</td><td>" + buildPrescriptionContent(ps, 1, 8) + "</td>";
        html += "<td valign='middle' align='center' style='width:380px;' rowspan='5'>" + buildImage(fs) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>饮食习惯</td><td>" + buildPrescriptionContent(ps, 4) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>运动锻炼</td><td>" + buildPrescriptionContent(ps, 3) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>心理活动</td><td>" + buildPrescriptionContent(ps, 6) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>其他</td><td>" + buildPrescriptionContent(ps, 10) + "</td></tr>";
        html += "<tr><td valign='middle' align='center' style='width:120px;vertical-align:middle'>注解</td><td valign='middle' style='vertical-align:middle' colspan='3'>" + buildTerminology(ts) + "</td></tr>";
    } else {
        $.infoMessage("没有有用的健康处方");
        return;
    }
    html += "</table>";

    $("#prescriptionDiv").html(html);

    showAppendix(result);
    
    document.body.scrollTop = document.documentElement.scrollTop = 0;
}

function showAppendix(result) {
    if (!result.terminologies || result.terminologies.length == 0) {
        return;
    }

    var html1 = "";
    var html2 = "";

    var i = 1;
    var load = [];
    result.terminologies.forEach(function(t) {
        if (t.type == 2 || t.type == 3) {
            var id = "tab_" + i;
            html1 += '<li class="' + (i == 1 ? 'active' : '') + '"><a href="#' + id + '" data-toggle="tab" aria-expanded="false">' + t.pageName + '</a></li>';
            html2 += '<div class="tab-pane ' + (i == 1 ? 'active' : '') + '" id="' + id + '"></div>';
            i++;
            load.push({
                target: id,
                url: "/static/html/appendix/" + t.pageUrl + ".html"
                //url: "/static/html/appendix/yycf.html"
            });
        }
    });
    var html = '<div class="nav-tabs-custom"><ul class="nav nav-tabs">' + html1 + '</ul><div class="tab-content">' + html2 + '</div></div>';
    $("#appendixDiv").html(html);

    load.forEach(function(d) {
        $("#" + d.target).load(d.url);
    });
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

