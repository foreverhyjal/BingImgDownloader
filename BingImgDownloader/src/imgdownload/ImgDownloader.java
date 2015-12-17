package imgdownload;

import java.io.*;
import java.net.URL;

/**
 * Created by LJ on 2015/12/17.
 * Describe : This class is responsible for download image
 */
public class ImgDownloader
{
    /**
     * Download img from url, use url's filename as output filename
     * @param imgUrl image url
     * @param outputPath image file output path
     * @throws IOException
     */
    public static void downloadImg(String imgUrl, String outputPath) throws IOException
    {
        URL url = new URL(imgUrl);
        String fileName = url.getFile();
        String destName = outputPath + File.separator + fileName.substring(fileName.lastIndexOf("/"));

        InputStream inStream = url.openStream();
        OutputStream outStream = new FileOutputStream(destName);

        byte[] b = new byte[1024];
        int length;
        while ((length = inStream.read(b)) != -1)
            outStream.write(b, 0, length);
        inStream.close();
        outStream.close();
    }
}
