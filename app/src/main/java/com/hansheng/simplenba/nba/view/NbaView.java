package com.hansheng.simplenba.nba.view;

import com.hansheng.simplenba.beans.NbaBean;

import java.util.List;

/**
 * Created by hansheng on 2016/3/26.
 */
public interface NbaView {
    void showProgress();

    void addNews(List<NbaBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
