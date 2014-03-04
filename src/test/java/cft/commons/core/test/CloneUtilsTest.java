package cft.commons.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cft.commons.core.util.CloneUtils;

/**
 * @author daniel
 *
 */
public class CloneUtilsTest {


	@Test
	public void cloneList() {

		List<String> srcList = new ArrayList<String>();
		srcList.add("AAA");
		srcList.add("BBB");
		srcList.add("CCC");

		List<String> cloneList = (List<String>) CloneUtils.deepCopy(srcList);
		cloneList.remove(1);

		System.out.println("srcList = " + srcList);
		System.out.println("cloneList = " + cloneList);

	}

}
