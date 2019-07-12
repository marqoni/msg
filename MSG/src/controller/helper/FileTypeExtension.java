package controller.helper;

import org.apache.commons.io.FilenameUtils;

public class FileTypeExtension {
	
	public static String getFileTypeExtension(String filePath) {
		////lastIndexoF .
		return FilenameUtils.getExtension(filePath);
	}
}
