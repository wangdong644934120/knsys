layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 婚礼用户表管理
     */
    var User = {
        tableId: "userTable"
    };

    /**
     * 初始化表格的列
     */
    User.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键id'},
            {field: 'xlxm', sort: true, title: '新郎姓名'},
            {field: 'xldh', sort: true, title: '新郎电话'},
            {field: 'xfdz', sort: true, title: '新房地址'},
            {field: 'xnxm', sort: true, title: '新娘姓名'},
            {field: 'xndh', sort: true, title: '新娘电话'},
            {field: 'njdz', sort: true, title: '娘家地址'},
            {field: 'hljd', sort: true, title: '婚礼酒店'},
            {field: 'jdr', sort: true, title: '接待人'},
            {field: 'hlrq', sort: true, title: '婚礼日期'},
            {field: 'jdrq', sort: true, title: '进店日期'},
            {field: 'tdjl', sort: true, title: '谈单记录'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    User.search = function () {
        var queryData = {};
        queryData['xlxm'] = $("#condition").val();
        table.reload(User.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    User.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/knuser/add';
    };

    /**
     * 导出excel按钮
     */
    User.exportExcel = function () {
        var checkRows = table.checkStatus(User.tableId);
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
    User.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/knuser/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    User.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/knuser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(User.tableId);
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
        elem: '#' + User.tableId,
        url: Feng.ctxPath + '/knuser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: User.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        User.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        User.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        User.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + User.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            User.openEditDlg(data);
        } else if (layEvent === 'delete') {
            User.onDeleteItem(data);
        }
    });


});
