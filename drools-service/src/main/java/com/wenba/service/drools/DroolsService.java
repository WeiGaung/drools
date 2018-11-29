package com.wenba.service.drools;


import com.wenba.domain.*;
import com.wenba.utils.base.DataResult;

import java.util.List;
import java.util.Map;

public interface DroolsService {

    List<Map<String, DataResult<String>>> createDroolsRule(DroolsRule dr);

    List<DataResult<String>> tranDroolsRuleObj(List<Object> list);
    List<DataResult<String>> tranDroolsRule(List<InvestSendIntegralv1> list);


    /*
     *   生成规则版本实体类
     */
    boolean createDroolsVersBean(List<DroolsRuleBean> pList);
    Map<Boolean,Map<String,List<Map<Integer,String>>>> createDroolsVersBean(DroolsRuleBean drb);

    /*
     *   drools_rule_p_type
     *   新增参数信息
     */
    boolean insertParamsInfo(List<DroolsRulePType> listDrpt);
    boolean insertParamsInfoSin(DroolsRulePType drpt);

    //校验参数类型是否已存在
    int checkParamsInfo(String getpType);

    /*
     *   drools_rule_p_type
     *   更新参数
     */
    int updateParamsInfo(List<DroolsRulePType> listDrpt);
    int updateParamsInfoSin(DroolsRulePType drpt);


    /*
     *   drools_rule_p_type
     *  查询所有的参数信息id
     */
    List<DroolsRulePType> selectParamsInfoIds();


    /*
     *   drools_rule_p_type
     *  查询所有的参数信息
     */
    int selectParamsInfoListNum(DroolsRulePType drpt);
    List<DroolsRulePType> selectParamsInfo(List<Integer> listIds);
    List<DroolsRulePType> selectParamsInfoList(DroolsRulePType drpt);



    /*
     *   drools_info
     *   新增规则信息
     */
    boolean insertDroolsInfo(List<DroolsInfo> listDi);
    boolean insertDroolsInfoSin(DroolsInfo di);


    /*
     *   drools_info
     *   更新规则信息
     */
    int updateDroolsInfoSin(DroolsInfo di);
    int updateDroolsInfo(List<DroolsInfo> listDi);



    /*
     *   drools_info
     *   查询所有的规则信息id
     */
    List<DroolsInfo> selectDroolsInfoIds();


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    int selectDroolsInfoListNum(DroolsInfo di);
    DroolsInfo selectDroolsInfoSin(int id);
    List<DroolsInfo> selectDroolsInfoList(DroolsInfo di);

    /*
     *   drools_info
     *   查询所有的规则信息
     */
    List<DroolsInfo> selectDroolsInfo(List<Integer> listI);


    /*
     *   drools_vers
     *   新增规则版本信息
     */
    boolean insertDroolsVers(List<DroolsVers> listDv);

    //校验规则版本是否已存在
    int checkDroolsVers(Integer droolsId);


    /*
     *   drools_info
     *   更新规则版本信息
     */
    int updateDroolsVers(List<DroolsVers> listDv);


    /*
     *   drools_vers
     *   查询所有规则版本信息
     */
    List<DroolsVers> selectDroolsVers(List<DroolsVers> listDv);

    List<DroolsIdVers> selectDroolsInfoIdName();
    List<DroolsIdVers> selectDroolsInfoVers();


    /*
     *   drools_rule_input_p
     *   新增输入参数信息
     */
    boolean insertDroolsRuleInputP(List<DroolsRuleInputP> listDrip);

    //校验输入参数的规则和版本是否已存在
    int checkDroolsRuleInputP(Integer droolsId, String droolsVers);

    //校验输入参数的规则和版本及参数类型是否已存在
    int checkCreateDroolsRuleInputP(Integer droolsId, String droolsVers, Integer pTypeId);

    /*
     *   drools_rule_input_p
     *  更新输入参数信息
     */
    int updateDroolsRuleInputP(List<DroolsRuleInputP> listDrip);


    /*
     *   drools_rule_input_p
     *   查询所有的输入参数信息
     */
    List<DroolsRuleInputP> selectDroolsRuleInputP(List<DroolsRuleInputP> listDrip);


    /*
     *   drools_rule
     *   新增具体规则信息
     */
    boolean insertDroolsRule(List<DroolsRule> listDr);


    /*
     *   drools_rule
     *   更新具体规则信
     */
    int updateDroolsRule(List<DroolsRule> listDr);


    /*
     *   drools_rule
     *   查询所有具体规则信息
     */
    List<DroolsRule> selectDroolsRule(List<DroolsRule> listDr);


    //检验规则名称是否存在
    int checkDroolsInfo(String droolsName);

    //校验实体类是否存在
    int checkDroolsRuleBean(Integer droolsId, String droolsVers);

    //删除规则信息
    int deleteDroolsInfoSin(int id);


}
