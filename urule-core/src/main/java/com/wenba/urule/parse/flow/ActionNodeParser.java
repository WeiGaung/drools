/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.wenba.urule.parse.flow;

import org.dom4j.Element;

import com.wenba.urule.model.flow.ActionNode;

/**
 * @author Jacky.gao
 * @since 2014年12月23日
 */
public class ActionNodeParser extends FlowNodeParser<ActionNode> {
	public ActionNode parse(Element element) {
		ActionNode action=new ActionNode(element.attributeValue("name"));
		action.setActionBean(element.attributeValue("action-bean"));
		action.setEventBean(element.attributeValue("event-bean"));
		action.setX(element.attributeValue("x"));
		action.setY(element.attributeValue("y"));
		action.setWidth(element.attributeValue("width"));
		action.setHeight(element.attributeValue("height"));
		action.setConnections(parseConnections(element));
		return action;
	}
	public boolean support(String name) {
		return name.equals("action");
	}
}