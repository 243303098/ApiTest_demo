package api.test.demo;

import org.testng.annotations.Test;

import boc.api.ass.Zson;
import boc.api.ass.ZsonResult;

import boc.api.ass.Assertion;
import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;
import boc.api.ass.DoString;
import boc.api.ass.RequestMethod;
import net.sf.json.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({boc.api.ass.AssertionListener.class})
public class Bindcard {
	String result;
	String result2;
	Zson zson = new Zson();

	@SuppressWarnings("static-access")
	@Test(dataProvider = "dp")
	public void bindcard(String param, String responseCode) {
		JSONObject jsonObject = new JSONObject().fromObject(param);
		result = RequestMethod.getRequestRusultForPost(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.NEWACCOUNT, DoString.StringToJson(
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527941\",\"smsFlag\": \"0\",\"reCard\": \"6228480403965559616\"}"));

		ZsonResult zsonResult1 = zson.parseJson(result);

		String cardNbr = zsonResult1.getString("/data/CARDNBR");
		jsonObject.put("cardNbr", cardNbr);
		result2 = RequestMethod.getRequestRusultForPost(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.BINCARD, DoString.StringToJson(param));
		ZsonResult zsonResult2 = zson.parseJson(result2);
		Assertion.verifyEquals(zsonResult2.getString("/responseCode"), responseCode);
	}

	@DataProvider
	public Object[][] dp() {

		return new Object[][] { new Object[] {
				"{\"idNo\": \"320830199303294032\",\"surName\": \"潘尚斌\",\"mobile\": \"15221527941\",\"cardNbr\":\"\",\"sigCard\": \"6228480403965559616\"}",
				"1" }, };
	}
}
