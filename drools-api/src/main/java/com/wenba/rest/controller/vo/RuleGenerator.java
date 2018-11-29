/*
package com.wenba.rest.controller.vo;


import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;

import java.util.*;

*/
/**
 * 规则生成器
 *//*

@Slf4j
public abstract class RuleGenerator {

    */
/**
     * drools默认时间格式是 `日-月-年` 比如 `29-四月-2016`
     * 通过加载类时设置系统变量，改变drools默认的日期格式
     *//*

    */
/*static {
        System.setProperty("drools.dateformat", RuleConstant.DATE_PATTERN);
    }*//*


    */
/**
     * 根据传递进来的参数对象生规则
     * @param ruleDTOs
     *//*

    public void generateRules(List<RuleDTO> ruleDTOs) {
        List<String> ruleDrls = new ArrayList<>();
        for (int i = 0; i < ruleDTOs.size(); i++) {
            String drlString = applyRuleTemplate(ruleDTOs.get(i));
            ruleDrls.add(drlString);
        }
        createOrRefreshDrlInMemory(ruleDrls);
    }

    */
/**
     * 根据String格式的Drl生成Maven结构的规则
     * @param rules
     *//*

    private void createOrRefreshDrlInMemory(List<String> rules) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.generateAndWritePomXML(getReleaseId());
        for (String str : rules) {
            kieFileSystem.write("src/main/resources/" + UUID.randomUUID() + ".drl", str);
        }
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
        */
/*if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            log.error("create rule in kieFileSystem Error", kb.getResults());
            throw new RuntimeException("生成规则文件失败");
        }*//*

        doAfterGenerate(kieServices);
    }

    */
/**
     * 根据Rule生成drl的String
     * @param ruleDTO
     * @return
     *//*

    private String applyRuleTemplate(RuleDTO ruleDTO) {
        Map<String, Object> data = prepareData(ruleDTO);
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
        return objectDataCompiler.compile(Arrays.asList(data), Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(getTemplateFileName()));
    }

    */
/**
     * 准备生成规则需要的数据，供模板使用
     * @param ruleDTO
     * @return
     *//*

    protected abstract Map<String, Object> prepareData(RuleDTO ruleDTO);

    */
/**
     * 获取模板文件名
     * @return
     *//*

    protected abstract String getTemplateFileName();

    */
/**
     * 生成的规则，在KieFileSystem中的 releaseId
     * @return
     *//*

    protected abstract ReleaseId getReleaseId();

    */
/**
     * 生成完毕后的清理工作，目前主要用于debug模式测试完毕后，从内存中清理掉规则文件。
     * @param kieServices
     *//*

    protected void doAfterGenerate(KieServices kieServices) {

    }

}
*/
