package com.wenba.domain;

import java.io.Serializable;

/**
 * drools_vers
 * @author 
 */
public class DroolsIdVers implements Serializable {



    private static final long serialVersionUID = 1L;

    /**
     * drools_info的规则id
     */
    private Integer droolsId;

    /**
     * 规则名称
     */
    private String droolsName;

    /**
     * 规则版本
     */
    private String droolsVers;


    public Integer getDroolsId() {
        return droolsId;
    }

    public void setDroolsId(Integer droolsId) {
        this.droolsId = droolsId;
    }

    public String getDroolsName() {
        return droolsName;
    }

    public void setDroolsName(String droolsName) {
        this.droolsName = droolsName;
    }

    public String getDroolsVers() {
        return droolsVers;
    }

    public void setDroolsVers(String droolsVers) {
        this.droolsVers = droolsVers;
    }

    @Override
    public String toString() {
        return "DroolsIdVers{" +
                "droolsId=" + droolsId +
                ", droolsName='" + droolsName + '\'' +
                ", droolsVers='" + droolsVers + '\'' +
                '}';
    }
}