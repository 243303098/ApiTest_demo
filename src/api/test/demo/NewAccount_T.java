package api.test.demo;

import org.testng.annotations.Test;

import boc.api.ass.Zson;
import boc.api.ass.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;
import boc.api.ass.DoString;
import boc.api.ass.RequestMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({ boc.api.ass.AssertionListener.class })
public class NewAccount_T extends Data {
	String result;

	@Test(dataProvider = "NewAccount_T_data")
	public void newAccount(String param, String retcode, String cardnbr, String responseCode) {
		result = RequestMethod.getRequestRusultForPost(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.NEWACCOUNT, DoString.StringToJson(param));

		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(result);
		
		Assertion.verifyEquals(zsonResult.getString("/RETCODE"), retcode);
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
		Assertion.verifyEquals(zsonResult.getString("/data/CARDNBR"), cardnbr);
	}
}
