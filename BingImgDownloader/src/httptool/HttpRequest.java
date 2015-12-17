package httptool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by LJ on 2015/12/17.
 * Describe : This class is responsible for complete http request
 */
public class HttpRequest
{
    private RequestProperty requestProperty = null;

    /**
     * No argument constructor<br>
     * Use this method to build HttpRequest will not contain any request property
     */
    public HttpRequest()
    {
        requestProperty = new RequestProperty();
    }

    /**
     * Use this method to build HttpRequest will set request property
     * @param requestProperty Some request property you want set
     *                        @see RequestProperty
     */
    public HttpRequest(RequestProperty requestProperty)
    {
        this.requestProperty = requestProperty;
    }

    /**
     * Send get request
     * @param urlString String of url
     * @return The Server response text
     * @throws IOException  if no protocol is specified, or an unknown protocol is found, or spec is null.
     */
    public String sendGet(String urlString) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection connection = buildConnection(url);
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null)
            sb.append(line).append("\n");
        in.close();
        return sb.toString();
    }

    /**
     * Build connection and set request property
     * @param url Connection url
     * @return URLConnection
     * @throws IOException if an I/O exception occurs.
     */
    private URLConnection buildConnection(URL url) throws IOException
    {
        URLConnection connection = url.openConnection();
        for (Map.Entry<String, String> entry : requestProperty.entrySet())
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        return connection;
    }
}
