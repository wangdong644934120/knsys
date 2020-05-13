layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var Sdjg = {
        tableId: "sdjgTable"
    };

    /**
     * 初始化表格的列
     */
    Sdjg.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'syjhwcrq', sort: true, title: '司仪计划完成日期'},
            {field: 'sysjhwcrq', sort: true, title: '摄影师计划完成日期'},
            {field: 'sxsjhwcrq', sort: true, title: '摄像师计划完成日期'},
            {field: 'hzsjhwcrq', sort: true, title: '化妆师计划完成日期'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Sdjg.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Sdjg.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Sdjg.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/sdjg/add';
    };

    /**
     * 导出excel按钮
     */
    Sdjg.exportExcel = function () {
        var checkRows = table.checkStatus(Sdjg.tableId);
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
    Sdjg.openEditDlg = function (data) {
        console.log(data.id);
        window.location.href = Feng.ctxPath + '/sdjg/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Sdjg.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sdjg/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Sdjg.tableId);
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
        elem: '#' + Sdjg.tableId,
        url: Feng.ctxPath + '/sdjg/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Sdjg.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Sdjg.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Sdjg.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Sdjg.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Sdjg.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Sdjg.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Sdjg.onDeleteItem(data);
        }
    });
});
