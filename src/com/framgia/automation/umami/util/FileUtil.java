package com.framgia.automation.umami.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	public static void copyFile(String src, String des) throws IOException {
		File desFile = new File(des);
		File srcFile = new File(src);
		FileOutputStream os = new FileOutputStream(desFile);
		FileInputStream in = new FileInputStream(srcFile);
		copyStream(in, os, srcFile.length());
		in.close();
		os.close();
	}

	public static boolean createFile(InputStream stream, String path, long fileSize) {
		try {
			File file = new File(path);
			FileOutputStream out = new FileOutputStream(file);
			boolean result = copyStream(stream, out, fileSize);
			out.close();
			// stream.close();

			File dir = new File("C:\\upload");
			if (!dir.exists()) {
				dir.mkdir();
			}

			return result;

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private static boolean copyStream(InputStream ins, OutputStream out, long fileSize) {
		try {
			synchronized (ins) {
				synchronized (out) {
					byte[] buffer = new byte[8192];
					long b = 0;
					while (b < fileSize) {
						int bytes = ins.read(buffer);
						b += bytes;
						out.write(buffer, 0, bytes);
						out.flush();
					}
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delFile(String path) {
		File file = new File(path);
		return file.delete();
	}

	public static InputStream readFile(String path) {

		File file = new File(path);
		if (file != null && file.exists()) {
			try {
				InputStream stream = new FileInputStream(file);
				return stream;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean createDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdir();
		}
		return false;
	}

	public static boolean copyFolder(String src, String des) throws FileNotFoundException {
		File srcFolder = new File(src);
		File desFolder = new File(des);
		if (!srcFolder.isDirectory()) {
			return false;
		}
		if (desFolder.exists()) {
			System.out.println("destination folder aready exist!");
			return false;
		}
		desFolder.mkdirs();
		File[] listFile = srcFolder.listFiles();
		for (File file : listFile) {
			String fileName = file.getName();
			FileUtil.createFile(new FileInputStream(file), des + File.separator + fileName, file.length());
		}
		return false;
	}

	public static void deleteFolder(String dir) {
		File folder = new File(dir);
		if (!folder.exists() || !folder.isDirectory()) {
			return;
		}
		File[] list = folder.listFiles();
		for (File file : list) {
			if (file.isDirectory()) {
				deleteFolder(file.getAbsolutePath());
			}
			file.delete();
		}
		folder.delete();

	}

	public static void makeZipFile(String dir, String fileName) {
		File resource = new File(dir);
		if (resource.exists() && resource.isDirectory()) {
			File[] listFiles = resource.listFiles();
			if (listFiles != null && listFiles.length > 0) {
				try {
					FileOutputStream fout = new FileOutputStream(
							new File(resource.getParentFile().getAbsolutePath() + File.separator + fileName));
					ZipOutputStream zip = new ZipOutputStream(fout);
					byte[] tmpBuf = new byte[1024];
					for (int i = 0; i < listFiles.length; i++) {
						if (listFiles[i].isDirectory()) {
							File[] subFileList = listFiles[i].listFiles();
							zip.putNextEntry(new ZipEntry(listFiles[i].getName()));
							zip.closeEntry();
							for (File file : subFileList) {
								zip.putNextEntry(
										new ZipEntry(listFiles[i].getName() + File.separator + file.getName()));
								FileInputStream in = new FileInputStream(file.getAbsolutePath());
								System.out.println("put file: " + file.getAbsolutePath());
								int len;
								while ((len = in.read(tmpBuf)) > 0) {
									zip.write(tmpBuf, 0, len);
									System.out.println("put: " + new String(tmpBuf));
								}
								zip.closeEntry();
								in.close();
							}
						} else {
							zip.putNextEntry(new ZipEntry(listFiles[i].getName()));
							FileInputStream in = new FileInputStream(listFiles[i].getAbsolutePath());
							int len;
							while ((len = in.read(tmpBuf)) > 0) {
								zip.write(tmpBuf, 0, len);
							}
							zip.closeEntry();
							in.close();
						}
					}
					zip.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void makeZip(String dir, String fileName) {
		try {
			// create a ZipOutputStream to zip the data to
			ZipOutputStream zos = new ZipOutputStream(
					new FileOutputStream((new File(dir)).getParentFile().getPath() + File.separator + fileName));
			// assuming that there is a directory named inFolder (If there
			// isn't create one) in the same directory as the one the code
			// runs from,
			// call the zipDir method
			zipDir("", dir, zos);
			// close the stream
			zos.close();
		} catch (Exception e) {
			// handle exception
		}
	}

	public static void zipDir(String parent, String dir2zip, ZipOutputStream zos) {
		try {
			// create a new File object based on the directory we
			// have to zip File
			File zipFile = new File(dir2zip);
			// get a listing of the directory content
			String[] dirList = zipFile.list();
			byte[] readBuffer = new byte[2156];
			int bytesIn = 0;
			// loop through dirList, and zip the files
			for (int i = 0; i < dirList.length; i++) {
				File f = new File(zipFile, dirList[i]);
				if (f.isDirectory()) {
					// if the File object is a directory, call this
					// function again to add its content recursively
					String filePath = f.getPath();
					zipDir(f.getParentFile().getName(), filePath, zos);
					// loop again
					continue;
				}
				// if we reached here, the File object f was not a directory
				// create a FileInputStream on top of f
				FileInputStream fis = new FileInputStream(f);
				// create a new zip entry
				String entryName = f.getParentFile().getName() + File.separator + f.getName();
				if (!CommonUtil.isEmpty(parent)) {
					entryName = parent + File.separator + entryName;
				}
				ZipEntry anEntry = new ZipEntry(entryName);
				// place the zip entry in the ZipOutputStream object
				zos.putNextEntry(anEntry);
				// now write the content of the file to the ZipOutputStream
				while ((bytesIn = fis.read(readBuffer)) != -1) {
					zos.write(readBuffer, 0, bytesIn);
				}
				anEntry.setSize(f.length());
				// close the Stream
				zos.closeEntry();
				fis.close();
			}
		} catch (Exception e) {
			// handle exception
		}
	}

	public static String extractZipFile(String filePath, String unzipDir) {
		// File file = new File(unzipDir);
		// if (!file.exists()) {
		// file.mkdirs();
		// }

		if (!filePath.endsWith("zip")) {
			return null;
		}

		UnZip unZip = new UnZip();
		return unZip.unZip(filePath, unzipDir);

		// file.delete();
	}

}
