$(function() {
    $.getAjax("/health/knowledge/list/disease", initCatalog);
    $("#searchBtn").click(getDiseaseKnowledge);
});

var catalog;
var knowledgeTree = $("#tree");
var knowledgeContentMap;
var selectedKnowledgeId;

function initCatalog(data) {
    catalog = data;
    var dataList = {
        value : []
    };
    if($.isArray(catalog)) {
        catalog.forEach(function(disease) {
            dataList.value.push({

                疾病Key : disease.nameKey,
                疾病名称 : disease.name
            });

        })
    }
    $("#diseaseInput").bsSuggest({
        keyField: '疾病Key',
        data: dataList,
        clearable: true,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        showBtn: false,
        showHeader: true
    }).on('onDataRequestSuccess', function (e, result) {
        console.log('从 json.data 参数中获取，不会触发 onDataRequestSuccess 事件', result);
    }).on('onSetSelectValue', function (e, keyword, data) {
        $("section  h1").empty().text("疾病数据管理");
        var  test = $("section  h1").text() + "——" + data.疾病名称;
        $("section  h1").text(test);
        getDiseaseKnowledge();
    }).on('onUnsetSelectValue', function () {
        console.log("onUnsetSelectValue");
    });
}

function getDiseaseKnowledge() {
    var disease = $("#diseaseInput").val();
    $.postAjax("/health/knowledge/list/knowledge", { diseaseKey: disease }, initKnowledgeTree);
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
    knowledgeTree.treeview({ data: treedata });
    knowledgeTree.on('nodeSelected', function(event, node) {
        getKnowledge(node.data, true);
    });
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

    $.postAjax("/health/knowledge/knowledge/content", { knowledgeId: selectedKnowledgeId }, initKnowledgeContent);
}

function initKnowledgeContent(content) {
    knowledgeContentMap[selectedKnowledgeId] = content;
    var html = "";
    content.forEach(function(a){
        html += '<p style="text-indent:2em;">' + a.content + "</p>";
    });
    $("#contents").html(html);
}