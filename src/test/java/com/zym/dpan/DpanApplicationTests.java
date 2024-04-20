package com.zym.dpan;



import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DpanApplicationTests {

	@Test
	public void contextLoads() {
	}
	private static String getFileExtension(String filePath) {
		String extension = "";
		int dotIndex = filePath.lastIndexOf(".");
		if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
			extension = filePath.substring(dotIndex + 1);
		}
		return extension;
	}

	@Test
	public void testFileType(){
		String filename = "example.7z";
//		filename = "23";
		System.out.println("File name:"+filename);
		String fileType = "";
		Tika tika = new Tika();

		fileType = tika.detect(filename);
		System.out.println("File type:"+fileType);
		Object[] objects = new Object[4];
		objects[0]=1;
		objects[1]="2";
		System.out.println(objects[0]+""+objects[1]);
	}
	@Test
	public void testCode() throws UnsupportedEncodingException {
		String filename = "动态文件名df23.ext";  // 动态生成的文件名
// 将文件名转换为ASCII字符集
		String asciiFilename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.US_ASCII);
// 使用UTF-8编码对文件名进行URL编码
		String encodedFilename = URLEncoder.encode(asciiFilename, String.valueOf(StandardCharsets.UTF_8));

		System.out.println(encodedFilename);

	}

}
