package com.jiazhangjia.exportimage.utils.AliOCR;

import com.jiazhangjia.exportimage.utils.ImageUpload.ImageUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云OCR
 */
public class AL {
    public static void main(String[] args) {
        String host = "https://ocrapi-advanced.taobao.com";
        String path = "/ocrservice/advanced";
        String method = "POST";
        String appcode = "73308c95ccc44278b80656e2fd629df6";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        long time = new Date().getTime();
        //将网络地址图片转为base64
        String s = ImageUtils.getBase64ByImgUrl("https://lwj-personal-zc.oss-cn-shenzhen.aliyuncs.com/test/20190412104034.jpg");
        String bodys = "{\"img\":\""+s+"\",\"url\":\"\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false}";
        try {
            //发送请求识别文字
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
            //获取response的body
            long time1 = new Date().getTime();
            System.err.println("时长" + (time-time1));
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
