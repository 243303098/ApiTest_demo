package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({boc.api.ass.AssertionListener.class})
public class Recharge_T {
  @Test(dataProvider = "dp")
  public void recharge(String cardNbr, String cardBind, String amount, String idNo, String name,String mobile) {
	  JSONObject jsonObject = new JSONObject();
	  jsonObject.put("cardNbr", cardNbr);
	  jsonObject.put("cardBind", cardBind);
	  jsonObject.put("amount", amount);
	  jsonObject.put("idNo", idNo);
	  jsonObject.put("name", name);
	  jsonObject.put("mobile", mobile);
	  
	  HttpClient httpClient = new HttpClient();
	  PostMethod postMethod = new PostMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/recharge");
	  
	  try {
		if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {
			String content = postMethod.getResponseBodyAsString();
			JSONObject json = new JSONObject().fromObject(content);
			System.out.println(content);
			
			Assertion.verifyEquals(json.get("responseCode"), "1");
		}else {
			Assertion.verifyTure(false);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "6212461030000010862", "6228480403965559616","1000","","","",},
    };
  }
}
