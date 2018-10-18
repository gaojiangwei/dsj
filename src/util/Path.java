package util;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class Path {

	public static String PATH = "F:\\apache-tomcat-8.0.28\\webapps\\image";
	
	
	public static String saveImg(MultipartFile spImg) {
		String newFileName = "";
		if (spImg != null && !spImg.isEmpty()) {
			// 鑾峰彇鍥剧墖鐨勬枃浠跺悕
            String fileName = spImg.getOriginalFilename();
            // 鑾峰彇鍥剧墖鐨勬墿灞曞悕
            String extensionName = fileName
                    .substring(fileName.lastIndexOf(".") + 1);
            // 鏂扮殑鍥剧墖鏂囦欢鍚� = 鑾峰彇鏃堕棿鎴�+"."鍥剧墖鎵╁睍鍚�
            newFileName = String.valueOf(System.currentTimeMillis())
                    + "." + extensionName;
            //鍥剧墖淇濆瓨璺緞
            String saveFilePath = Path.PATH;
            /* 鏋勫缓鏂囦欢鐩綍 */
            File fileDir = new File(saveFilePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            
            try {
                FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
                        + newFileName);
                // 鍐欏叆鏂囦欢
                out.write(spImg.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
		}
		return newFileName;
	}
}
