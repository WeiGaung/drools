package com.wenba.domain;

import java.io.Serializable;

	/**
    * investSendIntegralv1 实体类
    * Fri Nov 16 18:55:05 CST 2018 GaoBin
    */ 


public class InvestSendIntegralv1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private int inParam;

	private int outParam;

	private String droolsNameVers;

	public void setInParam(int inParam){
	this.inParam=inParam;
	}

	public int getInParam(){
		return inParam;
	}

	public void setOutParam(int outParam){
	this.outParam=outParam;
	}

	public int getOutParam(){
		return outParam;
	}

	public String getDroolsNameVers() {
        return droolsNameVers;
    }

	public void setDroolsNameVers(String droolsNameVers) {
        this.droolsNameVers = droolsNameVers;
    }

}

