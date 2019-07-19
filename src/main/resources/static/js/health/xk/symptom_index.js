$(function() {
    if ($("#symptomInput").length > 0) {

        $.get("/xk/symptom/code", function(data) {
            initCatalog(data.result);
        });
    }

});

var catalog;
var knowledgeTree = $("#tree");
var currentZSID;
var selectedKnowledgeId;

function initCatalog(data) {
    data["xk-disease-type"].forEach(function(disease) {
        disease.type = "疾病";

    })
    data["xk-index-type"].forEach(function(index) {
        index.type = "指标";

    })
    catalog = data["xk-disease-type"].concat(data["xk-index-type"]);

    var dataList = {
        value: catalog
    };

    $("#symptomInput").bsSuggest({
        idField: "key",
        keyField: "value",
        effectiveFields: ["value", "type"],
        effectiveFieldsAlias: { value: "名称", type: "类型" },
        searchFields: ["value", "key"],
        data: dataList,
        clearable: false,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        showBtn: true,
        showHeader: true
    }).on('onSetSelectValue', function(e, keyword, data) {
        currentSymptom = data.key;
        $("#symptomInput").val(data.value);
        search_menu();
    });
}

var currentSymptom;


function search_menu() {
    if (!currentSymptom) {
        return;
    }

    $.post("/xk/symptom", { code: currentSymptom }, function(data) {
        showKnowledge(data.result)
    });

}

function showKnowledge(result) {
    let base = result.base;
    let detail = result.detail;
    let type = base.type;
    var html = '<div class="box box-solid"><div class="box-body">';
    if (type === 1) {
        html += '<dl class="dl-horizontal" style="padding:20px;">' +
            '<dt>指标名称：</dt><dd id="dn">' + base.name + '</dd>' +
            '<dt>所在科室：</dt><dd id="ad">' + base.department + '</dd>' +
            '<dt>检查目的：</dt><dd id="dm">' + base.inspectionPurpose + '</dd>' +
            '<dt>判定标准：</dt><dd id="dc">' + base.judgementStandard + '</dd>' +
            '</dl>';

    } else {
        html += '<dl class="dl-horizontal" style="padding:20px;">' +
            '<dt>疾病名称：</dt><dd id="dn">' + base.name + '</dd>' +
            '<dt>所在科室：</dt><dd id="ad">' + base.department + '</dd>' +
            '<dt>疾病概述：</dt><dd id="dm">' + base.diseaseOverview + '</dd>' +
            '<dt>疾病分类：</dt><dd id="dc">' + base.diseaseClassification + '</dd>' +
            '</dl>';
    }

    html += "</div></div>";

    $("#summary").html(html);

    html = '<ul class="nav nav-tabs">';
    var htmlNode = '<div class="tab-content">';

    for (var i = 0; i < detail.length; i++) {
        html += '<li ' + (i === 0 ? 'class="active"' : '') + '><a href="#tab' + i + '" data-toggle="tab" aria-expanded="' + (i === 0 ? 'true' : 'false') + '">' + detail[i].title + '</a></li>';

        htmlNode += '<div class="tab-pane ' + (i === 0 ? 'active' : '') + '" id="tab' + i + '">';
        htmlNode += getEvaluateItemHtml((type === 1 ? '可能原因：' : '病因：'), detail[i].cause);
        htmlNode += getEvaluateItemHtml('症状：', detail[i].symptom);
        htmlNode += getEvaluateItemHtml((type === 1 ? '可能存在的风险或疾病：' : '风险或并发症：'), detail[i].risk);
        htmlNode += getEvaluateItemHtml('生活方式：', detail[i].lifestyle);
        htmlNode += getEvaluateItemHtml('饮食建议：', detail[i].dietaryAdvice);
        htmlNode += getEvaluateItemHtml('饮食宜吃：', detail[i].dietaryShouldEat);
        htmlNode += getEvaluateItemHtml('饮食忌吃：', detail[i].dietaryAvoidEat);
        htmlNode += getEvaluateItemHtml('运动建议：', detail[i].sportsAdvice);
        htmlNode += getEvaluateItemHtml('运动宜做：', detail[i].sportsShouldDo);
        htmlNode += getEvaluateItemHtml('运动忌做：', detail[i].sportsAvoidDo);
        htmlNode += getEvaluateItemHtml('医疗保健：', detail[i].medicalInsurance);
        htmlNode += getEvaluateItemHtml('就医复查指南：', detail[i].medicalReviewGuide);
        htmlNode += getEvaluateItemHtml('生活常识：', detail[i].lifeCommonSense);
        htmlNode += '</div>';
    }

    htmlNode += "</div>";
    html += "</ul>";

    $("#detail").html(html + htmlNode);

}

