package boc.api.ass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
	public String property_in(String key,String value) {
		Properties prop = new Properties();
		FileOutputStream oFile;
		try {
			oFile = new FileOutputStream("D:/a.properties", true);
			prop.remove("cardNbr");
			prop.setProperty(key, value);
			prop.save(oFile, "cardNbr");
			oFile.close();
			System.out.println(prop.getProperty(key));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// true表示追加打开
		return prop.getProperty(key);
	}
}
