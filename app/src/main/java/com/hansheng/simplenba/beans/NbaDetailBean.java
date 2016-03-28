package com.hansheng.simplenba.beans;

import java.io.Serializable;

/**
 * Created by hansheng on 2016/3/26.
 */
public class NbaDetailBean implements Serializable {

    private String author;


    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
