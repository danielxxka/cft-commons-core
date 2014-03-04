package cft.commons.core.helper.jackson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cft.commons.core.constant.Constants;

/** 
 * @author daniel
 * 
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<Date> {

	private static final Logger logger = LoggerFactory.getLogger(JsonDateTimeDeserializer.class);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			return DateUtils.parseDate(parser.getText(), new String[] { Constants.C_DATETIME_PATTERN_DEFAULT });
		} catch (Exception e) {
			logger.error("Exception during JsonDateTimeDeserializer:deserialize, init date ot 1970-01-01");
			Calendar ca = Calendar.getInstance();
			ca.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
			return ca.getTime();
		}
	}
}
