package org.opentosca.core.internal.file.storage.providers.service.util;

import java.io.File;

/**
 * File path utilities.<br />
 * <br />
 * Copyright 2013 IAAS University of Stuttgart<br />
 * <br />
 * 
 * @author Rene Trefft - rene.trefft@developers.opentosca.org
 * 
 */
public class PathUtils {
	
	public final static char UNIX_SEPARATOR = '/';
	
	
	/**
	 * Converts every occurrence of the system-dependent file separator in
	 * {@code path} to the Unix file separator '/'.<br />
	 * This is necessary for jclouds. Paths on a jclouds provider / api must
	 * have Unix file separators.
	 * 
	 * @param path whose file separators should be converted to the Unix file
	 *            separator.
	 * @return Path with Unix file separators.
	 */
	public static String separatorsToUnix(String path) {
		if (File.separatorChar != PathUtils.UNIX_SEPARATOR) {
			path = path.replace(File.separatorChar, PathUtils.UNIX_SEPARATOR);
		}
		return path;
	}
	
}
