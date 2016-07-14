package api.test.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zf.zson.Zson;
import com.zf.zson.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;
import boc.api.ass.DoString;
import boc.api.ass.RequestMethod;

@Listeners({ boc.api.ass.AssertionListener.class })
public class RedpacketTransaction_F extends Data {
	String result;

	/**
	 * 红包转账不符合要求的参数的情况
	 * 
	 * @param param
	 *            不符合要求的参数
	 * @param data
	 *            期望返回的data
	 * @param responseCode
	 *            期望返回的responseCode
	 */
	@Test(dataProvider = "redpacketTransaction_F_Data")
	private void redpacketTransaction(String param, String data, String responseCode) {
		// 发送请求，并获取请求结果
		result = RequestMethod.getRequestRusultForPost(
				DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.REDPACKETTRASACTION,
				DoString.StringToJson(param));
		// 解析result，并断言
		Zson zson = new Zson();
		ZsonResult zsonResult = zson.parseJson(result);
		Assertion.verifyEquals(zsonResult.getString("/data"), data);
		Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
	}
}
