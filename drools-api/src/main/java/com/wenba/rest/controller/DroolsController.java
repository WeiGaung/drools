package com.wenba.rest.controller;


import com.wenba.domain.*;
import com.wenba.service.drools.DroolsService;
import com.wenba.utils.ObjectUtil;
import com.wenba.utils.base.BaseResult;
import com.wenba.utils.base.BaseResultUtil;
import com.wenba.utils.base.DataResult;
import com.wenba.utils.base.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/config/drools")
@Configuration
public class DroolsController {

    @Autowired
    private DroolsService droolsService;

    /*
    *   定时任务 @Scheduled
    */
    /*@Scheduled(cron = "0/2 * * * * ?")
    public void pushDataScheduled() {
        log.info("start push data scheduled!");
        System.out.println("恭喜测试成功!");
        log.info("end push data scheduled!");
    }*/
    /*
    *   定时任务 @Scheduled
    */
    @GetMapping("/createDroolsVersBean")
    public void pushDataScheduled() {
        log.info("start push data scheduled!");
        System.out.println("恭喜测试成功!");
        log.info("end push data scheduled!");
    }


    /*
    *   检验规则
    */
    /*@RequestMapping(value = "/verify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse ruleVerify(@RequestParam(value = "rule") String rule,
                                   @RequestParam(value = "data") String json) {

        KieSession kieSession = getKieSession(rule);
        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);
        kieSession.insert(person);
        int rules=kieSession.fireAllRules();
        System.out.println(rules);
        kieSession.dispose();
        return new JsonResponse(person);
    }

    private KieSession getKieSession(String rule) {

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/rules.drl", rules.getBytes());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new BusinessException(300003,results.getMessages().toString(),4);
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();

        return kieBase.newKieSession();
    }*/


    /*
     *   生成规则版本实体类
     */

    /*@PostMapping("/createDroolsVersBean")
    public BaseResult createDroolsVersBean(@RequestBody List<DroolsRuleBean> listDrb) {
        System.out.println("进入");
        log.info("createDroolsVersBean 输入参数:" + listDrb.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsRuleBean> pList = new ArrayList<>();

        if(null != listDrb && listDrb.size() > 0) {
            for(DroolsRuleBean drb : listDrb) {
                DataResult<List<DroolsRuleBean>> result = new DataResult<>();
                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(drb.getDroolsId(),drb.getDroolsVers());
                if(i >= 1) {
                    boolean b = droolsService.createDroolsVersBean(pList);
                    if(b) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(drb.getDroolsId() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(drb.getDroolsId() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增失败");
                        }else{
                            bResult.setCode(-1);
                            bResult.setMsg("fail");
                        }
                    }
                    sbS.append(drb.getDroolsId() + ",");
                    pList.add(drb);
                }else{
                    bResult.setCode(-1);
                    bResult.setMsg(sbF.toString() + "该规则id或版本不存在");
                    sbF.append(drb.getDroolsId() + ",");
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.createDroolsVersBean(pList);
                if(b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则id或版本不存在");
            }
        }else {
            bResult.setCode(-4);
            bResult.setMsg("参数为空");
        }


        long endTime = System.currentTimeMillis();
        log.info("createDroolsVersBean 输出参数:" + bResult.toString());
        log.info("createDroolsVersBean cost time:{} ms", endTime - startTime);
        return bResult;
    }*/


    /*
     *   生成规则版本实体类
     */

