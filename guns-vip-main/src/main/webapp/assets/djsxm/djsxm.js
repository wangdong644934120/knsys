layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var djsxm = {
        tableId: "djsxmTable"
    };

    /**
     * 初始化表格的列
     */
    djsxm.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'fawcydrq', sort: true, title: '方案完成日期'},
            {field: 'dzqtydrq', sort: true, title: '电子请帖日期'},
            {field: 'sjapbydrq', sort: true, title: '时间安排表日期'},
            {field: 'jthyydrq', sort: true, title: '家庭会议日期'},
            {field: 'hchydrq', sort: true, title: '婚车花日期'},
            {field: 'djhy', sort: true, title: '搭建会议日期'},
            {field: 'wlcgydrq', sort: true, title: '物料采购日期'},
            {field: 'pmsjydrq', sort: true, title: '平面设计日期'},
            {field: 'sgzzydrq', sort: true, title: '手工制作日期'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    djsxm.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(djsxm.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    djsxm.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/djsxm/add';
    };

    /**
     * 导出excel按钮
     */
    djsxm.exportExcel = function () {
        var checkRows = table.checkStatus(djsxm.tableId);
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
    djsxm.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/djsxm/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    djsxm.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/djsxm/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(djsxm.tableId);
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
        elem: '#' + djsxm.tableId,
        url: Feng.ctxPath + '/djsxm/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: djsxm.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        djsxm.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        djsxm.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        djsxm.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + djsxm.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            djsxm.openEditDlg(data);
        } else if (layEvent === 'delete') {
            djsxm.onDeleteItem(data);
        }
    });
});
