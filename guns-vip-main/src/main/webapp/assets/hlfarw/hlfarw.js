layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 婚礼方案管理
     */
    var Hlfarw = {
        tableId: "hlfarwTable"
    };

    /**
     * 初始化表格的列
     */
    Hlfarw.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'xgtsjs', sort: true, title: '效果图设计师'},
            {field: 'hlfajhwcrq', sort: true, title: '计划完成日期'},
            {field: 'hlfaydrq', sort: true, title: '实际完成日期'},
            {field: 'hlfaxgt', sort: true, title: '效果图'},
            {field: 'hlfahtqd', sort: true, title: '合同清单'},
            {field: 'hlfazt', sort: true, title: '是否完成'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Hlfarw.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Hlfarw.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Hlfarw.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/hlfarw/add';
    };

    /**
     * 导出excel按钮
     */
    Hlfarw.exportExcel = function () {
        var checkRows = table.checkStatus(Hlfarw.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'csv');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Hlfarw.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/hlfarw/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Hlfarw.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/hlfarw/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Hlfarw.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Hlfarw.tableId,
        url: Feng.ctxPath + '/hlfarw/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Hlfarw.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Hlfarw.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Hlfarw.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Hlfarw.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Hlfarw.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Hlfarw.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Hlfarw.onDeleteItem(data);
        }
    });
});
