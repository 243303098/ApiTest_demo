package api.test.demo;

import org.testng.annotations.Test;

import com.zf.zson.Zson;
import com.zf.zson.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;
import boc.api.ass.RequestMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners({boc.api.ass.AssertionListener.class})
/**
 * 按手机号查询电子帐号信息
 * 
 * @author Administrator
 *
 */
public class AccountInfos_F extends Data{
	String result;
	  @Test(dataProvider = "accountInfos_F_data")
	  public void accountInfos(String param, String data, String responseCode) {
		  result = RequestMethod.getRequestRusultForGet(DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.ACCOUNTINFOS + param);
		  
		  Zson zson = new Zson();
		  ZsonResult zsonResult = zson.parseJson(result);
		  
		  Assertion.verifyEquals(zsonResult.getString("/data"), data);
		  Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
  }
}
