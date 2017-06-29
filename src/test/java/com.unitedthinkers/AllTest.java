package com.unitedthinkers;


import org.databene.benerator.anno.Source;
import org.databene.feed4junit.Feeder;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created by nata on 27.06.2017.
 */

@RunWith(Feeder.class)

public class AllTest {

    private String url = "https://sandbox-secure.unitedthinkers.com/gates/xurl?";
    private static int CONNECT_TIMEOUT = 10 * 1000;
    private static int READ_TIMEOUT = 1 * 60 * 1000;
    private final String USER_AGENT = "Mozilla/5.0";
    private String data = "&requestType=sale"
            + "&userName=test_api_user"
            + "&password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1";

    @Test
    @Source(uri = "test.xlsx", emptyMarker = "<empty>")
    public void doPost(String accountId, String amount, String accountType,
            String transactionIndustryType, String holderType,
            String holderName, String accountNumber, String accountAccessory,
            String street, String city, String state, String zipCode,
            String customerAccountCode, String transactionCode,
            String accountData, String terminalId, String responseCode,
            String avsResponseCode, String cscResponseCode, String cscCode) throws IOException

    {

        StringBuffer dataAll = new StringBuffer();
        InputStream stream = null;

        dataAll.append(data + "&accountId" + "=" + accountId);
        dataAll.append("&amount" + "=" + amount);
        dataAll.append("&accountType" + "=" + accountType);
        dataAll.append("&transactionIndustryType" + "=" + transactionIndustryType);
        dataAll.append("&holderType" + "=" + holderType);
        dataAll.append("&holderName" + "=" + holderName);
        dataAll.append("&accountNumber" + "=" + accountNumber);
        dataAll.append("&accountAccessory" + "=" + accountAccessory);
        dataAll.append("&street" + "=" + street);
        dataAll.append("&city" + "=" + city);
        dataAll.append("&state" + "=" + state);
        dataAll.append("&zipCode" + "=" + zipCode);
        dataAll.append("&customerAccountCode" + "=" + customerAccountCode);
        dataAll.append("&transactionCode" + "=" + transactionCode);
      if (avsResponseCode!=null)
        {
            dataAll.append("&accountData" + "=" + accountData);
            dataAll.append("&terminalId" + "=" + terminalId);
        }
      if (cscResponseCode!=null)
        {
            dataAll.append("&csc" + "=" + cscCode);
        }

        System.out.println(dataAll.toString());
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestMethod("POST");
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

        wr.write(dataAll.toString());
        wr.flush();
        wr.close();

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            stream = con.getInputStream();
        } else {
            stream = con.getErrorStream();
        }


        BufferedReader in = new BufferedReader(
                new InputStreamReader(stream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        Map<String, String> map = new HashMap<String, String>();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

        }
        in.close();

        String parametrsAll[] = response.toString().split("&");
        for (String d : parametrsAll
                ) {
            String paramets[] = d.split("=");
            if (paramets.length > 1) {
                map.put(paramets[0], paramets[1]);
            }
        }

        //print result
        System.out.println(response.toString());

        if (map.containsKey("cscResponseCode"))
        {
            assertTrue((cscResponseCode.equals(map.get("cscResponseCode"))));
        }
        else {
            if (map.containsKey("avsResponseCode")) {

                assertTrue((avsResponseCode.equals(map.get("avsResponseCode"))));
            } else {
                assertTrue((responseCode.equals(map.get("responseCode"))));
            }

        }

    }

}
