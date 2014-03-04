package cft.commons.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cft.commons.core.constant.Constants;

/**
 * java文件操作工具类
 * 
 * @author daniel
 * 
 */
public class FileDirUtils {

	protected static Logger logger = LoggerFactory.getLogger(FileDirUtils.class);

	/**
	 * 功能描述：列出文件夹及其子文件夹下面的文件，并可根据扩展名过滤，返回 List<File> fileList
	 * 
	 * @param path 目录名
	 * @param exts 希望包含的文件扩展名列表，  null 代表全部文件类型
	 * @param subfolders 是否包含子目录
	 *           
	 */
	@SuppressWarnings("unchecked")
	public static List<File> listFiles(File path, String[] exts, boolean subfolders) {

		List<File> fileList = new ArrayList<File>();
		Collection<File> fileListColl = FileUtils.listFiles(path, exts, subfolders);

		if (CollectionUtils.isNotEmpty(fileListColl)) {
			for (Iterator<File> it = fileListColl.iterator(); it.hasNext();) {
				fileList.add(it.next());
			}
		}

		return fileList;
	}

	/**
	 * 删除文件名中的路径，只返回文件名
	 * 
	 * @param filename
	 * @return
	 */
	public final static String removePath(String filename) {
		int i = filename.lastIndexOf('/');
		if (i == -1) {
			return filename;
		} else {
			return filename.substring(i + 1);
		}
	}

	/**
	 * 文件最后一次被修改的时间
	 * 
	 * 
	 * @param filename
	 * @return 0-表示文件不存在或无权限读取，大于0为修改时间
	 */
	public static long getFileLastModified(String filename) {
		long l = 0;
		File f = new File(filename);
		if (f.exists()) {
			try {
				l = f.lastModified();
			} catch (SecurityException se) {
				l = 0;
				se.printStackTrace();
			}
		}
		return l;
	}

	/**
	 * 把数据写至文件中
	 * @param filePath
	 * @param data
	 */
	public static void writeFile(String filePath, String data) {

		FileOutputStream fos = null;
		OutputStreamWriter writer = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			writer = new OutputStreamWriter(fos, Constants.ENCODING_UTF8);
			writer.write(data);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 读取文件内容
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		StringBuffer buffer = new StringBuffer();
		// 读出这个文件的内容
		try {
			File file = new File(filePath);
			FileInputStream fis = null;
			BufferedReader breader = null;
			try {
				fis = new FileInputStream(file);
				InputStreamReader isReader = new InputStreamReader(fis, Constants.ENCODING_UTF8);
				breader = new BufferedReader(isReader);
				String line;
				while ((line = breader.readLine()) != null) {
					buffer.append(line);
					buffer.append("\r\n");
				}
				breader.close();
				isReader.close();
				fis.close();

			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return buffer.toString();
	}

}
