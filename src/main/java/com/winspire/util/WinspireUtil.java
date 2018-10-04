package com.winspire.util;

import java.io.File;
import java.net.URLConnection;

import javax.activation.MimetypesFileTypeMap;

public class WinspireUtil {

	private static final String PDF_MIME_TYPE = "image/jpeg";

	public static String getMimeType(File file) {
		// TODO Auto-generated method stub
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file.getName());
		}
		if (PDF_MIME_TYPE.equalsIgnoreCase(mimeType)) {
			return mimeType;
		} else {
			return null;
		}
	}

}
