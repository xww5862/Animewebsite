package com.hu.video.entity;


import java.util.Date;
import java.util.List;

public class Comment {
    private Long commentId;
    private Long commentUserId;
    private Long videoId;
    private String content;
    private Date createTime;
    private List<ReplyAndComment> replyList;
    private TUser user;

    public Comment() {
    }

    public Comment(Long commentId, Long commentUserId, Long videoId, String content, Date createTime, List<ReplyAndComment> replyList, TUser user) {
        this.commentId = commentId;
        this.commentUserId = commentUserId;
        this.videoId = videoId;
        this.content = content;
        this.createTime = createTime;
        this.replyList = replyList;
        this.user = user;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ReplyAndComment> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyAndComment> replyList) {
        this.replyList = replyList;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }
}
