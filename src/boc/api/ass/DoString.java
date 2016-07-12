package boc.api.ass;

import net.sf.json.JSONObject;
/**
 * 将String类型转换为json
 * @author Administrator
 *
 */
public class DoString {
	/**
	 * 
	 * @param parameter:String类型的参数
	 * @return：返回json
	 */
	@SuppressWarnings("static-access")
	public static JSONObject StringToJson(String parameter){
		JSONObject json = new JSONObject().fromObject(parameter);
		return json;
	}
}
