package com.wenba.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * drools_vers
 * @author 
 */
public class DroolsVers implements Serializable {
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
     * 规则版本描述
     */
    private String droolsVersDesc;

    /**
     * 规则版本状态(是否启用): 1 是 0 否'
     */
    private Byte droolsVersStatus;

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

    public String getDroolsVersDesc() {
        return droolsVersDesc;
    }

    public void setDroolsVersDesc(String droolsVersDesc) {
        this.droolsVersDesc = droolsVersDesc;
    }

    public Byte getDroolsVersStatus() {
        return droolsVersStatus;
    }

    public void setDroolsVersStatus(Byte droolsVersStatus) {
        this.droolsVersStatus = droolsVersStatus;
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


    @Override
    public String toString() {
        return "DroolsVers{" +
                "id=" + id +
                ", droolsId=" + droolsId +
                ", droolsVers='" + droolsVers + '\'' +
                ", droolsVersDesc='" + droolsVersDesc + '\'' +
                ", droolsVersStatus=" + droolsVersStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ext='" + ext + '\'' +
                '}';
    }
}