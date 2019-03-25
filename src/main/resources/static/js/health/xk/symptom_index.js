$(function() {
    if ($("#symptomInput").length > 0) {
    	 
        $.getAjax("/xk/symptom/code", initCatalog);
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
	data["xk-disease-type"].forEach(function(disease){
		disease.type="疾病";
		
	})
	data["xk-index-type"].forEach(function(index){
		index.type="指标";
		
	})
    catalog = data["xk-disease-type"].concat(data["xk-index-type"]);
	
    var dataList = {
        value: catalog
    };

    $("#symptomInput").bsSuggest({
        idField: "key",
        keyField: "value",
        effectiveFields: ["value","type"],
        effectiveFieldsAlias: { value: "名称",type:"类型" },
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
	        $("#content").html(showEvaluateResult(data));
	        });
	        
}

function showEvaluateResult(result) {
	console.log(result);
	var data=result.knowledge;
	var type=result.type;
	if (!data) {
        return '';
    }
    var html = "";
   
    if(type=='index'){
    	  html += '<dl class="dl-horizontal" style="padding-bottom:20px;border-bottom: 1px solid #eee">'
    		    +'<dt>指标名称：</dt><dd id="dn">'+data[0].dn+'</dd>'
    		    +'<dt>所在科室：</dt><dd id="ad">'+data[0].ad +'</dd>'
    		    +'<dt>检查目的：</dt><dd id="dm">'+data[0].dm+'</dd>'
    		    +'<dt>判定标准：</dt><dd id="dc">'+data[0].dc+'</dd>'
    		    +'</dl>'
    		    +'<ul class="nav nav-tabs">'
    		    +'<li class=""><a href="#tab0" data-toggle="tab" aria-expanded="true">'+data[0].cr+'</a></li>'
    		    +'<li class=""><a href="#tab1" data-toggle="tab" aria-expanded="false">'+data[1].cr+'</a></li>'
                +'</ul>'
    }else{
    html += '<dl class="dl-horizontal" style="padding-bottom:20px;border-bottom: 1px solid #eee">'
    +'<dt>疾病名称：</dt><dd id="dn">'+data[0].dn+'</dd>'
    +'<dt>所在科室：</dt><dd id="ad">'+data[0].ad +'</dd>'
    +'<dt>疾病概述：</dt><dd id="dm">'+data[0].dm+'</dd>'
    +'<dt>疾病分类：</dt><dd id="dc">'+data[0].dc+'</dd>'
    +'</dl>';
   /* +'<dt class="dl-horizontal">疾病详情</dt>';*/
    }
    var htmlNode=''; 
    for(var i in data){
    	htmlNode += '<div class="tab-pane '+(i==0?'active':'')+'" id="tab'+i+'">';
    	htmlNode += getEvaluateItemHtml((type=='index'?'可能原因':'病因'), data[i].dd_c);
    	htmlNode += getEvaluateItemHtml('症状', data[i].dd_s);
    	htmlNode += getEvaluateItemHtml((type=='index'?'可能存在的风险或疾病':'风险或并发症'), data[i].dd_r);
    	htmlNode += getEvaluateItemHtml('生活方式', data[i].dd_l);
    	htmlNode += getEvaluateItemHtml('饮食建议', data[i].dd_d);
    	htmlNode += getEvaluateItemHtml('饮食宜吃', data[i].dd_d_s);
    	htmlNode += getEvaluateItemHtml('饮食忌吃', data[i].dd_d_a);
    	htmlNode += getEvaluateItemHtml('运动建议', data[i].dd_sa);
    	htmlNode += getEvaluateItemHtml('运动宜做', data[i].dd_sa_s);
    	htmlNode += getEvaluateItemHtml('运动忌做', data[i].dd_sa_a);
    	htmlNode += getEvaluateItemHtml('医疗保健', data[i].dd_m);
    	htmlNode += getEvaluateItemHtml('就医复查指南', data[i].dd_g);
    	htmlNode += getEvaluateItemHtml('生活常识', data[i].dd_n);
    	htmlNode+='</div>';
    	
    }
    return html+'<div class="tab-content">'+htmlNode+'</div>';
}




function getEvaluateItemHtml(name, data) {
	if(!data){
		return '';
	}

    return '<dl class="dl-horizontal" style="padding-bottom:20px;border-bottom: 1px solid #eee">' +
        '    <dt>' + name + '</dt>' +
        parseContent(data) +
        '</dl>';
}

function parseContent(content) {
	 var arr = content.split("@#");
	 var s = "";
	 arr.forEach(function(a) {
		 var child = a.split("#@");
		 if(child.length>1){
         s += "<dd style='color:orange'>" + child[0] + "</dd>"+"<dd>" + child[1] + "</dd>";
		 }else{
		 s += "<dd>" + child[0] + "</dd>"
		 }
     });

     return s;
}
