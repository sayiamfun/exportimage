package com.jiazhangjia.exportimage;

import com.jiazhangjia.exportimage.utils.ImageUpload.ImageUtils;
import com.sun.deploy.net.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportimageApplicationTests {

    @Test
    public void contextLoads() {
//        mergeImage("C:\\Users\\Administrator\\Pictures\\Camera Roll\\big3.jpg","C:\\Users\\Administrator\\Pictures\\Camera Roll\\smail1.jpg","0","0");
//        BMPLoader.compareImage("C:\\Users\\Administrator\\Pictures\\Camera Roll\\big3.jpg", "C:\\Users\\Administrator\\Pictures\\Camera Roll\\原3.jpg");
//        BMPLoader.compareImage("http://120.79.207.156:8080/group1/M00/00/00/rBJid1xihWKAeq6rAAAfi2XiEkU661.jpg", "http://120.79.207.156:8080/group1/M00/00/00/rBJid1xihWKAeq6rAAAfi2XiEkU661.jpg");
//        String s = ImageUtils.getBase64ByImgUrl("C:\\Users\\Administrator\\Pictures\\20190315143753.jpg");
        String s = ImageUtils.getBase64ByImgUrl("https://lwj-personal-zc.oss-cn-shenzhen.aliyuncs.com/test/15544643618531554464376994.jpeg");
        System.err.println("-----------------------------------------------------------------");
        System.err.println(s);
    }

    public void mergeImage(String bigPath,String smallPath,String x,String y){
        try {
            System.err.println("开始合成照片" + new Date());
            BufferedImage small ;
            BufferedImage big = ImageIO.read(new File(bigPath));
            if(smallPath.contains("http")){

                URL url = new URL(smallPath);
                small = ImageIO.read(url);
            }else{
                small = ImageIO.read(new File(smallPath));
            }

            Graphics2D g = big.createGraphics();

            float fx = Float.parseFloat(x);
            float fy = Float.parseFloat(y);
            int x_i = (int)fx;
            int y_i = (int)fy;
            g.drawImage(small, x_i, y_i,small.getWidth(),small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, "png", new File(bigPath));
            System.err.println("照片合成结束" + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //链接url下载图片
    private static void downloadPicture(String urlList) {
        URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  "F:/test.jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
