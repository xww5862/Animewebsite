package com.hu.video.entity;

import java.util.Date;

public class Reply {
    private Long replyId;
    private Long videoId;
    private Long commentId;
    private Long replyUserId;
    private Long repliedUserId;
    private String content;
    private Date createTime;
    private TUser user;

    public Reply() {
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", videoId=" + videoId +
                ", commentId=" + commentId +
                ", replyUserId=" + replyUserId +
                ", repliedUserId=" + repliedUserId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }

    public Reply(Long replyId, Long videoId, Long commentId, Long replyUserId, Long repliedUserId, String content, Date createTime, TUser user) {
        this.replyId = replyId;
        this.videoId = videoId;
        this.commentId = commentId;
        this.replyUserId = replyUserId;
        this.repliedUserId = repliedUserId;
        this.content = content;
        this.createTime = createTime;
        this.user = user;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Long getRepliedUserId() {
        return repliedUserId;
    }

    public void setRepliedUserId(Long repliedUserId) {
        this.repliedUserId = repliedUserId;
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

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }
}
