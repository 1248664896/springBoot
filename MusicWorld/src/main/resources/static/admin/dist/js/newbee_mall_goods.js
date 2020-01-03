$(function () {
    $("#jqGrid").jqGrid({
        url: '/music/paging/MusicsList',
        datatype: "json",
        colModel: [
            {label: '音乐编号', name: 'id', index: 'id', width: 60, key: true},
            {label: '音乐名称', name: 'name', index: 'name', width: 120},
            {label: '音乐简介', name: 'info', index: 'info', width: 120},
            {label: '音乐图片', name: 'coverImgId', index: 'coverImgId', width: 120, formatter: coverImageFormatter},
            {label: '音乐类型', name: 'type', index: 'type', width: 60},
            {label: '音乐售价', name: '', index: '', width: 60},
            // {label: '音乐播放', name: 'id', index: 'id', width: 60,
            //     formatter: showId
            // },
            {
                label: '上架状态',
                name: 'status',
                index: 'status',
                width: 80,
                formatter: musicStatusFormatter //以下 的方法  function musicStatusFormatter(status)
            },
            {label: '创建时间', name: 'addDateTime', index: 'addDateTime', width: 60}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function musicStatusFormatter(status) {
        //音乐上架状态 0-上架 1-下架
        if (status == 'putaway') {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">上架中</button>";
        }
        if (status == 'unShelve') {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">已下架</button>";
        }
    }
    // function showId(id) {
    //     //音乐上架状态 0-上架 1-下架
    //
    //         return "<a  href="'D:\musicWorld\newFile\'+'id'+'.mp3'" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">播放音乐</a>";
    //
    // }


    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"80\" width=\"80\" alt='音乐主图'/>";
    }

});

/**
 * jqGrid重新加载
 */
function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/**
 * 添加音乐
 */
function addMusic() {
    window.location.href = "/music/edit";
}

/**
 * 修改商品
 */
function editGoods() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/goods/edit/" + id;
}

/**
 * 上架
 */
function putUpGoods() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行上架操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
        if (flag) {
            $.ajax({
                type: "PUT",
                url: "/music/musicPutaway",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r == true) {
                        swal("上架成功", {
                            icon: "success",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        swal("上架失败", {
                            icon: "error",
                        });
                    }
                }
            });
        }
    }
)
    ;
}

/**
 * 下架
 */
function putDownGoods() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行下架操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
        if (flag) {
            $.ajax({
                type: "PUT",
                url: "/music/musicUnShelve",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r == true) {
                        swal("下架成功", {
                            icon: "success",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        swal("下架失败", {
                            icon: "error",
                        });
                    }
                }
            });
        }
    }
)
    ;
}