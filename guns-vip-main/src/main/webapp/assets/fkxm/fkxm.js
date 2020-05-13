layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 婚礼方案管理
     */
    var fkxm = {
        tableId: "fkxmTable"
    };

    /**
     * 初始化表格的列
     */
    fkxm.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'zfzr', sort: true, title: '总负责人'},
            {field: 'htje', sort: true, title: '合同金额'},
            {field: 'jfdj', sort: true, title: '小订金'},
            {field: 'jfdjwczt', sort: true, title: '小订金完成'},
            {field: 'ddj', sort: true, title: '50%大订金'},
            {field: 'ddjwczt', sort: true, title: '大订金完成'},
            {field: 'jdk', sort: true, title: '40%进度款'},
            {field: 'jdkwczt', sort: true, title: '进度款完成'},
            {field: 'wk', sort: true, title: '10%尾款'},
            {field: 'wkwczt', sort: true, title: '尾款完成'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    fkxm.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(fkxm.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    fkxm.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/fkxm/add';
    };

    /**
     * 导出excel按钮
     */
    fkxm.exportExcel = function () {
        var checkRows = table.checkStatus(fkxm.tableId);
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
    fkxm.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/fkxm/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    fkxm.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/fkxm/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(fkxm.tableId);
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
        elem: '#' + fkxm.tableId,
        url: Feng.ctxPath + '/fkxm/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: fkxm.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        fkxm.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        fkxm.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        fkxm.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + fkxm.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            fkxm.openEditDlg(data);
        } else if (layEvent === 'delete') {
            fkxm.onDeleteItem(data);
        }
    });
});
