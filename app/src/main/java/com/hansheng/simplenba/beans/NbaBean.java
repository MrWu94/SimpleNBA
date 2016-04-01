package com.hansheng.simplenba.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hansheng on 2016/3/26.
 */
public class NbaBean implements Serializable {


    /**
     * contentType : ARTICLE
     * description : 北京时间3月26日，在湖人主场对阵掘金的比赛中，榜眼丹吉洛-拉塞尔右脚踝扭伤，被搀扶离场。他在受伤后
     * title : 拉塞尔脚踝扭伤 万幸骨头跟腱没问题
     * putdate : 1458972000000
     * imgUrlList : ["http://image.res.meizu.com/image/reader/215c90abac25f0f7edaa006e5d70a947/original"]
     * randomNum : 1458971777000
     * articleId : 117677376
     * contentSourceName : NBA
     * articleUrl : http://reader.res.meizu.com/reader/articlecontent/20160326/117677376.json
     * type : IMAGETEXT
     * sourceType : ZAKER
     */



    private String contentType;
    private String description;
    private String title;
    private long putdate;
    private long randomNum;
    private int articleId;
    private String contentSourceName;
    private String articleUrl;
    private String type;
    private String sourceType;
    private List<String> imgUrlList;



    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPutdate(long putdate) {
        this.putdate = putdate;
    }

    public void setRandomNum(long randomNum) {
        this.randomNum = randomNum;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setContentSourceName(String contentSourceName) {
        this.contentSourceName = contentSourceName;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public String getContentType() {
        return contentType;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public long getPutdate() {
        return putdate;
    }

    public long getRandomNum() {
        return randomNum;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getContentSourceName() {
        return contentSourceName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getType() {
        return type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

}