    @PostMapping("/createDroolsVersBean")
    public DataResult createDroolsVersBean(@RequestBody List<DroolsRuleBean> listDrb) {
        System.out.println("进入");
        log.info("createDroolsVersBean 输入参数:" + listDrb.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        DataResult<Map<String,DataResult<Map<String,List<Map<Integer,String>>>>>> dataResult = new DataResult<>();
        Map<String,DataResult<Map<String,List<Map<Integer,String>>>>> map = new HashMap<>();

        //List<Map<Integer,String>> sList = new ArrayList<>();

        if(null != listDrb && listDrb.size() > 0) {
            for(DroolsRuleBean drb : listDrb) {
                DataResult<Map<String,List<Map<Integer,String>>>> result = new DataResult<>();
                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(drb.getDroolsId(),drb.getDroolsVers());
                if(i >= 1) {
                    //校验实体类是否存在
                    int j = droolsService.checkDroolsRuleBean(drb.getDroolsId(),drb.getDroolsVers());
                    if(j == 0) {
                        //boolean b = droolsService.createDroolsVersBean(drb);
                        Map<Boolean,Map<String,List<Map<Integer,String>>>> bMap = droolsService.createDroolsVersBean(drb);
                        if(null != bMap && bMap.size() > 0) {
                            Set<Boolean> s = bMap.keySet();
                            if(s.iterator().next()) {
                                result.setCode(1);
                                result.setMsg("success");
                                result.setData(bMap.get(s.iterator().next()));
                                map.put(drb.getDroolsId() + "-" + drb.getDroolsVers(),result);
                            }else {
                                result.setCode(-1);
                                result.setMsg("fail");
                                map.put(drb.getDroolsId() + "-" + drb.getDroolsVers(),result);
                            }
                        }else{
                            result.setCode(-5);
                            result.setMsg("未查询到结果");
                            map.put(drb.getDroolsId() + "-" + drb.getDroolsVers(),result);
                        }
                    }else{
                        result.setCode(-1);
                        result.setMsg("该实体类已存在");
                        map.put(drb.getDroolsId() + "-" + drb.getDroolsVers(),result);
                    }
                }else{
                    result.setCode(-1);
                    result.setMsg("该规则id或版本不存在");
                    map.put(drb.getDroolsId() + "-" + drb.getDroolsVers(),result);
                }
                dataResult.setCode(1);
                dataResult.setMsg("success");
                dataResult.setData(map);
            }
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
            return dataResult;
        }


        long endTime = System.currentTimeMillis();
        log.info("createDroolsVersBean 输出参数:" + bResult.toString());
        log.info("createDroolsVersBean cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
    *   生成drools的drl
    */
    @PostMapping("/createDroolsRule")
    @Transactional()
    public BaseResult createDroolsRule(@RequestBody List<DroolsRule> listDr) {
        System.out.println("进入");
        log.info("createDroolsRule 输入参数:" + listDr.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<Map<String, DataResult<String>>>> result = new DataResult<>();

        List<Map<String, DataResult<String>>> list = new ArrayList<>();
        Map<String, DataResult<String>> map = new HashMap<>();
        DataResult<String> dataResult = new DataResult<>();
        List<DroolsRule> pList = new ArrayList<>();

        if(null != listDr && listDr.size() > 0) {
            for(DroolsRule dr : listDr) {
                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(dr.getDroolsId(),dr.getDroolsVers());
                if(i >= 1) {
                    list = droolsService.createDroolsRule(dr);
                    if(null != list && list.size() > 0) {
                        result.setCode(1);
                        result.setMsg("success");
                        list.add(map);
                    }else{
                        result.setCode(-1);
                        result.setMsg("fail");
                        list.add(map);
                    }
                    result.setData(list);
                }else{
                    dataResult.setCode(-1);
                    dataResult.setMsg("该规则id或版本或参数类型不存在");
                    map.put(dr.getDroolsId() + "-" + dr.getDroolsVers(),dataResult);
                }
            }
            /*if(null != pList && pList.size() > 0) {
                list = droolsService.createDroolsRule(pList);
                *//*if(null != map && map.size() > 0) {*//*
                    if(null != list && list.size() > 0) {
                        result.setCode(1);
                        result.setMsg("success");
                        list.add(map);
                    }else{
                        result.setCode(-1);
                        result.setMsg("fail");
                        list.add(map);
                    }
                *//*}else{
                    if(null != list && list.size() > 0) {
                        result.setCode(1);
                        result.setMsg("success");
                    }else{
                        result.setCode(-1);
                        result.setMsg("fail");

                    }
                }*//*
                result.setData(list);
            }else {
                result.setCode(-1);
                result.setMsg("fail");
                list.add(map);
                result.setData(list);
            }*/
        }else {
            result.setCode(-4);
            result.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("createDroolsRule 输出参数:" + listDr.toString());
        log.info("createDroolsRule cost time:{} ms", endTime - startTime);
        return result;
    }


    /*
    *   调用drools
    */
    @PostMapping("/tranDroolsRuleBean")
    public void tranDroolsRuleBean(@RequestBody List<Object> list) {

        try{
            for(Object o : list) {
                Field[] fields = o.getClass().getDeclaredFields();
                for(Field f : fields) {
                    System.out.println(f.getName() + "-" + f.getType() + "-" + f.getAnnotatedType());
                }
                Method[] methods = o.getClass().getMethods();
                for(Method m : methods) {
                    System.out.println(m.getName() + "-" + m.getAnnotatedReturnType());

                }

                for (Field f:o.getClass().getDeclaredFields()){   //遍历通过反射获取object的类中的属性名
                    f.setAccessible(true);    //设置改变属性为可访问
                    if(f.getName().equals("droolsNameVers")){
                        System.out.println("属性值"+f.get(o));
                        System.out.println(f.getName() + ":" + f.get(o) );
                    }
                }

                System.out.println(o.toString());


                ObjectUtil objectUtil = new ObjectUtil();

                List filedsInfo = objectUtil.getFiledsInfo(o);

                Object[] filedValues = objectUtil.getFiledValues(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /*
    *   调用drools
    */
    @PostMapping("/tranDroolsRuleObj")
    public DataResult tranDroolsRuleObj(@RequestBody List<Object> list) {

        System.out.println("进入");
        log.info("tranDroolsRule 输入参数:" + list.toString());
        long startTime = System.currentTimeMillis();

        //返回数据

        DataResult<List<DataResult<String>>> dataResult = new DataResult<>();
        List<DataResult<String>> resultList = new ArrayList<>();

        if(null != list && list.size() > 0) {

            resultList  = droolsService.tranDroolsRuleObj(list);

            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("success");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-1);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        //log.info("tranDroolsRule 输出参数:" + listDp.toString());
        log.info("tranDroolsRule cost time:{} ms", endTime - startTime);
        return dataResult;
    }

    /*
    *   调用drools
    */
    @PostMapping("/tranDroolsRule")
    public DataResult tranDroolsRule(@RequestBody List<InvestSendIntegralv1> list) {

        System.out.println("进入");
        log.info("tranDroolsRule 输入参数:" + list.toString());
        long startTime = System.currentTimeMillis();

        //返回数据

        DataResult<List<DataResult<String>>> dataResult = new DataResult<>();
        List<DataResult<String>> resultList = new ArrayList<>();

        if(null != list && list.size() > 0) {

            resultList  = droolsService.tranDroolsRule(list);

            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("success");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-1);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        //log.info("tranDroolsRule 输出参数:" + listDp.toString());
        log.info("tranDroolsRule cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
     *   drools_rule_p_type
     *   新增参数信息
     */
    @PostMapping("/insertParamsInfoSin")
    public BaseResult insertParamsInfoSin(DroolsRulePType drpt) {
        System.out.println("进入");
        log.info("insertParamsInfoSin 输入参数:" + drpt.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        //校验输入参数的规则和版本是否已存在
        int i = droolsService.checkDroolsRuleInputP(drpt.getDroolsId(),drpt.getDroolsVers());
        if(i == 0) {
            bResult.setMsg("该规则id或规则版本不存在");
        }

        boolean b = droolsService.insertParamsInfoSin(drpt);
        if (b) {
            bResult.setCode(1);
            bResult.setMsg("success");
        }else {
            bResult.setCode(-1);
            bResult.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("insertParamsInfoSin 输出参数:" + bResult.toString());
        log.info("insertParamsInfoSin cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_rule_p_type
     *   新增参数信息
     */
    @PostMapping("/insertParamsInfo")
    public BaseResult insertParamsInfo(@RequestBody List<DroolsRulePType> listDrpt) {
        System.out.println("进入");
        log.info("insertParamsInfo 输入参数:" + listDrpt.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsRulePType> pList = new ArrayList<>();

        if(null != listDrpt && listDrpt.size() > 0) {
            for(DroolsRulePType drpt : listDrpt) {
                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(drpt.getDroolsId(),drpt.getDroolsVers());
                if(i >= 1) {
                    sbS.append(drpt.getpType() + ",");
                    pList.add(drpt);
                }else{
                    sbF.append(drpt.getpType() + ",");
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.insertParamsInfo(pList);
                if(b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该参数类型新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则id或版本不存在");
            }
        }else {
            bResult.setCode(-4);
            bResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("insertParamsInfo 输出参数:" + listDrpt.toString());
        log.info("insertParamsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_rule_p_type
     *   更新参数
     */
    @PostMapping("/updateParamsInfoSin")
    public BaseResult updateParamsInfoSin(DroolsRulePType drpt) {
        System.out.println("进入");
        log.info("updateParamsInfoSin 输入参数:" + drpt.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        //参数状态(是否启用): 1 是 0 否
        if(null == drpt.getpTypeStatus() || "".equals(drpt.getpTypeStatus())) {
            drpt.setpTypeStatus((byte) 0);
        }

        //参数来源 1:入参 2:出参
        if(null == drpt.getpSource() || "".equals(drpt.getpSource())) {
            drpt.setpSource((byte) 2);
        }

        int i = droolsService.updateParamsInfoSin(drpt);
        if(i > 0) {
            BaseResultUtil.success(bResult);
        }else {
            BaseResultUtil.fail(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateParamsInfoSin 输出参数:" + i);
        log.info("updateParamsInfoSin cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_rule_p_type
     *   更新参数
     */
    @PostMapping("/updateParamsInfo")
    public BaseResult updateParamsInfo(@RequestBody List<DroolsRulePType> listDrpt) {
        System.out.println("进入");
        log.info("updateParamsInfo 输入参数:" + listDrpt.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null != listDrpt && listDrpt.size() > 0) {
            int i = droolsService.updateParamsInfo(listDrpt);
            if(i > 0) {
                BaseResultUtil.success(bResult);
            }else {
                BaseResultUtil.fail(bResult);
            }
        }else {
            BaseResultUtil.paramNull(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateParamsInfo 输出参数:" + listDrpt.toString());
        log.info("updateParamsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_rule_p_type
     *   查询所有的参数信息id
     */
    @GetMapping("/selectParamsInfoIds")
    public DataResult<List<DroolsRulePType>> selectParamsInfoIds() {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsRulePType>> dataResult = new DataResult<>();

        List<DroolsRulePType> resultList = droolsService.selectParamsInfoIds();

        if(null != resultList && resultList.size() > 0) {
            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(resultList);
        }else {
            dataResult.setCode(-5);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        log.info("selectParamsInfoIds 输出参数:" + resultList.toString());
        long endTime = System.currentTimeMillis();
        log.info("selectParamsInfoIds cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
     *   drools_rule_p_type
     *   查询所有的参数信息
     */
    @PostMapping("/selectParamsInfoList")
    public PageResult<DroolsRulePType> selectParamsInfoList(DroolsRulePType drpt) {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        PageResult<DroolsRulePType> dataResult = new PageResult<>();
        dataResult.setPage(drpt.getPageNum());
        drpt.setPageNum(drpt.getPageNum() - 1);
        dataResult.setSize(drpt.getPageSize());


        int total = droolsService.selectParamsInfoListNum(drpt);

        dataResult.setTotal(new Long((long)total));

        List<DroolsRulePType> resultList = droolsService.selectParamsInfoList(drpt);
        if(null != resultList && resultList.size() > 0) {
            dataResult.setCode(1);
            dataResult.setMsg("成功");
            dataResult.setData(resultList);
        }else {
            dataResult.setCode(-5);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        long endTime = System.currentTimeMillis();
        log.info("selectParamsInfo 输出参数:" + resultList.toString());
        log.info("selectParamsInfo cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /*
     *   drools_rule_p_type
     *   查询所有的参数信息
     */
    @PostMapping("/selectParamsInfo")
    public DataResult<List<DroolsRulePType>> selectParamsInfo(@RequestBody List<Integer> listIds) {
        System.out.println("进入");
        log.info("selectParamsInfo 输入参数:" + listIds.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsRulePType>> dataResult = new DataResult<>();

        if(null != listIds && listIds.size() > 0) {
            List<DroolsRulePType> resultList = droolsService.selectParamsInfo(listIds);
            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("成功");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("selectParamsInfo 输出参数:" + listIds.toString());
        log.info("selectParamsInfo cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /*
     *   drools_info
     *   新增规则信息
     */
    @PostMapping("/insertDroolsInfo")
    public BaseResult insertDroolsInfo(@RequestBody List<DroolsInfo> listDi) {
        System.out.println("进入");
        log.info("insertDroolsInfo 输入参数:" + listDi.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsInfo> pList = new ArrayList<>();

        if(null != listDi && listDi.size() > 0) {
            for(DroolsInfo di : listDi) {
                //检验规则名称是否存在
                int i = droolsService.checkDroolsInfo(di.getDroolsName());
                if(i >= 1) {
                    sbF.append(di.getDroolsName() + ",");
                }else{
                    sbS.append(di.getDroolsName() + ",");
                    pList.add(di);
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.insertDroolsInfo(pList);
                if (b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则名称已存在" + "\r\n" + sbS.toString() + "该规则信息新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则名称已存在" + "\r\n" + sbS.toString() + "该规则信息新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则名称已存在");
            }
        }else {
            bResult.setCode(-4);
            bResult.setMsg("参数为空");
        }
        long endTime = System.currentTimeMillis();
        log.info("insertDroolsInfo 输出参数:" + listDi.toString());
        log.info("insertDroolsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_info
     *   新增规则信息
     */
    @PostMapping("/insertDroolsInfoSin")
    public BaseResult insertDroolsInfoSin(DroolsInfo di) {
        System.out.println("进入");
        log.info("insertDroolsInfo 输入参数:" + di.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null == di.getDroolsStatus() || "".equals(di.getDroolsStatus())) {
            di.setDroolsStatus((byte) 0);
        }
        //检验规则名称是否存在
        int i = droolsService.checkDroolsInfo(di.getDroolsName());
        if(i >= 1) {
            bResult.setMsg("该规则名称已存在");
        }

            boolean b = droolsService.insertDroolsInfoSin(di);
            if (b) {
                bResult.setCode(1);
                bResult.setMsg("success");
            }else {
                bResult.setCode(-1);
                bResult.setMsg("fail");
            }
        long endTime = System.currentTimeMillis();
        log.info("insertDroolsInfo 输出参数:" + bResult.toString());
        log.info("insertDroolsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }
    /*
     *   drools_info
     *   删除规则信息
     */
    @GetMapping("/deleteDroolsInfoSin")
    public BaseResult deleteDroolsInfoSin(@RequestParam int id) {
        System.out.println("进入");
        log.info("deleteDroolsInfoSin 输入参数:" + id);
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        //检验规则名称是否存在
        /*int i = droolsService.checkDroolsInfo(di.getDroolsName());
        if(i >= 1) {
            bResult.setMsg(di.getDroolsName() + "该规则名称已存在");
        }*/


        //删除规则信息
        int i = droolsService.deleteDroolsInfoSin(id);
        if(i > 0) {
            bResult.setCode(1);
            bResult.setMsg("success");
        }else {
            bResult.setCode(-1);
            bResult.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("deleteDroolsInfoSin 输出参数:" + bResult.toString());
        log.info("deleteDroolsInfoSin cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_info
     *   批量删除规则信息
     */
    @GetMapping("/deleteDroolsInfoList")
    public BaseResult deleteDroolsInfoList(@RequestParam String id) {
        System.out.println("进入");
        log.info("deleteDroolsInfoList 输入参数:" + id.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        //检验规则名称是否存在
        /*int i = droolsService.checkDroolsInfo(di.getDroolsName());
        if(i >= 1) {
            bResult.setMsg(di.getDroolsName() + "该规则名称已存在");
        }*/

        String[] split = id.split(",");
        int j = 0;
        for(String s : split) {
            //删除规则信息
            int i = Integer.valueOf(s).intValue();
            j += droolsService.deleteDroolsInfoSin(i);
        }

        if(j > 0) {
            bResult.setCode(1);
            bResult.setMsg("success");
        }else {
            bResult.setCode(-1);
            bResult.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("deleteDroolsInfoList 输出参数:" + bResult.toString());
        log.info("deleteDroolsInfoList cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_info
     *   更新规则信息
     */
    @PostMapping("/updateDroolsInfo")
    public BaseResult updateDroolsInfo(@RequestBody List<DroolsInfo> listDi) {
        System.out.println("进入");
        log.info("updateDroolsInfo 输入参数:" + listDi.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null != listDi && listDi.size() > 0) {
            int i = droolsService.updateDroolsInfo(listDi);
            if(i > 0) {
                BaseResultUtil.success(bResult);
            }else {
                BaseResultUtil.fail(bResult);
            }
        }else {
            BaseResultUtil.paramNull(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateDroolsInfo 输出参数:" + listDi.toString());
        log.info("updateDroolsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }

    /*
     *   drools_info
     *   更新规则信息
     */
    @PostMapping("/updateDroolsInfoSin")
    public BaseResult updateDroolsInfoSin(DroolsInfo di) {
        System.out.println("进入");
        log.info("updateDroolsInfo 输入参数:" + di.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null == di.getDroolsStatus() || "".equals(di.getDroolsStatus())) {
            di.setDroolsStatus((byte) 0);
        }
        int i = droolsService.updateDroolsInfoSin(di);
        if(i > 0) {
            BaseResultUtil.success(bResult);
        }else {
            BaseResultUtil.fail(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateDroolsInfo 输出参数:" + i);
        log.info("updateDroolsInfo cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_info
     *   查询所有的规则信息id
     */
    @GetMapping("/selectDroolsInfoIdName")
    public DataResult<List<DroolsIdVers>> selectDroolsInfoIdName() {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsIdVers>> dataResult = new DataResult<>();

        List<DroolsIdVers> resultList = droolsService.selectDroolsInfoIdName();

        if(null != resultList && resultList.size() > 0) {

            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(resultList);
        }else{
            dataResult.setCode(-5);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        log.info("selectDroolsInfoIdVers 输出参数:" + resultList.toString());
        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfoIdVers cost time:{} ms", endTime - startTime);
        return dataResult;
    }
    @GetMapping("/selectDroolsInfoVers")
    public DataResult<List<DroolsIdVers>> selectDroolsInfoVers() {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsIdVers>> dataResult = new DataResult<>();

        List<DroolsIdVers> resultList = droolsService.selectDroolsInfoVers();

        if(null != resultList && resultList.size() > 0) {

            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(resultList);
        }else{
            dataResult.setCode(-5);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        log.info("selectDroolsInfoIdVers 输出参数:" + resultList.toString());
        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfoIdVers cost time:{} ms", endTime - startTime);
        return dataResult;
    }

    /*
     *   drools_info
     *   查询所有的规则信息id
     */
    @GetMapping("/selectDroolsInfoIds")
    public DataResult<List<DroolsInfo>> selectDroolsInfoIds() {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsInfo>> dataResult = new DataResult<>();

        List<DroolsInfo> resultList = droolsService.selectDroolsInfoIds();

        if(null != resultList && resultList.size() > 0) {

            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(resultList);
        }else{
            dataResult.setCode(-5);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        log.info("selectDroolsInfoIds 输出参数:" + resultList.toString());
        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfoIds cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    @PostMapping("/selectDroolsInfoList")
    public PageResult<DroolsInfo> selectDroolsInfoList(DroolsInfo di) throws ParseException {
        System.out.println("进入");
        long startTime = System.currentTimeMillis();

        //返回数据
        PageResult<DroolsInfo> dataResult = new PageResult<>();
        dataResult.setPage(di.getPageNum());
        di.setPageNum(di.getPageNum() - 1);
        dataResult.setSize(di.getPageSize());

        String cd = di.getCreateDate();
        if(null != cd && !"".equals(cd)) {

            String[] split = cd.split(" - ");

            di.setCreate_start(split[0]);
            di.setCreate_end(split[1]);
        }

        //DataResult<List<DroolsInfo>> dataResult = new DataResult<>();
        int total = droolsService.selectDroolsInfoListNum(di);

        dataResult.setTotal(new Long((long)total));

        List<DroolsInfo> resultList = droolsService.selectDroolsInfoList(di);

        if(null != resultList && resultList.size() > 0) {
            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(resultList);
        }else {
            dataResult.setCode(1);
            dataResult.setMsg("未查询到结果");
            dataResult.setData(resultList);
        }

        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfo 输出参数:" + resultList.toString());
        log.info("selectDroolsInfo cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    @GetMapping("/selectDroolsInfoSin")
    public DataResult<DroolsInfo> selectDroolsInfoSin(@RequestParam int id) {
        System.out.println("进入");
        log.info("selectDroolsInfo 输入参数:" + id);
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<DroolsInfo> dataResult = new DataResult<>();

        DroolsInfo di = droolsService.selectDroolsInfoSin(id);
        dataResult.setCode(1);
        dataResult.setMsg("success");
        dataResult.setData(di);

        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfo 输出参数:" + dataResult.toString());
        log.info("selectDroolsInfo cost time:{} ms", endTime - startTime);
        return dataResult;
    }

    /*
     *   drools_info
     *   查询所有的规则信息
     */
    @PostMapping("/selectDroolsInfo")
    public DataResult<List<DroolsInfo>> selectDroolsInfo(@RequestBody List<Integer> listI) {
        System.out.println("进入");
        log.info("selectDroolsInfo 输入参数:" + listI.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsInfo>> dataResult = new DataResult<>();

        if(null != listI && listI.size() > 0) {
            List<DroolsInfo> resultList = droolsService.selectDroolsInfo(listI);
            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("success");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("selectDroolsInfo 输出参数:" + listI.toString());
        log.info("selectDroolsInfo cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /*
     *   drools_vers
     *   新增规则版本信息
     */
    @PostMapping("/insertDroolsVers")
    public BaseResult insertDroolsVers(@RequestBody List<DroolsVers> listDv) {
        System.out.println("进入");
        log.info("insertDroolsVers 输入参数:" + listDv.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsVers> pList = new ArrayList<>();

        if(null != listDv && listDv.size() > 0) {
            for(DroolsVers dv : listDv) {
                //校验规则id是否已存在
                int i = droolsService.checkDroolsVers(dv.getDroolsId());
                if(i >= 1) {
                    sbS.append(dv.getDroolsId() + ",");
                    pList.add(dv);
                }else{
                    sbF.append(dv.getDroolsId() + ",");
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.insertDroolsVers(pList);
                if(b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则id不存在" + "\r\n" + sbS.toString() + "该规则版本新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则id不存在" + "\r\n" + sbS.toString() + "该规则版本新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则id不存在");
            }
        }else {
            bResult.setCode(-4);
            bResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("insertDroolsVers 输出参数:" + listDv.toString());
        log.info("insertDroolsVers cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_vers
     *   更新规则版本信息
     */
    @PostMapping("/updateDroolsVers")
    public BaseResult updateDroolsVers(@RequestBody List<DroolsVers> listDv) {
        System.out.println("进入");
        log.info("updateDroolsVers 输入参数:" + listDv.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null != listDv && listDv.size() > 0) {
            int i = droolsService.updateDroolsVers(listDv);
            if(i > 0) {
                BaseResultUtil.success(bResult);
            }else {
                BaseResultUtil.fail(bResult);
            }
        }else {
            BaseResultUtil.paramNull(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateDroolsVers 输出参数:" + listDv.toString());
        log.info("updateDroolsVers cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_vers
     *   查询所有规则版本信息
     */
    @PostMapping("/selectDroolsVers")
    public DataResult<List<DroolsVers>> selectDroolsVers(@RequestBody List<DroolsVers> listDv) {
        System.out.println("进入");
        log.info("selectDroolsVers 输入参数:" + listDv.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsVers>> dataResult = new DataResult<>();

        if(null != listDv && listDv.size() > 0) {
            List<DroolsVers> resultList = droolsService.selectDroolsVers(listDv);
            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("成功");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("selectDroolsVers 输出参数:" + listDv.toString());
        log.info("selectDroolsVers cost time:{} ms", endTime - startTime);
        return dataResult;
    }


    /*
     *   drools_rule_input_p
     *   新增输入参数信息
     */
    @PostMapping("/insertDroolsRuleInputP")
    public BaseResult insertDroolsRuleInputP(@RequestBody List<DroolsRuleInputP> listDrip) {
        System.out.println("进入");
        log.info("insertDroolsRuleInputP 输入参数:" + listDrip.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsRuleInputP> pList = new ArrayList<>();

        if(null != listDrip && listDrip.size() > 0) {
            for(DroolsRuleInputP drip : listDrip) {
                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(drip.getDroolsId(),drip.getDroolsVers());
                if(i >= 1) {
                    sbS.append(drip.getDroolsId() + "-" + drip.getDroolsVers() + ",");
                    pList.add(drip);
                }else{
                    sbF.append(drip.getDroolsId() + "-" + drip.getDroolsVers() + ",");
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.insertDroolsRuleInputP(pList);
                if(b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该输入参数新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本不存在" + "\r\n" + sbS.toString() + "该输入参数新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则id或版本不存在");
            }
        }else {
            bResult.setCode(-5);
            bResult.setMsg("未查询到结果");
        }

        long endTime = System.currentTimeMillis();
        log.info("insertDroolsRuleInputP 输出参数:" + listDrip.toString());
        log.info("insertDroolsRuleInputP cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_rule_input_p
     *   更新输入参数信息
     */
    @PostMapping("/updateDroolsRuleInputP")
    public BaseResult updateDroolsRuleInputP(@RequestBody List<DroolsRuleInputP> listDrip) {
        System.out.println("进入");
        log.info("updateDroolsRuleInputP 输入参数:" + listDrip.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();

        if(null != listDrip && listDrip.size() > 0) {
            int i = droolsService.updateDroolsRuleInputP(listDrip);
            if(i > 0) {
                BaseResultUtil.success(bResult);
            }else {
                BaseResultUtil.fail(bResult);
            }
        }else {
            BaseResultUtil.paramNull(bResult);
        }

        long endTime = System.currentTimeMillis();
        log.info("updateDroolsRuleInputP 输出参数:" + listDrip.toString());
        log.info("updateDroolsRuleInputP cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /*
     *   drools_rule_input_p
     *   查询所有的输入参数信息
     */
    @PostMapping("/selectDroolsRuleInputP")
    public DataResult selectDroolsRuleInputP(@RequestBody List<DroolsRuleInputP> listDrip) {
        System.out.println("进入");
        log.info("selectDroolsRuleInputP 输入参数:" + listDrip.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<DroolsRuleInputP>> dataResult = new DataResult<>();

        if(null != listDrip && listDrip.size() > 0) {
            List<DroolsRuleInputP> resultList = droolsService.selectDroolsRuleInputP(listDrip);
            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("成功");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(-5);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("selectDroolsRuleInputP 输出参数:" + listDrip.toString());
        log.info("selectDroolsRuleInputP cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /*
     *   drools_rule
     *   新增具体规则信息
     */
    @PostMapping("/insertDroolsRule")
    public BaseResult insertDroolsRule(@RequestBody List<DroolsRule> listDr) {
        System.out.println("进入");
        log.info("insertDroolsRule 输入参数:" + listDr.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<DroolsRule> pList = new ArrayList<>();

        if(null != listDr && listDr.size() > 0) {
            for(DroolsRule dr : listDr) {
                //校验输入参数的规则和版本及参数类型是否已存在
                int i = droolsService.checkCreateDroolsRuleInputP(dr.getDroolsId(),dr.getDroolsVers(),dr.getpTypeId());
                if(i == 2) {
                    sbS.append(dr.getDroolsId() + "-" + dr.getDroolsVers() + ",");
                    pList.add(dr);
                }else{
                    sbF.append(dr.getDroolsId() + "-" + dr.getDroolsVers() + ",");
                }
            }
            if(null != pList && pList.size() > 0) {
                boolean b = droolsService.insertDroolsRule(pList);
                if(b) {
                    if(sbF.length() > 0) {
                        bResult.setCode(1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本或输出参数类型不存在" + "\r\n" + sbS.toString() + "该规则新增成功");
                    }else {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                    }
                }else {
                    if(sbF.length() > 0) {
                        bResult.setCode(-1);
                        bResult.setMsg(sbF.toString() + "该规则id或版本或输出参数类型不存在" + "\r\n" + sbS.toString() + "该规则新增失败");
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                    }
                }
            }else {
                bResult.setCode(-1);
                bResult.setMsg(sbF.toString() + "该规则id或版本或输出参数类型不存在");
            }
        }else {
            bResult.setCode(-5);
            bResult.setMsg("未查询到结果");
        }

        long endTime = System.currentTimeMillis();
        log.info("insertDroolsRule 输出参数:" + listDr.toString());
        log.info("insertDroolsRule cost time:{} ms", endTime - startTime);
        return bResult;
    }



    /*
     *   drools_rule
     *   更新具体规则信
     */
    @PostMapping("/updateDroolsRule")
    public DataResult updateDroolsRule(@RequestBody List<DroolsRule> listDr) {
        System.out.println("进入");
        log.info("updateDroolsRule 输入参数:" + listDr.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<Map<String, DataResult>> dataResult = new DataResult<>();
        Map<String, DataResult> map = new HashMap<>();

        if (null != listDr && listDr.size() > 0) {
            for (DroolsRule dr : listDr) {
                DataResult result = new DataResult();
                int i = 0;
                //校验输入参数的规则和版本是否已存在
                if(null != dr.getpTypeId()) {
                    i = droolsService.checkCreateDroolsRuleInputP(dr.getDroolsId(), dr.getDroolsVers(),dr.getpTypeId());
                }else {
                    i = droolsService.checkDroolsRuleInputP(dr.getDroolsId(), dr.getDroolsVers());
                }
                if (i >= 1) {
                    int j = droolsService.updateDroolsRule(listDr);
                    if (j > 0) {
                        result.setCode(1);
                        result.setMsg("success");
                        map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()), result);
                    } else {
                        result.setCode(-5);
                        result.setMsg("未查询到结果");
                        map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()), result);
                    }
                } else {
                    result.setCode(-1);
                    result.setMsg("该规则id或版本不存在");
                    map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()), result);
                }
            }
            dataResult.setCode(1);
            dataResult.setMsg("success");
            dataResult.setData(map);
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        long endTime = System.currentTimeMillis();
        log.info("updateDroolsRule 输出参数:" + listDr.toString());
        log.info("updateDroolsRule cost time:{} ms", endTime - startTime);
        return dataResult;

    }

    /*
     *   drools_rule
     *   查询所有具体规则信息
     */
    @PostMapping("/selectDroolsRule")
    public DataResult selectDroolsRule(@RequestBody List<DroolsRule> listDr) {
        System.out.println("进入");
        log.info("selectDroolsRule 输入参数:" + listDr.toString());

        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<Map<String,DataResult<List<DroolsRule>>>> dataResult = new DataResult<>();
        Map<String,DataResult<List<DroolsRule>>> map = new HashMap<>();

        if(null != listDr && listDr.size() > 0) {
            for(DroolsRule dr : listDr) {
                DataResult<List<DroolsRule>> result = new DataResult<>();

                //校验输入参数的规则和版本是否已存在
                int i = droolsService.checkDroolsRuleInputP(dr.getDroolsId(),dr.getDroolsVers());
                if(i >= 1) {
                    List<DroolsRule> resultList = droolsService.selectDroolsRule(listDr);
                    if(null != resultList && resultList.size() > 0) {
                        result.setCode(1);
                        result.setMsg("success");
                        result.setData(resultList);
                        map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()),result);
                    }else{
                        result.setCode(-5);
                        result.setMsg("未查询到结果");
                        result.setData(resultList);
                        map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()),result);
                    }
                }else{
                    result.setCode(-1);
                    result.setMsg("该规则id或版本不存在");
                    map.put((dr.getDroolsId() + "-" + dr.getDroolsVers()),result);
                }
            }
            dataResult.setData(map);
        }else {
            dataResult.setCode(-4);
            dataResult.setMsg("参数为空");
        }

        dataResult.setCode(1);
        dataResult.setMsg("success");
        long endTime = System.currentTimeMillis();
        log.info("selectDroolsRule 输出参数:" + listDr.toString());
        log.info("selectDroolsRule cost time:{} ms", endTime - startTime);
        return dataResult;
    }



}
