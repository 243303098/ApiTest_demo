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
public class WithDrawalPwd_T {
  @Test(dataProvider = "dp")
  public void withDrawalPwd(String cardNbr) {
	  HttpClient httpClient = new HttpClient();
	  GetMethod getMethod = new GetMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/withdrawalpwd?cardNbr="+cardNbr+"");
	  try {
		if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {
			String content = getMethod.getResponseBodyAsString();
			JSONObject jsonObject = new JSONObject().fromObject(content);
			System.out.println(content);
			
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
      new Object[] { "6212461030000010862"},
    };
  }
}
