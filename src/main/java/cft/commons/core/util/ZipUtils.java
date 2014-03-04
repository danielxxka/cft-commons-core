package cft.commons.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author daniel
 *
 */
public class ZipUtils {

	private OutputStream out = null;
	private BufferedOutputStream bos = null;
	private ZipArchiveOutputStream zaos = null;
	private String zipFileName = null;

	/**
	 * 把一个目录打包到ZIP文件中的某目录
	 * @param dirpath  目录绝对地址
	 * @param pathName ZIP中目录
	 */
	public void zipFiles(File fileDir, String pathName) throws FileNotFoundException, IOException {
		if (StringUtils.isNotEmpty(pathName)) {
			pathName = pathName + File.separator;
		}
		zipFiles(zaos, fileDir, pathName);
	}

	/**
	 * 把一个目录打包到一个指定的ZIP文件中
	 * @param dirpath 目录绝对地址
	 * @param pathName ZIP文件抽象地址
	 */
	private void zipFiles(ZipArchiveOutputStream zaos, File fileDir, String pathName) throws FileNotFoundException,
			IOException {

		if (fileDir.isDirectory()) {
			// 返回此绝对路径下的文件
			File[] files = fileDir.listFiles();
			if (files == null || files.length < 1) {
				return;
			}
			for (int i = 0; i < files.length; i++) {
				// 判断此文件是否是一个文件夹
				if (files[i].isDirectory()) {
					zipFiles(zaos, files[i], pathName + files[i].getName() + File.separator);
				} else {
					zaos.putArchiveEntry(new ZipArchiveEntry(pathName + files[i].getName()));
					IOUtils.copy(new FileInputStream(files[i].getAbsolutePath()), zaos);
					zaos.closeArchiveEntry();
				}
			}
		} else {
			zaos.putArchiveEntry(new ZipArchiveEntry(pathName + fileDir.getName()));
			IOUtils.copy(new FileInputStream(fileDir.getAbsolutePath()), zaos);
			zaos.closeArchiveEntry();
		}
	}

	/**
	 * 把一个ZIP文件解压到一个指定的目录中
	 *
	 * @param zipfilename
	 *            ZIP文件抽象地址
	 * @param outputdir
	 *            目录绝对地址
	 */
	public static void unZipToFolder(String zipfilename, String outputdir) throws IOException {
		File zipfile = new File(zipfilename);
		if (zipfile.exists()) {
			outputdir = outputdir + File.separator;
			FileUtils.forceMkdir(new File(outputdir));

			ZipFile zf = new ZipFile(zipfile, "UTF-8");
			Enumeration<ZipArchiveEntry> zipArchiveEntrys = zf.getEntries();
			while (zipArchiveEntrys.hasMoreElements()) {
				ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) zipArchiveEntrys.nextElement();
				if (zipArchiveEntry.isDirectory()) {
					FileUtils.forceMkdir(new File(outputdir + zipArchiveEntry.getName() + File.separator));
				} else {
					IOUtils.copy(zf.getInputStream(zipArchiveEntry),
							FileUtils.openOutputStream(new File(outputdir + zipArchiveEntry.getName())));
				}
			}
		} else {
			throw new IOException("Zip file is not exsit：\t" + zipfilename);
		}
	}

	public ZipUtils(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public void createZipOut() throws FileNotFoundException, IOException {
		File f = new File(zipFileName);
		out = new FileOutputStream(f);
		bos = new BufferedOutputStream(out);
		zaos = new ZipArchiveOutputStream(bos);
		zaos.setEncoding("UTF-8");
	}

	public void closeZipOut() throws Exception {
		zaos.flush();
		zaos.close();

		bos.flush();
		bos.close();

		out.flush();
		out.close();
	}
}