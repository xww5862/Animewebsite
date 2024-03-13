package com.hu.video.entity;

public class TBlock {
    private Long blockId;

    private Long userId;

    private Long blockedId;

    @Override
    public String toString() {
        return "TBlock{" +
                "blockId=" + blockId +
                ", userId=" + userId +
                ", blockedId=" + blockedId +
                '}';
    }

    public TBlock(Long blockId, Long userId, Long blockedId) {
        this.blockId = blockId;
        this.userId = userId;
        this.blockedId = blockedId;
    }

    public TBlock() {
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(Long blockedId) {
        this.blockedId = blockedId;
    }
}
