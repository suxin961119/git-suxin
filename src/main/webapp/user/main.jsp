<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#article-show-table").jqGrid({
            url: "${pageContext.request.contextPath}/user/selectUsersByStarId",
            datatype: "json",
            colNames: ['编号', '用户名', '密码', '昵称', '头像', '电话', '性别', '城区', '城市', '签名'],
            colModel: [
                {name: "id"},
                {name: "username"},
                {name: "password", hidden: true},
                {name: "nickname"},
                {
                    name: 'photo', editable: true, edittype: "file", formatter: function (value, option, rows) {
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/img/" + rows.photo + "'>";
                    }
                },
                {name: "phone"},
                {name: "sex", edittype: "select", editoptions: {value: "男:男;女:女"}},
                {name: "province", hidden: true},
                {name: "city"},
                {name: "sign"}

            ],
            styleUI: "Bootstrap",
            autowidth: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#article-page',
            viewrecords: true,
            caption: "所有用户",
            editurl: ""
        }).navGrid("#article-page", {edit: false, add: false, del: false, search: false});
    })

</script>


<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有用户</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/user/export">导出数据</a></li>
</ul>
<table id="article-show-table"></table>
<div id="article-page" style="height: 40px"></div>


