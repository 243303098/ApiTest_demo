package api.test.demo;

import org.testng.annotations.Test;

import boc.api.ass.Zson;
import boc.api.ass.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.RequestMethod;

public class AccountDetails_F extends Data {
	String result;
	/**
	 * 按证件号查询持卡人电子账户号--参数不合法
	 * @param url
	 * @param data
	 * @param responseCode
	 * @param responseBankCode
	 */
	@Test(dataProvider = "accountDetails_F")
	public void accountDetails(String url, String data, String responseCode, String responseBankCode) {
		result = RequestMethod.getRequestRusultForGet(url);

		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(result);
		Assertion.verifyEquals(zsonResult.getString("/data"), data);
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
		// 判断result中是否存在responseBankCode，不存在则不断言
		if (result.contains("responseBankCode")) {
			Assertion.verifyEquals(zsonResult.getString("/responseBankCode"), responseBankCode);
		}
	}
}
