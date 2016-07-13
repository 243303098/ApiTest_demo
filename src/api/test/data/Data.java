package api.test.data;

import org.testng.annotations.Test;

import boc.api.ass.BaseURL;
import boc.api.ass.DoProperties;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.DataProvider;

public class Data {
	/**
	 * 红包转账正确参数
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] redpacketTransaction_T_Data() {
		return new Object[][] {
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}" },
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"1\",\"desline\": \"\"}" },
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"1\",\"desline\": \"红包转账\"}" }, };
	}

	/**
	 * 红包转账不合法参数
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] redpacketTransaction_F_Data() {

		return new Object[][] {
				new Object[] {
						"{\"cardNbr\": \"62124610300000108622\",\"amount\": \"100\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}",
						"JX110003", "0" },
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"-100\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}",
						"JX110005", "0" },
				new Object[] { "{\"cardNbr\": \"\",\"amount\": \"100\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}",
						"参数不正确", "0" },
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}",
						"参数不正确", "0" }, };
	}

	/**
	 * 按证件号查询持卡人电子账户号正确参数
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public Object[][] accountDetails_T() throws IOException {
		return new Object[][] { new Object[] { DoProperties.getValue(BaseURL.FILENAMEPATH, "Url")
				+ BaseURL.ACCOUNTDETAILS + "?idNo=320830199303294032", "6212461030000010862", "1" }, };
	}
	
	/**
	 * 按证件号查询持卡人电子账户号不合法参数
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public Object[][] accountDetails_F() throws IOException {
		return new Object[][] {
				new Object[] { DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.ACCOUNTDETAILS + "?idNo=",
						"校验总参数为空！", "0","" },
				new Object[] { DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.ACCOUNTDETAILS
						+ "?idNo=32083019930329403", "证件号码长度非法", "0" ,"CA100146"}, };
	}
}
