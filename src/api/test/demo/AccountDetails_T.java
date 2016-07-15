package api.test.demo;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import boc.api.ass.Zson;
import boc.api.ass.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.RequestMethod;

@Listeners({ boc.api.ass.AssertionListener.class })
public class AccountDetails_T extends Data {
	String request;
	/**
	 * 按证件号查询持卡人电子账户号--参数合法
	 * @param url
	 * @param cardnbr
	 * @param responseCode
	 */
	@Test(dataProvider = "accountDetails_T")
	public void accountDetails(String url, String cardnbr, String responseCode) {
		request = RequestMethod.getRequestRusultForGet(url);

		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(request);
		Assertion.verifyEquals(zsonResult.getString("/data/CARDNBR"), cardnbr);
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
	}
}
