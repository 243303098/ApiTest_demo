package api.test.demo;

import org.testng.annotations.Test;

import boc.api.ass.Zson;
import boc.api.ass.ZsonResult;
import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;
import boc.api.ass.RequestMethod;
import net.sf.json.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({ boc.api.ass.AssertionListener.class })
/**
 * 按手机号查询电子帐号信息
 * 
 * @author Administrator
 *
 */
public class AccountInfos_T extends Data {
	String result;

	@Test(dataProvider = "accountInfos_T_data")
	public void accountInfos(String param, String custid, String name, String cardno, String responseCode) {
		result = RequestMethod.getRequestRusultForGet(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.ACCOUNTINFOS + param);

		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(result);

		Assertion.verifyEquals(zsonResult.getValue("/data/*[0]/CUSTID"), custid);
		Assertion.verifyEquals(zsonResult.getValue("/data/*[0]/NAME"), name);
		Assertion.verifyEquals(zsonResult.getValue("/data/*[0]/CARDNO"), cardno);
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
	}
}
