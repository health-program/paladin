 var my={
    saveDrag :function (){
    var arr = [];
    // 拼装数组保持value键值对,再推荐一个bootbox插件，很美观。自己搞定吧
    
    $(function () {
        if ($("#field_list_table tr").length > 1) {
            $("#field_list_table tr:not(.no)").each(function (i,item) {
                arr[i]= $(this).attr('id')+':'+$(this).attr('value');
            })
        }
    /*传递给后台的格式如下 id:value，id为标签id不可变，value为排序字段一直在变。后台数据库设置为value排序即可
    
    array(1) {
      ["data"]=>
          array(86) {
            [0]=>
            string(5) "323:1"
            [1]=>
            string(5) "321:2"
            [2]=>
            string(5) "320:3"
            [3]=>
            string(5) "322:4"
            [4]=>
            string(5) "318:5"
            [5]=>
            string(5) "327:6"
        }
    }*/


    
        $.post("yourajaxdo/path/", {data: arr}, function (jsoninfo) {
            if (jsoninfo.code == 0) {
                bootbox.alert({
                    'message': jsoninfo.msg, 'closeButton': false, callback: function () {
                        window.location.reload();
                    }
                });
            } else {
                bootbox.alert({
                    'message': jsoninfo.msg, 'closeButton': false
                });
            }
        }, "json");

    })
}
}

/*最后在附上后台php代码
    // 拖动排序自动保存
    public function dragSortAction()
    {
        foreach ($_POST['data'] as $v) {
            list($id, $drag_sort) = explode(':', $v);
            $data['sort'] = $drag_sort;
            $info = $this->model->saveDragSort($data, $table, $id);
        }
        if ($info['code'] == 0) {
            //$this->json('0', '保存排序成功', $info);
        } else {
            $this->json('-1', '保存排序失败,请重试', $info);
        }
    }
 */
