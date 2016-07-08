package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.DataProvider;

public class BindCardRelationship_T {
	@Test(dataProvider = "dp")
	public void f(String cardNbr, String state) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/bindcards?cardNbr="
				+ cardNbr + "&state=" + state + "");

		try {
			if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {

				String content = getMethod.getResponseBodyAsString();
				System.out.println(content);
				JSONObject jsonObject = new JSONObject().fromObject(content);
				
				Assertion.verifyEquals(jsonObject.get("responseCode"), "1");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "6212461030000011043", "0" },  };
	}
}
