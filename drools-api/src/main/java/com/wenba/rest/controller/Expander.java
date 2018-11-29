/*
package com.wenba.rest.controller;

*/
/*import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.drools.io.ResourceFactory;*//*


*/
/*import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;*//*


import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;

public class Expander {

    public void expand(KnowledgeBase kBase, InputStream is, Collection<?> act)
            throws Exception {
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(act, is);
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory
                .newKnowledgeBuilder();
        Reader rdr = new StringReader(drl);
        kBuilder.add(ResourceFactory.newReaderResource(rdr), ResourceType.DRL);
        if (kBuilder.hasErrors()) {
            for (KnowledgeBuilderError err : kBuilder.getErrors()) {
                System.err.println(err.toString());
            }
            throw new IllegalStateException("DRL errors");
        }
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
    }
}
*/
