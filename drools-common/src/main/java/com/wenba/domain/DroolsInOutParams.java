package com.wenba.domain;

import java.io.Serializable;

public class DroolsInOutParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 输入名称
     */
    private String pName;

    /**
     * 参数类型
     */
    private String pType;

    /**
     * 参数来源
     */
    private Integer pSource;

   public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public Integer getpSource() {
        return pSource;
    }

    public void setpSource(Integer pSource) {
        this.pSource = pSource;
    }

    @Override
    public String toString() {
        return "DroolsInOutParams{" +
                "pName='" + pName + '\'' +
                ", pType='" + pType + '\'' +
                ", pSource='" + pSource + '\'' +
                '}';
    }
}
