package com.atguigu.bibiq.find.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/24 0024.
 * 功能:小黑屋公示动态
 */

public class BlackHouseDynamicBean {

    @JSONField(name = "code")
    private int _$Code139; // FIXME check this code
    private long ts;
    private int total;
    private List<DataBean> data;

    public int get_$Code139() {
        return _$Code139;
    }

    public void set_$Code139(int _$Code139) {
        this._$Code139 = _$Code139;
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
         * id : 3854
         * uid : 39267822
         * uname : 好名字都让丑比取了
         * face : http://i0.hdslb.com/bfs/face/d670b41713c2f6e5aa816c3d7c599edc7d420859.jpg
         * originContentModify : www.****.com    www.*****.com这两个网站还存活。。(°∀°)ﾉ(〜￣△￣)〜(〜￣△￣)〜
         * originType : 1
         * reasonType : 13
         * punishTime : 1490303724000
         * punishType : 2
         * moralNum : 0
         * blockedDays : 0
         * blockedForever : true
         * commentSum : 25
         * punishTitle : 在评论中发布色情信息
         * reasonTypeName : 发布色情信息
         */

        private int id;
        private int uid;
        private String uname;
        private String face;
        private String originContentModify;
        private int originType;
        private int reasonType;
        private long punishTime;
        private int punishType;
        private int moralNum;
        private int blockedDays;
        private boolean blockedForever;
        private int commentSum;
        private String punishTitle;
        private String reasonTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getOriginContentModify() {
            return originContentModify;
        }

        public void setOriginContentModify(String originContentModify) {
            this.originContentModify = originContentModify;
        }

        public int getOriginType() {
            return originType;
        }

        public void setOriginType(int originType) {
            this.originType = originType;
        }

        public int getReasonType() {
            return reasonType;
        }

        public void setReasonType(int reasonType) {
            this.reasonType = reasonType;
        }

        public long getPunishTime() {
            return punishTime;
        }

        public void setPunishTime(long punishTime) {
            this.punishTime = punishTime;
        }

        public int getPunishType() {
            return punishType;
        }

        public void setPunishType(int punishType) {
            this.punishType = punishType;
        }

        public int getMoralNum() {
            return moralNum;
        }

        public void setMoralNum(int moralNum) {
            this.moralNum = moralNum;
        }

        public int getBlockedDays() {
            return blockedDays;
        }

        public void setBlockedDays(int blockedDays) {
            this.blockedDays = blockedDays;
        }

        public boolean isBlockedForever() {
            return blockedForever;
        }

        public void setBlockedForever(boolean blockedForever) {
            this.blockedForever = blockedForever;
        }

        public int getCommentSum() {
            return commentSum;
        }

        public void setCommentSum(int commentSum) {
            this.commentSum = commentSum;
        }

        public String getPunishTitle() {
            return punishTitle;
        }

        public void setPunishTitle(String punishTitle) {
            this.punishTitle = punishTitle;
        }

        public String getReasonTypeName() {
            return reasonTypeName;
        }

        public void setReasonTypeName(String reasonTypeName) {
            this.reasonTypeName = reasonTypeName;
        }
    }
}
