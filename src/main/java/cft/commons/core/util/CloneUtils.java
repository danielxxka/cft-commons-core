package cft.commons.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  complex object deep copy
 * 
 * @author daniel
 *
 */
public class CloneUtils {

	private static final Logger logger = LoggerFactory.getLogger(CloneUtils.class);

	/**
	 * Deep Copy - object have to implements Serializable interface
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deepCopy(T obj) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;

		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;

		Object o = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bais = new ByteArrayInputStream(baos.toByteArray());
			ois = new ObjectInputStream(bais);

			o = ois.readObject();
			return (T) o;
		} catch (Exception e) {
			logger.error("Exception during CloneUtils.deepCopy:", e);
		} finally {
			try {
				baos.close();
				oos.close();
				bais.close();
				ois.close();
			} catch (Exception e) {
				logger.error("Exception during CloneUtils.deepCopy:ois.close():", e);
			}
		}

		return obj;
	}
}
