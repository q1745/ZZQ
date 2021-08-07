package com.example.usercenter.bean;

import com.example.common.app.MyApplication;

import java.util.List;

/**
 * @author:ZZQ
 * @date:2021/7/31
 */
public class RecomBean {
    /**
     * code : 200
     * data : [{"category_id":16,"category_name":"女装/女士精品","id":5470,"parent_id":0},{"category_id":30,"category_name":"男装","id":5471,"parent_id":0},{"category_id":50011740,"category_name":"流行男鞋","id":5490,"parent_id":0},{"category_id":50012100,"category_name":"生活电器","id":5513,"parent_id":0},{"category_id":50010388,"category_name":"运动鞋","id":5514,"parent_id":0},{"category_id":1512,"category_name":"手机","id":5521,"parent_id":0},{"category_id":1101,"category_name":"笔记本电脑","id":5525,"parent_id":0}]
     * msg : 操作成功
     */

    private int code;
    private String msg;
    /**
     * category_id : 16
     * category_name : 女装/女士精品
     * id : 5470
     * parent_id : 0
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int category_id;
        private String category_name;
        private int id;
        private int parent_id;


        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }
    }
}
