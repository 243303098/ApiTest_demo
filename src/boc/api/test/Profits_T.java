package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({ boc.api.ass.AssertionListener.class })
public class Profits_T {
  @Test(dataProvider = "dp")
  public void profits(String cardNbr, String queryType) {
	  HttpClient httpClient = new HttpClient();
	  GetMethod getMethod = new GetMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/profits?cardNbr="+cardNbr+"&queryType="+queryType+"");
	  try {
		if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {
			String content = getMethod.getResponseBodyAsString();
			System.out.println("+++++" +content);
			JSONObject jsonObject = new JSONObject().fromObject(content);
			
			Assertion.verifyEquals(jsonObject.get("responseCode"), "1");
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
      new Object[] { "6212461030000010862", "9" },
      new Object[] { "6212461030000010862", "0" },
      new Object[] { "6212461030000010862", "1" },
    };
  }
}
