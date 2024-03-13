package com.hu.video.entity;

import java.util.Date;

public class ReplyAndComment {
        private Long replyId;
        private Long videoId;
        private Long commentId;
        private Long replyUserId;
        private Long repliedUserId;
        private String content;
        private Date createTime;
        private Long userId;
        private String userName;
        private String iconUrl;
        private String repliedUserName;
        private String repliedUserIconUrl;

        // 添加构造函数、getter和setter方法

        public ReplyAndComment(Long replyId, Long videoId, Long commentId, Long replyUserId, Long repliedUserId, String content, Date createTime, Long userId, String userName, String iconUrl, String repliedUserName, String repliedUserIconUrl) {
            this.replyId = replyId;
            this.videoId = videoId;
            this.commentId = commentId;
            this.replyUserId = replyUserId;
            this.repliedUserId = repliedUserId;
            this.content = content;
            this.createTime = createTime;
            this.userId = userId;
            this.userName = userName;
            this.iconUrl = iconUrl;
            this.repliedUserName = repliedUserName;
            this.repliedUserIconUrl = repliedUserIconUrl;
        }

        public ReplyAndComment() {
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

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getRepliedUserName() {
            return repliedUserName;
        }

        public void setRepliedUserName(String repliedUserName) {
            this.repliedUserName = repliedUserName;
        }

        public String getRepliedUserIconUrl() {
            return repliedUserIconUrl;
        }

        public void setRepliedUserIconUrl(String repliedUserIconUrl) {
            this.repliedUserIconUrl = repliedUserIconUrl;
        }

    @Override
    public String toString() {
        return "ReplyAndComment{" +
                "replyId=" + replyId +
                ", videoId=" + videoId +
                ", commentId=" + commentId +
                ", replyUserId=" + replyUserId +
                ", repliedUserId=" + repliedUserId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", repliedUserName='" + repliedUserName + '\'' +
                ", repliedUserIconUrl='" + repliedUserIconUrl + '\'' +
                '}';
    }
}
