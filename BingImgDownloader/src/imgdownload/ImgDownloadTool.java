package imgdownload;

import httptool.HttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LJ on 2015/12/17.
 * Describe : This class is responsible for match img url on web and download img
 */
public class ImgDownloadTool
{
    private String webUrl = null; //web url
    private String regex = null;  //Regular expression
    private int imgNum = 0;       //Num of img on web
    private String outputPath = null;  //Output path of img

    public ImgDownloadTool(String webUrl, String regex, int imgNum, String outputPath)
    {
        this.webUrl = webUrl;
        this.regex = regex;
        this.imgNum = imgNum;
        this.outputPath = outputPath;
    }

    /**
     * Download all img(the num of img is imgNum)
     */
    public void downloadAll()
    {
        HttpRequest httpRequest = new HttpRequest();
        String webText;
        try {
            webText = httpRequest.sendGet(webUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<String> imgUrlList = matchImgUrl(webText, regex);
        if (imgUrlList.size() == 0)
            System.out.println("No img url match, please checkout your regex");
        else
        {
            for (String imgUrl : imgUrlList)
            {
                try {
                    ImgDownloader.downloadImg(imgUrl, outputPath);
                } catch (IOException e) {
                    System.out.println("Download img error");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Use regular expression to match string
     * @param txt
     * @return List of matched String
     */
    private List<String> matchImgUrl(String txt, String regex)
    {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);
        int count = 0;
        while (matcher.find() && count++ < imgNum)
            result.add(matcher.group().replace("'", ""));
        return result;
    }

    @Override
    public String toString() {
        return "ImgDownloadTool{" +
                "webUrl='" + webUrl + '\'' +
                ", regex='" + regex + '\'' +
                ", imgNum=" + imgNum +
                ", outputPath='" + outputPath + '\'' +
                '}';
    }
}
