package com.wenba.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * drools_rule
 * @author 
 */
public class DroolsRuleParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 规则id
     */
    private Integer droolsId;

    /**
     * 规则版本
     */
    private String ruleVers;

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




}