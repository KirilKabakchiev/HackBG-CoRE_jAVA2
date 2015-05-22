package com.print;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Utils {


    private String jsonURL;

    public Utils(String jsonURL) {
        setJsonURL(jsonURL);
    }
    
    public String prettyPrint() {
        String ugglyJson = null;
        try {
            ugglyJson = getContent(jsonURL);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(ugglyJson);
        
        return gson.toJson(je);
    }
    
    private String getContent(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        StringBuilder content = new StringBuilder();
        
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String line = null;
            
            try(BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));) {
                while ((line = br.readLine()) != null) {
                    content.append(line);
                }
            }
            
        } finally {
            response.close();
        }
        
        return content.toString();
    }
    
    public String getJsonURL() {
        return jsonURL;
    }

    public void setJsonURL(String jsonURL) {
        this.jsonURL = jsonURL;
    }
}
