<template>
    <div class="app-container">
    
      <el-table :data="list" border fit highlight-current-row>
        <el-table-column align="center" label="序号" width="95">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="视频名称">
          <template slot-scope="scope">
            {{ scope.row.videoTitle }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="播放量" width="95">
          <template slot-scope="scope">
            {{ scope.row.viewNum }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="视频类型" width="95">
          <template slot-scope="scope">
            {{ scope.row.videoType.typeName }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="编辑日期">
          <template slot-scope="scope">
            {{ scope.row.editDate }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="用户">
          <template slot-scope="scope">
            {{ scope.row.tUser.userName }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="状态">
          <template slot-scope="scope">
            {{ scope.row.videoState.stateName }}
          </template>
        </el-table-column>
  
        <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="restorevideo(scope.row.videoId)">
              通过
            </el-button>
            <el-button type="danger" size="mini" @click="delvideo(scope.row.videoId)">
              拒绝
            </el-button>
            <el-button type="primary" size="mini" @click="playvideo(scope.row.videoId), dialogVisible = true">
              播放
            </el-button>
            <el-dialog title="审核中" :visible.sync="dialogVisible" width="70%" :before-close="handleClose">
              <div ref="dplayerContainer"></div>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
              </span>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total > 0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
        @pagination="getList" />
  
    </div>
  </template>
  <script>
  import Pagination from '@/components/Pagination' // secondary package based on el-pagination
  import DPlayer from 'dplayer'; // 导入DPlayer库
  
  export default {
    components: { Pagination },
    data() {
      return {
        total: 0,
        dialogVisible: false,
        listQuery: {
          page: 1,
          limit: 5,
        },
        list: [
          {
            'videoId': 1,
            'videoTitle': '霸王别姬',
            'videoInfo': '段小楼（张丰毅）与程蝶衣（张国荣）是一对打',
            'videoType': {
              'videotypeId': 1,
              'typeName': '影视'
            },
            'editDate': '2021-04-26T11:59:12.000+0000',
            'tUser': {
              'userId': 1,
              'userName': 'yourname',
              'userAge': 22,
              'userSex': '男',
              'password': '123456',
              'encryptedProblem': '123',
              'fanNum': 0,
              'userTel': '15566666626',
              'registerDate': '2021-04-17 15:17:10',
              'iconUrl': '/user/getIcon/icon1.png',
              'stateId': 1
            },
            'videoState': {
              'stateId': 4,
              'stateName': '已上架'
            },
            'videoUrl': '/static/video/bwbj.mp4',
            'thumbnailUrl': null,
            'viewNum': 107,
            'ppNum': 7
          }
        ]
      }
    },
    created() {
      // this.fetchData()
      this.getList()
    },
  
    methods: {
      show() {
        dialogVisible = true
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => { });
      },
  
      playvideo(videoId) {
        console.log(videoId);
        var vm = this
        this.axios({
          method: 'GET',
          url: 'http://localhost:8081/video/getVideoUrlByUserId?videoId=' + videoId
        }).then(function (resp) {
          console.log(resp.data);
          var videoUrl = "http://localhost:8081/"+resp.data.videoUrl; // 假设服务器返回视频URL
          console.log(videoUrl);
          // 获取DPlayer容器元素
          var dplayerContainer = vm.$refs.dplayerContainer;
  
          // 配置DPlayer选项
          var dpOptions = {
            container: dplayerContainer,
            video: {
              url: videoUrl,
              type: 'auto'
            }
          };
  
          // 创建DPlayer实例
          var dp = new DPlayer(dpOptions);
  
          // 现在，DPlayer播放器已经创建并嵌入到对话框中
        }).catch(function (error) {
          console.error("Error fetching video URL:", error);
        });
  
  
      },
      getList() {
        var vm = this
        this.axios({
          method: 'GET',
          url: 'http://localhost:8081/admin/underVideoPageInfo1?pageNum=' + vm.listQuery.page + '&pageSize=' + vm.listQuery.limit
        }).then(function (resp) {
          vm.total = resp.data.total // 讲pageInfo中的total放到vm的total
          vm.list = resp.data.list
        })
      },
      addvideo() {
        this.$router.push('/addvideo/index')
      },
      restorevideo(id) {
        var vm = this
        this.$confirm('此操作将上架该视频, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          vm.axios({
            method: 'GET',
            url: 'http://localhost:8081/admin/restoreVideo/' + id
          }).then(function (resp) {
            if (resp.data === 'success') {
              vm.$message({
                message: '通过成功',
                type: 'success'
              })
              vm.getList()
            }
            // eslint-disable-next-line handle-callback-err
          }).catch(function (error) {
            vm.$message.error('通过失败')
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消下架'
          });
        });
      },
      delvideo(id) {
        var vm = this
        this.$confirm('此操作将拒绝上架该视频, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios({
            method: 'GET',
            url: 'http://localhost:8081/admin/deleteVideo/' + id
          }).then(function (resp) {
            if (resp.data === 'success') {
              vm.$message({
                message: '拒绝成功',
                type: 'success'
              })
              vm.getList()
            }
            // eslint-disable-next-line handle-callback-err
          }).catch(function (error) {
            vm.$message.error('拒绝失败')
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消下架'
          })
        })
      },
      // 获取用户列表
      fetchData() {
        var vm = this
        this.axios({
          method: 'GET',
          url: 'http://localhost:8081/admin/list'
        }).then(function (resp) {
          vm.list = resp.data
          console.log(resp.data)
        })
      }
    }
  }
  </script>
  <style>
  .basicinfo {
    margin: 15px 0;
  }
  
  .basicinfo .row>.col-xs-4 {
    padding-right: 0;
  }
  
  .basicinfo .row>div {
    margin: 5px 0;
  }
  
  .Dplayer_box {
    overflow: hidden;
    width: 100%;
    height: 100%;
    margin-bottom: 3%
  }
  
  #player1 {
    background-size: 100% 100%;
  }
  
  .player_av {
    width: 100%;
    height: 100%;
    float: left;
    background: #000
  }
  
  .Dplayer_danmu {
    width: 299px;
    float: right;
    height: 100%
  }
  
  .Dplayer_watching {
    width: 100%;
    height: 60px;
    line-height: 60px;
    display: inline-block;
    font-size: 12px;
    color: #99a2aa;
    box-shadow: #ddd 0 0 5px
  }
  
  .Dplayer-watching-number {
    margin-left: 20px;
    font-size: 18px;
    font-weight: 700;
    color: #222;
    padding: 0
  }
  
  .Dplayer_list {
    width: 100%;
    height: 30px;
    overflow: hidden
  }
  
  .Dplayer_list li {
    width: 60px;
    height: 30px;
    float: left;
    color: #99a2aa;
    font-size: 12px;
    text-align: center;
    line-height: 30px
  }
  
  .Dplayer_list li:nth-child(1) {
    width: 58px !important
  }
  
  .Dplayer_list li:nth-child(2) {
    width: 136px !important
  }
  
  .list_ovefiow {
    width: 100%;
    height: 363px;
    overflow: auto
  }
  
  .danmuku_list {
    width: 100%;
    height: 20px;
    line-height: 20px;
    text-align: left;
    color: #99a2aa;
    font-size: 12px;
    display: block;
    margin: auto;
    overflow: auto;
    margin-top: 5px
  }
  
  .danmuku_list li {
    float: left;
    width: 60px;
    height: 20px;
    white-space: nowrap;
    cursor: pointer
  }
  
  .danmuku_list li:nth-child(1) {
    width: 58px;
    margin-left: 8px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap
  }
  
  .danmuku_list li:nth-child(2) {
    width: 125px;
    margin-right: 15px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap
  }
  
  .danmuku_list li:nth-child(2):hover {
    color: #00a1d6
  }
  
  .danmuku_list li:nth-child(3) {
    margin-left: 5px
  }
  
  label {
    display: inline;
  }</style>
  