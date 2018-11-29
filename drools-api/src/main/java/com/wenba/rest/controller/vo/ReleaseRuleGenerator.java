/*
package com.wenba.rest.controller.vo;

import com.sun.corba.se.spi.ior.ObjectId;
import org.drools.core.util.DateUtils;
import org.kie.api.builder.ReleaseId;

import java.util.HashMap;
import java.util.Map;

public class ReleaseRuleGenerator extends RuleGenerator {

    @Override
    protected Map<String, Object> prepareData(RuleDTO ruleDTO) {
        Map<String, Object> data = new HashMap<>();
        ActivityRule rule = ruleDTO.getRule();
        data.put("rule", rule.getRuleValue());
        data.put("eventType", FactParser.getEventFactClass(ActivityEvent.valueOf(rule.getEvent())).getName());
        data.put("ruleId", rule.getId());
        data.put("ruleCode", ObjectId.getIdentityId());
        data.put("joinChannels", ruleDTO.getJoinChannel());
        data.put("priority", rule.getPriority());
        data.put("beginTime", DateUtils.format(RuleConstant.DATE_PATTERN, ruleDTO.getBeginTime()));
        data.put("endTime", DateUtils.format(RuleConstant.DATE_PATTERN, ruleDTO.getEndTime()));
        return data;
    }

    @Override
    protected String getTemplateFileName() {
        return RuleConstant.RELEASE_TEMPLATE_NAME;
    }

    @Override
    protected ReleaseId getReleaseId() {
        return RuleConstant.releaseId;
    }
}

*/
