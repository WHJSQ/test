package com.example.test.demo;

import com.example.test.HttpUtils;
import com.example.test.JsonUtils;
import com.example.test.PabSignatureUtils;

import java.util.HashMap;
import java.util.Map;




public class TestDpayQuery {
	public static void main(String[] args) {
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("orderId","340563a9fd2f474caed424aaabdca0ea");
			map.put("customerId","12345678");
			map.put("version","1.0.0");
			map.put("requestTime","20181214173800");
			String json = JsonUtils.toJsonString(map);
			
			byte[] key = ":+_)(*&^%$#@!`~nhyu/;|ki".getBytes();
			String mw = PabSignatureUtils.encode(map.get("customerId"),json,key);
			System.out.println("密文="+mw);
			String res = HttpUtils.doPost("http://10.10.128.82:8083/proxy-infront/unionpay/dpay/query", mw);
			System.out.println("返回密文="+res);
			String plaintext1 = PabSignatureUtils.decode(res,key);
			System.out.println("返回明文="+plaintext1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
