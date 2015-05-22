package com.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherThingy {
    private static final String CITIES = "http://pastebin.com/skDTtbQL";
    private static final String TOCRAWL = "http://api.openweathermap.org/data/2.5/weather?q="; //+CITY

    private static String getContent(URL url) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String b = "";
            while ((b = input.readLine()) != null) {
                sb.append(b).append("\n");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getContent(new URL(CITIES)));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
