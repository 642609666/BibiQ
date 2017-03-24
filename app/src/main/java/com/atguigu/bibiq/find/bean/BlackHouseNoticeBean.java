package com.atguigu.bibiq.find.bean;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:小黑屋官方公告
 */

public class BlackHouseNoticeBean {

    /**
     * code : 0
     * data : [{"id":8,"url":"http://www.bilibili.com/blackboard/activity-pbctb.html","title":"弹幕阳光计划第一弹","subTitle":"屏蔽词可在电脑手机同步啦！","stickStatus":true,"ctime":1489661184000},{"id":4,"url":"","title":"小黑屋处罚条例v1.0","subTitle":"为了建立良好的视听环境和用户体验，规范网站的内容形式...","stickStatus":true,"ctime":1487340789000},{"id":6,"url":"","title":"\u201c小黑屋\u201d功能上线公测，同步恢复100题转正考试","subTitle":"为了持续维护和改善社区氛围，我们将于即日起对\u201c小黑屋\u201d...","stickStatus":false,"ctime":1487670662000},{"id":2,"url":"","title":"关于近期部分用户遭遇弹窗、错误解析等问题的公告","subTitle":"近期，我们接到反馈，某些地区...","stickStatus":false,"ctime":1487305813000},{"id":1,"url":"","title":"新版评论功能上线","subTitle":"随着加入哔哩哔哩这个大家庭的用户越来越多...","stickStatus":false,"ctime":1487233864000}]
     * ts : 1490335092158
     * total : 5
     */

    private int code;
    private long ts;
    private int total;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8
         * url : http://www.bilibili.com/blackboard/activity-pbctb.html
         * title : 弹幕阳光计划第一弹
         * subTitle : 屏蔽词可在电脑手机同步啦！
         * stickStatus : true
         * ctime : 1489661184000
         */

        private int id;
        private String url;
        private String title;
        private String subTitle;
        private boolean stickStatus;
        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public boolean isStickStatus() {
            return stickStatus;
        }

        public void setStickStatus(boolean stickStatus) {
            this.stickStatus = stickStatus;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }
}
