package com.kingjakeu.promode.common.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageDownloader {
    public static void download(String urlStr, String fileName, String extension) throws IOException {
        URL url = new URL(urlStr);

        BufferedImage image = ImageIO.read(url);
        ImageIO.write(image, extension, new File(fileName + "." + extension));
    }
}
