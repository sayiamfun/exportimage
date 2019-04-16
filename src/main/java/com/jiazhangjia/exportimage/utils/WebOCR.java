package com.jiazhangjia.exportimage.utils;

import com.jiazhangjia.exportimage.utils.AliOCR.FileUtil;
import com.jiazhangjia.exportimage.utils.AliOCR.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WebOCR {
    // OCR webapi 接口地址
    private static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
    // 应用ID
    private static final String APPID = "5cb08151";
    // 接口密钥
    private static final String API_KEY = "ec550922be434c41a08f43b508a09fd7";
    // 是否返回位置信息
    private static final String LOCATION = "false";
    // 语种
    private static final String LANGUAGE = "cn|en";
    // 图片地址
    private static final String PIC_PATH = "C:\\Users\\Administrator\\Pictures\\Camera Roll\\20190412104034.jpg";

    /**
     * OCR WebAPI 调用示例程序
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Map<String, String> header = buildHttpHeader();
        byte[] imageByteArray = FileUtil.read(PIC_PATH);
        String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
        String result = HttpUtil.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
        System.out.println("OCR WebAPI 接口调用结果：" + result);
    }

    /**
     * 组装http请求头
     */
    private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }
}
