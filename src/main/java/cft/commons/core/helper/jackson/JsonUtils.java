package cft.commons.core.helper.jackson;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @author daniel
 * 
 */
public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper objectMapper;

	/**
	 * JSON对象序列化
	 */
	public static String toJSON(Object obj) {

		StringWriter sw = new StringWriter();
		JsonGenerator jsonGen = getGenerator(sw);

		if (jsonGen == null) {
			try {
				sw.close();
			} catch (IOException e) {
				logger.error("IOException during JSONUtil:toJSON", e);
			}
			return null;
		}

		try {

			//由于在getGenerator方法中指定了OutputStream为sw,因此调用writeObject会将数据输出到sw
			jsonGen.writeObject(obj);

			//由于采用流式输出 在输出完毕后务必清空缓冲区并关闭输出流
			jsonGen.flush();
			jsonGen.close();
			return sw.toString();

		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException during JSONUtil:toJSON", e);
		} catch (IOException e) {
			logger.error("IOException during JSONUtil:toJSON", e);
		}

		return null;
	}

	/**
	 * JSON对象反序列化
	 */
	public static <T> T fromJSON(String json, Class<T> clazz) {

		try {
			JsonParser jp = getParser(json);
			return jp.readValueAs(clazz);
		} catch (JsonParseException e) {
			logger.error("JsonParseException during JSONUtil:fromJSON", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException during JSONUtil:fromJSON", e);
		} catch (IOException e) {
			logger.error("IOException during JSONUtil:fromJSON", e);
		}

		return null;
	}

	/**
	 * 单例模式得到ObjectMapper实例
	 * 此对象为Jackson的核心
	 */
	private static ObjectMapper getMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();

			//当找不到对应的序列化器时 忽略此字段
			objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
			objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);

			//使Jackson JSON支持Unicode编码非ASCII字符
			CustomSerializerFactory serializerFactory = new CustomSerializerFactory();
			serializerFactory.addSpecificMapping(String.class, new JsonUnicodeSerializer());
			objectMapper.setSerializerFactory(serializerFactory);
		}

		return objectMapper;
	}

	/**
	 * 创建JSON处理器的静态方法
	 * @param content JSON字符串
	 * @return
	 */
	private static JsonParser getParser(String content) {
		try {
			return getMapper().getJsonFactory().createJsonParser(content);
		} catch (IOException e) {
			logger.error("IOException during JSONUtil:getParser", e);
			return null;
		}
	}

	/**
	 * 创建JSON生成器的静态方法, 使用标准输出
	 * @return
	 */
	private static JsonGenerator getGenerator(StringWriter sw) {
		try {
			return getMapper().getJsonFactory().createJsonGenerator(sw);
		} catch (IOException e) {
			logger.error("IOException during JSONUtil:getGenerator", e);
			return null;
		}
	}
}
