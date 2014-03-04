package cft.commons.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import cft.commons.core.constant.Constants;

/**
 * 
 * java fileCache工具类
 * 
 * 此类包含JAVA 序列化及反序列化缓存POJO到文件系统, POJO 必须 implements Serializable.
 * 需要建立并指定 fileCachePath，其中包括 final folder 和 temp folder, 
 * example:
 * /fileCache
 * /fileCache/temp
 * 
 * @author daniel
 */
public class FileCacheUtils {

	private static Logger logger = LoggerFactory.getLogger(FileCacheUtils.class);

	public final static String FILE_SEPARATOR = "/";
	public final static String CACHE_FILE_EXTENSION = ".cache";
	public final static String CACHE_FILE_TEMP_PATH = "/temp";

	/**
	 * Serialize object to both temp and pregen folder by the cache key as the file name.
	 * It tries to serialize object to temp folder, if no exceptions, it then copy that serialized file to pregen folder.
	 * @param path fileCache path
	 * @param cacheKey the cache file name
	 * @param obj the object to be serialized
	 * @throws Exception throw when failed to serialize object either to temp or pregen
	 */
	public static void saveFileCache(String path, String cacheKey, Object data) throws Exception {

		try {
			saveFileCacheToTemp(path, cacheKey, data);

			try {
				copyTempFileToFinal(path, cacheKey);
			} catch (Exception e) {
				throw new Exception("Exception duing copyTempFileToPregenFolder." + e.getMessage());
			}
		} catch (Exception e) {
			throw new Exception("Exception duing saveFileCacheToTemp." + e.getMessage());
		}
	}

	/**
	 * Deserialize object from pregen, if exceptions thrown, deserialize object from temp.
	 * It will check if the deserialized object is collection or not, if it is a collection, check if its empty.
	 * If the collection is empty, it will not return it.
	 * @param path fileCache path
	 * @param cacheKey the cache file name
	 * @return Object - deserialized object
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static Object loadFileCache(String path, String cacheKey) throws Exception {

		try {
			Object data = loadFileCacheFromFinal(path, cacheKey);
			if (data != null) {
				if (data instanceof Collection) {
					if (((List) data).size() > 0) {
						return data;
					}
				} else {
					return data;
				}
			}
		} catch (Exception e) {
			logger.warn("load file cache from final folder failed, exception: " + e.getMessage());
		}

		try {
			Object tempData = loadFileCacheFromTemp(path, cacheKey);
			if (tempData != null) {
				if (tempData instanceof Collection) {
					if (((List) tempData).size() > 0) {
						return tempData;
					}
				} else {
					return tempData;
				}
			}
		} catch (Exception e) {
			logger.warn("load file cache from temp folder failed, exception: " + e.getMessage());
		}
		throw new Exception("no cache found!");
	}

	/**
	 * Serialize object by cache key to temp folder
	 * @param path fileCache path
	 * @param cacheKey the cache file name
	 * @param obj object to be serialized
	 * @throws Exception 
	 */
	private static void saveFileCacheToTemp(String path, String cacheKey, Object obj) throws Exception {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		String fileName = path + CACHE_FILE_TEMP_PATH + FILE_SEPARATOR + cacheKey + CACHE_FILE_EXTENSION;
		OutputStream file = new FileOutputStream(fileName);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		try {
			output.writeObject(obj);
		} catch (Exception e) {
			logger.error("saveFileCacheToTemp:" + fileName + ", failed - , exception: ", e);
			throw e;
		} finally {
			output.close();
			buffer.close();

			stopWatch.stop();
			logger.info(Constants.CACHE_LOG + "FileCacheUtils:saveFileCacheToTemp, time elapsed: "
					+ stopWatch.getTotalTimeMillis());
		}
	}

	/**
	 * Copy serialized file from temp folder to pregen folder
	 * @param path fileCache path
	 * @param cacheKey the cache file name
	 * @throws Exception
	 */
	private static void copyTempFileToFinal(String path, String cacheKey) throws Exception {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		String tempFileName = path + CACHE_FILE_TEMP_PATH + FILE_SEPARATOR + cacheKey + CACHE_FILE_EXTENSION;
		String finalFileName = path + FILE_SEPARATOR + cacheKey + CACHE_FILE_EXTENSION;
		try {
			FileUtils.copyFile(new File(tempFileName), new File(finalFileName));
		} catch (Exception e) {
			logger.error("Exception during copyTempFileToFinal from: " + tempFileName + " to: " + finalFileName, e);
		}

		stopWatch.stop();
		logger.info(Constants.CACHE_LOG + "FileCacheUtils:copyTempFileToFinalFolder, time elapsed: "
				+ stopWatch.getTotalTimeMillis());
	}

	/**
	 * Deserialize object by cache key from final folder
	 * @param path cache file path
	 * @param cacheKey the cache file name
	 * @return Deserialized object 
	 * @throws Exception
	 */
	private static Object loadFileCacheFromFinal(String path, String cacheKey) throws Exception {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		InputStream file = new FileInputStream(path + FILE_SEPARATOR + cacheKey + CACHE_FILE_EXTENSION);
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);
		try {
			Object object = input.readObject();
			return object;
		} catch (Exception e) {
			logger.error("Exception during loadFileCacheFromFinal, failed - , exception: ", e);
			throw e;
		} finally {
			input.close();
			buffer.close();

			stopWatch.stop();
			logger.info(Constants.CACHE_LOG + "FileCacheUtils:loadFileCacheFromFinal, time elapsed: "
					+ stopWatch.getTotalTimeMillis());
		}
	}

	/**
	 * Deserialize object by cache key from temp folder
	 * @param path fileCache path
	 * @param cacheKey the cache file name
	 * @return Deserialized object 
	 * @throws Exception
	 */
	private static Object loadFileCacheFromTemp(String path, String cacheKey) throws Exception {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		InputStream file = new FileInputStream(path + CACHE_FILE_TEMP_PATH + FILE_SEPARATOR + cacheKey
				+ CACHE_FILE_EXTENSION);
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);
		try {
			Object object = input.readObject();
			return object;
		} catch (Exception e) {
			logger.error("Exception during loadFileCacheFromTemp", e);
			throw e;
		} finally {
			input.close();
			buffer.close();

			stopWatch.stop();
			logger.info(Constants.CACHE_LOG + "FileCacheUtils:loadFileCacheFromTemp, time elapsed: "
					+ stopWatch.getTotalTimeMillis());
		}
	}

}
