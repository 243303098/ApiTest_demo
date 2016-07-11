package boc.api.test;

import org.apache.commons.logging.impl.Log4JLogger;

import com.zf.zson.Zson;
import com.zf.zson.ZsonResult;
import org.apache.log4j.*;
public class Demo {
	static Logger logger = Logger.getLogger(Demo.class);

	public static void main(String[] args) {
		String s1 = "[{ \"firstName\": \"Eric\", \"lastName\": \"Clapton\", \"instrument\": \"guitar\" },{ \"firstName\": \"Sergei\", \"lastName\": \"Rachmaninoff\", \"instrument\": \"piano\" }] ";
		String s2 = "[0,1,2,3.14,4.00,\"3\",true,\"\"]";
		String s3 = "{\"a\":[\"a1\",\"a2\",\"a1\"],\"cb\":{\"a\":1},\"d\":[\"a\",{\"a\":[1,20]},{\"a\":2},\"\"],\"e\":\"b\"}";
		String s4 = "{\"data\": \"1\",\"responseCode\": \"1\"}";
		Zson z = new Zson();
		ZsonResult zr4 = z.parseJson(s4);
		System.out.println(zr4.getString("/responseCode"));
		BasicConfigurator.configure();
		logger.info("开始解析json");
		ZsonResult zr1 = z.parseJson(s1);
		System.out.println(zr1.getValue("/*[1]/firstName"));  
		logger.info("获取data中的值");
		System.out.println(zr1.getMap("/*[1]"));
		
		ZsonResult zr2 = z.parseJson(s2);
		System.out.println(zr2.getInteger("/*[1]"));
		System.out.println(zr2.getLong("/*[2]"));
		System.out.println(zr2.getDouble("/*[3]"));
		System.out.println(zr2.getFloat("/*[4]"));
		System.out.println(zr2.getString("/*[5]"));
		System.out.println(zr2.getBoolean("/*[6]"));
		
		ZsonResult zr3 = z.parseJson(s3);
		System.out.println(zr3.getValues("//*[0]"));
		System.out.println(zr3.getValues("//*[1]"));
		System.out.println(zr3.getList("/a"));
		System.out.println(zr3.getMap("/cb"));
		System.out.println(zr3.toJsonString(zr3.getResult()));
		zr3.removeValue("/a/*[0]");
		System.out.println(zr3.getResult());
		System.out.println(zr3.getList("/a"));
	}

}
