package boc.api.ass;

import java.io.IOException;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;

import net.sf.json.JSONObject;

@Listeners({ boc.api.ass.AssertionListener.class })
/*
 * @author 该类是对于主要请求方法的封装
 */
public class RequestMethod {
	private static Logger logger = Logger.getLogger(RequestMethod.class);
	public static String RequestResult;
	/*
	 * @return 该类返回post请求的值
	 * @param url：请求的地址,parameter：请求的参数。String类型
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestRusultForPost(String url, String parameter) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestBody(parameter);

		try {
			int Status = httpClient.executeMethod(postMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				RequestResult = postMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回：" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		return RequestResult;
	}
	/*
	 * @return 该类返回get请求的值
	 */
	public static String getRequestRusultForGet(String url) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);

		try {
			int Status = httpClient.executeMethod(getMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				RequestResult = getMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回:" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		return RequestResult;
	}
	/*
	 * @return 该类返回put请求的值
	 * @param url：请求的地址,parameter：请求的参数。String类型
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestRusultForPut(String url, String parameter) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		
		putMethod.setRequestHeader("Content-Type", "application/json");
		putMethod.setRequestBody(parameter);

		try {
			int Status = httpClient.executeMethod(putMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				RequestResult = putMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回：" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		return RequestResult;
	}
}
