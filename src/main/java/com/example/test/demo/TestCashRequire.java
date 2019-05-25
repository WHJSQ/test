package com.example.test.demo;

import com.example.test.HttpUtils;
import com.example.test.JsonUtils;
import com.example.test.PabSignatureUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TestCashRequire {
	private static Random random = new Random();
	public static void main(String[] args) {
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("requestId", UUID.randomUUID().toString().replace("-", ""));
			map.put("customerId", "12345678");
			map.put("tradeAmount", "100000");
			map.put("tradeType", "T0");
			map.put("requestDate", "20181214");
			map.put("requestTime", "20181214144700");
			map.put("version", "1.0.0");
			String json = JsonUtils.toJsonString(map);
			byte[] key = "123456789012345678901123".getBytes();
			//random.nextBytes(key);
			System.out.println(json);
			String mw = PabSignatureUtils.encode(map.get("customerId"),json,key);
			System.out.println("密文===="+mw);
			
			String s = HttpUtils.doPost("http://10.10.128.82:8083/proxy-infront/unionpay/dpay/cashRequire", mw);
			//String s = HttpUtils.doPost("http://xmmcfp.95516.com:18080/proxy-infront/unionpay/dpay/cashRequire", mw);
			System.out.println("返回密文="+s);
			String plaintext1 = PabSignatureUtils.decode(s,key);
			System.out.println("返回明文="+plaintext1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
