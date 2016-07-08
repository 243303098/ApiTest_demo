package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Request;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({ boc.api.ass.AssertionListener.class })
public class AutoInvestandUnInvestApply_T {
	String AUTHCODE = null;
	@Test(dataProvider = "autoInvest_dp")
	public void A_autoInvest(String cardNbr,String product,String amount,String authCode,String intDate,String intType,String intPayDay,String endDate,String yield,String frzFlag,String remark) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cardNbr", cardNbr);
		jsonObject.put("product", product);
		jsonObject.put("amount", amount);
		jsonObject.put("authCode", authCode);
		jsonObject.put("intDate", intDate);
		jsonObject.put("intType", intType);
		jsonObject.put("intPayDay", intPayDay);
		jsonObject.put("endDate", endDate);
		jsonObject.put("yield", yield);
		jsonObject.put("frzFlag", frzFlag);
		jsonObject.put("remark", remark);
		
			String content="";
			//参数值
			HttpClient httpClient=new HttpClient();
			//httpClient.getHostConfiguration().setProxy("127.0.0.1", 8888);
			PostMethod postMethod = new PostMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/autoinvest/transaction");
			postMethod.setRequestHeader("Content-Type","application/json");
			postMethod.setRequestBody(jsonObject.toString());
			
				//执行远程连接,并返回结果
			    try {
			    	httpClient.executeMethod(postMethod);
			    	//获得响应内容String格式
					content=postMethod.getResponseBodyAsString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			   System.out.println(content); 
			   
			   JSONObject json = JSONObject.fromObject(content);
			   AUTHCODE = json.get("AUTHCODE").toString();
			   System.out.println(AUTHCODE);
			   Assertion.verifyEquals(json.get("responseCode"),"1");
	}
	/*P2P产品投标撤销接口*/
	@Test(dataProvider = "unInvestApply_dp")
	public void B_unInvestApply_T(String cardNbr,String authCode){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cardNbr", cardNbr);
		jsonObject.put("authCode", authCode);
		
		String content = null;
		HttpClient httpClient = new HttpClient();
		PutMethod putMethod = new PutMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/invest/unInvestApply");
		putMethod.setRequestHeader("Content-Type","application/json");
		putMethod.setRequestBody(jsonObject.toString());
		
		try {
			httpClient.executeMethod(putMethod);
			content = putMethod.getResponseBodyAsString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(content); 
		   
		JSONObject json = JSONObject.fromObject(content);
		Assertion.verifyEquals(json.get("responseCode"),"1");
	}
	
	@DataProvider
	public Object[][] autoInvest_dp() {
		return new Object[][] { new Object[] { "6212461030000010862", "123456", "100", "58022016063002170214933",
				"20160628", "1", "12", "20170628", "14.5", "1", "1" }, 
								new Object[] { "6212461030000010862", "123456", "100", "58022016063002170214933",
				"20160628", "2", "", "20170628", "14.5", "1", "1" },
		};
	}
	
	@DataProvider
	public Object[][] unInvestApply_dp() {
		return new Object[][] {new Object[] {"6212461030000010862",AUTHCODE},
		};
	}
}
