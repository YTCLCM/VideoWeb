package com.liucm.util;

import java.io.IOException;
import java.io.InputStream;

public class ThumbnailThread extends Thread {

	private String saveFileName;

	private String thumbnailUrl;

	public ThumbnailThread(String saveFileName, String thumbnailUrl) {
		this.saveFileName = saveFileName;
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public void run() {
		try {
			String videothumbnailcommand = "cmd /c start /b ffmpeg -y -i " + "\"" + saveFileName + "\""
					+ " -ss 5 -s 1920*1080 -f image2 -vframes 1 " + "\"" + thumbnailUrl + "\"";

			System.out.println(videothumbnailcommand);

			Process process = Runtime.getRuntime().exec(videothumbnailcommand);

			InputStream inputStream = process.getInputStream();
			new FileUtil().copyFile(inputStream, thumbnailUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
