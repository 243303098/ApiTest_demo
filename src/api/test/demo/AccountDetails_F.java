package api.test.demo;

import org.testng.annotations.Test;

import com.zf.zson.Zson;
import com.zf.zson.ZsonResult;

import api.test.data.Data;
import boc.api.ass.Assertion;
import boc.api.ass.RequestMethod;

public class AccountDetails_F extends Data{
	String result;
  @Test(dataProvider = "accountDetails_F")
  public void accountDetails(String url,String data,String responseCode,String responseBankCode) {
	  result = RequestMethod.getRequestRusultForGet(url);
	  
	  Zson zson = new Zson();
	  ZsonResult zsonResult = zson.parseJson(result);
	  Assertion.verifyEquals(zsonResult.getString("/data"), data);
	  Assertion.verifyEquals(zsonResult.getString("/responseCode"), responseCode);
	  //判断responseCode是否为空，为空的话则不断言
	  if (responseBankCode.trim().length()>0) {
		Assertion.verifyEquals(zsonResult.getString("/responseBankCode"), responseBankCode);
	}
  }
}
