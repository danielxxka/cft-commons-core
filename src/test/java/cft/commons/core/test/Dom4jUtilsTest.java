package cft.commons.core.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import cft.commons.core.helper.dom4j.Dom4jUtils;

/**
 * @author daniel
 *
 */
public class Dom4jUtilsTest {

	@SuppressWarnings("rawtypes")
	//@Test
	public void testGetDoc() throws Exception {

		Document document = Dom4jUtils.load("D:/getSoccerFixtures.xml");
		/*		
				Element root = document.getRootElement();
				
				Element menuElement = root.addElement("menu");
				menuElement.addAttribute("menuId", "001");
				Element engNameElement = menuElement.addElement("engName");
				engNameElement.addCDATA("aaaa");

				Element chiNameElement = menuElement.addElement("chiName");
				chiNameElement.addCDATA("中文");
				
				Dom4jUtils.toXmlFile(document,"d:/student_update.xml");
				
				
				
				document = Dom4jUtils.load("D:/student_update.xml");*/

		Element rootEl = (Element) document.selectSingleNode("//api_status/status_code");
		System.out.println(rootEl.getText());

		//List nodes = rootEl.elements("fixture");  

		List nodes = rootEl.selectNodes("//fixture");

		for(Iterator iterator = nodes.iterator(); iterator.hasNext();){
			Node node = (Node)iterator.next();
			
			Dom4jUtils.toXmlFile(Dom4jUtils.toDocument((node.asXML())), "d:/"+new Date().getTime()+".xml");
		}

		System.out.println(nodes.size());

	}

}
