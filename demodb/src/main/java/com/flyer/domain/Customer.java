package com.flyer.domain;

import java.io.Serializable;

public class Customer implements Serializable {
    private Integer fId;

    private String fName;

    private String fPhone;

    private String fSex;

    private String fCardId;

    private static final long serialVersionUID = 1L;

    public Customer(Integer fId, String fName, String fPhone, String fSex, String fCardId) {
        this.fId = fId;
        this.fName = fName;
        this.fPhone = fPhone;
        this.fSex = fSex;
        this.fCardId = fCardId;
    }

    public Customer() {
        super();
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone == null ? null : fPhone.trim();
    }

    public String getfSex() {
        return fSex;
    }

    public void setfSex(String fSex) {
        this.fSex = fSex == null ? null : fSex.trim();
    }

    public String getfCardId() {
        return fCardId;
    }

    public void setfCardId(String fCardId) {
        this.fCardId = fCardId == null ? null : fCardId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fId=").append(fId);
        sb.append(", fName=").append(fName);
        sb.append(", fPhone=").append(fPhone);
        sb.append(", fSex=").append(fSex);
        sb.append(", fCardId=").append(fCardId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}