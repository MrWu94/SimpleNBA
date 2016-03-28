package com.hansheng.simplenba.nba.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hansheng.simplenba.R;
import com.hansheng.simplenba.beans.NbaBean;
import com.hansheng.simplenba.nba.presenter.NbaDetailPresenter;
import com.hansheng.simplenba.nba.presenter.NbaDetailPresenterImpl;
import com.hansheng.simplenba.nba.view.NbaDetailView;
import com.hansheng.simplenba.utils.ImageLoaderUtils;
import com.hansheng.simplenba.utils.ToolsUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by hansheng on 2016/3/28.
 */
public class NbaDetailActivity extends SwipeBackActivity implements NbaDetailView {

    private NbaBean mNews;
    private HtmlTextView mTVNewsContent;
    private NbaDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTVNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        mNews = (NbaBean) getIntent().getSerializableExtra("news");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getTitle());


        List<String> img= mNews.getImgUrlList();
        if(img.isEmpty()){
            return;
        }
        else{
            System.out.println("img========"+img.get(0)+ mNews.getTitle()+mNews.getDescription()+mNews.getImgUrlList().toString());
            ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), img.get(0));
        }


        mNewsDetailPresenter = new NbaDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(mNews.getArticleUrl());
    }

    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        mTVNewsContent.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
