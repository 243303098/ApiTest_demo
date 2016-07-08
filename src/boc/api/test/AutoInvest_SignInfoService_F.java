package boc.api.test;

import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import net.sf.json.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.AfterTest;

@Listeners({boc.api.ass.AssertionListener.class})
public class AutoInvest_SignInfoService_F {
  @Test(dataProvider = "dp")
  public void autoInvestSignInfoService(String cardNbr, String remark) {
	  Connection con = Jsoup.connect("http://192.168.23.39:9656/api/newboc/v1/p2p/autoinvest/signInfo");
	  con.data("cardNbr", cardNbr);
	  con.data("remark", remark);
	  try {
		Document document = con.ignoreContentType(true).get();
		String content = Jsoup.parse(document.toString()).body().text();
		
		JSONObject json = JSONObject.fromObject(content);
		
		Assertion.verifyEquals(json.get("responseCode"), "0");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "62124610300000108622", "1" },
      new Object[] { "", "1" },
    };
  }
}
