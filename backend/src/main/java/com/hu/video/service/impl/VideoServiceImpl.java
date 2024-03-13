package com.hu.video.service.impl;

import com.hu.video.entity.TMessage;
import com.hu.video.entity.TPredict;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.vo.CommentedStar;
import com.hu.video.mapper.*;
import com.hu.video.service.IMsgTypeService;
import com.hu.video.service.IStateService;
import com.hu.video.service.IVideoService;
import com.hu.video.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {


    /*--------------调用mapper------------------*/
    @Autowired
    private TVideoMapper videoMapper;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TMessageMapper msgMapper;

    @Autowired
    private CommentedStarMapper commentedStarMapper;

    @Autowired
    private TPredictMapper predictMapper;
    /*------------调用service---------------*/
    @Autowired
    private IMsgTypeService msgTypeMService;

    @Autowired
    private IStateService stateService;

    @Override
    public String addViewSum(Long videoId) {
        int signal = videoMapper.updateVideoViewSumByVideoId(videoId);
        if (signal == 1) {
            return "更新成功";
        } else {
            return "观看次数更新出错";
        }
    }


    @Override
    public List<TVideo> getVideoListByUserId(Long userId) {
        return videoMapper.getVideoListByUserId(userId,4L);
    }

    @Override
    public List<TVideo> getVideoByVideoTypeId(Long videoTypeId) {
        return videoMapper.getVideoByVideoTypeId(videoTypeId);
    }

    @Override
    public List<TVideo> getIndexLastVideo() {
        return videoMapper.getIndexLastVideo();
    }

    @Override
    public List<TVideo> getAllVideo() {
        return videoMapper.getAllVideo();
    }

    @Override
    public List<TVideo> getVideoAndZanNum() {
        return videoMapper.getVideoAndZanNum();
    }

    @Override
    public int deleteVideo(Long videoId) {
        TVideo video = videoMapper.selectOneByVideoId(videoId);
        String videoPath = null;
        if(video.getVideoUrl() != null) {
            videoPath = "C:\\Users\\XWW\\Desktop\\VideoSystem-master\\backend\\src\\main\\resources" + video.getVideoUrl();
        }
        String suffix = null;
        String imagePath = "";
        if(video.getThunmbnailUrl() != null) {
            suffix = video.getThunmbnailUrl().substring(21);
            System.out.println(suffix);
            imagePath = "C:\\Users\\XWW\\Desktop\\VideoSystem-master\\backend\\src\\main\\resources\\static\\vimages\\" + suffix;
            System.out.println(imagePath);
        }
        // 删除文件
        try {
            if(suffix != null && video.getVideoUrl() != null) {
                FileUtil fileUtil = new FileUtil();
                fileUtil.deleteAllFilesOfDir(fileUtil.getFile(videoPath));
                fileUtil.deleteAllFilesOfDir(fileUtil.getFile(imagePath));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return videoMapper.deleteByPrimaryKey(videoId);
    }

    @Override
    public List<TVideo> getIndexRecommendVideo() {
        return videoMapper.getIndexRecommendVideo();
    }

    @Override
    public List<TVideo> queryByVideoTitle(String videoTitle) {
        return videoMapper.queryByVideoTitle(videoTitle);
    }

    @Override
    public int adminUpdateVideoImage(String s, Long videoId) {
        return videoMapper.adminUpdateVideoImage(s,videoId);
    }

    @Override
    public Long getVideoLastId(String videoTitle) {
        return videoMapper.getVideoLastId(videoTitle);
    }
    //上传视频封面
    @Override
    public int updateVideoImage(String s, Long videoId) {
        return videoMapper.updateVideoImage(s,videoId);
    }
    //上传视频路径
    @Override
    public int updateVideoPath(String path, Long videoId) {
        return videoMapper.updateVideoPath(path,videoId);
    }
    //上传视频详情
    @Override
    public int addVideo(TVideo video) {
        return videoMapper.addVideo(video);
    }

    //发评论
    @Override
    public String addComment(int starNum, Long userId, Long videoId) {
        TVideo video = videoMapper.selectOneByVideoId(videoId);
        TUser user = userMapper.selectByPrimaryKey(video.getUser().getUserId());
        CommentedStar commentedStar = commentedStarMapper.getCommentedStar(userId, videoId);
        TMessage msg = new TMessage();
        if(commentedStar != null) {
            return "你已经参加点评";
        }
        if(user != null && video != null) {
            commentedStarMapper.insertOne(userId, videoId, starNum);

            //----------------预测值插入----------------------------------
            TVideo tVideo = videoMapper.selectOneByVideoId(videoId);
            Long pre_userId = tVideo.getUserId();
            TPredict predict = new TPredict();
            Float pre_start = 0f;
            List<CommentedStar> commentedStars = commentedStarMapper.getCommentedStarByVideoId(videoId);
            for (CommentedStar commentedStar1:commentedStars){
                pre_start += commentedStar1.getStarNum();
            }
            if (commentedStars.size()!=0){
                pre_start/=commentedStars.size();
            }
            predict.setUserId(pre_userId);
            predict.setVideoId(videoId);
            predict.setPreStar(pre_start);
            if (predictMapper.selectByVideoId(videoId)!=null){
                predictMapper.updateByViodeoId(pre_start,videoId);
            }else {
                predictMapper.insert(predict);
            }

//-------------------消息发送通知

            msg.setMsgTitle("点评提醒");
            msg.setMsgContext("你好！你的视频为《" + video.getVideoTitle() + "》的视频获得用户【" + user.getUserName() + "】的" + starNum + "星点评");
            msg.setMsgType(msgTypeMService.findByTypeName("EvaluateNotice"));
            msg.setMsgState(stateService.getStateByStateId(6L));
            msg.setReceiveUser(video.getUser());
            msgMapper.insertMsg(msg);
            return "点评成功";
        }
        return "不可对自身视频点评";
    }

    //点赞
    @Override
    public String thumbsUp(TUser user, Long videoId) {
        TVideo video = this.getVideoByVideoId(videoId);
        TMessage msg = new TMessage();
        if (user != null && !user.getUserId().equals(video.getUser().getUserId())) {
            try {
                videoMapper.updateVideoPpSumByVideoId(videoId);
                msg.setMsgTitle("点赞提醒");
                msg.setMsgContext("你好! 你的视频《" + video.getVideoTitle() + "》的视频获得用户【" + user.getUserName() + "】的一枚点赞");
                msg.setMsgType(msgTypeMService.findByTypeName("SupportNotice"));
                msg.setMsgState(stateService.getStateByStateId(6L));
                msg.setReceiveUser(video.getUser());
                msgMapper.insertMsg(msg);
                return "点赞成功";
            } catch (Exception e) {
                return "点赞失败";
            }
        } else {
            return "不可对自己视频点赞";
        }
    }
    //推荐视频
    @Override
    public List<TVideo> getRecommendVideo(int curPage, int pageSize, Long userId) {
        int videoCount = videoMapper.selectVideoCount();
        int curIndex = (curPage - 1) * pageSize;
        if (curPage > 0 && pageSize > 0 && curIndex <= videoCount) {
            List<TVideo> videoList = videoMapper.selectRecommendVideo(curIndex, pageSize, userId);
            if (videoList != null) {
                return videoList;
            }
        }
        return null;
    }

    @Override
    public TVideo getVideoByVideoId(Long videoId) {
        if (videoId != 0) {
            TVideo video = videoMapper.selectOneByVideoId(videoId);
            if (video != null) {
                return video;
            }
        }
        return null;
    }
}
