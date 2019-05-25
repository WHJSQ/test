package com.example.test;

import org.dom4j.DocumentException;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * Created by Administrator on 2018/9/17.
 */
public class dateTest {
    @Test
    public  void test01() throws ParseException {
        String date = "2016-12-13";
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM");
        Date date1 = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date1);//设置当前日期
        String now =sdf.format(date1);
        System.out.println("9".equals(String.valueOf(calendar.get(Calendar.MONTH)+1)));
        calendar.add(Calendar.MONTH, 3);
        Date date2 = calendar.getTime();
        String now2 =sdf.format(date2);
        System.out.println(now2);
    }

    @Test
    public  void test02() throws ParseException {
        String date="2018-03-31";
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date1);//设置当前日期
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH)!=calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DATE));
        System.out.println(sdf.format(calendar.getTime()));
    }

    @Test
    public void test03() throws ParseException {
        String date1="2018-03-31";
        String date2="2018-03-25";
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(sdf.parse(date1));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(sdf.parse(date2));
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        System.out.println(day1+"-"+day2+"="+(day1-day2));
    }

    @Test
    public  void  test06() throws ParseException {
        SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String nowDate="2018-09-13";
        Date date = sdf1.parse(nowDate);
        String s = formatter.format(date);
        System.out.println(s);

        Date currentTime = new Date();

        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
    }

    @Test
    public  void test04(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.remove(list.get(list.size()-1));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void test05(){
        Date cur=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String today=sdf.format(cur);
        System.out.println(today);
    }

    @Test
    public void test07(){
        for (int i =0;i<10;i++){
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            System.out.println(new SimpleDateFormat("yyyyMMdd").format(date));
        }

        String num = "abcddhfh";
        System.out.println(num.substring(0,4));
    }

    public  String test08(){
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding = \"UTF-8\"?>").append("\r\n");
        sb.append("\t").append("<IN>").append("\r\n");
        sb.append("\t").append("\t").append("<FILE_TYPE>").append("  ").append("</FILE_TYPE>").append("\r\n");                                     //文件类型文件类型（必输）
        sb.append("\t").append("\t").append("<FUND_ID>").append(" ").append("</FUND_ID>").append("\r\n");
        sb.append("\t").append("\t").append("<REPORT_TYPE>").append("1073").append("</REPORT_TYPE>").append("\r\n");                               //报表类型 01:日报，不可为空（必输）
        sb.append("\t").append("\t").append("<BEGIN_DATE>").append("").append("</BEGIN_DATE>").append("\r\n");                                                //开始时间
        sb.append("\t").append("\t").append("<END_DATE>").append("").append("</END_DATE>").append("\r\n");                                                                      //结束时间
        sb.append("\t").append("\t").append("<DEPT_CODE>").append("").append("</DEPT_CODE>").append("\r\n");                                                           //公司代码（必填）
        sb.append("\t").append("\t").append("<CERT_ID>").append("").append("</CERT_ID>").append("\r\n");                                                               //证书代码（可控）
        sb.append("\t").append("\t").append("<SERIAL_NO>").append("").append("</SERIAL_NO>").append("\r\n");                                        //业务流水号(必填)
        sb.append("\t").append("\t").append("<RECORD>").append("\r\n");
        sb.append("\t").append("\t").append("\t").append("<ACCNT_CODE>").append("1232").append("</ACCNT_CODE>").append("\r\n");
        sb.append("\t").append("\t").append("\t").append("<ACCNT_NAME>").append("中国民航").append("</ACCNT_NAME>").append("\r\n");
        sb.append("\t").append("\t").append("</RECORD>").append("\r\n");
        sb.append("\t").append("\t").append("<RECORD>").append("\r\n");
        sb.append("\t").append("\t").append("\t").append("<ACCNT_CODE>").append("123456464").append("</ACCNT_CODE>").append("\r\n");
        sb.append("\t").append("\t").append("\t").append("<ACCNT_NAME>").append("中国货航").append("</ACCNT_NAME>").append("\r\n");
        sb.append("\t").append("\t").append("</RECORD>").append("\r\n");

        sb.append("\t").append("</IN>").append("\r\n");
        return sb.toString();
    }
    @Test
    public void test11() throws DocumentException {
        String str = test08();
        Document doc = DocumentHelper.parseText(str);
        Element rootElt = doc.getRootElement(); // 获取根节点
        Iterator iter = rootElt.elementIterator("RECORD");
        int i=0;
        while (iter.hasNext()){
            i++;
            System.out.println(i);
            Element recordEle = (Element) iter.next();
            String code = recordEle.elementTextTrim("ACCNT_CODE");
            String name = recordEle.elementTextTrim("ACCNT_NAME");
            System.out.println("code:"+ Double.parseDouble(code)+"--------"+"name:"+name);
        }
    }

    @Test
    public void testStr1(){
        String str = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><out><Head><ChnlNo>234</ChnlNo><FTranCode>5000</FTranCode><InstID>02345678</InstID><TrmSeqNum>201903270000154981</TrmSeqNum><TranDateTime>20190327104934</TranDateTime><ErrCode>ERR0007</ErrCode></Head><Body><ErrorCode>ERRFFFF</ErrorCode><ErrorInfo>收妥成功</ErrorInfo><Res1/><Res2/><Res3/></Body></out>";
        String code = getNodeValue(str,"ErrorCode");
        System.out.println(code);
    }

    public static String getNodeValue(String returnStr, String tagName) {
        String reStr = "";
        int startIdx, endIdx;
        String beginTag = "<" + tagName;
        String endTag = "</" + tagName + ">";
        startIdx = returnStr.indexOf(beginTag) + beginTag.length();
        while (!">".equals(returnStr.subSequence(startIdx, startIdx + 1))) {
            startIdx++;
        }
        startIdx++;
        endIdx = returnStr.indexOf(endTag, startIdx);
        try {
            reStr = returnStr.substring(startIdx, endIdx);
        } catch (RuntimeException e) {
            reStr = "";
        }
        return reStr;
    }

    @Test
    public void test09(){
        String ye = this.getNodeValue(test08(), "FILE_TYPE1").trim();
        System.out.println(!ye.equals("")&& ye!=null);
        String a= "";
        Boolean flag= !a.equals("")|| a!=null;
        String errCode ="" ;
        String errMessage ="" ;
        System.out.println((errCode!=null &&!"".equals(errCode)) || (errMessage!=null &&!"".equals(errMessage)));
    }

    @Test
    public void test10(){
            Map<String,String> map1 = new HashMap<>();
            Map<String,String> map2 = new HashMap<>();
            map1.put("1","value1");
            map1.put("2","value2");
            map2=map1;
            map2.put("3","value3");
            System.out.println(map1.get("3"));
        System.out.println(map2.get("3"));
    }

    @Test
    public void test15(){
        double yeMoney = 1.011947785E7;
        double lsMoney = 10119477.85;
        if (Math.abs((yeMoney - lsMoney)) < 0.000000001){
            System.out.println("111");
        }else{
            System.out.println("222");
        }
        double s = 1.011947785E7;
        double s1 = 10119477.85;
        System.out.println(Math.abs((s - s1)));
    }

    @Test
    public  void test16(){
        double number = 10119477.854;
        String format = "#,##0.00";
        int decs=0;//要保留的小数位数

        if(format.trim().length()==0) format="#0.00";
        int i=format.indexOf(".");
        //alex20041124，先按照format中指定的小数位数对number进行round，再格式化
        if(i>=0){
            for(decs= ++i;decs<format.length();decs++){
                if(format.charAt(decs)!='0' && format.charAt(decs)!='#') break;
            }
            decs -= i;
        }
        if(format.indexOf("%")>=0) decs +=2;//百分比

        System.out.print(new DecimalFormat(format).format(this.round(number,decs)));
    }

    public static final double round(double v,int scale){
        return round(v,scale,false);
    }

    public static final double round(double v,int scale,boolean bTrunc){
        if(scale<0)
            throw new IllegalArgumentException("小数位数不得小于0！");

        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");

        return b.divide(one,scale,bTrunc ? BigDecimal.ROUND_DOWN : BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Test
    public  void test13(){
        double s1 = 118487.21;
        double s2 = 1.000099064E7;
        double s3 = s1+s2;
        double s4 = 1.011947785E7;
        BigDecimal p4 = new BigDecimal(Double.toString(s4));
        BigDecimal p1 = new BigDecimal(Double.toString(s1));
        BigDecimal p2 = new BigDecimal(Double.toString(s2));
        double s5 =  p1.add(p2).doubleValue();
        BigDecimal p3 = new BigDecimal(Double.toString(s5));
        System.out.println(  p1.add(p2).doubleValue());
        System.out.println(s3);
        System.out.println(s3-s4);
        System.out.println(p3.subtract(p4).doubleValue() );
    }

    @Test
    public void test14(){
        HashMap<String,String> mp = new HashMap();
        mp.put("1","a");
        mp.put("2","b");
        mp.put("3","c");
        for (String string : mp.keySet()) {
            System.out.println(string);
        }
    }

    @Test
    public  void testStr(){
        String str = "12345678909876543123 45678";
        System.out.println(str.substring(6, str.length()-5));
    }

    @Test
    public  void test011(){
        boolean b  = "".contains("123");
        System.out.println(b);
    }



    @Test
    public  void Json() throws  Exception{
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("totalCount", "30");

        JSONObject user1 = new JSONObject();
        user1.put("id", "12");
        user1.put("name", "张三");
        user1.put("createTime", "2017-11-16 12:12:12");

        result.put("data",user1);
        System.out.println(result.toString());

        String char1 =null;
        System.out.println(char1.trim());
    }

    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    @Test
    public void test111() throws Exception {
        String md5Encode = this.md5Encode("123456888");
        System.out.println("123456加密后="+md5Encode);
    }
    public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "D://pyas.pfx";
    public static final String KEYSTORE_PASSWORD = "123123";
    public static final String JKS_KEYSTORE_FILE = "D://pyas.jks";
    @Test
    public  void test123(){
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(JKS_KEYSTORE_FILE);
            char[] nPassword = null;

            if ((KEYSTORE_PASSWORD == null)
                    || KEYSTORE_PASSWORD.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }

            inputKeyStore.load(fis, nPassword);
            fis.close();

            KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");

            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());

            Enumeration enums = inputKeyStore.aliases();

            while (enums.hasMoreElements()) { //   we   are   readin   just   one   certificate.

                String keyAlias = (String) enums.nextElement();

                System.out.println("alias=[" + keyAlias + "]");

                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore
                            .getCertificateChain(keyAlias);

                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD
                            .toCharArray(), certChain);
                }
            }

            FileOutputStream out = new FileOutputStream(PFX_KEYSTORE_FILE);

            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private static String getSqlStrByList(List sqhList, int splitNum,
                                          String columnName) {
        if (splitNum > 1000) // 因为数据库的列表sql限制，不能超过1000.
            return null;
        StringBuffer ids = new StringBuffer("");
        if (sqhList != null) {
            ids.append(" ").append(columnName).append(" IN ( ");
            for (int i = 0; i < sqhList.size(); i++) {
                ids.append("'").append(sqhList.get(i) + "',");
                if ((i + 1) % splitNum == 0 && (i + 1) < sqhList.size()) {
                    ids.deleteCharAt(ids.length() - 1);
                    ids.append(" ) OR ").append(columnName).append(" IN (");
                }
            }
            ids.deleteCharAt(ids.length() - 1);
            ids.append(" )");
        }
        return ids.toString();
    }
    @Test
    public  void getSqlStrByList1() {
        List list = new ArrayList();
        int id = 2;
        for(int i=0;i<15;i++){
            list.add(id);
        }
        String ss = getSqlStrByList(list, 10,"test");
        System.out.println(ss);

    }


}
