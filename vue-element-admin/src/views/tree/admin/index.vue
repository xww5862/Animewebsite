<template>
  <div class="dashboard-editor-container">


    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>
    <p style="color: red"><b>PS:横坐标视频标题按照点评值降序</b></p>


    <el-row>
      <el-col :span="12">
        <div id="main3" style="width: 500px; height: 500px;background-color:white;margin-top:20px"></div>
      </el-col>

      <el-col :span="12">
        <div id="main4" style="width: 500px; height: 500px;background-color:white;margin-top:20px"></div>
      </el-col>

      <el-col :span="12">
        <div id="main5" style="
            width: 500px;
            height: 500px;
            background-color: white;
            margin-top: 20px;
          "></div>
      </el-col>
      
      <el-col :span="12">
        <div id="main6" style="
            width: 500px;
            height: 500px;
            background-color: white;
            margin-top: 20px;
          "></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import GithubCorner from '@/components/GithubCorner'
import LineChart from './components/LineChart'
import * as echarts from "echarts"
import axios from 'axios'

function sortNumber(a, b) {
  return b - a
}

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}
export default {
  name: 'DashboardAdmin',
  components: {
    GithubCorner,
    LineChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {


    //动漫种类饼图
    var option3 = {
      title: {
        text: "动漫种类分布图",
        subtext: "比例图 ",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      series: [
        {
          name: "Access From",
          type: "pie",
          radius: "50%",
          label: {
            normal: {
              show: true,
              position: "inner",
              textStyle: {
                fontWeight: 300,
                fontSize: 14,
              },
              formatter: "{d}%",
            },
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
      ],
    };


    //动漫类型
    var btypeDom = document.getElementById("main3");
    var myChart3 = echarts.init(btypeDom);

    //动漫种类分布图
    axios.get("http://localhost:8081/echart/getAllVideoType").then((res) => {
      // console.log(res,"类型");
      option3.series[0].data = [
        {
          name: "热血",
          value: res.data.data[0],
        },
        {
          name: "恋爱",
          value: res.data.data[1],
        },
        {
          name: "穿越",
          value: res.data.data[2],
        },
        {
          name: "搞笑",
          value: res.data.data[3],
        },
        {
          name: "治愈",
          value: res.data.data[4],
        },
        {
          name: "VIP",
          value: res.data.data[5],
        },
      ];
      myChart3.setOption(option3);
    });

    //用户种类饼图
    var option4 = {
      title: {
        text: "用户类型分布图",
        subtext: "比例图 ",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      series: [
        {
          name: "Access From",
          type: "pie",
          radius: "50%",
          label: {
            normal: {
              show: true,
              position: "inner",
              textStyle: {
                fontWeight: 300,
                fontSize: 14,
              },
              formatter: "{d}%",
            },
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
      ],
    };


    //用户类型
    var btypeDom1 = document.getElementById("main4");
    var myChart4 = echarts.init(btypeDom1);

    //动漫种类分布图
    axios.get("http://localhost:8081/echart/getAllUserType").then((res) => {
      // console.log(res,"类型");
      option4.series[0].data = [
        {
          name: "普通用户",
          value: res.data.data[0],
        },
        {
          name: "VIP用户",
          value: res.data.data[1],
        },
        {
          name: "封禁用户",
          value: res.data.data[2],
        },
      ];
      myChart4.setOption(option4);
    });





    var talkDom = document.getElementById("main5");
    var myChart5 = echarts.init(talkDom);
    var option5 = {
      title: {
        text: "最受欢迎前五视频点赞统计",
        subtext: "视频与点赞",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      xAxis: {
        type: "category",
        data: [],
        axisLabel: {
          interval: 0,
          formatter: function (value) {
            // 设置富文本样式
            var maxLength = 4; // 最大字符长度
            var ellipsis = value.length > maxLength ? "..." : ""; // 超出最大长度显示省略号
            return "{value| " + value.substring(0, maxLength) + ellipsis + "}";
          },
          rich: {
            value: {
              // 自定义富文本样式
              align: "center",
              fontSize: 12,
              fontWeight: "normal",
            },
          },
        },
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          name: "点赞数",
          data: [],
          type: "line",
        },
        {
          data: [],
          type: "bar",
        },
      ],
    };

    axios.get("http://localhost:8081/echart/getVideoPPnum").then((res1) => {
      option5.series[0].data = res1.data.data;
      option5.series[1].data = res1.data.data;

      axios.get("http://localhost:8081/echart/getVideoName").then((res2) => {
        option5.xAxis.data = res2.data.data;

        myChart5.setOption(option5);
      });
    });

//----------------用户粉丝最高前5名------------------------
    var talkDom1 = document.getElementById("main6");
    var myChart6 = echarts.init(talkDom1);
    var option6 = {
      title: {
        text: "最受欢迎前五用户统计",
        subtext: "用户与粉丝",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      xAxis: {
        type: "category",
        data: [],
        axisLabel: {
          interval: 0,
          formatter: function (value) {
            // 设置富文本样式
            var maxLength = 4; // 最大字符长度
            var ellipsis = value.length > maxLength ? "..." : ""; // 超出最大长度显示省略号
            return "{value| " + value.substring(0, maxLength) + ellipsis + "}";
          },
          rich: {
            value: {
              // 自定义富文本样式
              align: "center",
              fontSize: 12,
              fontWeight: "normal",
            },
          },
        },
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          name: "粉丝数",
          data: [],
          type: "line",
        },
        {
          data: [],
          type: "bar",
        },
      ],
    };

    axios.get("http://localhost:8081/echart/getUserFannum").then((res1) => {
      option6.series[0].data = res1.data.data;
      option6.series[1].data = res1.data.data;

      axios.get("http://localhost:8081/echart/getUserName").then((res2) => {
        option6.xAxis.data = res2.data.data;

        myChart6.setOption(option6);
      });
    });



  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    fetchData() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getData'
      }).then(function (resp) {
        console.log(resp.data,'我是预测加实际播放量')
        var temp = []
        for (var i = 0; i < 7; i++) {
          console.log(parseInt(resp.data[i]))
          temp.push(parseInt(resp.data[i+7]))
        }
        console.log(temp)
        vm.lineChartData.actualData = resp.data
        vm.lineChartData.expectedData = temp
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
