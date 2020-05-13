layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 婚礼司仪管理
     */
    var Hlsxs = {
        tableId: "hlsxsTable"
    };

    /**
     * 初始化表格的列
     */
    Hlsxs.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'sxsjhwcrq', sort: true, title: '计划完成日期'},
            {field: 'sxsydrq', sort: true, title: '实际完成日期'},
            {field: 'sxspz', sort: true, title: '摄像师配置'},
            {field: 'sxswczt', sort: true, title: '是否完成'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Hlsxs.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Hlsxs.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Hlsxs.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/hlsxs/add';
    };

    /**
     * 导出excel按钮
     */
    Hlsxs.exportExcel = function () {
        var checkRows = table.checkStatus(Hlgz.tableId);
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
    Hlsxs.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/hlsxs/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Hlsxs.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/hlsxs/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Hlsxs.tableId);
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
        elem: '#' + Hlsxs.tableId,
        url: Feng.ctxPath + '/hlsxs/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Hlsxs.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Hlsxs.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Hlsxs.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Hlsxs.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Hlsxs.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Hlsxs.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Hlsxs.onDeleteItem(data);
        }
    });
});
