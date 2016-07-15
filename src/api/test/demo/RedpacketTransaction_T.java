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
import net.sf.json.JSONObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({ boc.api.ass.AssertionListener.class })
public class RedpacketTransaction_T extends Data {
	String request;

	/**
	 * 红包转账参数正确的情况
	 * 
	 * @param param
	 *            正确的参数
	 */
	@Test(dataProvider = "redpacketTransaction_T_Data")
	public void redpacketTransaction(String param) {
		// 发送请求，并获取请求结果
		request = RequestMethod.getRequestRusultForPost(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.REDPACKETTRASACTION,
				DoString.StringToJson(param));

		// 解析result，并断言
		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(request);
		Assertion.verifyEquals(zsonResult.getString("/data"), "1");
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), "1");
	}
}
