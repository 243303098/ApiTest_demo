package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.testng.annotations.DataProvider;
/*存管平台电子账户签约卡绑定关系取消*/
public class UnBindCard_T {
	Newaccount_T nat = new Newaccount_T();

	@Test(dataProvider = "dp")
	public void unBindCard(String cardNbr, String sigCard, String surName, String idNo, String mobile) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cardNbr", cardNbr);
		jsonObject.put("sigCard", sigCard);
		jsonObject.put("surName", surName);
		jsonObject.put("idNo", idNo);
		jsonObject.put("mobile", mobile);

		HttpClient httpClient = new HttpClient();
		PutMethod putMethod = new PutMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/unbindcard");
		putMethod.setRequestHeader("Content-Type","application/json");
		putMethod.setRequestBody(jsonObject.toString());
		try {
			if (httpClient.executeMethod(putMethod) == HttpStatus.SC_OK) {
				String content = putMethod.getResponseBodyAsString();
				System.out.println(content);

				JSONObject json = new JSONObject().fromObject(content);
				Assertion.verifyEquals(json.get("responseCode"), "1");
			}else{
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "6212461030000011043", nat.bankcar, nat.name, nat.idNo, nat.phone }, };
	}
}
