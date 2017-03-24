package com.atguigu.bibiq.find.bean;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:活动中心
 */

public class CentreBean {

    /**
     * code : 0
     * list : [{"title":"Vsinger创作征集大赛","cover":"http://activity.hdslb.com/blackboard/cover/k7j54jyl9r.jpg","link":"http://www.bilibili.com/blackboard/activity-vsinger2017-m.html","state":0},{"title":"拜年祭之哔哩哔哩星球","cover":"http://activity.hdslb.com/blackboard/cover/activity-3987/2233_960300.jpg","link":"http://planet2017.bilibili.com/h5/h5.html","state":1},{"title":"拜年祭之民族乐器演奏赛","cover":"http://activity.hdslb.com/blackboard/cover/activity-631/960_300-1.jpg","link":"http://www.bilibili.com/blackboard/activity_17bnj_folk_m.html","state":1},{"title":"拜年祭之来祈愿吧","cover":"http://activity.hdslb.com/blackboard/cover/activity-581/960X300.jpg","link":"http://www.bilibili.com/blackboard/activity_17bnj_pray_m.html","state":1},{"title":"拜年祭之元宵会h5","cover":"http://activity.hdslb.com/blackboard/cover/activity-681/bilibili%E6%8B%9C%E5%B9%B4%E7%A5%AD%E4%B9%8B%E5%85%83%E5%AE%B5%E4%BC%9A.jpg","link":"http://www.bilibili.com/blackboard/activity-2017lantern-m.html","state":1},{"title":"bilibili moe 2016 动画角色人气大赏 \u2014\u2014 日本动画场","cover":"http://i0.hdslb.com/bfs/archive/73a9f6882d340735f3cae258f6974adf5d0aa8d6.png","link":"http://bangumi.bilibili.com/moe/2016/jp/mobile","state":1},{"title":"bilibili moe 2016 动画角色人气大赏 \u2014\u2014 国产动画场","cover":"http://i0.hdslb.com/bfs/active/7151e449a4d670e8d26db5e3359a0e36c860dafd.png","link":"http://bangumi.bilibili.com/moe/2016/cn/mobile#!/","state":1},{"title":"bilibili MMD大赛2016","cover":"http://i0.hdslb.com/bfs/active/d5d8842836eb307c5c9ced71adf4bd684cbe6386.jpg","link":"http://www.bilibili.com/blackboard/activity-2016MMD-m.html","state":1},{"title":"搭配擂台 \u2014 卫衣篇","cover":"http://i0.hdslb.com/bfs/active/40e8f91b3da411eaec3d981c833a155a8e7b8feb.jpg","link":"http://www.bilibili.com/blackboard/activity-outfit01-m.html","state":1},{"title":"番剧异闻录·魔法少女的月下游行","cover":"http://i0.hdslb.com/bfs/active/c796733555e527d46de0287d96cd3982c46660b2.jpg","link":"http://www.bilibili.com/blackboard/activity-madoka-m.html","state":1},{"title":"「大鱼海棠」剧情重生Project","cover":"http://i0.hdslb.com/bfs/active/64c67b010823152cb157d1d80952464e83bdf697.jpg","link":"http://www.bilibili.com/html/activity-dayuhaitang-m.html","state":1},{"title":"[夏日延长线2]本家争霸赛","cover":"http://i0.hdslb.com/bfs/active/a384124b9e6aa841784a549390db3abc1d5d21ca.jpg","link":"http://www.bilibili.com/html/activity-VocaloidCover-m.html","state":1},{"title":"中秋来看天降月饼","cover":"http://i0.hdslb.com/bfs/active/75f054b8918bb6a3afb1be5231105ad29763a42c.jpg","link":"http://www.bilibili.com/html/activity-midautumn2016.html","state":1},{"title":"TGS2016之去了没去","cover":"http://i0.hdslb.com/bfs/active/d9b5fdc45e49597c2c942801ca6b033a951a7071.jpg","link":"http://www.bilibili.com/html/activity-TGS2016-m.html","state":1},{"title":"2233的一天","cover":"http://i0.hdslb.com/bfs/active/ac2f82614c4e27c2f20dce847322d91b19baf7a8.jpg","link":"http://www.bilibili.com/html/activity-2233birthday-m.html","state":1},{"title":"噗尼噗尼之小电视来袭","cover":"http://i0.hdslb.com/bfs/active/b83a22f4933117976392eca010516fd150fb15a5.jpg","link":"http://www.bilibili.com/html/activity-punipuni3-m.html","state":1},{"title":"「我来说电影」影评杂谈大赛","cover":"http://i0.hdslb.com/bfs/active/eaa9bccc90431978cddd2aabf4dd0c5a74365e4c.jpg","link":"http://www.bilibili.com/html/activity-cinecism-m.html","state":1},{"title":"【番剧异闻录】秘技·我有姿势我自豪","cover":"http://i0.hdslb.com/bfs/active/8c78b1cc4026a0fd2290d9df0964ce70f06ddd8b.jpg","link":"http://www.bilibili.com/html/activity-coolest-m.html","state":1},{"title":"2016夏夜鬼畜大赏","cover":"http://i0.hdslb.com/bfs/active/8f4abff9b7a9f21519e9e45f79afd8bd02799b63.jpg","link":"http://www.bilibili.com/html/activity-Kichiku2016-m.html","state":1}]
     * total : 39
     * pages : 2
     */

    private int code;
    private int total;
    private int pages;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : Vsinger创作征集大赛
         * cover : http://activity.hdslb.com/blackboard/cover/k7j54jyl9r.jpg
         * link : http://www.bilibili.com/blackboard/activity-vsinger2017-m.html
         * state : 0
         */

        private String title;
        private String cover;
        private String link;
        private int state;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
