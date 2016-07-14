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
						"校验总参数为空！", "0", "" },
				new Object[] { DoProperties.getValue(BaseURL.FILENAMEPATH, "Url") + BaseURL.ACCOUNTDETAILS
						+ "?idNo=32083019930329403", "证件号码长度非法", "0", "CA100146" }, };
	}

	/**
	 * 开户--合法参数
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] NewAccount_T_data() {
		return new Object[][] {
				new Object[] {
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527941\",\"smsFlag\": \"0\",\"reCard\": \"6228480403965559616\"}",
						"CA101068", "6212461030000010862", "1" },
				new Object[] {
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527942\",\"smsFlag\": \"0\",\"reCard\": \"6228480403965559616\"}",
						"CA101068", "6212461030000010862", "1" },
				new Object[] {
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527942\",\"smsFlag\": \"1\",\"reCard\": \"6228480403965559616\"}",
						"CA101068", "6212461030000010862", "1" }, };
	}

	/**
	 * 开户--不合法参数
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] NewAccount_F_data() {
		return new Object[][] {
				new Object[] {
						"{\"idNo\":\"320830199303294033\",\"surName\": \"胡杰\",\"mobile\": \"15221527941\",\"smsFlag\": \"0\",\"reCard\": \"6228480403965559616\"}",
						"", "身份证(18位)校验位错误", "CA100766", "0" },
				new Object[] {
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527941\",\"smsFlag\": \"0\",\"reCard\": \"62284804039655596151\"}",
						"", "参数不正确", "", "0" },
				new Object[] {
						"{\"idNo\":\"320830199303294032\",\"surName\": \"胡杰\",\"mobile\": \"15221527941\",\"smsFlag\": \"0\",\"reCard\": \"6228480403965559615\"}",
						"CA101068", "已开通过电子帐户", "", "0" }, };
	}

	/**
	 * 按手机号查询电子帐号信息 --合法參數
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] accountInfos_T_data() {
		return new Object[][] {
				new Object[] { "?phone=15221527941", "320830199303294032", "胡杰", "6212461030000010862", "1" }, };
	}

	/**
	 * 按手机号查询电子帐号信息 --不合法參數
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] accountInfos_F_data() {
		return new Object[][] { new Object[] { "?phone=1522152794", "此号码无电子账号信息", "1" },
				new Object[] { "?phone=", "校验总参数为空！", "0" }, };
	}
}
