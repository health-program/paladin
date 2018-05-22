$(function() {
    if ($("#diseaseInput").length > 0) {
        $.getAjax("/health/disease/list", initCatalog);
    }

    var diseaseKey = $("#diseaseKey").val();
    if (diseaseKey) {
        var diseaseName = $("#diseaseName").val();
        currentDisease = diseaseKey;
        getDiseaseKnowledge();
        getDiseaseSummary();
        $("#diseaseInput").val(diseaseName);
    }
});

var catalog;
var knowledgeTree = $("#tree");
var knowledgeContentMap;
var selectedKnowledgeId;

function initCatalog(data) {
    catalog = data;
    var dataList = {
        value: catalog
    };

    $("#diseaseInput").bsSuggest({
        idField: "name_key",
        keyField: "name",
        effectiveFields: ["name"],
        effectiveFieldsAlias: { name: "疾病名称" },
        searchFields: ["name", "nameKey"],
        data: dataList,
        clearable: false,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        //showBtn: false,
        showHeader: true
    }).on('onSetSelectValue', function(e, keyword, data) {
        currentDisease = data.nameKey;
        getDiseaseKnowledge();
        getDiseaseSummary();
        $("#diseaseInput").val(data.name);
    });
}

var currentDisease;
var summaryMap = {
    "sfsyyb": "是否属于医保",
    "bm": "别名",
    "fbbw": "发病部位",
    "crx": "传染性",
    "sfyc": "是否遗传",
    "ycfs": "遗传方式",
    "cbtj": "传播途径",
    "crbzl": "传染病种类",
    "qfq": "潜伏期",
    "qfqbx": "潜伏期表现",
    "dfrq": "多发人群",
    "xgzz": "相关症状",
    "bfjb": "并发疾病",
    "jzks": "就诊科室",
    "zlfy": "治疗费用",
    "zyl": "治愈率",
    "zlzq": "治疗周期",
    "zlff": "治疗方法",
    "xgjc": "相关检查",
    "xgss": "相关手术",
    "cyyp": "常用药品",
    "zjjzsj": "最佳就诊时间",
    "jzsz": "就诊时长",
    "fzplzlzq": "复诊频率/诊疗周期",
    "jzqzb": "就诊前准备"
}

function getDiseaseSummary() {
    $.postAjax("/health/disease/summary", { disease: currentDisease }, function(summary) {
        $("#diseaseSummary").html('<p style="text-indent:2em;">' + summary.summary + "</p>");
        var html = '<dl>';
        for (var o in summaryMap) {
            var content = summary[o];
            if (content !== null) {
                if (o == 'xgzz' || o == 'bfjb') {
                    var arr = content.split(",");
                    content = "";
                    arr.forEach(function(a) {
                        content += "<a class='" + o + "' href='javascript:void(0)'>" + a + "</a>,";
                    });
                    content = content.substring(0, content.length - 1);
                }

                html += '<dd style="padding:4px 10px 0;"><span style="color:#999">' + summaryMap[o] + '：</span>' + content + '</dd>';
            }
        }
        html += "</dl>";
        $("#diseaseBaseKnow").html(html);

        $(".xgzz").each(function() {
            var name = $(this).html();
            $(this).click(function() {
                showSymptom(name);
            });
        });

        $(".bfjb").each(function() {
            var name = $(this).html();
            $(this).click(function() {
                showDisease(name);
            });
        });

    });
}

function showDisease(disease) {

    if (isLayer()) {
        window.location = '/health/knowledge/index?diseaseName=' + disease;
    }

    var w = $(window).width() * 0.8 + "px";
    var h = $(window).height() * 0.9 + "px";

    layer.open({
        type: 2,
        title: disease,
        maxmin: true, //开启最大化最小化按钮
        area: [w, h],
        content: '/health/knowledge/index?diseaseName=' + disease
    });
}

function showSymptom(symptom) {

    if (isLayer()) {
        window.location = '/health/symptom/index?symptomName=' + symptom;
    }

    var w = $(window).width() * 0.8 + "px";
    var h = $(window).height() * 0.9 + "px";

    layer.open({
        type: 2,
        title: symptom,
        maxmin: true, //开启最大化最小化按钮
        area: [w, h],
        content: '/health/symptom/index?symptomName=' + symptom
    });
}

function isLayer() {
    if (parent && parent.layer && parent.layer.getFrameIndex(window.name)) {
        return true;
    } else {
        return false;
    }
}

