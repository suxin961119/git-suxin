<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#album-show-table").jqGrid({
            url: '${pageContext.request.contextPath}/album/selectAll',
            datatype: "json",
            height: 300,
            colNames: ['编号', '专辑名字', '专辑作者', '专辑封面', '音乐数量', '专辑内容', '发布时间'],
            colModel: [
                {name: 'id', editable: false},
                {name: 'name', editable: true},
                {
                    name: 'starId',
                    editable: true,
                    edittype: "select",
                    editoptions: {dataUrl: "${pageContext.request.contextPath}/star/getAllStarForSelect"},
                    formatter: function (value, option, rows) {
                        return rows.star.nickname;
                    }
                },
                {
                    name: 'cover', editable: true, edittype: "file", formatter: function (value, option, rows) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + rows.cover + "'>";
                    }
                },
                {name: 'count'},
                {name: 'brief', editable: true},
                {name: 'date', editable: true, edittype: "date"}
            ],
            styleUI: 'Bootstrap',
            autowidth: true,
            rowNum: 3,
            rowList: [3, 5, 10],
            pager: '#album-page',
            viewrecords: true,
            subGrid: true,
            caption: "所有专辑列表",
            editurl: "${pageContext.request.contextPath}/album/add",
            subGridRowExpanded: function (subgrid_id, id) {
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                    "<div id='" + pager_id + "' class='scroll'></div>");
                $("#" + subgrid_table_id).jqGrid(
                    {
                        url: "${pageContext.request.contextPath}/chapter/selectChopterByAlbumId?albumId=" + id,
                        datatype: "json",
                        colNames: ['编号', '名字', '歌手', '大小', '时长', '创建时间', '在线播放'],
                        colModel: [
                            {name: "id", hidden: true},
                            {name: "name", editable: true, edittype: "file"},
                            {name: "singer", editable: true},
                            {name: "size"},
                            {name: "duration"},
                            {name: "createDate"},
                            {
                                name: "operation", width: 300, formatter: function (value, option, rows) {
                                    return "<audio controls>\n" +
                                        "  <source src='${pageContext.request.contextPath}/img/" + rows.name + "' >\n" +
                                        "</audio>";
                                }
                            }
                        ],
                        styleUI: "Bootstrap",
                        rowNum: 2,
                        pager: pager_id,
                        autowidth: true,
                        height: '100%',
                        editurl: "${pageContext.request.contextPath}/chapter/edit?albumId=" + id
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit: false,
                        add: true,
                        del: false,
                        search: false
                    }, {}, {
                        //    控制添加
                        closeAfterAdd: true,
                        afterSubmit: function (response) {
                            var status = response.responseJSON.status;
                            if (status) {
                                var cid = response.responseJSON.message;
                                $.ajaxFileUpload({
                                    url: "${pageContext.request.contextPath}/chapter/upload",
                                    type: "post",
                                    fileElementId: "name",
                                    data: {id: cid, albumId: id},
                                    success: function () {
                                        $("#" + subgrid_table_id).trigger("reloadGrid");
                                    }
                                })
                            }
                            return "123";
                        }
                    });
            }

        }).jqGrid('navGrid', '#album-page', {
            add: true,
            edit: false,
            del: false,
            search: false
        }, {
            //控制修改
            closeAfterEdit: true,
            beforeShowForm: function (fmt) {
                fmt.find("#cover").attr("disabled", true);
            }
        }, {
            //控制添加
            closeAfterAdd: true,
            afterSubmit: function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if (status) {
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        type: "post",
                        fileElementId: "cover",
                        data: {id: id},
                        success: function (response) {
                            //自动刷新jqgrid表格
                            $("#album-show-table").trigger("reloadGrid");
                        }
                    });
                }
                return "aaa";
            }
        });
    })
</script>


<div class="panel page-header">
    <h3>展示所有的专辑</h3>
</div>
<table id="album-show-table"></table>
<div id="album-page" style="height: 40px;"></div>