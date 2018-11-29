package com.wenba;

import net.sourceforge.jeval.Evaluator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvalTest {

    private static final double X_VALUE = 1.0;
    private static final double Y_VALUE = 270;

    //	testJeval/testJeval2需要手動包裝變量，testJeval3增加了變量包裝方法
    public static void main(String[] args) throws Exception {
        /*testJeval();
        testJeval2();
        testJeval3();*/
        testJeval4();
    }

    private static void testJeval() throws Exception {
        String exp = "2 + (7-5) * 3.14159 * #{x} + sin(#{y})";

        // compile
        Evaluator eva = new Evaluator();


        eva.putVariable("x", Double.toString(X_VALUE));
        eva.putVariable("y", Double.toString(Y_VALUE));

        // evaluate
        double result = Double.parseDouble(eva.evaluate(exp));

        System.out.println(result);//-> 2.0
    }

    public static void testJeval2(){
        // 我们的游戏公式 ahit-(blv-alv)*(6*beva/100)+32
        String exp = "#{ahit}-(#{blv}-#{alv})*(6*#{beva}/100)+32";
        Evaluator eva = new Evaluator();
        try {
            /** * 添加变量到 Evaluator 类实例.  */
            eva.putVariable("ahit", "33");
            eva.putVariable("blv", "10");
            eva.putVariable("alv", "10");
            eva.putVariable("beva", "5");
            /** * 简单输出变量. */
		/*System.out.println(eva.evaluate("#{ahit}"));
		System.out.println(eva.evaluate("#{blv}"));
		System.out.println(eva.evaluate("#{alv}"));
		System.out.println(eva.evaluate("#{beva}"));*/
            //公式计算
            System.out.println(eva.evaluate(exp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testJeval3(){
        // 我们的游戏公式 ahit-(blv-alv)*(6*beva/100)+32
        String exp = "ahit-(blv-alv)*(6*beva/100)+32";
        Evaluator eva = new Evaluator();
        try {
            /** * 添加变量到 Evaluator 类实例.  */
            eva.putVariable("ahit", "33");
            eva.putVariable("blv", "10");
            eva.putVariable("alv", "10");
            eva.putVariable("beva", "5");

            //公式计算
            System.out.println(eva.evaluate(formatExpression(exp)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testJeval4(){
        Evaluator evaluator = new Evaluator();
        Map map = evaluator.getFunctions();
        System.out.println(map.toString());


        try {
            System.out.println("1.-->"+evaluator.evaluate("1+2*3-2/1"));  //直接计算字符串型的数学表达式
            System.out.println("2.-->"+evaluator.evaluate("toUpperCase(trim( trim(' a b c ') ))"));  //运行java中的方法
            evaluator.putVariable("a", "'Hello'");//定义字符串变量
            evaluator.putVariable("b", "'World'");
            evaluator.putVariable("c", "1"); //定义数字变量
            evaluator.putVariable("d", "2");
            System.out.println("3.-->"+evaluator.evaluate("#{a}")); //输出字符串
            System.out.println("4.-->"+evaluator.evaluate("#{b}"));
            System.out.println("5.-->"+evaluator.evaluate("#{PI}"));
            System.out.println("6.-->"+evaluator.evaluate("#{a} + ' ' + #{b} + '!'")); //拼接后输出
            System.out.println("7.-->"+evaluator.evaluate("(#{c} + #{d}) - #{c}"));  //拼接后输出计算结果

        } catch (Exception ee) {
            System.out.println(ee);
        }
    }


    public static String formatExpression(String exp){
        //英文字母變量用#{ }包裝：如變量x，#{x}
        String re = "([a-zA-Z]+)";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(exp);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "#{" + matcher.group(1) + "}");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
