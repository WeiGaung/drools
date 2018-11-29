package com.wenba.domain;


import java.io.Serializable;

public class DroolsParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private int params;

    private int results;

    private String droolsNameVers;

    private String beanGenericity;


    public int getParams() {
        return params;
    }

    public void setParams(int params) {
        this.params = params;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getDroolsNameVers() {
        return droolsNameVers;
    }

    public void setDroolsNameVers(String droolsNameVers) {
        this.droolsNameVers = droolsNameVers;
    }

    public String getBeanGenericity() {
        return beanGenericity;
    }

    public void setBeanGenericity(String beanGenericity) {
        this.beanGenericity = beanGenericity;
    }

    @Override
    public String toString() {
        return "DroolsParams{" +
                "params=" + params +
                ", results=" + results +
                ", droolsNameVers='" + droolsNameVers + '\'' +
                ", beanGenericity='" + beanGenericity + '\'' +
                '}';
    }
}
