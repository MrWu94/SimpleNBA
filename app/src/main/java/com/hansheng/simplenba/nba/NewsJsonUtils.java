package com.hansheng.simplenba.nba;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hansheng.simplenba.beans.NbaBean;
import com.hansheng.simplenba.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * hansheng
 */
public class NewsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为新闻列表对象
     *
     * @param res
     * @param
     * @return
     */
    public static List<NbaBean> readJsonNewsBeans(String res) {
        List<NbaBean> beans = new ArrayList<NbaBean>();
        JsonParser parser = new JsonParser();
        JsonObject jsonobj = parser.parse(res).getAsJsonObject();
        System.out.println("jsonobj=="+jsonobj.toString());
        JsonElement jsonElement = jsonobj.get("newslist");
        if (jsonElement == null) {
            return null;
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (int i = 1; i < jsonArray.size(); i++) {
            JsonObject jo = jsonArray.get(i).getAsJsonObject();
            NbaBean news = JsonUtils.deserialize(jo, NbaBean.class);
            beans.add(news);
        }
        return beans;
    }
}




