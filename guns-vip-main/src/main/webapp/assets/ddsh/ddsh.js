layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 首次跟单表管理
     */
    var ddsh = {
        tableId: "ddshTable"
    };

    /**
     * 初始化表格的列
     */
    ddsh.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'hlrq', sort: true, title: '婚礼日期'},
            {field: 'hljd', sort: true, title: '婚礼酒店'},
            {field: 'gdr', sort: true, title: '跟单人'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'htje', sort: true, title: '合同金额'},
            {field: 'jfdj', sort: true, title: '交付订金'},
            {field: 'bz', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ddsh.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(ddsh.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    ddsh.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/ddsh/add';
    };

    /**
     * 导出excel按钮
     */
    ddsh.exportExcel = function () {
        var checkRows = table.checkStatus(ddsh.tableId);
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
    ddsh.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/ddsh/edit?id=' + data.id;
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ddsh.tableId,
        url: Feng.ctxPath + '/ddsh/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ddsh.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ddsh.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ddsh.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ddsh.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ddsh.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ddsh.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ddsh.onDeleteItem(data);
        }
    });
});
