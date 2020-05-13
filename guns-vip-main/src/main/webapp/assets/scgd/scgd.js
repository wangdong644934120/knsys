layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 首次跟单表管理
     */
    var Scgd = {
        tableId: "scgdTable"
    };

    /**
     * 初始化表格的列
     */
    Scgd.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'hlrq', sort: true, title: '婚礼日期'},
            {field: 'hljd', sort: true, title: '婚礼酒店'},
            {field: 'jdr', sort: true, title: '接待人'},
            {field: 'jdrq', sort: true, title: '进店日期'},
            {field: 'tdjl', sort: true, title: '谈单记录'},
            {field: 'gdr', sort: true, title: '跟单人'},
            {field: 'gdjg', sort: true, title: '跟单结果'},
            {field: 'gdjl', sort: true, title: '跟单记录'},
            {field: 'cjrq', sort: true, title: '跟单日期'},
            {field: 'jfdj', sort: true, title: '交付定金'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Scgd.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Scgd.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Scgd.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/scgd/add';
    };

    /**
     * 导出excel按钮
     */
    Scgd.exportExcel = function () {
        var checkRows = table.checkStatus(Scgd.tableId);
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
    Scgd.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/scgd/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Scgd.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/scgd/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Scgd.tableId);
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
        elem: '#' + Scgd.tableId,
        url: Feng.ctxPath + '/scgd/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Scgd.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Scgd.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Scgd.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Scgd.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Scgd.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Scgd.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Scgd.onDeleteItem(data);
        }
    });
});
