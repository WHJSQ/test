package com.example.test;

import com.example.pojo.User;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/3.
 */
public class demoTest {

    public String makeMd5(String content,String key) throws UnsupportedEncodingException {
        {
            String str =content+ key;
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                return "";
            }
            byte[] byteArray = str.getBytes("GBK");
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
    }

    @Test
    public  void test1() throws UnsupportedEncodingException {
        //String str = "000464<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?><Out><Head><ChnlNo>859</ChnlNo><FTranCode>5001</FTranCode> <InstID>08599470</InstID> <TrmSeqNum>201904030000124213</TrmSeqNum><TranDateTime>20190403155707</TranDateTime><ErrCode>ERR0000</ErrCode></Head><Body><ErrorCode>ERR0000</ErrorCode><ErrorInfo>交易成功</ErrorInfo><Res1></Res1><Res2></Res2><Res3></Res3></Body></Out>02fe2f0223bf9c8eee9f3fd51908c2f2";
        String str = "000468<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n" +
                "<Out>\n" +
                "  <Head>\n" +
                "    <ChnlNo>859</ChnlNo>\n" +
                "    <FTranCode>5000</FTranCode>\n" +
                "    <InstID>08599470</InstID>\n" +
                "    <TrmSeqNum>201904030000124213</TrmSeqNum>\n" +
                "    <TranDateTime>20190403133703</TranDateTime>\n" +
                "    <ErrCode>ERR0013</ErrCode>\n" +
                "  </Head>\n" +
                "  <Body>\n" +
                "    <ErrorCode>ERR0013</ErrorCode>\n" +
                "    <ErrorInfo>交易金额异常</ErrorInfo>\n" +
                "    <Res1></Res1>\n" +
                "    <Res2></Res2>\n" +
                "    <Res3></Res3>\n" +
                "  </Body>\n" +
                "</Out>b0a8c13625b3ec993573a14f689cfbc9\n";
        System.out.println(str.endsWith("\n"));

        String xmlConten = str.substring(6, str.length()-33);
        String resMd5 = str.substring(str.length()-33);
        String s =  makeMd5(xmlConten,"123456");

        System.out.println(xmlConten);
        System.out.println(resMd5);
        System.out.println(s);
    }
    @Test
    public void test2(){
        String s = "3";
        System.out.println(s.equals("3")||s==null?"true":"false");
    }

    @Test
    public void outputExcelData() throws Exception {
        /**定义需要导出的实体*/
        List result = new ArrayList();
/*        User user = new User();
        user.setId("1");
        user.setName("yfli");
        result.add(user);
        User user2 = new User();
        user2.setId("1");
        user2.setName("zhangjie");
        result.add(user2);
        User user3 = new User();
        user3.setId("1");
        user3.setName("lzhang");
        result.add(user3);*/

        for (int i =0;i<8000;i++){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("lzhang");
            result.add(user);
        }

        //创建文件本地文件
        String fileName = "E:\\sfData.xlsx";

        //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
        WritableWorkbook wwb = Workbook.createWorkbook(new File(fileName));
        File dbfFile = new File(fileName);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }

        int totle = result.size();//获取List集合的size
        //int mus = 2;//每个工作表格最多存储2条数据（注：excel表格一个工作表可以存储65536条）
       // int avg = totle / mus;
        for (int i = 0; i < totle ; i++) {
            WritableSheet ws = wwb.createSheet("列表" + (i + 1), i);  //创建一个可写入的工作表

            User use = (User) result.get(i);

            //添加excel表头
            ws.addCell(new Label(0, 0, "序号"));
            ws.addCell(new Label(1, 0, "姓名"));
            ws.addCell(new Label(0,  1, use.getId()));
            ws.addCell(new Label(1,  1, use.getName()));

        }
        wwb.write();//从内存中写入文件中
        wwb.close();//关闭资源，释放内存
    }
}
