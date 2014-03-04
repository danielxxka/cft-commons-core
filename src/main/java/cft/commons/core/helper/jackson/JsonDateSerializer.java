package cft.commons.core.helper.jackson;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import cft.commons.core.constant.Constants;

/**
 * @author daniel
 *
 *	Demo:
 *	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate = new Date();  // output createDate":"2011-08-10"
 *
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

		String formattedDate = DateFormatUtils.format(date, Constants.C_DATE_PATTERN_DEFAULT);
		jgen.writeString(formattedDate);

	}
}