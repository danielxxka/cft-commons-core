package cft.commons.core.test;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

import cft.commons.core.helper.junit.Order;
import cft.commons.core.helper.junit.OrderedRunner;
import cft.commons.core.util.FileDirUtils;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author daniel
 *
 */
@RunWith(OrderedRunner.class)
public class FileDirUtilsTest {


	

	@Test
	@Order(order=1)
	public void testCopyURLToFile() throws Exception {

		URL url = new URL("http://www.baidu.com/img/bdlogo.gif");

		url.openConnection().setConnectTimeout(5000);
		url.openConnection().setReadTimeout(5000);

		FileUtils.forceMkdir(new File("/test1"));
		FileUtils.copyURLToFile(url, new File("/test1/mde_gogogo.png"));
	}

	@Test
	@Order(order=2)
	public void testOthersFunctions() throws Exception {

		FileUtils.copyFile(new File("/test1/mde_gogogo.png"), new File("/test1/mde_gogogo2.png"));
		System.out.println(FileUtils.isFileNewer(new File("/test1/mde_gogogo.png"), new Date().getTime() - 3600));

		FileUtils.deleteQuietly(new File("/test1/mde_gogogo2.png"));
		//FileUtils.forceDelete(new File("/test1"));

		System.out.println("dir size = " + FileUtils.sizeOfDirectory(new File("/test1")));

	}
	
	@Test
	@Order(order=3)
	public void listFiles() throws Exception {

		String[] exts = new String[] { "txt", "rar" };
		List<File> fileList = FileDirUtils.listFiles(new File("/test1"), exts, true);

		for (File file : fileList) {
			System.out.println(file);
		}

		System.out.println("fileList.size() = " + fileList.size());

	}

}
