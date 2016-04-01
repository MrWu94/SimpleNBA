package com.hansheng.simplenba.nba.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.simplenba.R;
import com.hansheng.simplenba.beans.NbaBean;
import com.hansheng.simplenba.beans.NextId;
import com.hansheng.simplenba.nba.NbaAdapter;
import com.hansheng.simplenba.nba.presenter.NbaPresenter;
import com.hansheng.simplenba.nba.presenter.NewsPresenterImpl;
import com.hansheng.simplenba.nba.view.NbaView;
import com.hansheng.simplenba.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/3/26.
 */
public class NewListFragment extends Fragment implements NbaView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NbaAdapter mAdapter;
    private List<NbaBean> mData;
    private NbaPresenter mNewsPresenter;
    private NextId next= new NextId();

    private String mType = NbasFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    public static NewListFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewListFragment fragment = new NewListFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getString("type");
        System.out.println("mType=="+mType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NbaAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                LogUtils.d(TAG, "loading more data");

              //  mNewsPresenter.loadNews(mType,"");
            }
        }
    };

    private NbaAdapter.OnItemClickListener mOnItemClickListener = new NbaAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NbaBean news = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NbaDetailActivity.class);
            intent.putExtra("news", news);

            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));

                    ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void addNews(List<NbaBean> newsList) {
        mAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<NbaBean>();
        }
        mData.addAll(newsList);

        mAdapter.setmDate(mData);

        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if (pageIndex == 0) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mData != null) {
            mData.clear();
        }
        System.out.println("mType====="+mType);
          mNewsPresenter.loadNews(mType,"");
       // mNewsPresenter.loadNews("http://nbaplus.sinaapp.com/api/v1.0/news/update",this);

    }

}

