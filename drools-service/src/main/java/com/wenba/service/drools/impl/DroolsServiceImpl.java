package com.wenba.service.drools.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.KeyConvertor;
import com.github.pagehelper.PageHelper;
import com.wenba.domain.*;
import com.wenba.reposity.drools.*;
import com.wenba.service.drools.DroolsService;
import com.wenba.utils.base.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Slf4j
@Service("droolsService")
public class DroolsServiceImpl implements DroolsService {


    @Autowired
    private DroolsRulePTypeDao droolsRulePTypeDao;

    @Autowired
    private DroolsInfoDao droolsInfoDao;

    @Autowired
    private DroolsVersDao droolsVersDao;

    @Autowired
    private DroolsRuleInputPDao droolsRuleInputPDao;

    @Autowired
    private DroolsRuleDao droolsRuleDao;

    @Value("${drools.path}")
    private String PATH;

    @Value("${drools.package}")
    private String PACKAGE;

    @Value("${drools.kmodule}")
    private String KMODULE;


    /*
     *   name 缓存名称   默认类名 + 方法名
     *   localLimit 本地缓存容量   默认100
     *   expire  默认无穷大
     */
    @CreateCache(name = "droolsSerivceCache", cacheType = CacheType.LOCAL,
            localExpire = 365 * 24 * 60 * 60, localLimit =1000000,
            keyConvertor = KeyConvertor.FASTJSON
    )

    private Cache<Integer,DroolsInfo> cache;
    private static volatile boolean isInit = false;

    /*
     *   初始化缓存
     */
    private void initCache() {
        if(!isInit) {
            synchronized (DroolsServiceImpl.class) {
                if(!isInit) {
                    int page = 1;
                    int pageSize = 1000;
                    while(true) {
                        PageHelper.startPage(page,pageSize);

                        List<DroolsInfo> list = droolsInfoDao.selectDroolsInfo(null);
                        PageHelper.clearPage();
                        if(null == list || list.size() == 0) {
                            break;
                        }else {
                            for(DroolsInfo di : list) {
                                cache.put(di.getId(),di);
                            }
                        }
                    }
                }
            }
        }
    }




    /*
     *   生成规则版本实体类
     */
    @Override
    public boolean createDroolsVersBean(List<DroolsRuleBean> pList) {

        boolean b = false;

        for(DroolsRuleBean drb : pList) {

            //查询规则id的名称
            drb.setDroolsName(droolsInfoDao.selectDroolsInfoById(drb.getDroolsId()));

            String beanName = drb.getDroolsName() + drb.getDroolsVers();

            //生成实体类
            b = GenDroolsEntityUtil(beanName, drb.getDroolsId(), drb.getDroolsVers());
        }
        return b;
    }

