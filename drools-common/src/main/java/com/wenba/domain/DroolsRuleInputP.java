package com.wenba.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * drools_rule_input_p
 * @author 
 */
public class DroolsRuleInputP implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * drools_info的规则id
     */
    private Integer droolsId;

    /**
     * 规则版本
     */
    private String droolsVers;

    /**
     * 输入参数位置
     */
    private Integer inputPLoc;

    /**
     * 输入参数状态(是否启用): 1 是 0 否'
     */
    private Byte inputPStatus;

    /**
     * 输入参数类型id
     */
    private Integer pTypeId;

    private Date createTime;

    private Date updateTime;

    /**
     * 扩展字段
     */
    private String ext;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getInputPLoc() {
        return inputPLoc;
    }

    public void setInputPLoc(Integer inputPLoc) {
        this.inputPLoc = inputPLoc;
    }

    public Byte getInputPStatus() {
        return inputPStatus;
    }

    public void setInputPStatus(Byte inputPStatus) {
        this.inputPStatus = inputPStatus;
    }

    public Integer getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(Integer pTypeId) {
        this.pTypeId = pTypeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}