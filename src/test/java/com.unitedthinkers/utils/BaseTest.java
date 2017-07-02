package com.unitedthinkers.utils;



import org.junit.Before;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nata on 01.07.2017.
 */



public class BaseTest {

    protected String url = "https://sandbox-secure.unitedthinkers.com/gates/xurl?";
    protected static int CONNECT_TIMEOUT = 10 * 1000;
    protected static int READ_TIMEOUT = 1 * 60 * 1000;
    protected final String USER_AGENT = "Mozilla/5.0";
    protected String data = "&requestType=sale"
            + "&userName=test_api_user"
            + "&password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1";

    protected URL obj;
    protected HttpsURLConnection con;
    protected OutputStreamWriter wr;

   @Before
    public void setUp() throws IOException

    {
        obj = new URL(url);
        con = (HttpsURLConnection) obj.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestMethod("POST");
        wr = new OutputStreamWriter(con.getOutputStream());

      }



    public Map<String, String> getParametrs() throws IOException

    {
        InputStream stream = null;
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            stream = con.getInputStream();
        } else {
            stream = con.getErrorStream();
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(stream));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        Map<String, String> map = new HashMap<String, String>();

        String parametrsAll[] = response.toString().split("&");
        for (String d : parametrsAll
                ) {
            String paramets[] = d.split("=");
            if (paramets.length > 1) {
                map.put(paramets[0], paramets[1]);
            }
        }
        return map;
    }
}
