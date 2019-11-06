<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#banner-show-table").jqGrid({
            url: '${pageContext.request.contextPath}/carousel/selectAll',
            datatype: "json",
            colNames: ['编号', '名称', '封面', '描述', '状态', '上传日期'],
            colModel: [
                {name: 'id', hidden: true, editable: false},
                {name: 'name', editable: true},
                {
                    name: 'cover', editable: true, edittype: "file", formatter: function (value, option, rows) {
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/img/" + rows.cover + "'>";
                    }
                },
                {name: 'describes', editable: true},
                {name: 'status', editable: true, edittype: "select", editoptions: {value: "正常:正常;冻结:冻结"}},
                {name: 'date'}
            ],
            height: 250,
            autowidth: true,
            styleUI: "Bootstrap",
            rowNum: 3,
            rowList: [3, 5, 10],
            pager: '#banner-page',
            sortname: 'id',
            viewrecords: true,
            caption: "轮播图列表",
            editurl: "${pageContext.request.contextPath}/carousel/edit"
        }).navGrid("#banner-page", {edit: true, add: true, del: true, search: false}, {
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
                        url: "${pageContext.request.contextPath}/carousel/upload",
                        type: "post",
                        fileElementId: "cover",
                        data: {id: id},
                        success: function (response) {
                            //自动刷新jqgrid表格
                            $("#banner-show-table").trigger("reloadGrid");
                        }
                    });
                }
                return "aaa";
            }
        });
    })
</script>


<div class="panel panel-heading">
    <h3>所有的轮播图</h3>
</div>
<table id="banner-show-table"></table>
<div id="banner-page" style="height: 40px"></div>
