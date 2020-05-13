layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var fawcrw = {
        tableId: "fawcrwTable"
    };

    /**
     * 初始化表格的列
     */
    fawcrw.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'fawcydrq', sort: true, title: '计划完成日期'},
            {field: 'fawcwczt', sort: true, title: '完成状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    fawcrw.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(fawcrw.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    fawcrw.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/fawcrw/add';
    };

    /**
     * 导出excel按钮
     */
    fawcrw.exportExcel = function () {
        var checkRows = table.checkStatus(fawcrw.tableId);
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
    fawcrw.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/fawcrw/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    fawcrw.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/fawcrw/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(fawcrw.tableId);
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
        elem: '#' + fawcrw.tableId,
        url: Feng.ctxPath + '/fawcrw/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: fawcrw.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        fawcrw.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        fawcrw.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        fawcrw.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + fawcrw.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            fawcrw.openEditDlg(data);
        } else if (layEvent === 'delete') {
            fawcrw.onDeleteItem(data);
        }
    });
});
