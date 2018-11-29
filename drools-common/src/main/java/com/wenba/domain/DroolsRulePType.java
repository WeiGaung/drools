package com.wenba.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * drools_rule_p_type
 * @author 
 */
public class DroolsRulePType implements Serializable {
    /**
     * 参数类型id(输入/输出)
     */
    private Integer pTypeId;

    private Integer droolsId;

    /**
     * 规则名称
     */
    private String droolsName;

    /**
     * 规则版本
     */
    private String droolsVers;

    /**
     * 参数类型
     */
    private String pType;

    /**
     * 参数名称
     */
    private String pName;

    /**
     * 参数来源 1:入参 2:出参
     */
    private Byte pSource;

    /**
     * 实体类名称
     */
    private String pBeanName;

    /**
     * 参数状态(是否启用): 1 是 0 否'
     */
    private Byte pTypeStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private String createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private String updateTime;

    private int pageNum;

    private int pageSize;

    /**
     * 扩展字段
     */
    private String ext;

    private static final long serialVersionUID = 1L;

    public Integer getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(Integer pTypeId) {
        this.pTypeId = pTypeId;
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

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Byte getpSource() {
        return pSource;
    }

    public void setpSource(Byte pSource) {
        this.pSource = pSource;
    }

    public String getpBeanName() {
        return pBeanName;
    }

    public void setpBeanName(String pBeanName) {
        this.pBeanName = pBeanName;
    }

    public Byte getpTypeStatus() {
        return pTypeStatus;
    }

    public void setpTypeStatus(Byte pTypeStatus) {
        this.pTypeStatus = pTypeStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public String getDroolsName() {
        return droolsName;
    }

    public void setDroolsName(String droolsName) {
        this.droolsName = droolsName;
    }

    @Override
    public String toString() {
        return "DroolsRulePType{" +
                "pTypeId=" + pTypeId +
                ", droolsId=" + droolsId +
                ", droolsName='" + droolsName + '\'' +
                ", droolsVers='" + droolsVers + '\'' +
                ", pType='" + pType + '\'' +
                ", pName='" + pName + '\'' +
                ", pSource=" + pSource +
                ", pBeanName='" + pBeanName + '\'' +
                ", pTypeStatus=" + pTypeStatus +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", ext='" + ext + '\'' +
                '}';
    }
}