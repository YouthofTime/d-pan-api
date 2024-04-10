package com.zym.dpan;



import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;

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
		System.out.println("File name:"+filename);
		String fileType = "";
		Tika tika = new Tika();

		fileType = tika.detect(filename);
		System.out.println("File type:"+fileType);
	}

}
