package util.file;

import java.io.File;
import java.io.IOException;

import org.apache.poi.util.IOUtils;

public class FileUtils {
	public static File createNewFile(String name) {
		File f = new File("outputs" + File.separator + name);
		if (f.exists()) {
			f.delete();
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建文件f失败");
				e.printStackTrace();
			}
		}
		return f;
	}

	public static File createNewFile(String path, String name) {
		File f = new File(path + File.separator + name);
		if (f.exists()) {
			f.delete();
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建文件f失败");
				e.printStackTrace();
			}
		}
		return f;
	}

	public static void main(String[] args) {
	}

	public static void writeLine(File f) {
		
	}
}
