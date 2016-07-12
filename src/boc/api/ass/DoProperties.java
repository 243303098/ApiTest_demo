package boc.api.ass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取properties文件中的值
 * @author Administrator
 *
 */
public class DoProperties {
	/**
	 * 
	 * @param fileNamePath:文件的地址
	 * @param key:对应的properties文件中的key
	 * @return key对应的value
	 * @throws IOException
	 */
	public static String getValue(String fileNamePath, String key) throws IOException {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(fileNamePath);
			// 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中
			// in = propertiesTools.class.getResourceAsStream(fileNamePath);
			props.load(in);
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != in)
				in.close();
		}
	}

}
