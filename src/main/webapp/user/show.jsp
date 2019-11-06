<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '上半年用户注册趋势'
        },
        tooltip: {},
        legend: {
            data: ['男', '女']
        },
        xAxis: {
            data: ["一月份", "二月份", "三月份", "四月份", "五月份", "六月份"]
        },
        yAxis: {},
        series: [{
            name: '男',
            type: 'line',//bar:柱状图
            data: [5, 20, 36, 10, 10, 20]
        }, {
            name: '女',
            type: 'line',//bar:柱状图
            data: [15, 20, 32, 40, 20, 25]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url: "${pageContext.request.contextPath}/user/show.json",
        type: "get",
        datatype: "json",
        success: function (data) {
            myChart.setOption({
                xAxis: {
                    data: data.name
                },
                series: [{
                    name: '男',
                    type: 'line',//bar:柱状图
                    data: data.nan
                }, {
                    name: '女',
                    type: 'line',//bar:柱状图
                    data: data.nv
                }]
            });
        }
    })


</script>