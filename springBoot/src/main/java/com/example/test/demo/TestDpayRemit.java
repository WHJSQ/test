package com.example.test.demo;


import com.example.test.HttpUtils;
import com.example.test.JsonUtils;
import com.example.test.PabSignatureUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TestDpayRemit {
	public static void main(String[] args) {
		try {
			Map<String,String> map = new HashMap<String,String>();
			String orderId = UUID.randomUUID().toString().replace("-", "");
			map.put("orderId",orderId);
			//map.put("batchId", orderId);
			map.put("customerId", "12345678");
			map.put("payee", "张三");
			map.put("bankCode", "ICBC");
			map.put("bankName", "中国工商银行");
			map.put("bankNo", "6214920200299203");
			map.put("accAttr","0");
			map.put("tradeType", "T0");
			map.put("tradeAmount", "1001");//分
			map.put("productCode", "123456");
			map.put("productName", "天弘基金");
			map.put("tradeTime", "20181214");
			map.put("notifyUrl", "http://10.10.128.82:8083/proxy-infront/callback/test");
			map.put("version", "1.0.0");
			map.put("remark", "付款");
			map.put("requestTime", "20181214120000");
			String json = JsonUtils.toJsonString(map);
			byte[] key = ":+_)(*&^%$#@!`~nhyu/;|ki".getBytes();
			String mw = PabSignatureUtils.encode(map.get("customerId"),json,key);
			System.out.println("密文="+mw);
			
			String res = HttpUtils.doPost("http://10.10.128.82:8083/proxy-infront/unionpay/dpay/remit", mw);
			System.out.println("返回密文="+res);
			String plaintext1 = PabSignatureUtils.decode(res,key);
			System.out.println("返回明文="+plaintext1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