    /*
     *   生成规则版本实体类
     */
    @Override
    public Map<Boolean,Map<String,List<Map<Integer,String>>>> createDroolsVersBean(DroolsRuleBean drb) {

        Map<Boolean,Map<String,List<Map<Integer,String>>>> map = new HashMap<>();
        Map<String,List<Map<Integer,String>>> m = new HashMap<>();
        List<Map<Integer,String>> list = new ArrayList<>();
        Map<Integer,String> pMap = new HashMap<>();
        boolean b = false;

        //查询规则id的名称
        drb.setDroolsName(droolsInfoDao.selectDroolsInfoById(drb.getDroolsId()));

        String beanName = drb.getDroolsName() + drb.getDroolsVers();

        //查询要生成实体类数据
        List<DroolsInOutParams> ioplist =  droolsRuleDao.selectDroolsBean(drb.getDroolsId(), drb.getDroolsVers());

        if(null != ioplist && ioplist.size() > 0) {
            int size = ioplist.size();	//统计列
            colNames = new String[size];
            colTypes = new String[size];
            colSources = new Integer[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colNames[i] = ioplist.get(i).getpName();
                colTypes[i] = ioplist.get(i).getpType();
                colSources[i] = ioplist.get(i).getpSource();
                pMap.put(colSources[i],colNames[i]);
                if(colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("timestamp")){
                    f_util = true;
                }
                if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
                    f_sql = true;
                }
                //colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            list.add(pMap);
        }else{
            map.put(b,m);
            return map;
        }

        String content = parse(beanName,colNames,colTypes,colSizes);

        try {
            File directory = new File("/Users/wenba/IdeaProjects/wenba-drools/drools-common/src/main/java/com/wenba/domain");
            //System.out.println("绝对路径："+directory.getAbsolutePath());
            //System.out.println("相对路径："+directory.getCanonicalPath());
            //String path=this.getClass().getResource("").getPath();

            //System.out.println(path);
            //System.out.println("src/?/"+path.substring(path.lastIndexOf("/com/", path.length())) );
            //String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tablename) + ".java";
            //String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/" + initcap(tablename) + ".java";

            File file = new File(directory.getAbsolutePath());
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = directory.getAbsolutePath() + File.separator + initcap(beanName) + ".java";

            FileWriter fw = new FileWriter(fileName);

            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
            b = true;

            //记录生成实体类名称
            int j = droolsRulePTypeDao.updateDrBeanName(initcap(beanName),drb.getDroolsId(),drb.getDroolsVers());
            if(j > 0) {
                System.out.println(drb.getDroolsId() + "-" + drb.getDroolsVers() + "的" + initcap(beanName) + " 实体类生成成功!");
                log.info(drb.getDroolsId() + "-" + drb.getDroolsVers() + "的" + initcap(beanName) + " 实体类生成成功!");
            }else{
                System.out.println(drb.getDroolsId() + "-" + drb.getDroolsVers() + "的" + initcap(beanName) + " 实体类生成失败!");
                log.info(drb.getDroolsId() + "-" + drb.getDroolsVers() + "的" + initcap(beanName) + " 实体类生成失败!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            map.put(b,m);
            return map;
        }
        m.put(initcap(beanName),list);
        map.put(b,m);
        return map;
    }

    private String packageOutPath = "com.wenba.domain";//指定实体生成所在包的路径
    private String authorName = "GaoBin";//作者名字
    //private String tablename = "";//表名
    private String[] colNames; // 列名数组
    private String[] colTypes; //列名类型数组
    private Integer[] colSources; //列名来源数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    private boolean GenDroolsEntityUtil(String beanName, Integer droolsId, String droolsVers) {

        //查询要生成实体类数据
        List<DroolsInOutParams> ioplist =  droolsRuleDao.selectDroolsBean(droolsId,droolsVers);
        if(null != ioplist && ioplist.size() > 0) {
            int size = ioplist.size();	//统计列
            colNames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colNames[i] = ioplist.get(i).getpName();
                colTypes[i] = ioplist.get(i).getpType();

                if(colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("timestamp")){
                    f_util = true;
                }
                if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
                    f_sql = true;
                }
                //colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
        }else{
            return false;
        }

        String content = parse(beanName,colNames,colTypes,colSizes);

        try {
            File directory = new File("/Users/wenba/IdeaProjects/wenba-drools/drools-common/src/main/java/com/wenba/domain");
            //System.out.println("绝对路径："+directory.getAbsolutePath());
            //System.out.println("相对路径："+directory.getCanonicalPath());
            //String path=this.getClass().getResource("").getPath();

            //System.out.println(path);
            //System.out.println("src/?/"+path.substring(path.lastIndexOf("/com/", path.length())) );
            //String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tablename) + ".java";
            //String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/" + initcap(tablename) + ".java";

            File file = new File(directory.getAbsolutePath());
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = directory.getAbsolutePath() + File.separator + initcap(beanName) + ".java";

            FileWriter fw = new FileWriter(fileName);

            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能：生成实体类主体代码
     * @param colNames
     * @param colTypes
     * @param colSizes
     * @return
     */

    private String parse(String beanName,String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");
        sb.append("import java.io.Serializable;");
        sb.append("\r\n");

        //判断是否导入工具包
        if(f_util){
            sb.append("import java.util.Date;\r\n");
        }
        if(f_sql){
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        //注释部分
        sb.append("\t/**\r\n");
        //sb.append("    * "+tablename+" 实体类\r\n");
        sb.append("    * "+beanName+" 实体类\r\n");
        sb.append("    * "+new Date()+" "+this.authorName+"\r\n");
        sb.append("    */ \r\n");
        //实体部分
        //sb.append("\r\n\r\npublic class " + initcap(tablename) + "{\r\n");
        sb.append("\r\n\r\npublic class " + initcap(beanName) + " implements Serializable " + "{\r\n");
        sb.append("\r\n");
        sb.append("\tprivate static final long serialVersionUID = 1L;");
        sb.append("\r\n");
        sb.append("\r\n");
        processAllAttrs(sb);//属性
        sb.append("\r\n");
        processAllMethod(sb);//get set方法
        sb.append("\r\n");
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colNames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colNames[i] + ";\r\n");

            sb.append("\r\n");
        }

        sb.append("\tprivate String droolsNameVers;");
        sb.append("\r\n");


    }

    /**
     * 功能：生成所有方法
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colNames.length; i++) {

            sb.append("\tpublic void set" + initcap(colNames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                    colNames[i] + "){\r\n");
            sb.append("\tthis." + colNames[i] + "=" + colNames[i] + ";\r\n");
            sb.append("\t}\r\n");

            sb.append("\r\n");

            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colNames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colNames[i] + ";\r\n");
            sb.append("\t}\r\n");

            sb.append("\r\n");
        }

        sb.append("\tpublic String getDroolsNameVers() {\n" +
                "        return droolsNameVers;\n" +
                "    }");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("\tpublic void setDroolsNameVers(String droolsNameVers) {\n" +
                "        this.droolsNameVers = droolsNameVers;\n" +
                "    }");
        sb.append("\r\n");

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if(sqlType.equalsIgnoreCase("bit")){
            return "boolean";
        }else if(sqlType.equalsIgnoreCase("tinyint")){
            return "byte";
        }else if(sqlType.equalsIgnoreCase("smallint")){
            return "short";
        }else if(sqlType.equalsIgnoreCase("int")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("bigint")){
            return "long";
        }else if(sqlType.equalsIgnoreCase("float")){
            return "float";
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")){
            return "double";
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("datetime")  || sqlType.equalsIgnoreCase("timestamp")){
            return "Date";
        }else if(sqlType.equalsIgnoreCase("image")){
            return "Blod";
        }

        return null;

    }


    @Override
    public List<Map<String, DataResult<String>>> createDroolsRule(DroolsRule drP) {

        //返回数据
        List<Map<String, DataResult<String>>> list = new ArrayList<>();
        Map<String, DataResult<String>> map = new HashMap<>();

        //从数据库中取drools的drl信息
        List<DroolsRule> drList = droolsRuleDao.selectDroolsRuleInfo(drP);
        //生产drools的drl文件
        String dir = PATH;
        //String dir = "/Users/wenba/IdeaProjects/wenba-example/example-api/src/main/resources/rules";
        //String fileName = "Template.drl";

        if(null != drList && drList.size() > 0) {
            drP.setDroolsName(drList.get(0).getDroolsName());
            String beanName = drP.getDroolsName() + drP.getDroolsVers();
            /*GenDroolsEntityUtil(beanName,dr.getDroolsId(),dr.getDroolsVers());*/

            DataResult<String> dataResult = new DataResult<>();

            String name = drP.getDroolsName() + "-" + drP.getDroolsVers();
            String namePath = name;
            String dirPath = dir + File.separator + namePath + File.separator;
            File dirFile = new File(dirPath);
            if (dirFile.exists()) {// 判断目录是否存在
                dataResult.setCode(-1);
                dataResult.setMsg(drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 创建目录失败，目标目录已存在！");
                map.put(drP.getDroolsId() + "-" + drP.getDroolsVers(),dataResult);
                log.info("++++ 注意 ++++ " + drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 创建目录失败，目标目录已存在！");
            }
            if (!name.endsWith(File.separator)) {// 结尾是否以"/"结束
                namePath = namePath + File.separator;
            }
            if (dirFile.mkdirs()) {// 创建目标目录
                log.info("++++ 注意 ++++ " + drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 创建目录成功！" + namePath);
            } else {
                dataResult.setCode(-1);
                dataResult.setMsg(drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 创建目录失败！");
                map.put(drP.getDroolsId() + "-" + drP.getDroolsVers(),dataResult);
                log.info("++++ 注意 ++++ " + drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 创建目录失败！");
            }

            String namePart1 = name.substring(0, 1).toUpperCase();
            String namePart2 = name.substring(1,name.length());

            String fileName = namePart1 + namePart2 + ".drl";

            File file = new File(dirPath, fileName);
            if(file.exists()){
                dataResult.setCode(-1);
                dataResult.setMsg(drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 该规则文件已存在！");
                map.put(drP.getDroolsId() + "-" + drP.getDroolsVers(),dataResult);
                log.info("++++ 注意 ++++ " + drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 该规则文件已存在！");
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getName());
                System.out.println(file.length());
            }else {
                file.getParentFile().mkdirs();
            }

            try {
                FileOutputStream fOutputStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(fOutputStream, "utf-8");
                writer.append(PACKAGE + "." + initcap(beanName) + ";");
                writer.append("\r\n");

                for(DroolsRule dr : drList) {
                    /*//生成实体类
                    String beanName = dr.getDroolsName() + dr.getDroolsVers();
                    *//*GenDroolsEntityUtil(beanName,dr.getDroolsId(),dr.getDroolsVers());*//*

                    DataResult<String> dataResult = new DataResult<>();

                    String name = dr.getDroolsName() + "-" + dr.getDroolsVers();
                    String namePath = name;
                    String dirPath = dir + File.separator + namePath + File.separator;
                    File dirFile = new File(dirPath);
                    if (dirFile.exists()) {// 判断目录是否存在
                        dataResult.setCode(-1);
                        dataResult.setMsg(dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 创建目录失败，目标目录已存在！");
                        map.put(dr.getDroolsId() + "-" + dr.getDroolsVers(),dataResult);
                        log.info("++++ 注意 ++++ " + dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 创建目录失败，目标目录已存在！");
                        continue;
                    }
                    if (!name.endsWith(File.separator)) {// 结尾是否以"/"结束
                        namePath = namePath + File.separator;
                    }
                    if (dirFile.mkdirs()) {// 创建目标目录
                        log.info("++++ 注意 ++++ " + dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 创建目录成功！" + namePath);
                    } else {
                        dataResult.setCode(-1);
                        dataResult.setMsg(dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 创建目录失败！");
                        map.put(dr.getDroolsId() + "-" + dr.getDroolsVers(),dataResult);
                        log.info("++++ 注意 ++++ " + dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 创建目录失败！");
                        continue;
                    }

                    String namePart1 = name.substring(0, 1).toUpperCase();
                    String namePart2 = name.substring(1,name.length());

                    String fileName = namePart1 + namePart2 + ".drl";

                    File file = new File(dirPath, fileName);
                    if(file.exists()){
                        dataResult.setCode(-1);
                        dataResult.setMsg(dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 该规则文件已存在！");
                        map.put(dr.getDroolsId() + "-" + dr.getDroolsVers(),dataResult);
                        log.info("++++ 注意 ++++ " + dr.getDroolsId() + "-" + dr.getDroolsVers() + " - 该规则文件已存在！");
                        System.out.println(file.getAbsolutePath());
                        System.out.println(file.getName());
                        System.out.println(file.length());
                        continue;
                    }else{
                        file.getParentFile().mkdirs();*/

                        //向template.drl写规则

                    //writer.append("import com.wenba.domain");
                    writer.append("\r\n");
                    writer.append("rule" + "\u0009" + "\"" + dr.getRuleDetailName() + "\"");
                    writer.append("\r\n");
                    writer.append("\t" + "no-loop" + "\u0009" + dr.getNoLoop());
                    writer.append("\r\n");
                    writer.append("\t" + "lock-on-active" + "\u0009" + dr.getLockOnActive());
                    writer.append("\r\n");
                    writer.append("\t" + "salience" + "\u0009" + dr.getSalience());
                    writer.append("\r\n");
                    writer.append("\t" + "when");
                    writer.append("\r\n");
                    writer.append("\t" + "\u0009" + dr.getRuleIf());
                    writer.append("\r\n");
                    writer.append("\t" + "then");
                    writer.append("\r\n");
                    writer.append("\t" + "\u0009" + dr.getRuleThen());
                    writer.append("\r\n");
                    writer.append("end");
                    writer.append("\r\n");

                    /*}*/
                }
                writer.close();
                fOutputStream.close();

                FileInputStream fInputStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fInputStream,"UTF-8");
                StringBuilder sBuilder = new StringBuilder();

                while(reader.ready()){
                    sBuilder.append((char)reader.read());
                }

                reader.close();
                fInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //读取kmodul内容
            RandomAccessFile randomFile = null;

            /*
             *   返回结果
             */
            dataResult.setCode(1);
            dataResult.setMsg("成功");
            dataResult.setData(drP.getDroolsName() + "-" + drP.getDroolsVers() + "KS");
            map.put(drP.getDroolsId() + "-" + drP.getDroolsVers(),dataResult);

            StringBuilder sbContent = new StringBuilder();
                        /*sbContent.append("\r\n\t<kbase name=\"" + dr.getDroolsName() + dr.getDroolsVers() + "KB\" packages=\"rules\">\n" +
                                "\t\t<ksession name=\"" + dr.getDroolsName() + dr.getDroolsVers() + "KS\"/>\r\n" +
                                "\t</kbase>\r\n");*/

            sbContent.append("\r\n");
            sbContent.append("\r\n\t");
            sbContent.append("<kbase name=\"" + name + "KB\" packages=\"rules." + name + "\">");
            sbContent.append("\r\n\t\t");
            sbContent.append("<ksession name=\"" + name + "KS\"/>");
            sbContent.append("\r\n\t");
            sbContent.append("</kbase>");
            sbContent.append("\r\n");
            sbContent.append("\r\n");
            sbContent.append("</kmodule>");

            try {
                // 打开一个随机访问文件流，按读写方式
                randomFile = new RandomAccessFile(KMODULE,"rw");
                // 文件长度，字节数
                long fileLength = randomFile.length();
                // 将写文件指针移到文件尾。
                randomFile.seek(fileLength - 11);
                //randomFile.writeBytes(sbContent.toString());
                randomFile.write(sbContent.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                if(randomFile != null){
                    try {
                        randomFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            log.info("++++ 注意 ++++ " + drP.getDroolsId() + "-" + drP.getDroolsVers() + " - 的规则drl生成成功!");
            list.add(map);
        }else {
            DataResult<String> dataResult = new DataResult<>();
            dataResult.setCode(-1);
            dataResult.setMsg("该规则信息不存在!");
            log.info("++++ 注意 ++++  该规则信息不存在!");
            list.add(map);
        }
        return list;

    }



    @Override
    public List<DataResult<String>> tranDroolsRuleObj(List<Object> list) {

        System.out.println("进入");
        List<DataResult<String>> resultList = new ArrayList<>();

        /*XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{DroolsService.class,DroolsServiceImpl.class});*/

        //初始化drools
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        if (null != list && list.size() > 0) {
            for (Object o : list) {
                try {

                    DataResult<String> dr = new DataResult<>();

                    String droolsNameVers = o.getClass().getDeclaredField("droolsNameVers").get(o).toString();

                    if(null != droolsNameVers && ! "".equals(droolsNameVers)) {
                        //读取drl文件
                        KieSession ksession = kc.newKieSession(droolsNameVers);
                        if(null == ksession) {
                            dr.setCode(-1);
                            dr.setMsg("该规则模板未生成!");
                            resultList.add(dr);
                            continue;
                        }
                        log.info("信息:" + o.toString());
                        ksession.insert(o);
                        ksession.fireAllRules();
                        //addScore(o);
                        ksession.dispose();

                        dr.setCode(1);
                        dr.setMsg("success");
                        //出参
                        //dr.setData(o);
                        resultList.add(dr);
                    }else{
                        dr.setCode(-1);
                        dr.setMsg("该规则模板未生成!");
                        resultList.add(dr);
                        continue;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    @Override
    public List<DataResult<String>> tranDroolsRule(List<InvestSendIntegralv1> list) {

        System.out.println("进入");
        List<DataResult<String>> resultList = new ArrayList<>();

        /*XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{DroolsService.class,DroolsServiceImpl.class});*/

        //初始化drools
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        if (null != list && list.size() > 0) {
            for (InvestSendIntegralv1 dp : list) {
                try {
                    DataResult<String> dr = new DataResult<>();

                    //读取drl文件
                    KieSession ksession = kc.newKieSession(dp.getDroolsNameVers());
                    if (null == ksession) {
                        dr.setCode(-1);
                        dr.setMsg("该规则模板未生成!");
                        resultList.add(dr);
                        continue;
                    }
                    log.info("信息:" + dp.toString());
                    ksession.insert(dp);
                    ksession.fireAllRules();
                    addScore(dp);
                    ksession.dispose();

                    dr.setCode(1);
                    dr.setMsg("success");
                    dr.setData(dp.getOutParam() + "");
                    resultList.add(dr);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }



    private static void addScore(InvestSendIntegralv1 o){
        System.out.println("用户参数:" + o.getInParam() + ",  用户结果:" + o.getOutParam());
    }


    /*
     *   drools_rule_p_type
     *   新增参数信息
     *
     */
    @Override
    public boolean insertParamsInfo(List<DroolsRulePType> listDrpt) {
        return droolsRulePTypeDao.insertParamsInfo(listDrpt);
    }

    @Override
    public boolean insertParamsInfoSin(DroolsRulePType drpt) {
        return droolsRulePTypeDao.insertParamsInfoSin(drpt);
    }

    //校验参数类型是否已存在
    @Override
    public int checkParamsInfo(String getpType) {
        return droolsRulePTypeDao.checkParamsInfo(getpType);
    }


    /*
     *   drools_rule_p_type
     *   更新参数
     */
    @Override
    public int updateParamsInfo(List<DroolsRulePType> listDrpt) {
        return droolsRulePTypeDao.updateParamsInfo(listDrpt);
    }

    @Override
    public int updateParamsInfoSin(DroolsRulePType drpt) {
        return droolsRulePTypeDao.updateParamsInfoSin(drpt);
    }


    /*
     *   drools_rule_p_type
     *  查询所有的参数信息id
     */
    @Override
    public List<DroolsRulePType> selectParamsInfoIds() {
        return droolsRulePTypeDao.selectParamsInfoIds();
    }



    /*
     *   drools_rule_p_type
     *  查询所有的参数信息
     */
    @Override
    public int selectParamsInfoListNum(DroolsRulePType drpt) {
        return droolsRulePTypeDao.selectParamsInfoListNum(drpt);
    }
    @Override
    public List<DroolsRulePType> selectParamsInfo(List<Integer> listIds) {
        return droolsRulePTypeDao.selectParamsInfo(listIds);
    }
    @Override
    public List<DroolsRulePType> selectParamsInfoList(DroolsRulePType drpt) {
        return droolsRulePTypeDao.selectParamsInfoList(drpt);
    }



    /*
     *   drools_info
     *   新增规则信息
     *
     */
    @Override
    public boolean insertDroolsInfo(List<DroolsInfo> listDi) {
        return droolsInfoDao.insertDroolsInfo(listDi);
    }
    @Override
    public boolean insertDroolsInfoSin(DroolsInfo di) {
        return droolsInfoDao.insertDroolsInfoSin(di);
    }


    /*
     *   drools_info
     *   更新规则信息
     */
    @Override
    public int updateDroolsInfoSin(DroolsInfo di) {
        return droolsInfoDao.updateDroolsInfoSin(di);
    }

    @Override
    public int updateDroolsInfo(List<DroolsInfo> listDi) {
        return droolsInfoDao.updateDroolsInfo(listDi);
    }


    /*
     *   drools_info
     *   查询所有的规则信息id
     */
    @Override
    public List<DroolsInfo> selectDroolsInfoIds() {
        return droolsInfoDao.selectDroolsInfoIds();
    }


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    @Override
    public int selectDroolsInfoListNum(DroolsInfo di) {
        return droolsInfoDao.selectDroolsInfoListNum(di);
    }
    @Override
    public DroolsInfo selectDroolsInfoSin(int id) {
        return droolsInfoDao.selectDroolsInfoSin(id);
    }
    @Override
    public List<DroolsInfo> selectDroolsInfoList(DroolsInfo di) {
        return droolsInfoDao.selectDroolsInfoList(di);
    }


    /*
     *   drools_info
     *   查询所有的规则信息
     */
    @Override
    public List<DroolsInfo> selectDroolsInfo(List<Integer> listI) {
        return droolsInfoDao.selectDroolsInfo(listI);
    }


    /*
     *   drools_vers
     *   新增规则版本信息
     */
    @Override
    public boolean insertDroolsVers(List<DroolsVers> listDv) {
        return droolsVersDao.insertDroolsVers(listDv);
    }

    //校验规则版本是否已存在
    @Override
    public int checkDroolsVers(Integer droolsId) {
        return droolsInfoDao.checkDroolsVers(droolsId);
    }


    /*
     *   drools_info
     *   更新规则版本信息
     */
    @Override
    public int updateDroolsVers(List<DroolsVers> listDv) {
        return droolsVersDao.updateDroolsVers(listDv);
    }


    /*
     *   drools_vers
     *   查询所有规则版本信息
     */
    @Override
    public List<DroolsVers> selectDroolsVers(List<DroolsVers> listDv) {
        return droolsVersDao.selectDroolsVers(listDv);
    }

    @Override
    public List<DroolsIdVers> selectDroolsInfoIdName() {
        return droolsVersDao.selectDroolsInfoIdName();
    }
    @Override
    public List<DroolsIdVers> selectDroolsInfoVers() {
        return droolsVersDao.selectDroolsInfoVers();
    }




    /*
     *   drools_rule_input_p
     *   新增输入参数信息
     */
    @Override
    public boolean insertDroolsRuleInputP(List<DroolsRuleInputP> listDrip) {
        return droolsRuleInputPDao.insertDroolsRuleInputP(listDrip);
    }


    //校验输入参数的规则和版本是否已存在
    @Override
    public int checkDroolsRuleInputP(Integer droolsId, String droolsVers) {
        return droolsRuleInputPDao.checkDroolsRuleInputP(droolsId,droolsVers);
    }


    //校验输入参数的规则和版本及参数类型是否已存在
    @Override
    public int checkCreateDroolsRuleInputP(Integer droolsId, String droolsVers, Integer pTypeId) {
        return droolsRuleInputPDao.checkCreateDroolsRuleInputP(droolsId,droolsVers,pTypeId);
    }

    /*
     *   drools_rule_input_p
     *  更新输入参数信息
     */
    @Override
    public int updateDroolsRuleInputP(List<DroolsRuleInputP> listDrip) {
        return droolsRuleInputPDao.updateDroolsRuleInputP(listDrip);
    }


    /*
     *   drools_rule_input_p
     *   查询所有的输入参数信息
     */
    @Override
    public List<DroolsRuleInputP> selectDroolsRuleInputP(List<DroolsRuleInputP> listDrip) {
        return droolsRuleInputPDao.selectDroolsRuleInputP(listDrip);
    }


    /*
     *   drools_rule
     *   新增具体规则信息
     */
    @Override
    public boolean insertDroolsRule(List<DroolsRule> listDr) {
        return droolsRuleDao.insertDroolsRule(listDr);
    }


    /*
     *   drools_rule
     *   更新具体规则信
     */
    @Override
    public int updateDroolsRule(List<DroolsRule> listDr) {
        return droolsRuleDao.updateDroolsRule(listDr);
    }


    /*
     *   drools_rule
     *   查询所有具体规则信息
     */
    @Override
    public List<DroolsRule> selectDroolsRule(List<DroolsRule> listDr) {
        return droolsRuleDao.selectDroolsRule(listDr);
    }

    //检验规则名称是否存在
    @Override
    public int checkDroolsInfo(String droolsName) {
        return droolsInfoDao.checkDroolsInfo(droolsName);
    }

    //校验实体类是否存在
    @Override
    public int checkDroolsRuleBean(Integer droolsId, String droolsVers) {
        return droolsRulePTypeDao.checkDroolsRuleBean(droolsId, droolsVers);
    }

    //删除规则信息
    @Override
    public int deleteDroolsInfoSin(int id) {
        return droolsInfoDao.deleteDroolsInfoSin(id);
    }

}
