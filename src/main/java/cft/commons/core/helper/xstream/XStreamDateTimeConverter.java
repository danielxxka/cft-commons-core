package cft.commons.core.helper.xstream;

import java.util.TimeZone;

import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * @author daniel
 * 
 * Demo:
 * @XStreamConverter(value =  XStreamDateTimeConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
 * @XStreamAlias("birthday")
 * private Date birthday;
 *
 */
public class XStreamDateTimeConverter extends DateConverter {

	public XStreamDateTimeConverter(String dateFormat) {
		super(dateFormat, new String[] { dateFormat }, TimeZone.getDefault());
	}
}