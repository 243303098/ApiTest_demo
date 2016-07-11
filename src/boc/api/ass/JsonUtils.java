package boc.api.ass;

import net.sf.json.JSONObject;

public class JsonUtils {
	public static void jsonReaderTest(String json){
		JSONObject jsonObject = new JSONObject().fromObject(json);
		
	}
}
