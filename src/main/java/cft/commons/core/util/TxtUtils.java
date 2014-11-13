package cft.commons.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cft.commons.core.constant.Constants;

/**
 * @author daniel
 *
 */
public class TxtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(TxtUtils.class);
	
	/**
	 * 读取txt file 返回List<String>
	 * @param file 文件
	 * @param encoding 编码，默认为 UTF-8
	 * @return
	 */
	public static List<String> readTxt(File file, String encoding) {
		
		List<String> list = new ArrayList<String>();

		encoding = StringUtils.isNotBlank(encoding)?encoding:Constants.ENCODING_UTF8;
		

		try {

			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);

			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;

			while ((lineTxt = bufferedReader.readLine()) != null) {
				list.add(lineTxt);
			}
			
			read.close();

		} catch (Exception e) {
			logger.error("Exception during TxtUtils.readTxt.", e);
		}

		return list;

	}
	
	
	
	
	/**
	 * 读取txt file 返回List<String>
	 * @param filePath 文件路径
	 * @param encoding 编码，默认为 GBK
	 * @return
	 */
	public static List<String> readTxt(String filePath, String encoding) {
		
		List<String> list = new ArrayList<String>();

		File file = new File(filePath);
		
		list = readTxt(file, encoding);

		return list;

	}

}
