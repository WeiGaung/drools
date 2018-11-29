package com.wenba.reposity.drools;

import com.wenba.domain.DroolsRulePType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroolsRulePTypeDao {

    int deleteByPrimaryKey(Integer pTypeId);

    int insert(DroolsRulePType record);

    int insertSelective(DroolsRulePType record);

    DroolsRulePType selectByPrimaryKey(Integer pTypeId);

    int updateByPrimaryKeySelective(DroolsRulePType record);

    int updateByPrimaryKey(DroolsRulePType record);


    /*
     *   新增参数信息
     */
    boolean insertParamsInfo(List<DroolsRulePType> listDrpt);
    boolean insertParamsInfoSin(DroolsRulePType drpt);

    //校验参数类型是否已存在
    int checkParamsInfo(String getpType);


    /*
     *   更新参数
     */
    int updateParamsInfo(List<DroolsRulePType> listDrpt);
    int updateParamsInfoSin(DroolsRulePType drpt);

    /*
     *  查询所有的参数信息id
     */
    List<DroolsRulePType> selectParamsInfoIds();


    /*
     *  查询所有的参数信息
     */
    int selectParamsInfoListNum(DroolsRulePType drpt);
    List<DroolsRulePType> selectParamsInfo(@Param("listIds") List<Integer> listIds);
    List<DroolsRulePType> selectParamsInfoList(DroolsRulePType drpt);



    //记录生成实体类名称
    int updateDrBeanName(@Param("pBeanName") String pBeanName, @Param("droolsId") Integer droolsId, @Param("droolsVers") String droolsVers);

    //校验实体类是否存在
    int checkDroolsRuleBean(@Param("droolsId") Integer droolsId,@Param("droolsVers")  String droolsVers);

}