$(function () {
    $.getAjax("/health/diet/list",initDiet);
});

var catalog;
var nameKey;
var dietInfoMap = {};
function initDiet(data) {
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
        clearable: true,
        autoSelect: false,
        autoDropup: true,
        ignorecase: true,
        showBtn: false,
        showHeader: true
    }).on('onDataRequestSuccess', function(e, result) {

    }).on('onSetSelectValue', function(e, keyword, data) {
        nameKey = data.nameKey;
        getDiseaseDiet();
        setDiseaseSummary(data);
    }).on('onUnsetSelectValue', function(a, b, c) {
        //console.log("onUnsetSelectValue", a);
    });
}

function setDiseaseSummary(data) {
    var html = '<dl><dd><i>疾病名称：</i>' + data.name + '</dd><dd><i>疾病关键字：</i>' + data.nameKey + '</dd></dl>';
    $("#diseaseSummary").html(html);
}

function getDiseaseDiet() {
    if (dietInfoMap) {
        var diet = dietInfoMap[nameKey];
        if (diet) {
            initDietTable(diet);
            return;
        }
    }

    $.postAjax("/health/diet/dietInfo", {diseaseKey: nameKey}, initDietTable);

}


var dietList;
function initDietTable(data) {
    dietList = data;
    dietInfoMap[nameKey]=dietList;
    var html = "";
    if (dietList && dietList.length > 0) {
        dietList.forEach(function(diet){
            if (diet.type == 3 || diet.type == 4 ) {
                html += '<thead><tr style="background-color:#94c17c"><th colspan="3">' + diet.summary + '</th></tr>'
                html = diet.type == 3 ? html += '<tr class="text-center success"><td  class="col-md-2">' + "宜吃食物" + '</td>' + '<td class="col-md-3>">' + "宜吃理由" + '</td>' + '<td class="col-md-3">' + "食用建议" + '</td></tr></thead>' :
                    html += '<tr style="text-align: center" class="success"><td>' + "忌吃食物" + '</td>' + '<td>' + "忌吃理由" + '</td>' + '<td>' + "忌吃建议" + '</td></tr></thead>'
                return;
            }
                html += '<tbody><tr ><td class="text-center" style="vertical-align: middle">'+ diet.food +'</td>'+ '<td style="vertical-align: middle">'+ diet.reason +'</td>' + '<td class="text-center">' + diet.suggestion + '</td></tr></tbody>'
        });
        $("#diet").html(html);
    }else {
        $("#diet").html(html);
        $.errorMessage("无该疾病的饮食宜忌 :（");
    }
}