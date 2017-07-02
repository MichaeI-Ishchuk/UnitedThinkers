package com.unitedthinkers.tests;


import com.unitedthinkers.utils.BaseTest;
import org.databene.benerator.anno.Source;
import org.databene.feed4junit.Feeder;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.*;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created by nata on 27.06.2017.
 */


@RunWith(Feeder.class)
public class AllTests extends BaseTest {


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
        {   dataAll.append("&accountData" + "=" + accountData);
            dataAll.append("&terminalId" + "=" + terminalId);
        }
        if (cscResponseCode!=null)
        {   dataAll.append("&csc" + "=" + cscCode);
        }
        wr.write(dataAll.toString());
        wr.flush();
        wr.close();

        Map<String, String> map=getParametrs();

        if (map.containsKey("cscResponseCode"))
        {
            assertTrue((cscResponseCode.equals(map.get("cscResponseCode"))));
        }
        else {
            if (map.containsKey("avsResponseCode")) {

                assertTrue((avsResponseCode.equals(map.get("avsResponseCode"))));
            } else {
                if (map.containsKey("responseCode")) {
                    assertTrue((responseCode.equals(map.get("responseCode"))));

                }else
                    assertFalse("Error from server", "exception".equals(map.get("responseType")));
            }

        }

    }

    @Test
    @Source(uri = "test2.xlsx", emptyMarker = "<empty>")
    public void doPost2(String accountId, String amount, String accountType,
            String transactionIndustryType,
            String customerAccountCode, String transactionCode,
            String accountData, String terminalId, String responseCode,
            String avsResponseCode, String cscResponseCode, String cscCode) throws Exception

    {
        StringBuffer dataAll = new StringBuffer();
        dataAll.append(data + "&accountId" + "=" + accountId);
        dataAll.append("&amount" + "=" + amount);
        dataAll.append("&accountType" + "=" + accountType);
        dataAll.append("&transactionIndustryType" + "=" + transactionIndustryType);
        dataAll.append("&customerAccountCode" + "=" + customerAccountCode);
        dataAll.append("&transactionCode" + "=" + transactionCode);
        if (avsResponseCode != null) {
            dataAll.append("&accountData" + "=" + accountData);
            dataAll.append("&terminalId" + "=" + terminalId);
        }
        if (cscResponseCode != null) {
            dataAll.append("&csc" + "=" + cscCode);
        }
        wr.write(dataAll.toString());
        wr.flush();
        wr.close();

        Map<String, String> map = getParametrs();


            if (map.containsKey("cscResponseCode")) {
                assertTrue((cscResponseCode.equals(map.get("cscResponseCode"))));
              } else {
                if (map.containsKey("avsResponseCode")) {

                    assertTrue((avsResponseCode.equals(map.get("avsResponseCode"))));
                } else {
                    if (map.containsKey("responseCode")) {
                        assertTrue((responseCode.equals(map.get("responseCode"))));

                    }else
                       assertFalse("Error from server", "exception".equals(map.get("responseType")));
                }

            }


    }


}
