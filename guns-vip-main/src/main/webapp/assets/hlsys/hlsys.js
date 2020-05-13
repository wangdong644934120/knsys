layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 婚礼司仪管理
     */
    var Hlsys = {
        tableId: "hlsysTable"
    };

    /**
     * 初始化表格的列
     */
    Hlsys.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'sysjhwcrq', sort: true, title: '计划完成日期'},
            {field: 'sysydrq', sort: true, title: '实际完成日期'},
            {field: 'syspz', sort: true, title: '摄影师配置'},
            {field: 'syswczt', sort: true, title: '是否完成'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Hlsys.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(Hlsys.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Hlsys.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/hlsys/add';
    };

    /**
     * 导出excel按钮
     */
    Hlsys.exportExcel = function () {
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
    Hlsys.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/hlsys/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Hlsys.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/hlsys/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Hlsys.tableId);
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
        elem: '#' + Hlsys.tableId,
        url: Feng.ctxPath + '/hlsys/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Hlsys.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Hlsys.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Hlsys.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Hlsys.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Hlsys.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Hlsys.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Hlsys.onDeleteItem(data);
        }
    });
});
