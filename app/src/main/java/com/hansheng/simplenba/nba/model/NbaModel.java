package com.hansheng.simplenba.nba.model;

/**
 * Created by hansheng on 2016/3/26.
 */
public interface NbaModel {
    void loadNews(String url,int type, NbaModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, NbaModelImpl.OnLoadNewsDetailListener listener);
}
