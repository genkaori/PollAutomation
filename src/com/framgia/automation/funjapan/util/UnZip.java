package com.framgia.automation.funjapan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * UnZip -- print or unzip a JAR or PKZIP file using java.util.zip. Command-line
 * version: extracts files.
 *
 * @author Ian Darwin, Ian@DarwinSys.com $Id: UnZip.java,v 1.7 2004/03/07
 *         17:40:35 ian Exp $
 */
public class UnZip {
	/** Constants for mode listing or mode extracting. */
	public static final int LIST = 0, EXTRACT = 1;

	/** Whether we are extracting or just printing TOC */
	protected int mode = EXTRACT;

	/** The ZipFile that is used to read an archive */
	protected ZipFile zippy;

	/** The buffer for reading/writing the ZipFile data */
	protected byte[] b;

	/**
	 * Simple main program, construct an UnZipper, process each .ZIP file from
	 * argv[] through that object.
	 */
	public static void main(String[] argv) {
		// UnZip u = new UnZip();
		// u.unZip("C:\\1186813902484.zip");
		// for (int i = 0; i < argv.length; i++) {
		// if ("-x".equals(argv[i])) {
		// u.setMode(EXTRACT);
		// continue;
		// }
		// String candidate = argv[i];
		// // System.err.println("Trying path " + candidate);
		// if (candidate.endsWith(".zip") || candidate.endsWith(".jar"))
		// u.unZip(candidate);
		// else
		// System.err.println("Not a zip file? " + candidate);
		// }
		// System.err.println("All done!");
	}

	/** Construct an UnZip object. Just allocate the buffer */
	public UnZip() {
		b = new byte[8092];
	}

	/** Set the Mode (list, extract). */
	protected void setMode(int m) {
		if (m == LIST || m == EXTRACT) {
			mode = m;
		}
	}

	/** Cache of paths we've mkdir()ed. */
	protected SortedSet<String> dirsMade;

	/** For a given Zip file, process each entry. */
	public String unZip(String filePath, String unzipDir) {
		String zipDirName = "";
		dirsMade = new TreeSet<String>();
		try {
			zippy = new ZipFile(filePath);
			Enumeration all = zippy.entries();
			// while (all.hasMoreElements()) {
			// System.out.println(((ZipEntry)all.nextElement()).getName());
			// }
			while (all.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) all.nextElement();
				if (entry.getName().endsWith("/") && !entry.getName().contains(File.separator)) {
					zipDirName = entry.getName();
				}
				getFile(entry, unzipDir);
			}
			zippy.close();
			return unzipDir + File.separator + zipDirName.substring(0, zipDirName.length() - 1);
		} catch (IOException err) {
			System.err.println("IO Error: " + err);
			return null;
		}
	}

	protected boolean warnedMkDir = false;

	/**
	 * Process one file from the zip, given its name. Either print the name, or
	 * create the file on disk.
	 */
	protected void getFile(ZipEntry e, String unzipDir) throws IOException {
		String zipName = e.getName();
		if (zipName == null || zipName.equalsIgnoreCase("")) {
			return;
		}
		switch (mode) {
		case EXTRACT:
			if (zipName.startsWith("/")) {
				if (!warnedMkDir) {
					System.out.println("Ignoring absolute paths");
				}
				warnedMkDir = true;
				zipName = zipName.substring(1);
			}

			// if a directory, just return. We mkdir for every file,
			// since some widely-used Zip creators don't put out
			// any directory entries, or put them in the wrong place.
			if (zipName.endsWith("/")) {
				return;
			}

			// Else must be a file; open the file for output
			// Get the directory part.
			int ix = zipName.lastIndexOf('\\');
			if (ix > 0) {
				String dirName = zipName.substring(0, ix);
				if (!dirsMade.contains(dirName)) {
					File d = new File(dirName);
					// If it already exists as a dir, don't do anything
					if (!(d.exists() && d.isDirectory())) {
						// Try to create the directory, warn if it fails
						// System.out.println("Creating Directory: " + dirName);
						if (!d.mkdirs()) {
							System.err.println("Warning: unable to mkdir " + dirName);
						}
						dirsMade.add(dirName);
					}
				}
			}

			// System.out.println("Creating " + zipName);
			// String filePath = unzipDir + File.separator +
			// zipName;//.substring(zipName.indexOf(File.separator),
			// zipName.length());
			String filePath = unzipDir + File.separator + zipName;
			;
			new File(filePath).getParentFile().mkdirs();
			FileOutputStream os = new FileOutputStream(filePath);
			InputStream is = zippy.getInputStream(e);
			int n = 0;
			while ((n = is.read(b)) > 0) {
				os.write(b, 0, n);
			}
			is.close();
			os.close();
			break;
		case LIST:

			// Not extracting, just list
			if (e.isDirectory()) {
				// System.out.println("Directory " + zipName);
			} else {
				// System.out.println("File " + zipName);
			}
			break;
		default:
			throw new IllegalStateException("mode value (" + mode + ") bad");
		}
	}
}
