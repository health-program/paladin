var table;
var constants;

$(function() {
    if (constants && constants.length > 0) {
        $.getConstantEnum(constants, initDataGrid);
    } else {
        initDataGrid();
    }
});

function initDataGrid(enumMap) {

    table = $.createTable("#dataGrid", {
        idField: "code",
        columns: [
            [
                { radio: true },
                { title: "CODE", field: "code" },
                { title: "名称", field: "name" },
                { title: "类型", field: "type" },
                { title: "顺序", field: "orderNo" }
            ]
        ],
        url: '/common/sys/constant/find/page',
        searchbar: '#searchbar',
        sortName: 'code',
        sortOrder: 'asc',
        showColumns: true,
        pagination: true,
        clickToSelect: true,
        toolbar: "#toolbar",
        showRefresh: true,
    });
}

function add() {
    window.location = '/common/sys/constant/add/input';
}

function edit() {
    var row = getSelectRow();
    if (row) {
        window.location = '/common/sys/constant/edit/input?code=' + row.code;
    } else {
        $.errorMessage("请选中一条业务数据");
    }
}

function remove() {
    var row = getSelectRow();
    if (row) {
        layer.confirm('确定删除吗?', function() {
            $.getAjax('/common/sys/constant/delete?code=' + row.code, function() {
                $.successMessage("删除成功");
                table.refresh();
            });
        });
    } else {
        $.errorMessage("请选中一条业务数据");
    }
}

function getSelectRow() {
    var rows = table.getSelections();
    return rows.length > 0 ? rows[0] : null;
}

function search() {
    table.refresh();
}