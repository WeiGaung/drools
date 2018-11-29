package com.wenba.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * drools_rule
 * @author 
 */
public class DroolsRule implements Serializable {
    /**
     * ID
     */
    private Integer id;

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
     * 规则序号
     */
    private Integer ruleIndex;

    /**
     * 具体规则名称
     */
    private String ruleDetailName;

    /**
     * 规则是否不允许多次循环执行
     */
    private Boolean noLoop;

    /**
     * 避免因某些Fact对象被修改而使已执行过的规则再次被激活执行
     */
    private Boolean lockOnActive;

    /**
     * 规则执行的优先级
     */
    private Byte salience;

    /**
     * 返回值类型
     */
    private Integer pTypeId;

    /**
     * 规则条件
     */
    private String ruleIf;

    /**
     * 规则条件结果
     */
    private String ruleThen;

    /**
     * 规则状态(是否启用): 1 是 0 否
     */
    private Byte ruleStatus;

    private Date createTime;

    private Date updateTime;

    /**
     * 扩展字段
     */
    private String ext;


    /**
     * 规则名称
     */
    private String droolsName;

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

    public Integer getRuleIndex() {
        return ruleIndex;
    }

    public void setRuleIndex(Integer ruleIndex) {
        this.ruleIndex = ruleIndex;
    }

    public String getRuleDetailName() {
        return ruleDetailName;
    }

    public void setRuleDetailName(String ruleDetailName) {
        this.ruleDetailName = ruleDetailName;
    }

    public Boolean getNoLoop() {
        return noLoop;
    }

    public void setNoLoop(Boolean noLoop) {
        this.noLoop = noLoop;
    }

    public Boolean getLockOnActive() {
        return lockOnActive;
    }

    public void setLockOnActive(Boolean lockOnActive) {
        this.lockOnActive = lockOnActive;
    }

    public Byte getSalience() {
        return salience;
    }

    public void setSalience(Byte salience) {
        this.salience = salience;
    }

    public Integer getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(Integer pTypeId) {
        this.pTypeId = pTypeId;
    }

    public String getRuleIf() {
        return ruleIf;
    }

    public void setRuleIf(String ruleIf) {
        this.ruleIf = ruleIf;
    }

    public String getRuleThen() {
        return ruleThen;
    }

    public void setRuleThen(String ruleThen) {
        this.ruleThen = ruleThen;
    }

    public Byte getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(Byte ruleStatus) {
        this.ruleStatus = ruleStatus;
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

    public String getDroolsName() {
        return droolsName;
    }

    public void setDroolsName(String droolsName) {
        this.droolsName = droolsName;
    }

    @Override
    public String toString() {
        return "DroolsRule{" +
                "id=" + id +
                ", droolsId=" + droolsId +
                ", droolsVers='" + droolsVers + '\'' +
                ", ruleIndex=" + ruleIndex +
                ", ruleDetailName='" + ruleDetailName + '\'' +
                ", noLoop=" + noLoop +
                ", lockOnActive=" + lockOnActive +
                ", salience=" + salience +
                ", pTypeId=" + pTypeId +
                ", ruleIf='" + ruleIf + '\'' +
                ", ruleThen='" + ruleThen + '\'' +
                ", ruleStatus=" + ruleStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ext='" + ext + '\'' +
                ", droolsName='" + droolsName + '\'' +
                '}';
    }
}