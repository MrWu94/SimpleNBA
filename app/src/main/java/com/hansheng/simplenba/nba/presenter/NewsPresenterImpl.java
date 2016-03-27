package com.hansheng.simplenba.nba.presenter;

import com.hansheng.simplenba.beans.NbaBean;
import com.hansheng.simplenba.commons.Urls;
import com.hansheng.simplenba.nba.model.NbaModel;
import com.hansheng.simplenba.nba.model.NbaModelImpl;
import com.hansheng.simplenba.nba.view.NbaView;
import com.hansheng.simplenba.nba.widget.NbasFragment;
import com.hansheng.simplenba.utils.LogUtils;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public class NewsPresenterImpl implements NbaPresenter, NbaModelImpl.OnLoadNewsListListener {

    private static final String TAG = "NewsPresenterImpl";

    private NbaView mNewsView;
    private NbaModel mNewsModel;

    public NewsPresenterImpl(NbaView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NbaModelImpl();
    }

    @Override
    public void loadNews() {


    }

    @Override
    public void loadNews(int type) {

        String url = getUrl(type);
        LogUtils.d(TAG, url);
        System.out.println("url="+url.toString());
        mNewsModel.loadNews(url,type,this);
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @return
     */
    private String getUrl(int type) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NbasFragment.NEWS_TYPE_TOP:
                sb.append(Urls.NBA);
                break;
            case NbasFragment.NEWS_TYPE_NBA:
                sb.append(Urls.BLOG);
                break;
            default:
                break;
        }
        return sb.toString();
    }



    @Override
    public void onSuccess(List<NbaBean> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
