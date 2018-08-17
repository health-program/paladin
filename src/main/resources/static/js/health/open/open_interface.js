$(function() {
    $.getAjax("/open/interface/data", init);
    hljs.initHighlighting();
});

function init(data) {

    var html = "";

    data.forEach(function(item) {
        var valueDefinition = item.itemValueDefinition;
        var content = "";
        if (valueDefinition.inputType == 'INPUT') {
            content = valueDefinition.valueType == "NUMBER" ? "输入数字" : "输入文本";
            content += "__________";
            if (valueDefinition.unit) {
                content += valueDefinition.unit;
            }
        } else if (valueDefinition.inputType == 'SELECT') {
            content = "<ul>";
            content += valueDefinition.single ? '单选' : '多选';

            valueDefinition.standards.forEach(function(s) {
                content += "<li><b>" + s.key + "</b><font style='color:#aaa'>(" + s.name + ")</font>" + "</li>";
            });
            content += "</ul>";
        }
        html += ' <tr><td>' + item.key + '</td><td>string</td><td>' + item.name + '</td><td>' + content + '</td></tr>'
    });

    $("#requestTable").html(html);
}