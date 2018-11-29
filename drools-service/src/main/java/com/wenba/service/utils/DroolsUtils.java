/*
package com.wenba.service.utils;

import com.wenba.domain.DroolsRuleParams;

import java.util.List;



public class DroolsUtils {


    public static void generate(List<DroolsRuleParams> listDrp) {

        if(listDrp != null && listDrp.size() > 0) {

            StringBuilder sbIf = new StringBuilder();
            StringBuilder sbThen = new StringBuilder();

            for(DroolsRuleParams drp : listDrp) {
                */
/*
                 *   最小值 只能大于或等于或大于等于
                 *       第一行的最小值可为空
                 *
                 *   最大值 只能小于或等于或小于等于
                 *       最后一行的最大值可为空
                 *
                 *   if                                                      then
                 *     $s : DroolsParams(params <= 100)                          $s.setResults(0);         update($s);
                 *     $s : DroolsParams(params > 100 && params <= 500)          $s.setResults(100);       update($s);
                 *     $s : DroolsParams(params > 500 && params <= 1000)         $s.setResults(500);       update($s);
                 *     $s : DroolsParams(params > 1000)                          $s.setResults(1000);      update($s);
                 *//*

                if(null != drp.getMinValue()) {         //最小值不为空
                    if(null == drp.getMaxValue()) {     //最小值不为空,最大值为空
                        if(">".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params > " + drp.getMinValue() + ")");
                        }else if("=".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params = " + drp.getMinValue() + ")");
                        }else if(">=".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params >= " + drp.getMinValue() + ")");
                        }else if("<".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params < " + drp.getMinValue() + ")");
                        }else if("<=".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params <= " + drp.getMinValue() + ")");
                        }
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else {     //最小值不为空,最大值不为空
                        if(">".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params > " + drp.getMinValue());
                            if("<".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params < " + drp.getMaxValue() + ")");
                            }else if("=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params = " + drp.getMaxValue() + ")");
                            }else if("<=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params <= " + drp.getMaxValue() + ")");
                            }
                            sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                        }else if("=".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params = " + drp.getMinValue());
                            if("<".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params < " + drp.getMaxValue() + ")");
                            }else if("=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params = " + drp.getMaxValue() + ")");
                            }else if("<=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params <= " + drp.getMaxValue() + ")");
                            }
                            sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                        }else if(">=".equals(drp.getMinSymbol())) {
                            sbIf.append("$s : DroolsParams(params >= " + drp.getMinValue());
                            if("<".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params < " + drp.getMaxValue() + ")");
                                sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                            }else if("=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params = " + drp.getMaxValue() + ")");
                                sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                            }else if("<=".equals(drp.getMaxSymbol())) {
                                sbIf.append(" && params <= " + drp.getMaxValue() + ")");
                                sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                            }
                        }
                    }
                }else if (null != drp.getMaxValue()) {      //最小值为空,最大值不为空
                    if("<".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params < " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else if("=".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params = " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else if("<=".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params <= " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else if(">".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params > " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else if("=".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params = " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }else if(">=".equals(drp.getMaxSymbol())) {
                        sbIf.append("$s : DroolsParams(params >= " + drp.getMaxValue() + ")");
                        sbThen.append("$s.setResults( " + drp.getResult() + " );         update($s);");
                    }
                }
                drp.setRuleIf(sbIf.toString());
                drp.setRuleThen(sbThen.toString());
            }
        }
    }
}
*/
