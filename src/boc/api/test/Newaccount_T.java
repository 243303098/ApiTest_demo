package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import boc.api.ass.Property;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({ boc.api.ass.AssertionListener.class })
public class Newaccount_T {
	
	String idNo = "51052119850508797x";
	String phone = "15221527949";
	String bankcar = "6228480403965559626";
	String name = "李洪";

	@Test(dataProvider = "dp")
	public void A_newAccount(String idNo, String surName, String mobile, String smsFlag, String reCard) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idNo", idNo);
		jsonObject.put("surName", surName);
		jsonObject.put("mobile", mobile);
		jsonObject.put("smsFlag", smsFlag);
		jsonObject.put("reCard", reCard);

		String content = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/newaccount");
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestBody(jsonObject.toString());

		try {

			if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {
				content = postMethod.getResponseBodyAsString();
				
				JSONObject json = JSONObject.fromObject(content);
				JSONObject data = JSONObject.fromObject(json.get("data"));
				System.out.println(data.get("CARDNBR").toString());
				Assertion.verifyEquals(json.get("responseCode"), "1");
			} else {
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
				new Object[] { idNo, name, phone, "1", bankcar}, };
	}
}
