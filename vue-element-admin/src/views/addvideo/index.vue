<template>
  <div class="app-container">
    <!-- 步骤条 -->
    <el-steps :active="currentStep" align-center>
      <el-step title="视频详情"></el-step>
      <el-step title="缩略图和类型"></el-step>
      <el-step title="上传视频"></el-step>
    </el-steps>

    <!-- 第一步：视频详情 -->
    <div v-if="currentStep === 0" class="step-container">
      <div class="center-content">
        <el-form ref="form" :model="video" label-width="120px">
          <el-form-item label="视频名称">
            <el-input v-model="video.videoTitle" />
          </el-form-item>
          <el-form-item label="视频信息">
            <el-col :span="8">
              <el-input :rows="5" v-model="video.videoInfo" type="textarea" />
            </el-col>
          </el-form-item>
          <el-form-item label="视频类型">
            <el-select v-model="video.videoTypeId" placeholder="请选择">
              <el-option v-for="item in videoTypeList" :key="item.typeName" :label="item.typeName"
                :value="item.videotypeId" />
            </el-select>
          </el-form-item>

          <el-form-item label="视频状态">
            <el-select v-model="video.videoStateId" placeholder="请选择">
              <el-option v-for="item in stateList" :key="item.stateName" :label="item.stateName" :value="item.stateId" />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit">确定</el-button>
            <el-button @click="onCancel()">取消</el-button>
          </el-form-item>

          <div class="button-container">
            <el-button type="primary" @click="nextStep(1)">下一步</el-button>
          </div>
        </el-form>

      </div>
    </div>

    <!-- 第二步：缩略图和视频类型 -->
    <div v-if="currentStep === 1" class="step-container">
      <div class="center-content">
        <pan-thumb :image="image" />
        <el-button type="primary" icon="el-icon-upload" style="position: relative;bottom: 15px;margin-left: 40px;"
          @click="imagecropperShow = true">
          添加缩略图
        </el-button>
        <image-cropper v-show="imagecropperShow" :key="imagecropperKey" :width="210" :height="300" :url="uploadUrl"
          lang-type="en" @close="close" @crop-upload-success="cropSuccess" />
        <div class="button-container">
          <el-button @click="prevStep">上一步</el-button>
          <el-button type="primary" @click="nextStep(2)">下一步</el-button>
        </div>
      </div>
    </div>

    <!-- 第三步：上传视频 -->
    <div v-if="currentStep === 2" class="step-container">
      <div class="center-content">

        <ele-upload-video :data="{
          /*token: token*/
        }" :fileSize="1024" @error="handleUploadError" :responseFn="handleResponse" style="margin: 50px"
          action="http://localhost:8081/admin/adminVideoUpload" v-model="video.videoUrl" />
        <div class="button-container">
          <el-button @click="prevStep">上一步</el-button>
          <!-- <el-button type="primary">完成</el-button> -->
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.step-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  /* Make sure each step takes the full viewport height */
}

.center-content {
  width: 100%;
  max-width: 600px;
  /* Adjust the max width as needed */
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  /* Adjust margin as needed */
}
</style>
<script>
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
import EleUploadVideo from 'vue-ele-upload-video'
import qs from 'qs'

export default {
  components: { ImageCropper, PanThumb, EleUploadVideo },
  data() {
    return {
      currentStep: 0,
      imagecropperShow: false,
      imagecropperKey: 0,
      video: {
        'videoId': null,
        'videoTitle': '',
        'videoInfo': '',
        'videoTypeId': null,
        'videoStateId': null,
        'thunmbnailUrl': '',
        'videoType': {
          'videotypeId': null,
          'typeName': ''
        },
        'editDate': '',
        'tUser': {
          'userId': null,
          'userName': '',
          'userAge': null,
          'userSex': '',
          'password': '',
          'encryptedProblem': '',
          'fanNum': 0,
          'userTel': '',
          'registerDate': '',
          'iconUrl': '',
          'stateId': null
        },
        'videoState': {
          'stateId': null,
          'stateName': ''
        },
        'videoUrl': '',
        'thumbnailUrl': '',
        'viewNum': null,
        'ppNum': null
      },
      stateList: [
        {
          'stateId': '',
          'stateName': ''
        }
      ],
      videoTypeList: [
        {
          'typeName': '',
          'videotypeId': null
        }
      ],
      image: '',
      uploadUrl: 'http://localhost:8081/admin/thumbnailImageupload?videoId='
    };
  },
  created() {
    this.fetchDataById();
  },
  methods: {
    handleUploadError(error) {
      console.log('error', error);
    },
    handleResponse(response) {
      console.log(response.data);
      return response.data;
    },
    fetchDataById() {
      var vm = this;
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getStateList'
      }).then(function (resp) {
        vm.stateList = resp.data;
      });
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getVideoTypeList'
      }).then(function (resp) {
        vm.videoTypeList = resp.data;
      });
    },
    nextStep(step) {
      if (step === undefined) {
        this.currentStep++;
      } else {
        this.currentStep = step;
      }
    },
    prevStep() {
      this.currentStep--;
    },
    onSubmit() {
      var vm = this;
      console.log(vm.video);
      this.axios({
        method: 'POST',
        data: vm.video,
        url: 'http://localhost:8081/admin/addVideo',
        transformRequest: [function (data) {
          if (data.code == 302) {
            alert("succ");
          }
          data = qs.stringify(data);
          return data;
        }],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(function (resp) {
        vm.$message({
          message: '添加成功',
          type: 'success'
        });
      });
    },
    onCancel() {
      this.$message({
        message: '返回中!',
        type: 'success'
      });
      this.$router.push('/user');
    },
    cropSuccess(resData) {
      this.imagecropperShow = false;
      this.imagecropperKey = this.imagecropperKey + 1;
      this.image = resData;
      this.$message({
        message: '上传成功',
        type: 'success',
        duration: 2000
      });
    },
    close() {
      this.imagecropperShow = false;
    }
  }
};
</script>
