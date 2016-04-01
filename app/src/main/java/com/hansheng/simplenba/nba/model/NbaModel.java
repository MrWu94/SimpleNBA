package com.hansheng.simplenba.nba.model;

/**
 * Created by hansheng on 2016/3/26.
 */
public interface NbaModel {
    void loadNews(String url,String type, String bas,NbaModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, NbaModelImpl.OnLoadNewsDetailListener listener);
}
