package imgdownload;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJ on 2015/12/17.
 * Describe : This class is responsible for set download configuration with xml file
 */
public class ImgDownloadToolConfiguration
{
    private static ImgDownloadToolConfiguration instance = null;
    private List<ImgDownloadTool> downloadToolArray = null;

    private ImgDownloadToolConfiguration()
    {
        downloadToolArray = new ArrayList<>();
    }

    public static ImgDownloadToolConfiguration getInstance()
    {
        if (instance == null)
            instance = new ImgDownloadToolConfiguration();
        return instance;
    }

    /**
     * Init configuration with xml file
     * @param initXmlFileName
     */
    public void init(String initXmlFileName)
    {
        File file = new File(initXmlFileName);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(file);
            Element root = doc.getRootElement();
            List<Element> configList = root.getChildren("DownloadInfo");
            for (Element e : configList)
            {
                String url = e.getChild("url").getValue();
                String regex = e.getChild("regex").getValue();
                int num = Integer.valueOf(e.getChild("num").getValue());
                String path = e.getChild("path").getValue();
                downloadToolArray.add(new ImgDownloadTool(url, regex, num, path));
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ImgDownloadTool> getDownloadToolArray()
    {
        return downloadToolArray;
    }

    public static void main(String[] args)
    {
        ImgDownloadToolConfiguration configuration = ImgDownloadToolConfiguration.getInstance();
        configuration.init("DownloadConfiguration.xml");

        for (ImgDownloadTool tool : configuration.getDownloadToolArray())
            System.out.println(tool);
    }
}
