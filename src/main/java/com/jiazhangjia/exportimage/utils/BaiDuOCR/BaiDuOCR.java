package com.jiazhangjia.exportimage.utils.BaiDuOCR;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
 * 百度OCR
 */
public class BaiDuOCR {
    public static final String APP_ID = "16000571";
    public static final String API_KEY = "lVYdRYel7CZaSjhXhZHlarSi";
    public static final String SECRET_KEY = "hN1xMQK3tiGO7ExTM7Oz9KA1TashhhE3";

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", SECRET_KEY);
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        String accessToken = AuthService.getAuth(API_KEY, SECRET_KEY);
        // 通用识别url
        // 本地图片路径
//        String filePath = "C:\\Users\\Administrator\\Pictures\\Camera Roll\\20190412104034.jpg";
        String filePath = "https://lwj-personal-zc.oss-cn-shenzhen.aliyuncs.com/test/20190412104034.jpg";
        try {
            byte[] imgData = getByte(filePath, "jpg");
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Title           getImgeHexString
     * @Description     网络图片转换成二进制字符串
     * @param URLName   网络图片地址
     * @param type      图片类型
     * @return  String  转换结果
     * @throws
     */
    public static byte[] getByte(String URLName,String type) {
        byte[] data  = null;
        try {
            int HttpResult = 0; // 服务器返回的状态
            URL url = new URL(URLName); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            System.out.println(HttpResult);
            if (HttpResult != HttpURLConnection.HTTP_OK) // 不等于HTTP_OK则连接不成功
                System.out.print("fail");
            else {
                BufferedInputStream bis = new BufferedInputStream(urlconn.getInputStream());

                BufferedImage bm = ImageIO.read(bis);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bm, type, bos);
                bos.flush();
                data = bos.toByteArray();
                bos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
