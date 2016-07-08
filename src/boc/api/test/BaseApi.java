package boc.api.test;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import boc.api.ass.Assertion;
import boc.api.ass.ExcelUtils;
import net.sf.json.JSONObject;

@Listeners({ boc.api.ass.AssertionListener.class })
public class BaseApi {
	static String FILEPATH = "D:\\test_data.xlsx";

	@Test
	public void baseApi() {
		HttpClient httpClient = new HttpClient();
		File file = new File(FILEPATH);
		if (file.exists()) {
			try {
				ExcelUtils.setExcelFile(FILEPATH, "data");
				for (int RowNum = 1; RowNum <= ExcelUtils.getLastRowNum(FILEPATH, "data"); RowNum++) {
					switch (ExcelUtils.getCellDate(RowNum, 2)) {
					case "post":
						PostMethod postMethod = new PostMethod(ExcelUtils.getCellDate(RowNum, 4));
						postMethod.setRequestHeader("Content-Type", ExcelUtils.getCellDate(RowNum, 5));
						postMethod.setRequestBody(ExcelUtils.getCellDate(RowNum, 3).toString());
						// 判断返回的状态是否是200，
						if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {
							// 获取返回的内容
							String content = postMethod.getResponseBodyAsString();
							System.out.println(content);
							// 解析返回的内容，判断返回的值和预期的是否一样并在excle中加入相应的标识
							JSONObject jsonObject = new JSONObject().fromObject(content);
							if (jsonObject.get(ExcelUtils.getCellDate(RowNum, 6))
									.equals(ExcelUtils.getCellDate(RowNum, 7))) {
								ExcelUtils.setCellData("T", RowNum, 8, FILEPATH);
							} else {
								ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							}
							Assertion.verifyEquals(jsonObject.get(ExcelUtils.getCellDate(RowNum, 6)),
									ExcelUtils.getCellDate(RowNum, 7));
						} else {
							ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							Assertion.verifyTure(false);
						}
						break;

					case "get":
						GetMethod getMethod = new GetMethod(ExcelUtils.getCellDate(RowNum, 4));
						if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {
							String content = getMethod.getResponseBodyAsString();
							System.out.println(content);
							JSONObject jsonObject = new JSONObject().fromObject(content);
							if (jsonObject.get(ExcelUtils.getCellDate(RowNum, 6))
									.equals(ExcelUtils.getCellDate(RowNum, 7))) {
								ExcelUtils.setCellData("T", RowNum, 8, FILEPATH);
							} else {
								ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							}
							Assertion.verifyEquals(jsonObject.get(ExcelUtils.getCellDate(RowNum, 6)),
									ExcelUtils.getCellDate(RowNum, 7));
						} else {
							ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							Assertion.verifyTure(false);
						}
						break;

					case "put":
						PutMethod putMethod = new PutMethod(ExcelUtils.getCellDate(RowNum, 4));
						putMethod.setRequestHeader("Content-Type", ExcelUtils.getCellDate(RowNum, 5));
						putMethod.setRequestBody(ExcelUtils.getCellDate(RowNum, 3).toString());
						if (httpClient.executeMethod(putMethod) == HttpStatus.SC_OK) {
							String content = putMethod.getResponseBodyAsString();
							System.out.println(content);
							JSONObject jsonObject = new JSONObject().fromObject(content);
							if (jsonObject.get(ExcelUtils.getCellDate(RowNum, 6))
									.equals(ExcelUtils.getCellDate(RowNum, 7))) {
								ExcelUtils.setCellData("T", RowNum, 8, FILEPATH);
							} else {
								ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							}
							Assertion.verifyEquals(jsonObject.get(ExcelUtils.getCellDate(RowNum, 6)),
									ExcelUtils.getCellDate(RowNum, 7));
						} else {
							ExcelUtils.setCellData("F", RowNum, 8, FILEPATH);
							Assertion.verifyTure(false);
						}

					default:
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
