package imgdownload;

import java.util.List;

/**
 * Created by LJ on 2015/12/17.
 */
public class DownloadImg
{
    public static void main(String[] args) {
        ImgDownloadToolConfiguration configuration = ImgDownloadToolConfiguration.getInstance();
        configuration.init("DownloadConfiguration.xml");

        List<ImgDownloadTool> toolList = configuration.getDownloadToolArray();
        for (ImgDownloadTool tool : toolList)
            tool.downloadAll();
    }
}
