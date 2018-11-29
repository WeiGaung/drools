/*
package com.wenba;


import com.wenba.rest.controller.Expander;
import com.wenba.rest.controller.vo.Item;
import com.wenba.rest.controller.vo.ItemCode;
import com.wenba.rest.controller.vo.ParamSet;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

*/
/*import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;*//*

public class TestItemTemplate {

    public static void main(String args[]) throws Exception {
        Collection<ParamSet> cfl = new ArrayList<ParamSet>();
        cfl.add(new ParamSet("price", 10, 50, EnumSet.of(ItemCode.BARREL)));
        cfl.add(new ParamSet("weight", 10, 99, EnumSet.of(ItemCode.LOCK,ItemCode.STOCK)));
        KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();

        Expander ex = new Expander();
        InputStream dis = ResourceFactory.newClassPathResource("rules/Item.drl", TestItemTemplate.class).getInputStream();
        ex.expand(kBase, dis, cfl);
        StatefulKnowledgeSession session = kBase.newStatefulKnowledgeSession();

        session.insert(new Item("A", 130, 42, ItemCode.LOCK));
        session.insert(new Item("B", 44, 140, ItemCode.STOCK));
        session.insert(new Item("C", 23, 80, ItemCode.BARREL));
        session.insert(new Item("D", 85, 9, ItemCode.LOCK));
        session.insert(new Item("E", 146, 189, ItemCode.STOCK));
        session.insert(new Item("F", 16, 90, ItemCode.STOCK));
        session.insert(new Item("G", 44, 140, ItemCode.BARREL));
        session.fireAllRules();
    }
}
*/
