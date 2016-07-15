package boc.api.ass;

public class BaseURL {
	public static final String FILENAMEPATH = "D:/workspace/BOC_API/parameter.properties";
	//红包转账
	public static final String REDPACKETTRASACTION = "api/newboc/v1/p2p/redpacket/transaction";
	//按证件号查询持卡人电子账户号
	public static final String ACCOUNTDETAILS = "api/newboc/v1/p2p/account/accountDetails";
	//开户
	public static final String NEWACCOUNT = "api/newboc/v1/p2p/account/newaccount";
	//按手机号查询电子帐号信息
	public static final String ACCOUNTINFOS = "api/newboc/v1/p2p/account/accountInfos";
	//存管平台电子账户签约卡绑定
	public static final String BINCARD = "api/newboc/v1/p2p/account/bindcard";
	//存管平台电子账户签约卡取消绑定
	public static final String UNBINCARD = "api/newboc/v1/p2p/account/unbindcard";
}
