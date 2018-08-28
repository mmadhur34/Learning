package com.home;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HTTPWebserviceTest {
    static Logger logger = Logger.getLogger(HTTPWebserviceTest.class);
    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://www.webservicex.net/stockquote.asmx?op=GetQuote");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = null;
        while ((line = rd.readLine()) != null) {
            logger.info(line);
        }
    }
}