function getEvaluateItemHtml(name, data) {
    if (!data) {
        return '';
    }

    return '<dl class="dl-horizontal" style="padding:20px;">' +
        '    <dt>' + name + '</dt>' +
        parseContent(data) +
        '</dl>';
}

function parseContent(content) {
    var s = "";
    if (content) {
        var arr = JSON.parse(content);
        if (arr && arr.length > 0) {
            arr.forEach(function(a) {
                if (a.value) {
                    s += "<dd style='color:orange'>" + a.key + "</dd>" + "<dd>" + a.value + "</dd>";
                } else {
                    s += "<dd>" + a.key + "</dd>"
                }
            });
        }
    }
    return s;
}


/*function showEvaluateResult(result) {
    var data = result.knowledge;
    var type = result.type;
    if (!data) {
        return '';
    }
    var html = '<div class="box box-solid"><div class="box-body">';

    if (type == 'index') {
        html += '<dl class="dl-horizontal" style="padding:20px;">' +
            '<dt>指标名称：</dt><dd id="dn">' + data[0].dn + '</dd>' +
            '<dt>所在科室：</dt><dd id="ad">' + data[0].ad + '</dd>' +
            '<dt>检查目的：</dt><dd id="dm">' + data[0].dm + '</dd>' +
            '<dt>判定标准：</dt><dd id="dc">' + data[0].dc + '</dd>' +
            '</dl>';

    } else {
        html += '<dl class="dl-horizontal" style="padding:20px;">' +
            '<dt>疾病名称：</dt><dd id="dn">' + data[0].dn + '</dd>' +
            '<dt>所在科室：</dt><dd id="ad">' + data[0].ad + '</dd>' +
            '<dt>疾病概述：</dt><dd id="dm">' + data[0].dm + '</dd>' +
            '<dt>疾病分类：</dt><dd id="dc">' + data[0].dc + '</dd>' +
            '</dl>';
        /!* +'<dt class="dl-horizontal">疾病详情</dt>';*!/
    }

    html += "</div></div>";

    $("#summary").html(html);


    html = '<ul class="nav nav-tabs">';
    var htmlNode = '<div class="tab-content">';

    for (var i = 0; i < data.length; i++) {

        html += '<li ' + (i == 0 ? 'class="active"' : '') + '><a href="#tab' + i + '" data-toggle="tab" aria-expanded="' + (i == 0 ? 'true' : 'false') + '">' + data[i].cr + '</a></li>';

        htmlNode += '<div class="tab-pane ' + (i == 0 ? 'active' : '') + '" id="tab' + i + '">';
        htmlNode += getEvaluateItemHtml((type == 'index' ? '可能原因：' : '病因：'), data[i].dd_c);
        htmlNode += getEvaluateItemHtml('症状：', data[i].dd_s);
        htmlNode += getEvaluateItemHtml((type == 'index' ? '可能存在的风险或疾病：' : '风险或并发症：'), data[i].dd_r);
        htmlNode += getEvaluateItemHtml('生活方式：', data[i].dd_l);
        htmlNode += getEvaluateItemHtml('饮食建议：', data[i].dd_d);
        htmlNode += getEvaluateItemHtml('饮食宜吃：', data[i].dd_d_s);
        htmlNode += getEvaluateItemHtml('饮食忌吃：', data[i].dd_d_a);
        htmlNode += getEvaluateItemHtml('运动建议：', data[i].dd_sa);
        htmlNode += getEvaluateItemHtml('运动宜做：', data[i].dd_sa_s);
        htmlNode += getEvaluateItemHtml('运动忌做：', data[i].dd_sa_a);
        htmlNode += getEvaluateItemHtml('医疗保健：', data[i].dd_m);
        htmlNode += getEvaluateItemHtml('就医复查指南：', data[i].dd_g);
        htmlNode += getEvaluateItemHtml('生活常识：', data[i].dd_n);
        htmlNode += '</div>';

    }

    htmlNode += "</div>";
    html += "</ul>";

    $("#detail").html(html + htmlNode);
}*/




/*
function parseContent(content) {
    var arr = content.split("@#");
    var s = "";
    arr.forEach(function(a) {
        var child = a.split("#@");
        if (child.length > 1) {
            s += "<dd style='color:orange'>" + child[0] + "</dd>" + "<dd>" + child[1] + "</dd>";
        } else {
            s += "<dd>" + child[0] + "</dd>"
        }
    });

    return s;
}*/
