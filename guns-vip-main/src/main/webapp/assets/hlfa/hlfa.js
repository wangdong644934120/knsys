layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var hlfa = {
        tableId: "hlfaTable"
    };

    /**
     * 初始化表格的列
     */
    hlfa.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'hlfajhwcrq', sort: true, title: '方案计划完成日期'},
            {field: 'xgtsjs', sort: true, title: '效果图设计师'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    hlfa.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(hlfa.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    hlfa.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/hlfa/add';
    };

    /**
     * 导出excel按钮
     */
    hlfa.exportExcel = function () {
        var checkRows = table.checkStatus(hlfa.tableId);
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
    hlfa.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/hlfa/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    hlfa.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/hlfa/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(hlfa.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + hlfa.tableId,
        url: Feng.ctxPath + '/hlfa/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: hlfa.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        hlfa.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        hlfa.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        hlfa.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + hlfa.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            hlfa.openEditDlg(data);
        } else if (layEvent === 'delete') {
            hlfa.onDeleteItem(data);
        }
    });
});
