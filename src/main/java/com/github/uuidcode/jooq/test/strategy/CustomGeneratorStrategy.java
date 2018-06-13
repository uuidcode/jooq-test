package com.github.uuidcode.jooq.test.strategy;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String javaClassName = super.getJavaClassName(definition, mode);
        return "Q" + javaClassName;
    }
}
