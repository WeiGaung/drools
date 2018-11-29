package com.wenba.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * drools_rule
 * @author 
 */
public class DroolsRuleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规则id
     */
    @NotNull
    private Integer droolsId;

    /**
     * 规则版本
     */
    @NotBlank
    private String droolsVers;

    /**
     * 规则名称
     */
    private String droolsName;


    public Integer getDroolsId() {
        return droolsId;
    }

    public void setDroolsId(Integer droolsId) {
        this.droolsId = droolsId;
    }

    public String getDroolsVers() {
        return droolsVers;
    }

    public void setDroolsVers(String droolsVers) {
        this.droolsVers = droolsVers;
    }

    public String getDroolsName() {
        return droolsName;
    }

    public void setDroolsName(String droolsName) {
        this.droolsName = droolsName;
    }


    @Override
    public String toString() {
        return "DroolsRuleBean{" +
                "droolsId=" + droolsId +
                ", droolsVers='" + droolsVers + '\'' +
                ", droolsName='" + droolsName + '\'' +
                '}';
    }
}