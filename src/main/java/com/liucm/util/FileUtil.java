package com.liucm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

import org.springframework.stereotype.Component;

/**
 * 文件处理工具
 * 
 * @author xiaoming
 * @version 1.0
 */
@Component
public class FileUtil {

	/**
	 * 将源文件路径下的文件复制到目标文件路径下
	 * 
	 * @param sourcePath 源文件路径
	 * @param targetPath 目标文件路径
	 */
	public void copyFile(String sourcePath, String targetPath) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(new File(sourcePath));
			out = new FileOutputStream(new File(targetPath));
			byte[] buff = new byte[1024];
			int len = in.read(buff);
			while (len != -1) {
				out.write(buff, 0, len);
				len = in.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 将源文件复制到目标文件路径下
	 * 
	 * @param sourceFile 源文件
	 * @param targetPath 目标文件路径
	 */
	public void copyFile(File sourceFile, String targetPath) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(new File(targetPath));
			byte[] buff = new byte[1024];
			int len = in.read(buff);
			while (len != -1) {
				out.write(buff, 0, len);
				len = in.read(buff);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 源文件流复制给目标文件流
	 * 
	 * @param in  文件输入流
	 * @param out 文件输出流
	 */
	public void copyFile(InputStream in, String targetPath) {

		File dest = new File(targetPath);
		if (!dest.getParentFile().exists()) {
			// 判断文件父目录是否存在
			dest.getParentFile().mkdirs();
		}

		byte[] buff = new byte[1024];
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(targetPath));
			int len = in.read(buff);
			while (len != -1) {
				out.write(buff, 0, len);
				len = in.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void copyFile(InputStream in, OutputStream out) {
		byte[] buff = new byte[1024];

		try {
			int len = in.read(buff);
			while (len != -1) {
				out.write(buff, 0, len);
				len = in.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 将两源路径下文件拼接后复制给目标路径下
	 * 
	 * @param sourcePath_Frist  源文件路径之一
	 * @param sourcepath_Second 源文件路径之二
	 * @param targetpath        目标文件路径
	 */
	public void fileConnectAndFileCopy(String sourcePath_Frist, String sourcepath_Second, String targetpath) {
		FileInputStream in_frist = null;
		FileInputStream in_second = null;
		InputStream in_fileConnect = null;
		FileOutputStream out_target = null;
		try {
			in_frist = new FileInputStream(new File(sourcePath_Frist));
			in_second = new FileInputStream(new File(sourcepath_Second));
			in_fileConnect = new SequenceInputStream(in_frist, in_second);
			out_target = new FileOutputStream(new File(targetpath));
			copyFile(in_fileConnect, out_target);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out_target) {
				try {
					out_target.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in_fileConnect) {
				try {
					in_fileConnect.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in_second) {
				try {
					in_second.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in_frist) {
				try {
					in_frist.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将两个源文件流拼接后复制给目标文件流
	 * 
	 * @param in_frist   源文件流之一
	 * @param in_second  源文件流之二
	 * @param out_target 目标文件流
	 */
	public void fileConnectAndFileCopy(InputStream in_frist, InputStream in_second, OutputStream out_target) {
		InputStream in_fileConnect = new SequenceInputStream(in_frist, in_second);
		copyFile(in_fileConnect, out_target);
		try {
			out_target.close();
			in_fileConnect.close();
			in_second.close();
			in_frist.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将源目录文件目录下的文件复制到目标文件路径下
	 * 
	 * @param sourceDirectory 源目录文件
	 * @param targetPath      目标文件路径
	 */
	public void traversalCopyDirectory(File sourceDirectory, String targetPath) {
		File targetFile = null;
		if (!sourceDirectory.exists()) {
			return;
		} else {
			targetPath = targetPath + "\\" + sourceDirectory.getName();
			targetFile = new File(targetPath);
		}
		if (sourceDirectory.isFile()) {
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			copyFile(sourceDirectory, targetPath);
		} else if (sourceDirectory.isDirectory()) {
			File[] childs = sourceDirectory.listFiles();
			if (childs.length == 0) {
				targetFile.mkdir();
			}
			for (File child : childs) {
				traversalCopyDirectory(child, targetPath);
			}
		}
	}

	public boolean isExist(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 删除文件夹（强制删除）
	 * 
	 * @param path
	 */
	public void deleteAllFilesOfDir(File file) {
		if (null != file) {
			if (!file.exists())
				return;
			if (file.isFile()) {
				boolean result = file.delete();
				int tryCount = 0;
				while (!result && tryCount++ < 10) {
					System.gc(); // 回收资源
					result = file.delete();
				}
			}
			File[] files = file.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					deleteAllFilesOfDir(files[i]);
				}
			}
			file.delete();
		}
	}
	
	public File getFile(String path) {
		return new File(path);
	}

}
