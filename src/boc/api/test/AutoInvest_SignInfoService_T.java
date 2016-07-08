package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

/*P2P产品购买自动投标签约查询*/
@Listeners({ boc.api.ass.AssertionListener.class })
public class AutoInvest_SignInfoService_T {
	String cardNbr = "6212461030000010862";
	String seriNo;

	@Test(dataProvider = "dp")
	public void autoInvestSignInfoService(String cardNbr, String remark) {
		try {
			Connection con = Jsoup.connect("http://192.168.23.39:9656/api/newboc/v1/p2p/autoinvest/signInfo");
			con.data("cardNbr", cardNbr);
			con.data("remark", remark);
			Document document = con.ignoreContentType(true).get();

			// 解析返回的html
			String content = Jsoup.parse(document.toString()).body().text();

			// 解析获取的json
			JSONObject json = JSONObject.fromObject(content);
			JSONObject data = JSONObject.fromObject(json.get("data"));
			seriNo = json.get("seriNo").toString();
			// 断言——responseCode是否返回1
			Assertion.verifyEquals(json.get("responseCode"), "1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { cardNbr, "1" }, };
	}
}
