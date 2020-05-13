layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var sgzzrw = {
        tableId: "sgzzrwTable"
    };

    /**
     * 初始化表格的列
     */
    sgzzrw.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'sgzzydrq', sort: true, title: '计划完成日期'},
            {field: 'sgzzwczt', sort: true, title: '完成状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    sgzzrw.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(sgzzrw.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    sgzzrw.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/sgzzrw/add';
    };

    /**
     * 导出excel按钮
     */
    sgzzrw.exportExcel = function () {
        var checkRows = table.checkStatus(sgzzrw.tableId);
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
    sgzzrw.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/sgzzrw/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    sgzzrw.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sgzzrw/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(sgzzrw.tableId);
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
        elem: '#' + sgzzrw.tableId,
        url: Feng.ctxPath + '/sgzzrw/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: sgzzrw.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        sgzzrw.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        sgzzrw.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        sgzzrw.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + sgzzrw.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            sgzzrw.openEditDlg(data);
        } else if (layEvent === 'delete') {
            sgzzrw.onDeleteItem(data);
        }
    });
});
