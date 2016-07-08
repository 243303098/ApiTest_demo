package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({ boc.api.ass.AssertionListener.class })
public class AutoInvest_Unsigned_T {
	AutoInvest_SignInfoService_T ast = new AutoInvest_SignInfoService_T();
  @Test(dataProvider = "dp")
  public void autoInvest_Unsigned(String cardNbr, String seriNo) {
	  JSONObject jsonObject = new JSONObject();
	  jsonObject.put("cardNbr", cardNbr);
	  jsonObject.put("seriNo", seriNo);
	  
	  String content = null;
	  HttpClient httpClient = new HttpClient();
	  PutMethod putMethod = new PutMethod();
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
	  JSONObject json = JSONObject.fromObject(jsonObject);
	  Assertion.verifyEquals(json.get("responseCode"), "1");
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { ast.cardNbr, ast.seriNo },
    };
  }
}
