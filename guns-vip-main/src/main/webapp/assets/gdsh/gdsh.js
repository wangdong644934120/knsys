layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 首次跟单表管理
     */
    var gdsh = {
        tableId: "gdshTable"
    };

    /**
     * 初始化表格的列
     */
    gdsh.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'hlrq', sort: true, title: '婚礼日期'},
            {field: 'hljd', sort: true, title: '婚礼酒店'},
            {field: 'scgdr', sort: true, title: '首次跟单人'},
            {field: 'scgdjg', sort: true, title: '首次跟单结果'},
            {field: 'ecgdr', sort: true, title: '二次跟单人'},
            {field: 'ecgdjg', sort: true, title: '二次跟单结果'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}

        ]];
    };

    /**
     * 点击查询按钮
     */
    gdsh.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(gdsh.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    gdsh.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/gdsh/add';
    };

    /**
     * 导出excel按钮
     */
    gdsh.exportExcel = function () {
        var checkRows = table.checkStatus(gdsh.tableId);
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
    gdsh.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/gdsh/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    gdsh.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/gdsh/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(gdsh.tableId);
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
        elem: '#' + gdsh.tableId,
        url: Feng.ctxPath + '/gdsh/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: gdsh.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        gdsh.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        gdsh.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        gdsh.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + gdsh.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            gdsh.openEditDlg(data);
        } else if (layEvent === 'delete') {
            gdsh.onDeleteItem(data);
        }
    });
});
