package com.hansheng.simplenba.nba.model;

import com.hansheng.simplenba.beans.NbaBean;
import com.hansheng.simplenba.beans.NbaDetailBean;
import com.hansheng.simplenba.nba.NewsJsonUtils;
import com.hansheng.simplenba.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by hansheng on 2016/3/26.
 */
public class NbaModelImpl implements NbaModel {

    /**
     * 加载新闻列表
     * @param url
     * @param listener
     */
    @Override
    public void loadNews(String url,int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response){
                List<NbaBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response);
                System.out.println("newsBeanList="+newsBeanList);
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 加载新闻详情
     * @param url
     * @param listener
     */
    @Override
    public void loadNewsDetail(final String url, final OnLoadNewsDetailListener listener) {
     //   String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NbaDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response);
               listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
       OkHttpUtils.get(url, loadNewsCallback);
    }



//    private String getDetailUrl(String docId) {
//        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
//        sb.append(docId).append(Urls.END_DETAIL_URL);
//        return sb.toString();
//    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NbaBean> list);
        void onFailure(String msg, Exception e);
    }

    public interface OnLoadNewsDetailListener {
        void onSuccess(NbaDetailBean newsDetailBean);
        void onFailure(String msg, Exception e);
    }

}
