package boc.api.ass;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;

import net.sf.json.JSONObject;

@Listeners({ boc.api.ass.AssertionListener.class })
/**
 * 该类封装了post、get、put等相应的请求操作
 * 
 * @author Administrator
 *
 */
public class RequestMethod {
	private static Logger logger = Logger.getLogger(RequestMethod.class);
	public static String RequestResult;

	/**
	 * 
	 * @param url
	 *            url为请求的地址
	 * @param parameter
	 *            parameter是JSONOBJECT类型
	 * @return 返回请求结果
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestRusultForPost(String url, JSONObject parameter) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		// 设置请求的头信息级参数
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestBody(parameter.toString());
		// 判断请求返回的状态是否为200
		try {
			int Status = httpClient.executeMethod(postMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				// 获取返回的结果
				RequestResult = postMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回：" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return RequestResult;
	}

	/**
	 * 
	 * @param url
	 *            请求的地址
	 * @param params
	 *            Map类型的参数
	 * @return 返回请求结果
	 */
	public static String getRequestRusultForPost(String url, Map<String, String> params) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);

		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
			try {
				int Status = httpClient.executeMethod(postMethod);
				if (Status == HttpStatus.SC_OK) {
					logger.info("接口返回:" + Status + "成功！");
					// 获取返回的结果
					RequestResult = postMethod.getResponseBodyAsString();
				} else {
					logger.warn("接口返回：" + Status);
					Assertion.verifyTure(false);
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.warn(e.getMessage());
			} finally {
				postMethod.releaseConnection();
			}
		}
		return RequestResult;
	}

	/**
	 * 
	 * @param url
	 *            url为请求的地址
	 * @return 返回请求结果
	 */
	public static String getRequestRusultForGet(String url) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		// 判断请求返回的状态是否为200
		try {
			int Status = httpClient.executeMethod(getMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				// 获取返回的结果
				RequestResult = getMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回:" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}
		return RequestResult;
	}

	/**
	 * 
	 * @param url
	 *            url为请求的地址
	 * @param paramete
	 *            parameter是JSONOBJECT类型
	 * @return 返回请求结果
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestRusultForPutJson(String url, String parameter) {
		BasicConfigurator.configure();
		HttpClient httpClient = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		// 设置请求的头信息级参数
		putMethod.setRequestHeader("Content-Type", "application/json");
		putMethod.setRequestBody(parameter);
		// 判断请求返回的状态是否为200
		try {
			int Status = httpClient.executeMethod(putMethod);
			if (Status == HttpStatus.SC_OK) {
				logger.info("接口返回:" + Status + "成功！");
				// 获取返回的结果
				RequestResult = putMethod.getResponseBodyAsString();
			} else {
				logger.warn("接口返回：" + Status);
				Assertion.verifyTure(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		} finally {
			putMethod.releaseConnection();
		}
		return RequestResult;
	}
}
