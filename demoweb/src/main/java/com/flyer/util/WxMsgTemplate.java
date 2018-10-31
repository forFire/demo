package com.sinoiov.lhjh.tool.bean.bo;

import java.io.Serializable;

/**
 * Created by td on 2018/10/18.
 */
public class WxMsgTemplate<T> implements Serializable {
    private String touser;// '触发帐号的opened',
    private String template_id;// '模版id',
    private String page;// '点击模版卡片的跳转页面',
    private String form_id;// 'form_id或者prepay_id',
    private T data;//数据
    private String emphasis_keyword;//加粗字段

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
