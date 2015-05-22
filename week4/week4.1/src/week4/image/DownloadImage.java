package week4.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage {

    public static void main(String[] args) throws IOException {
        String imageUrl = "http://d3dsacqprgcsqh.cloudfront.net/photo/aozrdx0_700b.jpg";
        String destinationFile = "image.jpg";

        downloadImage(imageUrl, destinationFile);
    }

    private static void downloadImage(String imageUrl, String destinationFile) throws MalformedURLException,
            IOException {
        URL url = new URL(imageUrl);
        try (InputStream input = url.openStream(); OutputStream output = new FileOutputStream(destinationFile)) {
            byte[] b = new byte[2048];
            int length;

            while ((length = input.read(b)) != -1) {
                output.write(b, 0, length);
                //utput.write(b);
                
            }
        }

    }
}
