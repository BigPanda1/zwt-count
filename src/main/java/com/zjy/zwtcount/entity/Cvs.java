package com.zjy.zwtcount.entity;

import lombok.Data;

/**
 * @Date 2020/4/1 9:58
 * @Created by zjy
 */

public class Cvs {
    private Integer id;
    private String url;
    private Integer volume; //访问量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
