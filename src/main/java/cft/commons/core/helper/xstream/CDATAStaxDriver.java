package cft.commons.core.helper.xstream;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;

public class CDATAStaxDriver extends StaxDriver {

	private static final Logger logger = LoggerFactory.getLogger(CDATAStaxDriver.class);

	@Override
	public StaxWriter createStaxWriter(XMLStreamWriter out, boolean writeStartEndDocument) throws XMLStreamException {
		final XMLStreamWriter writer = out;
		return new StaxWriter(getQnameMap(), out, writeStartEndDocument, isRepairingNamespace()) {

			@Override
			public void startNode(String name) {
				super.startNode(name);
			}

			@Override
			public void setValue(String value) {
				try {	
					writer.writeCData(value);
				} catch (XMLStreamException e) {
					logger.error("Exception during CDATAStaxDriver:createStaxWriter", e);
				}
			}

		};
	}
}
