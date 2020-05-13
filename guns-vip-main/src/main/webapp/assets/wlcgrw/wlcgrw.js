layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var wlcgrw = {
        tableId: "wlcgrwTable"
    };

    /**
     * 初始化表格的列
     */
    wlcgrw.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'wlcgydrq', sort: true, title: '计划完成日期'},
            {field: 'wlcgwczt', sort: true, title: '完成状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    wlcgrw.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(wlcgrw.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    wlcgrw.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wlcgrw/add';
    };

    /**
     * 导出excel按钮
     */
    wlcgrw.exportExcel = function () {
        var checkRows = table.checkStatus(wlcgrw.tableId);
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
    wlcgrw.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wlcgrw/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    wlcgrw.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wlcgrw/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(wlcgrw.tableId);
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
        elem: '#' + wlcgrw.tableId,
        url: Feng.ctxPath + '/wlcgrw/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: wlcgrw.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        wlcgrw.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        wlcgrw.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        wlcgrw.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + wlcgrw.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            wlcgrw.openEditDlg(data);
        } else if (layEvent === 'delete') {
            wlcgrw.onDeleteItem(data);
        }
    });
});
