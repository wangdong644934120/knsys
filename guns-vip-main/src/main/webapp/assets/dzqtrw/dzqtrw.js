layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var dzqtrw = {
        tableId: "dzqtrwTable"
    };

    /**
     * 初始化表格的列
     */
    dzqtrw.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'dzqtydrq', sort: true, title: '计划完成日期'},
            {field: 'dzqtwczt', sort: true, title: '完成状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    dzqtrw.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(dzqtrw.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    dzqtrw.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/dzqtrw/add';
    };

    /**
     * 导出excel按钮
     */
    dzqtrw.exportExcel = function () {
        var checkRows = table.checkStatus(dzqtrw.tableId);
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
    dzqtrw.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/dzqtrw/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    dzqtrw.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/dzqtrw/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(dzqtrw.tableId);
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
        elem: '#' + dzqtrw.tableId,
        url: Feng.ctxPath + '/dzqtrw/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: dzqtrw.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        dzqtrw.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        dzqtrw.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        dzqtrw.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + dzqtrw.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            dzqtrw.openEditDlg(data);
        } else if (layEvent === 'delete') {
            dzqtrw.onDeleteItem(data);
        }
    });
});
