layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 首次跟单表管理
     */
    var Ddkh = {
        tableId: "ddkhTable"
    };

    /**
     * 初始化表格的列
     */
    Ddkh.initColumn = function () {
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
            {field: 'jfdj', sort: true, title: '小订金'},
            {field: 'ddj', sort: true, title: '50%大订金'},
            {field: 'jdk', sort: true, title: '40%进度款'},
            {field: 'wk', sort: true, title: '10%尾款'},
            {field: 'bz', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Ddkh.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Ddkh.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Ddkh.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/ddkh/add';
    };

    /**
     * 导出excel按钮
     */
    Ddkh.exportExcel = function () {
        var checkRows = table.checkStatus(Ddkh.tableId);
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
    Ddkh.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/ddkh/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Ddkh.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/ddkh/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Ddkh.tableId);
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
        elem: '#' + Ddkh.tableId,
        url: Feng.ctxPath + '/ddkh/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Ddkh.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Ddkh.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Ddkh.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Ddkh.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Ddkh.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Ddkh.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Ddkh.onDeleteItem(data);
        }
    });
});
