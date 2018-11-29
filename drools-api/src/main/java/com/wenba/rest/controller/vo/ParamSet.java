package com.wenba.rest.controller.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.EnumSet;

@Getter
@Setter
public class ParamSet {

    private String field;
    private int lower;
    private int upper;
    private EnumSet<ItemCode> codeSet;

    public ParamSet(String field, int lower, int upper,
                    EnumSet<ItemCode> codeSet) {
        super();
        this.field = field;
        this.lower = lower;
        this.upper = upper;
        this.codeSet = codeSet;
    }

    public String getField() {
        return field;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public String getCodes() {
        StringBuilder sb = new StringBuilder();
        String conn = "";
        for (ItemCode ic : codeSet) {
            sb.append(conn).append(" == ItemCode.").append(ic);
            conn = " ||";
        }
        return sb.toString();
    }
}
