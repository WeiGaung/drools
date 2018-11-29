package com.wenba.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * drools_info
 * @author 
 */
public class DroolsInfo implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 规则名称
     */
    private String droolsName;

    /**
     * 规则描述
     */
    private String droolsDesc;

    /**
     * 规则条件状态(是否启用): 1 是 0 否'
     */
    private Byte droolsStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    //@JsonFormat(timezone = "GMT-4", pattern = "yyyy-MM-dd")
    private String createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    //@JsonFormat(timezone = "GMT-4", pattern = "yyyy-MM-dd")
    private String updateTime;


    private String create_start;

    private String create_end;


    private int pageNum;

    private int pageSize;

    private String createDate;

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

    public String getDroolsName() {
        return droolsName;
    }

    public void setDroolsName(String droolsName) {
        this.droolsName = droolsName;
    }

    public String getDroolsDesc() {
        return droolsDesc;
    }

    public void setDroolsDesc(String droolsDesc) {
        this.droolsDesc = droolsDesc;
    }

    public Byte getDroolsStatus() {
        return droolsStatus;
    }

    public void setDroolsStatus(Byte droolsStatus) {
        this.droolsStatus = droolsStatus;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreate_start() {
        return create_start;
    }

    public void setCreate_start(String create_start) {
        this.create_start = create_start;
    }

    public String getCreate_end() {
        return create_end;
    }

    public void setCreate_end(String create_end) {
        this.create_end = create_end;
    }

    @Override
    public String toString() {
        return "DroolsInfo{" +
                "id=" + id +
                ", droolsName='" + droolsName + '\'' +
                ", droolsDesc='" + droolsDesc + '\'' +
                ", droolsStatus=" + droolsStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", create_start=" + create_start +
                ", create_end=" + create_end +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", createDate='" + createDate + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}