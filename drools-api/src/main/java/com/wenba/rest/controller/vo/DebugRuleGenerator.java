/*
package com.wenba.rest.controller.vo;


import com.sun.corba.se.spi.ior.ObjectId;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;

import java.util.HashMap;
import java.util.Map;

public class DebugRuleGenerator extends RuleGenerator {

    @Override
    protected Map<String, Object> prepareData(RuleDTO ruleDTO) {
        Map<String, Object> data = new HashMap<>();
        ActivityRule rule = ruleDTO.getRule();
        data.put("rule", rule.getRuleValue());
        data.put("eventType", FactParser.getEventFactClass(ActivityEvent.valueOf(rule.getEvent())).getName());
        data.put("ruleCode", ObjectId.getIdentityId());
        return data;
    }

    @Override
    protected String getTemplateFileName() {
        return RuleConstant.TEST_TEMPLATE_NAME;
    }

    @Override
    protected ReleaseId getReleaseId() {
        return RuleConstant.snapshotId;
    }

    @Override
    protected void doAfterGenerate(KieServices kieServices) {
        kieServices.getRepository().removeKieModule(getReleaseId());
    }

}
*/
