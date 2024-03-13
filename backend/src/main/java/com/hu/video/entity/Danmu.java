package com.hu.video.entity;

public class Danmu {
    private Long danmuId;
    private double time;
    private Integer type;
    private Long color;
    private Long author;
    private String text;
    private Long videoId;

    @Override
    public String toString() {
        return "Danmu{" +
                "danmuId=" + danmuId +
                ", time=" + time +
                ", type=" + type +
                ", color=" + color +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", videoId=" + videoId +
                '}';
    }

    public Danmu() {
    }

    public Danmu(Long danmuId, double time, Integer type, Long color, Long author, String text, Long videoId) {
        this.danmuId = danmuId;
        this.time = time;
        this.type = type;
        this.color = color;
        this.author = author;
        this.text = text;
        this.videoId = videoId;
    }

    public Long getDanmuId() {
        return danmuId;
    }

    public void setDanmuId(Long danmuId) {
        this.danmuId = danmuId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
        this.color = color;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
