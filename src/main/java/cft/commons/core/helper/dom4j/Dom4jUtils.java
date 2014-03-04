package cft.commons.core.helper.dom4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cft.commons.core.constant.Constants;

/**
 * @author daniel
 *
 */
public class Dom4jUtils {

	/**
	 *  读写XML文档主要依赖于org.dom4j.io包，有DOMReader和SAXReader两种方式。因为利用了相同的接口，它们的调用方式是一样的。
	 */
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 *  读写XML文档主要依赖于org.dom4j.io包，有DOMReader和SAXReader两种方式。因为利用了相同的接口，它们的调用方式是一样的。
	 */
	public static Document load(URL url) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(url); // 读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * xml转换为字符串
	 * @param doc
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String toString(Document doc) throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(Constants.ENCODING_UTF8);
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(byteOS, Constants.ENCODING_UTF8), format);
		writer.write(doc);
		writer.flush();
		writer.close();
		writer = null;

		return byteOS.toString("UTF-8");
	}

	/**
	 * 字符串转换为Document
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document toDocument(String xmlStr) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlStr);
		return document;
	}

	/**
	 *  Dom4j通过XMLWriter将Document对象表示的XML树写入指定的文件，并使用OutputFormat格式对象指定写入的风格和编码方法。
	 *  调用OutputFormat.createPrettyPrint()方法可以获得一个默认的pretty print风格的格式对象。
	 *  对OutputFormat对象调用setEncoding()方法可以指定XML文件的编码方法。
	 * @param document
	 * @param filename
	 * @return
	 */
	public static boolean toXmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(Constants.ENCODING_UTF8);
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(filename), Constants.ENCODING_UTF8), format);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

}
