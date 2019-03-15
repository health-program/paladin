$(function() {
    if ($("#symptomInput").length > 0) {
    	 
        $.getAjax("/xk/dict", initCatalog);
    }
    var symptomKey = $("#symptomKey").val();
    if (symptomKey) {
        var symptomName = $("#symptomName").val();
        currentSymptom = symptomKey;
        $("#symptomInput").val(symptomName);
    }
});

var catalog;
var knowledgeTree = $("#tree");
var currentZSID;
var selectedKnowledgeId;

function initCatalog(data) {
	 
    catalog = data;
	
    var dataList = {
        value: catalog
    };

    $("#symptomInput").bsSuggest({
        idField: "key",
        keyField: "value",
        effectiveFields: ["value"],
        effectiveFieldsAlias: { value: "症状名称" },
        searchFields: ["value", "key"],
        data: dataList,
        clearable: false,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        //showBtn: false,        
        showHeader: true
    }).on('onSetSelectValue', function(e, keyword, data) {
        currentSymptom = data.key;
        $("#symptomInput").val(data.value);
    });
}

var currentSymptom;

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

    var roots = $.grep(data, function(row, index) {
        if (row.categoryKey == 'zs') {
            currentZSID = row.id;
            return false;
        } else {
            return row.parentId == null;
        }
    });

    var treedata = g(roots, data);
    if(knowledgeTree.length > 0){
	    knowledgeTree.treeview({ data: treedata, levels: 1 });
	    knowledgeTree.on('nodeSelected', function(event, node) {
	        getKnowledge(node.data, true);
	    });
    }

    initCategoryDetailContent(treedata);
}

function getKnowledge(knowledge) {

    selectedKnowledgeId = knowledge.id;

    // 指向内容 TODO
}

var chineseNumber = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九"];
var littleNumber = ["①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯", "⑰", "⑱", "⑲"];

function getArticleTitle(level, index, text) {

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

    return '<p style="text-indent:2em;font-size:' + fontsize + 'px;font-weight:bold">' + number + text + "</p>";
}

var gg = function(knowledge, data, index) {

    var html = knowledge.data.parentId ? getArticleTitle(knowledge.level, index, knowledge.text) : "";

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
    if (!currentSymptom) {
        return;
    }

    $.postAjax("/health/symptom/content/symptom", { symptomKey: currentSymptom }, function(data) {
        treedata.forEach(function(n) {
            var id = n.data.categoryKey;
            var html = gg(n, data, 1);
            $("#" + id).html(html);
        });

        data.forEach(function(d) {
            if (d.knowledgeId == currentZSID) {
                $("#symptomSummary").html('<p style="text-indent:2em;">' + d.content + "</p>");
            }
        });

    });
}

function search_menu(){
	if (!currentSymptom) {
        return;
    }
	
	 $.postAjax("/xk/symptom", { code: currentSymptom }, function(data) {
		 console.log(data.dn);
	        $("#dn").html('<p style="text-indent:2em;">' + data.dn + "</p>");
	        $("#ad").html('<p style="text-indent:2em;">' + data.ad + "</p>");
	        $("#dm").html('<p style="text-indent:2em;">' + data.dm + "</p>");
	        $("#dc").html('<p style="text-indent:2em;">' + data.dc + "</p>");
	        $("#dd_c").html('<p style="text-indent:2em;">' + data.dd_c + "</p>");
	        $("#dd_s").html('<p style="text-indent:2em;">' + data.dd_s + "</p>");
	        $("#dd_r").html('<p style="text-indent:2em;">' + data.dd_r + "</p>");
	        $("#dd_l").html('<p style="text-indent:2em;">' + data.dd_l + "</p>");
	        $("#dd_d").html('<p style="text-indent:2em;">' + data.dd_d + "</p>");
	        $("#dd_sa").html('<p style="text-indent:2em;">' + data.dd_sa + "</p>");
	        $("#dd_m").html('<p style="text-indent:2em;">' + data.dd_m + "</p>");
	        $("#dd_g").html('<p style="text-indent:2em;">' + data.dd_g + "</p>");
	        $("#dd_n").html('<p style="text-indent:2em;">' + data.dd_n + "</p>");
	        });
}