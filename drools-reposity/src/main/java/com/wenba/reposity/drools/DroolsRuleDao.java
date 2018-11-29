package com.wenba.reposity.drools;

import com.wenba.domain.DroolsInOutParams;
import com.wenba.domain.DroolsRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroolsRuleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DroolsRule record);

    int insertSelective(DroolsRule record);

    DroolsRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DroolsRule record);

    int updateByPrimaryKey(DroolsRule record);


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
    List<DroolsRule> selectDroolsRule(@Param("listDr") List<DroolsRule> listDr);


    /*
     *   drools_rule
     *   查询所有具体规则及规则的名称信息
     */
    List<DroolsRule> selectDroolsRuleInfoList(@Param("listDr") List<DroolsRule> listDr);
    List<DroolsRule> selectDroolsRuleInfo(DroolsRule dr);

    //查询要生成实体类数据
    List<DroolsInOutParams> selectDroolsBean(@Param("droolsId") Integer droolsId, @Param("droolsVers") String droolsVers);

}