package httptool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LJ on 2015/12/17.
 * Describe : This class is responsible for build RequestProperty of http request
 */
public class RequestProperty extends HashMap<String, String>
{
    /**
     * Put new key-value to Request Property
     * @param key
     * @param value
     */
    public void putProperty(String key, String value)
    {
        this.put(key, value);
    }

    /**
     * Build Common Request Property with:
     * accept : *\/*
     * connection : Keep-Alive
     * user-agent : Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)
     * @return
     */
    public static RequestProperty buildCommonRequestProperty()
    {
        RequestProperty requestProperty = new RequestProperty();
        requestProperty.putProperty("accept", "*/*");
        requestProperty.putProperty("connection", "Keep-Alive");
        requestProperty.putProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return requestProperty;
    }

}
