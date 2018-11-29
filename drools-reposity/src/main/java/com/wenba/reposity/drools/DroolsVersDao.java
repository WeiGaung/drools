package com.wenba.reposity.drools;

import com.wenba.domain.DroolsIdVers;
import com.wenba.domain.DroolsVers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroolsVersDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DroolsVers record);

    int insertSelective(DroolsVers record);

    DroolsVers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DroolsVers record);

    int updateByPrimaryKey(DroolsVers record);


    /*
     *   drools_vers
     *   新增规则版本信息
     */
    boolean insertDroolsVers(List<DroolsVers> listDv);


    /*
     *   drools_info
     *   更新规则版本信息
     */
    int updateDroolsVers(List<DroolsVers> listDv);


    /*
     *   drools_vers
     *   查询所有规则版本信息
     */
    List<DroolsVers> selectDroolsVers(@Param("listDv") List<DroolsVers> listDv);

    List<DroolsIdVers> selectDroolsInfoIdName();
    List<DroolsIdVers> selectDroolsInfoVers();


}