package com.wenba.reposity.drools;

import com.wenba.domain.DroolsRuleInputP;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroolsRuleInputPDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DroolsRuleInputP record);

    int insertSelective(DroolsRuleInputP record);

    DroolsRuleInputP selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DroolsRuleInputP record);

    int updateByPrimaryKey(DroolsRuleInputP record);



    /*
     *   drools_rule_input_p
     *   新增输入参数信息
     */
    boolean insertDroolsRuleInputP(List<DroolsRuleInputP> listDrip);


    //校验输入参数的规则和版本是否已存在
    int checkDroolsRuleInputP(@Param("droolsId") Integer droolsId,@Param("droolsVers") String droolsVers);


    //校验输入参数的规则和版本及参数类型是否已存在
    int checkCreateDroolsRuleInputP(@Param("droolsId") Integer droolsId,@Param("droolsVers") String droolsVers,@Param("pTypeId") Integer pTypeId);


    /*
     *   drools_rule_input_p
     *   更新输入参数信息
     */
    int updateDroolsRuleInputP(List<DroolsRuleInputP> listDrip);


    /*
     *   drools_rule_input_p
     *   查询所有的输入参数信息
     */
    List<DroolsRuleInputP> selectDroolsRuleInputP(@Param("listDrip") List<DroolsRuleInputP> listDrip);


}