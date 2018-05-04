$(function() {
    $.getAjax("/health/disease/list", initCatalog);
    initTable();
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
        idField: "diseaseKey",
        keyField: "diseaseName",
        effectiveFields: ["name", "nameKey"],
        effectiveFieldsAlias: { name: "疾病名称", nameKey: "拼音关键字" },
        data: dataList,
        clearable: false,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        showBtn: false,
        showHeader: true
    }).on('onDataRequestSuccess', function(e, result) {

    }).on('onSetSelectValue', function(e, keyword, data) {
        currentDisease = data.nameKey;
        getDiseaseKnowledge();
        setDiseaseSummary(data);
    }).on('onUnsetSelectValue', function(a, b, c) {
        console.log("onUnsetSelectValue", a);
    });
}

var table;

function initTable() {
    table = $.createTable("#dataGrid", {
        idField: "nameKey",
        columns: [
            [
                { radio: true },
                { title: "疾病关键字", field: "nameKey" },
                { title: "疾病名称", field: "name" },
            ]
        ],
        url: "/health/disease/pagelist",
        clickToSelect: true,
        pagination: true,
        pageSize: 20,
        onClickRow: function(row) {
            currentDisease = row.nameKey;
            getDiseaseKnowledge();
            setDiseaseSummary(row);
        }
    });
}

var currentDisease;

function setDiseaseSummary(data) {
    var html = '<dl><dd><i>疾病名称：</i>' + data.name + '</dd><dd><i>疾病关键字：</i>' + data.nameKey + '</dd></dl>';
    $("#diseaseSummary").html(html);
}

function getDiseaseKnowledge() {
    if (currentDisease) {
        $.postAjax("/health/knowledge/list/disease", { diseaseKey: currentDisease }, initKnowledgeTree);
    }
}

var g = function(nodeItems, data) {
    var nodes = [];
    nodeItems.forEach(function(item) {

        var text = item.name;
        var node = {
            text: text,
            data: item
        };

        var children = $.grep(data, function(row, index) {
            return item.id == row.parentId;
        });

        if (children && children.length > 0) {
            node.nodes = g(children, data);
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
    knowledgeTree.treeview({ data: treedata, levels: 1 });
    knowledgeTree.on('nodeSelected', function(event, node) {
        getKnowledge(node.data, true);
    });

    initCategoryDetailContent(treedata);
}

function getKnowledge(knowledge) {

    selectedKnowledgeId = knowledge.id;

    if (knowledgeContentMap) {
        var content = knowledgeContentMap[selectedKnowledgeId];
        if (content) {
            initKnowledgeContent(content);
            return;
        }
    }

    $.postAjax("/health/knowledge/content", { knowledgeId: selectedKnowledgeId }, initKnowledgeContent);
}

function initKnowledgeContent(content) {
    knowledgeContentMap[selectedKnowledgeId] = content;
    var html = "";
    content.forEach(function(a) {
        html += '<p style="text-indent:2em;">' + a.content + "</p>";
    });
    $("#contents").html(html);
}


var gg = function(knowledge, data) {

    var html = knowledge.data.parentId ? '<p style="text-indent:2em;"><b>' + knowledge.text + "</b></p>" : "";

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
            html += gg(knowledge.nodes[i], data);
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
            var html = gg(n, data)
            $("#" + id).html(html);
        });
    });
}