function getDiseaseDiet() {
    $.postAjax("/health/diet/dietInfo", { diseaseKey: currentDisease }, function(dietList) {
        var html = "";
        if (dietList && dietList.length > 0) {
            dietList.forEach(function(diet) {
                if (diet.type == 3 || diet.type == 4) {
                    html += '<thead><tr style="background-color:#94c17c"><th colspan="3">' + diet.summary + '</th></tr>'
                    html = diet.type == 3 ? html += '<tr class="text-center success"><td width="20%">' + "宜吃食物" + '</td>' + '<td width="45%">">' + "宜吃理由" + '</td width="35%">' + '<td>' + "食用建议" + '</td></tr></thead>' :
                        html += '<tr style="text-align:center" class="success"><td width="20%">' + "忌吃食物" + '</td>' + '<td width="45%">' + "忌吃理由" + '</td>' + '<td width="35%">' + "忌吃建议" + '</td></tr></thead>'
                    return;
                }
                html += '<tbody><tr ><td class="text-center" style="vertical-align: middle">' + diet.food + '</td>' + '<td class="text-center" style="vertical-align: middle">' + diet.reason + '</td>' + '<td class="text-center">' + diet.suggestion + '</td></tr></tbody>'
            });
        }

        $("#diet").html(html);
    });
}

function getDiseaseKnowledge() {
    if (currentDisease) {
        $.postAjax("/health/knowledge/list/disease", { diseaseKey: currentDisease }, initKnowledgeTree);
    }
}

var g = function(nodeItems, data, level) {
    level = level || 1;
    var nodes = [];
    nodeItems.forEach(function(item) {

        var text = item.name;
        var node = {
            text: text,
            data: item,
            level: level
        };

        var children = $.grep(data, function(row, index) {
            return item.id == row.parentId;
        });

        if (children && children.length > 0) {
            node.nodes = g(children, data, level + 1);
        }

        nodes.push(node);
    })
    return nodes;
}

function initKnowledgeTree(data) {

    if (!data) {
        return;
    }

    knowledgeContentMap = {};
    var roots = $.grep(data, function(row, index) {
        return row.parentId == null;
    });

    var treedata = g(roots, data);
    if (knowledgeTree.length > 0) {
        knowledgeTree.treeview({ data: treedata, levels: 1 });
        knowledgeTree.on('nodeSelected', function(event, node) {
            getKnowledge(node.data, true);
        });
    }
    initCategoryDetailContent(treedata);
}

function getKnowledge(knowledge) {
    // 指向内容 TODO
    selectedKnowledgeId = knowledge.id;
    var categoryKey = knowledge.categoryKey;

    $("." + categoryKey).click();

    if (knowledge.parentId != null) {
        var container = $('#' + categoryKey),
            scrollTo = $('#' +
                selectedKnowledgeId);
        container.scrollTop(scrollTo.offset().top - container.offset().top +
            container.scrollTop());
    }
}


var chineseNumber = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九"];
var littleNumber = ["①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯", "⑰", "⑱", "⑲"];

function getArticleTitle(level, index, text, id) {
    var fontsize = 14;
    var number = "";

    level = level - 1;

    if (level == 1) {
        fontsize = 18;
        number = chineseNumber[index - 1] + "、";
    } else if (level == 2) {
        fontsize = 17;
        number = "（" + chineseNumber[index - 1] + "）";
    } else if (level == 3) {
        fontsize = 16;
        number = index + ".";
    } else if (level == 4) {
        fontsize = 15;
        number = "（" + index + "）";
    } else if (level == 4) {
        fontsize = 15;
        number = littleNumber[index - 1];
    }

    return '<p id=' + id + ' style="text-indent:2em;font-size:' + fontsize + 'px;font-weight:bold">' + number + text + "</p>";
}

var gg = function(knowledge, data, index) {
    var html = knowledge.data.parentId ? getArticleTitle(knowledge.level, index, knowledge.text, knowledge.data.id) : "";

    var contents = $.grep(data, function(row, index) {
        return knowledge.data.id == row.knowledgeId;
    });

    if (contents) {
        for (var i = 0; i < contents.length; i++) {
            html += '<p style="text-indent:2em;">' + contents[i].content + "</p>";
        }
    }

    if (knowledge.nodes) {
        for (var i = 0; i < knowledge.nodes.length; i++) {
            html += gg(knowledge.nodes[i], data, i + 1);
        }
    }

    return html;
}

function initCategoryDetailContent(treedata) {
    if (!currentDisease) {
        return;
    }

    $.postAjax("/health/knowledge/content/disease", { diseaseKey: currentDisease }, function(data) {
        treedata.forEach(function(n) {
            var id = n.data.categoryKey;
            var html = gg(n, data, 1);
            if (id == 'ysbj') {
                html = '<table id="diet" class="table"></table>' + html;
                getDiseaseDiet();
            }

            $("#" + id).html(html);
        });
    });
}