package com.wenba.reposity.drools;

import com.wenba.domain.DroolsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroolsInfoDao {

    int deleteByPrimaryKey(Integer id);

    int insert(DroolsInfo record);

    int insertSelective(DroolsInfo record);

    DroolsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DroolsInfo record);

    int updateByPrimaryKey(DroolsInfo record);



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
    DroolsInfo selectDroolsInfoSin(@Param("id") int id);
    List<DroolsInfo> selectDroolsInfoList(DroolsInfo di);


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    List<DroolsInfo> selectDroolsInfo(@Param("listI") List<Integer> listI);


    //校验规则版本是否已存在
    int checkDroolsVers(Integer droolsId);

    //查询规则id的名称
    String selectDroolsInfoById(@Param("droolsId") Integer droolsId);

    //检验规则名称是否存在
    int checkDroolsInfo(@Param("droolsName") String droolsName);

    //删除规则信息
    int deleteDroolsInfoSin(@Param("id") int id);


}