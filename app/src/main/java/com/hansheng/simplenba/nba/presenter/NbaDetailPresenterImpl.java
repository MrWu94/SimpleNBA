package com.hansheng.simplenba.nba.presenter;

import android.content.Context;

import com.hansheng.simplenba.beans.NbaDetailBean;
import com.hansheng.simplenba.nba.model.NbaModel;
import com.hansheng.simplenba.nba.model.NbaModelImpl;
import com.hansheng.simplenba.nba.view.NbaDetailView;

/**
 * Created by hansheng on 2016/3/28.
 */
public class NbaDetailPresenterImpl implements NbaDetailPresenter, NbaModelImpl.OnLoadNewsDetailListener {

    private Context mContent;
    private NbaDetailView mNewsDetailView;
    private NbaModel mNewsModel;

    public NbaDetailPresenterImpl(Context mContent, NbaDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NbaModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }


    @Override
    public void onSuccess(NbaDetailBean newsDetailBean) {
        if(newsDetailBean != null) {
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getContent());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}

