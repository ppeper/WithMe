package com.bonobono.backend.global.config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class test {


    public test() throws IOException {
        byte[] imageInByte;

        BufferedImage originalImage = ImageIO.read(new File("C:/Users/SSAFY/Downloads/imag1.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos);
        baos.flush();

        imageInByte = baos.toByteArray();
        System.out.println(Arrays.toString(imageInByte));


        baos.close();
    }


}
