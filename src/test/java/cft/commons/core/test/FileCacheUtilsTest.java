package cft.commons.core.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cft.commons.core.test.model.Member;
import cft.commons.core.util.FileCacheUtils;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


/**
 * @author daniel
 *
 */
public class FileCacheUtilsTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testFileCache() throws Exception {
		Member mb1 = new Member("staff001", null, null, null, null);
		Member mb2 = new Member("staff002", null, null, null, null);

		List<Member> mbList = new ArrayList<Member>();
		mbList.add(mb1);
		mbList.add(mb2);

		FileUtils.forceMkdir(new File("/test1/temp")); //cache file repo
		String fileCachePath = "/test1";
		String fileCacheKey = "getMemberList";

		FileCacheUtils.saveFileCache(fileCachePath, fileCacheKey, mbList);

		List<Member> mbList2 = (List<Member>) FileCacheUtils.loadFileCache(fileCachePath, fileCacheKey);

		System.out.println(mbList2);

	}

}
