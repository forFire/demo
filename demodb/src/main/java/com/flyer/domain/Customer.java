package com.flyer.domain;

import java.io.Serializable;

public class Customer implements Serializable {
    private Integer id;

    /**
    * 姓名
     */
    private String name;

    /**
    * 手机号
     */
    private String phone;

    private String sex;

    /**
    * 身份证
     */
    private String idCard;

    private String address;

    private String idCardImg;

    private static final long serialVersionUID = 1L;

    public Customer(Integer id, String name, String phone, String sex, String idCard, String address, String idCardImg) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.idCard = idCard;
        this.address = address;
        this.idCardImg = idCardImg;
    }

    public Customer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg == null ? null : idCardImg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", idCard=").append(idCard);
        sb.append(", address=").append(address);
        sb.append(", idCardImg=").append(idCardImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}