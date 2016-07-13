package api.test.data;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.DataProvider;

public class Data {

  @DataProvider
  public Object[][] redpacketTransaction_T_Data() {
		return new Object[][] {
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"0\",\"desline\": \"红包转账\"}" },
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"1\",\"desline\": \"\"}" }, 
				new Object[] {
						"{\"cardNbr\": \"6212461030000010862\",\"amount\": \"100\",\"deslineflag\": \"1\",\"desline\": \"红包转账\"}" },};
	}
  
  @DataProvider
	public Object[][] redpacketTransaction_F_Data(){

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
}
