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
public class TransActionDetails_T {
	Newaccount_T nat = new Newaccount_T();
	
	@Test(dataProvider = "dp")
	public void transActionDetails(String cardNbr, String startDate, String endDate, String tranType, String nxInpd,
			String nxReld, String nxInpt, String nxTrnn) {
		
		HttpClient HttpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("http://192.168.23.39:9656/api/newboc/v1/p2p/account/transactionDetails/"
				+ cardNbr
				+ "/asc/page?startDate="+startDate+"&endDate="+endDate+"&tranType="+tranType+"&nxInpd="+nxInpd+"&nxReld="+nxReld+"&nxInpt="+nxInpt+"&nxTrnn="+nxTrnn+"");
		
		try {
			if (HttpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {
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
		return new Object[][] { new Object[] { "6212461030000009427","20150924","20151124","" ,"" ,"" ,"" ,"" },};
	}
}
